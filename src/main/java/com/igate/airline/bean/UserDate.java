package com.igate.airline.bean;

public class UserDate {
	public int intDay;
	public int intMonth;
	public int intYear;
	
	
	public int getIntDay() {
		return intDay;
	}
	public void setIntDay(int intDay) {
		this.intDay = intDay;
	}
	public int getIntMonth() {
		return intMonth;
	}
	public void setIntMonth(int intMonth) {
		this.intMonth = intMonth;
	}
	public int getIntYear() {
		return intYear;
	}
	public void setIntYear(int intYear) {
		this.intYear = intYear;
	}
	
	public String toString() //converts date obj to string.    
	{
		return 	+intDay+"/"+intMonth+"/"+intYear; 
	}
}
