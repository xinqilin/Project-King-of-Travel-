<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login的啦</title>
<!-- JQuery -->
<script
	src="<%=request.getContextPath()%>/front-end/CDN/jquery-3.4.1.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/CDN/jquery.form.js"></script>
<!-- 台灣地址 -->
<script src="<%=request.getContextPath()%>/front-end/CDN/twzip.js"></script>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>






   <!-- Bootstrap core CSS -->
<link href="/docs/4.2/dist/css/bootstrap.css" rel="stylesheet">


    <style>
    body{
	font-family:標楷體;
	}
    .container{
    width:600px;
    }
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
</head>
<body>

<div class="container">

  <form class="form-signin" action="BackLoginhandler.do" method="post">
  <div class="text-center mb-4">
    <img class="mb-4" src="/docs/4.2/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal"><font size="8px">遊記王</font></h1>
    <p><font  size="5px">[後端管理員登入]</font></p>
  </div>

  <div class="form-label-group">
    <input type="text" id="inputEmail" class="form-control" placeholder="帳號" name="backaccount" required autofocus>
  </div>

  <div class="form-label-group">
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密碼" required>
  </div>

  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
  <p class="mt-5 mb-3 text-muted text-center">遊記王&copy; 2017-2018</p>
</form>
</div>
</body>
</html>