<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>訂單管理</title>

<style>
	body {
		background-color: #77DDFF;
	}
</style>
</head>
<body>

<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:orange">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:orange">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllPointGoodsOrders.jsp'>List</a> all PointGoodsOrders <br><br></li>
	
	<li>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pointgoodsord/pointgoodsord.do">
			<b>輸入產品編號:</b>
			<input type="text" name="pointgoodsordno">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
			</FORM>
	</li>
</ul>

</body>
</html>