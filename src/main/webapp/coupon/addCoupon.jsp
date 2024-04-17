<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fallelove.coupon.model.*"%>

<% CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�u�f���Ʒs�W</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
  	width: 350px;
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
		 <h3>�u�f���Ʒs�W - addCouponEmp.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="120" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="couponServlet.do" name="form1">
<table>
	<tr>
		<td>�u�f��W��:</td>
		<td><input type="TEXT" name="coupName" value="${param.coupName}" size="45"/></td> <td>${errorMsgs.coupName}</td>
	</tr>
	<tr>
		<td>�u�f����:</td>
		<td><input type="TEXT" name="coupCond" value="${param.coupCond}" size="45"/></td> <td>${errorMsgs.coupCond}</td>
	</tr>
	<tr>
		<td>�馩��v:</td>
		<td><input type="TEXT" name="coupDisc" value="${param.coupDisc}" size="45"></td> <td>${errorMsgs.coupDisc}</td>
	</tr>
	<tr>
		<td>���Ĥ��:</td>
		<td><input name="coupExpDate" id="exp_date" type="text" ></td> <td>${errorMsgs.coupExpDate}</td>
	</tr>
	<tr>
		<td>�o����:</td>
		<td><input name="coupRelDate" id="rel_date" type="text" ></td> <td>${errorMsgs.coupRelDate}</td>
	</tr>
</table>
<jsp:useBean id="couponSvc" scope="page" class="fallelove.coupon.model.CouponService" />
<b>��ܵo�񪬺A:</b>
       <select size="1" name="coupRelStat">
         <c:forEach var="couponVO" items="${couponSvc.all}" > 
          <option value="${couponVO.coupRelStat}">${couponVO.coupRelStat}
         </c:forEach>   
       </select>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>

<% 
  java.sql.Date coupExpDate = null;
  try {
	  coupExpDate = java.sql.Date.valueOf(request.getParameter("coupExpDate").trim());
   } catch (Exception e) {
	   coupExpDate = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
var somedate1 = new Date();
     $('#exp_date').datetimepicker({
         beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
             ) {
                  return [false, ""]
             }
             return [true, ""];
     }});
$('#exp_date').datetimepicker({
    theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=coupExpDate%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});

$.datetimepicker.setLocale('zh');
var somedate1 = new Date();
     $('#rel_date').datetimepicker({
         beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
             ) {
                  return [false, ""]
             }
             return [true, ""];
     }});
$('#rel_date').datetimepicker({
    theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=coupExpDate%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});


</script>


</body>
</html>