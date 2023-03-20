<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
 <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- bootstrap�ޥ�cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
    <!-- �U��bootstrap�ޥ� -->
    <!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css"> -->
<!-- css�s�� -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/official.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/background_navbar.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/admin/news/css/backgroundAll.css"/>
   <!-- FontAwesom �s�� -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

  <title>�׭^�����@�z���a</title>
</head>

<body class="c2">
  <div class="flex-shrink-0 p-3 c1" id="navbar">
   <a href="/elitebaby/admin/member/background_nav.html">
   <img src="<%=request.getContextPath()%>/admin/news/images/logo.jpg" style="width: 30px" /> 
   <span class="fs-5 fw-semibold" style="color:black">�׭^�����@�z���a</span>
			 <a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
		</a>
    <!-- =======����======== -->
    <ul class="list-unstyled ps-0">
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#home-collapse" aria-expanded="true">
          �|������
        </button>
        <div class="collapse" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li>
                <a
                  href="/elitebaby/admin/member/management.html"class="link-dark rounded">���u�޲z</a>
              </li>
              <li>
                <a href="/elitebaby/admin/member/search.html" class="link-dark rounded">�|���޲z</a>
              </li>
            </ul>
        </div>
      </li>

      <!-- ================�����޲z============== -->

      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#orders-collapse" aria-expanded="false">
          �����޲z
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/meal/mealorder_background.html" class="link-dark rounded">�q�\�޲z</a></li>
            <li><a href="/elitebaby/admin/meal/meal_background.html" class="link-dark rounded">�ӫ~�޲z</a></li>
           
          </ul>
        </div>
      </li>
      <!-- ===============�ж��޲z============= -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#dashboard-collapse" aria-expanded="false">
          �ж��޲z
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/room/type.html" class="link-dark rounded">�Ы����@</a></li>
            <li><a href="/elitebaby/admin/room/order.html" class="link-dark rounded">�ж��q��</a></li>
            <li><a href="/elitebaby/admin/room/condition/html" class="link-dark rounded">�Ъp�޲z</a></li>
          </ul>
        </div>
      </li>
      <!-- =================�Q�װ�================ -->
      <li class="mb-1">
        <!-- <button
            class="btn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#texts-collapse"
            aria-expanded="false"
          >
            �Q�װ�
          </button> -->
        <!-- �Y�S���l�����A��µ��@��a���ҧY�i -->
        <a href="/elitebaby/admin/forum/backend.html" class="btn bkbtn">�Q�װ�</a>
      </li>
      <!-- ================�w�����[============= -->
      <li class="mb-1">
         <button
            class="btn bkbtn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
 data-bs-target="#orders-collapse111"
            aria-expanded="false">
            �w�����[
          </button>
          <div class="collapse" id="orders-collapse111">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li>
                <a href="/elitebaby/admin/visit/getall_visit.html" class="link-dark rounded">�w�����[</a>
              </li>
              <li>
                <a href="/elitebaby/admin/visit/getall_visit_history.html" class="link-dark rounded">���v�ѳX</a>
              </li>
              <li>
                <a href="/elitebaby/admin/visit/back_visit_callender.html" class="link-dark rounded" >�w���ѳX����</a>
              </li>
            </ul>
          </div>
        </li>
      <!-- =============�̷s����============ -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#orders-collapse2" aria-expanded="false">
          �����Ϻ޲z
        </button>
        <div class="collapse" id="orders-collapse2">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="/elitebaby/admin/news/listAllNewsMessage.jsp" class="link-dark rounded">�d���޲z</a></li>
            <li><a href="/elitebaby/admin/news/listAllNewsSort.jsp" class="link-dark rounded">���������޲z</a></li>
            <li><a href="/elitebaby/admin/news/listAllLatestNews.jsp" class="link-dark rounded">�̷s�����޲z</a></li>
          </ul>
        </div>
      </li>
      <!-- ===================���D�^��================== -->
      <li class="mb-1">
        <button class="btn bkbtn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse"
          data-bs-target="#account-collapse" aria-expanded="false">
          ���D�^��
          </button>
          <div class="collapse" id="account-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="/elitebaby/admin/visit/getall_email.html" class="link-dark rounded">���H�X</a></li>
              <li><a href="/elitebaby/admin/visit/back_admin_mailbox.html" class="link-dark rounded">�H�H�X</a></li>
              <li><a href="/elitebaby/admin/visit/report_to_member.html" class="link-dark rounded">�o�e�H��</a></li>
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
            �n�X
          </button>
        </li>
      </ul>
    </div>
  <div class="c1" id="header" style="z-index: 5"> 
    <p class="t1">��x�޲z�t��</p>
  </div>
	<div id="main_div">
		<div id="blank_area">���B�d��</div>
		<div class="t2" id="title">�̷s�����޲z-�j�M</div>
	</div>
	
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<ul style="margin-left:500px">
  <li><a href='/elitebaby/admin/news/listAllNewsSort.jsp'>���</a><br><br></li>
	<li>
		<FORM METHOD="post" ACTION="/elitebaby/NewsSort.do">
			<b>��J�����s�� (�p1):</b> <input type="text" name="sortId"> <input
				type="hidden" name="action" value="getOne_For_Display"> <input
				type="submit" value="�e�X">
		</FORM>
	</li>

	<ul>
		<li><a href='/elitebaby/admin/news/addNewsSort.jsp'>�s�W</a> </li>
	</ul>
</ul>
	<!-- �n�X�u������Modal -->
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
            <h5 class="modal-title" id="staticBackdropLabel">�T�w�n�n�X��?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">�I���U�������s�H�n�X</div>
          <div class="modal-footer">
            <button type="button" class="btn" data-bs-dismiss="modal" > ���� </button>
            <button type="button" class="btn btn_logout"> �n�X</button>
          </div>
        </div>
      </div>
    </div>
	<script src="<%=request.getContextPath()%>/admin/news/vendors/jquery/jquery-3.6.3.min.js"></script>
  <!-- jquery ui�s��-->
    <script src="<%=request.getContextPath()%>/admin/news/js/jquery-ui.js"></script>
      <script src="<%=request.getContextPath()%>/js/background_navbar.js"></script>
    <!-- bootstrap�ޥ�cdn -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
    <!-- �U��bootstrap�ޥ� -->
    <!-- <script src="./vendors/bootstrap/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"
    ></script> -->
</body>
</html>