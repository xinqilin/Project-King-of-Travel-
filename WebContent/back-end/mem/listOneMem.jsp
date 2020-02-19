<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
%>

<html>
<head>
<title>會員資料 - listOneMem.jsp</title>

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
		 <h3>員工資料 - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>Email</th>
		<th>密碼</th>
		<th>頭像</th>
		<th>暱稱</th>
		<th>身分證字號</th>
		<th>生日</th>
		<th>地址</th>
		<th>電話</th>
		<th>註冊日期</th>
		<th>狀態</th>
	</tr>
	<tr>
		<td><%=memVO.getMemNo()%></td>
		<td><%=memVO.getMemName()%></td>
		<td><%=memVO.getE_mail()%></td>
		<td><%=memVO.getMemPassWd()%></td>
		<td><%=memVO.getMemPhoto()%></td>
		<td><%=memVO.getNickName()%></td>
		<td><%=memVO.getIdNo()%></td>
		<td><%=memVO.getBirDay()%></td>
		<td><%=memVO.getAddress()%></td>
		<td><%=memVO.getPhone()%></td>
<%-- 		<td><%=memVO.getDateOfAccountEshablished()%></td> --%>
		<td><%=memVO.getStatus()%></td>
	</tr>
</table>

</body>
</html>