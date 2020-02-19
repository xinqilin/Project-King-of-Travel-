<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
	Object account = session.getAttribute("account");
	MemVO memVO = (MemVO) session.getAttribute("accountVO");
	MemVO NEWmem = (MemVO) request.getAttribute("memVO_update");

	if (NEWmem != null) {
		memVO = NEWmem;
	}
	pageContext.setAttribute("memVO", memVO);

	String memno = (String) session.getAttribute("memno");
	if (account == null) { // �p�� null, �N��user���n�J�L , �~���H�U�u�@
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>�C�O��-�|���M��</title>

<script src="https://code.jquery.com/jquery-1.11.3.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style type="text/css">
.gray:hover {
	cursor: pointer;
	background: #DDDDDD;
	text-align: center;
	border-radius: 8px;
}

table, td, tr {
	border: 0px solid;
}

td {
	width: 105px;
}

/*.mem{
		display:inline-block;
	    margin-right:20px;
	    box-shadow: 9px 9px 9px #888888;
	    border-radius: 180px ;
	}*/
.header {
	border-radius: 180px 180px 180px 180px;
}

.a {
	border-radius: 10px;
}

/*.option{
		border-radius: 10px;
		box-shadow: 1px 1px 1px 1px #888888;
		margin: auto;
	}*/
.green {
	background-color: #00AAAA;
	border-radius: 10px;
}

.gray {
	width: 100px;
	white-space: nowrap;
}

.green1 {
	background-color: #00AAAA;
	border-radius: 10px 10px 0px 0px;
}

.red {
	background-color: #FF5511;
	border-radius: 10px 10px 0px 0px;
}

.blue {
	background-color: #0088A8;
	border-radius: 10px 10px 0px 0px;
}

.orange {
	background-color: #FF8800;
	border-radius: 10px 10px 0px 0px;
}

.white {
	background-color: #FFFFFF;
	border-radius: 0px 0px 10px 10px;
}

.hot:hover {
	/*width: 200%;*/
	transform: scale(1.2, 1.2);
}

.hot {
	height: auto;
	transition: all 0.7s;
	width: 100%;
	height: 35%;
	border-radius: 8px;
}

.black {
	background-color: #000000;
	border-radius: 8px;
}

.purple {
	background-color: #A500CC;
	border-radius: 10px 10px 0px 0px;
}

.circle {
	border: 2px solid;
	border-color: #DDDDDD;
	width: 350px;
	height: 350px;
	-moz-border-radius: 100px;
	-webkit-border-radius: 100px;
	border-radius: 180px;
	box-shadow: 0px 0px 9px;
}

#div_a {
	display: inline-block;
	width: 150px;
	height: 50px;
	margin-right: 20px;
	box-shadow: 0px 0px 9px #EE7700;
}

#div_b {
	display: inline-block;
	width: 150px;
	height: 50px;
	margin-right: 20px;
	box-shadow: 0px 0px 9px #007799;
}

#div_c {
	display: inline-block;
	width: 150px;
	height: 50px;
	margin-right: 20px;
	box-shadow: 0px 0px 9px #CC0000;
}

#div_d {
	display: inline-block;
	width: 150px;
	height: 50px;
	margin-right: 20px;
	box-shadow: 0px 0px 9px #00AAAA;
}

#div_e {
	display: inline-block;
	width: 150px;
	height: 50px;
	margin-right: 20px;
	box-shadow: 0px 0px 9px #CC00CC;
}

body {
	font-family: �L�n������ !important;
}
</style>
</head>
<body>
	 <!--nav�Щ�bbody�Ĥ@��-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="nanana">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">�C�O��</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          
         
       <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/front-end/trip/trip_index.jsp" tabindex="-1" >��{</a>
      </li> 
          
          
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/front-end/spot/SpotIndex.jsp" tabindex="-1" >���I</a>
      </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              �C�O
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/listArticleOrderByTime.jsp">�[�ݹC�O</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp">���ɹC�O</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp">�d�ݭӤH�C�O</a>
            </div>
          </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             �ӫ�
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/item_list_all.jsp">�ӫ�����</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/f_item_list_all.jsp">�w�l�ܰӫ~</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/storeOrd_list_all.jsp">�ڪ��q��</a>
            </div>
          </li>
                          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             �N��
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">�N�ʰӫ�</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItem_sellList.jsp">�ݽ��</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItem_buyList.jsp">�ݶR��</a>
              
            </div>
          </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              �v�q
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/activity/activity2.jsp">�v�q</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
            
            
            
            <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/mem/new_ListOneMem.jsp" tabindex="-1" >�|��</a>
     		 </li>
         
<%--         <c:if test="${account==null}"><li class="nav-item" > <li><a  class="nav-link"  href="<%=request.getContextPath()%>/login.jsp">�n�J</a></li></c:if> --%>
		<c:if test="${account!=null}"><li class="nav-item"> 
		<li><a class="nav-link" href="#" style="text-decoration:underline; color:white;">
			�A�n! ${account}</a>
<%-- 		<a class="nav-link" href="<%=request.getContextPath()%>/Logouthandler.do">�n�X</a></li> --%>
			
			
			</c:if>
        </ul>
      </div>
    </nav>
     
     
  
     
    <!-- nav -->
	<table align="center" valign="center">
		<tr class="mem">
			<th><div style="border-radius: 20px;">
					<img name="memPhoto" style="width:250px;height:200px;border-radius:20px;" alt="img"
						src="<%=request.getContextPath()%>/back-end/mem/getmemphoto.do?memno=${memVO.memNo}" />
				</div></th>
		</tr>
	</table>
	<table align="center" valign="center" style="text-align: center;">
		<br>
		<tr>
			<td name="nickName"><font size="6"><strong>${account}</strong></font></td>
		</tr>
	</table>
	<table align="center" valign="center" style="font-family: �L�n������">
		<tr>
			<td colspan="4">
				<!--<button class="a" type="button" style="background-color:#FF7744;text-align:center;cursor: pointer;">
    			<img src="image/icons8-edit-property-17.png" style="text-align:center;">
    			<font size="4" color="#FFFFFF" >�ק���</font>
    			</button>-->
				<button class="a" type="button"
					style="background-color: #FF7744; text-align: center; cursor: pointer;"
					data-toggle="modal" data-target="#exampleModal"
					data-whatever="@fat">
					<img src="image/icons8-edit-property-17.png"
						style="text-align: center;"> <font size="3" color="#FFFFFF"><strong>�ק���</strong></font>
				</button>
			</td>
			<td>
				<button class="a" type="button"
					style="background-color: #2185d0; text-align: center; cursor: pointer;"
					data-toggle="modal" data-target="#exampleModal2"
					data-whatever="@fat">
					<img src="image/icons8-camera-17.png" style="text-align: center;">
					<font size="3" color="#FFFFFF"><strong>���Y��</strong></font>
				</button>
			</td>
		</tr>
	</table>
	<table align="center" valign="center">
		<tr>
			<div class="gray" style="width: 300px;">
				<td class="option"><a
					href="<%=request.getContextPath()%>/index.jsp"
					style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-home-18.png">����
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-foot-20-1.png">�h�L
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-airport-18.png">�n�h
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-edit-file-18.png">�峹
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-traveler-18.png">��{
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-ask-question-18.png">�ݵ�
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-photo-gallery-18.png">�Ӥ�
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-folder-18.png">����
						</div>
						<div class="green" align="center">
							<font color="#FFFFFF">0</font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-coins-20.png">�`�n��
						</div>
						<div class="green" align="center" name="points">
							<font color="#FFFFFF"><%=memVO.getPoints()%></font>
						</div>
				</a></td>
			</div>
			<div class="gray" style="width: 300px;">
				<td class="option"><a href="" style="text-decoration: none;">
						<div align="center">
							<img src="image/icons8-expensive-price-20.png">���n��
						</div>
						<div class="green" align="center" name="maxPoints">
							<font color="#FFFFFF"><%=memVO.getMaxPoints()%></font>
						</div>
				</a></td>
			</div>
		</tr>
	</table>
	<table align="center">
		<hr width="90%" style="margin-top: 2%; margin-bottom: 2%">
		<tr>
			<div>
				<td class="middle" align="center" style="width: 160pX;"><a
					href="" style="text-decoration: none;">
						<div class="orange" id="div_a" align="center"
							style="height: 50px; width: 150px; line-height: 55px; text-align: center;">
							<img src="image/icons8-foot-20.png"><font color="#FFFFFF"
								size="4">�h�L</font>
						</div>
						<div class="white" id="div_a" align="center"
							style="height: 110px; width: 150px; line-height: 80px; text-align: center;">
							<font color="#FF8800" size="6">0</font>
							<div align="center" style="line-height: 0px; text-align: center;">
								<font size="2">�Ӧa�I</font>
							</div>
						</div>
				</a></td>
			</div>
			<div>
				<td class="middle" align="center" style="width: 160pX;"><a
					href="" style="text-decoration: none;">
						<div class="blue" id="div_b" align="center"
							style="height: 50px; width: 150px; line-height: 55px; text-align: center;">
							<img src="image/icons8-airport-20.png"><font
								color="#FFFFFF" size="4">�n�h</font>
						</div>
						<div class="white" id="div_b" align="center"
							style="height: 110px; width: 150px; line-height: 80px; text-align: center;">
							<font color="#0088A8" size="6">0</font>
							<div align="center" style="line-height: 0px; text-align: center;">
								<font size="2">�Ӧa�I</font>
							</div>
						</div>
				</a></td>
			</div>
			<div>
				<td class="middle" align="center" style="width: 160pX;"><a
					href="" style="text-decoration: none;">
						<div class="red" id="div_c" align="center"
							style="height: 50px; width: 150px; line-height: 55px; text-align: center;">
							<img src="image/icons8-edit-file-20.png"><font
								color="#FFFFFF" size="4">�峹</font>
						</div>
						<div class="white" id="div_c" align="center"
							style="height: 110px; width: 150px; line-height: 80px; text-align: center;">
							<font color="#FF5511" size="6">0</font>
							<div align="center" style="line-height: 0px; text-align: center;">
								<font size="2">�g�峹</font>
							</div>
						</div>
				</a></td>
			</div>
			<div class="hot">
				<td class="middle" align="center" style="width: 160pX;"><a
					href="" style="text-decoration: none;">
						<div class="green1" id="div_d" align="center"
							style="height: 50px; width: 150px; line-height: 55px; text-align: center;">
							<img src="image/icons8-traveler-25.png"><font
								color="#FFFFFF" size="4">��{</font>
						</div>
						<div class="white" id="div_d" align="center"
							style="height: 110px; width: 150px; line-height: 80px; text-align: center;">
							<font color="#00AAAA" size="6">0</font>
							<div align="center" style="line-height: 0px; text-align: center;">
								<font size="2">�Ӧ�{</font>
							</div>
						</div>
				</a></td>
			</div>
			<div>
				<td rowspan="5" style="width: 20px"><hr noshade width="1"
						size=100 style="height: 400px;"></td>
			</div>
			<div class="hot" style="margin-right: 60%;">
				<td rowspan="5">
					<div class="purple" id="div_e" align="center"
						style="height: 60px; width: 500px; line-height: 70px; text-align: center;">
						<img src="image/icons8-person-20.png"><font color="#FFFFFF"
							size="4">²��</font>
					</div>
					<div class="white" id="div_e" align="center"
						style="height: 400px; width: 500px; line-height: 100px; text-align: center;">
						<div name="introduction" align="center"
							style="line-height: 20px; padding: 20px;">
							<strong><%=memVO.getIntroduction()%></strong>
						</div>

					</div>
				</td>
			</div>
		</tr>
		<tr align="center" valign="center"
			style="text-align: center; margin-left: 27%;">
			<td colspan="4" style="padding-bottom: 1px; padding-top: 1%;">
				<div class="circle" style="margin-left: 27%; margin-top: 6%;">
					<br> <br> <font color="#FF8800" size="25"><strong>0</strong></font>
					<div>
						<font size="2">�ڥh�L/�n�h���a�I</font>
					</div>
					<br>
					<div>
						<strong>�~��ֿn�ڪ�����</strong>
					</div>
					<br>
					<button class="black" style="height: 48px;">
						<strong> <img src="image/icons8-edit-file-20.png">
							<font color="#FFFFFF" size="4">���ɤ峹</font>
						</strong>
					</button>
					<button class="black" style="height: 48px;">
						<strong> <img src="image/icons8-traveler-25.png"> <a
							href="<%=request.getContextPath()%>/front-end/trip/addTrip.jsp"><font
								color="#FFFFFF" size="4">�إߦ�{</font></a>
						</strong>
					</button>
				</div>
			</td>
		</tr>
	</table>
	<!--<table align="center" valign="center" style="text-align: center;height: 100px;margin-left: 27%;">
    	<tr>
    		<td><font color="#FF8800" size="25"><strong>33</strong></font></td>
    	</tr>
    </table>-->
	<!--<table align="center" valign="center" style="text-align: center;width: 140px;margin-left: 26%;">
    	<td><font size="2">�ڥh�L/�n�h���a�I</font></td>
    </table>
    <table align="center" valign="center" style="text-align: center;width: 140px; height: 50px;margin-left: 26%;">
    	<td><strong>�~��ֿn�ڪ�����</strong></td>
    </table>-->
	<!--<table align="center" valign="center" style="text-align: center;border:8px;margin-left: 24%;">
    	<td class="black" style="width: 120px; height: 30px;">
    		<a href="" style="text-decoration:none;">
    			<div>
		    		<strong>
			    		<img src="image/icons8-edit-file-20.png">
			    		<font color="#FFFFFF" size="4">���ɤ峹</font>
		    		</strong>
    			</div>
    		</a>
    	</td>
    	<td class="black" style="width: 120px; height: 40px;">
    		<a href="" style="text-decoration:none;">
    			<div>
    				<strong>
		    			<img src="image/icons8-traveler-25.png">
		    			<font color="#FFFFFF" size="4">�إߦ�{</font>
		    		</strong>
    			</div>
    		</a>
    	</td>
	</table>-->

	<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form
				action="<%=request.getContextPath()%>/back-end/mem/getmemphoto.do"
				METHOD="post" enctype="multipart/form-data" name="form1">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">�ק���</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�Y��:</strong></label>
							<img id="output"
								style="width: 300px; height: 300px; border-radius: 8px;"
								alt="img" /> <input type="file" name="memPhoto"
								class="form-control" id="recipient-name"
								onchange="loadFile(event)">
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="update_mem_photo">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">����</button>
						<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
						<button type="submit" class="btn btn-primary">�e�X</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<c:if test="${not empty errorMsgs}">
<!-- 				<font style="color: red">�Эץ��H�U���~:</font> -->
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
<%-- 						<li style="color: red">${message}</li> --%>
					</c:forEach>
				</ul>
			</c:if>
			<form action="<%=request.getContextPath()%>/back-end/mem/mem.do"
				METHOD="post" enctype="multipart/form-data">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<strong>�ק���<b></strong>
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�K�X:</strong></label>
							<input type="password" class="form-control" id="recipient-name"
								name="memPassWd" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�T�{�K�X:</strong></label>
							<input type="password" class="form-control" id="recipient-name"
								name="memPassWdConfirm" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�ʺ�:</strong></label>
							<input type="text" class="form-control" id="recipient-name"
								name="nickName" value="<%=memVO.getNickName()%>">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�q��:</strong></label>
							<input type="text" class="form-control" id="recipient-name"
								name="phone" value="<%=memVO.getPhone()%>">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label"><strong>�a�}:</strong></label>
							<!--             <select id="zone1"  name="zone1" style="width: 110px;" value=""></select> -->
							<!-- 			<select id="zone2"  name="zone2" style="width: 110px;" value=""></select> -->
							<!-- 			<input	type="text" id="zipcode" name="zipcode" style="width: 30px;" /> -->
							<input type="text" class="form-control" id="recipient-name"
								name="address" required value="<%=memVO.getAddress()%>">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label"><strong>²��:</strong></label>
							<textarea class="form-control" id="message-text"
								name="introduction"><%=memVO.getIntroduction()%></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="update">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">����</button>
						<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
						<button type="submit" class="btn btn-primary">�e�X</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<!-- �w���� -->
	<script>
		var loadFile = function(event) {
			var output = document.getElementById('output');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>




	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>