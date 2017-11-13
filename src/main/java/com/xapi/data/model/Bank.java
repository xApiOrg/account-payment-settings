package com.xapi.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bank")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue 											private Long id;
    @Column(name="BBAN", nullable=false, unique=true)				private String bBAN;
    @Column(name="SWIFT_BIC", nullable=false, unique=true)			private String swiftBic; // https://en.wikipedia.org/wiki/ISO_9362#Examples
//    @Column(name="IBAN_CHECK_DIGITS", nullable=false, unique=false)	private Byte ibanCheckDigits; // MOVE IT TO IBAN
    @Column(name="BBAN_CHECK_DIGITS", nullable=true, unique=false)	private Byte bbanCheckDigits;
    @Column(name="BANK_IDENTIFIER", nullable=false, unique=true)	private String bic;
    @Column(name="SEPA_MEMBER", nullable=false)						private Boolean sepaMember;
    
    @OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="COUNTRY_CODE")
    																private Country country;
    @ManyToOne(fetch = FetchType.EAGER)	// @JsonBackReference
		@JoinColumn(name = "BANK_ID")								private Bank parent;    
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // @JsonBackReference(value="payments")
																	private Set<Bank> branches;

//  private List<Bank> branches;
//  private Set<AccountDetails> accounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getbBAN() {
		return bBAN;
	}

	public void setbBAN(String bBAN) {
		this.bBAN = bBAN;
	}

	public String getSwiftBic() {
		return swiftBic;
	}

	public void setSwiftBic(String swiftBic) {
		this.swiftBic = swiftBic;
	}

	public Byte getBbanCheckDigits() {
		return bbanCheckDigits;
	}

	public void setBbanCheckDigits(Byte bbanCheckDigits) {
		this.bbanCheckDigits = bbanCheckDigits;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public Boolean getSepaMember() {
		return sepaMember;
	}

	public void setSepaMember(Boolean sepaMember) {
		this.sepaMember = sepaMember;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Bank getParent() {
		return parent;
	}

	public void setParent(Bank parent) {
		this.parent = parent;
	}

	public Set<Bank> getBranches() {
		return branches;
	}

	public void setBranches(Set<Bank> branches) {
		this.branches = branches;
	}   

}

/*
Your IBAN Number	BG38 RZBB 9155 4027 1608 04
ISO Country Code	BG (Bulgaria) 					- country
IBAN Check Digits	38								- ibanCheckDigits
BBAN	RZBB 9155 4027 1608 04						- bBAN
Bank Identifier	RZBB								- bic
Branch Identifier	9155							- branches (missing sometimes)
Account Number	27160804
BBAN Check Digit(s)	40								- bbanCheckDigits (missing sometimes)
SEPA Member	Yes										- sepaMember

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

/*

private String branch;
private String bank;
private String address;
private String city;
private String state;
private String zip;
private String phone;
private String fax;
private String www;
private String email;
private String country_iso;
private String account;
*/