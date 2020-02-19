<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoods.model.*"%>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式  - Cart.jsp</title>
</head>
<body bgcolor="#FFFFFF">

<%Vector<PointGoodsVO> buylist = (Vector<PointGoodsVO>) session.getAttribute("shoppingcartp");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<font size="+3">目前您購物車的內容如下：</font><p>

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="200">書名</th><th width="100">作者</th><th width="100">出版社</th><th width="100">價格</th>
		<th width="120">數量</th><th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 PointGoodsVO order = buylist.get(index);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getPointgoodsno()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPointgoodsname()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getNeedpoints()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPointgoodsquantity()%></b></div></td>
		
		<td width="100"><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/front-end/pointgoods/pointgoods.do" method="POST">
              <input type="hidden" name="action" value="remove">
              <input type="hidden" name="remove" value="<%= index %>">
              <input type="submit" value="刪除"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/pointgoods/pointgoods.do" method="POST">
              <input type="hidden" name="action"	value="checkout"> 
              <input type="submit" value="付款結帳">
          </form>
<%}%>
</body>
</html>