package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.igate.airline.bean.BookingInformation;

public class AdminBookingsRowMapper implements RowMapper{
	
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		
	    AdminBookingsResultSetExtractor extractor = new  AdminBookingsResultSetExtractor();
 		Object obj = extractor.extractData(rs);
		return  obj;
	}
}
