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


<div class="container">
			<div class="col-12 col-md-12">
				<div class="card border-info mb-3">
					<div class="card-body" width="100%">
					<div class="row">
<%-- 						<%if(spotVO.getSpotPhoto()==null){%> --%>
						<img src="https://picsum.photos/1200/300?random=1" style="border:1px aqua dashed;width:200px;height:200px;">
<%-- 						<%}else{ %> --%>
<%-- 						<img src="<%=request.getContextPath()%>/trip/SpotPic?spotno=<%=spotVO.getSpotNo() %>" style="border:1px blue dashed;width:100px;height:100px;"> --%>
<%-- 						<%} %> --%>
						<h2 class="card-title" style="margin-left:5%;text-decoration:underline; text-decoration-color: red;"><br><br>景點名 : <%=spotVO.getSpotName() %></h2>
					</div>
						<hr color="pink" align="center" width="100%" size="5px">
						<h3 align="left">大綱:</h3>
						<h4>地址:<%=spotVO.getLocation() %></h4>
									
						<%if(spotVO.getTel()==null){ %>
						<h4>此景點無提供電話</h4><%} %>
						<%if(spotVO.getTel()!=null){ %>
						<h4><%=spotVO.getTel() %></h4>
						<%} %>
			
						<h4>景點描述&nbsp:&nbsp&nbsp<button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal">點我查看詳情</button></h4>


					</div>
				</div>
		</div>
	</div>
	
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
       <%=spotVO.getSpotDetail() %>
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