<%@page import="com.tripDetails.model.TripDetailsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.tripDetails.model.*"%>
<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO"); 
	SpotListVO spotVO=(SpotListVO)request.getAttribute("spotVO");	
	String cityno=(String)request.getAttribute("cityno");
	Integer whichday=(Integer)request.getAttribute("whichday");
	// 	System.out.println(tripVO);
	
	if(spotVO==null){
		SpotListService spot=new SpotListService();
		spotVO=spot.getOneSpot("SPT000001");
	}
	TripDetailsService tripDetailsSvc = new TripDetailsService();
	TripDetailsVO tripDetailsVO=tripDetailsSvc.getOneTripDetails(tripVO.getTripno(), spotVO.getSpotNo());
	List<TripDetailsVO> list = tripDetailsSvc.getfindByTripno(tripVO.getTripno());
	pageContext.setAttribute("list", list);
	SpotListService spot=new SpotListService();
	List<SpotListVO> list2=spot.getAllNoPic();
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("spotno1", spotVO.getSpotNo());
	
	//     Object account = session.getAttribute("account");
	//     String memno=(String)session.getAttribute("memno");
	//     if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
	// 		session.setAttribute("location", request.getRequestURI()); 
	// 		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
	// 		return;
	// 	}
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
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<script
  src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"
  integrity="sha256-0YPKAwZP7Mp3ALMRVB2i8GXeEndvCq3eSl/WsAl1Ryk="
  crossorigin="anonymous"></script>

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
h1,h2,h3, h4 ,h5,h6{
font-family:"微軟正黑體";
font-weight: bold;
text-shadow:3px 3px 3px #cccccc;
 display:block; line-height:1.5em; overflow:visible; text-shadow:#f3f3f3 1px 1px 0px, #b2b2b2 1px 2px 0;
}
a:hover, .current_page_item a {
	text-decoration: none;
	background: pink;
	border-radius: 10px;
	color: #FFFFFF;
	font-family: "微軟正黑體";
	cursor: grab;
}

input {
	border: 0;
	background-color: dodgerblue;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
	text-shadow:3px 3px 3px #cccccc;
}

input:hover {
	color: red;
	background-color: #fff;
	border: 2px dodgerblue solid;
	text-shadow:3px 3px 3px #cccccc;
	cursor: grab;
}
.mappp{
	border: 0;
	background-color: #00DD77;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}
.mappp:hover {
	color: red;
	background-color: #fff;
	border: 2px #00DD77 solid;
	cursor: grab;
}
.text{
	
	border: 0;
 	background-color: white; 
 	color: black; 
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
	border: 2px #0088A8 solid;

}
.text:hover{
	color: white;
	background-color: red;
	border: 2px #0088A8 solid;
	cursor: grab;
}
.text2{
	border: 0;
 	background-color: white; 
 	color: black; 
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
	border: 1px #0088A8 solid;

}
.text2:hover{
	color: black;
	background-color: #FFFFBB;
	border: 2px #0088A8 solid;
	cursor: grab;
}
 table {
	margin-top: 5px;
	margin-bottom: 5px;
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
#aa,#aaa{
　　border:1px solid #cccccc;
　　width:300px;
　　height:100px;
　　background-color:#FFFFFF;
　　padding:5px;
　　overflow:auto;

}
.onme{
	border: 0;
	background-color: #FF8888;
	color: white;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}
.onme:hover {
	color:#FF8888;
	background-color: white;
	border: 2px #FF8888 solid;
}
#leftdiv{
 	width:100%; 
 	height:80px; 
/*   	background-color:#99BBFF;   */
  	position:fixed;  
 	top:40%; 
 	left:0%; 
 	text-align:center; 
 	z-index: 999; 
 	border-radius:20px; 
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

.arrow:hover{
	color: red;
	background-color: #fff;
	border: 2px #FFAA33 solid;
	text-shadow:3px 3px 3px #cccccc;
	cursor: grab;
}
</style>

<%String welcome=(String)request.getAttribute("welcome");
if(welcome!=null){%>
<script type="text/javascript">
$(document).ready(function(){window.location.href='#redline'});
</script>
<%}%>

</head>
<body style="background-color:white;">

<%-- 	<%@ include file="/navbarNoCSS.file" %> --%>
<%@ include file="/nav-f1"%>
<br><br><br><br>
	<div class="row" >
			<div style="margin-left:8%; width:40%; height: 25%;background: linear-gradient(135deg, #FFDD55, #FFFFBB 80%, #EE7700);border-radius:30px;" >
			<h1 style="text-decoration:underline; text-decoration-color: red;">行程主題 : <%=tripVO.getTripname()%></h1>			
			<center><h3>更新日期 : <%=tripVO.getTripestdate()%></h3>
			<h3>瀏覽次數 :<%=tripVO.getTimeofviews()%></h3>
			<h4>是否成為代購賣家 :<c:if test="${tripVO.bethebuyer==0}"><c:out value="否"/></c:if>
			<c:if test="${tripVO.bethebuyer!=0}"><c:out value="是"/></c:if></h4>
			<h5>${tripVO.tripstartday} ~ ${tripVO.tripendday}</h5>
			<h5>共${tripVO.tripdays} 天</h5></center>
			<hr size="10" color="pink"/>
			</div>
			<div style="width:45%; height: 20%;margin-left:1%;" align="left" >
			<%if (request.getAttribute("spotVO")==null){%>
<!-- 			<div style="border:1px dotted red;border-radius: 20px;"> -->
			<h1 align="center" style="display: inline;">在此預覽景點</h1><h4 align="center" style="display: inline;">&nbsp&nbsp&nbsp&nbsp例:台北101</h4>
			<iframe width='100%' height='252px' frameborder='0' scrolling='auto' 
						marginheight='0' marginwidth='0' style="border:blue dotted 1px;border-radius: 20px;"
						src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=台北市信義區信義路五段7號&z=16&output=embed&t='>
						</iframe>
<!-- 			</div> -->
			<%} %>
				<%if (request.getAttribute("spotVO")!=null){%>
<!-- 				<div style="border:1px red dotted;border-radius: 30px;"> -->
						<jsp:include page="listOneTripDetails.jsp" />
						<iframe width='100%' height='200px' frameborder='0' scrolling='auto' 
						marginheight='0' marginwidth='0' style="border:blue dotted 1px;border-radius: 20px;"
						src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<%=spotVO.getLocation()%>&z=16&output=embed&t='>
						</iframe>
<!-- 				</div> -->
				<%} %>	
			</div>
			
</div>

<a name="redline"></a>
<br><br>

<hr size="10" color="black"/>

<br>





	<div class="row" style="margin-left:4%;">
		<div class="col-1" id="leftdiv">
		<br>
			<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/back.png" style="width:40px;height:40px;"><br>
			<input type="button" class="onme" value="跳回到頂層" onclick="window.location.href='#'"></input>
			<br><br>
			
			<% int j=1,p=0;
			 for(TripDetailsVO a : list){
			 	if(a.getTripdayorder()==j) {
			%>
			<input type="button" class="onme" value="跳到第<%=a.getTripdayorder()%>天" onclick="window.location.href='#go<%=a.getTripdayorder()%>'"></input>
			<br><br>
			<% j++;}%>
			<%}%>
			<%for(p=j;p<=tripVO.getTripdays();p++){ %>
			<input type="button" class="onme" value="跳到第<%=p %>天" style="background-color: #DCDCDC" disabled="disabled"></input>
			<br><br>
			<%}j=1; %>
			<input type="button" class="onme" value="跳我看地圖" onclick="window.location.href='#mapline'"></input><br><br>
			<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/forward2.png" style="width:30px;height:30px;">
			</div>
			
			
			
<div class="col-7" id="aa" >
<div align="center" style="overflow-x:hidden;overflow-y:auto; height:850px;" >

	<%if(list.size()==0){ %>
	<h1 style="text-decoration:underline; text-decoration-color:#FF0088;color:orange;">尚未有行程</h1>
	<%} %><br><br>

<table style="width:90%;">
	<%
 	int i=1,kk=0,kkk=list.size(),qq=0;
 	for(TripDetailsVO a : list){
	
 	%>
	
	<tr>
		<td style="border-right:2px red dashed">
		<h4>

		<%if(a.getTripdayorder()==i) {%>
				<a  name="go<%=a.getTripdayorder()%>"></a>
		第<%=a.getTripdayorder()%>天
		<% i++;}%></h4>
		</td>
		<td style="border-bottom: 2px solid #FFDAB9;">
		<br><br><h6 style="text-decoration:underline; text-decoration-color:#FF0088;">
		<%
 		for(SpotListVO s : list2){
			if(a.getSpotno().equals(s.getSpotNo())){%>
				<%=s.getSpotName()%>
			<%}%>
		<%} %>
		</h6>
		<br><br>
		</td>
		<td style="border-bottom: 2px solid #FFDAB9;">
		
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">

<%for(SpotListVO s : list2){
	if(a.getSpotno().equals(s.getSpotNo())){%>
		 <input type="hidden" name="spotNo" value="<%=s.getSpotNo()%>">
	<%}%>
<%} %>
<div style="background-color: white; display: inline-flex;border: 1px solid #99BBFF;color: #555; border-radius: 10px;">
<input type="number" name="time11" min="0" max="23" 
value="<%if(a.getTimeofarrive().getHours()<10){%>0<%=a.getTimeofarrive().getHours() %><%} %><%else{%><%=a.getTimeofarrive().getHours() %><%} %>" 
style=" border: none;background-color:white;text-align: center;color: #AAAAAA;" class="timee" required id="qq<%=++qq%>">:
<input type="number" name="time22" min="0" max="59" step="30" 
value="<%if(a.getTimeofarrive().getMinutes()<10){%>0<%=a.getTimeofarrive().getMinutes() %><%} %><%else{%><%=a.getTimeofarrive().getMinutes() %><%} %>"  
style=" border: none;background-color:white;text-align: center;color: #AAAAAA;" class="timee" required id="qq<%=++qq%>"></div>~
<div style="background-color: white; display: inline-flex;border: 1px solid #99BBFF;color: #555;border-radius: 10px;">
<input type="number" name="time33" min="0" max="23" 
value="<%if(a.getTimeofleave().getHours()<10){%>0<%=a.getTimeofleave().getHours() %><%} %><%else{%><%=a.getTimeofleave().getHours() %><%} %>" 
style=" border: none;background-color:white;text-align: center;color: #AAAAAA;" class="timee" required id="qq<%=++qq%>">:
<input type="number" name="time44" min="0" max="59" step="30" 
value="<%if(a.getTimeofleave().getMinutes()<10){%>0<%=a.getTimeofleave().getMinutes() %><%} %><%else{%><%=a.getTimeofleave().getMinutes() %><%} %>"  
style=" border: none;background-color:white;text-align: center;color: #AAAAAA;" class="timee" required id="qq<%=++qq%>"></div>
 <input type="hidden" name="cityno" value="${cityno}">
<input type="hidden" name="tripVO999" value="<%=a.getTripno()%>">
<input type="hidden" name="action" value="timetime">
<br><br>
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/clock.png" style="width:30px;height:30px;">
<input type="submit"value="更改時間" style="background-color:#99BBFF;">
</FORM>
		</td>
		<td style="border-bottom: 2px solid #FFDAB9;" id="tt1"><br><br><h6>停留:<%=a.getStayhours()%> hr</h6><br><br></td>
		<td style="border-bottom: 2px solid #FFDAB9;"><br><h6>第 <%=a.getTriporderby()%> 個</h6><br></td>
		<td style="border-bottom: 2px solid #FFDAB9;"><br><br><h6>
		<%if(a.getTriptips()==null) {%>
		目前尚無註記
		<% }else{%>
		<%=a.getTriptips() %>
		<%} %>
		</h6><br><br>
		</td>
		<td style="border-bottom: 2px solid #FFDAB9;">
		<%kk++; %>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
		<%
 		for(SpotListVO s : list2){
			if(a.getSpotno().equals(s.getSpotNo())){%>
				 <input type="hidden" name="spotNo" value="<%=s.getSpotNo()%>">
				 <input type="hidden" name="cityno" value="<%=s.getCityNo()%>">
			<%}%>
		<%} %>
		<input type="hidden" name="tripVO999" value="${tripVO.tripno}">
		 <input type="hidden" name="action" value="getOne_spot">
		<input type="submit"value="顯示景點詳情" class="mappp">
	</FORM>
	<br>
	<%if(a.getTripdayorder()!=1 || a.getTriporderby()!=1){ %>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" style="margin:0px; display:inline;">
	 <input type="image" src="<%=request.getContextPath()%>/images/upward.png" style="width:30px;height:30px;background-color: white;" class="arrow">  
	<input type="hidden" name="action" value="up">
	<input type="hidden" name="cityno" value="${cityno}">
	<input type="hidden" name="tripno" value="${tripVO.tripno}">
			<%for(SpotListVO s : list2){
			if(a.getSpotno().equals(s.getSpotNo())){%>
				 <input type="hidden" name="spotNo" value="<%=s.getSpotNo()%>">
				 
			<%}%>
		<%} %>
	</FORM>
	<%} %>
	<%if(kk!=kkk ){ %>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" style="margin:0px; display:inline;">
	<input type="image" src="<%=request.getContextPath()%>/images/download.png" style="width:30px;height:30px;background-color: white;" class="arrow">
	<input type="hidden" name="action" value="down">
	<input type="hidden" name="cityno" value="${cityno}">
	<input type="hidden" name="tripno" value="${tripVO.tripno}">
			<%for(SpotListVO s : list2){
			if(a.getSpotno().equals(s.getSpotNo())){%>
				 <input type="hidden" name="spotNo" value="<%=s.getSpotNo()%>">
			<%}%>
		<%} %>
	</FORM>
	<%} %>
	</td>
	</tr>

	<%}%>

	
</table>
	<%for(int k=1;k<=tripVO.getTripdays();k++){ %>
	<%if(i==k){ %>
	<br><br>
		<h4 style="text-decoration:underline; text-decoration-color:#FF0088;">
		第<b style="color:red;font-size:40px;"><%=i%></b>天是空的，親愛的勇者，快來新建完美的行程
		</h4>
		
	<%i++;}}%>
	<%i=1; %>
	</div>
</div>	
	
	
		<div class="col-4" id="aaa"><br>
<!-- 			新增一個 -->
					
					<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotListService" />
					<jsp:useBean id="citySvc" scope="page" class="com.city.model.CityService" />
						<div align="left">
						
						
						<b>現在查詢景點的縣市是:
						<b style="text-decoration:underline; text-decoration-color: red;color:red;">
						<c:if test="${cityno==null}">尚未選擇任何想找的城市，請選擇!!!</c:if>
						</b>
									<c:forEach var="city" items="${citySvc.all}">
										<c:if test="${city.cityNo.equals(cityno)}">
										<b style="text-decoration:underline; text-decoration-color: red;color:red;">
										<c:out value="${city.cityName}"></c:out></b>
										</c:if>
									</c:forEach></b>
								&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" id="findcity" style="margin:0px;display:inline;">
								<b>我想換縣市:</b> <select size="1" name="cityNo" id="cityno" style="border-radius: 10px;border:1px #0088A8 solid;">
									<c:forEach var="cityVO" items="${citySvc.all}">
									<option value="${cityVO.cityNo}"${cityno.equals(cityVO.cityNo)?'selected':''}>${cityVO.cityName}
									</c:forEach>
								</select>
								<input type="hidden" name="tripVO999" value="${tripVO.tripno}" id="tripno1">
								 <input type="hidden" name="action" value="selectOneCity" id="action1">
								<input type="submit" value="查這個縣市" id="findbbb">
							</FORM>
							<br><br>
							
							<%if(cityno!=null){ %>
							<br>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
								<b><c:forEach var="city" items="${citySvc.all}">
										<c:if test="${city.cityNo.equals(cityno)}">
										<b style="text-decoration:underline; text-decoration-color: red;color:red;">
										<c:out value="${city.cityName}"></c:out></b>
										</c:if>
									</c:forEach>的景點</b>
								
								<select size="1" name="spotNo" id="spotno" style="border-radius: 10px;border:1px #0088A8 solid;">
									<c:forEach var="spotVO" items="${spotSvc.allNoPic}">
										<c:if test="${spotVO.cityNo.equals(cityno)}">
										<option value="${spotVO.spotNo}"${spotno1.equals(spotVO.spotNo)?'selected':''}>${spotVO.spotName}
										</c:if>
									</c:forEach>
									
								</select>
								 <input type="hidden" name="cityno" value="${cityno}">
								<input type="hidden" name="tripVO999" value="${tripVO.tripno}">
								 <input type="hidden" name="action" value="getOne_spot">
								 <%if(whichday!=null){ %>
								 <input type="hidden" name="whichday" value="<%=whichday%>">
								 <%} %>
								<input type="submit" value="看景點">
								 <img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/eye.png" style="width:30px;height:30px;">
							</FORM>
							<%} %>
							
							
		<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/tripDetails/TripDetails.do" style="margin:0px; display:inline;">
			<b >關鍵字查詢:</b> 
			<input type="text" size="10" name="gogogo" value="" style="background-color: white;border:1px blue solid;color:black;">
			 <input type="hidden" name="action" value="gogogoJapan">
			 <input type="hidden" name="cityno" value="<%=cityno%>">
			 <input type="hidden" name="tripno" value="<%=tripVO.getTripno()%>">
			<input type="submit" value="查查">
			 <img src="<%=request.getContextPath()%>/front-end/trip/pic/icon2.png" style="width:50px;height:50px;">
		</FORM>
							
							
							
							
							<br>
							<div style="border:red dotted 1px;width:90%" >
							<div style="margin-left: 4%;"><br>
<div class="accordion" id="accordionExample">
<center><button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#addspot" aria-expanded="false" aria-controls="collapseExample">
   新增景點 點我!
  </button></center>
	<div class="collapse.show" id="addspot" data-parent="#accordionExample">
  <div class="card card-body">	


							<center>
							<h4 style="text-decoration:underline; text-decoration-color: red;color:#FF5511;">
							增加景點到行程 :</h4></center>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
							<b>選擇加入第幾天:</b> 
									<%for(int q=1;q<=tripVO.getTripdays();q++){%>
									&nbsp&nbsp
							<input type="radio" name="tripdayorder" value="<%=q%>" 
							<%
							if(whichday==null){
							if(q==1){ %>checked<%} 
							}
							if(whichday!=null){
							if(whichday==q){%>
								checked
							<%}}%>><b><%=q%></b>
							
									<%} %>
								
							
<!-- 					<br><br><b>抵達時間  </b><input   id="f_date1" type="text" name="timeofarrive" class="text" required style="width:90px;"> -->
<!-- 					~<b>離開時間 </b><input   id="f_date2" type="text" name="timeofleave" class="text" required style="width:90px;"> -->
	<br><br>				
<div style="background-color: white; display: inline-flex;border: 1px #0088A8 solid;color: #555; border-radius: 10px;">
<b>到達時間:<input type="number" id="f_date1" name="time11" value="00" min="0" max="23"  style=" border: none;background-color:white;text-align: center;color: #E63F00;" class="timee" required>:
<input type="number" name="time22" id="f_date3" min="0" max="59" value="00"  style=" border: none;background-color:white;text-align: center;color: #E63F00;" class="timee" required></b></div>~
<div style="background-color: white; display: inline-flex;border: 1px #0088A8 solid;color: #555;border-radius: 10px;">
<b>離開時間:<input type="number" id="f_date2" name="time33" value="00" min="0" max="23"  style=" border: none;background-color:white;text-align: center;color: #E63F00;" class="timee" required>:
<input type="number" name="time44" id="f_date4" value="00" min="0" max="59"  style=" border: none;background-color:white;text-align: center;color: #E63F00;" class="timee" required></b></div>
					
					
					
					
					<br><br><b>停留時間  &nbsp:&nbsp&nbsp</b><input   style="background-color:#DDDDDD;width:40px;"  id="times" readOnly="true" name="stayhours" class="text2" required>
					<br><br><b>當天順序  &nbsp:&nbsp&nbsp</b><input   name="triporderby"  type="number" min="1" max="99" class="text2" required style="width:40px;background-color: white;" value="1">
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>寫些小記  &nbsp:&nbsp&nbsp</b><input   name="triptips" value="" type="text" class="text2" style="background-color: white;">
					<br><br>
					
					<input type="hidden" name="spotno" value="${spotVO.spotNo}">
					<input type="hidden" name="tripno" value="${tripVO.tripno}">
					<input type="hidden" name="cityno" value="${cityno}">
					<input type="hidden" name="action" value="insertDetails">
					<c:if test="${cityno!=null}">
					<img alt="" src="<%=request.getContextPath()%>/images/correct.png" style="width:20px;height:20px;">
					</c:if>
					<c:if test="${cityno==null}">
					<img alt="" src="<%=request.getContextPath()%>/images/warning.png" style="width:20px;height:20px;">
					<b>(城市未選)</b></c:if>
					<c:if test="${spotVO.spotNo!=null}">
					<img alt="" src="<%=request.getContextPath()%>/images/correct.png" style="width:20px;height:20px;">
					
					</c:if> 
					<c:if test="${spotVO.spotNo==null}">
					<img alt="" src="<%=request.getContextPath()%>/images/warning.png" style="width:20px;height:20px;">
					<b>(景點未選)</b></c:if>
					<%int test=1;%>
					<c:if test="${spotVO.spotNo==null}">
					<%test=3; %>
					</c:if>
					<%for(TripDetailsVO s:list){ %>
					<%
					if(spotVO.getSpotNo().equals(s.getSpotno())){
					test=0;
					System.out.println("test:"+test+" "+s.getSpotno());
					break;
					}

					%>
					<%} %>
					
					<%if(test==0) {%>
					<img alt="" src="<%=request.getContextPath()%>/images/warning.png" style="width:20px;height:20px;">
					<b>(這景點已存在此次行程中!)</b><br>
					<%} %>
					<%if(test==1) {%>
					<img alt="" src="<%=request.getContextPath()%>/images/correct.png" style="width:20px;height:20px;">
					(${spotVO.spotName})<%} %>
					<br>
						<%if(spotVO.getSpotNo()==null||cityno==null||test==0 || test==3){ %>
					<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/plus.png" style="width:25px;height:25px;">
					<input type="submit"  disabled="disabled" value="新增這個景點到行程" style="background-color:#AAAAAA;">
							<% }%>
						<%if(spotVO.getSpotNo()!=null && cityno!=null && test==1){ %>
					<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/plus.png" style="width:25px;height:25px;">
					<input type="submit" value="新增這個景點到行程">
							<%} %>
						</form>
	</div></div>
				          <hr color="#DCDCDC" width="80%"/>
							
							
							
							<br><br>
<center><button class="btn btn-success" type="button" data-toggle="collapse" data-target="#swapspot" aria-expanded="false" aria-controls="collapseExample">
    交換景點 點我!
  </button></center>
	<div class="collapse" id="swapspot" data-parent="#accordionExample">
  <div class="card card-body">						
							
							<center><h4 style="text-decoration:underline; text-decoration-color: red;color:#FF5511;">
							交換景點順序:</h4>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
							<select size="1" name="spotno1" id="spot1" style="border-radius: 10px;border:1px #0088A8 solid;">
									<%for(TripDetailsVO a:list){%>
									<%for(SpotListVO b:list2){ %>
									<%if(b.getSpotNo().equals(a.getSpotno())) {%>
									<option value="<%=a.getSpotno()%>"><%=b.getSpotName()%>
									<%}}}%>
								</select><br><br>
							<center>
							<%if(list.size()<=1){ %>
							<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/swap.png" style="width:25px;height:25px;">
							<input type="submit" value="交換" disabled="disabled" style="background-color: #AAAAAA;">
								<%}else{ %>
							<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/swap.png" style="width:25px;height:25px;">
							<input type="submit" value="交換">
							<%} %>
							
							</center><br>
								<select size="1" name="spotno2" id="spot2" style="border-radius: 10px;border:1px #0088A8 solid;">
									<%int spottwo=0; %>
									<%for(TripDetailsVO a:list){%>
									<%for(SpotListVO b:list2){ %>
									<%if(b.getSpotNo().equals(a.getSpotno())) {++spottwo;%>
									<%if(spottwo==1){continue;} %>
									<option value="<%=a.getSpotno()%>"><%=b.getSpotName()%>
									<%}}}%>
								</select><br>
								
							<input type="hidden" name="tripno" value="${tripVO.tripno}">
							<input type="hidden" name="cityno" value="${cityno}">
							<input type="hidden" name="action" value="swapSpot">
							</form></center>
				</div></div>
							<hr color="#DCDCDC" width="80%"/>
							
							
							
							<br><br>
							
	<center><button class="btn btn-danger" type="button" data-toggle="collapse" data-target="#delete" aria-expanded="false" aria-controls="collapseExample">
    刪除景點 點我!
  </button></center>
  <div class="collapse" id="delete" data-parent="#accordionExample">
  <div class="card card-body">
  							<center><h4 style="text-decoration:underline; text-decoration-color: red;color:#FF5511;">
							刪除行程的景點:</h4></center>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
							<%int spotcount=0; %>
									<%for(TripDetailsVO a:list){%>
									<%for(SpotListVO b:list2){ %>
									<%if(b.getSpotNo().equals(a.getSpotno())) {%>
<%spotcount++; %>
<%if(spotcount==1){ %><b><label><input type="radio" name="spotno" value="<%=a.getSpotno()%>" checked><span style="font-size:16px;"><%=b.getSpotName()%>&nbsp&nbsp&nbsp<%} %></span></label></b>
<%if(spotcount!=1){ %><b><label><input type="radio" name="spotno" value="<%=a.getSpotno()%>"><span style="font-size:16px;"><%=b.getSpotName()%>&nbsp&nbsp&nbsp<%} %></span></label></b>
<%if(spotcount%3==0){ %>
<br>
<%} %>
									<%}}}%>

							<br>
							<input type="hidden" name="cityno" value="${cityno}">
							<input type="hidden" name="tripno" value="${tripVO.tripno}">
							<input type="hidden" name="action" value="deleteDetails">
							<%if(list.size()==0){ %>
							<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/cut.png" style="width:25px;height:25px;">
							<input type="submit" value="刪除這個景點" disabled="disabled" style="background-color: #AAAAAA;">
							<%}else{ %>
							<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/cut.png" style="width:25px;height:25px;">
							<input type="submit" value="刪除這個景點">
							<%} %>
							</form>
			</div></div>
							<hr color="#DCDCDC" width="80%"/><br>
							<div style="margin-left: 60%;margin-bottom: 3%;" >
						<a href='<%=request.getContextPath() %>/front-end/trip/listAllTrip.jsp'><input value="結束編輯，離開此頁" style="background-color: orange;"/></a>
							</div>
							</div>
	</div>
							</div>
						</div>
<!-- 					<hr color="pink" align="center" width="15%" size="5px"> -->
								

					
			
			</div>
			
			
			
			
		</div>
<!-- 		上面大的div row -->
<hr size="10" color="red" id="mapline"/>
<br>
 <div id="map" style="width: 80%;height: 1200px;margin-left:10%;"></div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<script type="text/javascript">
document.querySelectorAll('.timee')
.forEach(e => e.oninput = () => {
  if (e.value.length >= 2) e.value = e.value.slice(0, 2);
  if (e.value.length === 1) e.value = '0' + e.value;
  if (!e.value) e.value = '00';
});
</script>


    <script type="text/javascript">
      $('tbody').sortable();
      
    </script>
    
    
    <script type="text/javascript">
    var timepicker = new TimePicker('time', {
    	  lang: 'en',
    	  theme: 'dark'
    	});
    	timepicker.on('change', function(evt) {
    	  
    	  var value = (evt.hour || '00') + ':' + (evt.minute || '00');
    	  evt.element.value = value;

    	});
    
    </script>
    <script>
var map;
var markers = [];
var position = [
	<%for(TripDetailsVO a:list){for(SpotListVO b:list2){if(a.getSpotno().equals(b.getSpotNo())){%>
	{label:'<%=b.getSpotName()%>',lat:<%=b.getSpotLati()%>,lng:<%=b.getSpotLong()%>},
	<%}}}%>
	];

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 5,
    center: {
    	lat:<%=spotVO.getSpotLati()%>,
    	lng:<%=spotVO.getSpotLong()%>
//       lat: 23.883234,
//       lng: 120.9825975
    },
  mapTypeControl:true,
  fullscreenControl:true,
  rotateControl:true,
  scaleControl:true,
  streetViewControl:true,
  zoomControl:true,
  mapTypeControlOptions:{position:google.maps.ControlPosition.TOP_CENTER},
  fullscreenControlOptions:{position:google.maps.ControlPosition.TOP_RIGHT},
  rotateControlOptions:{position:google.maps.ControlPosition.RIGHT_CENTER},
  scaleControlOptions:{position:google.maps.ControlPosition.RIGHT_BOTTOM},
  streetViewControlOptions:{position:google.maps.ControlPosition.TOP_LEFT},
  zoomControlOptions:{position:google.maps.ControlPosition.RIGHT_BOTTOM},
  mapTypeId:'terrain'
  });

  for (var i = 0; i < position.length; i++) {
    addMarker(i);
  }
function addMarker(e) {
  setTimeout(function() {
    markers.push(new google.maps.Marker({
        position: {
          lat: position[e].lat,
          lng: position[e].lng
        },
        map: map,
        label: {
            text: position[e].label,
            color: "red",
            fontSize: "14px",
            fontWeight: "bold"
          },
         animation: google.maps.Animation.BOUNCE,
          icon: {
        	  url: '<%=request.getContextPath()%>/images/6.png',
		      scaledSize: new google.maps.Size(60, 80)
    }
      }));
  }, e * 150);
}

var polylinePathPoints = [
	<%for(TripDetailsVO a:list){for(SpotListVO b:list2){if(a.getSpotno().equals(b.getSpotNo())){%>
	{lat:<%=b.getSpotLati()%>,lng:<%=b.getSpotLong()%>},
	<%}}}%>
  ];
var iconPath = {
// 	    path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW
		path:'M 8.1326447,0.80527736 C 8.5471666,0.063577346 9.742752,0.030177346 10.052431,0.82497736 C 10.093464,3.0114774 10.134497,5.1980774 10.17553,7.3845774 C 12.760407,8.9653774 15.345284,10.546179 17.930161,12.127079 C 17.930161,12.881779 17.930161,13.636479 17.930161,14.391179 C 15.373077,13.579479 12.815993,12.767779 10.258908,11.956179 C 10.27281,13.280479 10.286713,14.604879 10.300615,15.929279 C 10.8565,16.555879 11.412385,17.182479 11.96827,17.809079 C 12.25527,18.269479 12.437605,19.641079 11.59784,19.085079 C 10.804104,18.802179 10.010367,18.519179 9.21663,18.236279 C 8.3133108,18.620779 7.4099916,19.005279 6.5066724,19.389779 C 6.3952441,18.705879 6.2272708,17.857479 6.8519879,17.359679 C 7.2927717,16.882879 7.7335555,16.406079 8.1743393,15.929279 C 8.1465467,14.604879 8.1187541,13.280479 8.0909615,11.956179 C 5.5894706,12.824879 3.0879797,13.693479 0.58648883,14.562179 C 0.54479393,13.821679 0.50309893,13.081079 0.46140403,12.340579 C 3.0184842,10.717079 5.5755645,9.0935778 8.1326447,7.4700774 C 8.1326447,5.2484774 8.1326447,3.0268774 8.1326447,0.80527736 z'
		,
			scale: 2,
		    strokeOpacity: 1,
		    strokeWeight: 5
	  };
  var polylinePath = new google.maps.Polyline({
    path: polylinePathPoints,
    geodesic: true,
    strokeColor: 'red',
    strokeOpacity: 0.8,
    strokeWeight: 5,
    editable: true,
    geodesic: false,
    draggable: false,

      map:map
  });
  var aa=1; 
  setInterval(function(){
		
		aa = aa + 0.5;
		  if(aa>100){
		    aa = 0;
		  }
		  polylinePath.setOptions({
		    icons:[{
		      icon: iconPath,
		      offset: aa + '%'
		    }]
		  })
		},60);
  polylinePath.setMap(map);
  
}

    </script>
	  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-iQKSM1MMsgdKN-WatxdCOFJYNBsV--I&callback=initMap">
    </script>
<!-- 	  key自己辦一個吧~~ 我的會爆~~~~~~~~~~~~ -->


 
    
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script type="text/javascript">
$("#f_date1").change(function(){

    var start=$("#f_date1").val();
     var end=$("#f_date2").val();
     var time1=$("#f_date3").val();
     var time2=$("#f_date4").val();
     var time=end-start;
     if(end<start){
     	$("#f_date2").val(start);
    	 $("#times").val(0);
     }
     else{
     $("#times").val(time);
     }
     if(end<=start && time2<time1){
    	 $("#f_date4").val(time1);
     }
 });  

$("#f_date2").change(function(){

	var start=$("#f_date1").val();
    var end=$("#f_date2").val();
    var time1=$("#f_date3").val();
    var time2=$("#f_date4").val();
    var time=end-start;
    if(end<start){
    	$("#f_date2").val(start);
   	 $("#times").val(0);
    }
    else{
    $("#times").val(time);
    }
    if(end<=start && time2<time1){
   	 $("#f_date3").val(time2);
    }

}); 
$("#f_date3").change(function(){

	var start=$("#f_date1").val();
    var end=$("#f_date2").val();
	var st=$("#f_date3").val();
    var et=$("#f_date4").val();
    if(start==end && et<st){
    	$("#f_date4").val(st);
    }
}); 
$("#f_date4").change(function(){

	var start=$("#f_date1").val();
    var end=$("#f_date2").val();
	var st=$("#f_date3").val();
    var et=$("#f_date4").val();
    if(start==end && et<st){
    	$("#f_date4").val(st);
    }
});


$( document ).ready(function() {
	var start=$("#f_date1").val();
    var end=$("#f_date2").val();
    
    
    var time=end-start;
    $("#times").val(time);
});
</script>   
<!-- 左邊 時間-->
<script type="text/javascript">
<%for(int RRR=0;RRR<=kkk;RRR++){%>
$("#qq<%=RRR*4+1%>").change(function(){
	$("#qq<%=RRR*4+1%>").attr("style","color:red;background-color:white;");
	
    var start=$("#qq<%=RRR*4+1%>").val();
     var end=$("#qq<%=RRR*4+3%>").val();
     var time1=$("#qq<%=RRR*4+2%>").val();
     var time2=$("#qq<%=RRR*4+4%>").val();
     var time=end-start;
     if(end<start){
    	$("#qq<%=RRR*4+3%>").attr("style","color:red;background-color:white;");
     	$("#qq<%=RRR*4+3%>").val(start);
    	 $("#tt1").val(0);
     }
     else{
     $("#tt1").val(time);
     }
     if(end<=start && time2<time1){
    	 $("#qq<%=RRR*4+4%>").attr("style","color:red;background-color:white;");
    	 $("#qq<%=RRR*4+4%>").val(time1);
     }
 });  

$("#qq<%=RRR*4+3%>").change(function(){
	$("#qq<%=RRR*4+3%>").attr("style","color:red;background-color:white;");
	var start=$("#qq<%=RRR*4+1%>").val();
    var end=$("#qq<%=RRR*4+3%>").val();
    var time1=$("#qq<%=RRR*4+2%>").val();
    var time2=$("#qq<%=RRR*4+4%>").val();
    var time=end-start;
    if(end<start){
    	$("#qq<%=RRR*4+1%>").attr("style","color:red;background-color:white;");
    	$("#qq<%=RRR*4+3%>").val(start);
   	 $("#tt1").val(0);
    }
    else{
    $("#tt1").val(time);
    }
    if(end<=start && time2<time1){
    $("#qq<%=RRR*4+2%>").attr("style","color:red;background-color:white;");
   	 $("#qq<%=RRR*4+2%>").val(time2);
    }

}); 
$("#qq<%=RRR*4+2%>").change(function(){
	$("#qq<%=RRR*4+2%>").attr("style","color:red;background-color:white;");
	var start=$("#qq<%=RRR*4+1%>").val();
    var end=$("#qq<%=RRR*4+3%>").val();
	var st=$("#qq<%=RRR*4+2%>").val();
    var et=$("#qq<%=RRR*4+4%>").val();
    if(start==end && et<st){
    	$("#qq<%=RRR*4+4%>").attr("style","color:red;background-color:white;");
    	$("#qq<%=RRR*4+4%>").val(st);
    }
}); 
$("#qq<%=RRR*4+4%>").change(function(){
	$("#qq<%=RRR*4+4%>").attr("style","color:red;background-color:white;");
	var start=$("#qq<%=RRR*4+1%>").val();
    var end=$("#qq<%=RRR*4+3%>").val();
	var st=$("#qq<%=RRR*4+2%>").val();
    var et=$("#qq<%=RRR*4+4%>").val();
    if(start==end && et<st){
    	$("#qq<%=RRR*4+2%>").attr("style","color:red;background-color:white;");
    	$("#qq<%=RRR*4+4%>").val(st);
    }
});


$( document ).ready(function() {
	var start=$("#qq<%=RRR*4+1%>").val();
    var end=$("#qq<%=RRR*4+3%>").val();
    
    var time=end-start;
    $("#tt1").val(time);
});
<%}%>
</script>   
</body>
</html>




