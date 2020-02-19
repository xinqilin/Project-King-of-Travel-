<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
	<script src="https://kit.fontawesome.com/3f96afac76.js"></script>
	
	<title>競猜活動</title>
	
<style type="text/css">
::-webkit-scrollbar
{
	width: 6px;
	background-color: #F5F5F5;
}
::-webkit-scrollbar-thumb
{
	border-radius: 5px;
		background-color: #555;
}
img.players{
	width: 45px;
	height: 54px;
	position: absolute;
	border-radius: 60%;
	background-color: blue;
	-webkit-transition: -webkit-transform 1.5s ease-in-out;
	opacity: 0;
}
img.balls{
	width: 20px;
	height: 20px;
	position: absolute;
	border-radius: 50%;
	-webkit-transition: -webkit-transform 1.5s ease-in-out;
}
img.blayers{
	width: 45px;
	height: 54px;
	position: absolute;
	border-radius: 60%;
	background-color: blue;
	-webkit-transition: -webkit-transform 1.5s ease-in-out;
	opacity: 0;
}
img.bats{
	-webkit-transition: -webkit-transform 1.5s ease-in-out;
}
#rbat{
	-webkit-transform-origin: 0% 0%;
}
#lbat{
	-webkit-transform-origin: 100% 0%;
}
td{
	text-overflow:ellipsis;
	word-break:keep-all; 
	white-space:nowrap;
}
</style>

</head>
<body onload="connect()" onunload="disconnect()">

<nav class="navbar navbar-expand-lg navbar-light bg-light" id="nanana">
		<a class="navbar-brand" href="#"><h3>遊記王</h3></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

			<li class="current_page_item">
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath() %>/index.jsp"> 首頁 <span class="sr-only"></span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/front-end/trip/trip_index.jsp">行程</a></li>
				<li class="nav-item"><a class="nav-link" href="#">遊記</a></li>
				<li class="nav-item"><a class="nav-link" href="#">許願池</a></li>
				<li class="nav-item"><a class="nav-link" href="#">商城</a></li>
				<li class="nav-item"><a class="nav-link" href="#">積分商城</a></li>
				<li class="nav-item"><a class="nav-link" href="#">競猜活動</a></li>
				<li class="nav-item"><a class="nav-link" href="#">景點專區</a></li>



				<c:if test="${account==null}"><li class="nav-item"><a  class="nav-link" href="<%=request.getContextPath()%>/login.jsp">會員登入</a></li></c:if>
				<c:if test="${account!=null}"><li class="nav-item"><a class="nav-link" href="#" style="text-decoration:underline; color:red;">
				你好! ${account}</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Logouthandler.do">登出</a></li></c:if>

				<li class="nav-item"><label style="display: none"></label></li>


			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
</nav>

<!-- 以上為NAV BAR -->

<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-2">123</div>
		
<div class="col-10">
	<div class="row">
<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
  </li>
</ul>

	
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab" style="height:500px;width:1500px;margin:10px 20px 10px 0px">
	<div class="row">
		<div style="height:520px;width:900px;margin:10px 0px 10px 15px;background-image: url('<%=request.getContextPath()%>/images/activity/baseballfield1.jpg');background-repeat:no-repeat;background-size: 80% 100%;background-position: center;background-color: black;"></div>

			<div style="height:520px;width:500px;margin:10px 0px 10px 0px">
				<div style="height:50px;width:500px;margin:0px 0px 0px 0px;text-align: center;border: 1px solid;display: table-cell; vertical-align: middle;">賽事文字直播</div>
				<div style="height:390px;width:500px;margin:0px 0px 0px 0px;border: 1px solid;padding-left: 5px;padding-right: 5px;overflow-y: auto;word-break: break-word;" id="stream"></div>
				<div style="height:80px;width:500px;margin:0px 0px 0px 0px;border: 1px solid">
					<div class="row justify-content-center" style="width:500px;margin:0px 0px 0px 0px;">
					<div style="height:80px;width:420px;margin:0px 0px 0px 0px;padding-left: 5px; padding-right: 5px;" contenteditable="true" id='liveinput'></div>
					<div style="height:80px;width:80px;margin:0px 0px 0px 0px;">
					<div style="text-align: center;"><img src="<%=request.getContextPath()%>/images/emote/sendemote2.png" height="40" width="40" id='emobtn1'></div>
					<div style="text-align: center;"><img src="<%=request.getContextPath()%>/images/emote/chatbubble.png" height="40" width="40" id='sendlive'></div>
				</div>
			</div>
				</div>
			</div>
		</div>
  </div>
  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab" style="height:520px;width:900px;margin:10px 0px 10px 15px;">...</div>
  <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab" style="height:520px;width:900px;margin:10px 0px 10px 15px;">...</div>
</div>
</div>
<div class="row">
	<div style="height:420px;width:900px;margin:0px 0px 0px 0px">
	<div class="row justify-content-center" style="height:200px;width:900px;margin:25px 0px 0px 0px">
		<table style="background-color: #486030; border-radius: 10px;color: #EEEEEE;font-size: 18px;border-spacing:25px 30px;border-collapse: separate;
           border-spacing: 10px;">
								<tr>
									<th>Team</th>
									<th>1st</th>
									<th>2nd</th>
									<th>3rd</th>
									<th>4th</th>
									<th>5th</th>
									<th>6th</th>
									<th>7th</th>
									<th>8th</th>
									<th>9th</th>
									<th>Final</th>
								</tr>
								<tr>
									<td><img src="<%=request.getContextPath()%>/images/mlbTeamLOGO/LosAngeles_Angels1.png" width="40px" height="40px"><span>Los Angeles Angels</span></td>
									<td data-th="1st">0</td>
									<td data-th="2nd">1</td>
									<td data-th="3rd">0</td>
									<td data-th="4th">0</td>
									<td data-th="5th">1</td>
									<td data-th="6th">0</td>
									<td data-th="7th">0</td>
									<td data-th="8th">3</td>
									<td data-th="9th">0</td>
									<td data-th="Final">0</td>
								</tr>
								<tr>
									<td><img src="<%=request.getContextPath()%>/images/mlbTeamLOGO/NewYork_Yankees.png" width="40px" height="40px"><span>NewYork Yankees</span></td>
									<td data-th="1st">0</td>
									<td data-th="2nd">3</td>
									<td data-th="3rd">0</td>
									<td data-th="4th">0</td>
									<td data-th="5th">0</td>
									<td data-th="6th">1</td>
									<td data-th="7th">0</td>
									<td data-th="8th">2</td>
									<td data-th="9th">0</td>
									<td data-th="Final">12</td>
								</tr>
							</table>
						</div>
						<div class="row">
							<div class="row justify-content-start" style="height:40px;width:900px;margin:0px 0px 0px 15px">
							<button id="btn1">OK</button>
							<button id="btn2">OK</button>
							<button id="btn3">OK</button>
							<button id="btn4">OK</button>
							<button id="btn5">OK5</button>
							<button id="btn6">OK6</button>
							<button id="btn7">OK7</button>
							<button id="btn8">OK8</button>
							<button id="btn9">OK9</button>
							<button id="btn10">OK10</button>
							</div>
						<div class="row justify-content-start" style="height:120px;width:900px;margin:0px 0px 0px 15px"> 
						<table>
							<tr>
								<td>防守球員進場</td>
								<td><select name="teamInit" id="teamInit">
									<option value="LA">LA</option>
									<option value="NY">NY</option>
								</select></td>
								<td>進攻球員進場</td>
								<td><select name="hitter" id="hitter">
									<option value="blayer1">David Fletcher</option>
									<option value="blayer2">Mike Trout</option>
									<option value="blayer3">Shohei Ohtani</option>
									<option value="blayer4">Justin Upton</option>
									<option value="blayer5">Andrelton Simmons</option>
									<option value=blayer6">Albert Pujols</option>
									<option value=blayer7">Kevan Smith</option>
									<option value=blayer8">Luis Rengifo</option>
									<option value=blayer9">Felix Pena</option>
								</select></td>
								<td>投手投球</td>
								<td><select name="pitchType" id="pitchType">
									<option value="fastBall">快速球</option>
									<option value="curveBall">曲球</option>
									<option value="breakingBall">變化球</option>
									<option value="bodyBall">觸身球</option>
								</select></td>
							</tr>
							<tr>
								<td>打者揮棒</td>
								<td><select name="hitType" id="hitType">
									<option value="swingBat">揮棒</option>
									<option value="swingBatMiss">揮棒落空</option>
									<option value="nswingBat">不揮棒</option>
								</select></td>
								<td>擊球結果</td>
								<td><select name="hitResult" id="hitResult">
									<option value="strike">好球</option>
									<option value="badBall">壞球</option>
									<option value="foulBall">界外球</option>
									<option value="groundHit">滾地安打</option>
									<option value="flyHit">飛越安打</option>
									<option value="homeRun">全壘打</option>
									<option value="outFlyBall">外野高飛球</option>
									<option value="inGroundBall">內野滾地球</option>
								</select></td>
							</tr>	
							<tr>
							    <td>本壘到一壘</td>
								<td><select name="base0to1" id="base0to1">
									<option>None</option>
									<option value="blayer1">David Fletcher</option>
									<option value="blayer2">Mike Trout</option>
									<option value="blayer3">Shohei Ohtani</option>
									<option value="blayer4">Justin Upton</option>
									<option value="blayer5">Andrelton Simmons</option>
									<option value="blayer6">Albert Pujols</option>
									<option value="blayer7">Kevan Smith</option>
									<option value="blayer8">Luis Rengifo</option>
									<option value="blayer9">Felix Pena</option>
								</select></td>
								<td>一壘到二壘</td>
								<td><select name="base1to2" id="base1to2">
									<option>None</option>
									<option value="blayer1">David Fletcher</option>
									<option value="blayer2">Mike Trout</option>
									<option value="blayer3">Shohei Ohtani</option>
									<option value="blayer4">Justin Upton</option>
									<option value="blayer5">Andrelton Simmons</option>
									<option value="blayer6">Albert Pujols</option>
									<option value="blayer7">Kevan Smith</option>
									<option value="blayer8">Luis Rengifo</option>
									<option value="blayer9">Felix Pena</option>
								</select></td>
								<td>二壘到三壘</td>
								<td><select name="base2to3" id="base2to3">
									<option>None</option>
									<option value="blayer1">David Fletcher</option>
									<option value="blayer2">Mike Trout</option>
									<option value="blayer3">Shohei Ohtani</option>
									<option value="blayer4">Justin Upton</option>
									<option value="blayer5">Andrelton Simmons</option>
									<option value="blayer6">Albert Pujols</option>
									<option value="blayer7">Kevan Smith</option>
									<option value="blayer8">Luis Rengifo</option>
									<option value="blayer9">Felix Pena</option>
								</select></td>
							</tr>
							<tr>
								<td>三壘到本壘</td>
								<td><select name="base3to0" id="base3to0">
									<option>None</option>
									<option value="blayer1">David Fletcher</option>
									<option value="blayer2">Mike Trout</option>
									<option value="blayer3">Shohei Ohtani</option>
									<option value="blayer4">Justin Upton</option>
									<option value="blayer5">Andrelton Simmons</option>
									<option value="blayer6">Albert Pujols</option>
									<option value="blayer7">Kevan Smith</option>
									<option value="blayer8">Luis Rengifo</option>
									<option value="blayer9">Felix Pena</option>
								</select></td>
							</tr>
						</table>
						</div>
						</div>
	</div>
	<div style="height:420px;width:500px;margin:30px 0px 10px 0px">
		<div style="height:50px;width:500px;margin:0px 0px 0px 0px;text-align: center;border: 1px solid;display: table-cell; vertical-align: middle;">競猜聊天室</div>
		
		<div style="height:200px;width:350px;position: absolute;background-color: grey;z-index: 50;margin-left: 150px;margin-top: 90px;visibility:hidden" id="emotewindow">
			<span id="em1">😀</span>
			<span id="em2">😁</span>
			<span id="em3">😂</span>
			<span id="em4">😃</span>
			<span id="em5">😅</span>
			<span id="em6">😆</span>
			<span id="em7">😇</span>
			<span id="em8">😈</span>
			<span id="em9">🐒</span>
<%-- 			<span><img src="<%=request.getContextPath()%>/images/emote/Los_Angeles_Angels_of_Anaheim.svg" height="40" width="40"></span> --%>
			<span><img src="<%=request.getContextPath()%>/images/emote/DaWu.jpg" height="40" width="40"></span>

			
		</div>
		
		<div style="height:290px;width:500px;margin:0px 0px 0px 0px;border: 1px solid;padding-left: 5px;padding-right: 5px;overflow-y: auto;word-break: break-all;" id='chatroom'></div>
		
		<div style="height:80px;width:500px;margin:0px 0px 0px 0px;border: 1px solid">
			<div class="row justify-content-center" style="width:500px;margin:0px 0px 0px 0px;">
				<div style="height:80px;width:420px;margin:0px 0px 0px 0px;padding-left: 5px; padding-right: 5px;" contenteditable="true" id='chatinput'></div>
				<div style="height:80px;width:80px;margin:0px 0px 0px 0px;">
				<div style="text-align: center;"><img src="<%=request.getContextPath()%>/images/emote/sendemote2.png" height="40" width="40" id='emobtn'></div>
				<div style="text-align: center;"><img src="<%=request.getContextPath()%>/images/emote/chatbubble.png" height="40" width="40" id='sendmes'></div>
				</div>
			</div>
		</div>
	</div>
</div>

</div>
</div>

	<h3 id="statusOutput" class="statusOutput"></h3>
<div>
<img src="<%=request.getContextPath()%>/images/activity/baseball.png" class="balls" id="ball" style="top:476px;left:746px;opacity: 0">
<img src="<%=request.getContextPath()%>/images/activity/baseball.png" class="balls" id="ball1" style="top:592px;left:770px;opacity: 0">
<img src="<%=request.getContextPath()%>/images/activity/bat5.png" class="bats" id="lbat" width="60px" height="40px"  style="top:630px;left:740px;position: absolute;opacity: 0;z-index: 99">
<img src="<%=request.getContextPath()%>/images/activity/bat6.png" class="bats" id="rbat" width="60px" height="40px"  style="top:630px;left:740px;position: absolute;opacity: 0;z-index: 99">

<img src="<%=request.getContextPath()%>/images/activity/LA-removebg-preview.png" width="320" height="180" style="top:470px;left:423px;position:absolute;z-index: 100">
<img src="<%=request.getContextPath()%>/images/activity/NYjpg-removebg-preview.png" width="300" height="165" style="top:485px;left:823px;position:absolute;z-index: 100">
			
<img src="<%=request.getContextPath()%>/images/ny/DJ LeMahieu.jpg" class="players" id="player1" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Aaron Judge.jpg" class="players" id="player2" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Aaron Hicks.jpg" class="players" id="player3" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Gary Sanchez.jpg" class="players" id="player4" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Didi Gregorius.jpg" class="players" id="player6" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Gleyber Torres.jpg" class="players" id="player7" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Gio Urshela.jpg" class="players" id="player8" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Brett Gardner.jpg" class="players" id="player9" style="top:593px;left:948px">
<img src="<%=request.getContextPath()%>/images/ny/Masahiro Tanaka.jpg" class="players" id="pitcher" style="top:593px;left:948px">

<img src="<%=request.getContextPath()%>/images/la/David Fletcher.jpg" class="blayers" id="blayer1" data-type="2" style="top:600px;left:565px;">
<img src="<%=request.getContextPath()%>/images/la/Mike Trout.jpg" class="blayers" id="blayer2" data-type="2" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Shohei Ohtani.jpg" class="blayers" id="blayer3" data-type="1" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Justin Upton.jpg" class="blayers" id="blayer4" data-type="2" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Andrelton Simmons.jpg" class="blayers" id="blayer5" data-type="2" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Albert Pujols.jpg" class="blayers" id="blayer6" data-type="2" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Kole Calhoun.jpg" class="blayers" id="blayer7" data-type="1" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Kevan Smith.jpg" class="blayers" id="blayer8" data-type="2" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Luis Rengifo.jpg" class="blayers" id="blayer9" data-type="1" style="top:616px;left:576px">
<img src="<%=request.getContextPath()%>/images/la/Felix Pena.jpg" class="blayers" id="bitcher" style="top:616px;left:576px">
<input type="hidden" id="xx"></inuput>
<input type="hidden" id="yy"></inuput>
<input type="hidden" id="xx1"></inuput>
<input type="hidden" id="yy1"></inuput>

</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>

<script>
	var MyPoint = "/ActivityChatWS/james";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			updateStatus("WebSocket Connected");
			alert(endPointURL);
		};

		webSocket.onmessage = function(event) {
			
			var jsonObj = JSON.parse(event.data);

			if(jsonObj.type == 'live'){
				var messagesArea = document.getElementById("stream");
				var message = jsonObj.message + '<br>';
				messagesArea.innerHTML = messagesArea.innerHTML + message;
				messagesArea.scrollTop = messagesArea.scrollHeight;
				<%--送出資料後重新定位至div起點--%>
				var range = document.createRange();
				var sel = window.getSelection();
				var inputMessage1 = document.getElementById("liveinput");
				range.setStart(inputMessage1.childNodes[0], 0);
				range.collapse(true);
				sel.removeAllRanges();
				sel.addRange(range);
				inputMessage1.focus();
				$("#liveinput").html('');
			}
			
			if(jsonObj.type == 'msg'){
				var messagesArea = document.getElementById("chatroom");
				var message = jsonObj.userName + ": " + jsonObj.message + '<br>';
				messagesArea.innerHTML = messagesArea.innerHTML + message;
				messagesArea.scrollTop = messagesArea.scrollHeight;
				<%--送出資料後重新定位至div起點--%>
				var range = document.createRange();
				var sel = window.getSelection();
				var inputMessage = document.getElementById("chatinput");
				range.setStart(inputMessage.childNodes[0], 0);
				range.collapse(true);
				sel.removeAllRanges();
				sel.addRange(range);
				inputMessage.focus();
				$("#chatinput").html('');
			}
			if(jsonObj.type == 'preball'){
				if(jsonObj.teamInit == 'NY'){$.nyInit();}
				if(jsonObj.hitterType == 1){
					$.lHitterInit(jsonObj.hitter);
				}
				if(jsonObj.hitterType == 2){
					$.rHitterInit(jsonObj.hitter);
				}
			}
			if(jsonObj.type == 'ball'){
				if(jsonObj.pitchType == 'curveBall'){
					$.curveBall();
				}
				if(jsonObj.pitchType == 'fastBall'){
					$.fastBall();
				}
				if(jsonObj.pitchType == 'breakingBall'){
					$.breakingBall();
				}
				if(jsonObj.hitResult == 'flyHit'){
					$.parabola(jsonObj.dx, jsonObj.dy);
				}
				if(jsonObj.hitResult == 'groundHit'){
					$.groundHit(jsonObj.dx, jsonObj.dy);
				}
				var abc = $('#hitter').val();
				console.log($('#'+ $('#hitter').val()).data('type'));
				if((jsonObj.hitType == 'swingBat') && (jsonObj.hitterType == 1)){
					$.lSwingBat();
				}
				if((jsonObj.hitType == 'swingBat') && (jsonObj.hitterType == 2)){
					$.rSwingBat();
				}
				$.runnerBase0to1(jsonObj.base0to1);
				$.runnerBase1to2(jsonObj.base1to2);
				$.runnerBase2to3(jsonObj.base2to3);
				$.runnerBase3to0(jsonObj.base3to0);				
			}
		};
		
		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}

	<%--var inputUserName = document.getElementById("userName");
	inputUserName.focus();--%>

	function sendMessage() {
		var userName = 'John';

		var inputMessage = document.getElementById("chatinput");
		var message = inputMessage.innerText;
		if (message == "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var messgae = inputMessage.innerHTML;
			var jsonObj = {
				"userName" : userName,
				"message" : message,
				"type" : "msg"
			};
			webSocket.send(JSON.stringify(jsonObj));
		}
	}
	
	function sendLive(){
		var inputMessage = document.getElementById("liveinput");
		var message = inputMessage.innerHTML;
			var jsonObj = {
				"type" : "live",
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
		}

	function disconnect() {
		webSocket.close();
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
	$(document).ready(function() {
		$("#emobtn").click(function(){
	 		 $('#emotewindow').css({'visibility':'visible'});
		});
		$("#emotewindow").click(function(){
	 		 $('#emotewindow').css({'visibility':'hidden'});
		});
		$('span').click(function(){
			if($('#chatinput').text().length <= 120) { 
				$('#chatinput').append($(this).html());
			  }
			var range = document.createRange();
			var sel = window.getSelection();
			var inputMessage = document.getElementById("chatinput");
			range.setStartAfter(inputMessage.lastChild, 0);
			range.collapse(true);
			sel.removeAllRanges();
			sel.addRange(range);
			inputMessage.focus();
		});
		$('#sendmes').click(function(){
			sendMessage();
		});
		$('#sendlive').click(function(){
			sendLive();
		});
		$('#chatinput').keydown(function(event){
			if(event.keyCode == 13){
			sendMessage();
			}
		});
		$('#chatinput').on('keydown paste', function(event) { 
			  if($(this).text().length === 120 && event.keyCode != 8) { 
			    event.preventDefault();
			  }
			});
		$('#btn1').click(function(){
			var jsonObj = {
					"type" : "ball",
					"dx" : $('#xx').val(),
					"dy" : $('#yy').val(),
					"dx1" : $('#xx1').val(),
					"dy1" : $('#yy1').val(),
					"teamInit" : $('#teamInit').val(),
					"hitterType" : $('#'+ $('#hitter').val()).data('type'),
					"hitType" : $('#hitType').val(),
					"hitter" : '#'+ $('#hitter').val(),
					"pitchType" : $('#pitchType').val(),
					"hitResult" : $('#hitResult').val(),
					"base0to1" : $('#base0to1').val(),
					"base1to2" : $('#base1to2').val(),
					"base2to3" : $('#base2to3').val(),
					"base3to0" : $('#base3to0').val()
				};
				webSocket.send(JSON.stringify(jsonObj));
		});
		$('#btn2').click(function(){
			var jsonObj = {
				"type" : "preball",
				"teamInit" : $('#teamInit').val(),
				"hitterType" : $('#'+ $('#hitter').val()).data('type'),
				"hitter" : $('#hitter').val()
				};
				webSocket.send(JSON.stringify(jsonObj));
		});
		$('#btn3').click(function(){
			$('#xx').val(dx);
		    $('#yy').val(dy);
		    console.log($('#xx').val());
		    console.log($('#yy').val());
		});
		$('#btn4').click(function(){
			$('#xx1').val(dx);
		    $('#yy1').val(dy);
		    console.log($('#xx1').val());
		    console.log($('#yy1').val());
		});
		var i = 0;
		<!--球員登場-->
		jQuery.lHitterInit = function(data){
			$( '#' + data ).css({opacity: 1});
			$( '#' + data ).animate({
				left: "790",
				top: "597",
			}, 
			{
				duration:1500,
				step: function() {
					$('#' + data).css({ 
					});
				},
				complete: function() {
					$('#lbat').css({opacity: 1});
				}
			});
		}
		jQuery.rHitterInit = function(data){
			$( '#' + data ).css({opacity: 1});
			$( '#' + data ).animate({
				left: "710",
				top: "597",
			}, 
			{
				duration:1500,
				step: function() {
					$('#' + data).css({ 
					});
				},
				complete: function() {
					$('#rbat').css({opacity: 1});
				}
			});
		}
		jQuery.nyInit = function(){
			$('img.players').css({opacity: 1}),
			$( '#player1' ).animate({left: "45%", top: "45%"}, 1000, "swing"),
			$( '#player2' ).animate({left: "50%", top: "25%"}, 2500, "swing"), 
			$( '#player3' ).animate({left: "39%", top: "20%"}, 2000, "swing"),
			$( '#player4' ).animate({left: "753", top: "624"}, 1500, "swing"),
			$( '#player6' ).animate({left: "33%", top: "35%"}, 2500, "swing"),
			$( '#player7' ).animate({left: "45%", top: "35%"}, 1500, "swing"),
			$( '#player8' ).animate({left: "33%", top: "45%"}, 1500, "swing"),
			$( '#player9' ).animate({left: "29%", top: "25%"}, 3000, "swing"),
			$( '#pitcher' ).animate({left: "39%", top: "45%"}, 1500, "swing")
		};
		<!--球種-->
		jQuery.fastBall = function(data){
			$( '#ball' ).animate({
				left: "770",
				top: "592",
			}, 
			{
				duration:2500,
				step: function(now) {
					$('#ball').css({ transform: 'rotateX(3600deg) scale(2, 0.5)',
					border: '2px solid red' });
				},
				start: function() {
					$('#ball').css({opacity: 1});
				},
				complete: function() {
					$('#ball').css({transform : '',  opacity: 0, top:'476px', left:'746px', border: ''});
				}
			});
		};
		jQuery.curveBall = function(){
			$( '#ball' ).animate({
				left: "650",
				top: "592",
			}, 
			{
				duration:2500,
				step: function(now) {
					$('#ball').css({ 
						transform: 'translateX(120px) rotateX(3600deg)'
						 });
				},
				start: function() {
					$('#ball').css({opacity: 1});
				},
				complete: function() {
					$('#ball').css({transform : '',  opacity: 0, top:'476px', left:'746px'});
				}
			});
		};
		jQuery.breakingBall = function(data){
			$('#ball').css({opacity: 1});
			$( '#ball' ).animate({
				left: "41%",
				top: "64%",
				opacity: 0
			}, 
			{
				duration:2500,
				step: function() {
					$('#ball').css({ 
						transform: 'rotateX(3600deg)'
						 });
					console.log($('#ball'));
				},
				start: function() {
					$('#ball').css({opacity: 1});
				},
				complete: function() {
					$('#ball').css({transform : '',  opacity: 0, top:'476px', left:'746px'}); 
				}
			});
		}
		<!--揮棒-->
		jQuery.lSwingBat = function(data){
			$( '#lbat' ).delay(500).animate({
				left: "740",
				top: "610",
			}, 
			{
				duration:1000,
				step: function(now) {
					$('#lbat').css({ 
						transform: 'rotate(150deg)'
					});
				},
				start: function() {
					$('#lbat').css({transform: '', opacity: 1})
				},
				complete: function() {
					$.lBatThrow();
				}
			});
		}
		jQuery.rSwingBat = function(data){
			$( '#rbat' ).delay(1000).animate({
				left: "730",
				top: "610",
			}, 
			{
				duration:1000,
				step: function(now) {
					$('#rbat').css({ 
						transform: 'rotate(-150deg)'
					});
				},
				start: function() {
					$('#rbat').css({transform: '', opacity: 1})
				},
				complete: function() {
					$.rBatThrow();
				}
			});
		}
		jQuery.rBatThrow = function(data){
			$( '#rbat' ).animate({
				left: "645",
				top: "640",
			}, 
			{
				duration:1500,
				step: function() {
					$('#rbat').css({ 
						transform: 'rotate(900deg)',
					});
				},
				complete: function() {
					$('#rbat').css({transform: '', opacity: 0, left:'740px', top:'637px'})
				}
			});
		}
		jQuery.lBatThrow = function(data){
			$( '#lbat' ).animate({
				left: "825",
				top: "630",
			}, 
			{
				duration:1500,
				step: function() {
					$('#lbat').css({ 
						transform: 'rotate(900deg)',
					});
				},
				complete: function() {
					$('#lbat').css({transform: '', opacity: 0,left:'740px', top:'630px'})
				}
			});
		}
		<!--擊球結果-->
		jQuery.groundHit = function(dx, dy){
			$( '#ball1' ).delay(1500).animate({
				left: dx,
				top: dy,
			}, 
			{
				duration:2500,
				step: function() {
					$('#ball1').css({ 
						transform: 'rotate(8400deg)'
					});
				},
				start: function() {$('#ball1').css({ opacity: 1, width:'10px', height:'10px'});	
				},
				complete: function() {
					$('#ball1').css({transform: '', opacity: 0, left: '770px', top: '592px', width:'20px', height:'20px'});
				}
			});
		}
		jQuery.parabola = function(dx, dy){
			$( '#ball1' ).delay(2500).animate({
				left: dx,
				top: dy,
			}, 
			{
				duration:2500,
				step: function() {
					if(i < 150 ){
						$('#ball1').css({ 
							transform: 'scale(3) rotateX(3600deg)'
						});
						i = i + 1;
						let go = document.getElementById('ball');
						let ht = window.getComputedStyle(go, null).getPropertyValue("transform");
						console.log(ht)
						console.log(i);
					}
					if(i >= 150 ){
						$('#ball1').css({ 
							transform: 'scale(1) rotateX(7200deg)' 
						});
						let go = document.getElementById('ball');
						let ht = window.getComputedStyle(go, null).getPropertyValue("transform");
						console.log(ht)
					}
				},
				start: function(){
					$('#ball1').css({opacity : 1});
				},
				complete: function() {
					$('#ball1').css({transform : '', opacity: 0, left: '770px', top: '592px'});
					i = 0;
				}
			});
		}
		<!--跑者移動-->
		jQuery.runnerBase0to1 = function(data){
			$( '#' + data ).delay(2550).animate({
				left: "874",
				top: "482",
			}, 
			{
				duration:1500,
				step: function() {
				}
			});
		}
		jQuery.runnerBase1to2 = function(data){
			$( '#' + data ).delay(50).animate({
				left: "750",
				top: "377",
			}, 
			{
				duration:1500,
				step: function() {
				}
			});
		}
		jQuery.runnerBase2to3 = function(data){
			var ddata = data;
			$( '#' + data ).delay(50).animate({
				left: "630",
				top: "482",
			}, 
			{
				duration:1500,
				step: function() {
				}
			});
		}
		jQuery.runnerBase3to0 = function(data){
			$( '#' + data ).delay(50).animate({
				left: "750",
				top: "593",
			}, 
			{
				duration:1500,
				step: function() {
					$('#' + data).css({ 
					});
				}
			});
		}
	});
	<!--確定球的落點-->
	var dx;
	var dy;
	$( document ).on( "dblclick", function( event ) {
		dx = event.pageX;
		dy = event.pageY;
	});	
	
</script>
</html>