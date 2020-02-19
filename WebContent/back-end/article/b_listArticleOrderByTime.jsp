<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
    ArticleService articleSvc = new ArticleService();
    List<ArticleVO> list = articleSvc.getAllOrderByTime();
    pageContext.setAttribute("list",list);
    Object account = session.getAttribute("backaccount"); 
	if (account == null) { 
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/b_login.jsp"); 
	return;
	}
%>

<html>
<head>

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

<title>Insert title here</title>
</head>
<body>			
		
		
				<c:if test="${account!=null}">
					<li class="nav-item"><a class="nav-link" href="#">Hello! ${account}</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet.do">logout</a></li>
				</c:if>
		
			 
				<!-- onclick="location.href='/DA101G3/back-end/article/select_page.jsp'" -->
		<%-- 錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
				<a href="<%=request.getContextPath()%>/back-end/article/addArticle.jsp" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">新建遊記</a>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">
				<b>輸入遊記編號:</b> <input type="text" name="articleno"> <input type="hidden" name="action" value="getOne_For_Display"> <input type="submit" class="btn btn-primary" value="送出">
				</FORM>

				<a href="" class="btn btn-secondary btn-lg disabled" role="button">最新建立</a>
				<a href="<%=request.getContextPath()%>/back-end/article/listArticleOrderByViews.jsp" class="btn btn-secondary btn-lg" role="button">最多瀏覽</a>
				<a href="#" class="btn btn-secondary btn-lg" role="button">搜尋條件</a>
				<a href="#" class="btn btn-secondary btn-lg" role="button">搜尋條件</a>
	
		<div class="row" style="margin-top:15px;">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>封面照片</th>
							<th>遊記編號</th>
							<th>會員編號</th>
							<th>行程編號</th>
							<th>遊記名稱</th>
							<th>總天數</th>
							<th>遊記起始日</th>
							<th>遊記結束日</th>
							<th>遊記狀態</th>
							<th>遊記編輯日</th>
							<th>瀏覽次數</th>
							<th>出遊類型</th>
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
							<td>${articleVO.articlestatus}</td>
							<td>${articleVO.dayoflastedit}</td>
							<td>${articleVO.timeofviews}</td>
							<td>${articleVO.kindoftrip}</td>
							<td>${articleVO.dayofcreate}</td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
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
			</div>
		</div>
	
</body>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</html>