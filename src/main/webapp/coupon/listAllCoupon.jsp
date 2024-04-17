<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="fallelove.coupon.model.*"%>

<%
	CouponService couponSvc = new CouponService();
	List<CouponVO> list = couponSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>

<title>所有優惠券資料</title>
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

<table id="table-1">
	<tr><td>
		 <h3>所有優惠券資料 - listAllCoupon.jsp</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="couponVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${couponVO.coupNo}</td>
			<td>${couponVO.coupName}</td>
			<td>${couponVO.coupCond}</td>
			<td>${couponVO.coupDisc}</td>
			<td>${couponVO.coupAddDate}</td>
			<td>${couponVO.coupExpDate}</td> 
			<td>${couponVO.coupRelDate}</td>
			<td>${couponVO.coupRelStat}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/couponServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coupNo"  value="${couponVO.coupNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/couponServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="coupNo"  value="${couponVO.coupNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>