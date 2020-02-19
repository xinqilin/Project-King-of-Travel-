<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<%-- <%= memVO==null %> --%>
<%-- ${memVO==null} <!-- EL寫法 --> --%>

<%-- <%@ page import="sun.misc.BASE64Encoder" %>> --%>
<%-- <%! int  index=0; %>> --%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	


	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/style.css">
	
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<%=request.getContextPath()%>/js/address.js"></script>
	
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body >
	<%@ include file="/nav-f" %>
		<div class="container" align="center">
			
				<div align="center" style="width:60%" >
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<!-- Start Sign In Form -->
					<form action="mem.do" runat="server" class="fh5co-form animate-box" data-animate-effect="fadeInRight" METHOD="post" enctype="multipart/form-data">
						<h2>會員註冊</h2>
						<table>
						<tr>
							<td><strong>姓名:</strong><font color=red><b>*</b></font></td>
							<td><input type="text" class="form-control" id="name" placeholder="Name" autocomplete="off"
							name="memName" value="<%= (memVO==null)? "" : memVO.getMemName()%>"/></td>
						</tr>
						<tr>
							<td><strong>E-mail:</strong><font color=red><b>*</b></font></td>
							<td><input type="email" class="form-control" id="email" placeholder="Email" autocomplete="off"
							 name="e_mail" value="<%= (memVO==null)? "" : memVO.getE_mail()%>" required/></td>
						</tr>
						<tr>
							<td><strong>密碼:</strong><font color=red><b>*</b></font></td>
							<td><input type="password" class="form-control" id="memPassWd" placeholder="Password" autocomplete="off"
							minlength="6" maxlength="12" name="memPassWd" value="<%= (memVO==null)? "" : memVO.getMemPassWd()%>" /></td>
						</tr>
						<tr>
							<td><strong>確認密碼:</strong><font color=red><b>*</b></font></td>
							<td><input type="password" class="form-control" id="memPassWdConfirm" placeholder="Password" autocomplete="off"
							minlength="6" maxlength="12" name="memPassWdConfirm" value="<%= (memVO==null)? "" : memVO.getMemPassWd()%>" /></td>
						</tr>
						<tr>
							<td></td>
							<td><img id="output" style="width:300px;height:200px;border-radius:8px;"></td>
						</tr>
						<tr>
							<td><strong>頭像:</strong></td>
							<td><input type="file" name="memPhoto" accept="image/*" onchange="loadFile(event)" size="10" /></td>
						</tr>
						<tr>
							<td><strong>暱稱:</strong></td>
							<td><input type="text" class="form-control" id="ncikName" placeholder="ncikName" autocomplete="off"
							name="nickName" value="<%= (memVO==null)? "" : memVO.getNickName()%>" /></td>
						</tr>
						<tr>
							<td><strong>身分證字號:</strong><font color=red><b>*</b></font></td>
							<td><input type="text" class="form-control" id="idNo" placeholder="idNo" autocomplete="off"
							name="idNo" value="<%= (memVO==null)? "" : memVO.getIdNo()%>" /></td>
						</tr>
						<tr>
							<td><strong>生日:</strong></td>
							<td><input name="birDay" id="f_date1" type="text" /></td>
						</tr>
						<tr>
							<td><strong>地址:</strong></td>
<!-- 							<input type="text" class="form-control" id="address" placeholder="address" autocomplete="off" -->
<%-- 							name="address" value="<%= (memVO==null)? "桃園市中壢區中大路300號" : memVO.getAddress()%>" /> --%>
							<td>
							<select id="zone1"  name="zone1" style="width: 110px;"></select>
							<select id="zone2"  name="zone2" style="width: 110px;"></select>
					        <input	type="text" id="zipcode" name="zipcode" style="width: 30px;" />
					        <input	type="text" id="address" name="address" value="" style="width: 200px;"><p>
							</td>
						</tr>
						<tr>
							<td><strong>電話:</strong></td>
							<td><input type="text" class="form-control" id="phone" placeholder="phone" autocomplete="off"
							name="phone" value="<%= (memVO==null)? "" : memVO.getPhone()%>" /></td>
						</tr>
						<tr>
							<td><strong>簡介:</strong></td>
							<td><input type="text" class="form-control" id="introduction" placeholder="introduction" autocomplete="off"
							name="introduction" value="<%= (memVO==null)? "" : memVO.getIntroduction()%>" /></td>
						</tr>
						
						<tr>
							<td><p>已經註冊? <a href='<%=request.getContextPath()%>/login.jsp'>登入</a></p></td>
						</tr>
						
						</table>
						<table>
							<input type="hidden" name="action" value="insert">
							<td><input type="submit" value="註冊" class="btn btn-primary" style="width:100px;"></td>
						</table>
					</form>
						<td><button onclick="setData()" style="background-color:#FFFFFF;border-radius:80px;"><img src="<%=request.getContextPath()%>/images/icons8-java-256.png"></button></td>
					<!-- END Sign In Form -->
				</div>
		</div>
			
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. More Templates <a href="#" target="_blank" title="遊記王">遊記王</a> - Collect from <a href="#" title="遊記王" target="_blank">遊記王</a></small></p></div>
			</div>

	<script>
	  var loadFile = function(event) {
	    var output = document.getElementById('output');
	    output.src = URL.createObjectURL(event.target.files[0]);
	  };
	</script>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	
	<script type="text/javascript">
		$(function() {
			init_address();
			$("#zone1").val('台北市'); //縣市
			for ( var i in zip['台北市']) {
				o = document.createElement('option');
				o.text = i;
				o.value = i;
				zone2.options.add(o);
			}
			$("#zone2").val('信義區'); //鄉鎮市區
			$("input[name='zipcode']").val('110'); //郵遞區號
		})
	</script>
	
	<script>
	function setData(){
		$('#name').val('圖玉米');
		$('#email').val('tucorn823@gmail.com');
		$('#memPassWd').val('111111');
		$('#memPassWdConfirm').val('111111');
		$('#ncikName').val('玉米');
		$('#idNo').val('A123456543');
		$('#address').val('信義路五段7號');
		$('#phone').val('0912345678');
		$('#introduction').val('我是玉米');
	}
	</script>
	</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date birDay = null;
  try {
	  birDay = memVO.getBirDay();
   } catch (Exception e) {
	    birDay = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=birDay%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>