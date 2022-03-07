package com.mishrole.undercontrol.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserHasRolePK implements Serializable {
	
	private static final long serialVersionUID = 7608273396353898017L;
	
	@Column
	private Integer user_id;
	@Column
	private Integer role_id;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = (prime * result + user_id);
		result = (prime * result + role_id);
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		UserHasRolePK other = (UserHasRolePK) obj;
		
		if (user_id != other.user_id)
			return false;
		if (role_id != other.role_id)
			return false;
		
		return true;
	}
	
}
