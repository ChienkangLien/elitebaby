<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<%@ page import="com.tibame.web.dao.impl.*"%>
<%@ page import="com.tibame.web.dao.*"%>


<%
   VisitVO visitVO = (VisitVO) request.getAttribute("visitid"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新參訪資料</title>
</head>
<body>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>更新參訪資料</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>


<h3>更新預約參訪資料:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" name="form1">


<table>
	<tr>
		<td>會員編號(自動抓取不能更改):</td>
		<td><%=visitVO.getUserId()%></td>
		
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="username" size="45" value="<%=visitVO.getUserName() %>"/></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" size="45" value="<%=visitVO.getPhoneNumber() %>"
			/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="email" size="45" value="<%=visitVO.getEmail() %>"
			 /></td>
	</tr>
	<tr>
		<td>聯絡時間:</td>
		<td><input type="TEXT" name="contecttime" size="45" value="<%=visitVO.getContectTime() %>"
			 /></td>
	</tr>
	<tr>
		<td>產期:</td>
		<td><input name="duedate" id="f_date1" type="date" value="<%=visitVO.getDueDate() %>"></td>
	</tr>
	<tr>
		<td>胎次:</td>
		<td><input type="TEXT" name="kids" size="45" value="<%=visitVO.getKids() %>"
			 /></td>
	</tr>
	<tr>
		<td>參訪日期:</td>
		<td><input name="visittime" id="f_date1" type="date" value="<%=visitVO.getVisitTime() %>"></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="remark" size="45" value="<%=visitVO.getRemark() %>"
			/></td>
	</tr>

	
</table>
<br>
<input type="hidden" name="action" value="update" id="update">
<input type="hidden" name="userid" value="<%=visitVO.getUserId()%>"/>
<input type="hidden" name="visitid" value="<%=visitVO.getVisitId()%>">
<input type="submit" value="送出修改">
</FORM>



</body>



