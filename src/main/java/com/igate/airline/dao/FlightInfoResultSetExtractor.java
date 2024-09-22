package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.igate.airline.bean.FlightInformation;
import com.igate.airline.bean.UserDate;

public class FlightInfoResultSetExtractor implements ResultSetExtractor<FlightInformation>{

	/****************************************************************************************
	 * File : FlightInfoResultSetExtractor
	 * Package : com.igate.airline.dao
	 * Description :  This method gets data from result set and sets in the flightInormation object            
	 * Version : v1.0
	 * Restrictions:  
	 * Modifications :
	 * Author : vs815919  Date : 18-12-2013 
	 * Author_Initials 18-12-2013 Initial Version 
	 * @param ResultSet
	 * @return FlightInformation
	 **************************************************************************************/
	@SuppressWarnings("static-access")
	@Override
	public FlightInformation extractData(ResultSet resultSet) throws SQLException,DataAccessException {
		
		FlightInformation flightInformation=new FlightInformation();
		
		flightInformation.setFlightNumber(resultSet.getString(1));
		flightInformation.setAirline(resultSet.getString(2));
		flightInformation.setDepartureCity(resultSet.getString(3));
		flightInformation.setArrivalCity(resultSet.getString(4));
		
		//Getting sql date
		java.sql.Date sqlDeptDate=resultSet.getDate(5);
		java.sql.Date sqlArriDate=resultSet.getDate(6);
		
		//Converting sql date into util defined date
		java.util.Date deptDate=new java.util.Date(sqlDeptDate.getTime());
		java.util.Date arrivalDate=new java.util.Date(sqlArriDate.getTime());
		
		//Converting util date into user defined date
		Calendar c=Calendar.getInstance();
		c.setTime(deptDate);
		UserDate uDeptDate=new UserDate();
		uDeptDate.setIntDay(c.get(c.DATE));
		uDeptDate.setIntMonth(c.get(c.MONTH));
		uDeptDate.setIntYear(c.get(c.YEAR));
		flightInformation.setuDepartureDate(uDeptDate);
		
		Calendar c1=Calendar.getInstance();
		c1.setTime(arrivalDate);
		UserDate uArrivalDate=new UserDate();
		uArrivalDate.setIntDay(c1.get(c1.DATE));
		uArrivalDate.setIntMonth(c1.get(c1.MONTH));
		uArrivalDate.setIntYear(c1.get(c1.YEAR));
		flightInformation.setuArrivalDate(uArrivalDate);
		
		flightInformation.setDepartureDate(deptDate);
		flightInformation.setArrivalDate(arrivalDate);
		flightInformation.setDepartureTime(resultSet.getString(7));
		flightInformation.setArrivalTime(resultSet.getString(8));
		flightInformation.setFirstSeatFare(resultSet.getDouble(9));
		flightInformation.setBusinessSeatFare(resultSet.getDouble(10));
		
		return flightInformation;
	}

}
