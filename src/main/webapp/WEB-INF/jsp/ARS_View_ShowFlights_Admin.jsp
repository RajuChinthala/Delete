<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%--<%@taglib prefix="display" uri="http://displaytag.sf.net"%>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/iGate.css" rel="stylesheet" type="text/css"  />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Flight Information</title>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready( function () {
            $('#flightTable').DataTable();
        } );
    </script>
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
<div class="left-wrapper"> <img alt="image" src="./images/ARS_AdminHomePage.jpg" ></div>
<div class="middle-wrapper">
<h1 align="center" class="classFont">Flight Information</h1>
<a href="getFlightInformationPageAdmin">Go back to search again</a><br></br>
<a href="showAdminHomePage">Go back to home page</a>
<%--
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
--%>

 <table id="flightTable" class="display" cellpadding="8"  cellspacing="8" id="list">
        <thead>
            <tr>
                <th>Flight Number</th>
                <th>Airline</th>
                <th>Departure City</th>
                <th>Arrival City</th>
                <th>Departure Date</th>
                <th class="contentTableHeading">Arrival Date</th>
                <th class="contentTableHeading">Departure Time</th>
                <th class="contentTableHeading">Arrival Time</th>
                <th class="contentTableHeading">First Seat Fare</th>
                <th class="contentTableHeading">Business Seat Fare</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="flight : ${flightInfoList}">
                <td th:text="${flight.flightNumber}"></td>
                <td th:text="${flight.airline}"></td>
                <td th:text="${flight.departureCity}"></td>
                <td th:text="${flight.arrivalCity}"></td>
                <td th:text="${#temporals.format(flight.uDepartureDate, 'yyyy-MM-dd')}"></td>
                <td class="contentTableHeading" th:text="${#temporals.format(flight.uArrivalDate, 'yyyy-MM-dd')}"></td>
                <td class="contentTableHeading" th:text="${flight.departureTime}"></td>
                <td class="contentTableHeading" th:text="${flight.arrivalTime}"></td>
                <td class="contentTableHeading" th:text="${flight.firstSeatFare}"></td>
                <td class="contentTableHeading" th:text="${flight.businessSeatFare}"></td>
            </tr>
        </tbody>
    </table>

</div> <!-- middle-wrapper end-->
</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>