function ARS_validateBookingDetails()
{
	var select=document.getElementById("flightNumber").value;
	if(select.match("select"))
	{
		document.getElementById("spanFlightNumber").innerHTML="select flight number";
	}
	else
	{
		document.getElementById("spanFlightNumber").innerHTML="";
		document.getElementById("adminBookings").submit();	
	}
}
function ARS_loginValidation()
{
	
  var txtUserName=document.getElementById("loginPage").userName.value;
  var txtPassword=document.getElementById("loginPage").password.value;
  if(txtUserName=="")
  {
	  document.getElementById("spanUserName").innerHTML="Enter user name";  
  }
  else
  {
	  document.getElementById("spanUserName").innerHTML=""; 
  }
  if(txtPassword=="")
  {
	  document.getElementById("spanPassword").innerHTML="Enter user name"; 
  }
  else
  {
	  document.getElementById("spanPassword").innerHTML=""; 
  }
  if(txtUserName!="" && txtPassword!="")
  {
	  document.getElementById("loginPage").submit();
  }
}

function ARS_validateSearchFlights()
{
	var optSource=document.getElementById("frmSearchFlight").optSource.selectedIndex;
	var optDestination=document.getElementById("frmSearchFlight").optDestination.selectedIndex;
    var txtDate=document.getElementById("frmSearchFlight").datepicker.value;
   
	if(optSource==0)
	{
		document.getElementById("spanSource").innerHTML="Please Select Source";
	}
	else
	{
		document.getElementById("spanSource").innerHTML="";
	}
	if(optDestination==0 )
	{
		document.getElementById("spanSourceDestination").innerHTML="Please Select Destination";
	}
	else
	{
		document.getElementById("spanDestination").innerHTML="";
	}
	if(txtDate=="")
	{
		document.getElementById("spanDate").innerHTML=" Please Select Date";
	}
	else
	{
		document.getElementById("spanDate").innerHTML=" ";
	}
	if(optSource==optDestination && optSource!=0 && optDestination!=0)
	{
		document.getElementById("spanSourceDestination").innerHTML=" Source and destination cannot be same";
	}
	else
	{
		document.getElementById("spanDestination").innerHTML=" ";
	}
	if(optSource!=optDestination)
	{
		document.getElementById("spanSourceDestination").innerHTML=" ";
	}
	if (optSource!=0 && optDestination!=0 && txtDate!="" && optSource!=optDestination)
	{
		document.getElementById("frmSearchFlight").submit();		
	}
	
}
function validateInsertForm()
{
	 var fNumber=document.getElementById("insertFlightInfo").flightNumber.value;
	 var fNumberPat=/^[a-z A-Z 0-9]{1,5}$/;
	  if(fNumber=="")
	  {
		  document.getElementById("fligthNumberError").innerHTML="Enter flight number";
	  }
	else if(!fNumberPat.test(fNumber))
	  {	   
	    document.getElementById("fligthNumberError").innerHTML="Enter only alphanumric values";
		document.getElementById("insertFlightInfo").flightNumber.value="";
	  }
	 else{
	  document.getElementById("fligthNumberError").innerHTML="";
	 } 
	  var airlineName=document.getElementById("insertFlightInfo").airline.value;
	  var airlinePat=/^[a-z A-Z]+$/;
	  if(airlineName==null)
	  {
		  document.getElementById("airlineError").innerHTML="Enter AirLine name";
	  }
	else if(!airlinePat.test(airlineName))
	  {	   
	    document.getElementById("airlineError").innerHTML="Enter only alphabets";
		document.getElementById("insertFlightInfo").airline.value="";
	 }
	 else{
	  document.getElementById("fligthNumberError").innerHTML="";
	 } 
	  var depCity=document.getElementById("insertFlightInfo").departureCity.selectedIndex;
		 
		 
		 if(depCity==0)
		  {
		   
		    document.getElementById("departureCityError").innerHTML="Select departure city";
			document.getElementById("insertFlightInfo").name.value="";
				
		 }
		 else{
		  document.getElementById("departureCityError").innerHTML="";
		 } 
		 var arrCity=document.getElementById("insertFlightInfo").arrivalCity.selectedIndex;
		  var depCity=document.getElementById("insertFlightInfo").departureCity.selectedIndex;
		  if(arrCity==0){	   
		    document.getElementById("arrivalCityError").innerHTML="Select arrival city";
			document.getElementById("insertFlightInfo").arrivalCity.value="";
		}
		 else if(arrCity==depCity){
			 document.getElementById("arrivalCityError").innerHTML="Arrival city is same as departure city";
			 document.getElementById("insertFlightInfo").arrivalCity.value="";
		 }
		  else{
		  document.getElementById("arrivalCityError").innerHTML="";
		  }
		  var depTime=document.getElementById("insertFlightInfo").departureTime.value;
		  var depTimePat=/^(([01][0-9])|2[0-3]):[0-5][0-9]$/;
			  if(depTime=="")
			  {
				  document.getElementById("departureTimeError").innerHTML="Enter departure time";
			  }
			else if(!depTimePat.test(depTime))
			  {   
			    document.getElementById("departureTimeError").innerHTML="Enter time in correct format(hh:mm)";
				document.getElementById("insertFlightInfo").departureTime.value="";
			 }
			 else{
			  document.getElementById("departureTimeError").innerHTML="";
			 } 
			  var arrTime=document.getElementById("insertFlightInfo").arrivalTime.value;
			  arrTimepat=/^(([01][0-9])|2[0-3]):[0-5][0-9]$/;
			  if(arrTime=="")
			  {
				  document.getElementById("arrivalTimeError").innerHTML="Enter arrival time";
			  }
			else if(!arrTimepat.test(arrTime))
			  {
			   
			    document.getElementById("arrivalTimeError").innerHTML="Enter time in correct format(hh:mm)";
				document.getElementById("insertFlightInfo").arrivalTime.value="";
			}
			 else{
			  document.getElementById("arrivalTimeError").innerHTML="";
			 } 
			  var fSeat=document.getElementById("insertFlightInfo").firstSeatNumber.value;
			  var fSeatPat=/^[0-9]+$/;
			  if(fSeat==0)
			  {
				  document.getElementById("firstSeatNumberError").innerHTML="Enter number of first seats";
			  }
			else if(!fSeatPat.test(fSeat))
			  {
			   
			    document.getElementById("firstSeatNumberError").innerHTML="Enter only numbers values";
				document.getElementById("insertFlightInfo").firstSeatNumber.value="";
			}
			 else{
			  document.getElementById("firstSeatNumberError").innerHTML="";
			 } 
			  var fFare=document.getElementById("insertFlightInfo").firstSeatFare.value;
			  var fFarePat=/^[0-9]+|[0-9]+[.]+[0-9]+$/;
			  if(fFare==0)
			  {
				  document.getElementById("firstSeatFareError").innerHTML="Enter first seat fare";
			  }
			else if(!fFarePat.test(fFare))
			  {
			    document.getElementById("firstSeatFareError").innerHTML="Enter only numerics values";
				document.getElementById("insertFlightInfo").firstSeatFare.value="";
			}
			 else{
			  document.getElementById("firstSeatFareError").innerHTML="";
			 } 
			  var bSeat=document.getElementById("insertFlightInfo").businessSeatNumber.value;
			  var bSeatPat=/^[0-9]+$/;
			  if(bSeat==0)
			  {
				  document.getElementById("businessSeatNumberError").innerHTML="Enter number of bussiness seats ";
			  }
			else if(!bSeatPat.test(bSeat))
			  {	   
			    document.getElementById("businessSeatNumberError").innerHTML="Enter only numeric values";
				document.getElementById("insertFlightInfo").businessSeatNumber.value="";
			}
			 else{
			  document.getElementById("businessSeatNumberError").innerHTML="";
			 } 
			  var bussFare=document.getElementById("insertFlightInfo").businessSeatFare.value;
			  var bFarePat=/^[0-9]+|[0-9]+[.]+[0-9]+$/;
			  if(bussFare==0)
			  {
				  document.getElementById("businessSeatFareError").innerHTML="Enter bussiness seat fare";
			  }
			else if(!bFarePat.test(bussFare))
			  {
			    document.getElementById("businessSeatFareError").innerHTML="Enter only numeric values";
				document.getElementById("insertFlightInfo").businessSeatFare.value="";
			}
			 else{
			  document.getElementById("businessSeatFareError").innerHTML="";
			 } 
			  var depDate=document.getElementById("insertFlightInfo").datepicker.value;
			   if(depDate=="")
			  {
				  document.getElementById("departureDateError").innerHTML="Select the departure date";
				  document.getElementById("insertFlightInfo").datepicker.value="";
			  }
			 else{
			  document.getElementById("departureDateError").innerHTML="";
			 } 
				var arrDate=document.getElementById("insertFlightInfo").datepicker1.value;
				if(arrDate==""){
					  document.getElementById("arrivalDateError").innerHTML="Select the arrival date";
					  document.getElementById("insertFlightInfo").datepicker1.value="";
				  }
				 else{
				  document.getElementById("arrivalDateError").innerHTML="";
				 } 
	
	if((fNumber!="")&&(airlineName!="")&&(depCity!=0)&&(arrCity!=0) &&(depTime!="")&&(arrTime!="")&&(fSeat!="")&&(fFare!="")&&(bSeat!="")&&(bussFare!=""))
	{
		document.getElementById("insertFlightInfo").submit();
	}
}
function validateUpdateForm()
{
  
	 
	  var arrCity=document.getElementById("updateFlightInfo").arrivalCity.selectedIndex;
	  var departureCity=document.getElementById("updateFlightInfo").departureCity.selectedIndex; 
	  var depTime=document.getElementById("updateFlightInfo").departureTime.value; 
	  var arrTime=document.getElementById("updateFlightInfo").arrivalTime.value;
		
		 if(arrCity==departureCity){
			 document.getElementById("arrivalCityError").innerHTML="Arrival city is same as departure city";
			 document.getElementById("updateFlightInfo").arrivalCity.value="";
		 }
		  else{
		  document.getElementById("arrivalCityError").innerHTML="";
		  }
		  
		 depTimePat=/^[0-9]{1,2}[:][0-9]{1,2}$/;
			  if(depTime=="")
			  {
				  document.getElementById("departureTimeError").innerHTML="Enter departure time";
			  }
			else if(!depTimePat.test(depTime))
			  {   
			    document.getElementById("departureTimeError").innerHTML="Invalid departure time";
				document.getElementById("updateFlightInfo").departureTime.value="";
			 }
			 else{
			  document.getElementById("departureTimeError").innerHTML="";
			 } 
			 
			  arrTimepat=/^[0-9]{1,2}[:][0-9]{1,2}$/;
			  if(arrTime=="")
			  {
				  document.getElementById("arrivalTimeError").innerHTML="Enter arrival time";
			  }
			else if(!arrTimepat.test(arrTime))
			  {
			   
			    document.getElementById("arrivalTimeError").innerHTML="Invalid arrival time";
				document.getElementById("updateFlightInfo").arrivalTime.value="";
			}
			 else{
			  document.getElementById("arrivalTimeError").innerHTML="";
			 } 
			  var fSeat=document.getElementById("updateFlightInfo").firstSeatNumber.value;
			  fSeatPat=/^[0-9]+$/;
			  if(fSeat==0)
			  {
				  document.getElementById("firstSeatNumberError").innerHTML="Enter first seat number";
			  }
			else if(!fSeatPat.test(fSeat))
			  {
			   
			    document.getElementById("firstSeatNumberError").innerHTML="Invalid first seat number";
				document.getElementById("updateFlightInfo").firstSeatNumber.value="";
			}
			 else{
			  document.getElementById("firstSeatNumberError").innerHTML="";
			 } 
			  var fFare=document.getElementById("updateFlightInfo").firstSeatFare.value;
			  fFarePat=/^[0-9]+|[0-9]+[.]+[0-9]+$/;
			  if(fFare==0)
			  {
				  document.getElementById("firstSeatFareError").innerHTML="Enter first seat fare";
			  }
			else if(!fFarePat.test(fFare))
			  {
			    document.getElementById("firstSeatFareError").innerHTML="Invalid first seat fare";
				document.getElementById("updateFlightInfo").firstSeatFare.value="";
			}
			 else{
			  document.getElementById("firstSeatFareError").innerHTML="";
			 } 
			  var bSeat=document.getElementById("updateFlightInfo").businessSeatNumber.value;
			  bSeatPat=/^[0-9]+$/;
			  if(bSeat==0)
			  {
				  document.getElementById("businessSeatNumberError").innerHTML="Enter bussiness seat number";
			  }
			else if(!bSeatPat.test(bSeat))
			  {	   
			    document.getElementById("businessSeatNumberError").innerHTML="Invalid bussiness seat number";
				document.getElementById("updateFlightInfo").businessSeatNumber.value="";
			}
			 else{
			  document.getElementById("businessSeatNumberError").innerHTML="";
			 } 
			  var bussFare=document.getElementById("updateFlightInfo").businessSeatFare.value;
			  bFarePat=/^[0-9]+|[0-9]+[.]+[0-9]+$/;
			  if(bussFare==0)
			  {
				  document.getElementById("businessSeatFareError").innerHTML="Enter bussiness seat fare";
			  }
			else if(!bFarePat.test(bussFare))
			  {
			    document.getElementById("businessSeatFareError").innerHTML="Invalid bussiness seat fare";
				document.getElementById("updateFlightInfo").businessSeatFare.value="";
			}
			 else{
			  document.getElementById("businessSeatFareError").innerHTML="";
			 } 
			 
	
	if((depTime!="") && (arrTime!="") && (fSeat!="") && (fFare!="") && (bSeat!="") && (bussFare!=""))
	{
		alert("in submit");
		document.getElementById("updateFlightInfo").submit();
	}
}
function validateDeleteForm()
{
	alert("in submit");
	document.getElementById("updateFlightInfo").submit();
}
function submitInformation()
{

	var email=document.getElementById("bookingDetailsForm").customerEmail.value;
	 // patEmail=/^[a-z0-9._]{6,20}[@][a-z]{4,10}[.][a-z]{2,4}$|^[a-z0-9]{6,20}[@][a-z]{4,10}[.][a-z]{2,4}[.][a-z]{2,4}$/;
	patEmail=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;  
	
	var passengersNumber=document.getElementById("bookingDetailsForm").numberOfPassengers.value;
	var patPassengersNumber=/^[1-9]{1}$/;
	
	var cardInformation=document.getElementById("bookingDetailsForm").creditCardInformation.value;
	patCardInformation=/^[a-z A-Z 0-9]{5,10}$/;
	
	var classtype=document.getElementById("bookingDetailsForm").classType.selectedIndex;
	
	
	
	
		
		if(email.match(patEmail))
		  {
		      document.getElementById("emailError").innerHTML="";
		  }
		  else
		  {
		   document.getElementById("emailError").innerHTML="Please enter valid email";
		   document.getElementById("bookingDetailsForm").customerEmail.value="";
		 }
		
		  if(passengersNumber.match(patPassengersNumber))
		  {
		   document.getElementById("numberOfPassengersError").innerHTML="";
		  }
		  else
		  {
		    document.getElementById("numberOfPassengersError").innerHTML="Please enter number of passengers(1-9)";
			document.getElementById("bookingDetailsForm").numberOfPassengers.value="";
			
		  }
		  if(patCardInformation.test(cardInformation)){
				 document.getElementById("creditCardInformationError").innerHTML="";
			 }
			 else{
				  document.getElementById("creditCardInformationError").innerHTML="Please enter credit card information(5-10 characters)";
			   	  document.getElementById("bookingDetailsForm").creditCardInformation.value="";
			 } 
		  if(classtype==0){
				 document.getElementById("classTypeError").innerHTML="Select class type";
				 document.getElementById("bookingDetailsForm").classType.value="";			
			 }
			 else{
				 document.getElementById("classTypeError").innerHTML="";
			 } 
		  if((emamil=="") && (passengersNumber=="") && (cardInformation=="") && (classtype==0))
			{
				
				document.getElementById("bookingDetailsForm").submit();
			   		
			}
		
		
	
		
		
}
