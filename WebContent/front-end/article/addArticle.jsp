<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.mem.model.*"%>


<% 	
	String account =(String) session.getAttribute("account");
	String memno = (String) session.getAttribute("memno");
	if (account == null) {
	session.setAttribute("location", request.getRequestURI());
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	return;
	}
	
	TripService tripSvc = new TripService();
	List<TripVO> list = tripSvc.getAll();
	pageContext.setAttribute("list", list);
	
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list2 = articleSvc.getAllByMemno(memno);
	pageContext.setAttribute("list2", list2);
%>
<%-- <%=list2 %> --%>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>insertArticle.jsp</title>
<style>

</style>
</head>
<body style="background-color:#eafafa; font-family:微軟正黑體;">
<%@ include file="/nav-f"%>
<jsp:useBean id="articleVO" scope="page" class="com.article.model.ArticleVO" />
<div class="container-fluid">
<span style="font-size:40px; font-color:#ffb433"><strong>建立遊記</strong></span>

<!--     		<div class="col-md-8"> -->
<!-- 			<div class="" style="font-size:25px;"><span>建立遊記</span></div> -->
		
<!-- 			<div class="" style="border:2px #ccc solid; border-radius:10px;"> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" enctype="multipart/form-data" name="form1"> --%>
		
<!-- 				<span>遊記名稱：<font color=red><b>*</b></font></span> -->
<!-- 				<div class="form-title"> -->
<!-- 					<input type="text" name="articletitle" maxlength="50" -->
<%-- 						value="<%=(articleVO == null) ? "為您的遊記起個名字" : articleVO.getArticletitle()%>" placeholder="為您的遊記取個名字"> --%>
<!-- 				</div> -->
		
<!-- 				<span>旅行日期：<font color=red><b>*</b></font></span> -->
			
<!-- 					<input type="text" name="dayofstart" id="f_date1" placeholder="請選擇出發日期"><span> ～ </span><input type="text" name="dayofend" id="f_date2" placeholder="請選擇回程日期"><span>共<input -->
<%-- 						type="text" name="daysofstaying" id="f_date3" size="20" value="<%=(articleVO == null) ? "" : articleVO.getDaysofstaying()%>">天 --%>
<!-- 					</span> -->
				
				
<!-- 				<input type="hidden" name="tripno" value=""> -->
<!-- 				<input type="hidden" name="articlestatus" value="1"> -->
<!-- 				<input type="hidden" name="timeofviews" size="40" value="777"> -->
				
<!-- 				<input type="hidden" name="dayoflastedit" value=""> -->
		
<!-- 				<div class="article-kindoftrip"><span>請選擇旅遊類型：<font color=red><b>*</b></font></span> -->
<!-- 					<select size="1" name="kindoftrip"> -->
<!-- 						<option value="0">單獨旅行 -->
<!-- 						<option value="1">情侶出遊 -->
<!-- 						<option value="2">家庭親子 -->
<!-- 						<option value="3">朋友出遊 -->
<!-- 						<option value="4">商務旅遊 -->
<!-- 						<option value="5">其他 -->
<!-- 					</select> -->
<!-- 				</div> -->
				
<!-- 				<span>封面照片：</span> -->
<%-- 				<div class="article-pic"><input type="file" id="imgInp" name="articlepic" size="40" accept="image/gif , image/jpeg ,image/png"><img id="blah" width="100px" height="100px"  style="border:1px blue dashed;border-radius:20px;"src="<%=request.getContextPath()%>/images/articlenoPic.png" /></div> --%>
				
				
				
<!-- 				<div class="insert-button"> -->
<!-- 					<input type="hidden" name="action" value="insert">  -->
<%-- 					<input type="hidden" name="memno" value="${account.getMemNo()}"> --%>
<!-- 					<input type="submit" value="送出新增"> -->
<!-- 				</div> -->
				
<%-- 						錯誤列表 --%>
<%-- 							<c:if test="${not empty errorMsgs}"> --%>
<!-- 								<font style="color:red">請修正以下錯誤:</font> -->
<!-- 								<ul> -->
<%-- 									<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 										<li style="coloe:red">${message}</li> --%>
<%-- 									</c:forEach> --%>
<!-- 								</ul> -->
<%-- 							</c:if>	 --%>
<!-- 					</FORM> -->
<!-- 				</div> -->
<!-- 		    </div> -->
		    
<!-- 第二個表格用按鈕JS切換，撈已經發布的個人行程 -->
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-5" style="margin-top:60px;">
			<span style="font-size:25px;"><strong>以行程匯入</strong></span>
	    	<% int i = 0; %>
	    	<c:forEach var="tripVO" items="${list}">
	    	<c:if test="${tripVO.memno.equals(memno)}">
	    	<%i++;%>
	    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" enctype="multipart/form-data">
<!-- 	    	<div class="add-article-info" style="display:flex; text-align:center;"> -->
<!-- 				<div class="article-pic"> -->
<%-- 					<img id="blah" width="200px;" height="200px;"  style="border:1px;"src="<%=request.getContextPath()%>/front-end/article/images/noPic.gif" /><br><br> --%>
<!-- 					<input type="file" id="imgInp" name="articlepic" size="40" accept="image/gif , image/jpeg ,image/png" data-toggle="tooltip" data-placement="top" title="上傳封面照"> -->
<!-- 				</div> -->
<!-- 		    	<div class="article-title" style="font-size:20px;"> -->
<%-- 					<input type="text" name="articletitle" maxlength="50" value="${tripVO.tripname}" placeholder="給您的遊記起個名字"><br><br><br> --%>
<%-- 					<span>${tripVO.tripstartday} > ${tripVO.tripendday}</span> --%>
<!-- 				</div> -->
<!-- 		    </div> -->
		    <table>
　			<tr>
　			<td rowspan="2">
					<img id="blah<%=i%>" width="300px;" height="200px;"  style="border:1px;"src="<%=request.getContextPath()%>/front-end/article/images/noPic.gif" /><br><br>
					<input type="file" id="imgInp<%=i%>" name="articlepic" size="40" accept="image/gif , image/jpeg ,image/png" data-toggle="tooltip" data-placement="top" title="上傳封面照">
			</td>
　			<td height="80%">
				遊 記 標 題 : <input type="text" name="articletitle" maxlength="50" value="${tripVO.tripname}" placeholder="給您的遊記起個名字"><br><br><br>
				<span><img src="<%=request.getContextPath()%>/front-end/article/images/icon_calender.png">${tripVO.tripstartday} > ${tripVO.tripendday}</span>
			</td>
　			</tr>
　			<tr>
　			<td height="20%">
				<input type="hidden" name="tripno" value="${tripVO.tripno}">
				<input type="hidden" name="dayofstart" value="${tripVO.tripstartday}">
				<input type="hidden" name="dayofend" value="${tripVO.tripendday}">
				<input type="hidden" name="daysofstaying" value="${tripVO.tripdays}">	
				<input type="hidden" name="articlestatus" size="2"value="1">
				<input type="hidden" name="timeofviews" size="4" value="1">
				<input type="hidden" name="dayoflastedit" value="">
				<input type="hidden" name="kindoftrip" value="${tripVO.kindoftrip}">
				<input type="hidden" name="memno" value="${memno}">
				<input type="hidden" name="action" value="Insert_By_Tripno">
				<input type="submit" class="btn btn-warning" value="&nbsp;&nbsp;選 擇&nbsp;&nbsp;">
			</td>
　			</tr>
			</table>
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
		    </FORM>
	    	<hr style="border: 0; height: 1px; background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));">
	    	</c:if>
	    	</c:forEach>
	    	<c:if test="<%=i == 0%>">
				<center>
					<font style="color: red; font-size:50px;">沒有建立行程，<br>給你錢快點出去玩！</font>
				</center>
			</c:if>
	   	</div>
	
	
		<div class="col-md-3" style="margin-top:60px; height:50%;">
			<span style="font-size:25px;"><strong>已發佈的遊記</strong></span>
			<div class="table" style="margin-top:30px;">
					<% int r = 0;%>
				<c:forEach var="articleVO" items="${list2}">
					<%r++; %>
					<div class="tr" style="margin-top:30px; display: table-row;">
						<div class="td" style=" display: table-cell;">
							<span style="font-size:18px;"><b>${articleVO.articletitle}</b></span>
						</div>
						<div class="td" style=" display: table-cell;">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/articleDetails/ArticleDetails.do">
								<c:if test="${articleVO.articlestatus!=2}">
									<input type="submit" class="btn btn-warning" value="修改">
								</c:if>
								<c:if test="${articleVO.articlestatus==2}">
								<font style="color: red;">冰箱<br>無法更改
								</font>
								</c:if>
								<input type="hidden" name="articleno" value="${articleVO.articleno}"> 
								<input type="hidden" name="action" value="getArticle_For_Update">
							</FORM>
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<%-- 							<input type="hidden" name="articleno" value="${articleVO.articleno}"> --%>
<!-- 							<input type="hidden" name="action" value="delete"> -->
<!-- 							<input type="submit" value="刪除"> -->
<!-- 	    				</FORM> -->
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="<%=r == 0%>">
				<center>
					<font style="color: red; font-size:50px;">沒有可編輯的遊記</font>
				</center>
			</c:if>
		</div>
	</div>
</div>
	
    	


</body>

<% 
  java.sql.Date dayofstart = null;
  try {
	   dayofstart = articleVO.getDayofstart();
   } catch (Exception e) {
	   dayofstart = new java.sql.Date(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Date dayofend = null;
  try {
	   dayofend = articleVO.getDayofend();
   } catch (Exception e) {
	   dayofend = new java.sql.Date(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function(){
		 $('#f_date1').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#f_date2').val()?$('#f_date2').val():false
		   })
		  },
		  timepicker:false
		 });
		 
		 $('#f_date2').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#f_date1').val()?$('#f_date1').val():false
		   })
		  },
		  timepicker:false
		 });
	});
	
	
	 $("#f_date1").change(function(){

         var start=$("#f_date1").val();
          var end=$("#f_date2").val();
          
          start=start.replace(/-/g,"/");
  
          var startdate=new Date(start);
          end=end.replace(/-/g,"/");
          var enddate=new Date(end);
  
          var time=enddate.getTime()-startdate.getTime();
          var days=parseInt(time/(1000 * 60 * 60 * 24))+1;
          console.log(days);
          $("#f_date3").val(days);
      });  
     
     $("#f_date2").change(function(){

         var start=$("#f_date1").val();
          var end=$("#f_date2").val();
          
          start=start.replace(/-/g,"/");
  
          var startdate=new Date(start);
          end=end.replace(/-/g,"/");
          var enddate=new Date(end);
  
          var time=enddate.getTime()-startdate.getTime();
          var days=parseInt(time/(1000 * 60 * 60 * 24))+1;
          console.log(days);
          $("#f_date3").val(days);
      }); 
	
	
</script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
</html>