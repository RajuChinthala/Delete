<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/static//css/iGate.css" rel="stylesheet" type="text/css"  />
<title>Insert title here</title>
</head>
<body>
<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="${pageContext.request.contextPath}/static//images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
<h3 align="center" class="classFont"> Flight does not exist on that date,Please select again </h3>
<br></br>
Go back to select again
<a href="getFlightInformationPageAdmin.obj">Click Here</a>
</div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>