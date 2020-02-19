<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.country.model.*"%>

<%
	String account = (String)session.getAttribute("account");
	String memno = (String) session.getAttribute("memno");

	MemService memSvc = new MemService();
	List<MemVO> list3 = memSvc.getAll();
	pageContext.setAttribute("list3",list3);
	
	CountryService countrySvc = new CountryService();
    List<CountryVO> list2 = countrySvc.getAll();
    pageContext.setAttribute("list2",list2);
    String cityno=(String)request.getParameter("countryNo");

	
	ArticleService articleSvc = new ArticleService();
    List<ArticleVO> list = articleSvc.getAllOrderByTime();
    pageContext.setAttribute("list",list);
    
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

<title>遊記首頁</title>

<style>
	.shadow-sm:hover{
		cursor: pointer;
	}

</style>

</head>
<body>
<%@ include file="/nav-f1"%>
<div class="container-fluid">
<!-- 	<div class="row" style="margin-top:10px;"> -->
<!-- 		<div class="col-md-12"> -->
<%-- 			<img src="<%=request.getContextPath()%>/front-end/article/images/article-titlepic02.jpg" style="width: 100%; height:65%;"> --%>
<!-- 		</div> -->
<!-- 	</div>	 -->
	<div class="row" style="margin-top:50px;">
		<div class="col-md-3">
				 
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
		</div>
<!-- 		<div class="col-md-1"> -->
<%-- 			<a href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">新建遊記</a> --%>
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<!-- 			<b>輸入遊記編號:</b> <input type="text" name="articleno"> <input type="hidden" name="action" value="getOne_For_Display"> <input type="submit" class="btn btn-primary" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</div>		 -->
		<div class="col-md-1">
			<a href="<%=request.getContextPath()%>/front-end/article/listArticleOrderByTime.jsp" class="btn btn-outline-success btn-lg disabled" role="button">最新建立</a>
		</div>
		<div class="col-md-1">
			<a href="<%=request.getContextPath()%>/front-end/article/listArticleOrderByViews.jsp" class="btn btn-outline-success btn-lg" role="button">最多瀏覽</a>
		</div>
		<div class="col-md-1">
			<a href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp" class="btn btn-outline-info btn-lg" role="button">分享遊記</a>
		</div>				
		<div class="col-md-1">
			<a href="<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp" class="btn btn-outline-info btn-lg" role="button">個人遊記</a>
		</div>
<!-- 		<div class="col-md-1"> -->
<!-- 		</div> -->
		<div class="col-md-1">
		</div>
		<div class="col-md-2">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" class="form-inline my-2 my-lg-0">
			<div class="keyword-input">
				<input type="text" name="keyword" value="" style="background-color: white;border:1px green solid;color:black;">
			</div>&nbsp;&nbsp;&nbsp;
			<div class="search-submit">
				<input type="submit" class="btn btn-outline-success" value="Search">
				<input type="hidden" name="action" value="keyword_search">
			</div>
			</FORM>
		</div>
	</div>
	
	<div class="row" style="margin-top:15px;">
		<div class="col-md-3" style="text-align:left;">
			
		</div>
				<div class="col-md-5" id="list_all_article">
			<div class="article-articleVO">
				<%@ include file="page1.file" %> 
				<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 				<c:if test="${articleVO.articlestatus==1}"> --%>
					<label>
					<span style="text-align:center; font-size:35px;"><strong>${articleVO.articletitle}</strong></span>
			
					<div class="" style="display: flex;">
						<div class="article-dayofstart" style="text-align:center;">
								<img src="<%=request.getContextPath()%>/front-end/article/images/icon_member.png">
								<c:forEach var="MemVO" items="${list3}">
								<c:if test="${articleVO.memno eq MemVO.memNo}">
									${MemVO.memName}
								</c:if>
							</c:forEach>
						</div>&nbsp;&nbsp;&nbsp;
						
						<div class="article-dayofstart" style="text-align:center;">
								<img src="<%=request.getContextPath()%>/front-end/article/images/icon_calender.png">${articleVO.dayofstart}
						</div>&nbsp;&nbsp;&nbsp;
						
						<div class="article-dayofstart" style="text-align:center;">
								<img src="<%=request.getContextPath()%>/front-end/article/images/icon_views.png">${articleVO.timeofviews}
						</div>&nbsp;&nbsp;&nbsp;
					</div>
							
					<div class="shadow-sm p-3 mb-5 bg-white rounded">
							<c:if test="${articleVO.articlepic==null }"><img src="<%=request.getContextPath()%>/front-end/article/images/noPic.gif" style="border:1px;width:675px;height:450px;"></c:if>
							<c:if test="${articleVO.articlepic!=null }"><img src="<%=request.getContextPath()%>/article/ArticlePic?articleno=${articleVO.articleno}" style="border:1px;width:675px;height:450px;"></c:if>
					</div>	
					<div class="get-one-article">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/articleDetails/ArticleDetails.do" style="margin-bottom: 0px;">
			     		<input type="submit" value="take_a_look" style="display: none;">
			     		<input type="hidden" name="tripno"  value="${articleVO.tripno}">
			     		<input type="hidden" name="articleno"  value="${articleVO.articleno}">
			     		<input type="hidden" name="action"	value="getOne_For_Display"></FORM>
					</div>
					</label>
<%-- 				</c:if>	 --%>
<!-- 					<div class="social-media-share" style="display: flex; flex-direction: row; flex-wrap: nowrap; justify-content:center;"> -->
<!-- 						<div class=""> -->
<%-- 							<a href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-facebook-48.png"></a> --%>
<!-- 						</div>	 -->
<!-- 						<div class="">	 -->
<%-- 							<a href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-google-48.png"></a> --%>
<!-- 						</div>	 -->
<!-- 						<div class=""> -->
<%-- 							<a href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-instagram-48.png"></a> --%>
<!-- 						</div> -->
<!-- 						<div class="">	 -->
<%-- 							<a href="#"><img src="<%=request.getContextPath()%>/front-end/article/images/icon-twitter-48.png"></a> --%>
<!-- 						</div> -->
<!-- 					</div> -->
					<hr style="border: 0; height: 1px; background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));">
				</c:forEach>
				<%@ include file="page2.file" %>
			</div>
		</div>
			
		<div class="col-md-2" style="text-align:left;">
			<div class="article-share" style="margin-top:15px;">
				<div class="trip-tips-filed" style="border:2px outset #DCDCDC; border-radius:10px;">
					<span style="font-size:30px;"><strong>Tips</strong></span>
						<br><br>
						<span style="font-size:20px;" id="triplittletip"><b>D</b>estination	:	目的地</span><br>
						<span style="font-size:20px;" id="triplittletip"><b>D</b>ays       	: 	去多久</span><br>
						<span style="font-size:20px;" id="triplittletip"><b>D</b>o 			: 想去做什麼?</span><br><br>
				</div>
				<div class="widget text" style="margin-top:60px; border:1px outset #DCDCDC;border-top:none; border-radius:10px;">
					<div><span style="font-size:25px; disable: block;"><strong>&nbsp;亞洲&nbsp;&nbsp;Asia</strong></span></div><br><br>
						&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/front-end/city/TaiwanCityIndex.jsp?countryno=${countryVO.countryNo}" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;臺灣&nbsp;Taiwan&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;中國&nbsp;China&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/front-end/city/JapanCityIndex.jsp?countryno=${countryVO.countryNo}" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;日本&nbsp;Japan(18)&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;泰國&nbsp;Thailand&nbsp;</a><br><br>	
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;韓國&nbsp;Korea&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;香港&nbsp;Hongkong&nbsp;</a><br><br><br>
				</div>
				<div class="widget text" style="margin-top:60px; border:1px outset #DCDCDC;border-top:none; border-radius:10px;">
					<span style="font-size:25px;text-align:center;"><strong>&nbsp;歐洲&nbsp;&nbsp;Europe</strong></span><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;土耳其&nbsp;Turkey&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;捷克&nbsp;czech republic&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;英國&nbsp;England&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;法國&nbsp;France&nbsp;</a><br><br>	
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;義大利&nbsp;Italy&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;西班牙&nbsp;Spain&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;德國&nbsp;Germany&nbsp;</a><br><br>
				</div>
				<div class="widget text" style="margin-top:60px; border:1px outset #DCDCDC; border-top:none;border-radius:10px;">
					<span style="font-size:25px;text-align:center;"><strong>&nbsp;北美&nbsp;&nbsp;North America</strong></span><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;美國&nbsp;American&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;加拿大&nbsp;Canada&nbsp;</a><br><br>
				</div>
				<div class="widget text" style="margin-top:60px; border:1px outset #DCDCDC; border-radius:10px;">
					<span style="font-size:25px;text-align:center;"><strong>&nbsp;南美&nbsp;&nbsp;South America</strong></span><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;祕魯&nbsp;Perú&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;阿根廷&nbsp;Argentina&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;巴西&nbsp;Brazil&nbsp;</a><br><br>
						&nbsp;&nbsp;<a href="#" class="btn btn-outline-dark btn-sm" style="font-size:20px;" id="triplittletip">&nbsp;玻利維亞&nbsp;Bolivia&nbsp;</a><br><br>	
				</div>
			</div>
		</div>
		<div class="col-md-2" style="text-align:left;">
			
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">

		<footer class="page-footer font-small unique-color-dark pt-4">
			<!-- Footer Elements -->
			<div class="container">
				<!-- Call to action -->
			    <ul class="list-unstyled list-inline text-center py-2">
			      	<li class="list-inline-item">
			        <h5 class="mb-1">Register for free</h5>
			      	</li>
			      	<li class="list-inline-item">
			        <a href="#!" class="btn btn-outline-white btn-rounded">Sign up!</a>
			      	</li>
			    </ul>
			    <!-- Call to action -->
			</div>
			<!-- Footer Elements -->
			<!-- Copyright -->
			<div class="footer-copyright text-center py-3">© 2018 Copyright:
			  <a href="#"> Yu-Gi-Oh!.com</a>
			</div>
			<!-- Copyright -->
		</footer>
		</div>
	</div>
	
</div>	
</body>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</html>