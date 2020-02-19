<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoods.model.*"%>

<%
	PointGoodsService pointgoodsSvc = new PointGoodsService();
	List<PointGoodsVO> list = pointgoodsSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
	<script src="https://kit.fontawesome.com/3f96afac76.js"></script>
<title>積分商城</title>

<style>
.card-img-top {
	width: 240px;
	height: 150px;
}

.card {
	margin-bottom: 15px;
	margin-left: 8px;
	margin-right: 8px;
	border: 0px;
}

.card-text {
	height: 50px;
}

.card-title {
	height: 35px;
}

.div_cart {
	width: 75px;
	height:80px;
	float: right;
	position: fixed;
	right: 0px;
	bottom: 0px;
	z-index: 100;
	text-align:center;
}
#amount{
	color:#ffe6b3;
	font-size:18px;
	font-weight:bold;
}
sub{
	font-size:36px;
	font-weight:bold;
}
</style>
</head>
<body>
<%@ include file="/navbarNoCSS.file" %>

	<div class="container">
		<div class="row">
			<div class="col-12" align="center"><%@ include file="page1.file"%></div>
		</div>
		<div class="row">
			<c:forEach var="pointgoodsVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<div class="col-3">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/pointgoods/showpic.do?pointgoodsno=${pointgoodsVO.pointgoodsno}"
							class="card-img-top">
						<div class="card-body">
							<h5 class="card-title">${pointgoodsVO.pointgoodsname}</h5>
							<p class="card-text">${pointgoodsVO.pointgoodsdesc}</p>
							<form name="shoppingForm" method="POST" id="${pointgoodsVO.pointgoodsno}">
							<input type="hidden" name="action" value="add">	
							<input type="hidden" name="pointgoodsno" value="${pointgoodsVO.pointgoodsno}">
							<input type="hidden" name="pointgoodsname" value="${pointgoodsVO.pointgoodsname}">
							<input type="hidden" name="needpoints" value="${pointgoodsVO.needpoints}">
							<input type="hidden" name="pointgoodsquantity" value="1">
							<button type="button" name="button" class="btn btn-primary">放入購物車</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<br>
	
	<a href="checkout.jsp">
	<div class="div_cart" align="left">
	<i class="fas fa-donate fa-lg" style="color:#ffe6b3"></i>
	<span id="amount"><%=(session.getAttribute("total")==null||(int)session.getAttribute("total")==0)?"":session.getAttribute("total")%></span>
	<span style="color:#cce6ff"><i class="fa fa-cart-arrow-down fa-3x">
	</i><sub><%=(session.getAttribute("shoppingcartq")==null||(int)session.getAttribute("shoppingcartq")==0)?"":session.getAttribute("shoppingcartq")%></sub></span></div></a>
	<%@ include file="page2.file" %>
	
	<script>
	$(document).ready(function() {
		$(".btn").click(function() {
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/pointgoods/pointgoods.do",
				data : $(this).parent().serialize(),
				dataType : "json",
				success : function(res) {
					alert("已添加至購物車");
					$("sub").text(res.cartquantity);
					$("#amount").text(res.cartamount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
	});
</script>

	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>