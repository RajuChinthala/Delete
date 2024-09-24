package com.igate.airline.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
//@Entity
public class UserDate {

    /*@Id
    public int date_id;*/
    public int intDay;
    public int intMonth;
    public int intYear;

    public String toString() //converts date obj to string.
    {
        return +intDay + "/" + intMonth + "/" + intYear;
    }
}
