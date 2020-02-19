<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.administrator.model.*"%>

<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員新增 - addEmp.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>管理員新增 - addAdmin.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>管理員新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1">
<table>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="adminName" size="45" 
			 value="<%= (administratorVO==null)? "吳永志" : administratorVO.getAdminName()%>" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="TEXT" name="e_mail" size="45"
			 value="<%= (administratorVO==null)? "seafood@gmail.com" : administratorVO.getE_mail()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="adminPassWd" size="45"
			 value="<%= (administratorVO==null)? "12345678" : administratorVO.getAdminPassWd()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>