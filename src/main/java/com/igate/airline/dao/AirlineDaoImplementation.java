package com.igate.airline.dao;

import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Login;
import com.igate.airline.bean.ViewFlights;
import com.igate.airline.exception.AirlineException;
import jakarta.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/************************************************************************************
 *Class Name      : AirlineDaoImplementation 
 * Description    : This is the DAO class  which has methods to fetch data from data base 
 * Author         : Team 5 
 * Last Edited By : 
 * Version        : <V 1.0>
 ***********************************************************************/

@Component("airlineDao")
public class AirlineDaoImplementation implements IAirlineDao {

    @Autowired
    JdbcTemplate simpleJdbcTemplate;
    static Logger myLogger = null;

    public AirlineDaoImplementation() {
        myLogger = Logger.getLogger("LoggingInterceptor.class");
    }

/*    @PostConstruct
    public void init() {
        myLogger.info("---------start location_master creation-------");
        //simpleJdbcTemplate.execute("CREATE TABLE IF NOT EXISTS location_master (location_id INT, location_name VARCHAR(255), location_city VARCHAR(255), location_state VARCHAR(255), location_zipCode VARCHAR(255),  PRIMARY KEY (location_id))");
        simpleJdbcTemplate.execute("INSERT INTO location_master (locationId, locationCity, locationState, locationZipCode) VALUES " +
                "(1,'HYD','TG','94536'), (2,'PUNE','MH','94536')");
        myLogger.info("---------end location_master creation-------");
        myLogger.info("---------start user_master creation-------");
        //simpleJdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user_master (user_id INT, user_name VARCHAR(255), password VARCHAR(255), role VARCHAR(255), PRIMARY KEY (user_id))");
        simpleJdbcTemplate.execute("INSERT INTO user_master (user_id, user_name, password, role) VALUES " +
                "(1,'RAJU','RAJU', 'admin'), (2,'PRAGYAN','PRAGYAN','admin')");
        myLogger.info("---------end user_master creation-------");
    }*/

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method takes booking Id  from the booking_information_master
     *              and returns the boolean value to service
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return boolean value
     **************************************************************************************/

    @Override
    public boolean getValidPNR(String bookingId) {
        String sql = "";
        int validPNR = 0;
        boolean statusPNR = false;
        sql = "SELECT 1 FROM booking_information_master WHERE booking_id=? AND is_active=1";
        myLogger.info(sql);
        Object[] params = new Object[]{bookingId};
        validPNR = simpleJdbcTemplate.update(sql, params);

        if (validPNR != 0) {
            statusPNR = true;
        } else {
            statusPNR = false;
        }

        return statusPNR;

    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : For valid booking Id this method takes booking Id  from the booking_information_master
     *              and returns the customer details to service
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return boolean value
     **************************************************************************************/

    @Override
    @SuppressWarnings("unchecked")
    public BookingInformation getCustomerDetailsById(String bookingId) {

        String sql = "";

        sql = "SELECT flight_number,source_city,destination_city,customer_email,no_of_passengers,total_fare,booking_id FROM booking_information_master WHERE booking_id=?";
        myLogger.info(sql);
        RowMapper mapper = new RowMapper() {
            public BookingInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
                BookingInformation bookingInfo = new BookingInformation();

                bookingInfo.setFlightNumber(rs.getString(1));
                bookingInfo.setDepartureCity(rs.getString(2));
                bookingInfo.setArrivalCity(rs.getString(3));
                bookingInfo.setCustomerEmail(rs.getString(4));
                bookingInfo.setNumberOfPassengers(rs.getInt(5));
                bookingInfo.setTotalFare(rs.getInt(6));
                bookingInfo.setBookingId(rs.getString(7));


                return bookingInfo;


            }

        };
        BookingInformation bookingInformation = (BookingInformation) simpleJdbcTemplate.queryForObject(sql, mapper, bookingId);
        return bookingInformation;
    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description :This method takes booking id and delete the customer details from
     booking_information master.Actually it is a soft delete therefore
     update query is used instead of delete.After action is performed
     respective boolean value return to the service.
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return boolean value
     **************************************************************************************/

    @Override
    public boolean cancelBooking(String bookingId) {
        int update = 0;
        boolean status = false;
        String sql = "";
        sql = "UPDATE booking_information_master SET is_active=0 WHERE booking_id=?";
        myLogger.info(sql);
        Object[] params = new Object[]{bookingId};
        update = simpleJdbcTemplate.update(sql, params);
        if (update == 0) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description :This method takes booking id and update the customer details from
     booking_information master.After action is performed respective boolean value
     return to the service.
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : mk815797  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return boolean value
     **************************************************************************************/

    @Override
    public boolean updateCustomerDetails(BookingInformation bookingInformation) {
        int update = 0;
        boolean status = false;
        String sql = "";

        sql = "UPDATE booking_information_master SET customer_email=? WHERE booking_id=?";
        myLogger.info(sql);
        Object[] params = new Object[]{bookingInformation.getCustomerEmail(), bookingInformation.getBookingId()};
        update = simpleJdbcTemplate.update(sql, params);
        if (update == 0) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    @Override
    public boolean validateUser(Login login) {
        boolean userPresent = false;
        String sql = "";
        sql = "Select role from  user_master where user_name=? AND password=?";
        myLogger.info(sql);
        Object[] params = {login.getUserName(), login.getPassword()};
        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int row) throws SQLException {

                String role = rs.getString(1);

                return role;
            }

        };
        String role = simpleJdbcTemplate.queryForObject(sql, mapper, params);

        if ("admin".equalsIgnoreCase(role)) {
            userPresent = true;
        }

        return userPresent;
    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method gets flight information details from the flight_information_master
     *              and returns the list to service
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return List<FlightInformation>
     **************************************************************************************/
    public List<FlightInformation> viewFlightInformation(ViewFlights viewFlights) {
        List<FlightInformation> flightList = new ArrayList<FlightInformation>();
        java.sql.Date deptDate = null;
	  /*	SELECT flight_number,airline,departure_city,arrival_city departure_date,arrival_date,departure_time,arrival_time,first_class_seat_fare,business_class_seat_fare 
		 FROM flight_information_master WHERE flight_number='jr910' and departure_city='bangalore' and arrival_city='hyd'; */

        String sql = "SELECT flight_number,airline," +
                "departure_city,arrival_city," +
                "departure_date,arrival_date," +
                "departure_time,arrival_time," +
                "first_class_seat_fare,business_class_seat_fare " +
                "FROM flight_information_master " +
                "WHERE departure_date=? and departure_city=? and arrival_city=? ";
        myLogger.info(sql);
        deptDate = new java.sql.Date(viewFlights.getDate().getTime());
        Object[] params = new Object[]{deptDate, viewFlights.getSource(), viewFlights.getDestination()};
        flightList = (List<FlightInformation>) simpleJdbcTemplate.query(sql, new FlightInfoRowMapper(), params);
        myLogger.info("Retrieved flight information");
        return flightList;
    }

    /************************************************************************************
     * File:        	AirlineDaoImplementation .java
     * Package:       	com.igate.airline.dao
     * Description:   	This method Retrieves Booking Details from Booking_Information_Master table based on flightNumber given as input and
     *               	list to the service
     * Version:       	v1.0
     * Author:jc815790				Date:18-12-2013
     * Modifications:
     * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
     * Method Name:viewBookingDetails
     * @param: flightNumber
     * @return:List<BookingInformation>
     ************************************************************************************/


    @SuppressWarnings("unchecked")
    public List<BookingInformation> viewBookingDetails(String flightNumber) {
        String sql = "SELECT booking_id,no_of_passengers,seat_numbers,flight_number,source_city,destination_city FROM Booking_Information_Master WHERE flight_number=? AND is_active=1";
        myLogger.info(sql);
        Object[] params = new Object[]{flightNumber};
        List<BookingInformation> BookingsList = (List<BookingInformation>) simpleJdbcTemplate.query(sql, new AdminBookingsRowMapper(), params);
        myLogger.info("Retreived booking datails");
        return BookingsList;
    }

    /************************************************************************************
     * File:        	AirlineDaoImplementation .java
     * Package:       	com.igate.airline.dao
     * Description:   	This method Retrieves Passengers list from Booking_Information_Master table based on flightNumber given as input and
     *               	list to the service
     * Version:       	v1.0
     * Author:vs815919			Date:19-12-2013
     * Modifications:
     * Author:<Resource Name>		Date:<dd/mm/yy>     	Change Description:
     * Method Name: viewPassengerListByFlightNo
     * @param: flightNumber
     * @return:List<BookingInformation>
     ************************************************************************************/

    @SuppressWarnings("unchecked")
    @Override
    public List<BookingInformation> viewPassengerListByFlightNo(String flightNumber) {
        String sql = "SELECT booking_id,flight_number,customer_email,credit_card_info,no_of_passengers,class_type,total_fare,source_city,destination_city FROM Booking_Information_Master WHERE flight_number=? AND is_active=1";
        myLogger.info(sql);
        Object[] params = new Object[]{flightNumber};
        List<BookingInformation> PassengersList = (List<BookingInformation>) simpleJdbcTemplate.query(sql, new AdminPassengerListRowMapper(), params);
        myLogger.info("Retreived passengar list");
        return PassengersList;

    }


    /***************************************************************************************
     * File 		: AirlineDaoImplementation.java
     * Package 		: com.igate.airline.dao
     * Description	: This method is called from AirlineServiceImplementation this method takes
     * 				  the  booking information details and adds the details to booking information
     * 				  table and returns the bookingId.
     * Version 		: v1.0
     * Restrictions	:
     * Modifications:
     * Author 		: rc815928
     * Date 		: 18-12-2013
     * Author_Initials:18-12-2013 Initial Version
     * Modification Date:
     * Changes Made :
     *
     * Method Name: addBookingInformation
     * Input 	  : BookingInformation
     * return type: String(BookingId)
     ****************************************************************************************/

    @Override
    public String addBookingInformation(BookingInformation bookingInformationObject) {
        String seat_sequence = null;//seat number
        String seatGenerate = null;//used to store the seat sequence number in query
        String returnBookingId = null;
        String str = null;
        String sequence = null;
        int numberOfPassengers = bookingInformationObject.getNumberOfPassengers();//Number of passengers


        sequence = "select booking_sequence.nextval from dual";
        myLogger.info(sequence);
        String bookingId = (String) simpleJdbcTemplate.queryForObject(sequence, String.class);

        for (int count = 1; count <= numberOfPassengers; count++) {
            seatGenerate = "select seat_sequence.nextval from dual";
            myLogger.info(sequence);

            seat_sequence = (String) simpleJdbcTemplate.queryForObject(seatGenerate, String.class);
            str = str + seat_sequence + ",";
        }
        String seatNumbers = str.substring(str.length() - (str.length() - 4), (str.length() - 1));

        String sql = "INSERT INTO BOOKING_INFORMATION_MASTER VALUES(?,?,?,?,?,?,?,?,?,?,1)";
        myLogger.info(sql);
        Object[] params = new Object[]{
                bookingId,
                bookingInformationObject.getCustomerEmail(),
                numberOfPassengers,
                bookingInformationObject.getClassType(),
                bookingInformationObject.getTotalFare(),
                seatNumbers,
                bookingInformationObject.getCreditCardInformation(),
                bookingInformationObject.getArrivalCity(),
                bookingInformationObject.getDepartureCity(),
                bookingInformationObject.getFlightNumber(),

        };
        int rowsUpdated = simpleJdbcTemplate.update(sql, params);

        if (rowsUpdated != 0) {
            returnBookingId = bookingId;
        }


        return returnBookingId;
    }

    /**************************************************************************
     * File 		: AirlineDaoImplementation.java
     * Package 		: com.igate.airline.dao
     * Description	: This method is called from AirlineServiceImplementation this method takes
     * 				  the  flight information details and adds the details to flight information
     * 				  table and returns the boolean.
     * Version 		: v1.0
     * Restrictions	:
     * Modifications:
     * Author 		: rc815928
     * Date 		: 18-12-2013
     * Author_Initials:18-12-2013 Initial Version
     * Modification Date:
     * Changes Made :
     *
     * Method Name: addFlightInformation
     * Input 	  :FlightInformation
     * return type: boolean
     * @throws AirlineException
     *
     *
     *
     *****************************************************************************/
    @Override
    public boolean addFlightInformation(FlightInformation flightInformation) throws AirlineException {

        boolean status = false;
        Date deptDate;
        Date arrDate;
        int depDay;
        int depMonth;
        int depYear;
        int arrDay;
        int arrMonth;
        int arrYear;
        deptDate = flightInformation.getDepartureDate();
        Calendar calender = Calendar.getInstance();
        calender.setTime(deptDate);
        depDay = calender.get(Calendar.DATE);
        depMonth = calender.get(Calendar.MONTH);
        depYear = calender.get(Calendar.YEAR);

        String depDate = new String(depDay + "/" + depMonth + "/" + depYear);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calender.setTime(sdf.parse(depDate));
        } catch (ParseException e1) {
            throw new AirlineException(e1.getMessage());
        }

        java.util.Date d = calender.getTime();


        java.sql.Date sqlDepDate = new java.sql.Date(d.getTime());

        arrDate = flightInformation.getArrivalDate();
        Calendar calender1 = Calendar.getInstance();
        calender1.setTime(arrDate);
        arrDay = calender1.get(Calendar.DATE);
        arrMonth = calender1.get(Calendar.MONTH);
        arrYear = calender1.get(Calendar.YEAR);
        String arrivalDate = new String(arrDay + "/" + arrMonth + "/" + arrYear);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calender1.setTime(sdf1.parse(arrivalDate));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        java.util.Date date1 = calender1.getTime();
        java.sql.Date sqlArrDate = new java.sql.Date(date1.getTime());
        String sql = """
                INSERT INTO FLIGHT_INFORMATION_MASTER (flight_number, airline,
                arrival_city, arrival_date, arrival_time,
                departure_city, departure_date, departure_time,
                first_class_seat, first_class_seat_fare,
                business_class_seat, business_class_seat_fare,
                max_seats, is_active) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";
        myLogger.info(sql);
        Object[] params = new Object[]{
                flightInformation.getFlightNumber(), flightInformation.getAirline(),
                flightInformation.getArrivalCity(), sqlArrDate, flightInformation.getArrivalTime(),
                flightInformation.getDepartureCity(), sqlDepDate, flightInformation.getDepartureTime(),
                flightInformation.getFirstSeatNumber(), flightInformation.getFirstSeatFare(),
                flightInformation.getBusinessSeatNumber(), flightInformation.getBusinessSeatFare(),
                100, 1
        };
        int update = simpleJdbcTemplate.update(sql, params);
        if (update == 0) {
            status = false;
        } else {
            status = true;
        }
        myLogger.info("Added flight information");
        return status;

    }

    @SuppressWarnings("unchecked")

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method will retrieve flight numbers from data base table
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return List<FlightInformation>
     **************************************************************************************/


    public List<String> getFlightNumbers() {
        String sql = "SELECT FLIGHT_NUMBER FROM flight_information_master ORDER BY FLIGHT_NUMBER";
        myLogger.info(sql);
        List flightNumberList = simpleJdbcTemplate.query(sql, new FlightNumberRowMapper());
        return flightNumberList;

    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method will retrieve flight information of a particular flight
     *                from data base table
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return List<FlightInformation>
     **************************************************************************************/

    @SuppressWarnings("unchecked")
    public FlightInformation getFlightInformationById(String flightNumber) {
        String sql = "SELECT  flight_number,departure_city,arrival_city," +
                "departure_date,arrival_date,departure_time,arrival_time,first_class_seat," +
                "first_class_seat_fare,business_class_seat,business_class_seat_fare from flight_information_master where flight_number=?";

        myLogger.info(sql);

        RowMapper mapper = new RowMapper() {
            public FlightInformation mapRow(ResultSet rs, int rowNum) throws SQLException {

                java.sql.Date depSqlDate = null;
                FlightInformation flightInformation = new FlightInformation();
                flightInformation.setFlightNumber(rs.getString(1));

                flightInformation.setDepartureCity(rs.getString(2));
                flightInformation.setArrivalCity(rs.getString(3));
                depSqlDate = rs.getDate(4);
                java.util.Date depDate = new java.util.Date(depSqlDate
                        .getTime());

                flightInformation.setDepartureDate(depDate);

                java.util.Date arrDate = new java.util.Date(depSqlDate
                        .getTime());

                flightInformation.setArrivalDate(arrDate);
                flightInformation.setDepartureTime(rs.getString(6));
                flightInformation.setArrivalTime(rs.getString(7));
                flightInformation.setFirstSeatNumber(rs.getInt(8));
                flightInformation.setFirstSeatFare(rs.getDouble(9));
                flightInformation.setBusinessSeatNumber(rs.getInt(10));
                flightInformation.setBusinessSeatFare(rs.getDouble(11));
                return flightInformation;
            }

        };

        FlightInformation flightInfo = (FlightInformation) simpleJdbcTemplate.queryForObject(sql, mapper, flightNumber);

        return flightInfo;
    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method will retrieve locations from data base table
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return List<FlightInformation>
     **************************************************************************************/


    @SuppressWarnings("unchecked")
    @Override
    public List<String> getLocations() {

        String sql = "SELECT location_name FROM location_master ORDER BY location_name";
        myLogger.info(sql);
        List locationsList = simpleJdbcTemplate.query(sql, new LocationsRowMapper());
        return locationsList;
    }

    @Override
    public boolean getStatusDelete(String flightNumber) {

        boolean statusdel = false;

        String sql = "SELECT count(booking_id) FROM BOOKING_INFORMATION_MASTER WHERE flight_number=?";
        myLogger.info(sql);
        Object[] params = new Object[]{flightNumber};
        int checkDelStatus = simpleJdbcTemplate.update(sql, params);

        if (checkDelStatus > 0) {
            statusdel = false;
        } else {
            statusdel = true;
        }
        return statusdel;
    }

    @Override
    public boolean deleteFlightInformation(String flightNumber) {

        boolean status = false;

        String sql = "UPDATE Flight_INFORMATION_MASTER SET IS_ACTIVE=0 WHERE  flight_number=?";
        myLogger.info(sql);
        Object[] params = new Object[]{flightNumber};
        int delete = simpleJdbcTemplate.update(sql, params);
        if (delete == 0) {
            status = false;
        } else {
            status = true;
        }


        return status;


    }

    /****************************************************************************************
     * File : AirlineDaoImplementation .java
     * Package : com.igate.airline.dao
     * Description : This method will update the changes to the data base table
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param
     * @return List<FlightInformation>
     * @throws AirlineException
     **************************************************************************************/


    public boolean updateFlightInformation(FlightInformation flightInformation) throws AirlineException {
        boolean status = false;
        Date deptDate;
        Date arrDate;
        int depDay;
        int depMonth;
        int depYear;
        int arrDay;
        int arrMonth;
        int arrYear;
        String sql = "UPDATE flight_information_master set departure_city=?,arrival_city=?," +
                "departure_date=?,arrival_date=?,departure_time=?,arrival_time=?,first_class_seat=?," +
                "first_class_seat_fare=?,business_class_seat=?,business_class_seat_fare=? where flight_number=?";
        myLogger.info(sql);
        deptDate = flightInformation.getDepartureDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(deptDate);
        depDay = calendar.get(Calendar.DATE);
        depMonth = calendar.get(Calendar.MONTH);
        depYear = calendar.get(Calendar.YEAR);

        String depDate = new String(depDay + "/" + depMonth + "/" + depYear);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar.setTime(sdf.parse(depDate));
        } catch (ParseException e1) {

            throw new AirlineException(e1.getMessage());
        }


        java.util.Date d = calendar.getTime();


        java.sql.Date sqlDepDate = new java.sql.Date(d.getTime());
        arrDate = flightInformation.getArrivalDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(arrDate);
        arrDay = calendar1.get(Calendar.DATE);
        arrMonth = calendar1.get(Calendar.MONTH);
        arrYear = calendar1.get(Calendar.YEAR);
        String arrivalDate = new String(arrDay + "/" + arrMonth + "/" + arrYear);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar1.setTime(sdf1.parse(arrivalDate));
        } catch (ParseException e) {

            throw new AirlineException(e.getMessage());
        }


        java.util.Date d1 = calendar1.getTime();

        java.sql.Date sqlArrDate = new java.sql.Date(d1.getTime());
        Object[] params = new Object[]{flightInformation.getDepartureCity(), flightInformation.getArrivalCity(),
                sqlDepDate, sqlArrDate, flightInformation.getDepartureTime(), flightInformation.getArrivalTime(),
                flightInformation.getFirstSeatNumber(), flightInformation.getFirstSeatFare(),
                flightInformation.getBusinessSeatNumber(), flightInformation.getBusinessSeatFare(), flightInformation.getFlightNumber()};
        int update = simpleJdbcTemplate.update(sql, params);
        if (update == 0) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }


}
