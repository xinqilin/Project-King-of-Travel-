<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>

<%
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	List<WishItemVO> wishBuyList = new ArrayList<WishItemVO>();
	List<WishItemVO> wishSellList = new ArrayList<WishItemVO>();
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		return;
	}
	pageContext.setAttribute("memNo", memNo);
	if(session.getAttribute("buyList")!=null) {
		wishBuyList = (ArrayList)session.getAttribute("buyList");
		pageContext.setAttribute("wishBuyList", wishBuyList);
	}
	if(session.getAttribute("sellList")!=null) {					
		wishSellList = (ArrayList)session.getAttribute("sellList");
		pageContext.setAttribute("wishSellList", wishSellList);
	}

	
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
<title>遊記王_我的購物車商品列表</title>
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

h3,th,input,td,div {
	font-family: "微軟正黑體";
	font-style: italic;
	font-weight: bold;
}
table,th,td{
border:1px pink solid;
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
				<c:if test="<%=wishBuyList.size()!=0%>">
				<div class="row">
						<table>
							<tr>
								<th>代購商品圖片</th>
								<th>代購商品名稱</th>
								<th>代購會員</th>
								<th>請求會員</th>
								<th>商品數量</th>
								<th>單筆金額</th>
								<th>總金額</th>
								<th>結帳</th>
								<th>刪除</th>
							</tr>
							<%
								int i = 0;
							%>
							<c:forEach var="wishBuyItemVO" items="${wishBuyList}">
								<tr>
									<td><img
										src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=<%=wishBuyList.get(i).getWishItemNo()%>"
										style="width: 250px ;height:200px;"></td>
									<td>${wishBuyItemVO.itemName}</td>
									<td>${memNo}</td>
									<td>${wishBuyItemVO.memNo}</td>
									<td>${wishBuyItemVO.amount}</td>
									<td>${wishBuyItemVO.itemPrice}</td>
									<td>${wishBuyItemVO.itemPrice*wishBuyItemVO.amount}</td>
									<td><input type="button" value="結帳" onclick="location.href='<%=request.getContextPath()%>/front-end/WishOrd/sell_CheckOut.jsp'"></td>
									<td>
										<form METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do">
											<input type="hidden" name="wishItemNo" value="<%=wishBuyList.get(i).getWishItemNo()%>">
										    <input type="hidden" name="buyOrSell"  value="<%=wishBuyList.get(i).getBuyOrSell()%>">
										    <input type="hidden" name="action" value="remove"> 
										    <input type="submit" name="type" value="取消購買">
										</form>
									</td>
								</tr>
								<%
									i++;
								%>
							</c:forEach>
						</table>
				</div>
				</c:if>
				
				<c:if test="<%=wishSellList.size()!=0%>">
				<div class="row">
						<table>
							<tr>
								<th>請求代購商品圖片</th>
								<th>請求代購商品名稱</th>
								<th>代購會員</th>
								<th>請求會員</th>
								<th>商品數量</th>
								<th>單筆金額</th>
								<th>總金額</th>
								<th>結帳</th>
								<th>刪除</th>
							</tr>
							<%
								int i = 0;
							%>
							<c:forEach var="wishSellItemVO" items="${wishSellList}">
								<tr>
									<td><img src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=<%=wishSellList.get(i).getWishItemNo()%>"
										style="width: 250px ;height:200px;"></td>
									<td>${wishSellItemVO.itemName}</td>
									<td>${memNo}</td>
									<td>${wishSellItemVO.memNo}</td>
									<td>${wishSellItemVO.amount}</td>
									<td>${wishSellItemVO.itemPrice}</td>
									<td>${wishSellItemVO.itemPrice*wishBuyItemVO.amount}</td>
									<td><input type="button" value="結帳" onclick="location.href='<%=request.getContextPath()%>/front-end/WishOrd/buy_CheckOut.jsp'"></td>
									<td>
										<form METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do">
											<input type="hidden" name="wishItemNo" value="<%=wishSellList.get(i).getWishItemNo()%>">
											<input type="hidden" name="buyOrSell"  value="<%=wishSellList.get(i).getBuyOrSell()%>">
											<input type="hidden" name="action" value="remove">
											<input type="submit" name="type" value="取消購買">
										</form>
									</td>
								</tr>
								<%i++;%>
							</c:forEach>
						</table>
						
					</div>
				</c:if>

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