<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/static//css/iGate.css" rel="stylesheet" type="text/css"  />
<title>Admin Home Page</title>
</head>
<body>
<%
/************************************************************************************
 * File          :  ARS_Admin_HomePage.jsp
 * Description   :  This is home page for admin after he logs in with his credentials.
 * Functionality :	Based on hyperlink selected admin is redirected to that particular page
 * Author		 :	jc815790				      
 * Creation Date :	17-12-2013   
 ************************************************************************************/
%>
 <%
 response.setHeader("pragma", "no-cache");              
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
  response.setHeader("Expires", "0");
  %>

<div class="site-wrapper">
      <jsp:include page="ARS_Header_Logout.jsp" flush="true"/>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="${pageContext.request.contextPath}/static//images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
<form>
 <br></br>
 <br></br>
 <table border="1" cellspacing="10" align="center">

    <tr>
         <td><a href="${pageContext.request.contextPath}/static//airline/showAddOrUpdate.obj">Manage Flight Information</a></td>
		 <td><a href="${pageContext.request.contextPath}/static//airline/getFlightInformationPageAdmin.obj" >View List of flights </a></td>
	</tr>
	
	<tr>
     	<td><a href="${pageContext.request.contextPath}/static//airline/showAdminBookingDetailsPage.obj">View Bookings</a></td>
     	<td><a href="${pageContext.request.contextPath}/static//airline/showAdminPassengerListPage.obj"">View Passenger List</a></td>
    </tr>
    
  </table>
 
</form>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>

</body>
</html>
     

	     

		 
     

	 

		

	        

		  

		  

		  

	
 