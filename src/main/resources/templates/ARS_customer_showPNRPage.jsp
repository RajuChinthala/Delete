<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PNR page</title>
<link href="./css/iGate.css" rel="stylesheet" type="text/css"  />
<script src="./js/iGateCommon.js" type="text/javascript"></script>
</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="./images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">

<h1 align="center" class="classFont">View PNR Status</h1>
<c:url var="myAction" value="getPNRdetails" />
<table align="center">
<form:form method="post" modelAttribute="bookingInformation" action="${myAction}">
<tr>
<td>Enter PNR Number</td>
<td><form:input path="bookingId"/></td>
<td><form:errors path="bookingId"/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="View"/></td>
</tr>
</form:form>
</table>

<c:if test="${check!=false}">
<h1 align="center" class="classFont">Customer Information</h1>

	<form:form method="post" modelAttribute="bookingInformation" action="deleteDetails" id="customerStatusForm">
	<table border="1" align="center" cellpadding="1" cellspacing="1">
<tr>
  <th>Flight number</th>
  <th>Source</th>
  <th>Destination</th>
  <th>Email Id<form:label cssStyle="color:red" path="*">*</form:label></th>
  <th>No Of passengers</th>
  <th>Total fare</th>
</tr>

<tr>

   <td><form:input path="flightNumber" size="10" disabled="true"/></td>
   <td><form:input path="departureCity" size="10" disabled="true"/></td>
   <td><form:input path="arrivalCity" size="10" disabled="true"/></td>
   <td><form:input path="customerEmail" id="emailId" size="20"/>
   <c:if test="${emailError!=null}">
     <span style="color:red">${emailError}</span>
    </c:if>
   <span id="emailError" style="color:red"></span></td>
   <td><form:input path="numberOfPassengers" size="15" disabled="true"/></td>
   <td><form:input path="totalFare" size="10" disabled="true"/></td>
   <td><form:hidden path="bookingId" /></td>
   </tr>
   </table>
   <table align="center">
   <tr>
   <td colspan="2"><input type="submit" name="onClick" value="Update"  onclick="eMailValidate()"/></td>
   <td colspan="2"><input type="submit" name="onClick" value="Cancel" onclick="deleteButton()"/></td>
   <td colspan="2"><input type="reset" value="Reset"></input></td>
   </tr>
  
</table>

   
</form:form>

</c:if>

<c:if test="${updateMsg!=null}">
     <h2>${updateMsg}</h2>
</c:if>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>
</body>
</html>