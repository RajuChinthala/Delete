package com.igate.airline.service;

import java.util.List;

import com.igate.airline.bean.BookingInformation;
import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.Login;
import com.igate.airline.bean.ViewFlights;
import com.igate.airline.exception.AirlineException;

public interface IAirlineService {
	FlightInformation getFlightInformationById(String flightNumber) throws AirlineException;
	boolean updateFlightInformation(FlightInformation flightInformation) throws AirlineException;
	boolean deleteFlightInformation(String flightNumber) throws AirlineException;
	boolean getStatusDelete(String flightNumber) throws AirlineException;
	String addBookingInformation(BookingInformation bookingInformationObject) throws AirlineException;
    boolean addFlightInformation(FlightInformation flightInformation) throws AirlineException;
    public List<FlightInformation> viewFlightInformation(ViewFlights viewFlights)throws AirlineException;
	List<BookingInformation> viewBookingDetails(String flightNumber)throws AirlineException;
	List<BookingInformation> viewPassengerListByFlightNo(String flightNumber)throws AirlineException;
	List<String> getLocations() throws AirlineException;
	List<String> getFlightNumbers() throws AirlineException;
	boolean validateUser(Login login) throws AirlineException;
	boolean validateDepartureTime(String depTime);
	boolean validateArrivalTime(String depTime);
	boolean validateFirstSeat(int firstSeat);
	boolean validateFirstSeatFare(double firstSeatFare);
	boolean validatebussSeat(int bussSeat);
	boolean validatebussSeatFare(double firstSeatFare);
	BookingInformation getCustomerDetailsById(String bookingId) throws AirlineException;
	boolean cancelBooking(String bookingId) throws AirlineException;
	boolean getValidPNR(String bookingId) throws AirlineException;
	boolean updateCustomerDetails(BookingInformation bookingInformation) throws AirlineException;
	
}
