package com.igate.airline.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Login;
import com.igate.airline.bean.ViewFlights;
import com.igate.airline.exception.AirlineException;
import com.igate.airline.service.IAirlineService;

/************************************************************************************
 *Class Name      : AirlineController
 * Description    : This is the controller class  which has methods to dispatches the requests  
 *                  to respective jsp pages          
 * Author         : Team 5 
 * Last Edited By : 
 * Version        : <V 1.0>
 ***********************************************************************/
@Controller
public class AirlineController {
	@Autowired
	private IAirlineService airlineService;
	boolean PNRexists=false;
	List<String> flightNumberList=new ArrayList<String>();
	List<BookingInformation> bookingsList=new ArrayList<BookingInformation>();
	List<BookingInformation> passengersList=new ArrayList<BookingInformation>();

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	
	/************************************************************************************
	 *Method Name     : processBookingDetailsPage 
	 * Description    : This is processBookingDetailsPage method and it gets the PNR number  
	                   from ARS_Home_page.jsp and calls the getValidPNR method.If the PNR is 
	                   present it goes to ARS_customer_showPNRPage else it goes to 
	                   ARS_customer_errorPage.          
	 * Author         : <mk815797>
	 * Last Edited By : 
	 * Version        : <V 1.1>
	 * Created on     : 19-12-13
	 * Modified By    :                 
	 *
	 * 
	 ***********************************************************************/
	
	@RequestMapping(value="/getPNRdetails")
	public String processBookingDetailsPage(@ModelAttribute(value="bookingInformation") @Valid BookingInformation bookingInformation, BindingResult result, Model model )
		
	{
		String jspPage=null;
		
		model.addAttribute("check","false");
		if(result.hasErrors())
		{
			jspPage="ARS_customer_showPNRPage";
		}
		try
		{
		
		PNRexists=airlineService.getValidPNR(bookingInformation.getBookingId());
		
		if(PNRexists==true)
		{
		
		
		BookingInformation bookingInformation2=airlineService.getCustomerDetailsById(bookingInformation.getBookingId());
		
		
		
		model.addAttribute("bookingInformation",bookingInformation2);
		model.addAttribute("check","true");
		jspPage="ARS_customer_showPNRPage";
	   
		}
		
		else
		{
			model.addAttribute("errorMessage","Invalid PNR number");
			jspPage="ARS_customer_errorPage";
		}
		}
		catch(AirlineException e)
		{
			model.addAttribute("errorMessage","Some technical problem has occured");
			jspPage="ARS_customer_errorPage";
		}
		return jspPage;
			
	}		
	/************************************************************************************
	 *Method Name     : processBookingDetailsPage 
	 * Description    : This is processManageBooking method and it gets the PNR number from  
	                    ARS_Home_page.jsp and checks whether customer wants to update
	                    its information or wants to cancel the booking on particular
	                    selection respective methods are called.For successful operation 
	                    it will go to successful update or booking page else go to
	                    ARS_customer_errorPage.
	                    it goes to ARS_customer_showPNRPage else it goes to ARS_customer_errorPage           
	 * Author         : <mk815797>
	 * Last Edited By : <mk815797>
	 * Version        : <V 1.1>
	 * Created on     : <Thu 19, 2013>
	 * Modified By    : <mk815797> on <Thu 19,2013>
	 *                : 
	 *
	 * 
	 ***********************************************************************/
	
		@RequestMapping(value="/deleteDetails")
		public String processManageBooking(@RequestParam("onClick") String submit,@ModelAttribute("bookingInformation") BookingInformation bookingInformation,Model model)
		
		{

			String jspPage=null;
			boolean status=false;
			boolean cancelStatus=false;
			try
			{
	        if("Cancel".equals(submit))
	        {
			cancelStatus = airlineService.cancelBooking(bookingInformation.getBookingId());

			

			if(cancelStatus==true){
				jspPage="ARS_customer_successcancel";
			
			}
			
			else
			{
				jspPage="ARS_customer_errorPage";
			}
	        }
	        else if("Update".equals(submit))
	        {
	        	
	        	String input=bookingInformation.getCustomerEmail();
	        	if(input=="")
	        	{
	        		 model.addAttribute("emailError","Please enter email id");
	        		 jspPage="ARS_customer_showPNRPage";
	        	}
	        	else
	        	{
	        		Pattern pattern=Pattern.compile("^[a-z0-9._%+-]{6,20}[@][a-z0-9.-]{4,10}[.][A-Za-z]{2,4}$|^[A-Z0-9._%+-]{6,20}[@][A-Z0-9.-]{4,10}[.][A-Za-z]{2,4}[.][a-zA-Z]{2,4}$");
					Matcher m=pattern.matcher(input);
				if(m.find())
				{
					
					status=airlineService.updateCustomerDetails(bookingInformation);
		            if(status==false)
		            {
		            	model.addAttribute("errorMessage","Not Successfully updated");
		            	jspPage="ARS_customer_errorPage";
		            }
		            else
		            {
		            	jspPage="ARS_Customer_UpdateSuccessPage";
		            }
				}
	        	
				else
				{
				    model.addAttribute("emailError","Invalid email id");
				    jspPage="ARS_customer_showPNRPage";
				}
	        	}
	            
	        }
	        else
	        {
	        	model.addAttribute("errorMessage","Select any option");
	        	jspPage="ARS_customer_errorPage";
	        }
			}
			catch(AirlineException e)
			{
				model.addAttribute("errorMessage","Some technical problem has occured");
				jspPage="ARS_customer_errorPage";
			}
			return jspPage;
		}
	@RequestMapping("showAddOrUpdate")
	public String showAddOrUpdate(HttpSession session, Model model)
	{
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
		    jspPage="ARS_Admin_ManagePage";
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
		
	}
	
	@RequestMapping("showFlightOrLogin")
	public String showFlightOrLogin(Model model,@RequestParam("btnCustomerAdmin") String submit)
	{
		String jspPage=null;
		if("Admin Login".equals(submit))
		{
			Login login=new Login();
			model.addAttribute("login",login);
			jspPage="ARS_Admin_Login";
		}
		else if("View Flights".equals(submit))
		{
			model.addAttribute("viewFlights",new ViewFlights());
			
			List<String> locationList=null;
			try
			{
			locationList=airlineService.getLocations();
			model.addAttribute("locationList",locationList);
			jspPage="ARS_View_FlightInfo_Customer";
			}
			catch(AirlineException e)
			{
				model.addAttribute("errorMessage",e.getMessage());
				jspPage="ARS_Error_Page";
			}
			
			    return jspPage;
		}
		return jspPage;
	}
	/**********************************
	 *Method Name: processLoginPage
	 * Description:This is processLoginPage method and it gets the username and password from jsp page Login.jsp and calls the
	 *             validateUser(Login login).If the user is present it goes to ARS_Admin_HomePage else it 
	 *             remains in Login.jsp
	 * Author         : <sk815835>
	 * Last Edited By : <sk815835>
	 * Version        : <V 1.1>
	 * Created on     : <Thu 19, 2013>
	 * Modified By    : <sk815835> on <Thu 19,2013>
	 *                : 
	 *
	 * 
	 */
	@RequestMapping(value="/checkLogin")
	public String processLoginPage(@ModelAttribute("login") @Valid Login login,BindingResult result,Model model,HttpSession session)
	{
		String JSP_Page=null;
		boolean isPresent=false;
		if (result.hasErrors())
		{
			JSP_Page="ARS_Admin_Login";
		}
		else
		{
		try {
			isPresent=airlineService.validateUser(login);
		} catch (AirlineException e) {
			model.addAttribute("errorMessage","Some technical problem has occured");
			JSP_Page="ARS_Error_Page";
		}
		if(isPresent)
		{
			session.setAttribute("flag","valid");
			JSP_Page="ARS_Admin_HomePage";
		}
		else
		{
			model.addAttribute("loginError","Invalid credentials");
			JSP_Page="ARS_Admin_Login";
		}
		}
	return JSP_Page;
  }
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method sets the source and destinations list to the ARS_View_FlightInfo.jsp page page
	 *                and sends the page to the client              
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815919  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/
	
	@RequestMapping("getFlightInformationPageCustomer")
	public String showFlightSearchPage(Model model){
		
		model.addAttribute("viewFlights",new ViewFlights());
		String jspPage=null;
		List<String> locationList=null;
		try
		{
		locationList=airlineService.getLocations();
		model.addAttribute("locationList",locationList);
		jspPage="ARS_View_FlightInfo_Customer";
		}
		catch(AirlineException e)
		{
			model.addAttribute("errorMessage",e.getMessage());
			jspPage="ARS_Error_Page";
		}
		
		    return jspPage;
	}
	
	
	
	
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method sets the source and destinations list to the ARS_View_FlightInfo.jsp page page
	 *                and sends the page to admin              
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : jc815790  Date : 19-12-2013 
	 * Author_Initials 19-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/
	
	@RequestMapping("getFlightInformationPageAdmin")
	public String showFlightSearchPageAdmin(Model model,HttpSession session){
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			
			model.addAttribute("viewFlights",new ViewFlights());
			List<String> locationList=null;
			try
			{
			locationList=airlineService.getLocations();
			model.addAttribute("locationList",locationList);
			jspPage="ARS_View_FlightInfo_Admin";
			}
			catch(AirlineException e)
			{
				model.addAttribute("errorMessage",e.getMessage());
				jspPage="ARS_Error_Page";				
			}
		   
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
		
	}
	
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method holds the values selected by the user and calls  service method to get 
	 *               flight information list             
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : jc815790  Date : 19-12-2013 
	 * Author_Initials 19-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/
	
	@RequestMapping("showFlightInformationAdmin")
	public String showFlightInformationAdmin(@ModelAttribute("viewFlights")@Valid ViewFlights viewFlights,BindingResult result,Model model,HttpSession session){
		List<String> locationList=null;
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			if (result.hasErrors()){
				
				try
				{
			     locationList=airlineService.getLocations();
				}
				catch(AirlineException e)
				{
					model.addAttribute("errorMessage",e.getMessage());
					jspPage="ARS_Error_Page";
				}
			    model.addAttribute("locationList",locationList);
			    jspPage="ARS_View_FlightInfo_Admin";
			}
			else{
				List<FlightInformation> flightInfoList=null;
				try
				{
			   flightInfoList=airlineService.viewFlightInformation(viewFlights);
				}
				catch(AirlineException e)
				{
					model.addAttribute("errorMessage",e.getMessage());
					jspPage="ARS_Error_Page";
				}
			  if(flightInfoList.isEmpty())
			  {
				  jspPage="ARS_No_Flight";
			  }
			  else
			  {
		       model.addAttribute("flightInfoList",flightInfoList);
		       jspPage="ARS_View_ShowFlights_Admin";
			  }
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
	}
	
	@RequestMapping("showAdminHomePage")
	public String showAdminPage(){
	  return "ARS_Admin_HomePage";	
	}
	
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	This method calls getFlightNumbers() from service and sends flight numbers list to
	 * 					ARS_Admin_BookingDetailsPage.jsp page
	 * Version:       	v1.0

	 * Author:jc815790				Date:17-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:showAdminBookingDetailsPage
	 * @param: model
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="showAdminBookingDetailsPage")
	public String showAdminBookingDetailsPage(Model model,HttpSession session)
	{
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try
			{
				flightNumberList=airlineService.getFlightNumbers();
				model.addAttribute("flightNumberList",flightNumberList);
				model.addAttribute("bookingInformation",new BookingInformation());
				jspPage="ARS_Admin_BookingDetailsPage";
			}
				
			catch(AirlineException e)
			{
				 model.addAttribute("errorMsg",e.getMessage());
				 jspPage="ARS_Admin_BookingDetails_ErrorPage";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		
		
		return jspPage;
	}
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	Based on flight number selected in ARS_Admin_BookingDetailsPage.jsp page
	 * 					booking details of that particular flight are retrieved from database and
	 * 					sent to ARS_Admin_BookingDetailsPage.jsp page
	 * Version:       	v1.0

	 * Author:jc815790				Date:18-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:processAdminBookingDetailsPage
	 * @param:bookingInformation
	 * @param: model
	 * @param: result
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="checkAdminBookingDetails")
	public String processAdminBookingDetailsPage(@ModelAttribute("bookingInformation") @Valid BookingInformation bookingInformation,BindingResult result,Model model,HttpSession session)

	{
		String jspPage=null;
		
		String flightNumber=bookingInformation.getFlightNumber();
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			if("select".equalsIgnoreCase(flightNumber))
			{
				try
				{
				flightNumberList=airlineService.getFlightNumbers();
				model.addAttribute("flightNumberList",flightNumberList);
				model.addAttribute("selectError","select flight number");
				jspPage="ARS_Admin_BookingDetailsPage";
				}
				catch(AirlineException e)
				{
					model.addAttribute("errorMsg", e.getMessage());
					jspPage="ARS_Admin_BookingDetails_ErrorPage";
					
				}
				
				
			}
			else
			{
			try
			{
			
			flightNumberList=airlineService.getFlightNumbers();
			model.addAttribute("flightNumberList",flightNumberList);
			bookingsList=airlineService.viewBookingDetails(flightNumber);
			
			if(!(bookingsList.isEmpty()))
			{
				
				model.addAttribute("bookingsList",bookingsList);
				jspPage="ARS_Admin_BookingDetailsPage";
			
			}
			else
			{
				model.addAttribute("errorMsg", "Bookings are not done......!!");
				jspPage="ARS_Admin_BookingDetails_ErrorPage";

			}
			}
			
			catch(AirlineException e)
			{
			   model.addAttribute("errorMsg", e.getMessage());
			   jspPage="ARS_Admin_BookingDetails_ErrorPage";
			}
			
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
		
	}
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	This method redirects to ARS_Admin_HomePage.jsp page
	 * 					from ARS_Admin_BookingDetailsPage.jsp page and ARS_Admin_PassengersListPage.jsp page
	 * Version:       	v1.0

	 * Author:jc815790				Date:18-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:backToAdminHomePage
	 * @param: model
	 * @return:String
	 ************************************************************************************/

	@RequestMapping(value="backToAdminHomePage")
	public String backToAdminHomePage(Model model)
	{
		return "ARS_Admin_HomePage";
	
	}
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	This method redirects to ARS_Admin_BookingDetailsPage.jsp page
	 * 					from ARS_Admin_BookingDetails_ErrorPage.jsp page 
	 * Version:       	v1.0

	 * Author:jc815790				Date:18-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:backToBookingPage
	 * @param: model
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="adminBookingPageError")
	public String backToBookingPage(Model model)
	{
		String jspPage=null;
		try
		{
			flightNumberList=airlineService.getFlightNumbers();
			model.addAttribute("flightNumberList",flightNumberList);
			model.addAttribute("bookingInformation",new BookingInformation());
			jspPage="ARS_Admin_BookingDetailsPage";
			
		}
			
		catch(AirlineException e)
		{
		   model.addAttribute("errorMsg", e.getMessage());
		   jspPage="ARS_Admin_BookingDetails_ErrorPage";
		}
		
		return jspPage;
		
	}
	
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	This method calls getFlightNumbers() from service and sends flight numbers list to
	 * 					ARS_Admin_PassengersListPage.jsp page
	 * Version:       	v1.0

	 * Author:vs815919				Date:19-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:showAdminPassengerListPage
	 * @param: model
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="showAdminPassengerListPage")
	public String showAdminPassengerListPage(Model model,HttpSession session)
	{
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try
			{
				flightNumberList=airlineService.getFlightNumbers();
				model.addAttribute("flightNumberList",flightNumberList);
				model.addAttribute("bookingInformation",new BookingInformation());
				jspPage="ARS_Admin_PassengersListPage";
				
			}
				
			catch(AirlineException e)
			{
			   model.addAttribute("errorMsg",e.getMessage());
			   jspPage="ARS_Admin_PassengerList_ErrorPage";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		
		return jspPage;
	}
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	Based on flight number selected in ARS_Admin_PassengersListPage.jsp page
	 * 					passenger list of that particular flight are retrieved from database and
	 * 					sent to ARS_Admin_PassengersListPage.jsp page
	 * Version:       	v1.0

	 * Author:vs815919				Date:19-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:processAdminPassengersListPage
	 * @param:bookingInformation
	 * @param: model
	 * @param: result
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="checkAdminPassengerList")
	public String processAdminPassengersListPage(@ModelAttribute("bookingInformation") @Valid BookingInformation bookingInformation,BindingResult result,Model model,HttpSession session)

	{
		String jspPage=null;
		String flightNumber=bookingInformation.getFlightNumber();
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			if("select".equalsIgnoreCase(flightNumber))
			{
				try
				{
				flightNumberList=airlineService.getFlightNumbers();
				model.addAttribute("flightNumberList",flightNumberList);
				model.addAttribute("selectError","select flight number");
				jspPage="ARS_Admin_PassengersListPage";
				}
				catch(AirlineException e)
				{
					model.addAttribute("errorMsg",e.getMessage());
					
					jspPage="ARS_Admin_PassengerList_ErrorPage";
				}
				
			}
			else
			{
			try
			{
			
			flightNumberList=airlineService.getFlightNumbers();
			model.addAttribute("flightNumberList",flightNumberList);
			passengersList=airlineService.viewPassengerListByFlightNo(flightNumber);
			
			
			if(!(passengersList.isEmpty()))
			{
				model.addAttribute("passengersList",passengersList);
				jspPage="ARS_Admin_PassengersListPage";
		
			}
			else
			{
				model.addAttribute("errorMsg", "Passengers List is Not Available......!!");
				jspPage="ARS_Admin_PassengerList_ErrorPage";

			}
			}
			
			catch(AirlineException e)
			{
			   model.addAttribute("errorMsg",e.getMessage());
			   jspPage="ARS_Admin_PassengerList_ErrorPage";
			}
			
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
	}
	
	/************************************************************************************
	 * File:        	AirlineController .java
	 * Package:       	com.igate.airline.controller
	 * Description:   	This method redirects to ARS_Admin_PassengersListPage.jsp page
	 * 					from ARS_Admin_PassengerList_ErrorPage.jsp page 
	 * Version:       	v1.0

	 * Author:vs815919				Date:19-12-2013         
	 * Modifications:
	 * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
	 * Method Name:backToPassengerListPage
	 * @param: model
	 * @return:String
	 ************************************************************************************/
	
	@RequestMapping(value="adminPassengerListPageError")
	public String backToPassengerListPage(Model model,HttpSession session)
	{
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try
			{
				flightNumberList=airlineService.getFlightNumbers();
				model.addAttribute("flightNumberList",flightNumberList);
				model.addAttribute("bookingInformation",new BookingInformation());
				jspPage="ARS_Admin_PassengersListPage";
			}
				
			catch(AirlineException e)
			{
			   model.addAttribute("errorMsg", e.getMessage());
			   jspPage="ARS_Admin_PassengerList_ErrorPage";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		
		return jspPage;
	}
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method sets the flight numbers to model attribute sends it to
	 *               to the ARS_Admin_UpdateFlightInformation.jsp.          
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815918  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/
	
	@RequestMapping(value = "showUpdateAdminPage")
	public String showUpdateFlightInformationPage(Model model,HttpSession session) {
		List<String> flightList=null;
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try {
				flightList = airlineService.getFlightNumbers();
			} catch (AirlineException e) {
				model.addAttribute("errorMessage", e.getMessage());
				jspPage="ARS_Error_Page";
			}
			if (flightList != null) {
				model.addAttribute("flightInformation", new FlightInformation());
				model.addAttribute("flightList", flightList);
				jspPage="ARS_Admin_UpdateFlightInformation";
			} else {
				jspPage="ARS_Admin_HomePage";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;

	}
	
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method sets the flight information and locatios list to the
	 *               model attribute and sends it to the ARS_Admin_UpdateFlightInformation.jsp            
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815918  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/

	@RequestMapping(value = "showUpdateFlightInformation")
	public String showFlightInformation(
			@ModelAttribute(value = "flightInformation")  FlightInformation flightInformation,
			BindingResult result, Model model, HttpSession session) {
		String jspPage=null;
		List<String> flightList=null;
		FlightInformation flightInfo=null;
		List<String> locations=null;
		String flighttNo = flightInformation.getFlightNumber();
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			if ("select".equals(flighttNo)) {
				model.addAttribute("selectError", "Please select flight number");
				try {
					flightList = airlineService.getFlightNumbers();
				} catch (AirlineException e) {
					model.addAttribute("errorMessage", e.getMessage());
					jspPage="ARS_Error_Page";
				}
				if (flightList != null) {
					model.addAttribute("flightInformation", new FlightInformation());
					model.addAttribute("flightList", flightList);
					jspPage="ARS_Admin_UpdateFlightInformation";
				} else {
					jspPage="ARS_Admin_HomePage";
				}
				jspPage="ARS_Admin_UpdateFlightInformation";
			} else {
				try {
					flightInfo= airlineService
						.getFlightInformationById(flighttNo);
					locations= new ArrayList<String>();

				
					locations = airlineService.getLocations();
					
				} catch (AirlineException e) {
					model.addAttribute("errorMessage", e.getMessage());
					jspPage="ARS_Error_Page";
				}
				if (flightInfo!= null && !(locations.isEmpty())) {
					session.setAttribute("departureDate", flightInfo
							.getDepartureDate());
					session
							.setAttribute("arrivalDate", flightInfo
									.getArrivalDate());
					model.addAttribute("flightInformation", flightInfo);
					model.addAttribute("locations", locations);
					jspPage="ARS_Admin_UpdateFlightInformation";
				} else {
					model.addAttribute("List is empty");
					jspPage="ARS_Admin_UpdateFlightInformation";

				}
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;

	}
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method takes the information from ARS_Admin_UpdateFlightInformation.jsp
	 *               and calls update method of service layer and sends success message to the same page          
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815918,mk815797  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/


	@RequestMapping(value = "updateFlightInformation")
	public String processManageFlightInformation(
			@RequestParam("onSubmit") String submit,
			@ModelAttribute(value = "flightInformation") FlightInformation flightInformation,
			BindingResult result, Model model, HttpSession session) {
		boolean status = false;
		String jspPage=null;
		boolean depTimeStatus=false;
		boolean arrTimeStatus=false;
		boolean firstSeat=false;
		boolean firstSeatFare=false;
		boolean bussSeat=false;
		boolean bussSeatFare=false;
		String departureTime=null;
		String arrivalTime=null;
		int firstSeatNumber=0;
		double fSeatFare=0.0;
		int bussSeatNumber=0;
		double bSeatFare=0.0;
		String depCity=null;
		String arrCity=null;
		List<String> locations=null;
		depCity=flightInformation.getDepartureCity();
		arrCity=flightInformation.getArrivalCity();
		departureTime=flightInformation.getDepartureTime();
		arrivalTime=flightInformation.getArrivalTime();
		firstSeatNumber=flightInformation.getFirstSeatNumber();
		fSeatFare=flightInformation.getFirstSeatFare();
		bussSeatNumber=flightInformation.getBusinessSeatNumber();
		bSeatFare=flightInformation.getBusinessSeatFare();
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try {
				if ("Update".equals(submit)) {
					if(departureTime=="" || arrivalTime=="" || firstSeatNumber==0 || fSeatFare==0 || bussSeatNumber==0 || bSeatFare==0)
					{
						if(departureTime=="")
						{
						model.addAttribute("depTimeError","Please enter departure time");
						}
						if(arrivalTime=="")
						{
						model.addAttribute("arrTimeError","Please enter arrival time");
						}
						if(firstSeatNumber==0)
						{
						model.addAttribute("firstSeatError","Please enter first seat number");
						}
						if(fSeatFare==0)
						{
						model.addAttribute("firstSeatFareError","Please enter first seat fare");
						}
						if(bussSeatNumber==0)
						{
						model.addAttribute("bussSeatError","Please enter business seat number");
						}
						if(bSeatFare==0)
						{
						model.addAttribute("bussSeatFareError","Please enter business seat fare");
						}
						try {
							
							locations= new ArrayList<String>();

						
							locations = airlineService.getLocations();
							model.addAttribute("locations", locations);
							jspPage="ARS_Admin_UpdateFlightInformation";
						} catch (AirlineException e) {
							model.addAttribute("errorMessage", e.getMessage());
							jspPage="ARS_Error_Page";
						}
						
					}
					else if(depCity.equalsIgnoreCase(arrCity))
					{
						model.addAttribute("depArrCityError","Source destination should not be same");
                       try {
							
							locations= new ArrayList<String>();

						
							locations = airlineService.getLocations();
							model.addAttribute("locations", locations);
							jspPage="ARS_Admin_UpdateFlightInformation";
						} catch (AirlineException e) {
							model.addAttribute("errorMessage", e.getMessage());
							jspPage="ARS_Error_Page";
						}
						
					}
					else
					{
					depTimeStatus= airlineService
					.validateDepartureTime(departureTime);
					arrTimeStatus=airlineService.validateArrivalTime(arrivalTime);
					firstSeat=airlineService.validateFirstSeat(firstSeatNumber);
					firstSeatFare=airlineService.validateFirstSeatFare(fSeatFare);
					bussSeat=airlineService.validatebussSeat(bussSeatNumber);
					bussSeatFare=airlineService.validatebussSeatFare(bSeatFare);
					if(depTimeStatus==true && arrTimeStatus==true && firstSeat==true && firstSeatFare==true && bussSeat==true && bussSeatFare==true)
					{
						if (flightInformation.getDepartureDate() == null) {
							Date depDate = (Date) session
									.getAttribute("departureDate");
							Date arrDate = (Date) session
									.getAttribute("arrivalDate");
							flightInformation.setDepartureDate(depDate);
							flightInformation.setArrivalDate(arrDate);

							status = airlineService
									.updateFlightInformation(flightInformation);

						} else {

							status = airlineService
									.updateFlightInformation(flightInformation);

						}
						if (status == true) {
							model.addAttribute("updateMsg", "Updated Successfully");
							jspPage="ARS_Admin_UpdateFlightInformation";
						} else {
							model.addAttribute("updateMsg",
									"Not Successfully Updated");
							jspPage="ARS_Admin_HomePage";
						}
					}
					else
					{
						if(depTimeStatus==false)
						{
						model.addAttribute("depTimeError","Invalid departure time");
						}
						if(arrTimeStatus==false)
						{
						model.addAttribute("arrTimeError","Invalid arrival time");
						}
						if(firstSeat==false)
						{
						model.addAttribute("firstSeatError","Invalid first seat number");
						}
						if(firstSeatFare==false)
						{
						model.addAttribute("firstSeatFareError","Invalid first seat fare");
						}
						if(bussSeat==false)
						{
						model.addAttribute("bussSeatError","Invalid business seat number");
						}
						if(bussSeatFare==false)
						{
						model.addAttribute("bussSeatFareError","Invalid business seat fare");
						}
                          try {
							
							locations= new ArrayList<String>();

						
							locations = airlineService.getLocations();
							model.addAttribute("locations", locations);
							jspPage="ARS_Admin_UpdateFlightInformation";
						} catch (AirlineException e) {
							model.addAttribute("errorMessage", e.getMessage());
							jspPage="ARS_Error_Page";
						}
						
					}
					}
					}
				

				else if ("Delete".equals(submit)) {

					boolean delete = false;

					delete = airlineService
							.deleteFlightInformation(flightInformation
									.getFlightNumber());
					if (delete == true) {

						model.addAttribute("deleteMessage", "Successfully Deleted");
						jspPage="ARS_Admin_UpdateFlightInformation";
					} else {
						model.addAttribute("deleteMessage",
								"Not Successfully deleted");
						jspPage="ARS_Admin_HomePage";
					}
				} else {
					jspPage="ARS_customer_errorPage";
				}
			} catch (AirlineException e) {

				model.addAttribute("errorMessage", "Some technical problem has occured");
				jspPage="ARS_AdminUpdate_Error";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt");
			jspPage="ARS_Error_Page";
		}
		
		
		
		return jspPage;

	}
	/****************************************************************************************
	 * File : AirlineController.java
	 * Package : com.igate.airline.controller
	 * Description : This method holds the values selected by the user and calls  service method to get 
	 *               flight information list             
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815919  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param model
	 * @return String
	 **************************************************************************************/
	
	@RequestMapping("showFlightInformationCustomer")
	public String showFlightInformationCustomer(@ModelAttribute("viewFlights")@Valid ViewFlights viewFlights,BindingResult result,Model model){
		String jspPage=null;
		if (result.hasErrors()){
			List<String> locationList=null;
			try
			{
		     locationList=airlineService.getLocations();
		     model.addAttribute("locationList",locationList);
		     jspPage="ARS_View_FlightInfo_Customer";
			}
			catch(AirlineException e )
			{
				model.addAttribute("errorMessage",e.getMessage());
				jspPage="ARS_Error_Page";
			}
		    
		}
		else{
			List<FlightInformation> flightInfoList=null;
			try
			{
		      flightInfoList=airlineService.viewFlightInformation(viewFlights);
			}
			catch(AirlineException e)
			{
				model.addAttribute("errorMessage",e.getMessage());
				jspPage="ARS_Error_Page";				
			}
		  if(flightInfoList.isEmpty()){
			  jspPage="ARS_No_Flight";
		  }
		  else{
			  model.addAttribute("bookFlightInfo",new FlightInformation());
		      model.addAttribute("flightInfoList",flightInfoList);
		      jspPage="ARS_View_ShowFlights_Customer";
		  }
		}
		return jspPage;
	}

	/*******************************************************************************************************************************************
	 * 
	 * File 		: AirlineController.java
	 * Package 		: com.igate.airline.controller
	 * Description 	:This method used to create the model attribute and return to the BookingInformationForm 
	 * 				to enter the user details
	 * Version 		: v1.0
	 * Restrictions	:  
	 * Modifications :
	 * Author 		 : rc815928  Date : 18-12-2013 
	 * Author_Initials :18-12-2013 Initial Version 
	 *  
	 * @param model
	 * @return
	 ********************************************************************************************************************************************/
	
	@RequestMapping(value="Booking")
	public String showBookingDetailsForm(@ModelAttribute(value="bookFlightInfo")FlightInformation flightInfo,Model model,HttpSession session) {
	
		
		
		session.setAttribute("flightInfo",flightInfo);
			
		BookingInformation bookingBeanObject=new BookingInformation();
		model.addAttribute("bookingDetailsFormAttribute",bookingBeanObject);
		model.addAttribute("condition",false);
		model.addAttribute("bookingStatus",false);
		model.addAttribute("errorMessage",false);
		
		return "BookingInformationForm";
		
		
	}//showBookingDetailsForm(Model model)

	/******************************************************************************************************************************************
	 * File 		: AirlineController.java
	 * Package		: com.igate.airline.controller
	 * Description 	: This method takes the information from the BookingInformationForm using model attribute
	 * 				  and again redirect to the BookingInformationForm for show the booking details for confirmation
	 * Version 		: v 1.0
	 * Restrictions	:  
	 * Modifications:
	 * Author 		:rc815928  
	 * Date 		: 18-12-2013 
	 * Author_Initials:18-12-2013 Initial Version 
	 * Modified by	:
	 * Modified on Date:
	 * Modifications:
	 * @param book
	 * @param result
	 * @param model
	 * @return
	 *******************************************************************************************************************************************/
	
	@RequestMapping(value="showBookingFormForConfirm")
	public String showDetailsToConfirm(@ModelAttribute("bookingDetailsFormAttribute")@Valid BookingInformation book,BindingResult result,Model model,HttpSession session){
		String jspPage=null;
		double totalFare=0;
		double bussFare=0;
		double firstSeatFare=0;
		FlightInformation flightInfo=new FlightInformation();
		flightInfo=(FlightInformation)session.getAttribute("flightInfo");
		
		model.addAttribute("flightNumber",flightInfo.getFlightNumber());
        session.setAttribute("fNumber",flightInfo.getFlightNumber());
			
		if(result.hasErrors()){
			jspPage="BookingInformationForm";
		}
		else {
			
			if(flightInfo.getFlightNumber()==null)
			{
				jspPage="ARS_View_FlightInfo_Customer";
			
			}
			else
			{
			
			
				if((book.getCustomerEmail()=="")||(book.getNumberOfPassengers()==0)||(book.getCreditCardInformation()=="")||(book.getClassType()=="Select"))
				{
					jspPage="ErrorPage";
				}
				else
				{	 
	                   if("BusinessClass".equalsIgnoreCase(book.getClassType()))	
	                   {
	                	   bussFare=flightInfo.getBusinessSeatFare();
	                	   totalFare=book.getNumberOfPassengers()*bussFare;
	                   }
	                   else
	                   {
	                	   firstSeatFare=flightInfo.getFirstSeatFare();
	                	   totalFare=book.getNumberOfPassengers()*firstSeatFare;
	                   }
	                   model.addAttribute("totalFare",totalFare);
	                   session.setAttribute("total",totalFare);
	                   
	                   
	                   
					   model.addAttribute("condition",true); 
					   jspPage="BookingInformationForm";
				}
			}
		}
		return jspPage;
		
		
	}//showDetailsToConfirm()
	
	/**********************************************************************************************************************************************
	 * File 		: AirlineController.java
	 * Package 		: com.igate.airline.controller
	 * Description 	: This method call addBookingInformation() of  IAirlineService class 
	 * 				  and return to the BookingInformationForm.jsp page
	 * 
	 * Version 		: v1.0
	 * Restrictions	:  
	 * Author		: rc815928  
	 * Date			: 18-12-2013
	 * Author_Initials 18-12-2013 Initial Version
	 * Modified by	:
	 * Modified on Date:
	 * Modifications:
	 * 
	 * @param book
	 * @param result
	 * @param model
	 * @return
	 *************************************************************************************************************************************************/
	
	@RequestMapping(value="addBookingDetails")
	public String generateBookingId(@ModelAttribute("bookingDetailsFormAttribute")@Valid BookingInformation book,BindingResult result,Model model,HttpSession session)
	{
		String jspPage=null;
		String bookingId=null;
		FlightInformation flightInfo=new FlightInformation();
		flightInfo=(FlightInformation)session.getAttribute("flightInfo");
		
		double totalFare=(Double)session.getAttribute("total");
		String flighNumber=(String)session.getAttribute("fNumber");
		
		
		
		if(result.hasErrors()){
			jspPage="BookingInformationForm";
		}
		else{
			BookingInformation bookingInformationObject=new BookingInformation();
			bookingInformationObject.setCustomerEmail(book.getCustomerEmail());
			bookingInformationObject.setNumberOfPassengers(book.getNumberOfPassengers());
			bookingInformationObject.setCreditCardInformation(book.getCreditCardInformation());
			bookingInformationObject.setClassType(book.getClassType());
			bookingInformationObject.setFlightNumber(flighNumber);
			bookingInformationObject.setTotalFare(totalFare);
			bookingInformationObject.setDepartureCity(flightInfo.getDepartureCity());
			bookingInformationObject.setArrivalCity(flightInfo.getArrivalCity());
			try{
				bookingId=airlineService.addBookingInformation(bookingInformationObject);
				model.addAttribute("bookingIdModel",bookingId);
			}
			catch (AirlineException dae){
				model.addAttribute("bookingIdModel",bookingId);
				model.addAttribute("exceptionMessage",dae);	
				jspPage="BookingInformationForm";
			}
			model.addAttribute("bookingStatus",true);
			jspPage="BookingInformationForm";
		}
		return jspPage;
		
	}//generateBookingId()
	
	/******************************************************************************************************************************************************
	 * File 		: AirlineController.java
	 * Package 		: com.igate.airline.controller
	 * Description 	: This method we will get the location names by calling the method getLocations() of IAirlineService and
	 * 				  return to the ARS_Admin_AddFlightInformation.jsp page and display the departure city and arrival city in comBoxes
	 * Version 		: v1.0
	 * Restrictions	:  
	 * Author 		: rc815928  
	 * Date 		: 19-12-2013
	 * Author_Initials 18-12-2013 Initial Version
	 * Modified by	:
	 * Modified on Date:
	 * Modifications:
	 * 
	 * 
	 *******************************************************************************************************************************************************/
	
	@RequestMapping("showAdminAddPage")
	public String showFlightDetailsForm(Model model,HttpSession session){
		String jspPage=null;
		List<String> locationList=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		if("valid".equalsIgnoreCase(flag))
		{
			try {
				locationList = airlineService.getLocations();
				model.addAttribute("locationList",locationList);
				model.addAttribute("flightInformation",new FlightInformation());
				jspPage="ARS_Admin_AddFlightInformation";
			} catch (AirlineException e) {
				model.addAttribute("errorMessage", e.getMessage());
				jspPage="ARS_Error_Page";
			}
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt!!!");
			jspPage="ARS_Error_Page";
		}
		
		
		return jspPage;
	}
	
	
	
	/******************************************************************************************************************************************************
	 * File 		: AirlineController.java
	 * Package 		: com.igate.airline.controller
	 * Description 	: This method call add the AirlineServiceImplementation addFlightInformation() 
	 * 				  and return to the ARS_Admin_AddFlightInformation.jsp page if the flight added 
	 * 				  successfully else it will go to ARS_Admin_HomePage
	 * Version 		: v1.0
	 * Restrictions	:  
	 * Author 		: rc815928  
	 * Date 		: 19-12-2013
	 * Author_Initials 18-12-2013 Initial Version
	 * Modified by	:
	 * Modified on Date:
	 * Modifications:
	 ********************************************************************************************************************************************************/
	
	@RequestMapping(value="addFlightInformation")
	public String addFlightInformation(@ModelAttribute(value="flightInformation")@Valid FlightInformation flightInformation,BindingResult result,Model model,HttpSession session){
		boolean status=false;
		
		String jspPage=null;
		String flag=null;
		flag=(String)session.getAttribute("flag");
		
		if("valid".equalsIgnoreCase(flag))
		{
			try {
				try {
					status=airlineService.addFlightInformation(flightInformation);
				} catch (AirlineException e) {
					model.addAttribute("errorMessage",e.getMessage());
					jspPage="ARS_Error_Page";
				}
			} catch (Exception dao) {
				model.addAttribute("errorMessage",dao.getMessage());
				jspPage="ARS_Error_Page";
			}
			if(status==true)
			{
				model.addAttribute("condition","successfully registered");
				jspPage="ARS_Admin_AddFlightInformation";
			}
			else
			{
				jspPage="ARS_Admin_HomePage";
			}
			
		}
		else
		{
			model.addAttribute("errorMessage","Invalid attempt!!!");
			jspPage="ARS_Error_Page";
		}
		
		return jspPage;
	}
	
	
	/******************************************************************************************************************************************************
	 * File 		: AirlineController.java
	 * Package 		: com.igate.airline.controller
	 * Description 	: This method shows home page
	 * Version 		: v1.0
	 * Restrictions	:  
	 * Author 		: sv815918  
	 * Date 		: 19-12-2013
	 * Author_Initials 18-12-2013 Initial Version
	 * Modified by	:
	 * Modified on Date:
	 * Modifications:
	 *****************************************************************************************/
	@RequestMapping("/showHomePage")
	public String showHomePage(HttpSession session)
	{
		session.invalidate();
		return "ARS_Home_Page";
		
	}
	@RequestMapping(value="/showAbout")
	public String showAbout()
	{
		return "ARS_About";
	}
	@RequestMapping(value="/showPNRPage")
	public String showBookingDetailsPage(Model model)
	{
		
		model.addAttribute("bookingInformation",new BookingInformation());	
		model.addAttribute("check","false");
		return "ARS_customer_showPNRPage";
		
		
	}
	

}
