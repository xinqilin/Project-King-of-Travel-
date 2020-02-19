<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>

<%
    TripService tripSvc = new TripService();
    List<TripVO> list = tripSvc.getAll();
    pageContext.setAttribute("list",list);
    
    Object account = session.getAttribute("account");
//     Object memno =session.getAttribute("memno");
    String memno=(String)session.getAttribute("memno");
    if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); 
	//*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
	//*工作2 : 請該user去登入網頁(login.html) , 進行登入
		return;
	}
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
<style>
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
.card:hover{
background:#CCEEFF;
border-radius: 10px;
border:3px #5500DD dashed;
}

.card-body b{
font-size:20px;
	font-family:"微軟正黑體";
	font-style: italic;
	font-weight: bold;
	
}

h3, h4 ,h5,h6{
font-family:"微軟正黑體";
font-weight: bold;
text-shadow:3px 3px 3px #cccccc;
}
a:hover, .current_page_item a {
	text-decoration: none;
	background: pink;
	border-radius: 10px;
	color: #FFFFFF;
	font-family: "微軟正黑體";
}

input {
border: 0;
background-color: #FF8800;
color: #fff;
border-radius: 10px;
cursor: pointer;
font-family: "微軟正黑體";
}

 input:hover { 
	color: #FF8800; 
 	background-color: #fff; 
 	border: 2px #FF8800 solid; 
 } 

  table {
	background-color:pink;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid orange;
  }
th{
	padding: 5px;
	text-align: center;
	border-bottom:2px dashed red;
}
td {
	padding: 5px;
	text-align: center;
}
  
  
 .return {
	border: 0;
	background-color: #003C9D;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}

 .return:hover { 
	color: #003C9D; 
 	background-color: #fff; 
 	border: 2px #003C9D solid; 
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

#fixed{
	width:100%;
	height:80px;
	position:fixed;
	top:60%;
	left:2%;
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
<body>


<%-- <%@ include file="/navbarNoCSS.file" %> --%>
<%@ include file="/nav-f"%>

<%String welcome=(String)request.getAttribute("welcome");
if(welcome!=null){%>
<script type="text/javascript">
window.onload(alertify.success('複製行程   \n   <%=welcome%>   \n 成功!!!'));
</script>
<%}%>


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
			<li class="breadcrumb-item"><a href="#">瑋齊不行</a></li>
			<li class="breadcrumb-item"><a href="#">奕伸大麻</a></li>
		</ol>
	</nav>
	
	<div align="left" id="fixed">
		<label><a href='<%=request.getContextPath() %>/index.jsp'><input type="button" value="回首頁" class="return"></a></label>
		<label><a href='<%=request.getContextPath() %>/front-end/trip/trip_index.jsp'><input type="button"value="回行程主頁" class="return"></a></label> &nbsp&nbsp&nbsp 
		<hr color="red" align="left" width="10%" size="5px">
	</div>
	<hr color="pink" align="center" width="14%" size="5px">
	

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>








<div class="container">
		<div class="row">
	<%int i=0; %>
	<c:forEach var="tripVO" items="${list}">
		<c:if test="${tripVO.memno.equals(memno)}">
		<%i++;%>
			<div class="col-12 col-md-3">
				<div class="card border-info">
				<label>
					<c:if test="${tripVO.trippic==null}"><img src="<%=request.getContextPath()%>/images/noPic.png"  title="${tripVO.tripname}" style="border:1px blue;height:200px;padding-right:10%;padding-left:10%;" class="card-img-top"></c:if>
					<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue;width:100%;height:200px;padding-right:10%;padding-left:10%;" title="${tripVO.tripname}"  class="card-img-top"></c:if>
					<div class="card-body">
						<h5 class="card-title">主題 :  <c:out value="${tripVO.tripname}"/></h5>
						<hr color="pink" align="center" width="100%" size="5px">
						<h6>大綱:</h6>
						<br>
						
						<jsp:useBean id="citySvc" scope="page" class="com.city.model.CityService" />
						<b class="card-text" style="text-decoration:underline; text-decoration-color: red;">
						<c:forEach var="cityVO" items="${citySvc.all}">
						<c:if test="${tripVO.cityno.equals(cityVO.cityNo)}">
						地區 :<c:out value="${cityVO.cityName}"/>
						</c:if>
						</c:forEach>
						</b><br>
						
						
						<b class="card-text" style="text-decoration:underline; text-decoration-color: red;">日期:<c:out value="${tripVO.tripstartday}~${tripVO.tripendday}"/></b><br>
						<b class="card-text" style="text-decoration:underline; text-decoration-color: red;">行程天數:<c:out value="${tripVO.tripdays}"/></b><br>
						<b class="card-text" style="text-decoration:underline; text-decoration-color: red;">創建日期:<c:out value="${tripVO.tripestdate}"/></b><br>
						<b class="card-text" style="text-decoration:underline; text-decoration-color: red;">此篇被觀看次數:<c:out value="${tripVO.timeofviews}"/></b><br>
						
				
					
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==0}"><c:out value="單獨旅行"/><br></c:if></b>
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==1}"><c:out value="情侶出遊"/><br></c:if></b>
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==2}"><c:out value="家庭親子"/><br></c:if></b>
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==3}"><c:out value="朋友出遊"/><br></c:if></b>
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==4}"><c:out value="商務旅遊"/><br></c:if></b>
			<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.kindoftrip==5}"><c:out value="其它"/><br></c:if></b>
					
						
		<c:if test="${tripVO.bethebuyer==0}"><b class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="是"/></b><br></c:if>
		<c:if test="${tripVO.bethebuyer!=0}"><b class="card-text" style="text-decoration:underline; text-decoration-color: red;">是否成為代購者:<c:out value="否"/></b><br></c:if>
				<b>狀態:</b>
				<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.tripstatus==0}"><c:out value="未公開等待審核中"/><br></c:if></b>
				<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.tripstatus==1}"><c:out value="公開行程"/><br></c:if></b>
				<b class="card-text" style="text-decoration:underline; text-decoration-color: red;"><c:if test="${tripVO.tripstatus==2}"><c:out value="Oops! 被凍結了"/><br></c:if></b>
<br><br>
				 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/trip/Trip.do" style="margin-bottom: 0px;">
			     <c:if test="${tripVO.tripstatus!=2}"><br><center><img src="<%=request.getContextPath()%>/front-end/trip/pic/rewrite.png" style="width:40px;height:40px;">
			     <input type="submit" value="我想修改" width="400px" height="400px"></center></c:if>
			     <c:if test="${tripVO.tripstatus==2}"><b><font style="text-decoration:underline; color:red; font-size:20px;">抱歉!你的行程已被凍結<br>無法更改</font></b></c:if>
			     <input type="hidden" name="tripno"  value="${tripVO.tripno}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
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
<c:if test="<%=i==0%>"><center><font style="color:red;font-size:72px;font-weight:bold;">抱歉!!!你還沒有創建任何行程</font><br><br>
<a href="<%=request.getContextPath() %>/front-end/trip/addTrip.jsp"><font style="font-size:48px;font-weight:bold;text-decoration:underline; text-decoration-color: red;"">點我去創建行程 GO!</font></a>
</center></c:if>
	<%i=0;%>







<hr color="pink" align="center" width="65%" size="5px">
<br>
<br>
<br>
<br>
<br>




	
<br>
<br>
<br>
<br>
<br>


	



</body>
</html>