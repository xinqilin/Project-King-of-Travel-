<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Object backaccount = session.getAttribute("backaccount"); // 從 session內取出 (key) account的值
if (backaccount == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/login_back.jsp"); 
	return;
}

%>
<html>
<head>
<meta charset="utf-8">
<!-- BootStrap4.3.1 2019/07/28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>[後台]商城</title>
<style>
body {
	font-family: 微軟正黑體;
	margin: 0 auto;
}
div.container{
border-style: dashed;
width: 800px;
height:250px;
}
div.col{
	float: left;
}

</style>
</head>
<body>
<%@ include file="/nav-BE" %> 

	<div class="container">
	<div style="margin: 0px auto;">
		<h2 style="text-align:center;">商品管理</h2>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}
				</c:forEach>
		</c:if>
		<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
	</div>




		<div class="row">

				<div class="col-3" style="text-align:center;">
						<div class="btn-group-vertical">
							<button type="button" class="btn btn-warning"
								onclick="self.location.href='item_list_all.jsp'">檢視所有商品</button>
							<button type="button" class="btn btn-success"
								onclick="self.location.href='item_add.jsp'">新增商品</button>
						</div>
				</div>
						
				<div class="col-9">

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/item.do">
					<b>輸入商品編號：</b> 
					<input type="text" name="itemNo">
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="送出">
					</FORM>

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/item.do">
					<b>選擇商品編號：</b> 
					<select size="1" name="itemNo">
					<c:forEach var="itemVO" items="${itemSvc.all}">
						<option value="${itemVO.itemNo}">${itemVO.itemNo}
					</c:forEach>
					</select> 
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
					</FORM>

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/item.do">
						<b>選擇商品名稱：</b>
						<select size="1" name="itemNo">
							<c:forEach var="itemVO" items="${itemSvc.all}">
								<option value="${itemVO.itemNo}">${itemVO.itemName}</option>
							</c:forEach>
						</select> 
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
						
					</FORM>
					
				</div>
				
		</div>

	</div>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
</html>