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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="account")
public class Account  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 								private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID") @JsonBackReference	private final User user;
	@Column(name="CREATED", nullable=false)				private final Date created = new Date();
	@Column(name="CURRENCY",nullable=false) 			private String currency;
	@Column(name="BALANCE",nullable=false) 				private Double balance = 0.0;
	@Column(name="OVERDRAFT",nullable=false) 			private Double overDraft = 0.0;
	@Column(name="TYPE", nullable = false)
		@Enumerated(EnumType.STRING)					private AccountType type;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
													private Set<Payment> payments;

	public Account(){ this.user = new User();}
	public Account(User user){ this.user = user;}
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() { return user; }
	
//	public Account(){ this.userId = 0l;}
//	public Account(Long userId){ this.userId = userId;}
//	public Long getUserId() { return userId; }
	
	public Long getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id) {
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
		for(Field field: this.getClass().getDeclaredFields())
			try {
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
}
// Bank of India IFSC and MICR Code, 
// https://en.wikipedia.org/wiki/Indian_Financial_System_Code, 
// https://www.bankbazaar.com/ifsc-code/bank-of-india.html, 
// https://sourceforge.net/p/ifscandmicrcode/wiki/Home/

/*
Your IBAN Number	BG38 RZBB 9155 4027 1608 04
ISO Country Code	BG (Bulgaria)
IBAN Check Digits	38
BBAN	RZBB 9155 4027 1608 04
Bank Identifier	RZBB
Branch Identifier	9155
Account Number	27160804
BBAN Check Digit(s)	40
SEPA Member	Yes

Your IBAN Number	BG50 CITI 9250 440C 9TEI AI
ISO Country Code	BG (Bulgaria)
IBAN Check Digits	50
BBAN	CITI 9250 440C 9TEI AI
Bank Identifier	CITI
Branch Identifier	9250
Account Number	0C9TEIAI
BBAN Check Digit(s)	44
SEPA Member	Yes

Your IBAN Number	CH61 0076 7000 S521 7090 4
ISO Country Code	CH (Switzerland)
IBAN Check Digits	61
BBAN	0076 7000 S521 7090 4
Bank Identifier	00767
Account Number	000S52170904
SEPA Member	Yes

Your IBAN Number	GB75 BARC 2004 1568 0883 06
ISO Country Code	GB (United Kingdom)
IBAN Check Digits	75
BBAN	BARC 2004 1568 0883 06
Bank Identifier	BARC
Branch Identifier	200415
Account Number	68088306
SEPA Member	Yes

Your IBAN Number	GB11 LOYD 3094 6610 4015 60
ISO Country Code	GB (United Kingdom)
IBAN Check Digits	11
BBAN	LOYD 3094 6610 4015 60
Bank Identifier	LOYD
Branch Identifier	309466
Account Number	10401560
SEPA Member	Yes
 */
