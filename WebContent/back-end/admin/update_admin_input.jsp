<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.administrator.model.*"%>

<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) 存入req的administratorVO物件 (包括幫忙取出的administratorVO, 也包括輸入資料錯誤時的administratorVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_admin_input.jsp</title>

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
		 <h3>管理員資料修改 - update_admin_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>管理員資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td><%=administratorVO.getAdminNo()%></td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="adminName" size="45" value="<%=administratorVO.getAdminName()%>" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="TEXT" name="e_mail" size="45"	value="<%=administratorVO.getE_mail()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="adminPassWd" size="45"	value="<%=administratorVO.getAdminPassWd()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adminNo" value="<%=administratorVO.getAdminNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>