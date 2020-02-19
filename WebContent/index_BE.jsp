<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Object backaccount = session.getAttribute("backaccount"); // 從 session內取出 (key) account的值
if (backaccount == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/login_back.jsp"); 
	return;
}

%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<meta charset="utf-8">
<title>遊記王:後臺首頁</title>
<style>
body{
font-family: 微軟正黑體;
margin: auto;
}
div{
	text-align: center;
}
div.row{
/* 	border-style: dashed; */
}
img{
	height:200px;
	width:200px;
}
h1{
font-family: 標楷體;
text-align: center;
}
</style>
</head>
<body>
		<!-- Header -->
		<header id="header"> </header>
		<h1>遊記王《後台管理》</h1><br><br><br><br><br>
		<div class="container">
			<div class="row">
				<div class="col-3"></div>

				<div class="col-3">
					
					<div class="img">
						<a href="<%=request.getContextPath()%>/back-end/mem/select_page.jsp"><img src="images/menber.png"></a>
						<p>會員管理</p>
					</div>
					
				</div>

				<div class="col-3">
					
					<div class="img">
							<a href="<%=request.getContextPath()%>/back-end/spot/select_page.jsp"><img src="images/Attractions.jfif"></a>
							<p>景點管理</p>
					</div>
					<div class="col-3"></div>
					
				</div>



			</div>	


			<div class="row">


				<div class="col-3"></div>
				<div class="col-3">
					
					<div class="img">
						<a href="<%=request.getContextPath()%>/back-end/store/item_select_page.jsp"><img src="images/store.png"></a>
						<p>商品管理</p>
					</div>
				</div>

				<div class="col-3">
					
					<div class="img">
					<a href="<%=request.getContextPath()%>/back-end/trip/b_trip_index.jsp"><img src="images/stroke.jpg"></a>
						<p>行程管理</p>
					</div>
				</div>
				<div class="col-3"></div>


			</div>

		
					<div class="row">


				<div class="col-3"></div>
				<div class="col-3">
					
					<div class="img">
						<a href="<%=request.getContextPath()%>/back-end/pointgoods/selectPage.jsp"><img src="images/pointgoods/cart1.png"></a>
						<p>積分商品管理</p>
					</div>
				</div>

				<div class="col-3">
					
					<div class="img">
					<a href="<%=request.getContextPath()%>/back-end/activity/activityB.jsp"><img src="images/activity/baseball.png"></a>
						<p>競猜直播</p>
					</div>
				</div>
				<div class="col-3"></div>


			</div>

		</div>
		



</body>
</html>