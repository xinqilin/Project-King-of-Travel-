<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.trip.model.*"%>
<%
	TripVO tripVO = (TripVO) request.getAttribute("tripVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="BIG5"> -->
<style>
table {
	background-color: pink;
	margin-top: 5px;
	margin-bottom: 5px;
	
	margin-left: auto;
	margin-right: auto;
}

table, th, td {
	border: 1px solid orange;
}
th{
	padding: 5px;
	text-align: center;
	border-bottom:2px dashed red;
}
td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body bgcolor='white'>

	<table>
		<tr>
<!-- 					<th>行程編號</th> -->
<!-- 					<th>會員編號</th> -->
			<th>城市名</th>
			<th>行程名稱</th>
			<th>啟程日</th>
			<th>結束日</th>
			<th>天數</th>
			<th>行程創建日</th>
			<th>是否成為買家</th>
			<th>行程狀態</th>
			<th>觀看次數</th>
			<th>行程類型</th>
			<th>行程圖片</th>
		</tr>
		<tr>
<%-- 					<td><%=tripVO.getTripno()%></td> --%>
<%-- 					<td><%=tripVO.getMemno()%></td> --%>
			<td><%=tripVO.getCityno()%></td>
			<td><%=tripVO.getTripname()%></td>
			<td><%=tripVO.getTripstartday()%></td>
			<td><%=tripVO.getTripendday()%></td>
			<td><%=tripVO.getTripdays()%></td>
			<td><%=tripVO.getTripestdate()%></td>
			<td><%if(tripVO.getBethebuyer()==1) out.println("是");else out.println("否");%></td>
			<td>
			<%if(tripVO.getTripstatus()==0) out.println("未公開等待審核中...");
			else if(tripVO.getTripstatus()==1) out.println("公開行程");
			else out.println("Oops! 被凍結了");
			%>
			</td>
			<td><%=tripVO.getTimeofviews()%></td>
			<td>
			<% 
			if(tripVO.getKindoftrip()==0) out.println("單獨旅行");	
			else if(tripVO.getKindoftrip()==1) out.println("情侶出遊");
			else if(tripVO.getKindoftrip()==2) out.println("家庭親子");
			else if(tripVO.getKindoftrip()==3) out.println("朋友出遊");
			else if(tripVO.getKindoftrip()==4) out.println("商務旅遊");
			else out.println("其它");
			%>
			</td>
			<td>
			<%if(tripVO.getTrippic()==null){%>
			<img src="/DA101G3/images/noPic.png" style="border:1px blue dashed;width:100px;height:100px;">
			<%}else{ %>
			<img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}" style="border:1px blue dashed;width:100px;height:100px;">
			<%} %>
			</td>
			
		</tr>
	</table>

</body>
</html>