<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約參觀首頁</title>

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
<body>
<ul>
  <a href='<%=request.getContextPath()%>/visit/VistroomGetAll.jsp'>全部預約參訪表單</a><br><br>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" >
        <b>輸入信件編號 (如1,2,3):</b>
        <input type="text" name="visitid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="visitDao" scope="page" class="com.tibame.web.dao.impl.VisitDAO" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VisitController" >
       <b>選擇會員編號:</b>
       <select size="1" name="empno">
         <c:forEach var="visitVO" items="${visitDao.all}" > 
          <option value="${visitVO.userId}">${visitVO.userId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  


<h3>======================================</h3>

<ul>
  <li><a href='VisitRoomInsert.jsp'>Add</a> 新增預約表單</li>
</ul>
</body>
</html>