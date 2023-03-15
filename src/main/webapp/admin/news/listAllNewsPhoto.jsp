<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>

<%
NewsPhotoService photoSvc = new NewsPhotoService();
List<NewsPhotoVO> list = photoSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- cdn的引用 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous" />
<!-- 下載bootstrap引用 -->
<!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css" /> -->
<link rel="stylesheet" href="css\official.css" />
<link rel="stylesheet" href="./css/bglistAllLatestnews.css" />

<title>菁英產後護理之家</title>
</head>

<body class="c2">
	<div class="flex-shrink-0 p-3 c1" id="navbar">
		<a href="/elitebaby/admin/news/selectNewsPhoto.jsp"><img
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
          </button> --> <!-- 若沒有子元素，單純給一個a標籤即可 --> 
          <a href="#class="btn bkbtn">預約參觀</a>
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
		<!-- <h1>建立表格</h1> -->

<!-- 		<button type="button" class="btn btn-primary" data-bs-toggle="modal" -->
<!-- 			data-bs-target="#latestnews">新增</button> -->
<!-- <td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/Latestnews.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" class="btn btn-add" value="新增">  -->
<!-- 						<input type="hidden" name="newsId" value=${latestNewsVO.newsId}> -->
<!-- 						<input type="hidden" name="action" value="add"> -->
<!-- 					</FORM> -->
<!-- 					</td> -->
		<table>
			<!-- 表提欄位內容 -->
			<thead>
				<tr>
					<th>照片編號</th>
					<th>最新消息編號</th>
					<th>消息照片</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="newsPhotoVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
			</thead>

			<tbody>
				<tr>
					<td>${newsPhotoVO.photoId}</td>
					<td>${newsPhotoVO.newsId}</td>
					<td><img class="figure-img img-fluid rounded" src="NewsPhotoServlet?photoId=${newsPhotoVO.photoId}"></td> 
				
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/NewsPhoto.do"
							style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-Revise" value="修改"> 
							<input type="hidden" name="photoId" value=${newsPhotoVO.photoId}>
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/NewsPhoto.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-delete" value="刪除"> 
						<input type="hidden" name="photoId" value=${newsPhotoVO.photoId}>
						<input type="hidden" name="action" value="delete">
					</FORM>
					</td>
				</tr>
				</c:forEach>
		</table>
		<%@ include file="page2.file"%>
		<!-- bootstrap引用cdn -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
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