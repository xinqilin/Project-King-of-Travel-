<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>
<%@ page import="com.wishord.model.*"%>

<%
	WishOrdService wishOrdSvc = new WishOrdService();
	List<WishOrdVO> wishOrdList = wishOrdSvc.getAll();
	List<WishOrdVO> wishBuyOrdList = new ArrayList<WishOrdVO>();
	List<WishOrdVO> wishSellOrdList = new ArrayList<WishOrdVO>();
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
 		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
 		return;
 	}
	for(int i =0;i<wishOrdList.size();i++){
		if((wishOrdList.get(i)).getBuyMemNo().equals(memNo)){
			wishBuyOrdList.add(wishOrdList.get(i));

		};
		if((wishOrdList.get(i)).getWishMemNo().equals(memNo)){
			wishSellOrdList.add(wishOrdList.get(i));
		};
	};	
	pageContext.setAttribute("wishSellOrdList", wishSellOrdList);//會員幫忙代購訂單
	pageContext.setAttribute("wishBuyOrdList", wishBuyOrdList);//會員請求代購訂單
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

h3,th,td {
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
					<a class="nav-link" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">回代購首頁</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_sellList.jsp">代購商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_buyList.jsp">託買商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/listMyWishItem.jsp">我的託買及代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/add_WishItem.jsp">新增許願代購商品</a>	
					<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/WishOrd/listMyWishOrd.jsp">我的訂單</a>						
				</div>
			</div>
			<div class="col-9">
		
				<c:if test="<%=wishBuyOrdList.size()!=0%>">
				<div><h3>我的託買訂單</h3></div>
				<br>
				<div class="row">
							
						<table>
							<tr>
								<th>訂單編號</th>
								<th>訂單狀態</th>
								<th>託買會員</th>
								<th>代購會員</th>
								<th>訂單成立日期</th>
								<th>總金額</th>
								<th>預計寄送日期</th>
								<th>最後送達日期</th>
								<th>訂單但書</th>
								<th>寄送地址</th>
							</tr>
							<%
								int i = 0;
							%>
								<c:forEach var="wishBuyOrdList" items="${wishBuyOrdList}">
								<tr>
									<td>${wishBuyOrdList.wishOrdNo}</td>
									<td>
									
									<c:if test="${wishBuyOrdList.wishOrdStatus==0}">交易中</c:if>
									<c:if test="${wishBuyOrdList.wishOrdStatus==1}">出貨中
									<form action="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do"  METHOD="post">
									<input type="hidden" name="wishord" value="${wishBuyOrdList.wishOrdNo}">
									<input type="hidden" name="action" value="finish">
									<input type="submit" value="已收貨完成交易">
									</form>
									</c:if>
									<c:if test="${wishBuyOrdList.wishOrdStatus==2}">交易完成</c:if>
									</td>
									<td>${wishBuyOrdList.buyMemNo}</td>
									<td>${wishBuyOrdList.wishMemNo}</td>
									<td>${wishBuyOrdList.dayOfEst}</td>
									<td>${wishBuyOrdList.wishOrdTotalPrice}</td>
									<td>${wishBuyOrdList.wishSendDate}</td>
									<td>${wishBuyOrdList.lastDate}</td>
									<td>${wishBuyOrdList.wishOrdBuyNote}</td>
									<td>${wishBuyOrdList.wishOrdSendAddr}</td>
								</tr>
								</c:forEach>
						</table>						
					</div>
				</c:if>
			
					<br><br><br>	
				<c:if test="<%=wishSellOrdList.size()!=0%>">
				<div><h3>我的代購訂單</h3></div>
				<br>
				<div class="row">
						
						<table>
							<tr>
								<th>訂單編號</th>
								<th>訂單狀態</th>
								<th>託買會員</th>
								<th>代購會員</th>
								<th>訂單成立日期</th>
								<th>總金額</th>
								<th>預計寄送日期</th>
								<th>最後送達日期</th>
								<th>訂單但書</th>
								<th>寄送地址</th>
							</tr>
							<%
								int i = 0;
							%>
								<c:forEach var="wishSellOrdList" items="${wishSellOrdList}">
								<tr>
									<td>${wishSellOrdList.wishOrdNo}</td>
									<td>
									<c:if test="${wishSellOrdList.wishOrdStatus==0}">交易中
									<form action="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do"  METHOD="post">
									<input type="hidden" name="wishord" value="${wishSellOrdList.wishOrdNo}">
									<input type="hidden" name="action" value="sendwishitem">
									<input type="submit" value="已將貨品寄出">
									</form>
									</c:if>
									<c:if test="${wishSellOrdList.wishOrdStatus==1}">出貨中</c:if>
									<c:if test="${wishSellOrdList.wishOrdStatus==2}">交易完成</c:if>
									</td>
									<td>${wishSellOrdList.buyMemNo}</td>
									<td>${wishSellOrdList.wishMemNo}</td>
									<td>${wishSellOrdList.dayOfEst}</td>
									<td>${wishSellOrdList.wishOrdTotalPrice}</td>
									<td>${wishSellOrdList.wishSendDate}</td>
									<td>${wishSellOrdList.lastDate}</td>
									<td>${wishSellOrdList.wishOrdBuyNote}</td>
									<td>${wishSellOrdList.wishOrdSendAddr}</td>
								</tr>
								</c:forEach>
						</table>						
					</div>
				</c:if>
					
			</div>
			</div>
	</div>
	<%for(int br=1;br<=15;br++){ %>
	<br>
	<%} %>
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