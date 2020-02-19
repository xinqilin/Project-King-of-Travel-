<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>CountryPage</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM Country: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Country: Home</p>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllCountry.jsp'> List</a> all Countries.<br>
		<br></li>

		<li><a href='addCountry.jsp'>Add</a> a new Country. </li>
		<br>	
		<br>
			
		<li><a href="updateCountry.jsp"></a></li>
		
		<li>
			<FORM METHOD="post" ACTION="country.do">
				<b>輸入國家編號 (如CRY0001):</b> <input type="text" name="countryNo"
					value="CRY0001"> <input type="hidden" name="action"
					value="getOne_For_Display"> <input type="submit" value="送出">
				<h4>(資料格式驗證 by Controller ).</h4>
			</FORM>
		</li>

		<jsp:useBean id="dao" scope="page"
			class="com.country.model.CountryDAO" />

		<li>
			<FORM METHOD="post" ACTION="country.do">
				<b>選擇國家編號:</b> <select size="1" name="countryNo">
					<c:forEach var="countryVO" items="${dao.all}">
						<option value="${countryVO.countryNo}">${countryVO.countryNo}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="country.do">
				<b>選擇國家名稱:</b> <select size="1" name="countryNo">
					<c:forEach var="countryVO" items="${dao.all}">
						<option value="${countryVO.countryNo}">${countryVO.countryName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>

</body>
</html>