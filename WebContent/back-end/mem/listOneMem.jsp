<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), �s�Jreq��memVO����
%>

<html>
<head>
<title>�|����� - listOneMem.jsp</title>

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
		 <h3>���u��� - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>Email</th>
		<th>�K�X</th>
		<th>�Y��</th>
		<th>�ʺ�</th>
		<th>�����Ҧr��</th>
		<th>�ͤ�</th>
		<th>�a�}</th>
		<th>�q��</th>
		<th>���U���</th>
		<th>���A</th>
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