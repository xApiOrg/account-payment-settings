package com.xapi.data.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import javax.persistence.Column;
//import javax.persistence.Table;

@Entity
//@Table
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 							private Long id;
//	@Column(name="USER_ID", nullable=false) 		private final Long userId;
    @ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "USER_ID")				private final User user;
//	@Column(name="ACCOUNT_ID", nullable=false) 		private final Long accountId;
    @ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "ACCOUNT_ID")			private final Account account;
//	@Column(name="PAYEE_ID", nullable=false) 		private final Long payeeId;
    @ManyToOne(fetch = FetchType.EAGER)	// @JsonBackReference
		@JoinColumn(name = "PAYEE_ID")				private final Payee payee;
	@Column(name="CREATED", nullable=false)			private final Date created = new Date();
	@Column(name="AMOUNT", nullable=false)			private Double amount = 0.00; 
	@Column(name="PAYMENT_CURRENCY",nullable=false) private String paymentCurrency;
	@Column(name="RATE", nullable=false)			private Double rate = 1.00;
	@Column(name="CHARGE", nullable=false)			private Double charge = 0.00;
	@Column(name="CALCULATED_AMOUNT",nullable=false)private Double calculatedAmount = 0.00;
	@Column(name="PAYEE_CURRENCY", nullable=false)	private String payeeCurrency;
	@Column(name="PAYMENT_DATE", nullable=false)	private Date paymentDate = new Date();
	@Column(name="PLACED", nullable=false)			private Boolean placed = false;
	@Column(name="DATE_PLACED", nullable=false)		private Date datePlaced = new Date();
	@Column(name="CANCELLED", nullable=false)		private Boolean cancelled = false;
	@Column(name="DATE_CANCELLED", nullable=false)	private Date dateCancelled = new Date();
	@Column(name="SETTLED", nullable=false)			private Boolean settled = false;
	@Column(name="DATE_SETTLED", nullable=false)	private Date dateSettled = new Date();
	
	public Payment(){ 
		this.user = new User(); this.account = new Account(); this.payee = new Payee();
	}
	
//	public Payment(User user, Long accountId, Long payeeId){
//		this.user = user; this.account = new Account(); this.payee = new Payee();
//	}
	
	public Payment(User user, Account account, Payee payee){
		this.user = user; this.account = account; this.payee = payee;
	}
	
	public static void main(String[] args){
//		System.out.println("dfgsdzxrgfxcv");
//		Payment payment = new Payment( new User(), 1l, 1l);
//
//		System.out.println("dfgsdzxrgfxcv = " + payment.getCreated());
	}

	public User getUser() {
		return user;
	}

	public Account getAccount() {
		return account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(Double calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

	public Boolean getPlaced() {
		return placed;
	}

	public void setPlaced(Boolean placed) {
		this.placed = placed;
	}

	public Boolean getSettled() {
		return settled;
	}

	public void setSettled(Boolean settled) {
		this.settled = settled;
	}

	public Date getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(Date datePlaced) {
		this.datePlaced = datePlaced;
	}

	public Date getDateSettled() {
		return dateSettled;
	}

	public Payee getPayee() {
		return payee;
	}

	public Date getCreated() {
		return created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getPayeeCurrency() {
		return payeeCurrency;
	}

	public void setPayeeCurrency(String payeeCurrency) {
		this.payeeCurrency = payeeCurrency;
	}

	public Date getDateCancelled() {
		return dateCancelled;
	}

	public void setDateCancelled(Date dateCancelled) {
		this.dateCancelled = dateCancelled;
	}
	
	public String toString(){
		StringBuffer toString = new StringBuffer();
		for(Field field: this.getClass().getDeclaredFields())
			try {
				toString.append( field.getName() + " = " ).append( field.get( this ) ).append("\n");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
//		return "id = " + id + ", userId = " + userId + ", accountId = " + accountId + ", payeeId = " + payeeId + ", created = " + created + ", amount = " + amount + ", paymentCurrency = " + paymentCurrency + ", rate = " + rate;
		return toString.toString();
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}

/*
[
    {
        "id": 1,
        "userId": 1000,
        "accountId": 10,
        "payeeId": 100,
        "created": 1505223404000,
        "amount": 2000,
        "paymentCurrency": "GBP",
        "rate": 1,
        "charge": 0,
        "calculatedAmount": 0,
        "payeeCurrency": "EUR",
        "placed": false,
        "datePlaced": 1505223404000,
        "canceled": false,
        "dateCancelled": 1505223404000,
        "settled": false,
        "dateSettled": 1505223404000
    }
]

    {
        "userId": 1000,
        "accountId": 10,
        "payeeId": 100,        
        "amount": 2000,
        "calculatedAmount": 0,
        "paymentCurrency": "GBP",
        "payeeCurrency": "EUR"
    }
    
<Collection>
    <item>
        <id>1</id>
        <userId>1000</userId>
        <accountId>10</accountId>
        <payeeId>100</payeeId>
        <created>1505223404000</created>
        <amount>2000.0</amount>
        <paymentCurrency>GBP</paymentCurrency>
        <rate>1.0</rate>
        <charge>0.0</charge>
        <calculatedAmount>0.0</calculatedAmount>
        <payeeCurrency>EUR</payeeCurrency>
        <placed>false</placed>
        <datePlaced>1505223404000</datePlaced>
        <cancelled>false</cancelled>
        <dateCancelled>1505223404000</dateCancelled>
        <settled>false</settled>
        <dateSettled>1505223404000</dateSettled>
    </item>
</Collection>


 * */
