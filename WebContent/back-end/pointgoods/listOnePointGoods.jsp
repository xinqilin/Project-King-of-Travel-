<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pointgoods.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PointGoodsVO pointgoodsVO = (PointGoodsVO) request.getAttribute("pointgoodsVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>積分商品資料 - listOnePointGoods.jsp</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		<h3>積分商品資料 - listOnePointGoods.jsp</h3>
		<h4><a href="selectPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
	</tr>
	<tr>
		<td><%=pointgoodsVO.getPointgoodsno()%></td>
		<td><%=pointgoodsVO.getPointgoodsname()%></td>
		<td><%=pointgoodsVO.getPointgoodsquantity() %></td>
		<td><%=pointgoodsVO.getNeedpoints()%></td>
		<td><%=pointgoodsVO.getPointgoodsdesc()%></td>
		<td><img alt="img" src="<%=request.getContextPath()%>/pointgoods/showpic.do?pointgoodsno=${pointgoodsVO.pointgoodsno}"/></td>
		<td>${Status.getStatus(pointgoodsVO.pointgoodsstatus)}</td>
	</tr>
</table>

</body>
</html>