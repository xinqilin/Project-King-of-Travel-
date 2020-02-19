<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.country.model.*"%>

<%
    CountryService countrySvc = new CountryService();
    List<CountryVO> list = countrySvc.getAll();
    pageContext.setAttribute("list",list);
    String cityno=(String)request.getParameter("countryNo");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style type="text/css">
	
	.heading{
		background-repeat: no-repeat; 
		width: 100%;
		height: 100%;
		padding-top: 33%;
		padding-bottom: 13%;
		margin: 0;
		background-position: center;
		background-attachment: fixed;
		background-image:url('image/climb851.jpg');

		/*background-repeat: no-repeat;
		background-size: cover;
		background-position: center;
		background-attachment: fixed;*/
	}

	.blue{background-color:#2185d0!important;border-color:#2185d0!important;color:#fff!important;
		width:60px!important;
		border-radius: 5px;
		text-align: center;
	}
	
	.gray-1{
		width:200px;
	}

	.gray{
		width: 100px;
		white-space: nowrap;
	}
	
	.gray-1:hover{
		cursor: pointer;
		background: #DDDDDD;
		text-align: center;
		border-radius: 8px;
	}

	.gray:hover{
		cursor: pointer;
		background: #DDDDDD;
		text-align: center;
		border-radius: 8px;
	}

	.red{background-color:#FF3333!important;border-color:#2185d0!important;color:#fff!important;
		width:35px!important;
		border-radius: 5px;
		text-align: center;
	}
	.ui .small .header{
		display: flex;
		justify-content: flex-start;
	}
	
	.header{
		font-family: "微軟正黑體";
	}

	.hot:hover{
	/*width: 200%;*/
	transform: scale(1.2, 1.2);
	}

	.hot{
	height: auto;
	transition: all 0.7s;
	width: 100%; 
	height: 35%;
	border-radius: 8px;
	}
	
	#search{
		width: 500px;
		height: 40px;
		border-radius: 8px;
		font-size: 15pt;
	}

	.search1{
		border-radius: 8px;
		height: 40px;
		width: 40px;
	}
	
	#box{
		padding-bottom: 1%;
	}
</style>
</head>
<body>
<%@ include file="/nav-f"%>
	<div>
		<!--<img class="heading" src="image/climb85.jpg">-->
		<div class="heading" id="box" class="container-fluid">
			<table align="center" valign="center">
				<td><input type="search" id="search" placeholder="&nbsp景點搜尋" /></td>
				<td><button class="search1"><img src="image/icons8-search-20.png"></button></td>
			</table>
		</div>
	</div>
    <table class="header" border="0" height="50%" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center" >
    	<tr>
    		<td><font size="6"><b>熱門地區</td>
    	</tr>
    	<tr>
    		<td>
    			<a href="">
    			<img class="hot" src="image/簡報1-6.jpg">
    			</a>
    		</td>
    		<td>
    			<a href="">
    			<img class="hot" src="image/簡報1-2.jpg">
    			</a>
    		</td>
    		<td>
    			<a href="">
    			<img class="hot" src="image/簡報1-4.jpg">
    			</a>
    		</td>
    		<td>
    			<a href="">
    			<img class="hot" src="image/簡報1-5.jpg">
    			</a>
    		</td>
    	</tr>
    	<tr>
    		<td><font size="6"><b>國家和地區</td>
    	</tr>
	　  <tr>
	　		<td><font size="5" color="#5599FF"><b>亞洲</font><div class="sub header">Asia</div></td>
	　  </tr>
	　  <tr>
		　	<td><div class="gray">汶淶<div class="sub header">Brunei</div></div></td>
		　	<td><div class="gray">柬埔寨<div class="sub header">Cambodia</div></div></td>
			<td><div class="gray">中國大陸<div class="sub header">China</div></div></td>
		　	<td><div class="gray">香港<div class="sub header">HongKong</div></div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">印度
				<div hover="111()" class="sub header">India</div>
				<div class="blue gray"><font size="2">即將開放</font></div>
			</div></td>
		　	<td><div class="gray">印尼<div class="sub header">Indonesia</div></div></td>
			<td><div class="gray">
				<a class="ui small header" href="<%=request.getContextPath()%>/front-end/city/JapanCityIndex.jsp?countryno=${countryVO.countryNo}" style="text-decoration:none;">
					<div>日本</div>
					<div class="sub header">Japan</div>
					<div class="red"><font size="2">熱門</font></div>
                </a>
            </div></td>
		　	<td><div class="gray">韓國<div class="sub header">Korea</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
		</tr>
		<tr>
		　	<td><div class="gray">澳門<div class="sub header">Macau</div></div></td>
			<td><div class="gray">馬來西亞<div class="sub header">Malaysia</div></div></td>
		　	<td><div class="gray">馬爾地夫<div class="sub header">Maldives</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</div></td>
			<td><div class="gray">緬甸<div class="sub header">Myanmar</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">菲律賓<div class="sub header">Philippines</div></div></td>
		　	<td><div class="gray">新加坡<div class="sub header">Singapore</div></div></td>
			<td><div class="gray">
				<a class="ui small header" href="<%=request.getContextPath()%>/front-end/city/TaiwanCityIndex.jsp?countryno=${countryVO.countryNo}" style="text-decoration:none;">
					台灣
					<div class="sub header">Taiwan</div>
					<div class="red"><font size="2">熱門</font></div>
                </a>
            </div></td>
		　	<td><div class="gray">泰國<div class="sub header">Thailand</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
		</tr>
		<tr>
		　	<td><div class="gray">土耳其<div class="sub header">Turkey</div></div></td>
		　	<td><div class="gray-1">阿拉伯聯合大公國
				<div class="sub header">United Arab Emirates</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</div></td>
			<td><div class="gray">越南<div class="sub header">Vietnam</div></div></td>
			<td><div class="sub header"></div></iv></td>
		</tr>
	</table>

	<hr width="70%" style="margin-top:30px;">

	<table border="0" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center">
		<tr>
	　		<td><font size="5" color="#5599FF"><b>歐洲</font><div class="sub header">Europe</div><iv></td>
	　  </tr>
	　  <tr>
		　	<td><div class="gray">比利時<div class="sub header">Belgium</div></div></td>
		　	<td><div class="gray">捷克
				<div class="sub header">Czech</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
			<td><div class="gray">丹麥<div class="sub header">Denmark</div></div></td>
		　	<td><div class="gray">芬蘭<div class="sub header">Finland</div></div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">法國 <div class="sub header">France</div></div></td>
		　	<td><div class="gray">德國<div class="sub header">Germany</div></div></td>
			<td><div class="gray">希臘<div class="sub header">Greece</div></div></td>
		　	<td><div class="gray">匈牙利<div class="sub header">Hungary</div></div></td>
		</tr>
		<tr>
		　	<td><div class="gray">冰島
				<div class="sub header">Iceland</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
		　	<td><div class="gray">義大利
				<div class="sub header">Italy</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
			<td><div class="gray">立陶宛
				<div class="sub header">Lithuania</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</div></td>
		　	<td><div class="gray">荷蘭<div class="sub header">Netherlands</div></div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">挪威<div class="sub header">Norway</div></div></td>
		　	<td><div class="gray">波蘭<div class="sub header">Poland</div></div></td>
			<td><div class="gray">葡萄牙<div class="sub header">Portugal</div></div></td>
		　	<td><div class="gray">俄羅斯<div class="sub header">Russia</div></div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">斯洛伐克
				<div class="sub header">Slovakia</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</div></td>
		　	<td><div class="gray">西班牙<div class="sub header">Spain</div></div></td>
			<td><div class="gray">瑞典<div class="sub header">Sweden</div></div></td>
		　	<td><div class="gray">瑞士
				<div class="sub header">Switzerland</div>
				<div class="red"><font size="2">熱門</font></div>
			</div></td>
	　  </tr>
		<tr>
		　	<td><div class="gray">英國<div class="sub header">United Kingdom</div></div></td>
		　	<td><div class="sub header"></div></td>
			<td><div class="sub header"></div></td>
		　	<td><div class="sub header"></div></td>
	　  </tr>
	</table>
	
	<hr width="70%" style="margin-top:30px;">

	<table border="0" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center">
		<tr>
	　		<td><font size="5" color="#5599FF"><b>北美洲</font><div class="sub header">North America</div></td>
	　  </tr>
	　  <tr>
		　	<td>加拿大<div class="sub header">Canada</div></td>
		　	<td>美國<div class="sub header">United States of America</div></td>
			<td><div class="sub header"></div></td>
		　	<td><div class="sub header"></div></td>
	　  </tr>
	</table>

	<hr width="70%" style="margin-top:30px;">

	<table border="0" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center">
		<tr>
	　		<td><font size="5" color="#5599FF"><b>大洋洲</font><div class="sub header">Oceania</div></td>
	　  </tr>
	　  <tr>
		　	<td>澳大利亞
				<div class="sub header">Australia</div>
				<div class="red"><font size="2">熱門</font></div>
			</td>
		　	<td>紐西蘭
				<div class="sub header">New Zealand</div>
				<div class="red"><font size="2">熱門</font></div>
			</td>
			<td>帛琉<div class="sub header">Palau</div></td>
		　	<td><div class="sub header"></div></td>
	　  </tr>
	</table>

	<hr width="70%" style="margin-top:30px;">

	<table border="0" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center">
		<tr>
	　		<td><font size="5" color="#5599FF"><b>非洲</font><div class="sub header">Africa</div></td>
	　  </tr>
	　  <tr>
		　	<td>埃及
				<div class="sub header">Egypt</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</td>
		　	<td>南非
				<div class="sub header">South Africa</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</td>
			<td><div class="sub header"></div></td>
		　	<td><div class="sub header"></div></td>
	　  </tr>
	</table>

	<hr width="70%" style="margin-top:30px;">

	<table border="0" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center">
		<tr>
	　		<td><font size="5" color="#5599FF"><b>南極洲</font><div class="sub header">Antarctica</div></td>
	　  </tr>
	　  <tr>
		　	<td>南極
				<div class="sub header">South Pole</div>
				<div class="blue"><font size="2">即將開放</font></div>
			</td>
		　	<td><div class="sub header"></div></td>
			<td><div class="sub header"></div></td>
		　	<td><div class="sub header"></div></td>
	　  </tr>
	</table>
</body>
</html>