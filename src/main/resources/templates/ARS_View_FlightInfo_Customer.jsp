<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="../js/iGateCommon.js" type="text/javascript"></script>
 <link rel="stylesheet" href="../css/jquery-ui.css">
 <link href="../css/iGate.css" rel="stylesheet" type="text/css"  />
  <script src="../js/jquery-1.9.1.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker({
      showButtonPanel: true
    });
  });
  </script>
</head>
<body>
<%
/************************************************************************************
 * File          :  ARS_View_FlightInfo_Customer.jsp
 * Description   : 	This page has list of source,destination  and date.Customer has to select these and click on button to see
                    list of flight details
 * Functionality :	Based on source,destination and date selected flight details are displayed in ARS_View_ShowFlights_Customer.jsp
 * Author		 :	vs815919				      
 * Creation Date :	18-12-2013   
 ************************************************************************************/
%>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="../images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
<h1 align="center" class="classFont">Search Flight Information</h1>
<c:url var="myAction" value="showFlightInformationCustomer.obj" />
 <form:form name="frmSearchFlight" id="frmSearchFlight" method="post"  modelAttribute="viewFlights" action="${myAction}" >
   <table align="center">
     <tr>
     <td>Source<form:label cssStyle="color:red" path="*">*</form:label>:</td>
     <td>
         <form:select path="source" id="optSource"> 
             <form:option value="" label="select"></form:option>
             <form:options items="${locationList}"></form:options>
         </form:select>
         <form:errors path="source" cssStyle="color:red"/> 
     </td>
     <td>
       <span id="spanSource" style="color:red"></span>
     </td>
     </tr>
     <tr>
       <td>Destination<form:label cssStyle="color:red" path="*">*</form:label>:</td>
       <td>
           <form:select path="destination" id="optDestination"> 
             <form:option value="" label="select"></form:option>
             <form:options items="${locationList}"></form:options>            
           </form:select>
           <form:errors path="destination" cssStyle="color:red"/> 
       </td>
       <td>
          <span id="spanDestination" style="color:red"></span>
          <span id="spanSourceDestination" style="color:red"></span> 
       </td>
     </tr>
     <tr>
       <td>Date<form:label cssStyle="color:red" path="*">*</form:label>:</td>
       <td>
           <form:input path="date" id="datepicker"/>
           <form:errors path="date" cssStyle="color:red"/> 
       </td>
       <td><span id="spanDate" style="color:red"></span></td>
     </tr>
     <tr>
       <td colspan="2"><input type="button" value="View Flights" onclick="ARS_validateSearchFlights()"></input></td>
     </tr>
   </table>
</form:form>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>
