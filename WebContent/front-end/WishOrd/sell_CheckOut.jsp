<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishitem.model.*"%>

<%
	List<WishItemVO> wishBuyList = new ArrayList<WishItemVO>();
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
 		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
 		return;
 	}
	if(session.getAttribute("buyList")!=null) {
		wishBuyList = (ArrayList)session.getAttribute("buyList");
		pageContext.setAttribute("wishBuyList", wishBuyList);
	}

	pageContext.setAttribute("memNo", memNo);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="icon" href="kingoftravel.ico" type="image/x-icon" />
<title>遊記王_許願願池首頁</title>
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

h3 {
	font-family: "微軟正黑體";
	font-style: italic;
	font-weight: bold;
}
table,th,td{
border:1px pink solid;
}
</style>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/js/address.js"></script>
</head>
<body bgcolor='white'>

<%@ include file="/nav-f1"%>
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
			<li class="breadcrumb-item"><a href="#">緯明不要</a></li>
			<li class="breadcrumb-item"><a href="#">瑋齊不行</a></li>
			<li class="breadcrumb-item"><a href="#">奕伸大麻</a></li>
		</ol>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-3">
				<div class="nav flex-column nav-pills" id="v-pills-tab"
					role="tablist" aria-orientation="vertical">
					<a class="nav-link" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">回代購首頁</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_sellList.jsp">代購商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_buyList.jsp">託買商品專區</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/listMyWishItem.jsp">我的託買及代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/add_WishItem.jsp">新增許願代購商品</a>	
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishOrd/listMyWishOrd.jsp">我的訂單</a>							
				</div>
			</div>
			<div class="col-9">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/WishOrd/wishOrd.do">
					<div class="row">
						<table>
							<tr>
								<th>代購商品圖片</th>
								<th>代購商品名稱</th>
								<th>代購會員</th>
								<th>請求會員</th>
								<th>商品數量</th>
								<th>單筆金額</th>
								<th>總金額</th>
							</tr>
							<%
								int i = 0;
								int sellTotal = 0;
							%>
							<c:forEach var="wishBuyItemVO" items="${wishBuyList}">
								<tr>
									<td><img src="<%=request.getContextPath()%>/getDatabaseServlet.do?wishItemNo=${wishBuyItemVO.wishItemNo}"
									style="width: 250px ;height:200px;"></td>
									<td>${wishBuyItemVO.itemName}</td>
									<td>${memNo}</td>
									<td>${wishBuyItemVO.memNo}</td>
									<td>${wishBuyItemVO.amount}</td>
									<td>${wishBuyItemVO.itemPrice}</td>
									<td>${wishBuyItemVO.itemPrice*wishBuyItemVO.amount}</td>

								</tr>
								<%									
								sellTotal = sellTotal + wishBuyList.get(i).getItemPrice()*wishBuyList.get(i).getAmount();
								i++;
								%>
							</c:forEach>
						</table>
					</div>
					<br>
					<br>
					<br>
					<br>
					<br>
						<% i = 0;%>
					<div class="container" align="left">
						<div align="left" style="width: 60%">
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<!-- Start Sign In Form -->

							<h2>代購訂單資訊填寫</h2>
							<table>
								<tr>
									<td>請求會員:<font color=red><b>*</b></font></td>
									<td><input type="text" class="form-control" id="buyMemNo1" name="buyMemNo1"
										value="<%=(wishBuyList.get(i).getMemNo() == null) ? "MEM0001" :wishBuyList.get(i).getMemNo()%>" 
										required readonly/></td>
								</tr>
								<tr>
									<td>托買會員:<font color=red><b>*</b></font></td>
									<td><input type="text" class="form-control" id="wishMemNo1" name="wishMemNo1"
										value="<%=(memNo == null) ? "MEM0001" : memNo%>"
										required readonly/></td>
								</tr>
								<tr>
									<td>訂單狀態:<font color=red><b>*</b></font></td>
									<td><input type="hidden" name="wishOrdStatus1" id="wishOrdStatus1" value="0">
									<input type="text" class="form-control"  readonly	value="申請中" 
										required readonly/>
								</tr>
								<tr>
									<td>應收取總金額:</td>
									<td><input type="text" class="form-control" id="price1" name="price1"
										value="<%= sellTotal%>" 
										required readonly/></td>
								</tr>
								<tr>
									<td>預計寄出商品日期:<font color=red><b>*</b></font></td>
									<td><input name="wishSendDate1" id="wishSendDate1" type="text"/></td>
								</tr>
								<tr>
									<td>代購但書:<font color=red><b>*</b></font></td>
									<td><input type="text" class="form-control" id="buyNote1" name="buyNote1"
										/></td>
								</tr>
								<tr>
									<td>最後交貨商品日期:<font color=red><b>*</b></font></td>
									<td><input name="lastDate1" id="lastDate1" type="text"/></td>
								</tr>
								<tr>
									<td>地址:<font color=red><b>*</b></td>
									<!-- 							<input type="text" class="form-control" id="address" placeholder="address" autocomplete="off" -->
									<%-- 							name="address" value="<%= (memVO==null)? "桃園市中壢區中大路300號" : memVO.getAddress()%>" /> --%>
									<td><select id="zone1" name="zone1" style="width: 110px;"></select>
										<select id="zone2" name="zone2" style="width: 110px;"></select>
										<input type="text" id="zipcode" name="zipcode"
										style="width: 30px;" /> <input type="text" name="address1"
										value="中正路一段一號1樓" style="width: 200px;">
									<p></td>
								</tr>							
							</table>
						</div>
					</div>
					<div align="center">
					<input type="hidden" name="action" value="sellcheckout">
        			<input type="submit" name="type" value="確認訂單成立">
        			</div>
				</form>
			</div>
			</div>
	</div>
	
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
	</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<script type="text/javascript">
		$(function() {
			init_address();
			$("#zone1").val('新北市'); //縣市
			for ( var i in zip['新北市']) {
				o = document.createElement('option');
				o.text = i;
				o.value = i;
				zone2.options.add(o);
			}
			$("#zone2").val('三重區'); //鄉鎮市區
			$("input[name='zipcode']").val('241'); //郵遞區號
		})
	</script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath() %>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	$('#wishSendDate1').datetimepicker({
   			theme: '',              //theme: 'dark',
   			timepicker:false,       //timepicker:tr                //step: 60 (這是timepicker的預設間隔60分鐘)
   			format:'Y-m-d',         //format:'Y-m-d H:i:s',
  			//   			startDate: new Date(),
            value:new Date()
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$('#lastDate1').datetimepicker({
   		theme: '',              //theme: 'dark',
    	timepicker:false,       //timepicker:tr                //step: 60 (這是timepicker的預設間隔60分鐘)
    	format:'Y-m-d',         //format:'Y-m-d H:i:s',
		//     startDate: new Date(),
		value:   new Date()
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
    	//startDate:	            '2017/07/10',  // 起始日
    //minDate:               '-1970-01-01', // 去除今日(不含)之前
    //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
 });

$(function(){
    $('#wishSendDate1').datetimepicker({
       onShow:function(){
		var minwish=$('#wishSendDate1').val();
		var newdate=new Date(minwish).getTime()+86400*7000;
    	   this.setOptions({
       		   minDate:newdate
       	 
       	   })
       	  },
});
    $.datetimepicker.setLocale('zh');
    $('#lastDate1').datetimepicker({
       onShow:function(){
    	   
    	 var minwish=$('#wishSendDate1').val();
   		var newdate=new Date(minwish).getTime()+86400*14000;
       	   this.setOptions({
          		   minDate:newdate
         })
       },
    });
    
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