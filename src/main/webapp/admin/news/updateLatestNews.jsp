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

  <!-- cdn的引用 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
  <!-- 下載bootstrap引用 -->
  <!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css" /> -->
  <link rel="stylesheet" href="css\official.css" />
  <link rel="stylesheet" href="./css/updateLatestNews.css" />

  <title>菁英產後護理之家</title>
</head>
<body class="c2">
	<div class="flex-shrink-0 p-3 c1" id="navbar">
		<a href="/elitebaby/admin/news/selectLatestNews.jsp"><img
			src="images/logo.jpg" style="width: 30px" /> <span
			class="fs-5 fw-semibold">菁英產後護理之家</span> <a href="#"
			class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
		</a> <!-- =======按鍵======== -->

			<ul class="list-unstyled ps-0">
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#home-collapse"
						aria-expanded="true">會員中心</button>
					<div class="collapse" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">用戶資料管理</a></li>
							<li><a href="#" class="link-dark rounded">查詢會員</a></li>
							<li><a href="#" class="link-dark rounded">編輯會員資訊</a></li>
						</ul>
					</div>
				</li>

				<!-- ================膳食管理============== -->

				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#orders-collapse"
						aria-expanded="false">膳食管理</button>
					<div class="collapse" id="orders-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">訂餐管理</a></li>
							<li><a href="#" class="link-dark rounded">預約試吃管理</a></li>
							<li><a href="#" class="link-dark rounded">商品管理</a></li>
							<li><a href="#" class="link-dark rounded">購物車</a></li>
						</ul>
					</div>
				</li>
				<!-- ===============房間管理============= -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#dashboard-collapse"
						aria-expanded="false">房間管理</button>
					<div class="collapse" id="dashboard-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">房型維護</a></li>
							<li><a href="#" class="link-dark rounded">房間訂單</a></li>
							<li><a href="#" class="link-dark rounded">房況管理</a></li>
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
          </button> --> <!-- 若沒有子元素，單純給一個a標籤即可 --> <a href="#"
					class="btn bkbtn">討論區</a>
				</li>
				<!-- ================預約參觀============= -->
				<li class="mb-1">
					<!-- <button
            class="btn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#orders-collapse1"
            aria-expanded="false"
          >
            預約參觀
          </button> --> <!-- 若沒有子元素，單純給一個a標籤即可 --> <a href="#"
					class="btn bkbtn">預約參觀</a>
				</li>
				<!-- =============最新消息============ -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#orders-collapse2"
						aria-expanded="false">消息區管理</button>
					<div class="collapse" id="orders-collapse2">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">留言管理</a></li>
							<li><a href="#" class="link-dark rounded">新增消息種類</a></li>
							<li><a href="#" class="link-dark rounded">發佈消息</a></li>
						</ul>
					</div>
				</li>
				<!-- ===================問題回報================== -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#account-collapse"
						aria-expanded="false">問題回報</button>
					<div class="collapse" id="account-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">回報信件</a></li>
							<li><a href="#" class="link-dark rounded">發送信件</a></li>
						</ul>
					</div>
				</li>
			</ul>
	</div>
	<div class="c1" id="header">
		<p class="t1">後台管理系統</p>
	</div>
	<div id="main_div">
		<div id="blank_area">此處留空</div>
		<div class="t2" id="title">最新消息管理</div>  
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
<!-- 	<ul> -->
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
<!-- 	</ul> -->
</c:if>
     
<FORM METHOD="post" ACTION="/elitebaby/Latestnews.do" name="form1">
<table>
    <tr>
		<td>最新消息編號:<font color=red><b>*</b></font></td>
<%-- 		<td>${latestNewsVO.newsId}</td> --%>
	</tr>
	
	<tr>
		<td>種類編號:</td>
		<td><input type="TEXT" name="sortId" size="45"
			 value="${latestNewsVO.sortId}"/></td>
<%-- 			 <td>${latestNewsVO.sortId}</td> --%>
	</tr>
	<tr>
		<td>管理員編號:</td>
		<td><input type="TEXT" name="adminId" size="45" 
			 value="${latestNewsVO.adminId}"/></td>
<%-- 			 <td>${latestNewsVO.adminId}</td> --%>
	</tr>
	<tr>
		<td>消息內容:</td>
		<td><input type="TEXT" name="newsIntro" size="45" 
			 value="${latestNewsVO.newsIntro}"/></td>
<%-- 			 <td>${latestNewsVO.newsIntro}</td> --%>
	</tr>
	<tr>
		<td>排程時間:</td>
		<td><input name="publishedTime" id="f_date1" type="text"/></td>
<%-- 		<td>${latestNewsVO.publishedTime}</td> --%>
	</tr>
	<tr>
		<td>上架時間:</td>
		<td><input name="onNews" id="f_date1" type="text"/></td>
<%-- 		<td>${latestNewsVO.onNews}</td> --%>
	</tr>
	<tr>
		<td>下架時間:</td>
		<td><input name="offNews" id="f_date1" type="text"/></td>
<%-- 		<td>${latestNewsVO.offNews}</td> --%>
	</tr>
	<tr>
		<td>標題名稱:</td>
		<td><input type="TEXT" name="postTitle" size="45"
			 value="${latestNewsVO.postTitle}"/></td>
<%-- 			 <td>${latestNewsVO.postTitle}</td> --%>
	</tr>
	
	</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="newsId" value="${latestNewsVO.newsId}">
<input type="submit" value="送出修改"></FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.publishedTime}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

        	<style>
        	  .xdsoft_datetimepicker .xdsoft_datepicker {
        	           width:  300px;   /* width:  300px; */
        	  }
        	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        	           height: 151px;   /* height:  151px; */
        	  }
        	</style>

        	<script>
        	        $.datetimepicker.setLocale('zh');
        	        $('#f_date1').datetimepicker({
        	           theme: '',              //theme: 'dark',
        	 	       timepicker:false,       //timepicker:true,
        	 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        	 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        	 		   value: '${param.onNews}', // value:   new Date(),
        	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        	           //startDate:	            '2017/07/10',  // 起始日
        	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
        	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        	        });
        	        
        	        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
        	        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
        	        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

        	        	<style>
        	        	  .xdsoft_datetimepicker .xdsoft_datepicker {
        	        	           width:  300px;   /* width:  300px; */
        	        	  }
        	        	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        	        	           height: 151px;   /* height:  151px; */
        	        	  }
        	        	</style>

        	        	<script>
        	        	        $.datetimepicker.setLocale('zh');
        	        	        $('#f_date1').datetimepicker({
        	        	           theme: '',              //theme: 'dark',
        	        	 	       timepicker:false,       //timepicker:true,
        	        	 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        	        	 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        	        	 		   value: '${param.offNews}', // value:   new Date(),
        	        	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        	        	           //startDate:	            '2017/07/10',  // 起始日
        	        	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
        	        	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        	        	        });
  <!-- bootstrap引用cdn -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
    crossorigin="anonymous"></script>
  <!-- 下載bootstrap引用 -->
  <!-- <script
     src="./vendors/bootstrap/bootstrap.bundle.min.js"
     integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
     crossorigin="anonymous"
   ></script> -->

</body>

</html>