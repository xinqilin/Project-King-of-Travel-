<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spot.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SpotListVO spotListVO = (SpotListVO) request.getAttribute("spotListVO");
    SpotListService spotSvc = new SpotListService();
    List<SpotListVO> list = spotSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有景點資料 - listAllSpot.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有景點資料 - listAllSpot.jsp</h3>
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

<table>
	<tr>
		<th>景點編號</th>
		<th>景點名稱</th>
		<th>城市編號</th>
		<th>地址</th>
		<th>景點類型</th>
		<th>景點照片</th>
		<th>景點狀態</th>
		<th>電話</th>
		<th>景點緯度</th>
		<th>景點經度</th>
		<th>景點描述</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="spotListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${spotListVO.spotNo}</td>
			<td>${spotListVO.spotName}</td>
			<td>${spotListVO.cityNo}</td>
			<td>${spotListVO.location}</td>
			<td>${spotListVO.spotType}</td>
			<td><img alt="img" src="<%=request.getContextPath()%>/back-end/spot/getspotphoto.do?spotno=${spotListVO.spotNo}"/></td>
			<td>${spotListVO.spotStatus}</td>
			<td>${spotListVO.tel}</td>
			<td>${spotListVO.spotLati}</td>
			<td>${spotListVO.spotLong}</td>
			<td>${spotListVO.spotDetail}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="spotNo"  value="${spotListVO.spotNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/spot/spot.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="spotNo"  value="${spotListVO.spotNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>