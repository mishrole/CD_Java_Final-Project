package com.mishrole.undercontrol.entity.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePwdUser implements Serializable {

	private static final long serialVersionUID = 61113876886212100L;

	@NotEmpty(message = "Current Password is required")
	@Size(min=8, max=128, message="Current Password must be between 8 and 128 characters")
	private String current;
	
	@NotEmpty(message = "Password is required")
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
	
	@NotEmpty(message = "Confirm Password is required")
	@Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	private String confirm;

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}
