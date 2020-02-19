<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DA101G3 Mem: Home</title>

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
   <tr><td><h3>DA101G3 Mem: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for DA101G3 Mem: Home</p>

<h3>��Ƭd��:</h3>
	
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
  <li><a href='listAllMem.jsp'>List</a> all Mems.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="mem.do" >
        <b>��J�|���s�� (�pMEM0001):</b>
        <input type="text" name="memNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="memNo">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memNo}">${memVO.memNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
       <b>��ܷ|���m�W:</b>
       <select size="1" name="memNo">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memNo}">${memVO.memName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�|���޲z</h3>

<ul>
  <li><a href='addMem.jsp'>Add</a> a new Mem.</li>
</ul>

</body>
</html>