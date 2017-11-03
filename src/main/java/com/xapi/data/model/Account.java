package com.xapi.data.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="account")
public class Account  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 								private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    	@JoinColumn(name = "USER_ID") 
    	@JsonBackReference(value="user")				private final User user;
    
	@Column(name="CREATED", nullable=false)				private final Date created = new Date();
	@Column(name="CURRENCY",nullable=false) 			private String currency;
	@Column(name="BALANCE",nullable=false) 				private Double balance = 0.0;
	@Column(name="OVERDRAFT",nullable=false) 			private Double overDraft = 0.0;
	
	@Column(name="TYPE", nullable = false)
		@Enumerated(EnumType.STRING)					private AccountType type;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JsonBackReference(value="payments")			private Set<Payment> payments;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
		@JoinColumn(name = "ACCOUNT_DETAILS_ID", nullable=false)
														private AccountDetails accountDetails;
														
	public Account(){ this.user = new User();}
	public Account(User user){ this.user = user;}
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() { return user; }
		
	public Long getId() {
		return id;
	}
	
//	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getOverDraft() {
		return overDraft;
	}
	public void setOverDraft(Double overDraft) {
		this.overDraft = overDraft;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	public String toString(){
		StringBuffer toString = new StringBuffer();
		for(Field field: this.getClass().getDeclaredFields()) //payments
			try {
				if(! field.getName().equalsIgnoreCase("payments"))
					toString.append( field.getName() + " = " ).append( field.get( this ) ).append("\n");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
		return toString.toString();
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getCreated() {
		return created;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}
	
	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
}

