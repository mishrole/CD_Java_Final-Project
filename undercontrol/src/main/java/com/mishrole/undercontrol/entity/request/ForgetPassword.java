package com.mishrole.undercontrol.entity.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgetPassword implements Serializable {

	private static final long serialVersionUID = 539439675319410639L;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
