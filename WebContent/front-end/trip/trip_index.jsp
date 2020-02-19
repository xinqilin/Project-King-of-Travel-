<%@page import="com.trip.model.TripVO"%>
<%@page import="com.city.model.CityVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
	Object account = session.getAttribute("account"); // 從 session內取出 (key) account的值
// 	if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
// 		session.setAttribute("location", request.getRequestURI()); 
// 	//*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
// 		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
// 	//*工作2 : 請該user去登入網頁(login.html) , 進行登入
// 		return;
// 	}
	List<TripVO> keyword=(List<TripVO>)request.getAttribute("keyword");
	String alert=(String)request.getAttribute("welcome");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="icon" href="/DA101G3/kingoftravel.ico" type="image/x-icon" />
<!-- <meta charset="UTF-8"> -->
<title>遊記王-行程</title>
<style type="text/css">
.modal-content {
	border-radius: 15px;
	background: #00BBFF;
}

.modal-backdrop {
	background-color: #000000;
}

.modal-backdrop.show {
	opacity: .5;
}

.modal-content {
	text-align: center;
	font-weight: bold;
}

.modal-header {
	align-items: center;
}

label {
	font-size: 20px;
}

a:hover, .current_page_item a {
	text-decoration: none;
	background: pink;
	border-radius: 10px;
	color: #FFFFFF;
	font-family: "微軟正黑體";
	cursor: pointer;
}
h3, h4 ,h5,h6{
font-family:"微軟正黑體";
font-weight: bold;
text-shadow:3px 3px 3px #cccccc;

}
.card:hover{
background: #EEFFBB;
border-radius: 10px;
border:3px #00FF00 dashed;
cursor: pointer;
}

.card-body p{
	font-family:"微軟正黑體";
	font-style: italic;
	font-weight: bold;
	
}

</style>

<!-- 按鈕 -->
<style>
input {
	border: 0;
	background-color: #003C9D;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}

 input:hover { 
	color: #003C9D; 
 	background-color: #fff; 
 	border: 2px #003C9D solid; 
 } 
#see{
	margin-left:18%;
}
#see input{
	border: 0;
	background-color: green;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
	margin-right:10px;
	
}
#see input:hover{
	color: #00AA55;
	background-color: #fff;
	border: 2px green solid;
	cursor: pointer;
}

#nanana{
	width:100%;
	height:80px;
	background-color:rgba(100,100,100,0.8);
	position:fixed;
	top:0px;
	left:0px;
	text-align:right;
	z-index: 999;
}

</style>
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>



</head>
<body style="background-color:white;">

	<jsp:useBean id="tripDetailSvc" scope="page"
		class="com.tripDetails.model.TripDetailsService" />

<%-- 	<%@ include file="/navbarNoCSS.file" %> --%>
<%@ include file="/nav-f"%>



	<%if(alert!=null){ %>
		<script type="text/javascript">
	window.onload(alertify.error('檢舉行程       \n   <%=alert%>    \n 管理員將為您審核!!!'));
 		</script>
	<% }%>


	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="https://picsum.photos/1200/300?random=1"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=2"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=3"
					class="d-block w-100">
			</div>
				<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=4"
					class="d-block w-100">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>


	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">大波霸組員</li>
			<li class="breadcrumb-item"><a
				href="https://www.facebook.com/ckk.wayne">緯明不要</a></li>
			<li class="breadcrumb-item"><a href="https://www.facebook.com/ej0936229943">瑋齊不行</a></li>
			<li class="breadcrumb-item"><a href="#">奕伸大麻</a></li>
		</ol>
	</nav>
	
<nav class="row justify-content-between">
<div class="spinner-border text-primary " role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-secondary" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-success" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-danger" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-warning" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-info" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-primary" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-secondary" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-success" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-danger" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-grow text-warning" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-info"  role="status">
  <span class="sr-only">Loading...</span>
</div>
</nav>
<br>
<br>

	<div style="margin-left:65%;">
	 <img src="<%=request.getContextPath()%>/front-end/trip/pic/icon3.png" style="width:40px;height:40px;"><label><a href='#aaa'><input type="button" value="觀看行程推薦"></a></label>
<!-- 	&nbsp&nbsp&nbsp -->
	 <img src="<%=request.getContextPath()%>/front-end/trip/pic/icon4.png" style="width:35px;height:35px;"><label><a href='<%=request.getContextPath() %>/front-end/trip/listAllTrip.jsp'><input type="button"value="觀看我的行程列表"></a></label> 
<!-- 		&nbsp&nbsp&nbsp  -->
 <img src="<%=request.getContextPath()%>/front-end/trip/pic/write.png" style="width:40px;height:40px;"><label><a href='<%=request.getContextPath() %>/front-end/trip/addTrip.jsp'><input type="button" value="我要新建行程"></a></label>
	</div>
	<hr color="red" align="right" width="36%" size="30px">
	<br>
	<br>




	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



	<jsp:useBean id="tripSvc" scope="page"
		class="com.trip.model.TripService" />


	
<%if (request.getAttribute("tripVO")!=null){%>
<jsp:include page="listOneTrip.jsp" />
<%} %>
<br>

<%!int count=86411,count2=2; %>
<h4 style="margin-left:18%;"><a name="aaa">行程資料庫</a></h4><h5 style="margin-left:18%;">共  <%=count++ %>筆資料  </h5><br>
	<div id="see" class="row">
	
	<input type="button" value="遊記王推薦" id="all" class="p-3 mb-3 bg-info text-white"></input>
	<input type="button" value="最新建立" id="date" class="p-3 mb-3 bg-primary text-white"></input> 
	<input type="button" value="最多瀏覽" id="view" class="p-3 mb-2 bg-success text-white"></input>
	<input type="button" value="依天數排序" id="days" class="p-3 mb-2 bg-danger text-white"></input>
	<input type="button" value="地區" id="city" class="p-3 mb-2 bg-warning text-dark"></input>
	<div>
		<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/trip/Trip.do">
			 <img src="<%=request.getContextPath()%>/front-end/trip/pic/icon2.png" style="width:50px;height:50px;"><b >查詢行程:</b> <select size="1" name="tripno" style="border-radius: 10px;">
				<c:forEach var="tripVO" items="${tripSvc.all}">
					<option value="${tripVO.tripno}">${tripVO.tripname}
				</c:forEach>
			</select>
			 <input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</FORM>
	<hr color="red" align="center" width="100%" size="5px">
	</div>
	&nbsp&nbsp
	<div>
		<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/trip/Trip.do">
			 <img src="<%=request.getContextPath()%>/front-end/trip/pic/icon2.png" style="width:50px;height:50px;"><b >關鍵字查詢:</b> 
			<input type="text" name="gogo" value="" style="background-color: white;border:1px green solid;color:black;">
			 <input type="hidden" name="action" value="gogoJapan">
			<input type="submit" value="查查">
		</FORM>
	<hr color="red" align="center" width="100%" size="5px">
	</div>
	
	
	</div>
	<hr color="pink" align="center" width="65%" size="5px">
	<br>
	<br>
	

	<div class="container" id="container1">
		<div class="row">
	<c:forEach var="tripVO" items="${tripSvc.all}">
	<c:if test="${tripVO.tripstatus!=2}">
			<div class="col-12 col-md-3">
				<div class="card border-info">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">行程天數:<c:out value="${tripVO.tripdays}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">此篇觀看次數:<c:out value="${tripVO.timeofviews}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
<!-- 						<a href="#" class="btn btn-info">觀看詳情</a> -->
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
		<!-- 111 for test reconding point	 -->
		 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 <input type="hidden" value="${tripVO.tripno}" name="tripno">
			 <input type="hidden" name="action" value="getOne">
			<input type="submit" value="觀看詳情" class="btn btn-info">
			</FORM>
					
					</div>
					</label>
				</div>
		</div>
		</c:if>
		</c:forEach>	
<!-- 		all Card end mark -->
		</div>
	</div>
	
	
	

		<div class="container" id="container2">
		<div class="row">
	<c:forEach var="tripVO" items="${tripSvc.orderByViews}">
	<c:if test="${tripVO.tripstatus!=2}">
			<div class="col-12 col-md-3">
				<div class="card border-success">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;color:red;">此篇觀看次數:<c:out value="${tripVO.timeofviews}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">行程天數:<c:out value="${tripVO.tripdays}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
						 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 			<input type="hidden" value="${tripVO.tripno}" name="tripno">
			 			<input type="hidden" name="action" value="getOne">
						<input type="submit" value="觀看詳情" class="btn btn-success">
						</FORM>


					</div>
					</label>
				</div>
				
		</div>
		</c:if>
		</c:forEach>	
		
		</div>
	</div>
		
	
	
	
	<div class="container" id="container3">
		<div class="row">
	<c:forEach var="tripVO" items="${tripSvc.orderByDate}">
	<c:if test="${tripVO.tripstatus!=2}">
			<div class="col-12 col-md-3">
				<div class="card border-primary mb-3">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;color:red;">此篇建立日期:<c:out value="${tripVO.tripestdate}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">行程天數:<c:out value="${tripVO.tripdays}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
						 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 			<input type="hidden" value="${tripVO.tripno}" name="tripno">
			 			<input type="hidden" name="action" value="getOne">
						<input type="submit" value="觀看詳情" class="btn btn-primary">
						</FORM>
					</div>
					</label>
				</div>
				
		</div>
		</c:if>
		</c:forEach>	
		
		</div>
	</div>
	
	
		<div class="container" id="container4">
		<div class="row">
	<c:forEach var="tripVO" items="${tripSvc.orderByDays}">
	<c:if test="${tripVO.tripstatus!=2}">
			<div class="col-12 col-md-3">
				<div class="card border-danger">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;color:red;">行程天數:<c:out value="${tripVO.tripdays}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">此篇建立日期:<c:out value="${tripVO.tripestdate}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
						 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 			<input type="hidden" value="${tripVO.tripno}" name="tripno">
			 			<input type="hidden" name="action" value="getOne">
						<input type="submit" value="觀看詳情" class="btn btn-danger">
						</FORM>
					</div>
					</label>
				</div>
				
		</div>
		</c:if>
		</c:forEach>	
		
		</div>
	</div>
	
	
	
	
	<div class="container" id="container5">
		<div class="row">
	<c:forEach var="tripVO" items="${tripSvc.orderByCity}">
	<c:if test="${tripVO.tripstatus!=2}">
	<jsp:useBean id="citySvc" scope="request" class="com.city.model.CityService" />

	
			<div class="col-12 col-md-3">
				<div class="card border-warning">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
<c:forEach var="cityVO" items="${citySvc.all}">
<c:if test="${tripVO.cityno.equals(cityVO.cityNo)}">
<p class="card-text" style="text-decoration:underline; text-decoration-color: red;color:red;">
地區名稱:<c:out value="${cityVO.cityName}"/></p>
</c:if>
	</c:forEach>
						
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">此篇建立日期:<c:out value="${tripVO.tripestdate}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
						 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 			<input type="hidden" value="${tripVO.tripno}" name="tripno">
			 			<input type="hidden" name="action" value="getOne">
						<input type="submit" value="觀看詳情" class="btn btn-warning">
						</FORM>
					</div>
					</label>
				</div>
				
		</div>
		</c:if>
		</c:forEach>	
		
		</div>
	</div>
	
	
	
	
		<div class="container" id="container6">
		<div class="row">
	<c:forEach var="tripVO" items="<%=keyword %>">
<c:if test="${tripVO.tripstatus!=2}">
	
			<div class="col-12 col-md-3">
				<div class="card border-primary">
				<label>
				<%int i=(int)((Math.random()*20)+1);%>
					<c:if test="${tripVO.trippic==null}"><img src="https://picsum.photos/350/200?random=<%=i%>"  title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:147px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>

						
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">啟程日期:<c:out value="${tripVO.tripstartday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">回程日期:<c:out value="${tripVO.tripendday}"/></p>
						<p class="card-text" style="text-decoration:underline; text-decoration-color: red;">此篇建立日期:<c:out value="${tripVO.tripestdate}"/></p>
						<c:if test="${tripVO.bethebuyer==0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></p></c:if>
						<c:if test="${tripVO.bethebuyer!=0}"><p class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></p></c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
						 <img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
			 			<input type="hidden" value="${tripVO.tripno}" name="tripno">
			 			<input type="hidden" name="action" value="getOne">
						<input type="submit" value="觀看詳情" class="btn btn-warning">
						</FORM>
					</div>
					</label>
				</div>
				
		</div>
		</c:if>
		</c:forEach>	
		
		</div>
	</div>
	
	
	
	

	
	
	
	
	
	
	
	
	<div class="container">
		<div class="row">
			<div class="col-12 ">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>


		
  <script>
<%if(keyword!=null){%>
$(document).ready(function(){
$("#container1").hide();
$("#container2").hide();
$("#container3").hide();
$("#container4").hide();
$("#container5").hide();
});
<%}%>
<%if(keyword==null){%>
$(document).ready(function(){
	 $("#container1").show();
	 $("#container2").hide();
	 $("#container3").hide();
	 $("#container4").hide();
	 $("#container5").hide();
});
<%}%>
$("#all").click(function(){
	 $("#container1").show();
	 $("#container2").hide();
	 $("#container3").hide();
	 $("#container4").hide();
	 $("#container5").hide();
	 $("#container6").hide();
	});
  
  $("#view").click(function(){
	 $("#container2").show();
	 $("#container1").hide();
	 $("#container3").hide();
	 $("#container4").hide();
	 $("#container5").hide();
	 $("#container6").hide();
	});
  $("#date").click(function(){
		 $("#container3").show();
		 $("#container1").hide();
		 $("#container2").hide();
		 $("#container4").hide();
		 $("#container5").hide();
		 $("#container6").hide();
		});
  $("#days").click(function(){
		 $("#container4").show();
		 $("#container1").hide();
		 $("#container3").hide();
		 $("#container2").hide();
		 $("#container5").hide();
		 $("#container6").hide();
		});
  $("#city").click(function(){
		 $("#container5").show();
		 $("#container1").hide();
		 $("#container3").hide();
		 $("#container2").hide();
		 $("#container4").hide();
		 $("#container6").hide();
		});

  </script>

</body>
</html>