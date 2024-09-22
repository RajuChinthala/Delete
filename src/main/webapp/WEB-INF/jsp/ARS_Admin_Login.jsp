<!--
File Name: Login.jsp
Description:This jsp file will provide a link for the admin to login
Author:sk815835
Last Edited By :sk815835
Version :1.2
Created on : <Thu,19 Dec>
Modified By : <Snigdha Kamath> on <Thu,19 Dec>
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
/************************************************************************************
 * File          :  ARS_Admin_Login.jsp
 * Description   :  This is home page for admin after he logs in with his credentials.
 * Author		 :	sk815835				      
 * Creation Date :	17-12-2013   
 ************************************************************************************/
%>
<html>
<head>
<script src="${pageContext.request.contextPath}/static//js/iGateCommon.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/static//css/iGate.css" rel="stylesheet"
	type="text/css" />
<title>Login Page</title>
</head>
<body>
<div class="site-wrapper"><jsp:include page="ARS_Header.jsp"
	flush="true"></jsp:include>
<div class="body-wrapper">
<div class="left-wrapper"><img alt="image"
	src="${pageContext.request.contextPath}/static//images/ARS_AdminHomePage.jpg"
	width="100%" height="100%"></div>
<div class="middle-wrapper"><br></br>
<br></br>
<br></br>
<br></br>
<%
 response.setHeader("pragma", "no-cache");              
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
  response.setHeader("Expires", "0");
  %>

<h1 align="center" class="classFont">Admin Login</h1>
<c:url var="myAction" value="checkLogin.obj" /> 
<form:form	method="post" modelAttribute="login" action="${myAction}" id="loginPage">
	<table align="center">
		<tr>
			<td class="classFont">Enter Name:</td>
			<td><form:input path="userName" id="userName"/> <form:errors path="userName" />
			</td>
			<td><span id="spanUserName" style="color:red"></span></td>
		</tr>
		<tr>
			<td class="classFont">Enter Password:</td>
			<td><form:password path="password" id="password" /><form:errors
				path="password" /></td>
			<td><span id="spanPassword" style="color:red"></span></td>
		</tr>
		<tr>
			<td><input type="button" value="Login"  onclick="ARS_loginValidation()"/></td>
			<td><input type="reset" value="Reset" /></td>
		</tr>
		</table>
</form:form> <c:if test="${loginError!=null}">
	<h2 align="center">${loginError}</h2>
</c:if></div>
<!-- middle-wrapper end--></div>
<jsp:include page="ARS_Footer.jsp" flush="true"/></div>


</body>
</html>