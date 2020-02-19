<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	Object account = session.getAttribute("accountVO");
	String memno = (String) session.getAttribute("memno");

	if (account == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
	
	
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAllByMemno(memno);
	pageContext.setAttribute("list", list);
	
%>



<html>
<head>
<meta charset="utf-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>listAllArticle.jsp</title>



</head>
<body style="background-color:#eafafa; font-family:微軟正黑體;">
<%@ include file="/nav-f"%>
	<div class="container-fluid">
		<div class="row" style="margin-top: 50px;">
			<div class="col-md-1">				
			</div>
			<div class="col-md-8">
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
					<%@ include file="page1.file"%>
					<%
						int r = 0;
					%>
					<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
							<%
								r++;
							%>
							<div class="shadow p-3 mb-5 bg-white rounded article-by-memno" style="margin-top:25px; display:flex;">
								<div class="tr">	
									<c:if test="${articleVO.articlepic==null }"><img src="<%=request.getContextPath()%>/front-end/article/images/articlenopic.jpg" style="border:1px;width:500px;height:300px;"></c:if>
									<c:if test="${articleVO.articlepic!=null }"><img src="<%=request.getContextPath()%>/article/ArticlePic?articleno=${articleVO.articleno}" style="border:1px;width:500px;height:300px;"></c:if>
								</div>
								<div class="tr" style="width:50px;">
								</div>
<%-- 							<td>${articleVO.articleno}</td> --%>
<%-- 							<td>${articleVO.memno}</td> --%>
<%-- 							<td>${articleVO.tripno}</td> --%>
								<div class="tr">
									<span style="font-size:30px"><strong>&nbsp;${articleVO.articletitle}&nbsp;</strong></span><br><br>
<%-- 									<td>${articleVO.daysofstaying}</td> --%>
									<span><img src="<%=request.getContextPath()%>/front-end/article/images/icon_calender.png">&nbsp;${articleVO.dayofstart} ~ ${articleVO.dayofend}</span><br><br>
<%-- 									<td><c:if test="${articleVO.articlestatus==0}"> --%>
<%-- 											<c:out value="草稿" /> --%>
<%-- 										</c:if> <c:if test="${articleVO.articlestatus==1}"> --%>
<%-- 											<c:out value="公開" /> --%>
<%-- 										</c:if> <c:if test="${articleVO.articlestatus==2}"> --%>
<%-- 											<c:out value="進冷凍庫了兄D" /> --%>
<%-- 										</c:if></td> --%>

<%-- 									<td>${articleVO.dayoflastedit}</td> --%>
									<span><img src="<%=request.getContextPath()%>/front-end/article/images/icon_views.png">&nbsp;${articleVO.timeofviews}</span><br><br>
									<span>
										<c:if test="${articleVO.kindoftrip==0}">
										類 型 :	
											<c:out value="單 獨 旅 行" />
										</c:if> <c:if test="${articleVO.kindoftrip==1}">
											<c:out value="情 侶 出 遊" />
										</c:if> <c:if test="${articleVO.kindoftrip==2}">
											<c:out value="家 庭 親 子" />
										</c:if> <c:if test="${articleVO.kindoftrip==3}">
											<c:out value="朋 友 出 遊" />
										</c:if> <c:if test="${articleVO.kindoftrip==4}">
											<c:out value="商 務 旅 遊" />
										</c:if> <c:if test="${articleVO.kindoftrip==5}">
											<c:out value="其 它" />
										</c:if>
									</span><br><br>
									
<%-- 									<td>${articleVO.dayofcreate}</td> --%>
								
									<div class="" style="display: flex">	
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/article/article.do"
											style="margin-bottom: 0px;">
											<c:if test="${articleVO.articlestatus!=2}">
												<label><input type="submit" value="修改" class="btn btn-outline-primary btn-lg"></label>
											</c:if>
											<c:if test="${articleVO.articlestatus==2}">
												<font
													style="color: red;">冰箱<br>無法更改
												</font>
											</c:if>
											<input type="hidden" name="articleno"
												value="${articleVO.articleno}"> <input type="hidden"
												name="action" value="getOne_For_Update">
										</FORM>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/article/article.do"
											style="margin-bottom: 0px;">
											<label><input type="submit" value="刪除" onclick="deleteArticle()" class="btn btn-outline-danger btn-lg"></label>
											<input type="hidden"
												name="articleno" value="${articleVO.articleno}"> <input
												type="hidden" name="action" value="delete">
										</FORM>
									</div>
								</div>
							</div>
							<hr style="border: 0; height: 1px; background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));">
							</c:forEach>
							</div>
							<c:if test="<%=r == 0%>">
								<center>
									<font style="color: red; font-size: 100px;">沒有分享過遊記</font>>
								</center>
							</c:if>
				<%@ include file="page2.file"%>
			</div>
			<div class="col-md-3">
				
			</div>
		</div>
	
	
	<div class="container-fluid">
		<div class="row" style="margin-top: 50px;">
			<div class="col-md-1">
			</div>			
			<div class="col-md-8">
				<div class="d-flex flex-wrap" style="margin-top: auto;">
					<c:forEach var="articleVO" items="${list}" >
						<div class="table">
							
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</div>

</body>

<script>
	
function deleteArticle() {
	var msg = "您真的確定要刪除嗎？\n\n刪除後就無法回復了！";
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}
	
</script>

<!-- <script src="js/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.min.js"></script> -->
<!-- <script src="js/scripts.js"></script> -->
</html>