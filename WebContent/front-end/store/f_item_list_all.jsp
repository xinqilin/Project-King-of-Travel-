<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%@ page import="com.f_item.model.*"%>
<%@ page import="com.f_item.controller.*"%>
<%
	//會員登入驗證(開啟)
	Object account = session.getAttribute("account");
	if (account == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
%>
<!-- 取得itemsvc -->
<% ItemService itemSvc = new ItemService();%>
<!-- 取得購物車 -->
<%
	LinkedHashMap<String, Integer> cart = (LinkedHashMap<String, Integer>) session.getAttribute("cart");
	if (cart == null) {
		cart = new LinkedHashMap<>();
		session.setAttribute("cart", cart);
	}
%>
<%
	Object SessionMemNo = session.getAttribute("memno");
	String memNo = (String) SessionMemNo;
	//取得我的最愛名明細
	F_ItemService f_itemSvc = new F_ItemService();
	HashSet<String> f_item_list = f_itemSvc.findByPrimaryKey(memNo);
	System.out.println("我的最愛內有"+f_item_list.size()+"樣商品");
%>


<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- bootstrap[css] 2019-07-28 -->
<title>我追蹤的商品</title>

<style>
#mycart {
	z-index: 99999;
	position: fixed; /*固定在網頁上不隨卷軸移動，若要隨卷軸移動用absolute*/
	top: 20%; /*設置垂直位置*/
	right: -40px; /*設置水平位置，依所放的內容多寡需要自行手動調整*/
	background: transparent; /*背景顏色*/
	padding: 10px 20px;
	border-radius: 10px; /*圓角*/
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
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
img {
	
	max-width: 200px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
<%@ include file="/nav-f1"%>
	<table id="table-1">
		<tr>
			<td>
				<a href="<%=request.getContextPath()%>/front-end/store/f_item_list_all.jsp"><h3>我追蹤的商品</h3></a>
				<FORM style="display: inline;" id="mycart" name="mycart"
						METHOD="post" ACTION="<%=request.getContextPath()%>/store/cart.do">

						<input type="hidden" name="action" value="show_cart"> <font
							id="count" style="color: red"> ${cart.size()}</font>樣商品<br>
						<input type="image" name="submit_Btn" id="submit_Btn" img
							src="<%=request.getContextPath()%>/front-end/store/images/cart-4.png"
							onClick="document.mycart.submit()">
				</FORM>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr><th>商品圖片</th>
			<th>商品名稱</th>
			<th>商品價格</th>
			<th>加入購物車</th>
		</tr>
<%for(String fitem:f_item_list){%>
	<%ItemVO itemVO=itemSvc.getOneItem(fitem);%>
	<tr>
	<td><img class="item_pic"
							src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=<%=itemVO.getItemNo()%>" /></td>
	<td style="text-align:left";><font size="5px"><%=itemVO.getItemName()%></font></td>
	<td><font color="red" size="5px"><%=itemVO.getPrice()%>元</font></td>
	<td>
		<FORM class="items" METHOD="post"
		ACTION="<%=request.getContextPath()%>/store/cart.do">
		數量<select name="quantity">
			<%
				for (int i = 1; i <= (itemVO.getAmount()); i++) {
			%>
			<option value=<%=i%>><%=i%></option>
			<%
				}
			%>
		</select> 
		<input type="hidden" name="itemNo" value="<%=itemVO.getItemNo()%>"> 
		<input type="hidden" name="action" value="add_cart2">
		<input class="btn btn-primary" type="submit" value="拉進購物車！">
		</FORM>
	</td>
	</tr>

<%}%>
	</table>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
<!-- AjaxForm -->
<script src="<%=request.getContextPath()%>/front-end/CDN/jquery.form.js"></script>
<!-- Alert樣式 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css" rel="stylesheet">
<script src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
</body>
<script>
//ajaxForm
$(document).ready(function() {
	$(".items").ajaxForm(function(data) {
		// 						alertify.success('幹幹幹');
		// 			alertify.error('Hi! 這是錯誤的訊息通知！');  
		alertify.log('拉進購物車！');
		$("#count").load(location.href + " #count");
	});
});
</script>
</html>