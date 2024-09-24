package com.igate.airline.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class FlightInformation {

    @Id
    @NotEmpty(message = "Enter flight number")
    @Pattern(message = "Enter only alphanumeric values", regexp = "^[a-z A-Z 0-9]{1,5}$")
    String flightNumber;


    @NotEmpty(message = "Enter AirLine name")
    @Pattern(message = "Enter only alphabets", regexp = "^[a-z A-Z]+$")
    String airline;
    @NotEmpty(message = "Select departure city")
    String departureCity;
    @NotEmpty(message = "Select arrival city")
    String arrivalCity;


    Date departureDate;
    Date arrivalDate;
    UserDate uDepartureDate;
    UserDate uArrivalDate;
    @NotEmpty(message = "Enter departure time")
    @Pattern(message = "Enter time in correct format(hh:mm)", regexp = "^(([01][0-9])|2[0-3]):[0-5][0-9]$")
    String departureTime;
    @NotEmpty(message = "Enter arrival time")
    @Pattern(message = "Enter time in correct format(hh:mm)", regexp = "^(([01][0-9])|2[0-3]):[0-5][0-9]$")
    String arrivalTime;
    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    int firstSeatNumber;
    double firstSeatFare;
    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    int businessSeatNumber;
    double businessSeatFare;

    int availableFirstSeats;
    int availableBusinessSeats;
    int maxSeats;
    int isActive;


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public UserDate getuDepartureDate() {
        return uDepartureDate;
    }

    public void setuDepartureDate(UserDate uDepartureDate) {
        this.uDepartureDate = uDepartureDate;
    }

    public UserDate getuArrivalDate() {
        return uArrivalDate;
    }

    public void setuArrivalDate(UserDate uArrivalDate) {
        this.uArrivalDate = uArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFirstSeatNumber() {
        return firstSeatNumber;
    }

    public void setFirstSeatNumber(int firstSeatNumber) {
        this.firstSeatNumber = firstSeatNumber;
    }

    public double getFirstSeatFare() {
        return firstSeatFare;
    }

    public void setFirstSeatFare(double firstSeatFare) {
        this.firstSeatFare = firstSeatFare;
    }

    public int getBusinessSeatNumber() {
        return businessSeatNumber;
    }

    public void setBusinessSeatNumber(int businessSeatNumber) {
        this.businessSeatNumber = businessSeatNumber;
    }

    public double getBusinessSeatFare() {
        return businessSeatFare;
    }

    public void setBusinessSeatFare(double businessSeatFare) {
        this.businessSeatFare = businessSeatFare;
    }

    public int getAvailableFirstSeats() {
        return availableFirstSeats;
    }

    public void setAvailableFirstSeats(int availableFirstSeats) {
        this.availableFirstSeats = availableFirstSeats;
    }

    public int getAvailableBusinessSeats() {
        return availableBusinessSeats;
    }

    public void setAvailableBusinessSeats(int availableBusinessSeats) {
        this.availableBusinessSeats = availableBusinessSeats;
    }

}
