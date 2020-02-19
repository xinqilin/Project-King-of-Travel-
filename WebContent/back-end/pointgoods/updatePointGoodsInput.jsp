<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointgoods.model.*"%>
<%@ page import="java.util.*"%>

<%
  PointGoodsVO pointgoodsVO = (PointGoodsVO) request.getAttribute("pointgoodsVO"); 
%>

<html>
<head>
<title>積分商品資料修改 - update_pointgoods_input.jsp</title>

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
  img {
  	width: 100px;
  	height :100px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>積分商品資料修改 - updatePointgoodsInput.jsp</h3>
		 <h4><a href="selectPage.jsp">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="pointgoods.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td>
			<%=pointgoodsVO.getPointgoodsno()%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pointgoodsname" size="45" 
			 value="<%=pointgoodsVO.getPointgoodsname()%>"/></td>
	</tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="pointgoodsquantity" size="45"
			 value="<%=pointgoodsVO.getPointgoodsquantity()%>"/></td>
	</tr>
	<tr>
		<td>需求點數:</td>
		<td><input type="TEXT" name="needpoints" size="45"
			 value="<%=pointgoodsVO.getNeedpoints()%>"/></td>
	</tr>
	<tr>
		<td>商品描述:</td>
		<td><textarea name="pointgoodsdesc" style="width:400px;height:50px;"><%=pointgoodsVO.getPointgoodsdesc()%></textarea></td>
	</tr>
	<tr>
		<td>商品圖片:<img alt="img" src="data:image/*;base64,${Base64.getEncoder().encodeToString(pointgoodsVO.getPointgoodspic())}" id="output"/></td>
		<td><input type="FILE" accept="image/*" name="pointgoodspic" onchange="loadFile(event)"></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td>
			<select size="1" name="pointgoodsstatus">
			 <option value="0" ${(pointgoodsVO.pointgoodsstatus==0)?'selected':''}>下架</option>
			 <option value="1" ${(pointgoodsVO.pointgoodsstatus==1)?'selected':''}>上架</option>
			</select>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pointgoodsno" value="<%=pointgoodsVO.getPointgoodsno()%>">
<input type="submit" value="送出修改"></FORM>

<script>
			<%--$(document).ready(function() {
				$("imgupload").change(function(){
					
				});
			});--%>
			var loadFile = function(event) {
				var output = document.getElementById('output');
				output.src = URL.createObjectURL(event.target.files[0]);
			};
</script>

</body>
</html>