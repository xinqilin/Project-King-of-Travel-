<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.storeOrd.model.*"%>
<%@ page import="com.storeOrd.controller.*"%>
<%      //會員登入驗證(開啟)
	Object account = session.getAttribute("account");
	Object total = session.getAttribute("total");
	
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		return;
	}%>







<%Object SessionMemNo = session.getAttribute("memno");
String memNo=(String)SessionMemNo;
StoreOrdService storeOrdSvc = new StoreOrdService();
System.out.println(memNo);
    List<StoreOrdVO> list = storeOrdSvc.getAllByMemno(memNo);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- bootstrap[css] 2019-07-28 -->

<title>我的訂單</title>

<style>


img{
	max-height: 240px;
 }
table { 
  border:1px solid #000; 
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
body {
	font-family: 微軟正黑體 !important;
	margin:auto;
}
h4 {
	color: blue;
	display: inline;
}
div.cus_info {
	max-width: 960px;
	margin: auto;
}
</style>
<!-- AjaxForm -->
<script src="<%=request.getContextPath()%>/front-end/CDN/jquery.form.js"></script>
<!-- Alert樣式 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css" rel="stylesheet">
<script src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
</head>
<body onload="aaaa()">
	<%@ include file="/nav-f1"%>
<table id="table-1">
	<tr><td>
		 <a href="<%=request.getContextPath()%>/front-end/store/storeOrd_list_all.jsp"><h3>我的訂單</h3></a>
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
		<th>訂單編號</th>
<!-- 		<th>會員編號</th> -->
		<th>總金額</th>
		<th>地址</th>
		<th>下單時間</th>
<!-- 		<th>付款時間</th> -->
		<th>付款方式</th>
		<th>訂單狀態</th>
		<th>收貨確認</th>
		<th>查看明細</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="storeOrdVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr id="${storeOrdVO.ordNo}">
			<td style="text-align:left;">${storeOrdVO.ordNo}</td>
<%-- 			<td>${storeOrdVO.memNo}</td> --%>
			<td>${storeOrdVO.price}</td>
			<td style="text-align:left;">${storeOrdVO.address}</td>
			<td><fmt:formatDate value="${storeOrdVO.orderedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
<%-- 			<td><fmt:formatDate value="${storeOrdVO.paymentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
			<td>${TransferTool.getPaymentMethod(storeOrdVO.paymentMethod)}</td>
			<td class="status">${TransferTool.getStatus(storeOrdVO.status)}</td> 
			
			<c:if test="${(storeOrdVO.status)==1}">
			<td bgcolor="#ffff6f">
				 <FORM METHOD="post"   ACTION="<%=request.getContextPath()%>/store/storeOrdFE.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="ordNo" value="${storeOrdVO.ordNo}">
			     <input type="hidden" name="action" value="change_ord_status">
			     <input type="hidden" name="status" value="${storeOrdVO.status}">
			     <button class="status_button" name="${storeOrdVO.status}" type="submit" disabled="disabled" value="Submit">確認收貨</button>
			     </FORM>
			</td>
			 </c:if>    
			<c:if test="${(storeOrdVO.status)==2}">
			<td bgcolor="#9aff02">
				 <FORM METHOD="post"   ACTION="<%=request.getContextPath()%>/store/storeOrdFE.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="ordNo" value="${storeOrdVO.ordNo}">
			     <input type="hidden" name="action" value="change_ord_status">
			     <input type="hidden" name="status" value="${storeOrdVO.status}">
			     <button class="status_button" name="${storeOrdVO.status}" type="submit" value="Submit">確認收貨</button>
			     </FORM>
			</td>
			 </c:if>       
			<c:if test="${(storeOrdVO.status)==3}">
			<td bgcolor="#9aff02">
				 <FORM METHOD="post"   ACTION="<%=request.getContextPath()%>/store/storeOrdFE.do" style="margin-bottom: 0px;">
			     <input type="hidden" name="ordNo" value="${storeOrdVO.ordNo}">
			     <input type="hidden" name="action" value="change_ord_status">
			     <input type="hidden" name="status" value="${storeOrdVO.status}">
			     <button class="status_button" name="${storeOrdVO.status}" type="submit" value="Submit" disabled="disabled">已確認</button>
			     </FORM>
			</td>
			 </c:if>     
		
			<td> 
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/storeOrdDetail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看訂單明細" onclick="select_order('${storeOrdVO.ordNo}')">
			     <input type="hidden" name="ordNo" value="${storeOrdVO.ordNo}">
			     <input type="hidden" name="action" value="get_details">
			     </FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%if(request.getAttribute("ordNo")!=null){%>
	<jsp:include page="storeOrdDetail_list_one.jsp"/>
<%} %>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
<script>
function select_order(ordNo){
	
	$("#"+ordNo).attr("bgcolor","#9aff02");
}




// 		function change_color(ordNo){
// 			alertify.error(ordNo);
// 			 $("#"+ordNo).css("color","red");
// 		}	
</script>
</html>