<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
/************************************************************************************
 * File          :  ARS_Admin_ManagePage.jsp
 * Description   : 	This page shows the options for adding and updating flight numbers
 * Author		 :	jc815790				      
 * Creation Date :	18-12-2013   
 ************************************************************************************/
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./css/iGate.css" rel="stylesheet" type="text/css"  />
<title>Admin Manage Page</title>
</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="./images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <form action="./showFlightOrLogin">
            <h1 align="center" class="classFont"><a href="showAdminAddPage">Add Flight Information</a></h1>
            <br></br>
            <br></br>
            <br></br>
            <h1 align="center" class="classFont"><a href="showUpdateAdminPage">Update Flight Information</a></h1>
         </form>
      </div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>
</body>
</html>