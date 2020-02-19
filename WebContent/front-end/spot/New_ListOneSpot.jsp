<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.city.model.*"%>

<%
//   SpotListVO spotListVO = (SpotListVO) request.getAttribute("spotListVO"); //SpotListServlet.java(Concroller), 存入req的spotListVO物件
//   pageContext.setAttribute("spotListVO",spotListVO);
	String cityno=(String)request.getParameter("cityno");
	String spotno=(String)request.getParameter("spotno");
	SpotListService spotSvc=new SpotListService();
	SpotListVO spotVO=spotSvc.getOneSpot(spotno);
	System.out.println("spotno=?"+spotVO.getSpotName());
	List<SpotListVO> spots=new ArrayList<>();
	for(SpotListVO spots1:spotSvc.getAll()){
		if(spots1.getCityNo().equals(cityno)){
			spots.add(spots1);
		}
		
	}
		pageContext.setAttribute("list", spots);
// 	System.out.println(spotVO.getSpotName());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>景點頁面</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

<style type="text/css">
	body{
		font-family: 微軟正黑體 !important;
	}

	.photo{
		width: 90%;
		height: 40%;
		border-radius: 20px;
	}
	table{
		border: 2px solid;
		border-color: #DDDDDD; 
		width: 30%;
		height: 60%;
		border-radius: 20px;
		padding-left: 1%;
		padding-right: 1%;
	}

	.card{
		border-radius: 20px;
	}
</style>
</head>
<body>
 <!--nav請放在body第一行-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="nanana">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">遊記王</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          
         
       <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/front-end/trip/trip_index.jsp" tabindex="-1" >行程</a>
      </li> 
          
          
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/front-end/spot/SpotIndex.jsp" tabindex="-1" >景點</a>
      </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              遊記
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/listArticleOrderByTime.jsp">觀看遊記</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp">分享遊記</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp">查看個人遊記</a>
            </div>
          </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             商城
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/item_list_all.jsp">商城首頁</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/f_item_list_all.jsp">已追蹤商品</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/store/storeOrd_list_all.jsp">我的訂單</a>
            </div>
          </li>
                          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             代購
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItemHomepage.jsp">代購商城</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItem_sellList.jsp">待賣區</a>
              <a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/WishItem/wishItem_buyList.jsp">待買區</a>
              
            </div>
          </li>
                <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              競猜
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/activity/activity2.jsp">競猜</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
            
            
            
            <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/front-end/mem/new_ListOneMem.jsp" tabindex="-1" >會員</a>
     		 </li>
         
<%--         <c:if test="${account==null}"><li class="nav-item" > <li><a  class="nav-link"  href="<%=request.getContextPath()%>/login.jsp">登入</a></li></c:if> --%>
		<c:if test="${account!=null}"><li class="nav-item"> 
		<li><a class="nav-link" href="#" style="text-decoration:underline; color:white;">
			你好! ${account}</a></li>
<%-- 		<li><a class="nav-link" href="<%=request.getContextPath()%>/Logouthandler.do">登出</a></li> --%>
			
			
			</c:if>
        </ul>
      </div>
    </nav>
     
     
  
     
    <!-- nav -->
    <table align="center" valign="center">
    	<tr>
	    	<td>
	    		<font size="5"><strong><%=spotVO.getSpotName() %></strong></font>
	    	</td>
    	</tr>
    	<c:forEach var="spotListVO" items="${list}" >
    	<tr>
    		<td colspan="2" style="text-align: center;">
    			<img style="height:100%;" class="photo" src="<%=request.getContextPath()%>/back-end/spot/getspotphoto.do?spotno=<%=spotVO.getSpotNo()%>">
    		</td>
    	</tr>
    	</c:forEach>
    	<tr>
    		<td style="text-align: center;">
    			<button style="background-color: #0088A8; border-radius: 10px 10px 10px 10px;">
    				<img src="image/icons8-edit-file-23.png">
    				<font color="#FFFFF" size="4">發表文章<b></font>
    			</button>
    			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
    			<button style="background-color: #FF5511; border-radius: 10px 10px 10px 10px;">
    				<img src="image/icons8-traveler-25.png">
    				<font color="#FFFFF" size="4">加入行程<b></font>
    			</button>
    			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
    			<button style="background-color: #66DD00; border-radius: 10px 10px 10px 10px;">
    				<img src="image/icons8-heart-20.png">
    				<font color="#FFFFF" size="4">加入收藏<b></font>
    			</button>
    		</td>
    	</tr>
    	<tr><td><hr width="95%;"></td></tr>
    	<tr>
    		<td>
    			<center>
	    			<div style="background-color: #888888;border-radius: 8px;width:100%;">
    				<img src="image/icons8-information-20.png">
    				<font size="4" color="#FFFFFF" ><strong>簡介：</strong></font>
    				</div>
    			</center>
    			<div><%=spotVO.getSpotDetail()%></div>
    		</td>
    	</tr>
    	<tr><td><hr width="95%;"></td></tr>
    	<tr>
    		<td>
    			<center>
    			<div style="background-color: #888888;border-radius: 8px;width:100%;">
    				<img src="image/icons8-marker-20.png">
    				<font size="4" color="#FFFFFF"><strong>地址：</strong></font>
    			</div>
    			</center>
    			<div><%=spotVO.getLocation()%></div>
    		</td>
    	</tr>
    	<tr><td><hr width="95%;"></td></tr>
    	<tr>
    		<td>
    			<center>
    			<div style="background-color: #888888;border-radius: 8px;width:100%;">
    				<img src="image/icons8-tags-20.png">
    				<font size="4" color="#FFFFFF"><strong>種類：</strong></font>
    			</div>
    			</center>
    			<div>
    			<%if(spotVO.getSpotType()==0){ %>
    				旅館
    			<%}else if(spotVO.getSpotType()==1){ %>
    				餐廳
    			<%}else if(spotVO.getSpotType()==2){ %>
    				景點 
    			<%}else if(spotVO.getSpotType()==3){ %>
    				遊樂園
    			<%}else if(spotVO.getSpotType()==4){ %>
    				寺廟
    			<%}else if(spotVO.getSpotType()==5){ %>
    				購物中心
    			<%}else{%>
    				其他
    			<%}%>
    			</div>
    		</td>
    	</tr>
    	<tr><td><hr width="95%;"></td></tr>
    </table>
    <hr width="65%" >
<!--     <table align="center"> -->
<!--     	<tr> -->
<!--     		<td> -->
<!--     			<h3>相關行程</h3> -->
<!--     		</td> -->
<!--     	</tr> -->
<!--     	<tr> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!--     		</td> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>    			 -->
<!--     		</td> -->
<!--     		<td> -->
<!--      			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>   			 -->
<!--     		</td> -->
<!--     		<tr> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!--     		</td> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>    			 -->
<!--     		</td> -->
<!--     		<td> -->
<!--      			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>   			 -->
<!--     		</td> -->
<!--     	</tr> -->
<!--     </table> -->
<!--         <hr width="65%" > -->
<!--     <table align="center"> -->
<!--     	<tr> -->
<!--     		<td> -->
<!--     			<h3>相關遊記</h3> -->
<!--     		</td> -->
<!--     	</tr> -->
<!--     	<tr> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!--     		</td> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>    			 -->
<!--     		</td> -->
<!--     		<td> -->
<!--      			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>   			 -->
<!--     		</td> -->
<!--     		<tr> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!--     		</td> -->
<!--     		<td> -->
<!--     			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>    			 -->
<!--     		</td> -->
<!--     		<td> -->
<!--      			<div class="card" style="width: 18rem;"> -->
<!-- 				  <img src="..." class="card-img-top" alt="..."> -->
<!-- 				  <div class="card-body"> -->
<!-- 				    <h5 class="card-title">Card title</h5> -->
<!-- 				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
<!-- 				    <a href="#" class="btn btn-primary">Go somewhere</a> -->
<!-- 				  </div> -->
<!-- 				</div>   			 -->
<!--     		</td> -->
<!--     </table> -->

<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> -->
</body>

</html>