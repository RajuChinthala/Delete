package com.igate.airline.bean;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;


@Data
public class ViewFlights {

    @NotEmpty(message = "Source is mandatory")
    String source;

    @NotEmpty(message = "Destination is mandatory")
    String destination;

    Date date;

}
