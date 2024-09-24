package com.igate.airline.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Entity
@Table(name = "flight_information_master")
public class FlightInformation {
    @Id
    @NotEmpty(message = "Enter flight number")
    @Pattern(message = "Enter only alphanumeric values", regexp = "^[a-z A-Z 0-9]{1,5}$")
    @Column(name = "flight_number")
    String flightNumber;
    @NotEmpty(message = "Enter AirLine name")
    @Pattern(message = "Enter only alphabets", regexp = "^[a-z A-Z]+$")
    @Column(name = "airline")
    String airline;
    @NotEmpty(message = "Select departure city")
    @Column(name = "departure_city")
    String departureCity;
    @NotEmpty(message = "Select arrival city")
    @Column(name = "arrival_city")
    String arrivalCity;
    @Column(name = "departure_date")
    Date departureDate;
    @Column(name = "arrival_date")
    Date arrivalDate;
    UserDate uDepartureDate;
    UserDate uArrivalDate;
    @NotEmpty(message = "Enter departure time")
    @Pattern(message = "Enter time in correct format(hh:mm)", regexp = "^(([01][0-9])|2[0-3]):[0-5][0-9]$")
    @Column(name = "departure_time")
    String departureTime;
    @NotEmpty(message = "Enter arrival time")
    @Pattern(message = "Enter time in correct format(hh:mm)", regexp = "^(([01][0-9])|2[0-3]):[0-5][0-9]$")
    @Column(name = "arrival_time")
    String arrivalTime;
    @NotNull
    @Min(value = 1)
    @Max(value = 50)

    @Column(name = "first_class_seat")
    int firstSeatNumber;
    @Column(name = "first_class_seat_fare")
    double firstSeatFare;
    @NotNull
    @Min(value = 1)
    @Max(value = 50)

    @Column(name = "business_class_seat")
    int businessSeatNumber;
    @Column(name = "business_class_seat_fare")
    double businessSeatFare;

    @Column(name = "available_first_class_seats")
    int availableFirstSeats;
    @Column(name = "available_business_class_seats")
    int availableBusinessSeats;

    @Column(name = "max_seats")
    int maxSeats;
    @Column(name = "is_active")
    int isActive;

}
