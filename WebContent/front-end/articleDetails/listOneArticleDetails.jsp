<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.articleDetails.model.*"%>
<%@ page import="com.tripDetails.model.*"%>
<%	

	String account =(String) session.getAttribute("account");
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
	
	List<TripDetailsVO> list3 = (List<TripDetailsVO>)request.getAttribute("list3");
	
	MemService memSvc = new MemService();
	List<MemVO> list4 = memSvc.getAll();
	pageContext.setAttribute("list4",list4);
	
	
	String articleno = request.getParameter("articleno");
	ArticleService articleSvc = new ArticleService();
	ArticleVO articleVO = articleSvc.getOneArticle(articleno);
	//計數器
	Integer timeofviews = 
	(Integer)session.getAttribute("timeofviews");
	if(  timeofviews==null || timeofviews == 0 ){
		//第一次來
		timeofviews = 1;
		}else{
		//不是第一次來
		timeofviews += 1;
	}
	
	session.setAttribute("timeofviews", timeofviews);
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
	
<title>listOneArticle</title>
<style>
#li{
	display:block;
}
</style>

</head>
<body style="font-family:微軟正黑體;">
<%@ include file="/nav-f"%>
<div class="container-fluid">

		<div class="row" style="margin-top:30px;">
			<div class="col-md-3">			
			</div>
			<div class="col-md-6" id="titlediv" style="font-size:30px; text-shadow: 2px 2px #BEBEBE;">
				<span><strong><%=articleVO.getArticletitle()%></strong></span>
			</div>
			<div class="col-2">			
			</div>
		</div>
		
		<div class="row" style="margin-top:30px;">
			<div class="col-md-3">			
			</div>
			<div class="col-md-6">
				<ul class="nav">
				  	<li class="nav-item">
				    	<a class="nav-link active" href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon_member.png">
				    		<%
					 		for(MemVO memVO : list4){
					 			if(articleVO.getMemno().equals(memVO.getMemNo())){%> 
									<%=memVO.getMemName()%>
								<%}%>
							<%} %>
				    	</a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link active" href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon_calender.png"><%=articleVO.getDayofcreate()%></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon_views.png"><%=7777 + timeofviews%></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"></a>
				  	</li>
				  
				  	<li class="nav-item">
				    	<a class="nav-link" href="https://www.facebook.com/ckk.wayne"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-facebook-48.png"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="https://docs.google.com/spreadsheets/d/1YhNq7k-ivfjUU2YqWv_0-ZLEYVw8VPNL9y7x53oi0Yc/edit#gid=0"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-google-48.png"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="https://www.instagram.com/siao_mi/?hl=zh-tw"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-instagram-48.png"></a>
				  	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-twitter-48.png"></a>
				  	</li>
				</ul>
			</div>
			<div class="col-md-3">			
			</div>
		</div>
		
	<div class="row" style="margin-top:20px;">
		<div class="col-2" id="leftdiv" style="text-align:right; position:fixed">
			<div class="btn-group-vertical" role="group" aria-label="Basic example">
			<br>
			<input type="button" id="onme"  class="btn btn-info" value="TOP" onclick="window.location.href='#'"></input>
			<br><br>
			<% int j=1;
			for(ArticleDetailsVO a : list){
				if(a.getTripdayorder()==j) {
			%>
			<input type="button" id="onme"  class="btn btn-info" value="D<%=a.getTripdayorder()%>" onclick="window.location.href='#go<%=a.getTripdayorder()%>'"></input>
			<br><br>
				<% j++;}%>	
			<%}j=1;%>
			</div>
		</div>
		<div class="col-md-6" style="border:1px solid #DCDCDC; border-radius: 5px; margin: auto;display: flex;justify-content: center;">
			<div class="articledetails-all">
			<div class="tripdetails-all">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" style="margin-bottom: 0px;" enctype="multipart/form-data" name="form1">
				<input type="submit" value="複製行程" class="btn btn-primary btn-lg">
				<input type="hidden" name="action" value="copyTrip">
				<input type="hidden" name="memno" value="<%=memno%>">
				<input type="hidden" name="tripVO999" value="<%=articleVO.getTripno()%>">
			</FORM>
			</div>
			<%
	 		int i=1;
	 		for(ArticleDetailsVO a : list){
	 		%> 
				<div class="" style="margin-top:20px; text-align:left;">
					<div class="articledetails-tripdayorder"><h4>
					<%if(a.getTripdayorder()==i) {%>
						<a name="go<%=a.getTripdayorder()%>"></a>
						<hr style="border: 0; height: 1px; background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));">
						Day<%=a.getTripdayorder()%>
					<% i++;}%>
					</h4></div>
					<div class="articledetails-spotno">
						<%
	 					for(SpotListVO s : list2){
	 						if(a.getSpotno().equals(s.getSpotNo())){%> 
								<%=s.getSpotName()%>
							<%}%>
						<%} %>
						<div class="articledetails-articlenotes">
							<c:if test="<%=a.getArticlenotes() == null%>"><c:out value=""></c:out></c:if>
							<c:if test="<%=a.getArticlenotes() != null%>"><c:out value="<%=a.getArticlenotes()%>"></c:out></c:if>
						</div>
						<div class="articledetails-pic-picnote" style="border:1px solid #DCDCDC; border-radius: 5px; border-top: none; border-left: none;">
							<div class="articledetails-pic">
								<c:if test="<%=a.getArticledetailspic()==null%>"><img src="" style=""></c:if>
								<c:if test="<%=a.getArticledetailspic()!=null%>"><img src="<%=request.getContextPath()%>/articleDetails/ArticleDetailsPic?articleno=<%=a.getArticleno()%>&spotno=<%=a.getSpotno()%>" style="border:1px blue;width:720px;height:480px;"></c:if>
							</div>
							<div class="articledetails-picnote">
								<c:if test="<%=a.getPicnote()!=null%>"><c:out value="<%=a.getPicnote() %>"></c:out></c:if>
								<c:if test="<%=a.getPicnote()==null%>"><c:out value=""></c:out></c:if>
							</div>
						</div>
					</div>
				</div>
			<%}i=1;%>
			</div>
		</div>
	</div>
</div>
</body>
</html>