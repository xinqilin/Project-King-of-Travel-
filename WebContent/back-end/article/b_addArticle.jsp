<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	Object account = session.getAttribute("backaccount"); 
	if (account == null) { 
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/b_login.jsp"); 
	return;
	}
%>

<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>

	<div class="container-fluid">

		<div class="row" style="margin-top: 10px;">
			<div class="col-md-12">
				<a href=""
					style="font-size: 50px; font-family: monospace; margin-left: 300px;">Yu-Gi-Oh!</a>
				<ul class="nav justify-content-center" style="font-size: 20px;">
					<li class="nav-item"><a class="nav-link active" href="#">SPOT</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">TRIP</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/article/select_page.jsp">ARTICLE</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">WISH-POOL</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">STORE</a></li>
					<li class="nav-item"><a class="nav-link" href="#">GAME</a></li>
				</ul>
			</div>
		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="" style="font-size: 25px;">
					<span>建立遊記</span>
				</div>
				<div class="" style="border: 2px #ccc solid; border-radius: 10px;">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						enctype="multipart/form-data" name="form1">
						<input type="text" name="memno" size="20"
							value="<%=(articleVO == null) ? "MEM0001" : articleVO.getMemno()%>">
							
						<input type="text" name="tripno" size="20"
							value="<%=(articleVO == null) ? "TLI0001" : articleVO.getTripno()%>">

						<span>遊記名稱：<font color=red><b>*</b></font></span>
						<div class="form-title">
							<input type="text" name="articletitle" maxlength="50"
								value="<%=(articleVO == null) ? "" : articleVO.getArticletitle()%>"
								placeholder="為您的遊記取個名字">
						</div>

						<span>旅行日期：<font color=red><b>*</b></font></span> <input
							type="text" name="dayofstart" id="f_date1" placeholder="請選擇出發日期"><span>
							～ </span><input type="text" name="dayofend" id="f_date2"
							placeholder="請選擇回程日期"><span>共<input type="text"
							name="daysofstaying" id="f_date3" size="20"
							value="<%=(articleVO == null) ? "" : articleVO.getDaysofstaying()%>">天
						</span> <input type="hidden" name="articlestatus" size="2"
							value="<%=(articleVO == null) ? 0 : articleVO.getArticlestatus()%>">
						<input type="hidden" name="timeofviews" size="4"
							value="<%=(articleVO == null) ? 777 : articleVO.getTimeofviews()%>">
						<input type="hidden" name="dayoflastedit" id="f_date4"
							value="<%=(articleVO == null) ? "2019-12-02" : articleVO.getDayoflastedit()%>">

						<div class="article-kindoftrip">
							<span>請選擇旅遊類型：<font color=red><b>*</b></font></span> <select
								size="1" name="kindoftrip">
								<option value="0">單獨旅行
								<option value="1">情侶出遊
								<option value="2">家庭親子
								<option value="3">朋友出遊
								<option value="4">商務旅遊
								<option value="5">其他
							</select>
						</div>

						<span>封面照片：</span>
						<div class="article-pic">
							<input type="file" id="imgInp" name="articlepic" size="40"
								accept="image/gif , image/jpeg ,image/png"><img
								id="blah" width="100px" height="100px"
								style="border: 1px blue dashed; border-radius: 20px;"
								src="<%=request.getContextPath()%>/images/articlenoPic.png" />
						</div>



						<div class="insert-button">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="送出新增">
						</div>

						<%-- 錯誤列表 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="coloe: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</FORM>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12"></div>
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

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#f_date1').datetimepicker({
			format : 'Y-m-d',
			onShow : function() {
				this.setOptions({
					maxDate : $('#f_date2').val() ? $('#f_date2').val() : false
				})
			},
			timepicker : false
		});

		$('#f_date2').datetimepicker({
			format : 'Y-m-d',
			onShow : function() {
				this.setOptions({
					minDate : $('#f_date1').val() ? $('#f_date1').val() : false
				})
			},
			timepicker : false
		});
	});

	$("#f_date1").change(function() {

		var start = $("#f_date1").val();
		var end = $("#f_date2").val();

		start = start.replace(/-/g, "/");

		var startdate = new Date(start);
		end = end.replace(/-/g, "/");
		var enddate = new Date(end);

		var time = enddate.getTime() - startdate.getTime();
		var days = parseInt(time / (1000 * 60 * 60 * 24)) + 1;
		console.log(days);
		$("#f_date3").val(days);
	});

	$("#f_date2").change(function() {

		var start = $("#f_date1").val();
		var end = $("#f_date2").val();

		start = start.replace(/-/g, "/");

		var startdate = new Date(start);
		end = end.replace(/-/g, "/");
		var enddate = new Date(end);

		var time = enddate.getTime() - startdate.getTime();
		var days = parseInt(time / (1000 * 60 * 60 * 24)) + 1;
		console.log(days);
		$("#f_date3").val(days);
	});
</script>




<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</html>