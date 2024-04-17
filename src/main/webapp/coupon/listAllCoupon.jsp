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

<title>�Ҧ��u�f����</title>
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
		 <h3>�Ҧ��u�f���� - listAllCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�u�f��s��</th>
		<th>�u�f������W��</th>
		<th>�ϥα���</th>
		<th>�馩��v</th>
		<th>�إߤ��</th>
		<th>���Ĥ��</th>
		<th>�o����</th>
		<th>�o�񪬺A</th>
		<th>�ק�</th>
		<th>�R��</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="coupNo"  value="${couponVO.coupNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/couponServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="coupNo"  value="${couponVO.coupNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>