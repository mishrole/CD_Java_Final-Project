package com.mishrole.undercontrol.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "records")
public class Record implements Serializable {

	private static final long serialVersionUID = 6996854299610483535L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	//@JsonBackReference(value = "account-record")
	private Account account;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "record_category_id", nullable = false)
	//@JsonBackReference(value = "category-record")
	private Category category;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "record_type_id", nullable = false)
	//@JsonBackReference(value = "type-record")
	private Type type;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;
	
	@NotNull
	@DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
	@Digits(integer = 8, fraction = 2, message = "Amount has a limit of 10 digits (8 integer, 2 decimal)")
	//@Range(min = 0, message = "Amount must be at least 0")
	private BigDecimal amount;
	
	@NotEmpty(message = "Name is required")
	@Size(min = 3, max = 30, message = "Name must have 3-30 characters long")
	private String name;
	
	@Nullable
	private String concept;
	
	private Boolean deleted;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.deleted = false;
    }
	
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Record() {}

}
