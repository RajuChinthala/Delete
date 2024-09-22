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
 
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;
@Component("login")
public class Login {
	
	@Override
	public String toString() {
		return "Login [password=" + password + ", userName=" + userName + "]";
	}
	
	@NotEmpty(message="UserName is mandatory")
	
	private String userName;
	
	
	@NotEmpty(message="Password is mandatory")
	
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
