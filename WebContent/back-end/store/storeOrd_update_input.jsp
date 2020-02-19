<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.storeOrd.model.*"%>
<%@ page import="com.storeOrd.controller.*"%>
<% 
	StoreOrdVO storeOrdVO = (StoreOrdVO) request.getAttribute("storeOrdVO");
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>[後台]修改訂單</title>

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
  width:600px;
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
  text-align:right;
} 
td { 
  border:1px solid #000;
  padding:5px;
  text-align:left;
} 
</style>

</head>
<body>
<%@ include file="/nav-BE" %> 

	<h3 style="text-align:center;">資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/store/storeOrd.do">
		<table>
			<tr>
				<th>訂單編號:<font color=red><b>*</b></font></th>
				<td>${storeOrdVO.ordNo}</td><input type="hidden" name="ordNo"  value="${storeOrdVO.ordNo}">
			</tr>
			<tr>
				<th>會員編號:</th>
				<td name="memNo">${storeOrdVO.memNo}</td><input type="hidden" name="memNo"  value="${storeOrdVO.memNo}">
			</tr>
			<tr>
				<th>訂單總金額:</th>
				<td name="price">${storeOrdVO.price}</td><input type="hidden" name="price"  value="${storeOrdVO.price}">
			</tr>
			<tr>
				<th>收貨地址:</th>
				<td><input type="TEXT" name="address" size="45"
					value="${storeOrdVO.address}" /></td>
			</tr>
			<tr>
				<th>訂單狀態:</th>
				<td><select name="status">
						<option value=<%=storeOrdVO.getStatus()%>>${TransferTool.getStatus(storeOrdVO.status)}</option>
						<c:forEach var="X" begin="0" end="3">
							<option value=${ X } >${TransferTool.getStatus(X)}</option>
						</c:forEach>
				</select>
					
					
					
					
					</td>
			</tr>
						<tr>
				<th>付款方式:</th><input type="hidden" name="paymentMethod"  value="${storeOrdVO.paymentMethod}">
				<td>${TransferTool.getPaymentMethod(storeOrdVO.paymentMethod)}</td>
			</tr>
			
			<jsp:useBean id="storeSvc" scope="page"
				class="com.storeOrd.model.StoreOrdService" />
			<tr><th></th><td> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="ordNo" value="<%=storeOrdVO.getOrdNo()%>">
		<input type="submit" value="送出修改"></td></tr>
		</table>
	</FORM>
	<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
</html>