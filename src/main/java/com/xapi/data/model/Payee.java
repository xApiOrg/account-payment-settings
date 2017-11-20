package com.xapi.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="payee", uniqueConstraints={
		@UniqueConstraint(columnNames = { "ACCOUNT_DETAILS_ID" }, name="ACCOUNT_DETAILS_ID_Unique_Index")
})
public class Payee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 								private Long id;
	@Column(name="NAME",nullable=false, unique=true) 	private final String name;
	@Column(name="ACTIVE", columnDefinition="BIT(1) NOT NULL DEFAULT 1", nullable=false, insertable=false) 		
														private Boolean active;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
		@JoinColumn(name = "ACCOUNT_DETAILS_ID", nullable=false, unique=true)
														private AccountDetails accountDetails;	
	@ManyToMany(mappedBy = "payees") 			
		@JsonBackReference(value="users")				private Set<User> users;
	
	@OneToMany(mappedBy = "payee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JsonBackReference(value="payments")			private Set<Payment> payments;
	
	public Payee(){ this.name = "SYSTEM"; this.users = new HashSet<>(); this.payments = new HashSet<>();}
	
	public Payee(String name){
		this.name = name; this.users = new HashSet<>(); this.payments = new HashSet<>();
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
//		String paymentsString = payments.toString();
		return "id = " + id + ", name = " + name + ", active = " + active  + 
				", users = " + users.toString() 
//				+ ", payments = " + payments
				+ ", accountDetails = " + (accountDetails != null? accountDetails.toString(): "" )
				;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
}
