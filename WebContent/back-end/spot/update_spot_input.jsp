<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>

<%
  SpotListVO spotListVO = (SpotListVO) request.getAttribute("spotListVO"); //SpotListServlet.java (Concroller) 存入req的spotListVO物件 (包括幫忙取出的spotListVO, 也包括輸入資料錯誤時的spotListVO物件)
  pageContext.setAttribute("spotListVO",spotListVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>景點資料修改 - update_spot_input.jsp</title>

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
	width: 500px;
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
   td{
  width:300px;
  }
  

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>景點資料修改 - update_spot_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/spot/spot.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>景點編號:</td>
		<td><%=spotListVO.getSpotNo()%></td>
	</tr>
	<tr>
		<td>景點名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="spotName" size="45" value="<%=spotListVO.getSpotName()%>" /></td>
	</tr>
	<tr>
		<td>城市編號:</td>
<%-- 		<td><%=spotListVO.getCityNo()%></td> --%>
		<td><input type="TEXT" name="cityNo" size="45"   value="<%=spotListVO.getCityNo()%>" /></td>
	</tr>
	<tr>
		<td>地址:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="location" size="45"	value="<%=spotListVO.getLocation()%>" /></td>
	</tr>
	<tr>
		<td>景點類型:</td>
		<td><input type="TEXT" name="spotType" size="45"	value="<%=spotListVO.getSpotType()%>" /></td>
	</tr>
	<tr>
		<td>景點照片:</td>
		<td>
			<img alt="img" src="<%=request.getContextPath()%>/back-end/spot/getspotphoto.do?spotno=${spotListVO.spotNo}"/>
			<input type="file" name="spotPhoto" size="45" value="<%=spotListVO.getSpotPhoto()%>" />
		</td>
	</tr>
	<tr>
		<td>景點狀態:</td>
		<td><input type="TEXT" name="spotStatus" size="45" value="<%=spotListVO.getSpotStatus()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="tel" size="45" value="<%=spotListVO.getTel()%>" /></td>
	</tr>
	<tr>
		<td>景點緯度:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="spotLati" size="45" value="<%=spotListVO.getSpotLati()%>" /></td>
	</tr>
	<tr>
		<td>景點經度:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="spotLong" size="45" value="<%=spotListVO.getSpotLong()%>" /></td>
	</tr>
	<tr>
		<td>景點描述:</td>
		<td><textarea cols="50" rows="15" name="spotDetail"><%=spotListVO.getSpotDetail()%></textarea></td>
<%-- 		<td><input type="TEXT" name="spotDetail" size="45" value="<%=spotListVO.getSpotDetail()%>" /></td> --%>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="spotNo" value="<%=spotListVO.getSpotNo()%>">
			<input type="submit" value="送出修改" style="text-align: center">
		</td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
</FORM>
</body>
</html>