<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	/*******************************************************************	
	 *	
	 *  File Name      : BookingInformationForm.jsp	
	 *  Description    : This jsp takes the input from the customer for booking flight information,generates the booking id to customer	
	 *  Author         : rc815928	
	 *  Last Edited By : ResourceNumber	
	 *  Version        : V:1.0	
	 *  Since          : 	
	 *  Style Sheets   : ARS_Customer_BookingInformation.js
	 *  Scripts        : ARS_Customer_BookingInformation.js	
	 *  Created on     : DEC/18/2013	
	 *  History	
	 *  Modified By    : <ResourceName> on <Date in format Mon DD, YYYY>	
	 *                 : <Description of change>	
	 *	
	 *****************************************************************/
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Information Form</title>
<script type="text/javascript"
	src="./js/iGateCommon.js"></script>
<link href="./css/iGate.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="site-wrapper"><jsp:include page="ARS_Header.jsp"
	flush="true"></jsp:include>
<div class="body-wrapper">
<div class="left-wrapper"><img alt="image"
	src="./images/ARS_AdminHomePage.jpg" width="100%"
	height="100%"></div>
<div class="middle-wrapper">

<h1 align="center" class="classFont">Booking Information Details Form</h1>
<table align="center" border="1">
	<form:form method="post" modelAttribute="bookingDetailsFormAttribute"
		action="showBookingFormForConfirm" id="bookingDetailsForm">
		<tr>
			<td>Customer Email:</td>
			<td><form:input path="customerEmail" id="customerEmail"
				onblur="validateEmail()" /> <span id="emailError"
				style="color: red"></span> <form:errors path="customerEmail"
				cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td>Number of Passengers:</td>
			<td><form:input path="numberOfPassengers"
				id="numberOfPassengers"  /> <span
				id="numberOfPassengersError" style="color: red"></span> <form:errors
				path="numberOfPassengers" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td>Credit card Number</td>
			<td><form:input path="creditCardInformation"
				id="creditCardInformation"  />
			<span id="creditCardInformationError" style="color: red"></span> <form:errors
				path="creditCardInformation" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td>Class Type</td>
			<td><form:select path="classType" id="classType"
				onblur="validateClassType()">
				<form:option value="Select">Select</form:option>
				<form:option value="BusinessClass">BusinessClass</form:option>
				<form:option value="FirstClass">FirstClass</form:option>
			</form:select> <span id="classTypeError" style="color: red"></span> <form:errors
				path="classType" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="submit" id="submitInformation" onClick="submitInformation()" />
			<span id="allError" style="color: red"></span></td>
		</tr>

	</form:form>

</table>

<%--
	This part display the information for the confirmation after the customer 
	fill the all details in booking details form

 --%> <c:if test="${condition==true}">

	<h2 align="center">Confirm Booking Information</h2>
	<table border="1" align="center">
		<form:form method="post" modelAttribute="bookingDetailsFormAttribute"
			action="addBookingDetails">
			<tr>
			     <th>Flight Number</th>
				<th>Customer Email</th>
				<th>Number of Passengers</th>
				<th>Class Type</th>
                <th>Total Fare</th>
                 
			</tr>
			<tr>
			    <td><c:out value="${flightNumber}" /></td>
				<td><c:out value="${bookingDetailsFormAttribute.customerEmail}" /></td>
				<td><c:out value="${bookingDetailsFormAttribute.numberOfPassengers}" /></td>
				<td><c:out value="${bookingDetailsFormAttribute.classType}" /></td>
				<td><c:out value="${totalFare}" />
					<form:hidden path="customerEmail"/>
					<form:hidden path="numberOfPassengers"/>
					<form:hidden path="classType"/>
					<form:hidden path="creditCardInformation"/>
				</td>

			</tr>

			<tr>
				<td colspan="3" align="center"><input type="submit"
					value="confirm" /></td>
			</tr>
			
						
		</form:form>
	</table>
</c:if> <%--
/********************************************************************************
*	This part displays generated Booking id
*	after storing the all customer details along with flight details 
**********************************************************************************/	
--%> 
<c:if test="${bookingStatus==true}">
		<c:if test="${bookingIdModel!=null}">
			<h2 align="center">successfully registered</h2>
			<table border="1" align="center">
			<tr>
				<td>Booking Id</td>
				<td>${bookingIdModel}</td>
			</tr>
			</table>
		</c:if> 
		<c:if test="${errorMessage!=null}">
			<h2 align="center">Technical Problem Occured</h2>
		</c:if>
</c:if> 
</div>
<!-- middle-wrapper end--></div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include></div>



</body>
</html>