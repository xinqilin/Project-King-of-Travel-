<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM Emp: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>DA101 FuncList: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for DA101 FuncList: Home</p>

<h3>�޲z���\��d��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFunc.jsp'>List</a> all Funcs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="funclist.do" >
        <b>��J�\��s�� (�pFUN0001):</b>
        <input type="text" name="funcNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="funcSvc" scope="page" class="com.funcList.model.FuncListService" />
   
  <li>
     <FORM METHOD="post" ACTION="funclist.do" >
       <b>��ܥ\��s��:</b>
       <select size="1" name="funcNo">
         <c:forEach var="funcListVO" items="${funcSvc.all}" > 
          <option value="${funcListVO.funcNo}">${funcListVO.funcNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="funcList.do" >
       <b>��ܥ\��W��:</b>
       <select size="1" name="funcNo">
         <c:forEach var="funcListVO" items="${funcSvc.all}" > 
          <option value="${funcListVO.funcNo}">${funcListVO.funcName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�޲z���\��޲z</h3>

<ul>
  <li><a href='addFunc.jsp'>Add</a> a new Func.</li>
</ul>

</body>
</html>