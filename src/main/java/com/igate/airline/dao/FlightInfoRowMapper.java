package com.igate.airline.dao;

import com.igate.airline.bean.FlightInformation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightInfoRowMapper implements RowMapper<FlightInformation> {

    /****************************************************************************************
     * File : FlightInfoRowMapper
     * Package : com.igate.airline.dao
     * Description :  This method calls extractor method of  FlightInfoResultSetExtractor
     *               and returns the the object flightInformation
     * Version : v1.0
     * Restrictions:
     * Modifications :
     * Author : vs815919  Date : 18-12-2013
     * Author_Initials 18-12-2013 Initial Version
     * @param ResultSet
     * @return FlightInformation
     **************************************************************************************/
    @Override
    public FlightInformation mapRow(ResultSet rs, int arg1) throws SQLException {
        FlightInfoResultSetExtractor extractor = new FlightInfoResultSetExtractor();
        FlightInformation flightInformation = extractor.extractData(rs);
        return flightInformation;
    }

}
