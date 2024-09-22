<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	/*******************************************************************	
	 *	File Name      : ARS_Admin_AddFlgihtInformation.jsp	
	 *  Description    : This jsp takes the input from the airline componies for registering the  flight information	
	 *  Author         : rc815928	
	 *  Last Edited By : ResourceNumber	
	 *  Version        : V:1.0	
	 *  Since          : 	
	 *  Style Sheets   : ARS_Admin_AddFlightInformation.js
	 *  Scripts        : ARS_Admin_AddFlightInformation.js	
	 *  Created on     : DEC/19/2013	
	 *  History	
	 *  Modified By    : <ResourceName> on <Date in format Mon DD, YYYY>	
	 *                 : <Description of change>	
	 *	
	 *****************************************************************/
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/static//css/iGate.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static//css/jquery-ui.css">
<script src="${pageContext.request.contextPath}/static//js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/static//js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/static//js/iGateCommon.js"></script>

<script>
  $(function() {
    $( "#datepicker" ).datepicker({
      showButtonPanel: true
    });
  });
  $(function() {
	    $( "#datepicker1" ).datepicker({
	      showButtonPanel: true
	    });
	  });
  </script>
</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header_Logout.jsp" flush="true"/>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="${pageContext.request.contextPath}/static//images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">

<h1 align="center" class="classFont">Add Flight Information Form</h1>
<form:form method="post" modelAttribute="flightInformation"
	action="addFlightInformation.obj" id="insertFlightInfo">

	<table border="1" align="center">

		<tr>
			<td>Flight Number<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="flightNumber" id="flightNumber" />
				<span id="fligthNumberError" style="color:red"></span>
				<form:errors path="flightNumber" cssStyle="color:red" />
				
			</td>
		</tr>
		<tr>
			<td>Airline<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="airline" id="airline" />
				<span id="airlineError" style="color:red"></span>
				<form:errors path="airline" cssStyle="color:red" />
			</td>
		</tr>
		<tr>
			<td>Departure City<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
			<form:select path="departureCity" id="departureCity" >
				<form:option value="departureCity" label="select"></form:option>
				<form:options items="${locationList}"></form:options>
			</form:select> 
			<span id="departureCityError" style="color:red"></span>
			<form:errors path="departureCity" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td>Arrival City<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
			<form:select path="arrivalCity" id="arrivalCity" >
				<form:option value="arrivalCity" label="select"></form:option>
				<form:options items="${locationList}"></form:options>
			</form:select> 
			<span id="arrivalCityError" style="color:red"></span>
			<form:errors path="arrivalCity" cssStyle="color:red" />
		</td>
		</tr>
		<tr>
			<td>Departure Date<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="departureDate" id="datepicker" />
				<span id="departureDateError" style="color:red" ></span>
				<form:errors path="departureDate" cssStyle="color:red" />
			</td>
		</tr>
		<tr>
			<td>Arrival Date<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="arrivalDate" id="datepicker1"/>
				<span id="arrivalDateError" style="color:red"></span>
				<form:errors path="arrivalDate" cssStyle="color:red" />
			</td>
		</tr>
		<tr>
			<td>Departure Time(hh:mm)<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="departureTime" id="departureTime" />
				<span id="departureTimeError"" style="color:red"></span>
				<form:errors path="departureTime" cssStyle="color:red" />	
			</td>
		</tr>
		<tr>
			<td>Arrival Time(hh:mm)<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="arrivalTime" id="arrivalTime" />
				<span id="arrivalTimeError" style="color:red"></span>
				<form:errors path="arrivalTime" cssStyle="color:red" />		
			</td>
		</tr>
		<tr>
			<td>Business Seat Number<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="businessSeatNumber" id="businessSeatNumber" />
				<span id="businessSeatNumberError" style="color:red"></span>
				<form:errors path="businessSeatNumber" cssStyle="color:red" />		
					
			</td>
		</tr>
		<tr>
			<td>Business Seat Fare<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="businessSeatFare" id="businessSeatFare" />
				<span id="businessSeatFareError" style="color:red"></span>
				<form:errors path="businessSeatFare" cssStyle="color:red" />	
					
			</td>
		</tr>
		<tr>
			<td>First Seat Number<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="firstSeatNumber" id="firstSeatNumber" />
				<span id="firstSeatNumberError" style="color:red"></span>
				<form:errors path="firstSeatNumber" cssStyle="color:red" />		
			</td>
		</tr>
		<tr>
			<td>First Seat Fare<form:label cssStyle="color:red" path="*">*</form:label></td>
			<td>
				<form:input path="firstSeatFare" id="firstSeatFare" />
				<span id="firstSeatFareError" style="color:red"></span>	
				<form:errors path="firstSeatFare" cssStyle="color:red" />	
				
			</td>
		</tr>
		<tr>
			<td align="center" ><input type="button" value="submit" onClick="validateInsertForm()"/>			
				<span id="selectError" style="color:red"></span>	
			</td>
			<td align="center"><input type="reset" value="reset" /></td>
		</tr>
	</table>
</form:form>

<c:if test="${condition!=null}">
	<h2 align="center" style="color: green">${condition}</h2>
</c:if>
 </div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>


</body>
</html>