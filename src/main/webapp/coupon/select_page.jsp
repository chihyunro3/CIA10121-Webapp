<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<title>FallELove Coupon Management</title>

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
   <tr><td><h3>FallELove Coupon Management</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for FallELove Coupon Management</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li><a href='listAllCoupon.jsp'>List</a> all Coupons.  <br><br></li>
  	
  	<li>
    <FORM METHOD="post" ACTION="couponServlet.do" >
        <b>��J�u�f��s��:</b>
        <input type="text" name="coupNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <jsp:useBean id="couponSvc" scope="page" class="fallelove.coupon.model.CouponService" />
  
  <li>
     <FORM METHOD="post" ACTION="couponServlet.do" >
       <b>����u�f��s��:</b>
       <select size="1" name="coupNo">
         <c:forEach var="couponVO" items="${couponSvc.all}" > 
          <option value="${couponVO.coupNo}">${couponVO.coupNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="couponServlet.do" >
       <b>����u�f��:</b>
       <select size="1" name="coupNo">
         <c:forEach var="couponVO" items="${couponSvc.all}" > 
          <option value="${couponVO.coupNo}">${couponVO.coupName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>�u�f��޲z</h3>

<ul>
  <li><a href='addCoupon.jsp'>Add</a> a new Coupon.</li>
</ul>


</body>
</html>