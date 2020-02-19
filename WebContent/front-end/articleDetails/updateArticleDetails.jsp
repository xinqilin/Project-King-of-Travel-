<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.articleDetails.model.*"%>

<%
	Object account = session.getAttribute("account");
	String memno = (String) session.getAttribute("memno");
	if (account == null) {
	session.setAttribute("location", request.getRequestURI());
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	return;
	}
	
	List<ArticleDetailsVO> list = (List<ArticleDetailsVO>)request.getAttribute("list");
	
	SpotListService spot = new SpotListService();
	List<SpotListVO> list2 = spot.getAllNoPic();
	pageContext.setAttribute("list2", list2);
%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- <meta charset="utf-8"> -->
<title>Insert title here</title>
</head>
<body>
<%@ include file="/nav-f"%>
<div class="container-fluid">

	
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-12">
<!-- 			<div> -->
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;"> --%>
<!-- 			    <input type="submit" value="儲存草稿" class="btn btn-primary btn-lg"> -->
<!-- 			    <input type="hidden" name="articleno"  value=""> -->
<!-- 			    <input type="hidden" name="action"	value="save"></FORM> -->
<!-- 			</div> -->
<!-- 			<div> -->
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/articleDetails/ArticleDetails.do" style="margin-bottom: 0px;"> --%>
<!-- 				<input type="submit" value="發表文章" class="btn btn-primary btn-lg"> -->
<!-- 			    <input type="hidden" name="articleno"  value=""> -->
<!-- 			    <input type="hidden" name="action"	value="update_ArticleDetails"></FORM> -->
<!-- 			</div>	 -->
		</div>
	</div>

	
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-3" id="leftdiv">

		</div>		
	
		<div class="col-md-6" id="aa"align="center" style=" height:1000px;">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/articleDetails/ArticleDetails.do" style="margin-bottom: 0px;" enctype="multipart/form-data" name="form1">
			<input type="submit" value="發表文章" class="btn btn-primary btn-lg">
			<input type="hidden" name="action" value="update_ArticleDetails">
			<div class="table">
				<%
			 	int i=1;
			 	for(ArticleDetailsVO a : list){
			 	%> 
				<div class="tr" style=" display: table-row;">
					<div class="td" style=" display: table-cell;">
		<!-- 			天數 -->
					<h4>
					<%if(a.getTripdayorder()==i) {%>
							<a href="go<%=a.getTripdayorder()%>"></a>
					第<%=a.getTripdayorder()%>天
					<% }%></h4>
					</div>
		<!-- 			景點 -->
					<div class="td" style="border-bottom: 2px solid orange; font-size:25px;">
					<br><br>
					<%
			 		for(SpotListVO s : list2){
			 			if(a.getSpotno().equals(s.getSpotNo())){%> 
							<%=s.getSpotName()%>
						<%}%>
					<%} %>
					<br><br>
					</div>
		<!-- 			景點敘述 -->
					<div class="md-form td">
					<%if(a.getArticlenotes()==null){ %>
			  			<textarea id="textarea-char-counter" name="articlenotes" class="form-control md-textarea" placeholder="寫點猛的..."  style="width:500px;height:200px;" value=""></textarea>
			  		<%}if(a.getArticlenotes()!=null) {%>
			  			<textarea id="textarea-char-counter" name="articlenotes" class="form-control md-textarea" placeholder="寫點猛的..."  style="width:500px;height:200px;"><%=a.getArticlenotes()%></textarea>
			  		<%} %>	
			  			<label for="textarea-char-counter"></label>
					</div>
		<!-- 			插入照片 -->
					<div class="file-field td">
				    	<%if(a.getArticledetailspic()==null){ %>
				    		<img id="blah<%=i%>" width="300px;" height="200px;" style="border:1px;"src="<%=request.getContextPath()%>/front-end/article/images/noPic.gif" />
				      	<%}if(a.getArticledetailspic()!=null) {%>
<img  width="300px;" height="200px;" style="border:1px;"src="<%=request.getContextPath()%>//articleDetails/ArticleDetailsPic?articleno=<%=a.getArticleno() %>&spotno=<%=a.getSpotno() %>" /> 	
				      	<%} %>
				      	
				      	<input type="file" id="imgInp<%=i%>" name="articledetailspic" accept="image/gif , image/jpeg ,image/png" data-toggle="tooltip" data-placement="top" title="上傳景點照" onClick="readURL<%=i%>()">
				      	
				      	
				     </div>
		<!-- 			照片敘述 -->
					<div class="md-form md-bg td">
				  		<%if(a.getPicnote()!=null){ %>
				  		<input type="text" id="formBg1" name="picnote" class="form-control" placeholder="描述這張照片" value="<%=a.getPicnote()%>">
				  		<%} %>
				  		<%if(a.getPicnote()==null){ %>
				  		<input type="text" id="formBg1" name="picnote" class="form-control" placeholder="描述這張照片" value="">
				  		<%} %>
				  		<label for="formBg1">
				  		</label>
					</div>
					<div class="td">
					<input type="hidden" name="articleno" value="<%=a.getArticleno()%>">
					<input type="hidden" name="spotno" value="<%=a.getSpotno()%>">
					<input type="hidden" name="articletriporderby" value="<%=a.getArticletriporderby()%>">
					<input type="hidden" name="tripdayorder" value="<%=a.getTripdayorder()%>">
					</div>
				</div>
				<script>
					function readURL<%=i%>(input){
	  					if(input.files && input.files[0]){
	    					var reader = new FileReader();
	    					reader.onload = function (e) {
	      	 				$("#blah<%=i%>").attr('src', e.target.result);
	    					}
	    				reader.readAsDataURL(input.files[0]);
	  					}
					}
	
					$("#imgInp<%=i%>").change(function(){
						  readURL<%=i%>(this);
					});
				</script>
				<%i++;}%>	
			</div>
			</FORM>
		</div>		
		<div class="col-3" id="leftdiv" style="text-align:right; position:fixed">
		</div>
	</div>			
</div>

</body>

    
</html>