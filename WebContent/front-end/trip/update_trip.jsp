<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>

<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO");
	// 	TripDetailsVO tripDetailsVO = (TripDetailsVO) request.getAttribute("tripDetailsVO");
%>
<%
	Object account = session.getAttribute("account"); // 從 session內取出 (key) account的值
// 	if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
// 		session.setAttribute("location", request.getRequestURI()); 
// 	//*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
// 		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
// 	//*工作2 : 請該user去登入網頁(login.html) , 進行登入
// 		return;
// 	}
%>
<%-- --{tripVO==null} --${tripVO.tripno} ${tripVO == null } --%>
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
}
  input {
  background-color: #fff;
  color:#0000FF;
border: 0;

border-radius: 10px;
cursor: pointer;
font-family: "微軟正黑體";
}

 input:hover { 
	color: red; 
 	background-color: #fff; 
 	border: 2px red solid; 
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


<style>
  table {
	background-color:pink;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid orange;
  }
  th, td {
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
  
</style>

</head>
<body style="background-color:white;">
<%-- 	<%@ include file="/navbarNoCSS.file" %> --%>
<%@ include file="/nav-f"%>

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

	
		<div align="center">
		<label><a href='trip_index.jsp'><input type="button"value="回行程主頁" class="return"></a></label> &nbsp&nbsp&nbsp 
		<label><a href='/DA101G3/index.jsp'><input type="button" value="回首頁" class="return"></a></label>
	</div>
	<hr color="pink" align="center" width="14%" size="5px">
	
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
	
	
<div align="center">
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front-end/trip/Trip.do" name="form1" enctype="multipart/form-data">
		<table >
			<jsp:useBean id="tripSvc" scope="page"
				class="com.trip.model.TripService" />

<!-- 			<tr> -->
<%-- 				<td>行程編號</td><td>(<%=tripVO.getTripno()%>) --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td>會員編號</td><td>(<%=tripVO.getMemno()%>) --%>
<!-- 				</td> -->
<!-- 			</tr> -->

<tr>
<td>城市名<font color=red><b>*</b></font></td>
<jsp:useBean id="citySvc" scope="page" class="com.city.model.CityService" />
<td>
原來為 :(<%=citySvc.getOne(tripVO.getCityno()).getCityName()%>)<br>更改為:
<select size="1" name="cityno" style="border-radius: 20px;"> 
<c:forEach var="cityVO" items="${citySvc.all}">
<option value="${cityVO.cityNo}">${cityVO.cityName}
</c:forEach>
</select>
</td>

			</tr>

			<tr>
				<td>行程名稱</td>
<!-- 				<td><input type="TEXT" name="tripname" size="45" -->
<%-- 					value="<%=(tripVO == null) ? "請輸入" : tripVO.getTripname()%>" /></td> --%>
						<td><input type="TEXT" name="tripname" size="45"	value="<%=tripVO.getTripname()%>" /></td>
			</tr>
			<tr>
				<td>啟程日<font color=red><b>*</b></font></td>
				<td><input name="tripstartday" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>結束日<font color=red><b>*</b></font></td>
				<td><input name="tripendday" id="f_date2" type="text"></td>
			</tr>
			<tr>
				<td>天數</td>
				<td><input type="text" name="tripdays" size="20" id="days" style="background-color:#DDDDDD;" readOnly="true" value="<%=tripVO.getTripdays()%>"/><font color="red">(系統自動產生,不可更改)</font></td>
		
			</tr>
<!-- 			<tr> -->
<!-- 				<td>行程創建日<font color=red><b>*</b></font></td> -->
<!-- 				<td><input name="tripestdate" id="f_date3" type="text"></td> -->
<!-- 			</tr> -->

			<tr>
				<td>行程創建日<font color=red><b>*</b></font></td>
				<td ><input name="tripestdate"  value="" id="f_date3" style="background-color:#DDDDDD;" readonly type="text"></td>
			</tr>
			 
			<tr>
					<td>是否成為買家:<font color=red><b>*</b></font></td>
					<td><select size="1" name="bethebuyer" style="border-radius: 20px;">
							<option value="1">我願意
							<option value="0">我不願意
					</select></td>
			</tr>
			<tr>
				<td>這個行程狀態</td><td>
				<c:if test="${tripVO.tripstatus==0}"><c:out value="未公開等待審核中"/></c:if>
				<c:if test="${tripVO.tripstatus==1}"><c:out value="公開行程"/></c:if>
				<c:if test="${tripVO.tripstatus==2}"><c:out value="Oops! 被凍結了"/></c:if>
				</td>
			</tr>
			<tr>
				<td>這個行程觀看次數</td>
				
				<td><input readonly type="text" style="background-color:#DDDDDD;" value="<%=tripVO.getTimeofviews()%>"></td>
			</tr>
			<tr>
				<td>行程類型</td>
					<td>
					原來:&nbsp 
			<c:if test="${tripVO.kindoftrip==0}"><c:out value="單獨旅行"/></c:if>
			<c:if test="${tripVO.kindoftrip==1}"><c:out value="情侶出遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==2}"><c:out value="家庭親子"/></c:if>
			<c:if test="${tripVO.kindoftrip==3}"><c:out value="朋友出遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==4}"><c:out value="商務旅遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==5}"><c:out value="其它"/></c:if>&nbsp &nbsp 要改為: &nbsp 
					<select size="1" name="kindoftrip" style="border-radius: 20px;">
						<option value="0">單獨旅行
						<option value="1">情侶出遊
						<option value="2">家庭親子
						<option value="3">朋友出遊
						<option value="4">商務旅遊
						<option value="5">其它
					</select></td>
			
			</tr>
			
			<tr>
				<td>行程圖片</td>
				<td>
<input type="file" name="trippic" size="45" id="imgInp" accept="image/gif , image/jpeg ,image/png " 
  style="border: 0;background-color: #c00;color: #fff;border-radius: 10px;cursor: pointer;font-family: "微軟正黑體";">
<%-- 	value="<%= (tripVO.getTrippic()!=null)? tripVO.getTrippic() : null %>"	判斷圖片		 --%>


				<c:if test="${tripVO.trippic==null}"><img id="blah" src="<%=request.getContextPath()%>/images/noPic.png" style="border:1px blue dashed;width:100px;border-radius:20px;height:100px;"></c:if>
				<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" id="blah"  style="border-radius:20px;border:1px blue dashed;width:100px;height:100px;"></c:if>
					
				</td>
			</tr>
			
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="tripno" value="<%=tripVO.getTripno()%>">
		<input type="hidden" name="memno" value="<%=tripVO.getMemno()%>">
		<input type="hidden" name="tripstatus" value="<%=tripVO.getTripstatus()%>">
		<input type="hidden" name="timeofviews" value="<%=tripVO.getTimeofviews()%>">
		<input   type="submit" value="繼續修改細節" style="border: 0;background-color: #FF8800;color: #fff;border-radius: 10px;cursor: pointer;font-family: "微軟正黑體";">
	
<%-- 	<form  METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do"> --%>
<!-- 	<input type="hidden" name="action" value="lastone" /> -->
<!-- 	<input type="submit"  value="我還想更改行程細節" style="border: 0;background-color: #FF8800;color: #fff;border-radius: 10px;cursor: pointer;font-family: "微軟正黑體";"/> -->
<!-- 	</form> -->
	</FORM>

	</div>
	
	
	
</body>
<br>
<br>
<br>
<br>
<br>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; 
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; 
}
</style>

<script>
var date=new Date();
var today=date.getFullYear()+ "-"+(date.getMonth()+1)+"-"+date.getDate(); 
$('#f_date3').val(today)


function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#blah').attr('src', e.target.result);
	    }
	    
	    reader.readAsDataURL(input.files[0]);
	  }
	}

	$("#imgInp").change(function() {
	  readURL(this);
	});


$.datetimepicker.setLocale('zh');
$('#f_date1').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    value: '<%=tripVO.getTripstartday()%>' ,
   format:'Y-m-d'         //format:'Y-m-d H:i:s',
  
});
$('#f_date2').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=tripVO.getTripendday()%>'
});
$(function(){
    $('#f_date1').datetimepicker({
       onShow:function(){
       	   this.setOptions({
       	    maxDate:$('#f_date2').val()?$('#f_date2').val():false
       	   })
       	  }
});
    $.datetimepicker.setLocale('zh');
    $('#f_date2').datetimepicker({
       onShow:function(){
       this.setOptions({
       minDate:$('#f_date1').val()?$('#f_date1').val():false
         })
       }
    });
    
    
    });

        
        
        $("#f_date1").change(function(){

            var start=$("#f_date1").val();
             var end=$("#f_date2").val();
             
             start=start.replace(/-/g,"/");
     
             var startdate=new Date(start);
             end=end.replace(/-/g,"/");
             var enddate=new Date(end);
     
             var time=enddate.getTime()-startdate.getTime();
             var days=parseInt(time/(1000 * 60 * 60 * 24))+1;
             console.log(days);
             $("#days").val(days);
         });  
        
        
        
        
        $("#f_date2").change(function(){

            var start=$("#f_date1").val();
             var end=$("#f_date2").val();
             
             start=start.replace(/-/g,"/");
     
             var startdate=new Date(start);
             end=end.replace(/-/g,"/");
             var enddate=new Date(end);
     
             var time=enddate.getTime()-startdate.getTime();
             var days=parseInt(time/(1000 * 60 * 60 * 24))+1;
             console.log(days);
             $("#days").val(days);
         });      
        
</script>


</html>