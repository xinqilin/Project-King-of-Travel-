<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.city.model.*"%>


<%
    SpotListService spotSvc = new SpotListService();
    List<SpotListVO> list = spotSvc.getAll();
    pageContext.setAttribute("list",list);
    
  	 String cityno=(String)request.getParameter("cityNo");
 System.out.println(cityno);
  	 CityService citySvc=new CityService();
  	 CityVO cityVO=citySvc.getOne(cityno);
  	 
  	 pageContext.setAttribute("cityVO", cityVO);
  	 
%>
<%-- <%=list%> --%>
<jsp:useBean id="listCitySpot_ByCityNo" scope="page" class="com.city.model.CityService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title><%=cityVO.getCityName() %></title>
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style type="text/css">
	
	.heading{
  	background-image:url("<%=request.getContextPath()%>/front-end/spot/image/<%=cityVO.getCityNo()%>.jpg") ;
 	background-repeat: no-repeat; 
    width: 100%;
    height: 100%;
    padding-top: 13%;
    padding-bottom: 13%;
    background-size: 100% 100%;
    padding: 250px;
    background-position: center;
    background-attachment: fixed;
	}

	.hot{
	transition: all 0.7s;
	width: 100%; 
	height: 40%;
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
<%@ include file="/nav-f1"%>
</head>
<body>

  <br>
    <div class="heading">
      <table align="center" valign="center">
        <tr><td><font size="20" color="#FFFFFF" align="center" valign="center"><strong><%=cityVO.getCityName() %></strong></font></td></tr>  
<!--         <tr><td><font size="10" color="#FFFFFF">Taiwan</font></td></tr> -->
      </table>
    </div>
    <br>
    <table class="header" width="70%" style="table-layout:fixed;" cellpadding='10'; align="center" >
     	<tr>
     		<td colspan="4">
     			<font size="6"><strong>熱門旅遊景點</strong></font>
     		</td>
     	</tr>
    </table> 
    <div style="margin-left:14%;">

    	

	<div class="row">
		<%for(SpotListVO spot:list){ %>
			<%if(spot.getCityNo().equals(cityVO.getCityNo())){ %>
    			<div class="card" style="width: 18rem;margin:10px;">
				  <img src="<%=request.getContextPath()%>/back-end/spot/getspotphoto.do?spotno=<%=spot.getSpotNo() %>" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title"><strong><%=spot.getSpotName() %></strong></h5>
				    <p class="card-text">
					<%if(spot.getSpotDetail().length()>=20){ %>
					<%=spot.getSpotDetail().substring(0, 20) %>...
					<%}else{ %>
					<%=spot.getSpotDetail() %>
					<%} %>
				    </p>
				    <a href="<%=request.getContextPath()%>/front-end/spot/New_ListOneSpot.jsp?spotno=<%=spot.getSpotNo() %>&cityno=<%=spot.getCityNo() %>" class="btn btn-success">Go <%=spot.getSpotName() %></a>
				  </div>
				</div>
			<%}} %>
    	</div>
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
 
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 
</body>
</html>