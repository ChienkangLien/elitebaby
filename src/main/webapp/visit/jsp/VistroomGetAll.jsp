<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<%@ page import="com.tibame.web.dao.impl.*"%>
<%@ page import="com.tibame.web.dao.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	VisitDAO dao = new VisitDAO();
    List<VisitVO> list = dao.getAll();       // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
    pageContext.setAttribute("list",list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>


<html>
<head>
<title>所有預約參訪資料</title>

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

<h4>預約參訪信件</h4> <br>
<h4><a href="<%=request.getContextPath()%>/visit/VisitFrontPage.jsp">回首頁</a></h4>
<a href="<%=request.getContextPath()%>/visit/VisitRoomInsert.jsp">新增參訪表單</a>

<table>
	<tr>
		<th>信件編號</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>聯絡時間</th>
		<th>預產期</th>
		<th>信箱</th>
		<th>胎次</th>
		<th>參訪時間</th>
		<th>備註內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="VisitVO" items="${list}" >
	<tr>
			<td>${VisitVO.visitId}</td>
			<td>${VisitVO.userId}</td>
			<td>${VisitVO.userName}</td>
			<td>${VisitVO.phoneNumber}</td>
			<td>${VisitVO.contectTime}</td>
			<td>${VisitVO.dueDate}</td>
			<td>${VisitVO.email}</td>
			<td>${VisitVO.kids}</td>
			<td>${VisitVO.visitTime}</td>
			<td>${VisitVO.remark}</td>
			<td>${VisitVO.createTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="visitid"  value="${VisitVO.visitId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" style="margin-bottom: 0px;">
			     <input type="submit" id="delete"value="刪除">
			     <input type="hidden" name="visitid"  value="${VisitVO.visitId}">
			     <input type="hidden" name="userid"  value="${VisitVO.userId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
			<input type="hidden" name="visitId"  value="${VisitVO.visitId}">
	</tr>
	</c:forEach>
</table>



</body>
</html>