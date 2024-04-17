<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fallelove.coupon.model.*"%>

<% CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>優惠券資料修改 update_coupon_input.jsp</title>

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
		 <h3>優惠券資料修改 update_coupon_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

錯誤表列
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="couponServlet.do" name="form1">

<table>
	<tr>
		<td>優惠券編號:<font color=red><b>*</b></font></td>
		<td>${param.coupNo}</td>
	</tr>
	<tr>
		<td>優惠券名稱:</td>
		<td><input type="TEXT" name="coupName" value="${param.coupName}" size="45"/></td>
	</tr>
	<tr>
		<td>優惠條件:</td>
		<td><input type="TEXT" name="coupCond"   value="${param.coupCond}" size="45"/></td>
	</tr>
	<tr>
		<td>折扣比率:</td>
		<td><input type="TEXT" name="coupDisc"   value="${param.coupDisc}" size="45"/></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>建立日期:</td> -->
<%-- 		<td><input name="coupAddDate" id="add_date" type="text" value="${param.coupAddDate}" size="45"></td>  --%>
<!-- 	</tr> -->
	<tr>
		<td>失效日期:</td>
		<td><input name="coupExpDate" id="exp_date" type="text" value="${param.coupExpDate}" size="45"></td> 
	</tr>
	<tr>
		<td>發放日期:</td>
		<td><input type="TEXT" id="rel_date" name="coupRelDate"   value="${param.coupRelDate}" size="45"/></td>
	</tr>
	<tr>
		<td>發放狀態:</td>
		<td><input type="TEXT" name="coupRelStat"   value="${param.coupRelStat}" size="45"/></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(couponVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<%-- <jsp:useBean id="couponSvc" scope="page" class="fallelove.coupon.model.CouponService" /> --%>
<!-- <b>選擇發放狀態:</b> -->
<!--        <select size="1" name="coupRelStat"> -->
<%--          <c:forEach var="couponVO" items="${couponSvc.all}" >  --%>
<%--           <option value="${param.getCoupRelDate()%>">${couponVO.coupRelStat} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="${param.coupNo}">
<input type="submit" value="送出修改"></FORM>

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
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=coupExpDate%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=coupExpDate%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


</script>
</body>
</html>