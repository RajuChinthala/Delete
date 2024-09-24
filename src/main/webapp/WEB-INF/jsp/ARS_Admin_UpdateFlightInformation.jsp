<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	/*******************************************************************	
	 *	File Name      : ARS_Admin_AddFlgihtInformation.jsp	
	 *  Description    : In this page admin can select the flight number and update or delete the information	
	 *  Author         : sv815918	
	 *  Last Edited By : ResourceNumber	
	 *  Version        : V:1.0	
	 *  Since          : 	
	 *  Style Sheets   : iGateCommon.js
	 *  Scripts        : iGateCommon.js	
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
<link href="../css/iGate.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="../css/jquery-ui.css">
  <script src="../js/jquery-1.9.1.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <!--<script src="../js/iGateCommon.js"></script>
  --><script>
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
      <jsp:include page="ARS_Header_Logout.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="./images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
      
<c:url var="myAction" value="showUpdateFlightInformation" />

<h1 align="center">Update Flight Information</h1>
<form:form method="post" action="${myAction}" modelAttribute="flightInformation">

  
  <table border="1" align="center">
      <tr>
      <td class="classFont">Flight Number<form:label cssStyle="color:red" path="*">*</form:label></td>
      <td>
      <form:select path="flightNumber"> 
	<form:option value="select" label="Please Select"/>
	<form:options items="${flightList}" />
       </form:select>
       <c:if test="${selectError!=null}">
    <span style="color:red">${selectError}</span>
     </c:if>
      </td>
    
      
    <td><input type="submit" value="Modify"/></td></tr>
 </table>
</form:form>
<c:if test="${flightInformation.departureCity!=null}">
<h1 align="center" class="classFont">Flight Information</h1>
 <form:form method="post" modelAttribute="flightInformation" action="updateFlightInformation" id="updateFlightInfo">
 
 <table border=1 align="center" class="table-body">
    
       <tr><td>Flight Number</td><td><form:input path="flightNumber" /></td></tr>
      <tr><td>Departure City<form:label cssStyle="color:red" path="*">*</form:label></td><td>
      
         <form:select path="departureCity" id="departureCity"> 
	
	<form:options items="${locations}" />
       </form:select>
      </td></tr>
      <tr><td>Arrival City<form:label cssStyle="color:red" path="*">*</form:label></td><td>
        
         <form:select path="arrivalCity" id="arrivalCity" > 
	
	<form:options items="${locations}" />
	</form:select><span id="arrivalCityError" style="color:red"></span>
	<c:if test="${depArrCityError!=null}">
    <span style="color:red">${depArrCityError}</span>
     </c:if>
         </td></tr>
      <tr><td>Departure Date<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="departureDate" id="datepicker"/></td></tr>
      <tr><td>Arrival Date<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="arrivalDate" id="datepicker1"/></td></tr>
      <tr><td>Departure Time(hh:mm)<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="departureTime" id="departureTime"/>
      <span id="departureTimeError"" style="color:red"></span>
      <c:if test="${depTimeError!=null}">
    <span style="color:red">${depTimeError}</span>
     </c:if>
       </td>
        </tr>
      <tr><td>Arrival Time(hh:mm)<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="arrivalTime" id="arrivalTime"/>
      <span id="arrivalTimeError" style="color:red"></span>
       <c:if test="${arrTimeError!=null}">
    <span style="color:red">${arrTimeError}</span>
     </c:if></td></tr>
      <tr><td>First Seat Number<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="firstSeatNumber" id="firstSeatNumber"/>
      <span id="firstSeatNumberError" style="color:red"></span>
       <c:if test="${firstSeatError!=null}">
    <span style="color:red">${firstSeatError}</span>
     </c:if></td></tr>
      <tr><td>First Seat Fare<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="firstSeatFare" id="firstSeatFare"/>
      <span id="firstSeatFareError" style="color:red"></span>	
       <c:if test="${firstSeatFareError!=null}">
    <span style="color:red">${firstSeatFareError}</span>
     </c:if></td></tr>
      <tr><td>Business Seat Number<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="businessSeatNumber" id="businessSeatNumber"/>
       <span id="businessSeatNumberError" style="color:red"></span>
       <c:if test="${bussSeatError!=null}">
    <span style="color:red">${bussSeatError}</span>
     </c:if></td></tr>
      <tr><td>Business Seat Fare<form:label cssStyle="color:red" path="*">*</form:label></td><td><form:input path="businessSeatFare" id="businessSeatFare"/>
      <span id="businessSeatFareError" style="color:red"></span>
     
       <c:if test="${bussSeatFareError!=null}">
    <span style="color:red">${bussSeatFareError}</span>
     </c:if></td></tr>
     
      <form:hidden path="departureDate"></form:hidden>
      <form:hidden path="arrivalDate"></form:hidden>
    
     </table>
     <table align="center">
    <tr>
     <td><input type="submit"  name="onSubmit" value="Update" onclick="validateUpdateForm()"/></td>
     <td><input type="submit" name="onSubmit" value="Delete" onclick="validateDeleteForm()"/></td>
     <td><input type="reset" value="Clear"></input></td>
     </tr> 
     </table>
 
 </form:form>
</c:if>
<c:if test="${updateMsg!=null}">
     <h2 align="center">${updateMsg}</h2>
     <h2 align="center"><a href="showAdminHomePage">Go Back To Admin Home</a></h2>
</c:if>
<c:if test="${deleteMessage!=null}">
     <h2 align="center">${deleteMessage}</h2>
</c:if>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>

</body>
</html>