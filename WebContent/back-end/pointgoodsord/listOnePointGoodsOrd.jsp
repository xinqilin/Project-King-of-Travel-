<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoodsord.model.*"%>
<%@ page import="com.pointgoods.model.*"%>

<%
	PointGoodsOrdVO pointgoodsordVO = (PointGoodsOrdVO) request.getAttribute("pointgoodsordVO"); 
%>

<html>
<head>
<title>listOnePointGoodsOrd.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>ListOnePointGoodsOrd.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/pointgoodsord/selectPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>寄件會員編號</th>
		<th>收件人姓名</th>
		<th>收件人手機</th>
		<th>收件地址</th>
		<th>訂單成立日期</th>
		<th>訂單狀態</th>
		<th>訂單點數</th>
	</tr>
	<tr>
		<td>${pointgoodsordVO.pointgoodsordno}</td>
		<td>${pointgoodsordVO.memno}</td>
		<td>${pointgoodsordVO.receiver}</td>
		<td>${pointgoodsordVO.phone}</td>
		<td>${pointgoodsordVO.address}</td>
		<td>${Status.getNoZeroDate(pointgoodsordVO.orderdate)}</td>
		<td>${Status.getOrderStatus(pointgoodsordVO.orderstatus)}</td>
		<td>${pointgoodsordVO.orderpoint}</td>
	</tr>
</table>

</body>
</html>