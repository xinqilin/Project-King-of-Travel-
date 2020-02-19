<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.administrator.model.*"%>

<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) �s�Jreq��administratorVO���� (�]�A�������X��administratorVO, �]�]�A��J��ƿ��~�ɪ�administratorVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�޲z����ƭק� - update_admin_input.jsp</title>

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
		 <h3>�޲z����ƭק� - update_admin_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>�޲z����ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="admin.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�޲z���s��:<font color=red><b>*</b></font></td>
		<td><%=administratorVO.getAdminNo()%></td>
	</tr>
	<tr>
		<td>�޲z���m�W:</td>
		<td><input type="TEXT" name="adminName" size="45" value="<%=administratorVO.getAdminName()%>" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="TEXT" name="e_mail" size="45"	value="<%=administratorVO.getE_mail()%>" /></td>
	</tr>
	<tr>
		<td>�K�X:</td>
		<td><input type="TEXT" name="adminPassWd" size="45"	value="<%=administratorVO.getAdminPassWd()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adminNo" value="<%=administratorVO.getAdminNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>