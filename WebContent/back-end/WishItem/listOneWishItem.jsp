<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wishitem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
WishItemVO wishItemVO = (WishItemVO) request.getAttribute("wishItemVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>代購商品資料 - listOneWishItem.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>資料 - ListOneWishItem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="../images/1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>代購商品編號</th>
		<th>會員編號</th>
		<th>數量</th>
		<th>商品名稱</th>
		<th>商品價錢</th>
		<th>商品店家名</th>
		<th>商品店家地址</th>
		<th>商品店家經度</th>
		<th>商品店家緯度</th>
		<th>請求代購或代購</th>
		<th>代購商品詳情</th>
		<th>代購商品圖片</th>
		<th>代購但書</th>
		<th>狀態</th>
		<th>代購商品類型</th>
	</tr>
	<tr>
		<td>${wishItemVO.wishItemNo}</td>
		<td>${wishItemVO.memNo}</td>
		<td>${wishItemVO.amount}</td>
		<td>${wishItemVO.itemName}</td>
		<td>${wishItemVO.itemPrice}</td>
		<td>${wishItemVO.itemStoreName}</td> 
		<td>${wishItemVO.itemStoreAddr}</td>
		<td>${wishItemVO.itemStoreLati}</td>
		<td>${wishItemVO.itemStoreLong}</td>
		<td>${wishItemVO.buyOrSell}</td>
		<td>${wishItemVO.wishItemDetail}</td>
		<td>
		<image src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=${wishItemVO.wishItemNo}">
		</td>
		<td>${wishItemVO.wishNote}</td>
		<td>${wishItemVO.status}</td>
		<td>${wishItemVO.itemType}</td>
	</tr>
</table>

</body>
</html>