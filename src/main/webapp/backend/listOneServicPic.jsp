<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fallelove.backend.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ServicePictureVO servicePictureVO = (ServicePictureVO) request.getAttribute("servicePictureVO"); 
	//ServicePicServlet.java(Concroller), 存入req的servicePictureVO物件
	String photoBase64 = (String) request.getAttribute("photoBase64");
%>
<!DOCTYPE html>
<html>
<head>

<title>客服紀錄圖片查詢 by 圖片編號 - listOneServicePic.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>客服紀錄圖片 - listOneServicePic.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>客服圖片編號</th>
		<th>客服紀錄編號</th>
		<th>圖片</th>
	</tr>
	<tr>
		<td>${servicePictureVO.servicePicNo}</td>
		<td>${servicePictureVO.recordNo}</td>
		<td>					
				<c:if test="${servicePictureVO.servicePic != null }">
					<img src="data:image/png;base64,${photoBase64}" alt="客服圖片" width="100" ; height="100">
				</c:if>			
		</td>
	</tr>
</table>


</body>
</html>