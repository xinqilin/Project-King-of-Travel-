<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.item.controller.*"%>
<% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<%
	Object account = session.getAttribute("account");

%>

<!-- 取得所有商品 -->
<%
	ItemService itemSvc = new ItemService();
	ArrayList itemVO_list = (ArrayList)itemSvc.showStore();
	ItemVO newitemVO1 = (ItemVO)itemVO_list.get(0);
	ItemVO newitemVO2 = (ItemVO)itemVO_list.get(1);
	ItemVO newitemVO3 = (ItemVO)itemVO_list.get(2);
	System.out.println(newitemVO1.getItemName());
	System.out.println(itemVO_list.size());
%>
<!doctype html>
<html lang="en">
  <head>
<title>遊記王</title>
<style type="text/css">

img{
max-height:500px;
}
body{
 font-family: 微軟正黑體 !important;
}
div.container{
  border-color:	#F8F8FF;
border-style:solid;
}
div.card-body{
  height:150px;
}
div.card img{
  mex-height:150px;
}
/* div.card-body{ */
/* height:150px; */
/* } */
.card-img-top{
  height:auto;
  width:auto;
}
img.foot_img{
 max-height:300px;
}
</style>

<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
  </head>
  <body>
  <div class="container">
  	<%@ include file="/nav-f1"%>
<!-- 廣告 -->


    <div class="row">
      <div class="col">
        <h2>熱門景點</h2>
      </div>
    </div>
    <div class="row" style="height:500px">
      <div class="col"style="height:500px">

        <div id="carouselExampleControls1" class="carousel slide" data-ride="carousel">
          <div class="carousel-inner">

            <div class="carousel-item active">
              <div class="row">
                <div class="col-7">
                  <img src="https://pic.pimg.tw/cindy6732/1467102661-2451931092.jpg" class="d-block w-100" alt="...">
                </div>
                  <div class="col-4">
                    <div class="row">
                         <div class="col" style="height:450px">
                        <h5>伏見稻荷大社:</h5>
                        <p>伏見稻荷大社主要是祀奉以宇迦之御魂大神為首的諸位稻荷神，自古以來就是農業與商業的神明，除此之外也配祀包括佐田彥大神、大宮能賣大神、田中大神與四大神等其他的神明。</p>
                          <iframe width='404px' height='200px' frameborder='0' scrolling='auto' marginheight='0' marginwidth='0' style="border:blue dotted 1px;border-radius: 20px;" src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=〒612-0882 京都府京都市伏見区深草藪之内町68&z=16&output=embed&t='>
                          </iframe>
                        </div>
       

                    </div>
                  </div>
              </div>
            </div>

            <div class="carousel-item">
                            <div class="row" style="height:500px">
                <div class="col-7">
                  <img src="https://image.kkday.com/v2/image/get/s1.kkday.com/product_10759/20180301034453_NwH1A/jpg" class="d-block w-100" alt="...">
                </div>
                  <div class="col-4">
                    <div class="row">
                      <div class="col" style="height:450px">
                        <div class="row">
                        <div class="col">
                                                 <h5>東京晴空塔:</h5>
                        <p>東京晴空塔，又譯稱東京天空樹、新東京鐵塔，是位於日本東京都墨田區的電波塔，由東武鐵道及其子公司東武塔晴空塔共同籌建，於2008年7月14日動工，2012年2月29日完工、同年5月22日正式啟用。</p> 
                        </div>
                                                   <iframe width='404px' height='200px' frameborder='0' scrolling='auto' marginheight='0' marginwidth='0' style="border:blue dotted 1px;border-radius: 20px;" src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=東京晴空塔&z=16&output=embed&t='>
                          </iframe> 
                        </div>
                      </div>
                    </div>
                  </div>
              </div>
            </div>



            <div class="carousel-item">
                            <div class="row" style="height:500px">
                <div class="col-7">
                  <img src="https://blog.kkday.com/wp-content/uploads/Taiwan_Taipei_Taipei-101_AShutterstock_28310509.jpg" class="d-block w-100" alt="...">
                </div>
                  <div class="col-4">
                    <div class="row">
                        <div class="col" style="height:450px">
                        <h5>臺北101:</h5>
                        <p>台北101（TAIPEI 101）是位於臺灣臺北市信義區的摩天大樓，樓高509.2公尺（1,671英尺），地上樓層共有101層、另有地下5層，總樓地板面積37萬4千平方公尺，由李祖原聯合建築師事務所設計，KTRT團隊承造，於1999年9月動工，2004年12月31日完工開幕。最初名稱為臺北國際金融中心，2003年改為現名。</p>
                          <iframe width='404px' height='200px' frameborder='0' scrolling='auto' marginheight='0' marginwidth='0' style="border:blue dotted 1px;border-radius: 20px;" src='https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=臺北101&z=16&output=embed&t='>
                          </iframe>
                        </div>
                    </div>
                  </div>
              </div>
            </div>



          </div>
          <a class="carousel-control-prev" href="#carouselExampleControls1" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleControls1" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    </div>
        <!-- 最新商品 -->
    <div class="row">
      <div class="col">
        <h2>最新商品</h2>
      </div>
    </div>

<div class="card-deck">
  <div class="card">
      <img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=<%=newitemVO1.getItemNo()%>" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title"><%=newitemVO1.getItemName()%></h5>
      <p>優惠價：<font  size="5px" color="red"><%=newitemVO1.getPrice()%></font>元</p>
      <p class="card-text"><%=newitemVO1.getItemDetail()%></p>
    </div>
  </div>
  <div class="card">
    <img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=<%=newitemVO2.getItemNo()%>" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title"><%=newitemVO2.getItemName()%></h5>
      <p>優惠價：<font color="red"><%=newitemVO2.getPrice()%></font>元</p>
      <p class="card-text"><%=newitemVO2.getItemDetail()%></p>
    </div>
  </div>
  <div class="card">
    <img src="<%=request.getContextPath()%>/DBGifReader4.do?itemNo=<%=newitemVO3.getItemNo()%>" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title"><%=newitemVO3.getItemName()%></h5>
      <p>優惠價：<font color="red"><%=newitemVO3.getPrice()%></font>元</p>
      <p class="card-text"><%=newitemVO3.getItemDetail()%></p>
    </div>
  </div>
</div>
<!-- 最新行程 -->
<br><br><br>
    <div class="row">
      <div class="col-6">
        <%
        TripService trip1 = new TripService();
        TripVO tripVO=new TripVO();
        tripVO=trip1.getlastOne();
        %>
                 <h2>最新行程</h2>
                     <h5>行程名稱:<%=tripVO.getTripname()%></h5>
          <a href="<%=request.getContextPath()%>/front-end/tripDetails/fromIndex.jsp?tripVO=<%=tripVO.getTripno()%>"><%if(tripVO.getTrippic()!=null){ %>
        <img class="foot_img"  src="<%=request.getContextPath()%>/trip/TripPic?tripno=<%=tripVO.getTripno() %>" class="card-img-top" alt="...">
         <%}else{ %>
         <img class="foot_img"  src="<%=request.getContextPath()%>/NoData/1.jpg" class="card-img-top" alt="...">
         <%} %></a>
        <font>瀏覽次數:<%=tripVO.getTimeofviews()%></font>
      </div>
 <!-- 最新活動 -->    
      <div class="col-6">
        <h2>活動</h2>
        <h5>活動名稱:..........................</h5>
        <img class="foot_img" src="<%=request.getContextPath()%>/images/event_logo.png">
      </div>
    </div>

<!--   container -->
  </div>
  
  
  <br><br><br><br><br><br>
  
  
 </body>
</html>
