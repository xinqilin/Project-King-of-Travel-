<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%
    ItemService itemSvc = new ItemService();
    List<ItemVO> list = itemSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
Object backaccount = session.getAttribute("backaccount"); // 從 session內取出 (key) account的值
if (backaccount == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/login_back.jsp"); 
	return;
}

%>
<html>
<head>
<!-- BootStrap4.3.1 2019/07/28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>[後台]所有商品資料 </title>

<style>
body{
margin:auto;
}
 img{
	max-height: 200px;
 }
table { 
  border:1px solid #000; 
  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1200px;
  border:1px solid #000;
  text-align:center;
  border-collapse:collapse;
  margin:auto;
} 
th { 
  background-color: #009FCC;
  padding:10px;
  border:1px solid #000;
  color:#fff;
} 
td { 
  border:1px solid #000;
  padding:5px;
} 
</style>
</head>
<body>
<%@ include file="/nav-BE" %> 
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
		<th>商品編號</th>
		<th>圖片</th>
		<th>商品名稱</th>
		<th>價格</th>
		<th>商品庫存</th>
		<th>商品明細</th>
		<th>商品類別</th>
		<th>上/下架狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${itemVO.itemNo}</td>
			<td><img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=${itemVO.itemNo}" /> </td>
			<td style="text-align:left;">${itemVO.itemName}</td>
			<td>${itemVO.price}</td>
			<td class="amount">${itemVO.amount}</td>
			<td style="text-align:left;">${itemVO.itemDetail}</td> 
			<td>${TransferTool.getClass(itemVO.itemClass)}</td>
			<td>${TransferTool.getStatus(itemVO.status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/item.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemNo"  value="${itemVO.itemNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>

<script>
</script>
</html>