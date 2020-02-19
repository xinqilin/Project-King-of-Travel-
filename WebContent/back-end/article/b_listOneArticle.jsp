<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.article.model.*"%>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<% 
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //ArticleServlet.java(Controller), 存入req的articleVO物件
%>

<html>
<head>

<title>listOneArticle.jsp</title>


</head>
<body>



<!-- 		<h4><a href="/DA101G3/back-end/article/select_page.jsp">回首頁</a></h4> -->
<%-- 		<h4><a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">回首頁</a></h4> --%>


<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>遊記編號</th> -->
<!-- 		<th>會員編號</th> -->
<!-- 		<th>行程編號</th> -->
<!-- 		<th>遊記名稱</th> -->
<!-- 		<th>總天數</th> -->
<!-- 		<th>遊記起始日</th> -->
<!-- 		<th>遊記結束日</th> -->
<!-- 		<th>遊記狀態</th> -->
<!-- 		<th>遊記編輯日</th> -->
<!-- 		<th>瀏覽次數</th> -->
<!-- 		<th>出遊類型</th> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<%-- 		<td><%=articleVO.getArticleno()%></td> --%>
<%-- 		<td><%=articleVO.getMemno()%></td> --%>
<%-- 		<td><%=articleVO.getTripno()%></td> --%>
<%-- 		<td><%=articleVO.getArticletitle()%></td> --%>
<%-- 		<td><%=articleVO.getDaysofstaying()%></td> --%>
<%-- 		<td><%=articleVO.getDayofstart()%></td> --%>
<%-- 		<td><%=articleVO.getDayofend()%></td> --%>
<%-- 		<td><%=articleVO.getArticlestatus()%></td> --%>
<%-- 		<td><%=articleVO.getDayoflastedit()%></td> --%>
<%-- 		<td><%=articleVO.getTimeofviews()%></td> --%>
<%-- 		<td><%=articleVO.getKindoftrip()%></td> --%>
<!-- 	</tr>	 -->
<!-- </table> -->

	<div class="card" style="width: 18rem;">
  		<img src="https://picsum.photos/350/200?random=1" class="card-img-top" alt="...">
  		<div class="card-body">
    		<h5 class="card-title">${articleVO.articletitle}</h5>
    		<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    		<a href="#" class="btn btn-primary">ArticleDetails</a>
  		</div>
	</div>

</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</html>