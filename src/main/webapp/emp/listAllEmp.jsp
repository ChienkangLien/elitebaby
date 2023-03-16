<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<!-- �����m�߱ĥ� EL ���g�k���� -->

<% 
 LatestNewsService newsSvc = new LatestNewsService();
     List<LatestNewsVO> list = newsSvc.getAll();
     pageContext.setAttribute("list",list);
 %>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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
	width: 800px;
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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ�������� - listAllEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/emp/images/book_visit.png" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
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
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="latestNewsVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${latestNewsVO.newsId}</td>
				<td>${latestNewsVO.sortId}</td>
				<td>${latestNewsVO.adminId}</td>
				<td>${latestNewsVO.newsIntro}</td>
				<td>${latestNewsVO.publishedTime}</td>
				<td>${latestNewsVO.onNews}</td>
				<td>${latestNewsVO.offNews}</td>
				<td>${latestNewsVO.postTitle}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Latestnews.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="newsId" value="${latestNewsVO.newsId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Latestnews.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="newsId" value="${latestNewsVO.newsId}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>