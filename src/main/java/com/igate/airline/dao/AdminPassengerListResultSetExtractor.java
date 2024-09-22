package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.igate.airline.bean.BookingInformation;

public class AdminPassengerListResultSetExtractor {
	public Object extractData(ResultSet rs) throws SQLException {
		BookingInformation bookingInformation=new BookingInformation();
		bookingInformation.setBookingId(rs.getString(1));
		bookingInformation.setFlightNumber(rs.getString(2));
		bookingInformation.setCustomerEmail(rs.getString(3));
		bookingInformation.setCreditCardInformation(rs.getString(4));
		bookingInformation.setNumberOfPassengers(rs.getInt(5));
		bookingInformation.setClassType(rs.getString(6));
		bookingInformation.setTotalFare(rs.getDouble(7));
		bookingInformation.setDepartureCity(rs.getString(8));
		bookingInformation.setArrivalCity(rs.getString(9));
		

		return  bookingInformation;
	}

}
