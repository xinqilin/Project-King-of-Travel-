<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">
	

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/style.css">


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	
	
	</head>
	<body class="style-3">

		<div class="container">
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12 text-center"> -->
<!-- 					<ul class="menu"> -->
<!-- 						<li><a href="index.html">Style 1</a></li> -->
<!-- 						<li><a href="index2.html">Style 2</a></li> -->
<!-- 						<li class="active"><a href="index3.html">Style 3</a></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="row">
				<div class="col-md-4 col-md-push-4">
					

					<!-- Start Sign In Form -->
					<form action="Loginhandler.do" class="fh5co-form animate-box" data-animate-effect="fadeInRight" method="post">
						<h2>登入</h2>
						<div class="form-group">
							<label for="username" class="sr-only">E-mail</label>
							<input type="text" class="form-control" name="account" id="username" placeholder="Email" autocomplete="off" value="">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密碼</label>
							<input type="password" class="form-control" name="password" id="password" placeholder="密碼" autocomplete="off" value="">
						</div>
						<div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> 記住帳號</label>
						</div>
						<div class="form-group">
							<p>尚未註冊? <a href="<%=request.getContextPath()%>/back-end/mem/addMem.jsp">註冊</a> 
						</div>
						<div class="form-group">
							<input type="submit" value="登入" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->
					<table align="center">
					<td><button onclick="setData()" style="background-color:#FFFFFF;border-radius:80px;"><img src="<%=request.getContextPath()%>/images/icons8-java-256.png"></button></td>
					</table>
				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. More Templates <a href="#" target="_blank" title="遊記王">遊記王</a> - Collect from <a href="#" title="遊記王" target="_blank">遊記王</a></small></p></div>
			</div>
			
		</div>
	<div style="margin-top: 8%;">
			<button id="Curry" style="border-radius: 20px;">Curry</button><br><br>
			<button id="Harden" style="border-radius: 20px;">爆米花</button>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	
	
	<script>
	function setData(){
		$('#username').val('tucorn823@gmail.com');
// 		$('#password').val('111111');
	}
	</script>
	<script type="text/javascript">
	$('#Harden').click(
			function(){
				$('#username').val('zzx123bill@gmail.com');
				$('#password').val('123456876');
				
			});
	$('#Curry').click(
			function(){
				$('#username').val('Curry@gmail.com');
				$('#password').val('123456790');
				
			});
	</script>
	</body>
</html>

