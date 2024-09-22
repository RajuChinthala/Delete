<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/iGate.css" rel="stylesheet" type="text/css"  />
<title>View Flight Information</title>
</head>
<body>
<%
/************************************************************************************
 * File          :  ARS_View_ShowFlights_Customer.jsp
 * Description   : 	This page has list of flight information.
 * Author		 :	vs815919				      
 * Creation Date :	18-12-2013   
 ************************************************************************************/
%>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="/images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
<h1 align="center" class="classFont">Flight Information</h1>
<a href="getFlightInformationPageCustomer.obj">Go back</a>
<br></br>
<c:url var="myAction" value="Booking.obj" />
<form:form method="post"  modelAttribute="bookFlightInfo"  action="${myAction}" >
		<table border="1" align="center">
			<tr>
				<th>Flight Number</th>
				<th>Airline</th>
				<th>Departure City</th>
				<th>Arrival City</th>
				<th>Departure Date</th>
				<th>Arrival Date</th>
				<th>Departure Time</th>
				<th>Arrival Time</th>
				<th>First Seat Fare</th>
				<th>Business Seat Fare</th>
				<th></th>
			</tr>

			<c:forEach var="each" items="${flightInfoList}">
				<tr>
					<td><c:out value="${each.flightNumber}"></c:out></td>
					<td><c:out value="${each.airline}"></c:out></td>
					<td><c:out value="${each.departureCity}"></c:out></td>
					<td><c:out value="${each.arrivalCity}"></c:out></td>
					<td><c:out value="${each.uDepartureDate}"></c:out></td>
					<td><c:out value="${each.uArrivalDate}"></c:out></td>
					<td><c:out value="${each.departureTime}"></c:out></td>
					<td><c:out value="${each.arrivalTime}"></c:out></td>
					<td><c:out value="${each.firstSeatFare}"></c:out></td>
					<td><c:out value="${each.businessSeatFare}"></c:out></td>
					<td>
					    <input name="flightNumber" type="hidden" value="${each.flightNumber}">
					    <input name="departureCity" type="hidden" value="${each.departureCity}">
					    <input name="arrivalCity" type="hidden" value="${each.arrivalCity}">
					    <input name="firstSeatFare" type="hidden" value="${each.firstSeatFare}">
					    <input name="businessSeatFare" type="hidden" value="${each.businessSeatFare}">
					    <input type="submit" value="Book Ticket"></input>  					    
				
					
					 </td>				
				</tr>
			</c:forEach>

		</table>

</form:form>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>


</body>
</html>