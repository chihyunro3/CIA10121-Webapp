<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="fallelove.backend.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ServicePictureService svc = new ServicePictureService();
    List<ServicePictureVO> list = svc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有客服紀錄圖片 - listAllServicePicture.jsp</title>

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
	<tr><td>
		 <h3>所有客服紀錄圖片資料 - listAllServicePic.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>客服圖片編號</th>
		<th>客服紀錄編號</th>
		<th>圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="servicePictureVO" items="${list}">
		
		<tr>
			<td>${servicePictureVO.servicePicNo}</td>
			<td>${servicePictureVO.recordNo}</td>
			<td>
			
				<c:if test="${servicePictureVO.servicePic != null }">
					<img src="data:image/png;base64,${Base64.getEncoder().encodeToString(servicePictureVO.servicePic)}" alt="客服圖片" width="100" ; height="100">
				</c:if>
			
			</td>
			<td>
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/servicePic.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="servicpictureNo"  value="${ServicePictureVO.servicpictureNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"> -->
<!-- 			  </FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"> -->
<!-- 			  </FORM> -->
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>