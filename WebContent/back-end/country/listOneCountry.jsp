<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.city.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<html>
<head>
<title>員工資料 - listOneCountry.jsp</title>

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

<h4>此頁將取出 一筆Country 資料</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="countrySelect.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>CountryNo</th>
		<th>CountryName</th>
	</tr>
	<tr>
		<td>${countryVO.countryNo}</td>
		<td>${countryVO.countryName}</td>
	</tr>

</table>

<a href=""></a>

</body>
</html>