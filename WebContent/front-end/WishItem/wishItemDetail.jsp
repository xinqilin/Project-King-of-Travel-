<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>

<%
	WishItemVO wishItemVO = (WishItemVO) request.getAttribute("wishItemVO");
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="icon" href="kingoftravel.ico" type="image/x-icon" />
<title>遊記王_許願商品列表</title>
<style type="text/css">
.modal-content {
	border-radius: 15px;
	background: #00BBFF;
}

.modal-backdrop {
	background-color: #000000;
}

.modal-backdrop.show {
	opacity: .5;
}

.modal-content {
	text-align: center;
	font-weight: bold;
}

.modal-header {
	align-items: center;
}

label {
	font-size: 20px;
}

a:hover, .current_page_item a {
	text-decoration: none;
	background: pink;
	border-radius: 10px;
	color: #FFFFFF;
	font-family: "微軟正黑體";
}

h3,li,input,p,h5 {
	font-family: "微軟正黑體";
	font-weight: bold;
}
</style>

</head>
<body bgcolor='white'>

<%@ include file="/nav-f1"%>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="https://picsum.photos/1200/300?random=1"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=2"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=3"
					class="d-block w-100">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">大波霸組員</li>
			<li class="breadcrumb-item"><a href="#">緯明不要</a></li>
			<li class="breadcrumb-item"><a href="#">瑋齊不行</a></li>
			<li class="breadcrumb-item"><a href="#">奕伸大麻</a></li>
		</ol>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-3">
				<div class="nav flex-column nav-pills" id="v-pills-tab"
					role="tablist" aria-orientation="vertical">
					<a class="nav-link" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">回代購首頁</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_sellList.jsp">代購商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_buyList.jsp">託買商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/listMyWishItem.jsp">我的託買及代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/add_WishItem.jsp">新增許願代購商品</a>	
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishOrd/listMyWishOrd.jsp">我的訂單</a>	
				</div>
			</div>
			<div class="col-9">
			

			<div class="card" >
 				<img src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=${wishItemVO.wishItemNo}"
 				style="width: 450px ;height:400px; align-items: center;">
 				<div class="card-body">
					<h5 class="card-title">商品名稱:${wishItemVO.itemName}</h5><!-- 商品名稱 -->
    				<p class="card-text">商品數量需求:${wishItemVO.amount}</p>
    		<c:if test="${wishItemVO.buyOrSell==0}">
    				<p class="card-text">託買會員:${wishItemVO.memNo}</p>
    		</c:if>
    		<c:if test="${wishItemVO.buyOrSell==1}">
    				<p class="card-text">代購會員:${wishItemVO.memNo}</p>
    		</c:if>
    				<p class="card-text">商品詳述:${wishItemVO.wishItemDetail}</p><!-- 商品詳述 -->
 				</div>
				 <ul class="list-group list-group-flush">
				    <li class="list-group-item">店家名稱:${wishItemVO.itemStoreName}</li>
				    <li class="list-group-item">店家地址:${wishItemVO.itemStoreAddr}</li>
				    <li class="list-group-item">店家緯度:${wishItemVO.itemStoreLati}</li>
				    <li class="list-group-item">店家經度:${wishItemVO.itemStoreLong}</li>
				    <li class="list-group-item">代購但書:${wishItemVO.wishNote}</li>
				 </ul>

				  	 <div class="card-body">
				     <form  METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do">	
						
						<input type="hidden" name="wishItemNo" value="<%=wishItemVO.getWishItemNo() %>">
						<input type="hidden" name="action" value="addCart">
        				<input type="submit" name="type" value="結帳"><!-- 寫直接結帳的按鈕 -->
				     </form>
				  	 </div>
			  	 </div>
			

			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.js"
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
</body>
</html>