<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.f_item.model.*"%>
<%@ page import="com.f_item.controller.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem.controller.*"%>
<%
	// 	取得會員編號
	Object SessionMemNo = session.getAttribute("memno");
	String memNo = (String) SessionMemNo;
	// 	取得所有商品
	List<ItemVO> itemVO_list = (List<ItemVO>) request.getAttribute("itemVO_list");
	if (itemVO_list == null) {
		ItemService itemSvc = new ItemService();
		itemVO_list = itemSvc.showStore();
	}
	//取得我的最愛名明細
	F_ItemService f_itemSvc = new F_ItemService();
	HashSet<String> f_item_list = f_itemSvc.findByPrimaryKey(memNo);
	System.out.println(f_item_list.size());
	//取得購物車清單
	LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");
	if (cart == null) {
		cart = new LinkedHashMap<>();
	}
// 	Object session_cart_size = session.getAttribute("cart_size");
	
// 	int cart_size =Integer.parseInt(session_cart_size);
// 	if(cart_size>0){

// 	}else{
// 		cart_size=0;
// 	}
	session.setAttribute("cart", cart);
	
%>
<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- bootstrap[css] 2019-07-28 -->
<title>商城首頁</title>
<style>
img.item_pic {
	height:200px;
	max-width:220px;
}

div#itemName {
	height: 50px;
}

div#itemDetail {
	height: 50px;
}

div.container {
	width: 1800px;
}

nav {
	
}

#mycart {
	z-index: 99999;
	position: fixed; /*固定在網頁上不隨卷軸移動，若要隨卷軸移動用absolute*/
	top: 20%; /*設置垂直位置*/
	right: -40px; /*設置水平位置，依所放的內容多寡需要自行手動調整*/
	background: transparent; /*背景顏色*/
	padding: 10px 20px;
	border-radius: 10px; /*圓角*/
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}


div.img {
	height: 100%;
	width: 100%;
	text-align: center;
}

body {
	margin: 0px auto;
	font-family: 微軟正黑體 !important;
}
</style>

</head>
<body bgcolor='white'>

	<!-- NavBar -->
	<%@ include file="/nav-f1"%>
	<div class="container">

		<table id="table-1">
			<tr>
				<td>
					<a href="<%=request.getContextPath()%>/front-end/store/item_list_all.jsp"><h1 style="display: inline;">旅遊商品</h1></a>
					<div class="row">
									<div class="col">
											<div class="form-group">
											
												<form class="item_class" METHOD="post"
													ACTION="<%=request.getContextPath()%>/store/cart.do">
													<select class="form-control" id="exampleFormControlSelect1"
														name="itemClass" onchange="this.form.submit()">
														<option value="0">請選擇商品類別</option>
														<option value="1">旅用收納</option>
														<option value="2">旅用舒眠紓壓</option>
														<option value="3">貼身用品</option>
														<option value="4">SIM卡、WIFI機</option>
														<option value="5">3C商品</option>
														<option value="6">其他</option>
														<input type="hidden" name="action" value="showStore_byclass">
													</select>
												</form>
										
											</div>
									</div>	
										<form class="form-inline" METHOD="post"
											ACTION="<%=request.getContextPath()%>/store/cart.do">
											<input type="hidden" name="action" value="search_item">
											<input class="form-control mr-sm-2" name="keyWord" type="search"
												placeholder="搜尋商品" aria-label="Search">
											<button class="btn btn-outline-success my-2 my-sm-0"
												type="submit">Search</button>
										</form>
					</div>
					<FORM style="display: inline;" id="mycart" name="mycart"
						METHOD="post" ACTION="<%=request.getContextPath()%>/store/cart.do">

						<input type="hidden" name="action" value="show_cart"> <font
							id="count" style="color: red"font-size:40px; > <%=cart.size()%></font>樣商品<br>
						<input type="image" name="submit_Btn" id="submit_Btn" img
							src="<%=request.getContextPath()%>/front-end/store/images/cart-4.png"
							onClick="document.mycart.submit()">
					</FORM>
				</td>
				
			</tr>
		</table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="row">
			<%
				for (ItemVO itemVO : itemVO_list) {
			%>
			<div class="col-3">
				<div class="card" style="width: 18rem;">
					<div class="img">
						<img class="item_pic"
							src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=<%=itemVO.getItemNo()%>" />
					</div>
					<div class="card-body">
						<div id="itemName">
							<h5 class="card-title" name="itemName">
								☆<%=itemVO.getItemName()%></h5>
						</div>
						<div id="itemDetail">
							<p class="card-text"><font color="#888888"><%=itemVO.getItemDetail()%></font></p>
						</div>
						<div>
							<p class="card-text">
								<font size="4px">單價:</font><font color="red" size="5px"><%=itemVO.getPrice()%></font>元 <font id="<%=itemVO.getItemNo()%>">
									<%
										if (f_item_list.contains(itemVO.getItemNo())) {
									%> <input type="image"
									onclick="del_f_item('<%=memNo%>','<%=itemVO.getItemNo()%>','<%=itemVO.getItemName()%>');"
									img
									src="<%=request.getContextPath()%>/front-end/store/images/heart.png"
									title="移除我的最愛"> <%
 	} else {
 %> <input type="image"
									onclick="add_f_item('<%=memNo%>','<%=itemVO.getItemNo()%>','<%=itemVO.getItemName()%>');"
									img
									src="<%=request.getContextPath()%>/front-end/store/images/heart2.png"
									title="加入我的最愛"> <%
 	}
 %>
								</font>
							</p>


							<FORM class="items" METHOD="get"
								ACTION="<%=request.getContextPath()%>/store/cart.do">
								<font size="4px">數量</font><select name="quantity" class="form-control" id="exampleFormControlSelect2" style="width:90px;display:inline;">
									<%
										for (int i = 1; i <= (itemVO.getAmount()); i++) {
									%>
									<option value=<%=i%>><%=i%></option>
									<%
										}
									%>
								</select> <input type="hidden" name="itemNo"
									value="<%=itemVO.getItemNo()%>"> <input type="hidden"
									name="action" value="add_cart"> <input
									class="btn btn-primary" type="submit" value="拉進購物車！">
							</FORM>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
<!-- AjaxForm -->
<script src="<%=request.getContextPath()%>/front-end/CDN/jquery.form.js"></script>
<!-- Alert樣式 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css" rel="stylesheet">
<script src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
</body>
<script>
// ajaxForm
	$(document).ready(function() {
		$(".items").ajaxForm(function(data) {
			// 						alertify.success('幹幹幹');
			// 			alertify.error('Hi! 這是錯誤的訊息通知！');  
			alertify.log('拉進購物車！');
			$("#count").load(location.href + " #count");
<%-- 						$('#count').text(<%=cart.size()%>); --%>
		});
	});
	
	
// 	$(document).ready(function() {
// 		$(".item_class").ajaxForm(function(data) {
// 			// 						alertify.success('幹幹幹');
// 			// 			alertify.error('Hi! 這是錯誤的訊息通知！');  
// 			alertify.log('拉進購物車！');
			
// 			$("#count").load(location.href + " #count");
<%-- 			$('#count').text(<%=cart.size()%>); --%>
			
// 		});
// 	});

	
	
	
	
//加入我的最愛
 function add_f_item(memNo,itemNo,itemName) {
	 var exp =null;
	if(memNo==="null"){
		location.href='http://localhost:8081<%=request.getContextPath()%>/login.jsp'
	}else{

	$.ajax({
			url: 'http://localhost:8081<%=request.getContextPath()%>/store/f_item.do',
				type: 'post',
				dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
				async: false,
				data: {memNo:memNo,itemNo:itemNo,action:'add'}
			})
			 	$("#"+itemNo).load(location.href + " #"+itemNo);
			alertify.log(itemName);
			alertify.error("加入我的最愛");
		};
	}
//移除我的最愛
 function del_f_item(memNo,itemNo,itemName) {	

		$.ajax({
			url: 'http://localhost:8081<%=request.getContextPath()%>/store/f_item.do',
						type : 'post',
						dataType : 'default: Intelligent Guess (Other values: xml, json, script, or html)',
						async : false,
						data : {
							memNo : memNo,
							itemNo : itemNo,
							action : 'del'
						}
					})
			$("#" + itemNo).load(location.href + " #" + itemNo);
			alertify.log(itemName);
			alertify.error("移除我的最愛");
}
</script>
</html>