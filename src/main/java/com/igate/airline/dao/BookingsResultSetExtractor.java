package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.igate.airline.bean.BookingInformation;



public class BookingsResultSetExtractor {
	public Object extractData(ResultSet rs) throws SQLException {
		BookingInformation bookingInformation=new BookingInformation();
		bookingInformation.setBookingId(rs.getString(1));
		bookingInformation.setNumberOfPassengers(rs.getInt(2));
		bookingInformation.setSeatNumbers(rs.getString(3));
		bookingInformation.setFlightNumber(rs.getString(4));
		bookingInformation.setDepartureCity(rs.getString(5));
		bookingInformation.setArrivalCity(rs.getString(6));
		return  bookingInformation;
	}

}
