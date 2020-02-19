<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointgoods.model.*"%>
<%@ page import="com.mem.model.*"%>
<%! @SuppressWarnings("unchecked") %>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"/>
 	<title></title>
<script> 	
 	$(document).ready(function() {
		$(".btn-delete").click(function() {
			var btn = this;
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/pointgoods/pointgoods.do",
				data : $(this).parent().serialize(),
				dataType : "json",
				success : function(res) {
					var cartamount = res.cartamount
					$("#tdtotal").text('$'+cartamount);
					$(btn).parents("tr").remove();
					if(res.cartamount==0){
						alert("目前購物車空空如也");
						window.location.href="http://localhost:8081/DA101G3/front-end/pointgoods/listAllPointGoodsF.jsp"};
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		})
		$("select").change(function(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/pointgoods/pointgoods.do",
				data : createQueryString($(this).val(), $(this).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartamount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
		$(".form-control").change(function(e){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/pointgoods/pointgoods.do",
				data : createQueryString($(this).val(), $(this).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartamount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
		$(".btn").click(function(){
			var put;
			if(this.name=='add'){
			put = $(this).parent().prev();
			var value = parseInt($(this).parent().prev().val(), 10);
			value = isNaN(value) ? 0 : value;
			value++;
			put.val(value);
			}else{
				put = $(this).parent().next();
				var value = parseInt($(this).parent().next().val(), 10);
				value = isNaN(value) ? 0 : value;
				value <= 1 ? value = 1 : value--;
				put.val(value);
			}
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/pointgoods/pointgoods.do",
				data : createQueryString($(put).val(), $(put).attr('name')),
				dataType : "json",
				success : function(res) {
					$("#tdtotal").text("$"+res.cartamount);
				},
				error : function() {
					alert("Sorry, there was a problem!");
				}
			});
		});
	});
	function createQueryString(quantity, pgno){
		var queryString= {"action":"modifyq", "pointgoodsquantity":quantity, "pointgoodsno":pgno};
		return queryString;
	}
</script>
<style>
table{
	width:720px;
	margin-left:auto; 
	margin-right:auto;
}
#emptycart{
	font-size:72px;
	font-weight:bold;
	color:#ff8080;
	margin-left:auto; 
	margin-right:auto;
}
#tdtotal{
	color:red;
	font-weight:bold;
}
.form-control{
	width:20px;
}
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
  -webkit-appearance: none; 
  margin: 0; 
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
th{
	text-align:center;
}
table img{
	width:100px;
	height:60px;
}

</style>	
</head>
<body bgcolor="#FFFFFF">
<%@ include file="/navbarNoCSS.file" %>
<img src="<%=request.getContextPath()%>/images/pointgoods/addtocart.png" id="imgflow">
<%--錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<p>

	<%  Object account = session.getAttribute("account"); 
		MemVO memVO = (MemVO) session.getAttribute("accountVO");
	 	 if (account == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
			session.setAttribute("location", request.getRequestURI()); 
			response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			return;
		}
		Vector<PointGoodsVO> buylist = (Vector<PointGoodsVO>) session.getAttribute("shoppingcartp");
		String amount =  String.valueOf(session.getAttribute("total"));
	%>	
	<%	if(buylist != null && buylist.size() != 0){ %>
		<table>
		<tr>
			<th>積分商品編號</th>
			<th>積分商品圖片</th>
			<th>積分商品名稱</th>
			<th>需求點數</th>
			<th width="120px">數量</th>
			<th></th>
		</tr>
	<% 	for (int i = 0; i < buylist.size(); i++) {
			PointGoodsVO order = buylist.get(i);
			String pointgoodsno = order.getPointgoodsno();
			String pointgoodsname = order.getPointgoodsname();
			int needpoints = order.getNeedpoints();
			int pointgoodsquantity = order.getPointgoodsquantity();
	%>
	<tr>
		<td><div align="center"><b><%=pointgoodsno%></b></div></td>
		<td align="center"><img alt="img" src="<%=request.getContextPath()%>/pointgoods/showpic.do?pointgoodsno=<%=order.getPointgoodsno()%>"/></td>
		<td><div align="center"><b><%=pointgoodsname%></b></div></td>
		<td><div align="center"><b><%=needpoints%></b></div></td>
		<%-- <td><div align="center"><b><%=pointgoodsquantity%></b></div></td> --%>
		<%-- <td><select name="<%=pointgoodsno%>">	
			<%for(int j = 1; j <= 10; j++) { 
				if(pointgoodsquantity == j) {%>
				<option value="<%=j%>" selected><%=j%></option>
				<%}else{%>
				<option value="<%=j%>"><%=j%></option>
					<%}
				}%>
		</select></td>--%>
			<td width="80px">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon1" name="minus">-</button>
					</div>
					<input type="number" class="form-control" placeholder=""
						aria-label="Example text with button addon"
						aria-describedby="button-addon1" value="<%=pointgoodsquantity%>" name="<%=pointgoodsno%>">
					<div class="input-group-prepend">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon2" name="add">+</button>
					</div>	
				</div>
			</td>
			<td><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/front-end/pointgoods/pointgoods.do" method="POST">
              <input type="hidden" name="action" value="remove">
              <input type="hidden" name="remove" value="<%= pointgoodsno %>">
              <input type="button" class="btn-delete" value="取消">
          </form>
        </div></td>
	</tr>
	<%}%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>兌換點數：</b></font></div></td>
		<td id="tdtotal"> $<%=amount%></td>
		<td></td>
	</tr>
		<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>點數餘額：</b></font></div></td>
		<td id="tdtotal"> $<%=(memVO.getPoints() == null)? "0": memVO.getPoints()%></td>
		<td></td>
	</tr>
</table>
	<%}else{%>
	<div id="emptycart">目前購物車空空如也喔</div>	
	<% response.sendRedirect("/DA101G3/front-end/pointgoods/listAllPointGoodsF.jsp");}%>
<div>
<button onclick="location.href='<%=request.getContextPath()%>/front-end/pointgoods/listAllPointGoodsF.jsp'">繼續購物</button>
<%-- <button onclick="location.href='<%=request.getContextPath()%>/front-end/pointgoodsord/ordconfirm.jsp'">結帳</button> --%>
<button onclick="location.href='<%=request.getContextPath()%>/pointgoods/pointgoods.do?action=checkmoney'">結帳</button>
</div>	

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" 
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" 
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
