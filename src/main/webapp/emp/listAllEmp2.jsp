<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>

<% 
 LatestNewsService newsSvc = new LatestNewsService();
     List<LatestNewsVO> list = newsSvc.getAll();
     pageContext.setAttribute("list",list);
 %>
 
 <!DOCTYPE html>
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
				<h3>所有消息資料 - listAllEmp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>最新消息編號</th>
			<th>種類編號</th>
			<th>管理員編號</th>
			<th>消息內容</th>
			<th>排程時間</th>
			<th>上架時間</th>
			<th>下架時間</th>
			<th>標題名稱</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="latestNewsVO" items="${list}" 
		begin="<%=pageIndex%>"
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
						<input type="submit" value="修改"> <input type="hidden"
							name="newsId" value="${latestNewsVO.newsId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Latestnews.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
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