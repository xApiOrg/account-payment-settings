package com.xapi.payment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import javax.persistence.Column;
//import javax.persistence.Table;

@Entity
//@Table
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 				private Long id;
	@Column(name = "USER_ID") 			private final Long userId;
	@Column(name = "ACCOUNT_ID") 		private final Long accountId;
	@Column(name = "PAYEE_ID") 			private final Long payeeId;
										private final Date created = new Date();
										private Double amount = 0.00;
	@Column(name = "PAYMENT_CURRENCY") 	private String paymentCurrency;
										private Double rate = 1.00;
										private Double charge = 0.00;
	@Column(name = "CALCULATED_AMOUNT")	private Double calculatedAmount = 0.00;
	@Column(name = "PAYEE_CCURRENCY")	private String payeeCurrency;
										private Boolean placed = false;
	@Column(name = "DATE_PLACED")		private Date datePlaced = new Date();
										private Boolean canceled = false;
	@Column(name = "DATE_CANCELED")		private Date dateCancelled = new Date();
										private Boolean settled = false;
	@Column(name = "DATE_SETTLED")		private Date dateSettled = new Date();
	
	public Payment(){ 
		this.userId = 0l; this.accountId = 0l; this.payeeId = 0l;
	}
	
	public Payment(Long userId, Long accountId, Long payeeId){
		this.userId = userId; this.accountId = accountId; this.payeeId = payeeId;
	}
	
	public static void main(String[] args){
		System.out.println("dfgsdzxrgfxcv");
		Payment payment = new Payment(1l, 1l, 1l);

		System.out.println("dfgsdzxrgfxcv = " + payment.getCreated());
	}

	public Long getUserId() {
		return userId;
	}

	public Long getAccountId() {
		return accountId;
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

	public Date getDateSettled() {
		return dateSettled;
	}

	public Long getPayeeId() {
		return payeeId;
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

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
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
        <canceled>false</canceled>
        <dateCancelled>1505223404000</dateCancelled>
        <settled>false</settled>
        <dateSettled>1505223404000</dateSettled>
    </item>
</Collection>


 * */
