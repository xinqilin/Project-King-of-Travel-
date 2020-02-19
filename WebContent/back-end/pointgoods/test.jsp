<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pointgoods.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PointGoodsService pointgoodsSvc = new PointGoodsService();
	PointGoodsVO pointgoodsVO = pointgoodsSvc.getOnePointGoods("PG00015");
	request.setAttribute("pointgoodsVO",pointgoodsVO);
	Base64.Encoder encoder = Base64.getEncoder();
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
		<h4><a href="selectpage.jsp">回首頁</a></h4>
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
		<td><%=pointgoodsVO.getNeedpoints() %></td>
		<td><%=pointgoodsVO.getPointgoodsdesc() %></td>
		<td><%=pointgoodsVO.getPointgoodspic() %></td>
		<%-- <jsp:include page="/showpic.do" />--%>
		<td>${Base64.getEncoder().encodeToString(pointgoodsVO.getPointgoodspic())}</td>
		<img alt="img" src="data:image/*;base64,${Base64.getEncoder().encodeToString(pointgoodsVO.getPointgoodspic())}"/>
		<td><%=pointgoodsVO.getPointgoodsstatus() %></td>
	</tr>
	
</table>
</body>
</html>