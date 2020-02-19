<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.country.model.*"%>

<% CountryVO country= (CountryVO) request.getAttribute("country"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Country</title>
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
</head>
<table id="table-1">
	<tr><td>
		 <h3>修改國家資料 - update_country_info.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/country/countrySelect.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="country.do" name="form1">
<table>
	<tr>
		<td>國家編號:</td>
		<td><%=country.getCountryNo() %></td>
	</tr>
	<tr>
		<td>國家名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="countryName" size="45" value="<%=country.getCountryName()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="countryNo" value="<%=country.getCountryNo() %>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>