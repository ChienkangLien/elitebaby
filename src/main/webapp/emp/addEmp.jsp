<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>

<%
String adminId = "1";
String sortId = "1";
LatestNewsVO latestNewsVO = (LatestNewsVO) request.getAttribute("latestNewsVO");
%>
<%-- --<%= latestNewsVO==null %>--${latestNewsVO.sortId}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>最新消息資料新增 - addEmp.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>最新消息資料新增 - addEmp.jsp</h3>
				 <h4><a href="<%=request.getContextPath()%>/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/emp/images/book_visit.png" width="100" height="32" border="0">回首頁</a></h4>
			</td>
			<td>
<!-- 				<h4> -->
<!-- 					<a href="select_page.jsp"><img src="images/tomcat.png" -->
<!-- 						width="100" height="100" border="0">回首頁</a> -->
<!-- 				</h4> -->
			</td>
		</tr>
	</table>
	
		<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
<!-- 	<ul> -->
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
<!-- 	</ul> -->
</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/Latestnews.do" name="form1">
		<table>
			<tr>
				<td>消息內容:</td>
				<td><input type="TEXT" name="newsIntro" size="45"
					value="" /></td>
				<td></td>
			</tr>
			<tr>
				<td>排程時間:</td>
				<td><input type="TEXT" name="publishedTime" size="45"
					value="" /></td>

			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input name="onNews" id="f_date1" type="text" /></td>

			</tr>

			<tr>
				<td>下架時間:</td>
				<td><input name="offNews" id="f_date1" type="text" /></td>

			</tr>

			<tr>
				<td>標題名稱:</td>
				<td><input type="TEXT" name="postTitle" size="45"
					value="" /></td>

			</tr>
		
			<tr>
				<td>種類:<font color=red><b>*</b></font></td>
				<td><select size="1" name="deptno">
						
							<option value="1"
								>一般
						
							<option value="2"
								>優惠
						
							<option value="3"
								>其他		
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="hidden" name="adminId" value="1"> <input
			type="hidden" name="sortId" value="1"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<link rel="stylesheet" type="text/css"
	href="/elitebaby/datetimepicker/jquery.datetimepicker.css" />
<script src="/elitebaby/datetimepicker/jquery.js"></script>
<script
	src="/elitebaby/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '2023-02-20', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
<link rel="stylesheet" type="text/css" href="/elitebaby/datetimepicker/jquery.datetimepicker.css" />
<script src="/elitebaby/datetimepicker/jquery.js"></script>
<script
	src="/elitebaby/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '2023-02-20', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
<link rel="stylesheet" type="text/css" href="/elitebaby/datetimepicker/jquery.datetimepicker.css" />
<script src="/elitebaby/datetimepicker/jquery.js"></script>
<script
	src="/elitebaby/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '2023-02-20', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        </script>
</body>
</html>