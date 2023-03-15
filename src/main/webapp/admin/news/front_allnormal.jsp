<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- bootstrap引用cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
    <!-- 下載bootstrap引用 -->
    <!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css"> -->

    <!-- css連結 -->
    <link rel="stylesheet" href="css\official.css" />
    <link rel="stylesheet" href="./css/front_allnormal.css">
   
    <!-- FontAwesom 連結 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

    <title>菁英產後護理之家</title>

</head>

<body>
    <div id="nav">
        <nav class="navbar navbar-expand-lg navbar-light c1 fw-bold  ">
            <div class="container-fluid">
                <img src="images/logo.jpg"/> 
                <a class="navbar-brand" href="#" id="home">菁英產後護理之家</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" style=" justify-content: space-between;" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">最新消息 </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="#">綜合</a></li>
                                <li><a class="dropdown-item" href="#">優惠</a></li>
                                <li><a class="dropdown-item" href="#">一般</a></li>
                                <li><a class="dropdown-item" href="#">其他</a></li>
                            </ul>
                        <li class="nav-item"><a class="nav-link" href="#">關於我們</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">環境介紹</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">房型介紹</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">月子膳食</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">討論區</a></li>

                        <!-- ====================================會員中心================================================= -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
                                會員中心
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

                                <li><a class="dropdown-item" href="#">編輯會員資料</a></li>
                                <li><a class="dropdown-item" href="#">查詢訂單</a></li>
                                <li> <a class="dropdown-item" href="#">信箱</a> </li>
                                <li> <a class="dropdown-item" href="#">問題回報</a> </li>
                            </ul>
                        </li>
                </div>
                <div id="login">
                    <ul class="navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link " id="login_btn">登入</a></li>
                        <li class="nav-item"><a class="nav-link " href="#">註冊</a></li>
                    </ul>
                </div>
                <!-- ==============================================按鈕觸發============================================ -->
                <!-- ===============================================購物車============================================= -->
                <div id="logout">
                    <ul class="navbar-nav navbar-right">
                        <button id="cart_btn" style="border: 0px; margin-right: 10px;margin-top: 5px; " class="c1"><svg
                                xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                class="bi bi-cart2" viewBox="0 0 16 16">
                                <path
                                    d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
                            </svg>
                            <span class="badge badge-danger navbar-badge ;">5</span>
                        </button>

                        <!-- ===================================鈴鐺==================================== -->
                        <button id="cart_btn" style="border: 0px; margin-right: 10px; margin-top: 5px;" class="c1"><svg
                                xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                class="bi bi-bell" viewBox="0 0 16 16">
                                <path
                                    d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                            </svg>
                            <span class="badge badge-danger navbar-badge ;">3</span>
                        </button>

                        <li class="nav-item"><a class="nav-link " id="logout_btn">登出</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div id="book_visit">
        <a href="#">
            <img class="img-responsive" src="images/book_visit.png" />
        </a>
    </div>
    <div id="blank_area">
        <!-- 此處留空 -->
    </div>
    <ul class="container-filter categories-filter">
        <li>
            <a class="categories active" href="/elitebaby/admin/news/front_allnews.jsp">綜合</a>
        </li>
        <li>
            <a class="categories" href="/elitebaby/admin/news/front_allnormal.jsp">一般</a>
        </li>
        <li>
            <a class="categories" href="/elitebaby/admin/news/front_alldiscount.jsp">優惠</a>
        </li>
    </ul>
    <!-- 搜尋欄位 -->
    <div class="container">
    <form method="post" action="<%=request.getContextPath()%>/Latestnews.do">
            <input class="bar" id="tags" placeholder="搜尋..." name=select>
            <input type="hidden" name="action" value="search">
            <button id="search" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
        <c:forEach var="result" items="${Msgs}">
              <div >${result.postTitle}</div>
  </c:forEach>
    </div>
    
    
<%-- <form method="post" action="<%=request.getContextPath()%>/Latestnews.do"> --%>
<!-- 		<input class="bar" id="tags" placeholder="搜尋..." name=yyy> -->
<!-- 		<input type="hidden" name="action" value="search"> -->
<!-- 		<button id="search" type="submit"> -->
<!-- 			<i class="fas fa-search"></i> -->
<!-- 		</button> -->
<!-- 	</form> -->
<%-- 	<c:forEach var="result" items="${kkk}"> --%>
<%--               <div >${result.postTitle}</div> --%>
<%--   </c:forEach> --%>
    <!-- 圖片內容 -->
    <section class="sec1 -type1">
        <div class="flex_container">

            <div class="flex_items -left">
                <a href="/elitebaby/admin/news/front_allnormal_1.jsp">
                    <img src="./images/環境1.png">
                </a>
            </div>

            <div class="flex_items -right">
                <a href="/elitebaby/admin/news/front_allnormal_1.jsp">
                    <h1 class="title1">日式婦女節</h1>
                    <p class="para">櫃檯客服<br>
                        3/3是日本女兒節，3/8是婦女節，3/8在這裡有日式婦女節，西瓜和服和鳳梨和服，大飽眼福

                        還有隱藏版的明星目前入住在館內喲!

                        櫻花樹目前還在館內，賞櫻趁現在</p>
                </a>
            </div>
        </div>
    </section>

    <section class="sec1 -type2">
        <div class="flex_container">

            <div class="flex_items -left">
                <a href="/elitebaby/admin/news/front_allnormal_2.jsp">
                    <img src="./images/環境2.png">
                </a>
            </div>

            <div class="flex_items -right">
                <a href="/elitebaby/admin/news/front_allnormal_2.jsp">
                    <h1 class="title1">新環境公告</h1>
                    <p class="para">打造最新交誼廳環境讓媽媽們有舒適良好空間</p>
                </a>
            </div>
        </div>
    </section>

    <div>
        <p class="text-center">
            10488 台北市中山區南京東路三段219號5樓｜TEL : 02-2712-0589｜FAX :
            02-2794-0123｜E-mail : service@shinebc.com.tw
        </p>
    </div>
   


	
 <script src="./vendors/jquery/jquery-3.6.3.min.js"></script>
  <!-- jquery ui連結-->
    <script src="js/jquery-ui.js"></script>
    <!-- bootstrap引用cdn -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
    <!-- 下載bootstrap引用 -->
    <!-- <script src="./vendors/bootstrap/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"
    ></script> -->
    <script src="js/front_navbar.js"></script>


</body>
</html>