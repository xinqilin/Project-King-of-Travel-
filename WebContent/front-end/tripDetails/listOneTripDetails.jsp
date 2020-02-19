<%@page import="com.tripDetails.model.TripDetailsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.city.model.*"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.tripDetails.model.*"%>
<% 
SpotListVO spotVO=(SpotListVO)request.getAttribute("spotVO");
%>

<table style="background-color: #BBFFEE;width:100%;border-radius: 20px;">
		<tr>
			<th><h5>景點名</h5></th>
			<th><h5>地址</h5></th>
			<th><h5>電話</h5></th>
			<th><h5>景點描述</h5></th>
<!-- 			<th>照片</th> -->
		</tr>
		<tr>

			<td style="text-decoration:underline; text-decoration-color: red;"><h4><%=spotVO.getSpotName() %></h4></td>	
			<td><h6><%=spotVO.getLocation() %></h6></td>
			<td>
			<%if(spotVO.getTel()==null){ %>
			<h6>此景點無提供電話</h6><%} %>
			<%if(spotVO.getTel()!=null){ %>
			<h6><%=spotVO.getTel() %></h6>
			<%} %>
			</td>
			<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">點我查看詳情</button></td>
<%-- 			<td><%=spotVO.getSpotDetail() %></td> --%>
			<td>
<%-- 			<%if(spotVO.getSpotPhoto()==null){%> --%>
<!-- 			<img src="/DA101G3/images/noPic.png" style="border:1px blue dashed;width:100px;height:100px;"> -->
			
			
<!-- 			<img src="https://picsum.photos/1200/300?random=1" style="border:1px blue dashed;width:100px;height:100px;"> -->


<%-- 			<%}else{ %> --%>
<%-- 			<img src="<%=request.getContextPath()%>/trip/SpotPic?spotno=<%=spotVO.getSpotNo() %>" style="border:1px blue dashed;width:100px;height:100px;"> --%>
<%-- 			<%} %> --%>
			</td>
			
		</tr>
	</table>
	
	
	
	
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content bg-warning text-dark">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">景點詳情</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <%if(spotVO.getSpotDetail()!=null){ %>
       <%=spotVO.getSpotDetail() %>
       <%}else{ %>
       Oops!!!   這景點尚無評論
       <%} %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

        <script>
		$('#myModal').on('shown.bs.modal', function () {
		  $('#myInput').trigger('focus')
		})
        </script>