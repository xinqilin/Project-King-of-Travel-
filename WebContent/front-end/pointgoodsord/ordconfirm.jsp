<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoods.model.*"%>
<%@ page import="com.pointgoodsord.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
	<script src="<%=request.getContextPath()%>/js/jquery.twzipcode.min.js"></script>
<title>填寫訂單資料</title>

<style type="text/css">
table{
	margin-left:auto;
	margin-right:auto;
}
.table1>tbody>tr>td, .table1>tbody>tr>th{
	text-align:center;
	width:120px;
	white-space:nowrap;
	text-overflow:ellipsis;
}
th{
	background-color:#e6f2ff;
	border-top:2px #ccccff solid;
	border-bottom:2px #ccccff dashed;
	border-right:2px white solid;
}
td{
	border-bottom:2px #ccccff dashed;
	border-right:2px white solid;
}
#st{
	margin-left:auto;
	margin-right:auto;
	text-align: center;
}
#dtable.td{
	text-align:left;
}
.table2>tbody>tr{
	width:600px;
}
#imgflow {
	display: block;
	margin-left: auto;
	margin-right: auto;
	max-width: 350px;
	max-height: 161px;
	width: auto;
	height: auto;
}

</style>

</head>
<body>
<%@ include file="/navbarNoCSS.file" %>
	<img src="<%=request.getContextPath()%>/images/pointgoods/filldata.png" id="imgflow"/>
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>	
		</c:forEach>
	</ul>
</c:if>
	<%
		Object account = session.getAttribute("account");
		MemVO memVO = (MemVO) session.getAttribute("accountVO");
		if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
			session.setAttribute("location", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		PointGoodsOrdVO pointgoodsordVO = (PointGoodsOrdVO) request.getAttribute("pointgoodsordVO");
		Vector<PointGoodsVO> buylist = (Vector<PointGoodsVO>) session.getAttribute("shoppingcartp");
		String amount = String.valueOf(session.getAttribute("total"));

		String county, district, address, taddress;
		Set<String> cd = new HashSet<String>();
		cd.add("鄉");
		cd.add("鎮");
		cd.add("市");
		cd.add("區");
		cd.add("島");
		if (pointgoodsordVO != null) {
			county = (String) request.getAttribute("county");
			district = (String) request.getAttribute("district");
			address = (String) request.getAttribute("road");
		} else {
			taddress = memVO.getAddress();
			county = taddress.substring(0, 3);
			if (taddress.substring(4, 5).equals("區")) {
				district = taddress.substring(3, 5);
				address = taddress.substring(5);
			} else if (cd.contains(taddress.substring(5, 6))) {
				district = taddress.substring(3, 6);
				address = taddress.substring(6);
			} else if (cd.contains(taddress.substring(6, 7))) {
				district = taddress.substring(3, 7);
				address = taddress.substring(7);
			} else {
				district = taddress.substring(3, 8);
				address = taddress.substring(8);
			}
		}
	%>
 <div id='st'>
 	<span>本次兌換總計</span><span><%=amount%></span>
 	<a class="" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    	展開明細
    </a>
</div>
<div class="collapse" id="collapseExample">
  <div class="card card-body">	
	
	<%	if(buylist != null && buylist.size() != 0){ %>
		<table class="table1">
		<tr>
			<th>積分商品編號</th>
			<th>積分商品名稱</th>
			<th>數量</th>
			<th>點數</th>
			<th>點數小計</th>
		</tr>
	<% 	for (int i = 0; i < buylist.size(); i++) {
			PointGoodsVO order = buylist.get(i);
			String pointgoodsno = order.getPointgoodsno();
			String pointgoodsname = order.getPointgoodsname();
			int needpoints = order.getNeedpoints();
			int pointgoodsquantity = order.getPointgoodsquantity();
	%>
	<tr>
		<td><div align="center"><%=pointgoodsno%></div></td>
		<td><div align="center"><%=pointgoodsname%></div></td>
		<td><div align="center"><%=pointgoodsquantity%></div></td>
		<td><div align="center"><%=needpoints%></div></td>
		<td><div align="center"><%=needpoints*pointgoodsquantity%></div></td>

			
	</tr>
	<%}
	}%>
	</table>
	 </div>
</div>
	<table class="table2">
		<%-- <tr>
		<td rowspan="3">購<br>買<br>人</td>
		<td>姓名&nbsp;<input type="text"></td></tr>
		<tr><td>手機&nbsp;<input type="text"></td></tr>
		<tr><td width="600px">地址
		<form class="form-inline" role="form"><div class="twzipcode"></div></form>
		<input type="text">
		</td></tr>
		<tr><td><br></td></tr>
		<tr><td><br></td></tr>--%>
		
		<tr>
		<td rowspan="3">收<br>件<br>人</td>
		<td>姓名<input type="text" id="receiversub" value="<%=(pointgoodsordVO==null)?memVO.getMemName():pointgoodsordVO.getReceiver()%>"></td></tr>
		<tr><td>手機<input type="text" id="phonesub" value="<%=(pointgoodsordVO==null)?memVO.getPhone():pointgoodsordVO.getPhone()%>"></td></tr>
		<tr><td>地址
		<form class="form-inline" role="form"><div class="twzipcode" id='zip1'></div></form>
		<input type="text" id="addresssub" value="<%=address%>">
		</td></tr>
		<tr>
		<td colspan="2"><form method="GET" action="<%=request.getContextPath()%>/pointgoodsord/pointgoodsord.do">
		<input type="hidden" name="action" value="insert">
		<input type="hidden" id="memno" name="memno" value="<%=memVO.getMemNo()%>">
		<input type="hidden" id="receiver" name="receiver" value="">
		<input type="hidden" id="phone" name="phone" value="">
		<input type="hidden" id="county" name="county" value="">
		<input type="hidden" id="district" name="district" value="">
		<input type="hidden" id="road" name="road" value="">
		<input type="submit" id="subm" value="確認送出" class="btn btn-primary">
	</form></td></tr>
	</table>
	
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function() {
		$('#subm').click(function(){
			$('#receiver').val($('#receiversub').val());
			$('#phone').val($('#phonesub').val());
			$('#county').val($("select:nth-child(1) option:selected").val());
			$('#district').val($("select:nth-child(2) option:selected").val());
			$('#road').val($("#addresssub").val());
			
		});
	});
		$('.twzipcode').twzipcode({
			countySel: "<%=county%>",
			districtSel:"<%=district%>",
			zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
			css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱 
			});
	</script>

</body>
</html>