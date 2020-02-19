<%@page import="com.tripDetails.model.TripDetailsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.tripDetails.model.*"%>
<%String cityno=(String)request.getAttribute("cityno"); %>
<!DOCTYPE html>
<html>
<head>
<title>遊記王-行程</title>
<script type="text/javascript">
	onload = function() {
		setTimeout(go, 1);
	}
	function go() {
		document.test.submit();
	}
</script>
</head>
<body>
<form name="test" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/tripDetails/TripDetails.do">
<input type="hidden" name="cityno" value="${cityno}"/>
<input type="hidden" name="action" value="lastone" />
</form>
</body>
</html>