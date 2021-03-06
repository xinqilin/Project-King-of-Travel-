<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wishitem.model.*"%>
<%
	WishItemVO wishItemVO = (WishItemVO) request.getAttribute("wishItemVO");
	Object account = session.getAttribute("account");
	String memNo = (String)session.getAttribute("memno");
	if (account == null) { 
		session.setAttribute("location", request.getRequestURI()); 
		response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		return;
	}
	pageContext.setAttribute("memNo",memNo);
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="icon" href="kingoftravel.ico" type="image/x-icon" />
<title>遊記王_新增代購許願商品</title></title>
<style type="text/css">
.modal-content {
	border-radius: 15px;
	background: #00BBFF;
}

.modal-backdrop {
	background-color: #000000;
}

.modal-backdrop.show {
	opacity: .5;
}

.modal-content {
	text-align: center;
	font-weight: bold;
}

.modal-header {
	align-items: center;
}

label {
	font-size: 20px;
}

a:hover, .current_page_item a {
	text-decoration: none;
	background: pink;
	border-radius: 10px;
	color: #FFFFFF;
	font-family: "微軟正黑體";
}

h1,h3,label,option,button,select {
	font-family: "微軟正黑體";
	font-weight: bold;
}
</style>

</head>
<body>

	<%@ include file="/nav-f1"%>

	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="https://picsum.photos/1200/300?random=1"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=2"
					class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="https://picsum.photos/1200/300?random=3"
					class="d-block w-100">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">大波霸組員</li>
			<li class="breadcrumb-item"><a href="#">緯明不要</a></li>
			<li class="breadcrumb-item"><a href="#">瑋齊不行</a></li>
			<li class="breadcrumb-item"><a href="#">奕伸大麻</a></li>
		</ol>
	</nav>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container-fluid">
		<div class="row">
			<div class="col-3">
				<div class="nav flex-column nav-pills" id="v-pills-tab"
					role="tablist" aria-orientation="vertical">
					<a class="nav-link" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">回代購首頁</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_sellList.jsp">代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/wishItem_buyList.jsp">託買商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishItem/listMyWishItem.jsp">我的託買及代購商品</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/WishOrd/listMyWishOrd.jsp">我的訂單</a>
					<a class="nav-link" 
						href="<%=request.getContextPath()%>/front-end/WishItem/add_WishItem.jsp"
						>新增許願代購商品</a>						
				</div>
			</div>
			<div class="col-9">

				<h1 align="center">
					<b>新增許願或代購商品</b>
				</h1>
				<div class="card" style="width: auto;">
					<div class="card-body">
						
						<form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/WishItem/wishItem.do" name="form1"
							enctype="multipart/form-data">
							<div class="form-group">
								<div  align=middle><img id="output" style="width:300px;height:200px;border-radius:8px;"></div> <br>
								<label for="wishItemPictureFile">商品樣本上傳</label> <br>
								<input type="file" name="wishItemPicture" accept="image/*" onchange="loadFile(event)"
								value="<%=(wishItemVO == null) ? "" : wishItemVO.getWishItemPicture()%>" 
								id ="wishItemPictureFile"/>
							</div>

						
						
						<p class="card-text">
						
							<div class="form-group">
								<label for="askMemid">提出請求會員</label>
								<input type="text" class="form-control" 
							value="<%=(account == null) ? "MEM0006" : memNo%>"id="askMemid" name="memNo" readonly="readonly"/>
							</div>
							<div class="row">
								<div class="col form-group">
									<label for="wishItemName">商品名稱:<font color=red><b>*</b></font></label>
									<input type="text" name="wishItemName" class="form-control" id="wishItemName"
									value="<%=(wishItemVO == null) ? "" : wishItemVO.getItemName()%>" required/>
								</div>
								<div class="col form-group">
									<label for="wishItemAmount">商品數量:<font color=red><b>*</b></font></label>
									 <input type="number" name="wishItemAmount" class="form-control" id="wishItemAmount"
									value="<%=(wishItemVO == null) ? 1 : wishItemVO.getAmount()%>" required/>
								</div>
							</div>
							<div class="form-group">
								<label for="wishItemPrice">商品價錢:<font color=red><b>*</b></font></label>
								<input type="text" class="form-control" id="wishItemPrice" name="wishItemPrice"
								value="<%=(wishItemVO == null) ? "" : wishItemVO.getItemPrice()%>" required/>
							</div>


							
							<div class="form-group">
								<label for="itemStoreName">商品店家名稱</label>
								<input type="text" class="form-control" id="itemStoreName"
								name ="itemStoreName" value="<%=(wishItemVO==null)?"":wishItemVO.getItemStoreName()%>" required/>
							</div>
							<div class="form-group">
								<label for="itemStoreAddr">商品店家地址</label>
								<textarea class="form-control" id="itemStoreAddr" name="itemStoreAddr" required
									rows="2"><%=(wishItemVO==null)?"":wishItemVO.getItemStoreAddr()%></textarea>
							<div class="form-group">
								<label for="wishItemType">商品類型</label> 
								<select name="wishItemType" class="form-control" id="wishItemType"
								autufocus="<%=(wishItemVO==null)?5:wishItemVO.getItemType()%>">
									<option value="0">家用電器</option>
									<option value="1">3C商品</option>
									<option value="2">生活用品</option>
									<option value="3">藥妝商品</option>
									<option value="4">服飾</option>
									<option value="5">其他</option>
								</select>
							</div></div>


							<div class="row form-group">
								<div class="col">
									<label for="itemStoreLati">商品店家緯度</label>
									<input type="text" class="form-control" id="itemStoreLati" name="itemStoreLati"
									value="<%=(wishItemVO==null)?"25.3226997":wishItemVO.getItemStoreLati()%>"/>
								</div>
								<div class="col">
									<label for="itemStoreLong">商品店家經度</label>
									<input type="text" class="form-control" id="itemStoreLong" name="itemStoreLong"
									value="<%=(wishItemVO==null)?"121.546827":wishItemVO.getItemStoreLong()%>"/>
								</div>
							</div>

							<label >商品託買或出售:<font color=red><b>*</b></font></label>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="buyOrSell" id="buyOrSell1" value="0"
									checked>
							 	<label class="form-check-label"	for="buyOrSell1"> 商品託買 </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="buyOrSell" id="buyOrSell2" value="1">
								<label class="form-check-label" for="buyOrSell2">代購商品 </label>
							</div>

							<div class="form-group">
								<label for="wishItemDetail">商品詳情敘述:<font color=red><b>*</b></font></label>
								<textarea class="form-control" id="wishItemDetail" name="wishItemDetail"
									rows="4"><%=(wishItemVO==null)?"":wishItemVO.getWishItemDetail()%></textarea>
							</div>

							<div class="form-group">
								<label for="wishNote">代購/許願但書</label>
								<textarea class="form-control" id="wishNote" name="wishNote"
									rows="4"></textarea>
							</div>

							<label >商品上架</label>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="status"
									id="UP" value="1" checked> <label
									class="form-check-label" for="UP"> 商品上架 </label>
							</div>
							

							</p>
							<div class="col-sm-10">
								<button type="submit" class="btn btn-secondary btn-lg" value="insert" name="action">新增商品</button>
							</div>
						</form>
					</div>
					
				
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 10%;">
			<button id="clickme" style="border-radius: 20px;">clickme</button><br><br>
	</div>
	<script>
	  var loadFile = function(event) {
	    var output = document.getElementById('output');
	    output.src = URL.createObjectURL(event.target.files[0]);
	  };
	</script>
	
	

	
	
	
	
	
	
	

	<script src="https://code.jquery.com/jquery-3.4.1.js"
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
			<script type="text/javascript">
	$('#clickme').click(
			function(){
				$('#wishItemName').val('蒸氣眼罩');
				$('#wishItemAmount').val('10');
				$('#wishItemPrice').val('500');
				$('#itemStoreName').val('OS_DRUG');
				$('#itemStoreAddr').val('1 Chome-2-15 Shinsaibashisuji, Chuo Ward, Osaka, 542-0085日本');
				$('#itemStoreLati').val('34.674535');
				$('#itemStoreLong').val('135.501507');
				$('#wishItemDetail').val('花王12片/包');
				$('#wishNote').val('被拆封了就不收');
			});
	</script>
</body>
</html>