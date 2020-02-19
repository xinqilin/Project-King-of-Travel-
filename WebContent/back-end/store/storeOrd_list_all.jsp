<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.storeOrd.model.*"%>
<%@ page import="com.storeOrd.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    StoreOrdService storeOrdSvc = new StoreOrdService();
    List<StoreOrdVO> list = storeOrdSvc.getAll();
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
<title>[後台]所有訂單資料</title>

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
</style>

</head>
<body>
<%@ include file="/nav-BE" %> 
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
<h3 style="text-align:center;">所有訂單資料</h3>
</table>

<table>
	<tr>		
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>總金額</th>
		<th>地址</th>
		<th>訂單狀態</th>
		<th>下單時間</th>
<!-- 		<th>付款時間</th> -->
		<th>付款方式</th>
		<th></th>
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="storeOrdVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${storeOrdVO.ordNo}</td>
			<td>${storeOrdVO.memNo}</td>
			<td>${storeOrdVO.price}</td>
			<td style="text-align:left;">${storeOrdVO.address}</td>
			
			
			<c:if test="${storeOrdVO.status==1}">
			<td bgcolor="#ffd2d2">${TransferTool.getStatus(storeOrdVO.status)}</td> 
			</c:if>
			<c:if test="${storeOrdVO.status==2}">
			<td bgcolor="#ffff6f">${TransferTool.getStatus(storeOrdVO.status)}</td> 
			</c:if>
			<c:if test="${storeOrdVO.status==3}">
			<td bgcolor="#b7ff4a">${TransferTool.getStatus(storeOrdVO.status)}</td> 
			</c:if>
			<td><fmt:formatDate value="${storeOrdVO.orderedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
<%-- 			<td><fmt:formatDate value="${storeOrdVO.paymentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
			<td>${TransferTool.getPaymentMethod(storeOrdVO.paymentMethod)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/storeOrd.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ordNo"  value="${storeOrdVO.ordNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>	
<!-- 			<td> -->
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/storeOrd.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="ordNo"  value="${storeOrdVO.ordNo}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
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
</html>