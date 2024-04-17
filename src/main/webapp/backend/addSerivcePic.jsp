<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="fallelove.backend.model.*"%>

<%ServicePictureVO servicePictureVO = (ServicePictureVO) request.getAttribute("servicePictureVO");%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ȪA��Ʒs�W</title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
  	width: 350px;
    border: 0px solid #CCCCFF;
  }
  th, td {
  	
    padding: 1px;
  }
</style>

<style>
      input[disabled]{
        background-color: #eee;
        cursor: not-allowed;
      }
      #drop_zone{
        border: 1px dashed #ccc;
        height: 200px;
        position: relative;
      }
      #drop_zone span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: -1;
        color: lightgray;
      }
      #drop_zone.-on{
        border: 1px dashed lightblue;
        box-shadow: 3px 3px 5px lightblue inset, -3px -3px 5px lightblue inset;
      }
      #preview{
        border: 1px solid lightgray;
        display: inline-block;
        width: 250px;
        min-height: 375px;
        position: relative;
      }
      #preview span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: -1;
        color: lightgray;
      }
      #preview img.preview_img{
        width: 100%;
      }
    </style>

</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>�ȪA��Ʒs�W - addServicePic.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="servicePic.do" name="form1" id="the_form" enctype="multipart/form-data">

<table>
	
	
	<tr>
		<td>�ȪA�Ϥ��s��:</td>
		<td><input type="TEXT" name="servicePicNo" value="<%= (servicePictureVO ==null)? "123" : servicePictureVO.getServicePicNo()%>" size="10"/></td>
	</tr>
	<jsp:useBean id="servicePicSvc" scope="page" class="fallelove.backend.model.ServicePictureService" />
	<tr>
		<td>�ȪA�����s��:<font color=red><b>*</b></font></td>
		<td><select size="1" name="recordNo">
			<c:forEach var="servicePictureVO" items="${servicePicSvc.all}">
				<option value="${servicePictureVO.recordNo}">${servicePictureVO.recordNo}
			</c:forEach>
			
		</select></td>
	</tr>
	

<!-- 	<tr> -->
<!-- 		<td>�Ϥ�</td> -->
<!-- 		<td><input name="servicePic" id="f_date1" type="image" ></td> -->
<!-- 	</tr>	 -->
	
</table>

<div>
       <label for="p_file">�ӫ~�Ϥ��G</label>
       <input type="file" name="servicePic" id="p_file" accept="image/*">
       <div id="drop_zone"><span class="text">�Ϥ��즲�ܦ��B</span></div>
       <div id="preview"><span class="text">�w����</span></div>
       
    </div>
    
       <button type="reset">�M�Ÿ��</button>
       <button type="submit" id="btn_submit">�e�X���</button>



<br>
<input type="hidden" name="action" value="insert">
<!-- <input type="submit" value="�e�X�s�W"> -->

</FORM>

<script type="text/javascript">

	window.addEventListener("load",function(e){
	var the_form = document.getElementById("the_form");
	the_form.addEventListener("reset", function(){
	    
	    preview_el.innerHTML = `<span class="text">�w����</span></div>`;
	});
	
	
	//�B�J4-1. �w���u�ؤ��\�ϥΪ̩즲�Ϥ��A�j�wdragover�Bdrop�ƥ�
	
	let drop_zone_el = document.getElementById("drop_zone");
	drop_zone_el.addEventListener("dragover", function(e){
	  e.preventDefault();
	  // console.log(e);
	});
	
	// �B�J4-2. �w���u�ؼW�[&����css�Ŧ⳱�v
	drop_zone_el.addEventListener("dragenter", function(e){
	  drop_zone_el.classList.add("-on");
	});
	
	drop_zone_el.addEventListener("dragleave", function(e){
	  drop_zone_el.classList.remove("-on");
	});
	
	drop_zone_el.addEventListener("drop", function(e){
	  e.preventDefault();
	  drop_zone_el.classList.remove("-on");
	  // e.dataTransfer.files[0];
	  preview_img(e.dataTransfer.files[0]);
	  // console.log(e.dataTransfer.files[0]);
	  p_file_el.value = "";
	});
	
	// �B�J5. �z�Lfile���o�w���ϡA
	let preview_el = document.getElementById("preview");
	var preview_img = function(file){
	  var reader = new FileReader();
	  reader.readAsDataURL(file);
	  reader.addEventListener("load", function(){
	    let img_str = `
	    <img src="${reader.result}" class="preview_img">`;
	    preview_el.innerHTML = img_str;
	  });
	};
	
	// �B�J5.2 �w�����ɮת�input���ҤW�ǷӤ�
	let p_file_el = document.getElementById("p_file");
	p_file_el.addEventListener("change", function(){
	  if(this.files.length > 0){
	    preview_img(this.files[0]);
	  }else{
	    preview_el.innerHTML = `<span class="text">�w����</span></div>`;
	  }
	});
}
</script>

</body>
</html>