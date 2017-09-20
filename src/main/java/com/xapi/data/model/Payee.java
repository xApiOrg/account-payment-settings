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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="payee")
public class Payee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 						private Long id;
	@Column(name="NAME",nullable=false) 		private final String name;
	@Column(name="ACTIVE",nullable=false) 		private Boolean active;
	@ManyToMany(mappedBy = "payees") @JsonBackReference
												private Set<User> users;
	
	@OneToMany(mappedBy = "payee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
												private Set<Payment> payments;
	
	// https://giannigar.wordpress.com/2009/09/04/mapping-a-many-to-many-join-table-with-extra-column-using-jpa/
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//												private Set<Account> accounts;
	
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
		return "id = " + id + ", name = " + name + ", active = " + active + ", users = " + 
				users.toString() + ", payments = " + payments.toString();
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
//	public Set<Account> getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(Set<Account> accounts) {
//		this.accounts = accounts;
//	}
}
