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
<!-- BootStrap4.3.1 2019/07/28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
body {
	font-family: 微軟正黑體;
	margin: 0 auto;
}
div.container{
border-style: dashed;
width:800px;
height:250px;
}
div.col{
	float: left;
}
</style>
<title>[後台]訂單管理</title>
</head>
<body>
	<%@ include file="/nav-BE"%>
	<div class="container">
			<div style="margin: 0px auto;">
				<h2 style="text-align:center;">訂單管理</h2>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<jsp:useBean id="storeOrdSvc" scope="page"
					class="com.storeOrd.model.StoreOrdService" />
			</div>


			<div class="row">

					<div class="col-3" style="text-align:center;">
						<div class="btn-group-vertical">
							<button type="button" class="btn btn-warning"
								onclick="self.location.href='<%=request.getContextPath()%>/back-end/store/storeOrd_list_all.jsp'">檢視所有訂單</button>
						</div>
					</div>
<!-- 							<button type="button" class="btn btn-success" -->
<!-- 								onclick="self.location.href='storeOrd_add.jsp'">新增訂單</button> -->
				<div class="col-9">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/storeOrd.do">
						<b>輸入訂單編號：</b> 
						<input type="text" name="ordNo"> 
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/storeOrd.do">
						<b>選擇訂單編號：</b> <select size="1" name="ordNo">
							<c:forEach var="storeOrdVO" items="${storeOrdSvc.all}">
								<option value="${storeOrdVO.ordNo}">${storeOrdVO.ordNo}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/store/storeOrd.do"> --%>
<!-- 						<b>選擇會員編號：</b> <select size="1" name="memNo"> -->
<%-- 							<c:forEach var="storeOrdVO" items="${storeOrdSvc.all}"> --%>
<%-- 								<option value="${storeOrdVO.ordNo}">${storeOrdVO.memNo} --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->
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