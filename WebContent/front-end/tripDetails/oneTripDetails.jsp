<%@page import="com.tripDetails.model.TripDetailsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.tripDetails.model.*"%>
<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO"); 
System.out.println(tripVO);
TripVO tripVO2=(TripVO) session.getAttribute("tripppVO");
if(tripVO==null){
	tripVO=tripVO2;
}
System.out.println(tripVO);
	TripDetailsService tripDetailsSvc = new TripDetailsService();
	List<TripDetailsVO> list = tripDetailsSvc.getfindByTripno(tripVO.getTripno());
	pageContext.setAttribute("list", list);
	
	SpotListVO spotVO=(SpotListVO)request.getAttribute("spotVO");	
	
	if(spotVO==null){
		SpotListService spot=new SpotListService();
		spotVO=spot.getOneSpot("SPT000001");
	}
	SpotListService spot=new SpotListService();
	List<SpotListVO> list2=spot.getAllNoPic();
	pageContext.setAttribute("list2", list2);
	
	    Object account = session.getAttribute("account");
	    String memno1=(String)session.getAttribute("memno");
	    if (account == null) { 
	    	session.setAttribute("tripppVO", tripVO);
			session.setAttribute("location", request.getRequestURI()); 
			response.sendRedirect(request.getContextPath() + "/login.jsp"); 
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

h1, h2, h3, h4, h5, h6 {
	font-family: "微軟正黑體";
	font-weight: bold;
	text-shadow: 3px 3px 3px #cccccc;
	display: block;
	line-height: 1.5em;
	overflow: visible;
	text-shadow: #f3f3f3 1px 1px 0px, #b2b2b2 1px 2px 0;
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
.share{
	border: 0;
	background-color: #77FFEE;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}
.share:hover {
	color:blue;
	background-color: #fff;
	border: 2px #77FFEE solid;
}
th {
	padding: 5px;
	text-align: center;
	border-bottom: 2px dashed red;
	font-family: "微軟正黑體";
}

td {
	/* 	padding: 5px; */
	text-align: center;
	font-family: "微軟正黑體";
	font-size: 20px;
}

.onme {
	border: 0;
	background-color: #FF0000;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	font-family: "微軟正黑體";
}

.onme:hover {
	color: #FF0000;
	background-color: #fff;
	border: 2px #FF0000 solid;
}

#leftdiv {
	width: 100%;
	height: 80px;
	/* 	background-color:#99BBFF; */
	position: fixed;
	top: 40%;
	left: 1%;
	text-align: right;
	z-index: 999;
	border-radius: 20px;
}

#nanana {
	width: 100%;
	height: 80px;
	background-color: rgba(100, 100, 100, 0.8);
	position: fixed;
	top: 0px;
	left: 0px;
	text-align: right;
	z-index: 999;
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

	<br>
	<br>

	<br>
	<br><br>
	<br>
	<div class="row">
	<div
		style="margin-left: 15%; width: 40%; height: 25%; background: linear-gradient(135deg, #77FF00 10%, #FFDDAA 80%, #009FCC); border-radius: 20px;">
		<h1 style="text-decoration: underline; text-decoration-color: red;">
			行程主題 :
			<%=tripVO.getTripname()%></h1>
		<center>
			<h2>
				更新日期 :
				<%=tripVO.getTripestdate()%></h2>
			<h3>
				瀏覽次數 :<%=tripVO.getTimeofviews()%></h3>
			<h4>
				是否成為代購賣家 :<%if(tripVO.getBethebuyer()==0){ %><c:out value="否" />
				<%} %>
				<%if(tripVO.getBethebuyer()==1){%><c:out value="是" />
				<%}%>
			</h4>
			<h5><%=tripVO.getTripstartday() %>~ <%=tripVO.getTripendday() %></h5>
			<h5>共<%=tripVO.getTripdays() %> 天</h5>
		</center>
		<hr size="10" color="pink" />
	</div>
	<div style="margin-left:20%;">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" >
<div class="row">
					<input type="hidden" name="tripVO999" value="<%=tripVO.getTripno() %>"> 
					<input type="hidden" name="memno" value="<%=memno1%>"> 
					<input type="hidden" name="action" value="copyTrip"> 
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/copy.png" style="width:40px;height:40px;">
					<input type="submit" value="複製行程" class="btn btn-outline-success btn-lg nav-link">
					<br>
</div><br>
				</FORM>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do" >
	<div class="row">
		<input type="hidden" name="tripVO999" value="<%=tripVO.getTripno() %>"> 
<%-- 		<input type="hidden" name="memno" value="<%=memno1%>">  --%>
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/block.png" style="width:40px;height:40px;">
		<input type="hidden" name="action" value="blockTrip"> 
		<input type="submit" value="檢舉行程" class="btn btn-outline-danger btn-lg nav-link"><br>
	</div><br>
	</FORM>
<div class="row">
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/print.png" style="width:45px;height:45px;">
		<input type="button" value="列印行程" class="btn btn-outline-warning btn-lg nav-link" onclick="javascript:window.print()"><br>
</div><br>
<div class="row">
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/message.png" style="width:40px;height:40px;">
		<input type="button" value="去留言GO!" class="btn btn-outline-info btn-lg nav-link" onclick="window.location.href='#textarea'"><br>
</div><br>
<div class="row">
<img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/share.png" style="width:40px;height:40px;">
		<iframe src="https://www.facebook.com/plugins/share_button.php?href=http://34.80.28.39/DA101G3/front-end/trip/trip_index.jsp&layout=button_count&size=large&width=120&height=50&appId" width="120" height="50" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowTransparency="true" allow="encrypted-media"></iframe><br>
</div><br>
	</div>
	</div>
	<hr color="#FF7744" align="right" width="40%" size="5px">
	<br>
	
	<hr size="10" color="red" />
	<a name="redline"></a>
	<br>
	<br>
	<br>


	<div class="row" style="margin-left: 10%;">
		<div class="col-1" id="leftdiv">
			<br>
			<center><img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/back.png" style="width:40px;height:40px;"></center><br>
			<input type="button" class="onme" value="點我!跳回到頂層" onclick="window.location.href='#'"></input>
			<br><br>
			<% int j=1;
for(TripDetailsVO a : list){
	if(a.getTripdayorder()==j) {
%>
			<input type="button" class="onme"
				value="點我!跳到第<%=a.getTripdayorder()%>天"
				onclick="window.location.href='#go<%=a.getTripdayorder()%>'"></input>
			<br>
			<br>
			<% j++;}%>

			<%}j=1;%>
<input type="button" class="onme" value="點我!跳到看地圖" onclick="window.location.href='#mapline'"></input><br><br>
<center><img src="<%=request.getContextPath()%>/front-end/tripDetails/pic/forward2.png" style="width:30px;height:30px;"></center>

		</div>

		<div class="col-6">
			<div align="center"
				style="overflow-x: hidden; overflow-y: auto; height: 900px;">
				<%if(list.size()==0){ %>
				<h1 style="text-decoration:underline; text-decoration-color:#FF0088;color:orange;"><br><br><br><br>
				Oops!<br>此篇行程尚未有新增景點內容</h1>
				<%} %><br><br>
				<table width="90%">
					<%
					int i=1;
					for(TripDetailsVO a : list){
					%>
					<tr>
						<td style="border-right: 3px #EEEE00 dashed">
							<h4>
								<%if(a.getTripdayorder()==i) {%>
								<a name="go<%=a.getTripdayorder()%>"></a> 第<%=a.getTripdayorder()%>天
								<% i++;}%>
							</h4>
						</td>
						<td>
							<%
							for(SpotListVO s : list2){
							if(a.getSpotno().equals(s.getSpotNo())){%> <br>
						<br>
						<h3
								style="text-decoration: underline; text-decoration-color: red;"><%=s.getSpotName()%></h3>
							<%}%> <%} %>
						</td>
						<td><br>
						<br>
							<h5><%=a.getTimeofarrive().toString().substring(0,5) %>~<%=a.getTimeofleave().toString().substring(0,5) %></td>
						<td><br>
						<br>
						</td>
						<td><br>
						<br>
						<h5>
								第
								<%=a.getTriporderby() %>
								個
							</h5></td>
					</tr>
					<tr>
						<td style="border-right: 3px #EE7700 dashed"></td>
						<td style="border-bottom: 3px solid dodgerblue;">
						<h5>
								<%if(a.getTriptips()==null) {%>目前尚無評論
								<% }else{%>
								<%=a.getTriptips() %>
								<%} %>
							</h5>
							
						<br><br>
						<br></td>
						<td style="border-bottom: 3px solid dodgerblue;">
						<h5>
								停留了:<%=a.getStayhours()%>
								hr
							</h5>
							<br>
						<br>
						<br></td>
						<td style="border-bottom: 3px solid dodgerblue;"></td>
						<td style="border-bottom: 3px solid dodgerblue;">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
								<%
						 		for(SpotListVO s : list2){
								if(a.getSpotno().equals(s.getSpotNo())){%>
								<input type="hidden" name="spotNo" value="<%=s.getSpotNo()%>">
								<%}%>
								<%} %>
								<input type="hidden" name="tripVO999" value="${tripVO.tripno}">
								<input type="hidden" name="action" value="getOne_spot2">
			<img src="<%=request.getContextPath()%>/front-end/trip/pic/read.png" style="width:40px;height:40px;">
								<input type="submit" value="顯示景點敘述" class="btn btn-info">
							</FORM>

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
		<div class="col-5">
			<br>
			<center>
				<%if(request.getAttribute("spotVO")==null){ %>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<h1
					style="text-decoration: underline; text-decoration-color: red; color: #00AA88;">點選</h1>
				<h1
					style="text-decoration: underline; text-decoration-color: red; color: #00AAAA;"><---"顯示景點詳情"</h1>
				<br>
				<h1
					style="text-decoration: underline; text-decoration-color: red; color: #00AA88;">可以看哦!</h1>

				<%} %>
				<%if (request.getAttribute("spotVO")!=null){%>
				<jsp:include page="listSecondTripDetails.jsp" />
				<iframe width='80%' height='300px' frameborder='0' scrolling='no'
					marginheight='0' marginwidth='0'
					style="border: blue dotted 1px; border-radius: 20px;"
					src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=<%=spotVO.getLocation()%>&z=16&output=embed&t='>
				</iframe>
				<%} %>
			</center>
		</div>
	</div>
	<!-- 		上面大的div row -->

	<br>
	<hr size="10" color="red" id="mapline"/>
	<br><br><br>
	<div id="map" style="width: 80%; height: 1200px; margin-left: 10%;"></div>
	<br>
	<br>
	<div  align="center">
	<div style="width:35%;height:10;border:3px dodgerblue solid;" id="textarea">
	<textarea rows="10" cols="70"  placeholder="留言給作者...遊記王幫您寄出"></textarea>
	<input type="button" value="送出" id="sendback">
	</div></div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	
	<script type="text/javascript">
	$('#sendback').click(function() {
		  alert("已送出 \n感謝您的提交");
	});
	
	</script>
	<script>
var map;
var markers = [];
var bb=-1;
var position = [
	<%for(TripDetailsVO a:list){for(SpotListVO b:list2){if(a.getSpotno().equals(b.getSpotNo())){%>
	{label:'<%=b.getSpotName()%>',lat:<%=b.getSpotLati()%>,lng:<%=b.getSpotLong()%>},
	<%}}}%>
	];

function initMap() {
	  map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 5,
		    center: {
		      lat: <%=spotVO.getSpotLati()%>,
		      lng: <%=spotVO.getSpotLong()%>

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
        	  url: '<%=request.getContextPath()%>/images/9.png',
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
</body>
</html>




