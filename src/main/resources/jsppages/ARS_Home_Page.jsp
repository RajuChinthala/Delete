<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/iGate.css" rel="stylesheet" type="text/css"  />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"/>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="${pageContext.request.contextPath}/images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
      <div style="float:right"><a href="${pageContext.request.contextPath}/airline/showPNRPage.obj">View Status</a></div>
          <marquee direction="left" ><h2 class="clsMarquee"><b>Fly High In The Sky</b></h2></marquee>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <form action="${pageContext.request.contextPath}/airline/showFlightOrLogin.obj">
         <h1 align="center"><input type="submit" name="btnCustomerAdmin" value="View Flights" class="clsHomeButton"/>
         <br></br>
         <br></br>
         <input type="submit" name="btnCustomerAdmin" value="Admin Login" class="clsHomeButton" /></h1>
         </form>
         <br></br>
         
      </div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"/>
	
</div>
</body>
</html>