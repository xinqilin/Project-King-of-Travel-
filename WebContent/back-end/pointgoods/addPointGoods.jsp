<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointgoods.model.*"%>

<%
	PointGoodsVO pointgoodsVO = (PointGoodsVO) request.getAttribute("pointgoodsVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>商品資料新增 addPointGoods.jsp</title>

<style>
	body {
		background-color: #77DDFF;
	}
	img {
  	width: 100px;
  	height :100px;
    }
</style>
</head>
<body>
	<table id="table-1">
	<tr><td>
		 <h3>商品資料新增 addPointGoods.jsp</h3></td><td>
		 <h4><a href="selectPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>	
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="pointgoods.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pointgoodsname" size="45" 
			 value="<%= (pointgoodsVO==null)? "這是商品" : pointgoodsVO.getPointgoodsname()%>" /></td>
	</tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="pointgoodsquantity" size="45"
			 value="<%= (pointgoodsVO==null)? 666 : pointgoodsVO.getPointgoodsquantity()%>" /></td>
	</tr>
	<tr>
		<td>需求點數:</td>
		<td><input type="TEXT" name="needpoints" size="45"
			 value="<%= (pointgoodsVO==null)? 666 : pointgoodsVO.getNeedpoints()%>" /></td>
	</tr>
	<tr>
		<td>商品描述:</td>
		<td><textarea name="pointgoodsdesc" style="width:400px;height:50px;"><%= (pointgoodsVO==null)? "請勿空白喔!" : pointgoodsVO.getPointgoodsdesc()%></textarea></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
		<td>	
			<input type="FILE" accept="image/*" name="pointgoodspic" onchange="loadFile(event)"/>
			<img id="output"/>
		</td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td>
			<select size="1" name="pointgoodsstatus">
			 <option value="0">下架</option>
			 <option value="1">上架</option>
			</select>
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

<script>
			var loadFile = function(event) {
				var output = document.getElementById('output');
				output.src = URL.createObjectURL(event.target.files[0]);
			};
</script>


</body>
</html>