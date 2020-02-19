<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item.controller.*"%>
<%
	ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");
%>
<%
Object backaccount = session.getAttribute("backaccount"); // 從 session內取出 (key) account的值
if (backaccount == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
	session.setAttribute("location", request.getRequestURI()); 
	response.sendRedirect(request.getContextPath() + "/login_back.jsp"); 
	return;
}

%>
<html>
<head>
<!-- BootStrap4.3.1 2019/07/28 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>[後台]商品資料修改</title>

<style>
body{
  font-family: 微軟正黑體 !important; 
}
img{
max-height:300px;
}
h3{
 text-align:center; 
 }
 table { 
  border:1px solid #000; 
  font-family: 微軟正黑體; 
  font-size:16px; 
  width:600px;
  border:1px solid #000;
  text-align:left;
  border-collapse:collapse;
  margin:auto;
} 
th { 
  background-color: #009FCC;
  padding:10px;
  border:1px solid #000;
  color:#fff;
} 
td { 
  border:1px solid #000;
  padding:5px;
} 
</style>

</head>
<body>
<%@ include file="/nav-BE" %> 
	<h3>商品資料修改:</h3>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/store/item.do" name="form1"
		enctype="multipart/form-data">
		<table>
		
				<tr>
			
				<td style="text-align:center;";" colspan="2"><img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=${itemVO.itemNo}" />
				</tr>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=itemVO.getItemNo()%></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="itemName" size="45"
					value="<%=itemVO.getItemName()%>" /></td>
			</tr>
			<tr>
				<td>商品詳情:</td>
				<td><input type="TEXT" name="itemDetail" size="45"
					value="<%=itemVO.getItemDetail()%>" /></td>
			</tr>
			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="price" size="45"
					value="<%=itemVO.getPrice()%>" /></td>
			</tr>
			<tr>
				<td>商品庫存:</td>
				<td><input type="TEXT" name="amount" size="45"
					value="<%=itemVO.getAmount()%>" /></td>
			</tr>
			<tr>
				<td>類別:</td>
				<td><select name="itemClass">
						<option value=<%=itemVO.getItemClass()%>>${TransferTool.getClass(itemVO.itemClass)}</option>
						<c:forEach var="X" begin="1" end="7" step="1">
							<option value=${ X }>${TransferTool.getClass(X)}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>上/下架狀態:</td>
				<td><select name="status">
						<option value=<%=itemVO.getStatus()%>>${TransferTool.getStatus(itemVO.status)}</option>
						<c:forEach var="X" begin="0" end="1">
							<option value=${ X } >${TransferTool.getStatus(X)}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>上傳圖片預覽:</td>
				<td><img id="xmTanImg"/></td>
			</tr>


			<tr>
				<td>商品圖片:</td>
				<td><input type="file" name="picture" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*"/></td>
			</tr>
			<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
			<tr><td></td><td> 
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="itemNo" value="<%=itemVO.getItemNo()%>">
		<input type="submit" value="送出修改"></td></tr>
		</table>
	</FORM>
<!-- bootstrap+jquery 2019-07-28 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- bootstrap+jquery 2019-07-28 -->
</body>
       <script type="text/javascript">            
            //判断浏览器是否支持FileReader接口
            if (typeof FileReader == 'undefined') {
                document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
                //使选择控件不可操作
                document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
            }

            //选择图片，马上预览
            function xmTanUploadImg(obj) {
                var file = obj.files[0];
                
                console.log(obj);console.log(file);
                console.log("file.size = " + file.size);  //file.size 单位为byte

                var reader = new FileReader();

                //读取文件过程方法
                reader.onloadstart = function (e) {
                    console.log("开始读取....");
                }
                reader.onprogress = function (e) {
                    console.log("正在读取中....");
                }
                reader.onabort = function (e) {
                    console.log("中断读取....");
                }
                reader.onerror = function (e) {
                    console.log("读取异常....");
                }
                reader.onload = function (e) {
                    console.log("成功读取....");

                    var img = document.getElementById("xmTanImg");
                    img.src = e.target.result;
                    //或者 img.src = this.result;  //e.target == this
                }

                reader.readAsDataURL(file)
            }
        </script>
</html>