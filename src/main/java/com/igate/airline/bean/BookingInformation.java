package com.igate.airline.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class BookingInformation {

	@Id
	String bookingId;
	@NotEmpty(message="Enter valid email")
	@Email(message="Please enter valid email")
	String customerEmail;
	@NotNull
	@Max(value=9) @Min(value=1)
	int numberOfPassengers;
	String arrivalCity;
	String departureCity;
	double totalFare;
	String seatNumbers; 
	@NotEmpty(message="Please enter credit card information(0-9)")
	@Pattern(message="Enter only numeric values", regexp = "^[0-9]{5,10}$" )
	String creditCardInformation; 
	String flightNumber;
	@NotEmpty(message="Select class type")
	String classType;
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public String getSeatNumbers() {
		return seatNumbers;
	}
	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}
	public String getCreditCardInformation() {
		return creditCardInformation;
	}
	public void setCreditCardInformation(String creditCardInformation) {
		this.creditCardInformation = creditCardInformation;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	} 
	
  
}
