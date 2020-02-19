<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoods.model.*"%>
<%@ page import="com.pointgoodsord.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	Object account = session.getAttribute("account");
	MemVO memVO = (MemVO) session.getAttribute("accountVO");
	if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
	PointGoodsOrdVO pointgoodsordVO = null;
	if (request.getAttribute("pointgoodsordVO") == null) {
		response.sendRedirect("/DA101G3/front-end/pointgoods/listAllPointGoodsF.jsp");
	} else {
		pointgoodsordVO = (PointGoodsOrdVO) request.getAttribute("pointgoodsordVO");
	}
	pointgoodsordVO = (PointGoodsOrdVO) request.getAttribute("pointgoodsordVO");
	pageContext.setAttribute("memVO", memVO);
	pageContext.setAttribute("pointgoodsordVO", pointgoodsordVO);
%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
	<script src="<%=request.getContextPath()%>/js/jquery.twzipcode.min.js"></script>
<title>成立訂單</title>
<style>
img {
	display: block;
	margin-left: auto;
	margin-right: auto;
	<%--width: 50%;--%>
	max-width: 350px;
	max-height: 161px;
	width: auto;
	height: auto;
}
#content{
	text-align:left;
	margin-left:450px;
	margin-right:auto;
	border:1px solid lightblue;
}
</style>
</head>
<body>
<%@ include file="/navbarNoCSS.file" %>
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>	
		</c:forEach>
	</ul>
</c:if>

	<img src="<%=request.getContextPath()%>/images/pointgoods/ordercomplete.png"/>
	<div id="content">
	親愛的<b>${memVO.memName}</b> 您好!!!<br>
	感謝您兌換商品,您的訂單資料已送出!訂單編號為:<b>${pointgoodsordVO.pointgoodsordno}</b><br>
	我們將盡快為您安排出貨,您可以至<a href>會員中心</a>查看您的訂單詳情.<br>
	若是您有任何疑問,可隨時寄信至我們的客服信箱或與我們的客服人員聯絡!
	</div>




	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>