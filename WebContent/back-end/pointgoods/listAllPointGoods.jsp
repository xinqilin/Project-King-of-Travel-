<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.pointgoods.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PointGoodsService pointgoodsSvc = new PointGoodsService();
    List<PointGoodsVO> list = pointgoodsSvc.getAll();
    pageContext.setAttribute("list",list);
    Boolean isInsert = false;
    Boolean isUpdate = false;
    int upageIndex = 0; int uwhichPage = 0;
%>

<html>
<head>
<title>所有員工資料 - listAllPointGoods.jsp</title>

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
  img {
  	width: 100px;
  	height :100px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="selectPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>--%>

<table>
	<tr>
		<th>積分商品編號</th>
		<th>積分商品名稱</th>
		<th>積分商品數量</th>
		<th>積分商品需求點數</th>
		<th>積分商品描述</th>
		<th>積分商品圖片</th>
		<th>積分商品狀態</th>
	</tr>
	<c:if test="${requestScope.in == 1}"><%isInsert=true;%></c:if>
	<c:if test="${requestScope.in == 2}"> 
	<%
		isUpdate=true;
		upageIndex = (int)session.getAttribute("upageIndex");
    	uwhichPage = (int)session.getAttribute("uwhichPage");
	%></c:if>
	<%@ include file="page1.file" %>
	<c:forEach var="pointgoodsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${pointgoodsVO.pointgoodsno}</td>
			<td>${pointgoodsVO.pointgoodsname}</td>
			<td>${pointgoodsVO.pointgoodsquantity}</td>
			<td>${pointgoodsVO.needpoints}</td>
			<td>${pointgoodsVO.pointgoodsdesc}</td>
			<td><img alt="img" src="data:image/*;base64,${Base64.getEncoder().encodeToString(pointgoodsVO.getPointgoodspic())}"/></td>
			<%-- <td><img src="http://localhost:8081/DA101G3/pointgoods/showpic.do"></td>--%>
			<%-- <td>${pointgoodsVO.pointgoodsstatus}</td>--%>
			<%-- <td><c:out value="${Status.getStatus(pointgoodsVO.pointgoodsstatus)}"/></td>--%>
			<td>${Status.getStatus(pointgoodsVO.pointgoodsstatus)}</td>
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pointgoods/pointgoods.do" style="margin-bottom: 0px;">
			     <%session.setAttribute("upageIndex", pageIndex);%>
			     <%session.setAttribute("uwhichPage", whichPage);%>
			     <input type="submit" value="修改">
			     <input type="hidden" name="pointgoodsno"  value="${pointgoodsVO.pointgoodsno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pointgoods/pointgoods.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="pointgoodsno"  value="${pointgoodsVO.pointgoodsno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	<tr></tr>
</table>
	<%@ include file="page2.file" %>
</body>
</html>