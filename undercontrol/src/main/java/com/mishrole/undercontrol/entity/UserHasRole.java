package com.mishrole.undercontrol.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_has_role")
public class UserHasRole implements Serializable {
	
	private static final long serialVersionUID = 8413732975695929992L;

	@EmbeddedId
	private UserHasRolePK userHasRolePK;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;
	
}
