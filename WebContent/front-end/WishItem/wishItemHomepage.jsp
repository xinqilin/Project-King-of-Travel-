<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>

<%
	WishItemService wishItemSvc = new WishItemService();
	List<WishItemVO> wishList = wishItemSvc.getAll();
	List<WishItemVO> wishBuyList = new ArrayList<WishItemVO>();
	List<WishItemVO> wishSellList = new ArrayList<WishItemVO>();
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	for(int i =0;i<wishList.size();i++){
		int j=0;
		int k=0;
		if((wishList.get(i)).getBuyOrSell()==0&&(wishList.get(i)).getStatus()==1){
			wishBuyList.add(j, wishList.get(i));
			j++;
		};
		if((wishList.get(i)).getBuyOrSell()==1&&(wishList.get(i)).getStatus()==1){
			wishSellList.add(k, wishList.get(i));
			k++;
		};
	};
	pageContext.setAttribute("wishBuyList", wishBuyList);//請託購買
	pageContext.setAttribute("wishSellList", wishSellList);//代購清單
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
<title>遊記王_許願願池首頁</title>
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

h3,h1,h2,h4,h5,h6,p,input {
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
					<a class="nav-link active" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">回代購首頁</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_sellList.jsp">代購商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_buyList.jsp">託買商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/listMyWishItem.jsp">我的託買及代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/add_WishItem.jsp">新增許願代購商品</a>	
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishOrd/listMyWishOrd.jsp">我的訂單</a>	
				</div>
			</div>
			<div class="col-9">
			<%@ include file="wishBuyListPage1.file" %>			
				<br><h1 style="color:orange;">會員託買商品專區:</h1><br>
				<div class="row">
				<c:forEach var="wishItemVO" items="${wishBuyList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<form  METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/WishItem/wishItem.do">
				<div class="card" style="width: 16rem;">
 					<img src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=${wishItemVO.wishItemNo}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${wishItemVO.itemName}"  class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">商品名稱:${wishItemVO.itemName}</h5>
    					<p class="card-text">商品簡述:${wishItemVO.wishItemDetail}</p>
    					<p class="card-text">商品價錢:${wishItemVO.itemPrice}</p>
    					<p class="card-text">商品數量:${wishItemVO.amount}</p>
						<input type="hidden" name="wishItemNo" value="${wishItemVO.wishItemNo}">
    					<input type="hidden" name="action" value="getOne_For_Display">
        				<input type="submit" value="商品詳情">
 					 </div>
				</div>
				</form>
				</c:forEach>
				
				</div>
				<%@ include file="wishBuyListPage2.file" %>
				<%@ include file="wishSellListPage1.file" %>			
				<br><h1 style="color:orange;">會員代購商品專區:</h1><br>
				<div class="row">
				<c:forEach var="wishItemVO" items="${wishSellList}" begin="<%=pageIndexSell%>" end="<%=pageIndex+rowsPerPageSell-1%>">
				<form  METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/WishItem/wishItem.do">
				<div class="card" style="width: 16rem;">
 					<img src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=${wishItemVO.wishItemNo}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${wishItemVO.itemName}"  class="card-img-top">
					<div class="card-body">
    					<h5 class="card-title">商品名稱:${wishItemVO.itemName}</h5>
    					<p class="card-text">商品簡述:${wishItemVO.wishItemDetail}</p>
    					<p class="card-text">商品價錢:${wishItemVO.itemPrice}</p>
    					<p class="card-text">商品數量:${wishItemVO.amount}</p>
    					<input type="hidden" name="wishItemNo" value="${wishItemVO.wishItemNo}">
    					<input type="hidden" name="action" value="getOne_For_Display">
        				<input type="submit" value="商品詳情">	
					</div>
				</div>
				</form>
				</c:forEach>
				</div>
			
				<%@ include file="wishSellListPage2.file" %>
					
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