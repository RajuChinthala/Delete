package com.igate.airline.bean;

import jakarta.validation.constraints.*;

import java.util.Date;


//@Entity
public class ViewFlights {
	
	@NotEmpty(message="Source is mandatory")
	String source;
	
	@NotEmpty(message="Destination is mandatory")
	String destination;
	
	Date date;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
