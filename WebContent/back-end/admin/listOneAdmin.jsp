<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.administrator.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO"); //AdministratorServlet.java(Concroller), �s�Jreq��administratorVO����
%>

<html>
<head>
<title>�޲z����� - listOneAdmin.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�޲z����� - ListOneAdmin.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�޲z���s��</th>
		<th>�޲z���m�W</th>
		<th>Email</th>
		<th>�K�X</th>
	</tr>
	<tr>
		<td><%=administratorVO.getAdminNo()%></td>
		<td><%=administratorVO.getAdminName()%></td>
		<td><%=administratorVO.getE_mail()%></td>
		<td><%=administratorVO.getAdminPassWd()%></td>
	</tr>
</table>

</body>
</html>