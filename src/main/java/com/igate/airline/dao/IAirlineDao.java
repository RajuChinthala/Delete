package com.igate.airline.dao;

import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Login;
import com.igate.airline.bean.ViewFlights;
import com.igate.airline.exception.AirlineException;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface IAirlineDao {
    List<String> getFlightNumbers();

    FlightInformation getFlightInformationById(String flightNumber);

    boolean updateFlightInformation(FlightInformation flightInformation) throws ParseException, AirlineException;

    boolean deleteFlightInformation(String flightNumber);

    boolean getStatusDelete(String flightNumber);

    String addBookingInformation(BookingInformation bookingInformationObject);

    boolean addFlightInformation(FlightInformation flightInformation) throws AirlineException;

    List<String> getLocations();

    List<FlightInformation> viewFlightInformation(ViewFlights viewFlights);

    List<BookingInformation> viewBookingDetails(String flightNumber);

    List<BookingInformation> viewPassengerListByFlightNo(String flightNumber);

    boolean validateUser(Login login);

    BookingInformation getCustomerDetailsById(String bookingId);

    boolean cancelBooking(String bookingId);

    boolean getValidPNR(String bookingId);

    boolean updateCustomerDetails(BookingInformation bookingInformation);

}
