<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.storeOrd.model.*"%>
<%@ page import="com.storeOrd.controller.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%      //會員登入驗證(開啟)
	Object account = session.getAttribute("account");
	Object memNo = session.getAttribute("memno");
	Object total = session.getAttribute("total");
	
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		return;
	}%>
<%
	StoreOrdVO  storeOrdVO= (StoreOrdVO) request.getAttribute("storeOrdVO"); 
%>

<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- bootstrap[css] 2019-07-28 -->
<title>訂單資料</title>

<style>
 img{
max-height:250px;
 }
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
	width: 1000px;
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
		 <h3>訂單明細</h3>
	</td></tr>
</table>

<table class="detail">
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>價格</th>
		<th>地址</th>
		<th>訂單狀態</th>
		<th>下單時間</th>
		<th>付款時間</th>
		<th>付款方式</th>
	</tr>
	<tr>
			<td>${storeOrdVO.ordNo}</td>
			<td>${storeOrdVO.memNo}</td>
			<td>${storeOrdVO.price}</td>
			<td>${storeOrdVO.address}</td>
			<td>${storeOrdVO.status}</td> 
			<td>${storeOrdVO.orderedTime}</td>
			<td>${storeOrdVO.paymentTime}</td>
			<td>${storeOrdVO.paymentMethod}</td>
<%-- 			<td><FORM METHOD="post" ACTION="<%=request.getContextPath() %>/store/storeOrdFE.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="ordNo"  value="${storeOrdVO.ordNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"> -->
<!-- 			     </FORM></td> -->
	</tr>
</table>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
</html>