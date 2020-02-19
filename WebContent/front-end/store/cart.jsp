<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem.controller.*"%>
<%
	//會員登入驗證(開啟)
	Object account = session.getAttribute("account");
	Object memNo = session.getAttribute("memno");
	Object total = session.getAttribute("total");
	if (account == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
	//取得會員VO
	MemVO accountVO = (MemVO)session.getAttribute("accountVO");
%>
<%
	LinkedHashMap<String, Integer> cart = (LinkedHashMap<String, Integer>) session.getAttribute("cart");
	if (cart == null) {
		cart = new LinkedHashMap<>();
		session.setAttribute("cart", cart);
	}
%>

<!DOCTYPE html>
<html>
<head>
<!-- bootstrap[css] 2019-07-28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- bootstrap[css] 2019-07-28 -->
<title>購物車</title>
<style>
div.container{
width:800px;
}
img#creadit_pic{
height:25px;
}
img{
	max-height: 240px;
 }
table { 
  border:1px solid #000; 
  font-size:16px; 
  width:800px;
  border:1px solid #000;
  text-align:center;
  border-collapse:collapse;
} 
th { 
  background-color: #009FCC;
  padding:10px;
  border:1px solid #000;
  color:#fff;
} 
td { 
  border:1px solid #000;
  padding:5px;
} 
body {
	font-family: 微軟正黑體 !important;
}
h4 {
	color: blue;
	display: inline;
}
div.cus_info {
	max-width: 960px;
	margin: auto;
}
</style>
</head>
<body>
  	<%@ include file="/nav-f1"%>
	<div class="container" id="cus_info">
	<table>
		<tr>
			<th>商品名稱</th>
			<th>數量</th>
			<th>單價</th>
			<th>小計</th>
			<th>可買量</th>
			<th></th>
		</tr>

		<c:forEach var="cart" items="${cart}">
			<tr>
				<td style="text-align:left;">${(cart.key).itemName}</td>
				<td>
					<FORM class="AAAA" METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/cart.do">
						<select class="form-control"
							onchange="aaaa('${cart}','${(cart.key).itemNo}',$(this).val(),'${(cart.key).price}');"
							name="quantity">
							<option value="${cart.value}">${cart.value}</option>
							<c:forEach var="i" begin="1" end="${(cart.key).amount}" step="1">
								<option value=${i}>${i}</option>
							</c:forEach>
						</select>
					</FORM>
				</td>
				<td>${(cart.key).price}</td>
				<td id="${(cart.key).itemNo}" class="total">${((cart.key).price)*(cart.value)}</td>
				<td>${(cart.key).amount}</td>
				<td>
					<FORM METHOD="post" class="BBBB"
						ACTION="<%=request.getContextPath()%>/store/cart.do"
						style="margin-bottom: 0px;">
						<input type="hidden" name="itemNo" value="${(cart.key).itemNo}">
						<input class="form-control" type="hidden" name="action" value="delete"> <input
							type="submit" value="取消">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	</br>

	
		<FORM name="ord_detail_form" METHOD="post"
			ACTION="<%=request.getContextPath()%>/store/storeOrdFE.do">
		<div class="row">
		<h5>　收件者：</h5>
		<input id="name" type="text" style="width:130px; height: 30px;display:inline;" class="form-control" id="Name"
				value="${accountVO.memName}" maxlength="30" name="name" required>
		</div>
		<div class="row">
		<h5>行動電話：</h5>
		<input id="phoneNum" type="tel" style="width: 130px; height: 30px;display:inline;" class="form-control"
				value="${accountVO.phone}" maxlength="10" name="phoneNum" required>
		</div>
		
		<div class="row">
		<h5>電子郵件：</h5>
		<input id="e_mail" type="text" style="width: 300px; height: 30px;display:inline;" class="form-control"
				value="${accountVO.e_mail}" maxlength="50" name="e_mail" required>
		</div>
		
		<div class="row">
				<h5>收件地址：</h5>
				<input style="display:inline;" name="address" value="${accountVO.address}" class="twaddress" />
		</div>	

			<div class="row">
	
			<h5>付款方式：</h5>
			
				<div style="float:left;">
						
					<div class="custom-control custom-radio">

						<input id="credit" name="paymentMethod" type="radio" class="custom-control-input" value="1">
							
						<label class="custom-control-label" for="credit">信用卡</label>
						<img id="creadit_pic" src="<%=request.getContextPath()%>/front-end/store/images/credit_card.png"> 
					</div>
				</div>
				<div>　</div>
				<div style="float:left;">
					<div class="custom-control custom-radio">
						<input id="debit" name="paymentMethod" type="radio" class="custom-control-input" value="2">
						<label class="custom-control-label" for="debit">貨到付款</label>
					</div>
				</div>
			</div>
			


			<div ID="m1" >
				<div  class="row" style="text-align: center">
					<h5 class="mb-3">信用卡號：</h5>
					</br> <input class="form-control" type=text name=pan_no1 value=""
						maxlength=4 style="text-align: center; width: 100px;"
						onKeyUp="setBlur(this,'pan_no2');"> <input
						class="form-control" type=text name=pan_no2 value="" maxlength=4
						style="text-align: center; width: 100px;"
						onKeyUp="setBlur(this,'pan_no3');"> <input
						class="form-control" type=text name=pan_no3 value="" maxlength=4
						style="text-align: center; width: 100px;"
						onKeyUp="setBlur(this,'pan_no4');"> <input
						class="form-control" type=text name=pan_no4 value="" maxlength=4
						style="text-align: center; width: 100px;">
				</div>


				<div class="row">
					<h5 class="mb-3">　檢核碼：</h5>
					</br> <input type="text" class="form-control" id="cc-cvv" size="3"
						maxlength=3 placeholder=""
						style="text-align: center; width: 100px;">

					<h5 class="mb-3">有效期限：</h5>
					</br> <input type="text" class="form-control" id="cc-cvv" size="2"
						maxlength=2 placeholder="MM"
						style="text-align: center; width: 70px;"> <input
						type="text" class="form-control" id="cc-cvv" size="2" maxlength=2
						placeholder="YY" style="text-align: center; width: 70px;">
				</div>
			</div>
			<div class="row">
			<h3>總金額：</h3><h3 id="total"  style="color:red;">${total}</h3><h3>元</h3><h6><a href="<%=request.getContextPath()%>/front-end/store/item_list_all.jsp">(回賣場繼續選購)</a></h6>
			<input type="hidden" name="action" value="check_out">
			<button id="check_out" class="btn btn-primary btn-lg btn-block"
				onclick="return false" type="submit">送出訂單</button>
			</div>
		</FORM>

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
<!-- 台灣地址 -->
<script src="<%=request.getContextPath()%>/front-end/CDN/twzip.js"></script>
</body>

<script>
$(document).ready(function() {
	$(".AAAA").ajaxForm(function(data) {
// 		alertify.success('拉進購物車！');  
//			alertify.error('Hi! 這是錯誤的訊息通知！');  
//			alertify.log('Hi! 這是標準的訊息通知！');  
	});
});

function aaaa(cart,itemNo,quantity,price){
// 	alertify.success('更改成功'); 
	$.ajax({
		url: 'http://localhost:8081<%=request.getContextPath()%>/store/cart.do',
		type: 'post',
		dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
		async: false,
		data: {cart:cart,itemNo:itemNo,quantity:quantity,action:'update_cart'}
	})
	$("#"+itemNo).html(price*quantity);
	
	
	var total = 0;
    $(".total").each(function(){
      total += parseInt($(this).text())
    });
    $('#total').text(total);
// 	$("#total").load(location.href + " #total");
// 	$("#total").html(price*quantity);
}



$(document).ready(function(){     
    $("#check_out").click(function(){

    	var phoneNum=$("#phoneNum").val();
    	
        if($("#total").text()==""||$("#total").text()=="0"){
        	alertify.error("購物車中仍無商品唷");     
        }else if($("#name").val()==""){
        	alertify.error("請輸入收件者姓名");
        }    
        else if(!(/^09\d{8}$/.test(phoneNum))){
        	alertify.error("行動電話格式錯誤");
        }
        else if($("#address").val()==""){
        	alertify.error("請輸入收件者地址");
        }else if(typeof(method =$("input[name='paymentMethod']:checked").val())=="undefined"){
        	alertify.error("選取付款方式");
        }else{document.ord_detail_form.submit();
        }
    })
 })

// 信用卡卡號自動跳下欄位
$('input').keyup(function(e){   
    if($(this).val().length==$(this).attr('maxlength'))   
    $(this).next(':input').focus();   
});  
//隱藏顯示信用卡
$("#m1").hide();
$(document).ready(function() {
    $('input[type=radio][name=paymentMethod]').change(function() {
        if (this.value == 1) {
        	$("#m1").show();
        }
        else if (this.value == 2) {
        	$("#m1").hide();
        }
    });
});

</script>
</html>