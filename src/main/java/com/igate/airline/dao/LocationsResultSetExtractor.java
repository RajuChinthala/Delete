package com.igate.airline.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationsResultSetExtractor implements ResultSetExtractor<String> {

    @Override
    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
        String location = rs.getString(1);
        return location;
    }

}
