<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../css/iGate.css" rel="stylesheet" type="text/css"  />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Flight Information</title>
</head>
<body>
<%
/************************************************************************************
 * File          :  ARS_View_ShowFlights_Admin.jsp
 * Description   : 	This page has list of flight information.
 * Author		 :	jc815790				      
 * Creation Date :	19-12-2013   
 ************************************************************************************/
%>
<div class="site-wrapper">
<jsp:include page="ARS_Header_Logout.jsp" flush="true"></jsp:include>
<div class="body-wrapper">
<div class="left-wrapper"> <img alt="image" src="../images/ARS_AdminHomePage.jpg" ></div>
<div class="middle-wrapper">
<h1 align="center" class="classFont">Flight Information</h1>
<a href="getFlightInformationPageAdmin">Go back to search again</a><br></br>
<a href="showAdminHomePage">Go back to home page</a>
									
<display:table cellpadding="8"  cellspacing="8" requestURI="showFlightInformationAdmin" id="list" export="false" defaultsort="2" name="${flightInfoList}" pagesize="2">
<display:column property="flightNumber" title="Flight Number"  sortable="true" />
<display:column property="airline" title="Airline"   sortable="true"  />
<display:column property="departureCity" title="Departure City"  sortable="true" />
<display:column property="arrivalCity" title="ArrivalCity" sortable="true"/>
<display:column property="uDepartureDate" title="Departure Date"  sortable="true" />
<display:column property="uArrivalDate" title="Arrival Date"   sortable="true" headerClass="contentTableHeading"  />
<display:column property="departureTime" title="Departure Time"  sortable="true" headerClass="contentTableHeading" />
<display:column property="arrivalTime" title="Arrival Time" sortable="true" headerClass="contentTableHeading" />
<display:column property="firstSeatFare" title="First Seat Fare"  sortable="true" headerClass="contentTableHeading" />
<display:column property="businessSeatFare" title="Business Seat Fare"  sortable="true" headerClass="contentTableHeading" />
</display:table>
</div> <!-- middle-wrapper end-->
</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>