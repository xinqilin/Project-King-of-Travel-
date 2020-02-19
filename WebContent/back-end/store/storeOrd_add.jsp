<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.storeOrd.model.*"%>

<%
  StoreOrdVO storeOrdVO = (StoreOrdVO)request.getAttribute("storeOrdVO");
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料新增</title>

<style>

body{
margin: 0px auto;
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
} 
td { 
  border:1px solid #000;
  padding:5px;
} 
</style>

</head>
<body>
<%@ include file="/nav-BE" %> 
<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/store/storeOrd.do">
<table>
	<tr>
		<td>會員編號:</td>
		<td>
		<input type="TEXT" name="memNo" size="45" 
			 value="<%= (storeOrdVO==null)? "MEM0001" : storeOrdVO.getMemNo()%>" />
		</td>
	</tr>
	<tr>
		<td>訂單總金額:</td>
		<td><input type="TEXT" name="price" size="45"
			 value="<%= (storeOrdVO==null)? "99999" : storeOrdVO.getPrice()%>" /></td>
	</tr>
	<tr>
		<td>收貨地址:</td>
		<td><input type="TEXT" name="address" size="45"
		value="<%= (storeOrdVO==null)? "台灣桃園市桃園區" : storeOrdVO.getAddress()%>" /></td>
	</tr>	
	<jsp:useBean id="storeOrdSvc" scope="page" class="com.storeOrd.model.StoreOrdService" />

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
</html>