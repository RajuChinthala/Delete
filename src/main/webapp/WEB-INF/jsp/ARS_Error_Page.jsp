<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
<link href="./css/iGate.css" rel="stylesheet" type="text/css"  />
</head>
<body>

<div class="site-wrapper">
      <jsp:include page="ARS_Header.jsp" flush="true"></jsp:include>
   <div class="body-wrapper">
      <div class="left-wrapper"><img alt="image" src="./images/ARS_AdminHomePage.jpg" width="100%" height="100%" ></div>
      <div class="middle-wrapper">
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <h2 class="classFont" align="center">
            ${errorMessage}

         <br></br>
          <a href="showHomePage">Go to Home page</a></h2>
      </div> <!-- middle-wrapper end-->

</div>
<jsp:include page="ARS_Footer.jsp" flush="true"></jsp:include>
	
</div>
</body>
</html>