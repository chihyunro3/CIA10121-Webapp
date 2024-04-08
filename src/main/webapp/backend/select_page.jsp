<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>

<title>FallELove ServiceRecord</title>

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
   <tr><td><h3>FallELove ServiceRecord</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for FallELove ServiceRecord</p>

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
  <li><a href='listAllServicePic.jsp'>List</a> all ServicePictures.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="servicePic.do" >
        <b>��J�ȪA�Ϥ��s�� (�p10):</b>
        <input type="text" name="servicePicNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="servicePicSvc" scope="page" class="fallelove.backend.model.ServicePictureService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="servicePic.do" > -->
<!--        <b>��ܫȪA�Ϥ��s��:</b> -->
<!--        <select size="1" name="servicePicNo"> -->
<%--          <c:forEach var="servicePictureVO" items="${servicePicSvc.all}" >  --%>
<%--           <option value="${servicePictureVO.servicePicNo}">${servicePictureVO.servicePicNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="servicePic.do" > -->
<!--        <b>��ܭ��u�m�W:</b> -->
<!--        <select size="1" name="servicePicNo"> -->
<%--          <c:forEach var="servicePictureVO" items="${servicePicSvc.all}" >  --%>
<%--           <option value="${servicePictureVO.servicePicNo}">${servicePictureVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>



<body>

</body>
</html>