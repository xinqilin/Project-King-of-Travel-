<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ItemVO  itemVO= (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
<!-- BootStrap -->
<!-- BootStrap4.3.1 2019/07/28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>[後台]商品資料</title>

<style>
body{
margin: 0px auto;
}
table { 
  border:1px solid #000; 
  font-family: 微軟正黑體; 
  font-size:16px; 
  width:1300px;
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
img{
height:250px;
}
</style>
</head>
<body>
<%@ include file="/nav-BE" %> 
<table>
	<tr>
		<th>商品編號</th>
		<th>商品圖片</th>
		<th>商品名稱</th>
		<th>價格</th>
		<th>商品庫存</th>
		<th>商品明細</th>
		<th>上/下架狀態</th>
		<th>商品類別</th>
		<th>修改</th>

	</tr>
	<tr>
		<td><%=itemVO.getItemNo()%></td>
		<td><img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=${itemVO.itemNo}" /> </td>
		<td><%=itemVO.getItemName()%></td>
		<td><%=itemVO.getPrice()%></td>
		<td><%=itemVO.getAmount()%></td>
		<td><%=itemVO.getItemDetail()%></td>
		<td>${TransferTool.getStatus(itemVO.status)}</td>
		<td>${TransferTool.getClass(itemVO.itemClass)}</td>
					  <td><FORM METHOD="post" ACTION="<%=request.getContextPath() %>/store/item.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemNo"  value="${itemVO.itemNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     </FORM></td>
	</tr>
</table>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
</html>