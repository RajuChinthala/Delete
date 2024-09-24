package com.igate.airline.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "location_master")
public class Locations {

    @Id
    @Column(name = "location_id")
    String locationId;
    @Column(name = "location_name")
    String locationName;
    @Column(name = "location_city")
    String locationCity;
    @Column(name = "location_state")
    String locationState;
    @Column(name = "location_zipcode")
    String locationZipCode;

}
