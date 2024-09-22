package com.igate.airline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminPassengerListRowMapper implements RowMapper{
public Object mapRow(ResultSet rs, int line) throws SQLException {
		
	AdminPassengerListResultSetExtractor extractor = new  AdminPassengerListResultSetExtractor();
 		Object obj = extractor.extractData(rs);
		return  obj;
	}
}
