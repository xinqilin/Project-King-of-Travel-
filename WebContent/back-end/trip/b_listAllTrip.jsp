<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
    TripService tripSvc = new TripService();
    List<TripVO> list = tripSvc.getAll();
    pageContext.setAttribute("list",list);

%>
<style>
table {
	background-color:#AAFFEE;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table,th, td {
    border: 1px solid #000000;
  }
th{
	padding: 5px;
	text-align: center;
	border-bottom:2px dashed #FF8800;
}
td {
	padding: 5px;
	text-align: center;
}
</style>
<table align="center">
	<tr>
		<th>行程編號</th>
		<th>會員編號</th>
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
		<th>編輯按鈕</th>
		<th>查看詳情</th>
		<th>審核按鈕</th>
		<th>凍結按鈕</th>
	</tr>
		
	<%@ include file="page1.file" %> 
	<c:forEach var="tripVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${tripVO.tripno}</td>
			
<%-- 			<td>${tripVO.memno}</td> --%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<td>
<c:forEach var="memVO" items="${memSvc.all}">
<c:if test="${tripVO.memno.equals(memVO.memNo)}">
<c:out value="${memVO.memName}"/><br>
<c:out value="${memVO.memNo}"/>
</c:if>
</c:forEach>
</td>



<%-- 			<td>${tripVO.cityno}</td> --%>
			
<jsp:useBean id="citySvc" scope="page" class="com.city.model.CityService" />
<td>
<c:forEach var="cityVO" items="${citySvc.all}">
<c:if test="${tripVO.cityno.equals(cityVO.cityNo)}">
<c:out value="${cityVO.cityName}"/>
</c:if>
</c:forEach>
</td>
			
			
			
			<td>${tripVO.tripname}</td>
			<td>${tripVO.tripstartday}</td>
			<td>${tripVO.tripendday}</td>
			<td>${tripVO.tripdays}</td>
			<td>${tripVO.tripestdate}</td>
			<td>
			<c:if test="${tripVO.bethebuyer==0}"><c:out value="否"/></c:if>
			<c:if test="${tripVO.bethebuyer!=0}"><c:out value="是"/></c:if>
			</td>
			
			<c:if test="${tripVO.tripstatus==0}">
			<td bgcolor="#DDDDDD">
				<c:if test="${tripVO.tripstatus==0}"><c:out value="被檢舉"/></c:if>
				<c:if test="${tripVO.tripstatus==1}"><c:out value="公開行程"/></c:if>
				<c:if test="${tripVO.tripstatus==2}"><c:out value="Oops! 被凍結了"/></c:if>
			</td>
			</c:if>
			
			<c:if test="${tripVO.tripstatus==2}">
			<td bgcolor="#FF3333">
				<c:if test="${tripVO.tripstatus==0}"><c:out value="被檢舉"/></c:if>
				<c:if test="${tripVO.tripstatus==1}"><c:out value="公開行程"/></c:if>
				<c:if test="${tripVO.tripstatus==2}"><c:out value="Oops! 被凍結了"/></c:if>
			</td>
			</c:if>
			
			<c:if test="${tripVO.tripstatus==1}">
			<td>
				<c:if test="${tripVO.tripstatus==0}"><c:out value="被檢舉"/></c:if>
				<c:if test="${tripVO.tripstatus==1}"><c:out value="公開行程"/></c:if>
				<c:if test="${tripVO.tripstatus==2}"><c:out value="Oops! 被凍結了"/></c:if>
			</td>
			</c:if>
			<td>${tripVO.timeofviews}</td>
			<td>
			<c:if test="${tripVO.kindoftrip==0}"><c:out value="單獨旅行"/></c:if>
			<c:if test="${tripVO.kindoftrip==1}"><c:out value="情侶出遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==2}"><c:out value="家庭親子"/></c:if>
			<c:if test="${tripVO.kindoftrip==3}"><c:out value="朋友出遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==4}"><c:out value="商務旅遊"/></c:if>
			<c:if test="${tripVO.kindoftrip==5}"><c:out value="其它"/></c:if>
			</td>
			<td>
			<c:if test="${tripVO.trippic==null}"><img src="<%=request.getContextPath()%>/images/noPic.png" style="border:1px blue dashed;width:100px;height:100px;"></c:if>
			<c:if test="${tripVO.trippic!=null}"><img src="<%=request.getContextPath()%>/trip/TripPic?tripno=${tripVO.tripno}"  style="border:1px blue dashed;width:100px;height:100px;"></c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/trip/Trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="編輯這筆資料">
			     <input type="hidden" name="tripno"  value="${tripVO.tripno}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			    </FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/trip/Trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看這筆資料">
			     <input type="hidden" name="tripno"  value="${tripVO.tripno}">
			     <input type="hidden" name="action"	value="seeDetails">
			    </FORM>
			</td>
			
			
			
			<td>
			<c:if test="${tripVO.tripstatus!=1}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/trip/Trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="更改為公開">
			      <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			      <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			     <input type="hidden" name="tripno"  value="${tripVO.tripno}">
			     <input type="hidden" name="action"	value="accept">
			    </FORM>
			</c:if>
			<c:if test="${tripVO.tripstatus==1}">
			<c:out value="已是公開狀態"></c:out>
			</c:if>
			</td>
			<td>
			<c:if test="${tripVO.tripstatus!=2}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/trip/Trip.do" style="margin-bottom: 0px;" id="form1">
			     <input type="hidden" name="action" value="delete">
			     <input type="hidden" name="tripno"  value="${tripVO.tripno}">
			      <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">              
			     <input type="submit" value="凍結這筆" id="freeze" >
			     </FORM>
			</c:if>
			<c:if test="${tripVO.tripstatus==2}">
			<c:out value="已被凍結"></c:out>
			</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<hr color="pink" align="center" width="65%" size="5px">
<br>
<br>
<br>
<br>
<br>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>


