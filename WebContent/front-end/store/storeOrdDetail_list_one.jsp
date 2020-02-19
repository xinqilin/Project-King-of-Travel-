<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.controller.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.storeOrd.model.*"%>
<%@ page import="com.storeOrd.controller.*"%>
<%@ page import="com.storeOrdDetail.controller.*"%>
<%@ page import="com.storeOrdDetail.model.*"%>
<%
	//會員登入驗證(開啟)
	Object account = session.getAttribute("account");
	Object total = session.getAttribute("total");
	Object SessionMemNo = session.getAttribute("memno");
	Object reqOrdNo = request.getAttribute("ordNo");
	System.out.print("reqOrdNo=" + reqOrdNo);
	if (account == null) {
		session.setAttribute("location", request.getRequestURI());

		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
	String ordNo = (String) reqOrdNo;
	StoreOrdDetailService storeOrdDetailSvc = new StoreOrdDetailService();
	List<StoreOrdDetailVO> list = storeOrdDetailSvc.findByPrimaryKey(ordNo);
	pageContext.setAttribute("list", list);

	ItemService itemSvc = new ItemService();
%>
<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
<!-- bootstrap[css] 2019-07-28 -->
<title>訂單資料 - storeOrd_list_one.jsp</title>

<style>

</style>

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>訂單明細</h3>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>訂單編號</th>
			<th>商品名稱</th>
			<th>商品單價</th>
			<th>購買數量</th>
		</tr>
		<%
			for (StoreOrdDetailVO storeOrdDetailVO : list){
		%>
		<tr>
			<td style="text-align:left;"><%=storeOrdDetailVO.getOrdNo()%></td>
			<td style="text-align:left;"><%=(itemSvc.getOneItem(storeOrdDetailVO.getItemNo())).getItemName()%></td>
			<td><%=storeOrdDetailVO.getPrice()%></td>
			<td><%=storeOrdDetailVO.getQuantity()%></td>
		</tr>
		<%
			}
		%>
	</table>
<!-- <!-- bootstrap+jquery 2019-07-28 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> -->
<!-- <!-- bootstrap+jquery 2019-07-28 --> 
</body>
</html>