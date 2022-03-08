package com.mishrole.undercontrol.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "currencies")
public class Currency implements Serializable {

	private static final long serialVersionUID = -4768358812002874092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@NotEmpty(message = "Name is required")
	@Size(min = 3, max = 15, message = "Name must have 3-15 characters long")
	private String name;
	
	@NotEmpty(message = "ISO is required")
	@Size(min = 3, max = 3, message = "ISO must have 3 characters long")
	private String iso;
	
    /*@OneToMany(mappedBy = "currency", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "account-currency")
    private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public Currency() {} 
}
