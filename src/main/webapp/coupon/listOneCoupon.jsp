<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="fallelove.coupon.model.*"%>

<% CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); %>

<html>
<head>

<title>優惠券資料 - listOneCoupon.jsp</title>

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
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠券編號</th>
		<th>優惠券種類名稱</th>
		<th>使用條件</th>
		<th>折扣比率</th>
		<th>建立日期</th>
		<th>失效日期</th>
		<th>發放日期</th>
		<th>發放狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%=couponVO.getCoupNo()%></td>
		<td><%=couponVO.getCoupName()%></td>
		<td><%=couponVO.getCoupCond()%></td>
		<td><%=couponVO.getCoupDisc()%></td>
		<td><%=couponVO.getCoupAddDate()%></td>
		<td><%=couponVO.getCoupExpDate()%></td>
		<td><%=couponVO.getCoupRelDate()%></td>
		<td><%=couponVO.getCoupRelStat()%></td>
	</tr>
</table>

</body>
</html>