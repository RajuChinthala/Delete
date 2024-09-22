package com.igate.airline.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Locations {

	@Id
  String locationId;
  String locationCity;
  String locationState;
  String locationZipCode;
public String getLocationId() {
	return locationId;
}
public void setLocationId(String locationId) {
	this.locationId = locationId;
}
public String getLocationCity() {
	return locationCity;
}
public void setLocationCity(String locationCity) {
	this.locationCity = locationCity;
}
public String getLocationState() {
	return locationState;
}
public void setLocationState(String locationState) {
	this.locationState = locationState;
}
public String getLocationZipCode() {
	return locationZipCode;
}
public void setLocationZipCode(String locationZipCode) {
	this.locationZipCode = locationZipCode;
}
}
