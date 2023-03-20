<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>

<%
NewsSortService sortSvc = new NewsSortService();
List<NewsSortVO> list = sortSvc.getAll();
pageContext.setAttribute("list", list);
%>

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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/official.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/background_navbar.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/news/css/backgroundOne.css"/>
   <!-- FontAwesom 連結 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

   <title>菁英產後護理之家</title>
</head>

<body class="c2">
  <div class="flex-shrink-0 p-3 c1" id="navbar">
   <a href="/elitebaby/admin/member/background_nav.html">
   <img src="<%=request.getContextPath()%>/admin/news/images/logo.jpg" style="width: 30px" /> 
   <span class="fs-5 fw-semibold" style="color:black">菁英產後護理之家</span>
			 <a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
		</a>
    <!-- =======按鍵======== -->
    <ul class="list-unstyled ps-0">
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#home-collapse" aria-expanded="true">
          會員中心
        </button>
        <div class="collapse" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li>
                <a
                  href="/elitebaby/admin/member/management.html"class="link-dark rounded">員工管理</a>
              </li>
              <li>
                <a href="/elitebaby/admin/member/search.html" class="link-dark rounded">會員管理</a>
              </li>
            </ul>
        </div>
      </li>

      <!-- ================膳食管理============== -->

      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#orders-collapse" aria-expanded="false">
          膳食管理
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/meal/mealorder_background.html" class="link-dark rounded">訂餐管理</a></li>
            <li><a href="/elitebaby/admin/meal/meal_background.html" class="link-dark rounded">商品管理</a></li>
           
          </ul>
        </div>
      </li>
      <!-- ===============房間管理============= -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#dashboard-collapse" aria-expanded="false">
          房間管理
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/room/type.html" class="link-dark rounded">房型維護</a></li>
            <li><a href="/elitebaby/admin/room/order.html" class="link-dark rounded">房間訂單</a></li>
            <li><a href="/elitebaby/admin/room/condition/html" class="link-dark rounded">房況管理</a></li>
          </ul>
        </div>
      </li>
      <!-- =================討論區================ -->
      <li class="mb-1">
        <!-- <button
            class="btn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#texts-collapse"
            aria-expanded="false"
          >
            討論區
          </button> -->
        <!-- 若沒有子元素，單純給一個a標籤即可 -->
        <a href="/elitebaby/admin/forum/backend.html" class="btn bkbtn">討論區</a>
      </li>
      <!-- ================預約參觀============= -->
      <li class="mb-1">
         <button
            class="btn bkbtn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
 data-bs-target="#orders-collapse111"
            aria-expanded="false">
            預約參觀
          </button>
          <div class="collapse" id="orders-collapse111">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li>
                <a href="/elitebaby/admin/visit/getall_visit.html" class="link-dark rounded">預約參觀</a>
              </li>
              <li>
                <a href="/elitebaby/admin/visit/getall_visit_history.html" class="link-dark rounded">歷史參訪</a>
              </li>
              <li>
                <a href="/elitebaby/admin/visit/back_visit_callender.html" class="link-dark rounded" >預約參訪月曆表</a>
              </li>
            </ul>
          </div>
        </li>
      <!-- =============最新消息============ -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#orders-collapse2" aria-expanded="false">
          消息區管理
        </button>
        <div class="collapse" id="orders-collapse2">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/news/listAllNewsMessage.jsp" class="link-dark rounded">留言管理</a></li>
            <li><a href="/elitebaby/admin/news/listAllNewsSort.jsp" class="link-dark rounded">消息種類管理</a></li>
            <li><a href="/elitebaby/admin/news/listAllLatestNews.jsp" class="link-dark rounded">最新消息管理</a></li>
          </ul>
        </div>
      </li>
      <!-- ===================問題回報================== -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#account-collapse" aria-expanded="false">
          問題回報
          </button>
          <div class="collapse" id="account-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="/elitebaby/admin/visit/getall_email.html" class="link-dark rounded">收信匣</a></li>
              <li><a href="/elitebaby/admin/visit/back_admin_mailbox.html" class="link-dark rounded">寄信匣</a></li>
              <li><a href="/elitebaby/admin/visit/report_to_member.html" class="link-dark rounded">發送信件</a></li>
            </ul>
          </div>
        </li>
        <li>
          <!-- Button trigger modal -->
          <button
            type="button"
            id="logoutButton"
            class="btn-logout"
            data-bs-toggle="modal"
            data-bs-target="#staticBackdrop">
            登出
          </button>
        </li>
      </ul>
    </div>
  <div class="c1" id="header" style="z-index: 5"> 
    <p class="t1">後台管理系統</p>
  </div>
  <div id="main_div">
    <div id="blank_area">此處留空</div>
		<div class="t2" id="title">消息管理</div>
		<table>
			<tr>
				<th>種類編號</th>
				<th>種類名稱</th>

			</tr>
			<%-- 					<%@ include file="page1.file"%> --%>

			<%-- 					begin="<%=pageIndex%>" --%>
			<%-- 						end="<%=pageIndex+rowsPerPage-1%>" --%>

			<tr>
				<td>${newsSortVO.sortId}</td>
				<td>${newsSortVO.sortName}</td>
			</tr>
		</table>

			<!-- 登出彈跳視窗Modal -->
    <div
      class="modal fade"
      id="staticBackdrop"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabindex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">確定要登出嗎?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">點擊下面的按鈕以登出</div>
          <div class="modal-footer">
            <button type="button" class="btn" data-bs-dismiss="modal" > 關閉 </button>
            <button type="button" class="btn btn_logout"> 登出</button>
          </div>
        </div>
      </div>
    </div>
	<script src="<%=request.getContextPath()%>/admin/news/vendors/jquery/jquery-3.6.3.min.js"></script>
  <!-- jquery ui連結-->
    <script src="<%=request.getContextPath()%>/admin/news/js/jquery-ui.js"></script>
      <script src="<%=request.getContextPath()%>/js/background_navbar.js"></script>
    <!-- bootstrap引用cdn -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
    <!-- 下載bootstrap引用 -->
    <!-- <script src="./vendors/bootstrap/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"
    ></script> -->
</body>
</html>