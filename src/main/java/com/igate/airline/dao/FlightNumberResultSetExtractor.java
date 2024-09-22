package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FlightNumberResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		
		List<String> flightList=new ArrayList<String>();
		flightList.add(rs.getString(1));
		return flightList;
	
	}
}
