<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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

<!-- cdn���ޥ� -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous" />
<!-- �U��bootstrap�ޥ� -->
<!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css" /> -->
<link rel="stylesheet" href="./css/official.css" />
<link rel="stylesheet" href="./css/bglistAllLatestnews.css" />
 <!-- jquery ui�s��-->
  <script src="js/jquery-ui.js"></script>
   <!-- FontAwesom �s�� -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
<title>�׭^�����@�z���a</title>
</head>

<body class="c2">
	<div class="flex-shrink-0 p-3 c1" id="navbar">
		<a href="/elitebaby/admin/news/listAllNewsSort.jsp">
		<img src="images/logo.jpg" style="width: 30px" />
		 <span class="fs-5 fw-semibold">�׭^�����@�z���a</span>
		  <a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom"></a> 
		  
		  <!-- =======����======== -->

			<ul class="list-unstyled ps-0">
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#home-collapse"
						aria-expanded="true">�|������</button>
					<div class="collapse" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">�Τ��ƺ޲z</a></li>
							<li><a href="#" class="link-dark rounded">�d�߷|��</a></li>
							<li><a href="#" class="link-dark rounded">�s��|����T</a></li>
						</ul>
					</div>
				</li>

				<!-- ================�����޲z============== -->

				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#orders-collapse"
						aria-expanded="false">�����޲z</button>
					<div class="collapse" id="orders-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">�q�\�޲z</a></li>
							<li><a href="#" class="link-dark rounded">�w���զY�޲z</a></li>
							<li><a href="#" class="link-dark rounded">�ӫ~�޲z</a></li>
							<li><a href="#" class="link-dark rounded">�ʪ���</a></li>
						</ul>
					</div>
				</li>
				<!-- ===============�ж��޲z============= -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#dashboard-collapse"
						aria-expanded="false">�ж��޲z</button>
					<div class="collapse" id="dashboard-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">�Ы����@</a></li>
							<li><a href="#" class="link-dark rounded">�ж��q��</a></li>
							<li><a href="#" class="link-dark rounded">�Ъp�޲z</a></li>
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
          </button> --> <!-- �Y�S���l�����A��µ��@��a���ҧY�i --> <a href="#"
					class="btn bkbtn">�Q�װ�</a>
				</li>
				<!-- ================�w�����[============= -->
				<li class="mb-1">
					<!-- <button
            class="btn btn-toggle align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#orders-collapse1"
            aria-expanded="false"
          >
            �w�����[
          </button> --> <!-- �Y�S���l�����A��µ��@��a���ҧY�i --> <a href="#"
					class="btn bkbtn">�w�����[</a>
				</li>
				<!-- =============�̷s����============ -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#orders-collapse2"
						aria-expanded="false">�����Ϻ޲z</button>
					<div class="collapse" id="orders-collapse2">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">�d���޲z</a></li>
							<li><a href="#" class="link-dark rounded">�s�W��������</a></li>
							<li><a href="#" class="link-dark rounded">�o�G����</a></li>
						</ul>
					</div>
				</li>
				<!-- ===================���D�^��================== -->
				<li class="mb-1">
					<button
						class="btn bkbtn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#account-collapse"
						aria-expanded="false">���D�^��</button>
					<div class="collapse" id="account-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">�^���H��</a></li>
							<li><a href="#" class="link-dark rounded">�o�e�H��</a></li>
						</ul>
					</div>
				</li>
			</ul>
	</div>
	<div class="c1" id="header">
		<p class="t1">��x�޲z�t��</p>
	</div>
	<div id="main_div">
		<div id="blank_area">���B�d��</div>
		<div class="t2" id="title">���������޲z</div>
		<!-- <h1>�إߪ��</h1> -->
	<div class="container">
 
    <!-- �j�M��� -->
		<FORM METHOD="post" ACTION="/elitebaby/NewsSort.do">
		 <button style="margin-left: 10px"class="btn btn-Revise"><a href='/elitebaby/admin/news/addNewsSort.jsp'>�s�W</a></button>
<!-- 			<b>��J�����s�� (�p1):</b> -->
			  <input class="bar" id="tags" placeholder="�j�M..." name="sortId">
				<input type="hidden" name="action" value="getOne_For_Display"> 
				 <button id="search" type="submit">
                <i class="fas fa-search"></i>
            </button>
		</FORM>
		<table>
			<!-- ����줺�e -->
			<thead>
				<tr>
					
					<th>�����s��</th>
					<th>�����W��</th>
					<th>�ק�</th>
					<th>�R��</th>
				</tr>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="newsSortVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
			</thead>

			<tbody>
				<tr>
					<td>${newsSortVO.sortId}</td>
					<td>${newsSortVO.sortName}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/NewsSort.do"
							style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-Revise" value="�ק�"> 
							<input type="hidden" name="sortId" value=${newsSortVO.sortId}>
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/NewsSort.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-delete" value="�R��"> 
						<input type="hidden" name="sortId" value=${newsSortVO.sortId}>
						<input type="hidden" name="action" value="delete">
					</FORM>
					</td>
				</tr>
				</c:forEach>
		</table>
		</div>
		<%@ include file="page2.file"%>
		<!-- bootstrap�ޥ�cdn -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
			crossorigin="anonymous"></script>
		<!-- �U��bootstrap�ޥ� -->
		<!-- <script
     src="./vendors/bootstrap/bootstrap.bundle.min.js"
     integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
     crossorigin="anonymous"
   ></script> -->
</body>

</html>