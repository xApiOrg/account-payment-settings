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
	@Column(name="IDETIFIER",nullable=false) 		private final String idetifier;
	@Column(name="ISO_COUNTRY_CODE",nullable=false)	private final String isoCountryCode;
	@Column(name="BANK_IDENTIFIER",nullable=false)	private final String bankIdentifier;
	@Column(name="BRANCH_IDENTIFIER",nullable=false)private final String branchIdentifier;
	@Column(name="ACCOUNT_NUMBER",nullable=false)	private final String accountNumber;
	@Column(name="SEPA_MEMBER",nullable=false)		private final Boolean sepaMember;
	
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "account_id")
    												private Account account;
	
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "payee_id")
    												private Payee payee;
	
	public AccountDetails(	String idetifier, String bankIdentifier, // String isoCountryCode, 
							String branchIdentifier, String accountNumber, Boolean sepaMember){
		this.idetifier = idetifier; 
		this.isoCountryCode = idetifier != null && idetifier.length() > 2? idetifier.substring(0, 2): "";
		this.bankIdentifier = bankIdentifier; this.branchIdentifier = branchIdentifier;
		this.accountNumber = accountNumber; this.sepaMember = sepaMember; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdetifier() {
		return idetifier;
	}

	public String getIsoCountryCode() {
		return isoCountryCode;
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

	public Boolean getSepaMember() {
		return sepaMember;
	}	
}
// https://stackoverflow.com/questions/1493229/multiple-yet-mutually-exclusive-foreign-keys-is-this-the-way-to-go
// https://dba.stackexchange.com/questions/42434/database-design-two-1-to-many-relationships-to-the-same-table
// https://blogs.oracle.com/sql/implementing-a-mutually-exclusive-relationship-in-the-database

//Bank of India IFSC and MICR Code, 
//https://en.wikipedia.org/wiki/Indian_Financial_System_Code, 
//https://www.bankbazaar.com/ifsc-code/bank-of-india.html, 
//https://sourceforge.net/p/ifscandmicrcode/wiki/Home/

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