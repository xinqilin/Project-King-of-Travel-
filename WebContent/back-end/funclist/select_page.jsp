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

<h3>管理員功能查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
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
        <b>輸入功能編號 (如FUN0001):</b>
        <input type="text" name="funcNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="funcSvc" scope="page" class="com.funcList.model.FuncListService" />
   
  <li>
     <FORM METHOD="post" ACTION="funclist.do" >
       <b>選擇功能編號:</b>
       <select size="1" name="funcNo">
         <c:forEach var="funcListVO" items="${funcSvc.all}" > 
          <option value="${funcListVO.funcNo}">${funcListVO.funcNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="funcList.do" >
       <b>選擇功能名稱:</b>
       <select size="1" name="funcNo">
         <c:forEach var="funcListVO" items="${funcSvc.all}" > 
          <option value="${funcListVO.funcNo}">${funcListVO.funcName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>管理員功能管理</h3>

<ul>
  <li><a href='addFunc.jsp'>Add</a> a new Func.</li>
</ul>

</body>
</html>