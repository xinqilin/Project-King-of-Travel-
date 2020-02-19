<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wishitem.model.*"%>

<%
	WishItemVO wishItemVO = (WishItemVO) request.getAttribute("wishItemVO"); //WishListServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的wishListVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>許願池物品資料修改 - update_wishList_input.jsp</title>

<style>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>許願池資料修改 - update_wishList_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="wishItem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>代購清單編號:<font color=red><b>*</b></font></td>
		<td><%=wishItemVO.getWishItemNo()%></td>
	</tr>
	<tr>
		<td>代購會員編號:</td>
		<td><input type="TEXT" name="memNo" size="45" value="<%=wishItemVO.getMemNo()%>" /></td>
	</tr>
	<tr>
		<td>數量:</td>
		<td><input type="TEXT" name="amount" size="45" value="<%=wishItemVO.getAmount()%>" /></td>	
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="itemName" size="45" value="<%=wishItemVO.getItemName()%>" /></td>	
	</tr>
	<tr>
		<td>商品價錢:</td>
		<td><input type="TEXT" name="itemPrice" size="45" value="<%=wishItemVO.getItemPrice()%>" /></td>
	</tr>
	<tr>
		<td>商品店家名稱:</td>
		<td><input type="TEXT" name="itemStoreName" size="45" value="<%=wishItemVO.getItemStoreName()%>" /></td>
	</tr>
	<tr>
		<td>商品店家地址:</td>
		<td><input type="TEXT" name="itemStoreAddr" size="45" value="<%=wishItemVO.getItemStoreAddr()%>" /></td>
	</tr>
	<tr>
		<td>商品店家緯度:</td>
		<td><input type="TEXT" name="itemStoreLati" size="45" value="<%=wishItemVO.getItemStoreLati()%>" /></td>
	</tr>
	<tr>
		<td>商品店家經度:</td>
		<td><input type="TEXT" name="itemStoreLong" size="45" value="<%=wishItemVO.getItemStoreLong()%>" /></td>
	</tr>
	<tr>
		<td>買或賣:</td>
		<td><input type="TEXT" name="buyOrSell" size="45" value="<%=wishItemVO.getBuyOrSell()%>" /></td>
	</tr>
	<tr>
		<td>商品詳情:</td>
		<td><input type="TEXT" name="wishItemDetail" size="45" value="<%=wishItemVO.getWishItemDetail()%>" /></td>
	</tr>
	<tr>
		<td>商品店家圖片:</td>
		<td><input type="file" name="wishItemPicture" size="45" value="<%=wishItemVO.getWishItemPicture()%>" /></td>
	</tr>
	<tr>
		<td>代購但書:</td>
		<td><input type="TEXT" name="wishNote" size="45" value="<%=wishItemVO.getWishNote()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="status" size="45" value="<%=wishItemVO.getStatus()%>" /></td>
	</tr>
	<tr>
		<td>商品類型:</td>
		<td><input type="TEXT" name="itemType" size="45" value="<%=wishItemVO.getItemType()%>" /></td>
	</tr>
</table>
<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="wishItemNo" value="<%=wishItemVO.getWishItemNo()%>">
	<input type="submit" value="送出修改">
</FORM>



</body>




</html>