<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.igate.airline.bean.BookingInformation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net"%> --%>
<html>
<head>
<link href="./css/iGate.css" rel="stylesheet" type="text/css"  />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passengers List</title>
<script src="./js/iGateCommon.js" type="text/javascript"/>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
 <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready( function () {
            $('#passengerTable').DataTable();
        } );
    </script>

</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header_Logout.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="./images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
<%
/************************************************************************************
 * File          :  ARS_Admin_PassengerListPage.jsp
 * Description   : 	This page has flight numbers list.Admin can select flight number for which he want to view passengers list
 * Functionality :	Based on flight number selected passengers list is displayed on the same page
 * Author		 :	vs815919			      
 * Creation Date :	19-12-2013   
 ************************************************************************************/
%>
<a href="backToAdminHomePage">Go To Home</a>
<table align="center">
<form:form method="post" id="adminBookings" modelAttribute="bookingInformation" action="checkAdminPassengerList">

<tr>
  		<td>Flight Number:<form:label cssStyle="color:red" path="*">*</form:label></td>
  		<td>
  		<form:select id="flightNumber" path="flightNumber"> 
		<form:option value="select" label="select"></form:option>
    	<form:options items="${flightNumberList}"></form:options> 
  		</form:select>
		</td>
  		<td><form:errors path="flightNumber" cssStyle="color:red"></form:errors>
  		<c:if test="${selectError!=null}">
    <span style="color:red">${selectError}</span>
     </c:if>
     </td>  
  		<td><span id="spanFlightNumber"  style="color:red"></span></td>
</tr>

<tr>
		<td><input type="submit" value="SUBMIT" onclick="ARS_validateBookingDetails()"/></td>
</tr>

</form:form>
</table>

<!--  To display Passengers List,using pagination concept-->



<c:if test="${passengersList!=null}">
	<h1 align="center" class="classFont">Passengers Information</h1>
<%--
	<display:table cellpadding="8" cellspacing="8" requestURI="checkAdminPassengerList" id="list" export="false" defaultsort="2" name="${passengersList}" pagesize="5">
<display:column property="bookingId" title="Booking Id"  sortable="true" />
<display:column property="flightNumber" title="Flight Number"   sortable="true"  />
<display:column property="customerEmail" title="Email"  sortable="true" />
<display:column property="creditCardInformation" title="CreditCard Information"  sortable="true" />
<display:column property="numberOfPassengers" title="Number Of Passengers" sortable="true"/>
<display:column property="classType" title="Class Type"  sortable="true" />
<display:column property="totalFare" title="Total Fare"   sortable="true" />
<display:column property="departureCity" title="Departure City"  sortable="true" />
<display:column property="arrivalCity" title="Arrival City"   sortable="true" />
</display:table>
--%>

<table id="passengerTable" class="display" requestURI"checkAdminPassengerList" cellpadding="8" cellspacing="8" id="list">
        <thead>
            <tr>
                <th>Booking Id</th>
                <th>Flight Number</th>
                <th>Email</th>
                <th>Credit Card Information</th>
                <th>Number Of Passengers</th>
                <th>Class Type</th>
                <th>Total Fare</th>
                <th>Departure City</th>
                <th>Arrival City</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="passenger : ${passengersList}">
                <td th:text="${passenger.bookingId}"></td>
                <td th:text="${passenger.flightNumber}"></td>
                <td th:text="${passenger.customerEmail}"></td>
                <td th:text="${passenger.creditCardInformation}"></td>
                <td th:text="${passenger.numberOfPassengers}"></td>
                <td th:text="${passenger.classType}"></td>
                <td th:text="${passenger.totalFare}"></td>
                <td th:text="${passenger.departureCity}"></td>
                <td th:text="${passenger.arrivalCity}"></td>
            </tr>
        </tbody>
    </table>
</c:if>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>
</body>
</html>