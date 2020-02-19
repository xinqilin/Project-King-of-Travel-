<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>DA101G3 City: Home</title>

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
   <tr><td><h3>DA101G3 City: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for DA101G3 City: Home</p>

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
  <li><a href='listAllCity.jsp'>List</a> all Citys.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="city.do" >
        <b>��J�����s�� (�pCIT0001):</b>
        <input type="text" name="cityNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="citySvc" scope="page" class="com.city.model.CityService" />
   
  <li>
     <FORM METHOD="post" ACTION="city.do" >
       <b>��ܫ����s��:</b>
       <select size="1" name="cityNo">
         <c:forEach var="cityVO" items="${citySvc.all}" > 
          <option value="${cityVO.cityNo}">${cityVO.cityNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="city.do" >
       <b>��ܫ����m�W:</b>
       <select size="1" name="cityNo">
         <c:forEach var="cityVO" items="${citySvc.all}" > 
          <option value="${cityVO.cityNo}">${cityVO.cityName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�����޲z</h3>

<ul>
  <li><a href='addCity.jsp'>Add</a> a new City.</li>
</ul>

</body>
</html>