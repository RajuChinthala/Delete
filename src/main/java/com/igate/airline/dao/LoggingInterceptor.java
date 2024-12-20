package com.igate.airline.dao;


//import org.apache.log4j.Logger;
//import ch.qos.logback.classic.Logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/*******************************************************************************************************************
 * File Name		:	LogInfo.java
 * Description		:	This is the controller to navigate between the Dao and performs the function of Logging database information.
 * Author			:	Team G5
 * Version			:	1.0
 ******************************************************************************************************************/

@Component("logging")
@Slf4j
public class LoggingInterceptor {


    //@Before("configure()")
    public void configureLogging() throws Throwable {

       /* Logger myLog = LoggerFactory.getLogger(LoggingInterceptor.class);
        URL url = Loader.getResource("log4j.properties");
        PropertyConfigurator.configure(url.getFile());*/

        try {
            log.info("Hello : It is {} " , new java.util.Date().toString());
        } catch (Exception e) {
            log.info("Exception occured");
        }
    }
}
