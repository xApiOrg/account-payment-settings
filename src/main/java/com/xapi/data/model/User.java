package com.xapi.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 						private Long id;
	@Column(name="NAME",nullable=false) 		private final String name;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	@JsonBackReference(value="accounts")		private Set<Account> accounts;	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_payee", 
    	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
    	inverseJoinColumns = @JoinColumn(name = "payee_id", referencedColumnName = "id"))
	@JsonBackReference(value="payees")			private Set<Payee> payees;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference(value="payments")		private Set<Payment> payments; // @JsonBackReference // commented in order to appear in the payments, i.e. /payment/{user_id}
	
	public User(){ this.name = "SYSTEM";}
	
	public User(String name){
		this.name = name;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Payee> getPayees() {
		return payees;
	}

	public void setPayees(Set<Payee> payees) {
		this.payees = payees;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
