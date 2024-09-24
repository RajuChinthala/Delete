package com.igate.airline.service;

import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Login;
import com.igate.airline.bean.ViewFlights;
import com.igate.airline.dao.IAirlineDao;
import com.igate.airline.exception.AirlineException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/************************************************************************************
 *Class Name      : AirlineServiceImplementation 
 * Description    : This is the Service class  which has methods to call DAO methods  
 * Author         : Team 5 
 * Last Edited By : 
 * Version        : <V 1.0>
 ***********************************************************************/
@Component("airlineService")
public class AirlineServiceImplementation implements IAirlineService {
    @Autowired
    IAirlineDao airlineDao;
    static Logger myLogger = null;

    public AirlineServiceImplementation() {
        myLogger = Logger.getLogger("LoggingInterceptor.class");
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the departure time
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/
    @Override
    public boolean validateDepartureTime(String depTime) {
        String input = depTime;
        boolean status = false;
        Pattern depTimePattern = Pattern.compile("^(([01][0-9])|2[0-3]):[0-5][0-9]$");
        Matcher m = depTimePattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the arrival time
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/
    @Override
    public boolean validateArrivalTime(String arrTime) {
        String input = arrTime;
        boolean status = false;
        Pattern depTimePattern = Pattern.compile("^(([01][0-9])|2[0-3]):[0-5][0-9]$");
        Matcher m = depTimePattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the first seat
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/
    @Override
    public boolean validateFirstSeat(int firstSeat) {
        String input = firstSeat + "";
        boolean status = false;
        Pattern firstSeatPattern = Pattern.compile("^[0-9]+$");
        Matcher m = firstSeatPattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the first seat fare
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/

    @Override
    public boolean validateFirstSeatFare(double firstSeatFare) {
        String input = firstSeatFare + "";

        boolean status = false;
        Pattern firstSeatFarePattern = Pattern.compile("^[0-9]+|[0-9]+[.]+[0-9]+$");
        Matcher m = firstSeatFarePattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the business seat
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/

    @Override
    public boolean validatebussSeat(int bussSeat) {
        String input = bussSeat + "";

        boolean status = false;
        Pattern bussSeatPattern = Pattern.compile("^[0-9]+$");
        Matcher m = bussSeatPattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method validates the business seat fare
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 19-12-2013
     * @param :depTime
     * @return boolean
     **************************************************************************************/

    @Override
    public boolean validatebussSeatFare(double bussSeatFare) {
        String input = bussSeatFare + "";
        boolean status = false;

        Pattern bussSeatFarePattern = Pattern.compile("^[0-9]+|[0-9]+[.]+[0-9]+$");
        Matcher m = bussSeatFarePattern.matcher(input);
        if (m.find()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's validateUser method to get user details
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sk815835  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @throws AirlineException
     **************************************************************************************/

    @Override
    public boolean validateUser(Login login) throws AirlineException {
        boolean isPresent = false;
        try {
            isPresent = airlineDao.validateUser(login);
        } catch (Exception e) {
            myLogger.info(e.getMessage());
            throw new AirlineException(e.getMessage());
        }

        return isPresent;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's getCustomerDetailsById method to get customer details
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     **************************************************************************************/

    @Override
    public BookingInformation getCustomerDetailsById(String bookingId) throws AirlineException {
        BookingInformation bookingInformation = new BookingInformation();
        try {
            bookingInformation = airlineDao.getCustomerDetailsById(bookingId);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }
        return bookingInformation;

    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's cancelBooking method to cancel the booking
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     **************************************************************************************/

    @Override
    public boolean cancelBooking(String bookingId) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.cancelBooking(bookingId);
        } catch (Exception e) {
            myLogger.info(e.getMessage());
            throw new AirlineException(e.getMessage());
        }

        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's getValidPNR method to check availability of
     *              PNR
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author :mk815797   Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     **************************************************************************************/

    @Override
    public boolean getValidPNR(String bookingId) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.getValidPNR(bookingId);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }

        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's updateCustomerDetails method to update
     *              customer details
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : sv815918  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param BookingInformation
     * @return boolean
     **************************************************************************************/

    @Override
    public boolean updateCustomerDetails(BookingInformation bookingInformation) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.updateCustomerDetails(bookingInformation);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }

        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's viewFlightInformation method to get list
     *              of flight information and sends back to the controller
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param viewFlights
     * @return List<FlightInformation>
     **************************************************************************************/

    public List<FlightInformation> viewFlightInformation(ViewFlights viewFlights) throws AirlineException {
        List<FlightInformation> flightInfoList = new ArrayList<FlightInformation>();
        try {
            flightInfoList = airlineDao.viewFlightInformation(viewFlights);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException("Some thechincal problem sorry for inconvience");
        }
        return flightInfoList;
    }

    /************************************************************************************
     * File:        	AirlineDaoImplementation .java
     * Package:       	com.igate.airline.service
     * Description:   	This method calls viewBookingDetails(String flightNumber) from AirlineDaoImplementation .java and
     *               	list to the controller
     * Version:       	v1.0
     * Author:jc815790				Date:18-12-2013
     * Modifications:
     * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
     * Method Name:viewBookingDetails
     * @param: flightNumber
     * @return:List<BookingInformation>
     ************************************************************************************/
    public List<BookingInformation> viewBookingDetails(String flightNumber) throws AirlineException {
        List<BookingInformation> BookingsList = null;
        try {
            BookingsList = airlineDao.viewBookingDetails(flightNumber);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException("Some thechincal problem sorry for inconvience");
        }
        return BookingsList;
    }

    /************************************************************************************
     * File:        	AirlineDaoImplementation .java
     * Package:       	com.igate.airline.service
     * Description:   	This method calls viewPassengerListByFlightNo(String flightNumber) from AirlineDaoImplementation .java and
     *               	list to the controller
     * Version:       	v1.0
     * Author:vs815919			Date:19-12-2013
     * Modifications:
     * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
     * Method Name: viewPassengerListByFlightNo
     * @param: flightNumber
     * @return:List<BookingInformation>
     ************************************************************************************/
    @Override
    public List<BookingInformation> viewPassengerListByFlightNo(String flightNumber) throws AirlineException {
        List<BookingInformation> PassengersList = null;
        try {
            PassengersList = airlineDao.viewPassengerListByFlightNo(flightNumber);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException("Some thechincal problem sorry for inconvience");
        }
        return PassengersList;
    }


    /***************************************************************************************
     *	File 		: AirlineServiceImplementation.java
     * Package 		: com.igate.airline.service
     * Description	: This method is called from AirlineController,in this method we pass
     * 				 the  booking information details and in this method we call the dao
     * 				 method returns the bookingId.
     * Version 		: v1.0
     * Restrictions	:
     * Modifications :
     * Author 		: rc815928
     * Date 		: 18-12-2013
     * Author_Initials:18-12-2013 Initial Version
     * Modification Date:
     * Modifications :
     * Method Name: addBookingInformation
     * Input 	  : BookingInformation
     * return type: String(BookingId)
     * @throws AirlineException
     ****************************************************************************************/

    @Override
    public String addBookingInformation(BookingInformation bookingInformationObject) throws AirlineException {
        String bookingId = null;
        try {
            bookingId = airlineDao.addBookingInformation(bookingInformationObject);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }
        return bookingId;
    }

    /*********************************************************************************
     * File 		: AirlineServiceImplementation.java
     * Package 		: com.igate.airline.service
     * Description	: This method is called from AirlineController this method takes
     * 				  the  flight information details and calls the dao addFlgihtInfomration() method and returns the boolean.
     * Version 		: v1.0
     * Restrictions	:
     * Modifications:
     * Author 		: rc815928
     * Date 		: 19-12-2013
     * Author_Initials:18-12-2013 Initial Version
     * Modification Date:
     * Changes Made :
     * Method Name: addFlightInformation
     * Input 	  : FlightInformation
     * return type: boolean
     * @throws AirlineException
     * @throws AirlineException
     ***********************************************************************************/

    @Override
    public boolean addFlightInformation(FlightInformation flightInformation) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.addFlightInformation(flightInformation);
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }
        return status;
    }

    /************************************************************************************
     * File:        	AirlineServiceImplementation .java
     * Package:       	com.igate.airline.service
     * Description:   	This method calls getFlightNumbers() from AirlineDaoImplementation .java and returns the
     *               	list to the controller
     * Version:       	v1.0
     * Author:jc815790				Date:17-12-2013
     * Modifications:
     * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
     * Method Name:getFlightNumbers
     * @param:
     * @return:List<String>
     ************************************************************************************/

    public List<String> getFlightNumbers() throws AirlineException {
        List<String> flightList = null;
        try {
            flightList = airlineDao.getFlightNumbers();
        } catch (Exception e) {
            myLogger.error(e.getMessage());
            throw new AirlineException("Some thechincal problem sorry for inconvience");
        }
        return flightList;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method will call the getFlightInformationById() and return the flight
     *               information of a particular flight
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 17-12-2013
     * Author_Initials 17-12-2013 Initial Version
     * @param :flightNumber
     * @return String
     * @throws AirlineException
     * @throws AirlineException
     **************************************************************************************/


    public FlightInformation getFlightInformationById(String flightNumber) throws AirlineException {
        FlightInformation filghtInfo = null;
        try {
            filghtInfo = airlineDao.getFlightInformationById(flightNumber);
        } catch (Exception exception) {
            myLogger.error(exception.getMessage());
            throw new AirlineException(exception.getMessage());
        }
        return filghtInfo;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description :
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 17-12-2013
     * Author_Initials 17-12-2013 Initial Version
     * @param :FlightInformation
     * @return :boolean
     * @throws :AirlineException
     **************************************************************************************/


    public boolean updateFlightInformation(FlightInformation flightInformation) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.updateFlightInformation(flightInformation);
        } catch (ParseException e) {
            myLogger.error(e.getMessage());
            throw new AirlineException(e.getMessage());
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method will call deleteFlightInformation() method of dao layer and
     *                 returns the status
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 17-12-2013
     * Author_Initials 17-12-2013 Initial Version
     * @param :flightNumber
     * @return :boolean
     * @throws AirlineException
     * @throws :AirlineException
     **************************************************************************************/


    public boolean deleteFlightInformation(String flightNumber) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.deleteFlightInformation(flightNumber);
        } catch (Exception exception) {
            myLogger.error(exception.getMessage());
            throw new AirlineException(exception.getMessage());
        }
        return status;

    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method will call the getStatusDelete() method of dao layer and returns
     *               status
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 17-12-2013
     * Author_Initials 17-12-2013 Initial Version
     * @param :flightNumber
     * @return :boolean
     * @throws AirlineException
     * @throws :AirlineException
     **************************************************************************************/


    public boolean getStatusDelete(String flightNumber) throws AirlineException {
        boolean status = false;
        try {
            status = airlineDao.getStatusDelete(flightNumber);
        } catch (Exception exception) {
            myLogger.error(exception.getMessage());
            throw new AirlineException(exception.getMessage());
        }

        return status;
    }

    /****************************************************************************************
     * File : AirlineServiceImplementation.java
     * Package : com.igate.airline.service
     * Description : This method calls airlineDao's getLocations() method to get list of locations
     *               and sends back to the controller
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 17-12-2013
     * Author_Initials 17-12-2013 Initial Version
     * @param
     * @return List<String>
     * @throws AirlineException
     **************************************************************************************/
    @Override
    public List<String> getLocations() throws AirlineException {

        List<String> locationList = new ArrayList<String>();
        try {
            locationList = airlineDao.getLocations();
        } catch (DataAccessException exception) {
            myLogger.error(exception.getMessage());
            throw new AirlineException("Some thechincal problem sorry for inconvience");
        }
        return locationList;
    }


}
