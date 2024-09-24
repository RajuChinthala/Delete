package com.igate.airline.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationsRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int arg1) throws SQLException {
        LocationsResultSetExtractor extractor = new LocationsResultSetExtractor();
        String location = extractor.extractData(rs);
        return location;
    }

}
