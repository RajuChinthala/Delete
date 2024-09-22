<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/iGate.css" rel="stylesheet" type="text/css"  />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Back to Home</title>
</head>
<body>
<%
/************************************************************************************
 * File          :  ARS_Admin_PassengerList_ErrorPage.jsp
 * Description   : 	Displays error message and link is provided to go back to ARS_Admin_PassengerLisPage.jsp
 * Author		 :	vs815919			      
 * Creation Date :	19-12-2013   
 ************************************************************************************/
%>
<div class="site-wrapper">
      <jsp:include page="ARS_Header_Logout.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="${pageContext.request.contextPath}/images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
          <h2 class="classFont" align="center">${errorMsg}</h2><br/>
<a href="adminPassengerListPageError.obj">Go back</a>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>