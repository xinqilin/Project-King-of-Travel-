<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>

<%
  SpotListVO spotListVO = (SpotListVO) request.getAttribute("spotListsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>景點新增 - addSpot.jsp</title>

<style>
  table#table-1 {   #CCCCFF;
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
	margin-left:auto;
	margin-right:auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>景點新增 - addSpot.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="spot.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>景點名稱:</td>
		<td><input type="TEXT" name="spotName" size="45" 
			 value="<%= (spotListVO==null)? "SOGO台北復興館" : spotListVO.getSpotName()%>" /></td>
	</tr>
	<tr>
		<td>城市編號:</td>
		<td><input type="TEXT" name="cityNo" size="45"
			 value="<%= (spotListVO==null)? "CIT0002" : spotListVO.getCityNo()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="location" size="45"
			 value="<%= (spotListVO==null)? "台北市大安區忠孝東路三段300號" : spotListVO.getLocation()%>" /></td>
	</tr>
	<tr>
		<td>景點類型:</td>
		<td><input type="TEXT" name="spotType" size="45"
			 value="<%= (spotListVO==null)? "1" : spotListVO.getSpotType()%>" /></td>
	</tr>
	<tr>
		<td>景點圖片:</td>
		<td><input type="file" name="spotPhoto" size="45" /></td>
	</tr>
	<tr>
		<td>景點狀態:</td>
		<td><input type="TEXT" name="spotStatus" size="45"
			 value="<%= (spotListVO==null)? "0" : spotListVO.getSpotStatus()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="tel" size="45"
			 value="<%= (spotListVO==null)? "02-2776-5555" : spotListVO.getTel()%>" /></td>
	</tr>
	<tr>
		<td>緯度:</td>
		<td><input type="TEXT" name="spotLati" size="45"
			 value="<%= (spotListVO==null)? "25.041203" : spotListVO.getSpotLati()%>" /></td>
	</tr>
	<tr>
		<td>經度:</td>
		<td><input type="TEXT" name="spotLong" size="45"
			 value="<%= (spotListVO==null)? "121.542942" : spotListVO.getSpotLong()%>" /></td>
	</tr>
	<tr>
		<td>景點描述:</td>
<%-- 		<td><textarea cols="50" rows="15" name="spotDetail" value="<%= (spotListVO==null)? "SOGO百貨為具有領導流行、高級形象、機能豐富、高品味、高格調之全客層百貨公司，提供顧客親切、體貼、安全的服務" : spotListVO.getSpotDetail()%>"><%=spotListVO.getSpotDetail()%></textarea></td> --%>
		<td><input type="TEXT" name="spotDetail" size="45" value="<%= (spotListVO==null)? "SOGO百貨為具有領導流行、高級形象、機能豐富、高品味、高格調之全客層百貨公司，提供顧客親切、體貼、安全的服務" : spotListVO.getSpotDetail()%>" ></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</td>
	</tr>
	
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
</table>
</FORM>
</body>
</html>