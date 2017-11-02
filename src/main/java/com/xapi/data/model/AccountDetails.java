package com.xapi.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_details")
public class AccountDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 							private Long id;
	@Column(name="BANK_IDENTIFIER",nullable=false)	private final String bankIdentifier;
	@Column(name="BRANCH_IDENTIFIER",nullable=false)private final String branchIdentifier;
	@Column(name="ACCOUNT_NUMBER",nullable=false)	private final String accountNumber;
	
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "account_id")
    												private Account account;
	
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "payee_id")
    												private Payee payee;
	
	public AccountDetails(	String bankIdentifier, String branchIdentifier, String accountNumber ){ 
		this.bankIdentifier = bankIdentifier; this.branchIdentifier = branchIdentifier;
		this.accountNumber = accountNumber; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankIdentifier() {
		return bankIdentifier;
	}

	public String getBranchIdentifier() {
		return branchIdentifier;
	}

	public String getAccountNumber() {
		return accountNumber;
	}	
}
// https://stackoverflow.com/questions/1493229/multiple-yet-mutually-exclusive-foreign-keys-is-this-the-way-to-go
// https://dba.stackexchange.com/questions/42434/database-design-two-1-to-many-relationships-to-the-same-table
// https://blogs.oracle.com/sql/implementing-a-mutually-exclusive-relationship-in-the-database

//Bank of India IFSC and MICR Code, 
//https://en.wikipedia.org/wiki/Indian_Financial_System_Code, 
//https://www.bankbazaar.com/ifsc-code/bank-of-india.html, 
//https://sourceforge.net/p/ifscandmicrcode/wiki/Home/

