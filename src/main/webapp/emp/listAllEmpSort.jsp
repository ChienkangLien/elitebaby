<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
NewsSortService sortSvc = new NewsSortService();
List<NewsSortVO> list = sortSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有種類資料 - listAllEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/emp/images/book_visit.png" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>種類編號</th>
			<th>種類名稱</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="newsSortVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${newsSortVO.sortId}</td>
				<td>${newsSortVO.sortName}</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/NewsSort.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="sortId" value="${newsSortVO.sortId}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/NewsSort.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="sortId" value="${newsSortVO.sortId}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>