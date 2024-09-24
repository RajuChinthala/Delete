package com.igate.airline.dao;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.springframework.stereotype.Component;

import java.net.URL;


/*******************************************************************************************************************
 * File Name		:	LogInfo.java
 * Description		:	This is the controller to navigate between the Dao and performs the function of Logging database information.
 * Author			:	Team G5
 * Version			:	1.0
 ******************************************************************************************************************/

@Component("logging")
public class LoggingInterceptor {


    //@Before("configure()")
    public void configureLogging() throws Throwable {

        Logger myLog = Logger.getLogger(LoggingInterceptor.class);
        URL url = Loader.getResource("ARS_logger.properties");
        PropertyConfigurator.configure(url.getFile());

        try {
            myLog.info("Hello : It is " + new java.util.Date().toString());
        } catch (Exception e) {
            myLog.info("Exception occured");
        }
    }
}
