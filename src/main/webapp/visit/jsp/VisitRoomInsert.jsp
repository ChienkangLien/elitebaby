<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.vo.*"%>
<%@ page import="com.tibame.web.dao.impl.*"%>
<%@ page import="com.tibame.web.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增參訪表單</title>
</head>
<body>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增參訪表單</title>

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


<h3>新增預約參訪:</h3><br>
<h4><a href="<%=request.getContextPath()%>/visit/VisitFrontPage.jsp">回首頁</a></h4>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" name="form1">

<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="userid" size="45" 
			/></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="username" size="45" 
			 /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" size="45"
			/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="email" size="45"
			 /></td>
	</tr>
	<tr>
		<td>聯絡時間:</td>
		<td><input type="TEXT" name="contecttime" size="45"
			 /></td>
	</tr>
	<tr>
		<td>產期:</td>
		<td><input name="duedate" id="f_date1" type="date"></td>
	</tr>
	<tr>
		<td>胎次:</td>
		<td><input type="TEXT" name="kids" size="45"
			 /></td>
	</tr>
	<tr>
		<td>參訪日期:</td>
		<td><input name="visittime" id="f_date1" type="date"></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="remark" size="45"
			/></td>
	</tr>

	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>




</body>



