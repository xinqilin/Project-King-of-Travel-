<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<% 
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
    
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<!-- Required meta tags -->
<meta charset="utf-8">
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

<title>b_update_article_input.jsp</title>



</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<%-- <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/article/article.do" name="form1"> --%>
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td>遊記編號:<font color=red><b>*</b></font></td> -->
<%-- 			<td><%=articleVO.getArticleno()%></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>會員編號:<font color=red><b>*</b></font></td> -->
<%-- 			<td><%=articleVO.getMemno()%></td> --%>
<%-- 			<td><input type="hidden" name="memno" value="<%=articleVO.getMemno()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>行程編號:<font color=red><b>*</b></font></td> -->
<%-- 			<td><%=articleVO.getTripno()%></td> --%>
<%-- 			<td><input type="hidden" name="tripno" value="<%=articleVO.getTripno()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>遊記名稱</td> -->
<%-- 			<td><input type="text" name="articletitle" size="66" value="<%=articleVO.getArticletitle()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>總天數</td> -->
<%-- 			<td><input type="text" name="daysofstaying" size="4" value="<%=articleVO.getDaysofstaying()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>遊記起始日</td> -->
<%-- 			<td><input type="text" name="dayofstart" id="f_fate1" value="<%=articleVO.getDayofstart()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>遊記結束日</td> -->
<%-- 			<td><input type="text" name="dayofend" id="f_date2" value="<%=articleVO.getDayofend()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>遊記狀態</td> -->
<%-- 			<td><input type="text" name="articlestatus" value="<%=articleVO.getArticlestatus()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>遊記編輯日</td> -->
<%-- 			<td><%=articleVO.getArticlestatus()%></td> --%>
<%-- 			<td><input type="hidden" name="tripno" value="<%=articleVO.getArticlestatus()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>瀏覽次數</td> -->
<%-- 			<td><input type="text" name="timeofviews" value="<%=articleVO.getTimeofviews()%>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>出遊類型</td> -->
<!-- 			<td> -->
<!-- 			<select size="1" name="kindoftrip"> -->
<!-- 				<option value="0">單獨旅行 -->
<!-- 				<option value="1">情侶出遊 -->
<!-- 				<option value="2">家庭親子 -->
<!-- 				<option value="3">朋友出遊 -->
<!-- 				<option value="4">商務旅遊 -->
<!-- 				<option value="5">其他 -->
<!-- 			</select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- 	<br> -->
<!-- 	<input type="hidden" name="action" value="update"> -->
<%-- 	<input type="hidden" name="articleno" value="<%=articleVO.getArticleno()%>"> --%>
<!-- 	<input type="submit" value="送出修改"></FORM> -->

		<div class=""
			style="width: 600px; height: 600px; margin: 0 auto; border: 3px limegreen dashed; padding: 10px;">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/article/article.do"
				 enctype="multipart/form-data" name="form1">

				<input type="hidden" name="articleno"
					value="<%=articleVO.getArticleno()%>"> <input type="hidden"
					name="memno" value="<%=articleVO.getMemno()%>"> <input
					type="text" name="tripno" value="<%=articleVO.getTripno()%>">

				<span>遊記名稱：<font color=red><b>*</b></font></span>
				<div class="form-title">
					<input type="text" name="articletitle" size="66"
						value="<%=articleVO.getArticletitle()%>">
				</div>

				<span>旅行日期：<font color=red><b>*</b></font></span>
				<div class="form-date">
					<input type="text" name="dayofstart" id="f_date1"
						value="<%=articleVO.getDayofstart()%>"><span> ～ </span><input
						type="text" name="dayofend" id="f_date2"
						value="<%=articleVO.getDayofend()%>"><span>共<input
						type="text" name="daysofstaying" size="4"
						value="<%=articleVO.getDaysofstaying()%>">天
					</span>
				</div>


				<input type="text" name="articlestatus"
					value="<%=articleVO.getArticlestatus()%>"> 
					
					<input type="text" name="dayoflastedit" id="f_date3"
					value="<%=articleVO.getDayoflastedit()%>"> 
					
					<input type="hidden" name="timeofviews"
					value="<%=articleVO.getTimeofviews()%>"> 
					
					<br><span>請選擇旅遊類型：<font
					color=red><b>*</b></font></span>
				<div class="article-kindoftrip">
					<select size="1" name="kindoftrip">
						<option value="0">單獨旅行
						<option value="1">情侶出遊
						<option value="2">家庭親子
						<option value="3">朋友出遊
						<option value="4">商務旅遊
						<option value="5">其他
					</select>
				</div>

				<span>想放的封面照：</span>
				<input type="file" name="trippic" size="45" id="imgInp"
						accept="image/gif , image/jpeg ,image/png "
						style="border: 0; background-color: #c00; color: #fff; border-radius: 10px; cursor: pointer;">


						<c:if test="${articleVO.articlepic==null}">
							<img id="blah"
								src="<%=request.getContextPath()%>/images/arcitlenoPic.png"
								style="border: 1px blue dashed; width: 100px; border-radius: 20px; height: 100px;">
						</c:if> <c:if test="${articlepicVO.articlepicpic!=null}">
							<img
								src="<%=request.getContextPath()%>/article/articlePic?articleno=${articlepicVO.articlepicno}"
								id="blah"
								style="border-radius: 20px; border: 1px blue dashed; width: 100px; height: 100px;">
						</c:if></td>
				
				<input type="hidden" name="timeofviews"
					value="<%=articleVO.getDayofcreate()%>"> 
				
				<div class="update-button">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="articleno"
						value="<%=articleVO.getArticleno()%>"> <input
						type="submit" value="送出修改">
				</div>
			</FORM>
		</div>




</body>

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
        
</script>

 <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
</html>