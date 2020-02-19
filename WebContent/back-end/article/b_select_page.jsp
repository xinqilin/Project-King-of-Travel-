<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list",list);	


	Object account = session.getAttribute("account"); 
	if (account == null) { 
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/b_login.jsp"); 
	return;
} %>

<title>Insert title here</title>

<!-- Required meta tags -->

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

</head>
<body>

				<c:if test="${account!=null}">
					<li class="nav-item"><a class="nav-link" href="#">Hello! ${account}</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet.do">logout</a></li>
				</c:if>

	<table class="table">
		<tr>
			<th>遊記照片</th>
			<th>遊記編號</th>
			<th>會員編號</th>
			<th>行程編號</th>
			<th>遊記名稱</th>
			<th>總天數</th>
			<th>起始日</th>
			<th>結束日</th>
			<th>出遊類型</th>
			<th>瀏覽次數</th>
			<th>上次編輯日</th>
			<th>建立日期</th>
			<th></th>
			<th></th>
		</tr>
		<%@ include file="page1.file" %> 
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>
				<c:if test="${articleVO.articlepic==null }"><img src="<%=request.getContextPath()%>/front-end/article/images/articlenopic.jpg" style="border:1px blue dashed;width:100px;height:100px;"></c:if>
				<c:if test="${articleVO.articlepic!=null }"><img src="<%=request.getContextPath()%>/article/ArticlePic?articleno=${articleVO.articleno}" style="border:1px blue dashed;width:100px;height:100px;"></c:if>
			</td>
			<td>${articleVO.articleno}</td>
			<td>${articleVO.memno}</td>
			<td>${articleVO.tripno}</td>
			<td>${articleVO.articletitle}</td>
			<td>${articleVO.daysofstaying}</td>
			<td>${articleVO.dayofstart}</td>
			<td>${articleVO.dayofend}</td>
			<td>${articleVO.kindoftrip}</td>
			<td>${articleVO.timeofviews}</td>
			<td>${articleVO.dayoflastedit}</td>
			<td>${articleVO.dayofcreate}</td>
			<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="articleno"  value="${articleVO.articleno}">
				<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
				<input type="submit" value="刪除">
				<input type="hidden" name="articleno"  value="${articleVO.articleno}">
				<input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		</c:forEach>	
	</table>
	<%@ include file="page2.file" %>
</body>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</html>