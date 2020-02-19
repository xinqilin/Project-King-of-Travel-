<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  SpotListVO spotListVO = (SpotListVO) request.getAttribute("spotListVO"); //SpotListServlet.java(Concroller), 存入req的spotListVO物件
  pageContext.setAttribute("spotListVO",spotListVO);
%>

<html>
<head>
<title>景點資料 - listOneSpot.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>景點資料 - ListOneSpot.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>
		<td><%=spotListVO.getSpotNo()%></td>
		<td><%=spotListVO.getSpotName()%></td>
		<td><%=spotListVO.getCityNo()%></td>
		<td><%=spotListVO.getLocation()%></td>
		<td><%=spotListVO.getSpotType()%></td>
		<td><img alt="img" src="<%=request.getContextPath()%>/back-end/spot/getspotphoto.do?spotno=${spotListVO.spotNo}"/></td>
		<td><%=spotListVO.getSpotStatus()%></td>
		<td><%=spotListVO.getTel()%></td>
		<td><%=spotListVO.getSpotLati()%></td>
		<td><%=spotListVO.getSpotLong()%></td>
		<td><%=spotListVO.getSpotDetail()%></td>
	</tr>
</table>

</body>
</html>