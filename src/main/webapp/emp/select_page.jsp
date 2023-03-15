<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>IBM Emp: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/elitebaby/Latestnews.do" >
        <b>��J�̷s�����s�� (�p1):</b>
        <input type="text" name="newsId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

<%--   <jsp:useBean id="newsSvc" scope="page" class="com.tibame.web.service.LatestNewsService" /> --%>
  
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="LatestNews.do" > -->
<!--        <b>��ܺ����s��:</b> -->
<!--        <select size="1" name="newsId"> -->
<%--          <c:forEach var="latestNewsVO" items="${newsSvc.all}" >  --%>
<%--           <option value="${latestNewsVO.newsId}">${latestNewsVO.sortId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  

  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="LatestNews.do" > -->
<!--         <b>��J�����s�� (�p1):</b> -->
<!--         <input type="text" name="sortId"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
</ul>


<!-- <h3>���u�޲z</h3> -->

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>