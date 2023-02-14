<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<%@ page import="com.tibame.web.dao.impl.*"%>
<%@ page import="com.tibame.web.dao.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
   VisitVO visitVO = (VisitVO) request.getAttribute("visitid"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>查詢單一預約參訪資料</title>

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

<h4>查詢單一預約參訪資料</h4>
<table id="table-1">
	<tr><td>
		 <h3>單一預約參訪資料(依信件編號查詢)</h3>
		 <h4><a href="<%=request.getContextPath()%>/visit/VisitFrontPage.jsp">回首頁</a></h4>
	</td></tr>
</table>

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

	</tr>
	<tr>
		<td><%=visitVO.getVisitId()%></td>
		<td><%=visitVO.getUserId()%></td>
		<td><%=visitVO.getUserName()%></td>
		<td><%=visitVO.getPhoneNumber()%></td>
		<td><%=visitVO.getContectTime()%></td>
		<td><%=visitVO.getDueDate()%></td>
		<td><%=visitVO.getEmail()%></td>
		<td><%=visitVO.getKids()%></td>
		<td><%=visitVO.getVisitTime()%></td>
		<td><%=visitVO.getRemark()%></td>
	</tr>
</table>

</body>
</html>