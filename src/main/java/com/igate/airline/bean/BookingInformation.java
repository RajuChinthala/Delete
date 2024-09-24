package com.igate.airline.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(name = "booking_information_master")
public class BookingInformation {

    @Id
    @Column(name = "booking_id")
    String bookingId;
    @NotEmpty(message = "Enter valid email")
    @Email(message = "Please enter valid email")
    @Column(name = "customer_email")
    String customerEmail;
    @NotNull
    @Max(value = 9)
    @Min(value = 1)
    @Column(name = "no_of_passengers")
    int numberOfPassengers;
    @Column(name = "destination_city")
    String arrivalCity;
    @Column(name = "source_city")
    String departureCity;
    @Column(name = "total_fare")
    double totalFare;
    @Column(name = "seat_numbers")
    String seatNumbers;
    @NotEmpty(message = "Please enter credit card information(0-9)")
    @Pattern(message = "Enter only numeric values", regexp = "^[0-9]{5,10}$")
    @Column(name = "credit_card_info")
    String creditCardInformation;
    @Column(name = "flight_number")
    String flightNumber;
    @NotEmpty(message = "Select class type")
    @Column(name = "class_type")
    String classType;

}
