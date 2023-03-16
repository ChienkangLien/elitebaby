<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.web.vo.*"%>

<%

  // EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
	<tr><td>
		 <h3>������ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/emp/images/book_visit.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
<!-- 	<ul> -->
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
<!-- 	</ul> -->
</c:if>

<FORM METHOD="post" ACTION="/elitebaby/Latestnews.do" name="form1">
<table>
    <tr>
		<td>�̷s�����s��:<font color=red><b>*</b></font></td>
		<td>${latestNewsVO.newsId}</td>
	</tr>
	
	<tr>
		<td>�����s��:</td>
		<td><input type="TEXT" name="sortId" size="45"
			 value="${latestNewsVO.sortId}"/></td><td>${latestNewsVO.sortId}</td>
	</tr>
	<tr>
		<td>�޲z���s��:</td>
		<td><input type="TEXT" name="adminId" size="45" 
			 value="${latestNewsVO.adminId}"/></td><td>${latestNewsVO.adminId}</td>
	</tr>
	<tr>
		<td>�������e:</td>
		<td><input type="TEXT" name="newsIntro" size="45" 
			 value="${latestNewsVO.newsIntro}"/></td><td>${latestNewsVO.newsIntro}</td>
	</tr>
	<tr>
		<td>�Ƶ{�ɶ�:</td>
		<td><input name="publishedTime" id="f_date1" type="text"/></td><td>${latestNewsVO.publishedTime}</td>
	</tr>
	<tr>
		<td>�W�[�ɶ�:</td>
		<td><input name="onNews" id="f_date1" type="text"/></td><td>${latestNewsVO.onNews}</td>
	</tr>
	<tr>
		<td>�U�[�ɶ�:</td>
		<td><input name="offNews" id="f_date1" type="text"/></td><td>${latestNewsVO.offNews}</td>
	</tr>
	<tr>
		<td>���D�W��:</td>
		<td><input type="TEXT" name="postTitle" size="45"
			 value="${latestNewsVO.postTitle}"/></td><td>${latestNewsVO.postTitle}</td>
	</tr>
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(param.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="newsId" value="${latestNewsVO.newsId}">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.publishedTime}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

        	<style>
        	  .xdsoft_datetimepicker .xdsoft_datepicker {
        	           width:  300px;   /* width:  300px; */
        	  }
        	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        	           height: 151px;   /* height:  151px; */
        	  }
        	</style>

        	<script>
        	        $.datetimepicker.setLocale('zh');
        	        $('#f_date1').datetimepicker({
        	           theme: '',              //theme: 'dark',
        	 	       timepicker:false,       //timepicker:true,
        	 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
        	 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        	 		   value: '${param.onNews}', // value:   new Date(),
        	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
        	           //startDate:	            '2017/07/10',  // �_�l��
        	           //minDate:               '-1970-01-01', // �h������(���t)���e
        	           //maxDate:               '+1970-01-01'  // �h������(���t)����
        	        });
        	        
        	        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
        	        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
        	        	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

        	        	<style>
        	        	  .xdsoft_datetimepicker .xdsoft_datepicker {
        	        	           width:  300px;   /* width:  300px; */
        	        	  }
        	        	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        	        	           height: 151px;   /* height:  151px; */
        	        	  }
        	        	</style>

        	        	<script>
        	        	        $.datetimepicker.setLocale('zh');
        	        	        $('#f_date1').datetimepicker({
        	        	           theme: '',              //theme: 'dark',
        	        	 	       timepicker:false,       //timepicker:true,
        	        	 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
        	        	 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        	        	 		   value: '${param.offNews}', // value:   new Date(),
        	        	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
        	        	           //startDate:	            '2017/07/10',  // �_�l��
        	        	           //minDate:               '-1970-01-01', // �h������(���t)���e
        	        	           //maxDate:               '+1970-01-01'  // �h������(���t)����
        	        	        });
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>