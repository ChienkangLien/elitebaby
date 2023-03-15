<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.web.vo.*"%>

<%
LatestNewsVO latestNewsVO = (LatestNewsVO) request.getAttribute("latestNewsVO");
%>
<%-- --<%= latestNewsVO==null %>--${latestNewsVO.sortId}-- --%>

<html>
<head>
<title>������� - listOneEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>������� - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/emp/images/book_visit.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�̷s�����s��</th>
			<th>�����s��</th>
			<th>�޲z���s��</th>
			<th>�������e</th>
			<th>�Ƶ{�ɶ�</th>
			<th>�W�[�ɶ�</th>
			<th>�U�[�ɶ�</th>
			<th>���D�W��</th>
			
	</tr>
	<tr>
		<td>${latestNewsVO.newsId}</td>
				<td>${latestNewsVO.sortId}</td>
				<td>${latestNewsVO.adminId}</td>
				<td>${latestNewsVO.newsIntro}</td>
				<td>${latestNewsVO.publishedTime}</td>
				<td>${latestNewsVO.onNews}</td>
				<td>${latestNewsVO.offNews}</td>
				<td>${latestNewsVO.postTitle}</td>
	</tr>
</table>

</body>
</html>