<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.city.model.*"%>

<%
    CityService citySvc = new CityService();
    List<CityVO> list = citySvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="listCitySpot_ByCityNo" scope="page" class="com.city.model.CityService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>台灣</title>
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style type="text/css">
	
	.heading{
    background-repeat: no-repeat; 
    width: 100%;
    height: 100%;
    padding-top: 13%;
    padding-bottom: 13%;
    margin: 0;
    background-position: center;
    background-attachment: fixed;
  	background-image:url("image/南投2.jpg");
	}

	.hot{
	height: auto;
	transition: all 0.7s;
	width: 90%; 
	height: 25%;
	border-radius: 8px;
	}

	.hot:hover{
	/*width: 200%;*/
	transform: scale(1.2, 1.2);
	}

	.introduction{
		background-color: #DDDDDD;
		border-radius: 10px;
	}

</style>

</head>
<body>
<%@ include file="/nav-f"%>
  <br>
    <div class="heading">
      <table align="center" valign="center">
        <tr><td><font size="20" color="#FFFFFF"><strong>台&nbsp&nbsp&nbsp灣</strong></font></td></tr>  
        <tr><td><font size="10" color="#FFFFFF">Taiwan</font></td></tr>
      </table>
    </div>
    <br>
    <table class="header" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center" >
     	<tr>
     		<td class="introduction" colspan="4">
     			<blockquote>
     				位於亞洲東部、太平洋西北側的島嶼，地處琉球群島與菲律賓群島之間，以原住民族與漢族為兩大民族構成。台灣的複雜地貌，蘊藏了豐富的自然風光和人文景觀，變化萬千的阿里山日出和雲海，風和日麗的日月潭山湖景色，及以火山地形著稱的陽明山國家公園等等。民族文化融合了閩南、客家、原住民的精神、習俗，使得各地方的節日慶典都別具特色。觀光休閒街道的伴手禮烏龍高山茶、風味水果、傳統糕點等商品琳瑯滿目，深受觀光客的喜愛。 
     			</blockquote>
     		</td>
     	</tr>
     	<tr>
     		<td colspan="4">
     			<font size="6"><strong>熱門旅遊景點</strong></font>
     		</td>
     	</tr>
    </table>
   
    <div style="margin-left:14%;">
    	<c:forEach var="cityVO" items="${list}">
    		<c:if test="${cityVO.getCountryNo().equals(\"CRY0001\")}">
    			<a href="<%=request.getContextPath()%>/front-end/spot/CitySpotIndex.jsp?cityNo=${cityVO.cityNo}" >
    				<img class="hot" style="width:280px; height:200px; margin:10px; border-radius: 15px;"  src="<%=request.getContextPath()%>/front-end/city/image/${cityVO.getCityNo()}.jpg">
    			</a>
    		</c:if> 
    	</c:forEach>
    </div>
    
    
    <hr style="width: 70%;height: 2px;" color="#DDDDDD">
    <table class="header" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center" >
    	<tr>
     		<td colspan="4">
     			<font size="6"><strong>熱門旅遊遊記</strong></font>
     		</td>
     	</tr>
    </table>
   
    <hr style="width: 70%;height: 2px;" color="#DDDDDD">
    <table class="header" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center" >
    	<tr>
     		<td colspan="4">
     			<font size="6"><strong>熱門旅遊行程</strong></font>
     		</td>
     	</tr>
    </table>
 
</body>
</html>