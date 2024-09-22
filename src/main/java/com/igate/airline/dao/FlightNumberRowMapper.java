package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;





public class FlightNumberRowMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		
	    FlightNumberResultSetExtractor extractor = new  FlightNumberResultSetExtractor();
 		Object obj = extractor.extractData(rs);
		return  obj;
	}

}
