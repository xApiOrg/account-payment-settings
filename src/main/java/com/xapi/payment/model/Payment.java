package com.xapi.payment.model;

import java.io.Serializable;
import java.util.Date;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@Id
	private Long id;
	private final Long userId;
	private final Long accountId;
	private final Long payeeId;
	private final Date created = new Date();
	private Double amount;
	private Double rate;
	private Double charge;
	private Double calculatedAmount;
	private Boolean placed = false;
	private final Date datePlaced = new Date();
	private Boolean canceled = false;
	private Boolean settled = false;
	private final Date dateSettled = new Date();
	
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
	
	

}
