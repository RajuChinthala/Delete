package com.igate.airline.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBookingsRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int line) throws SQLException {

        AdminBookingsResultSetExtractor extractor = new AdminBookingsResultSetExtractor();
        Object obj = extractor.extractData(rs);
        return obj;
    }
}
