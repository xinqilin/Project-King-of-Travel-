<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.country.model.*"%>

<%
    CountryService countrySvc= new CountryService();
    List<CountryVO> list = countrySvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List-all countries by taking data from session by EL</title>
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

<body>
<h4> 此頁練習採用  JSP 寫法取 CountryList</h4>
<table id="table-1">
	<tr><td>
		 <h3>全部國家資料 - ListAllCountry.jsp</h3>
		 <h4><a href="countrySelect.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>國家編號</th>
		<th>國家名稱</th>
		<th colspan="2">功能選擇</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="CountryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${CountryVO.countryNo}</td>
			<td>${CountryVO.countryName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/country/country.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="countryNo"  value="${CountryVO.countryNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/country/country.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="countryNo"  value="${CountryVO.countryNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
</html>