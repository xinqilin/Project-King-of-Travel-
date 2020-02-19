<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
	<form id="form1" runat="server">
		<div class="alert alert-info" role="alert"  align="center">
			<font size="10"><strong>已寄送驗證信，請至信箱進行驗證！</strong></font>
			<div><font size="10"><strong>驗證信將於1小時後失效！</strong></font></div>
			<div><font color="#FF3333"><strong><div id='div1' style="font-size:20px;"></div>秒後跳轉回首頁</strong></font></div>
		</div>
	</form>

	<div style="text-align:center"><img alt="" src="<%=request.getContextPath()%>/images/run.gif"></div>
	
<script type="text/javascript">
//設定倒數秒數
var t = 6;
//顯示倒數秒數
function showTime(){
t -= 1;
document.getElementById('div1').innerHTML= t;
if(t==0){
location.href='<%=request.getContextPath()%>/index.jsp';
}
//每秒執行一次,showTime()
setTimeout("showTime()",1000);
}
//執行showTime()
showTime();
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	    
</body>
</html>

