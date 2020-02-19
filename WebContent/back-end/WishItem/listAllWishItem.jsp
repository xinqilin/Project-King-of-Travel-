<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	WishItemService wishItemSvc = new WishItemService();
	List<WishItemVO> list = wishItemSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有代購資料 - listAllWishList.jsp</title>

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
	width: 800px;
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
		 <h3>所有代購資料 - listAllWishItem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="NoData/1.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="wishItemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
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
			<td>
			  <FORM METHOD="post" ACTION="wishItem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="wishItemNo"  value="${wishItemVO.wishItemNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="wishItem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="wishItemNo"  value="${wishItemVO.wishItemNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>