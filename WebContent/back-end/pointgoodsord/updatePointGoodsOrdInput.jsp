<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointgoodsord.model.*"%>
<%@ page import="com.pointgoods.model.*"%>
<%@ page import="java.util.*"%>

<%
  PointGoodsOrdVO pointgoodsordVO = (PointGoodsOrdVO) request.getAttribute("pointgoodsordVO"); 
%>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.twzipcode.min.js"></script>

<title>積分商品訂單資料修改 - update_pointgoodsord_input.jsp</title>

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
		 <h3>積分商品訂單資料修改 - update_pointgoodsord_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/pointgoodsord/selectPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

	<%	
		String county, district, address, taddress;
		Set<String> cd = new HashSet<String>();
		cd.add("鄉");
		cd.add("鎮");
		cd.add("市");
		cd.add("區");
		cd.add("島");
		taddress = pointgoodsordVO.getAddress();
		county = taddress.substring(0, 3);
		if(taddress.substring(4,5).equals("區")){
			district = taddress.substring(3, 5);
			address = taddress.substring(5);
		}else if(cd.contains(taddress.substring(5,6))){
			district = taddress.substring(3, 6);
			address = taddress.substring(6);
		}else if(cd.contains(taddress.substring(6,7))){
			district = taddress.substring(3, 7);
			address = taddress.substring(7);
		}else{
			district = taddress.substring(3, 8);
			address = taddress.substring(8);
		}
	%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="GET" ACTION="pointgoodsord.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td>
			<%=pointgoodsordVO.getPointgoodsordno()%></td>
	</tr>
	<tr>
		<td>寄件會員編號:</td>
		<td><%=pointgoodsordVO.getMemno()%></td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><input type="TEXT" name="pointgoodsname" size="45" 
			 value="<%=pointgoodsordVO.getReceiver()%>"/></td>
	</tr>
	<tr>
		<td>收件人電話:</td>
		<td><input type="TEXT" name="pointgoodsname" size="45" 
			 value="<%=pointgoodsordVO.getPhone()%>"/></td>
	</tr>
	<tr>
		<td>收件地址:</td>
		<%-- <td><input type="TEXT" name="address" size="45"
			 value="<%=pointgoodsordVO.getAddress()%>"/></td>--%>
		<td>
			<div class="twzipcode" id='zip1'></div>
			<input type="text" id="addresssub" value="<%=address%>">
		</td>	 
	</tr>
	<tr>
		<td>訂單成立時間:</td>
		<td><%=Status.getNoZeroDate(pointgoodsordVO.getOrderdate())%></td>
	</tr>
		<td>訂單狀態:</td>
		<td>
		<select size="1" name="pointgoodsstatus">
			 <option value="0" ${(pointgoodsordVO.orderstatus==0)?'selected':''}>已結賬未出貨</option>
			 <option value="1" ${(pointgoodsVO.orderstatus==1)?'selected':''}>已結賬已出貨</option>
			 <option value="2" ${(pointgoodsVO.orderstatus==2)?'selected':''}>已取消</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>訂單點數:</td>
		<td><%=pointgoodsordVO.getOrderpoint()%></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pointgoodsordno" value="<%=pointgoodsordVO.getPointgoodsordno()%>">
<input type="submit" value="送出修改"></FORM>

<script>
$('.twzipcode').twzipcode({
	countySel: "<%=county%>",
	districtSel:"<%=district%>",
	zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
	css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱 
	});
</script>


</body>
</html>