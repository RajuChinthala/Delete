package com.igate.airline.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPassengerListRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int line) throws SQLException {

        AdminPassengerListResultSetExtractor extractor = new AdminPassengerListResultSetExtractor();
        Object obj = extractor.extractData(rs);
        return obj;
    }
}
