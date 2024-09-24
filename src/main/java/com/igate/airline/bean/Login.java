package com.igate.airline.bean;

/**********************************
 * File Name:Login.java
 * Description:This is Login Bean and has the initialization of attributes username and password
 *             and its setters and getters along with their validations
 *             for not null,not empty and min and max size validations.
 *
 * Author         : <sk815835>
 * Last Edited By : <sk815835>
 * Version        : <V 1.1>
 * Created on     : <Thu 19, 2013>
 * Modified By    : <sk815835> on <Thu 19,2013>
 *                : 
 *
 *
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name="user_master")
public class Login {

    @Override
    public String toString() {
        return "Login [password=" + password + ", userName=" + userName + "]";
    }

    @Id
    @Column(name = "user_id")
    private int user_id;

    @NotEmpty(message = "UserName is mandatory")
    @Column(name = "user_name")
    private String userName;
    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}
