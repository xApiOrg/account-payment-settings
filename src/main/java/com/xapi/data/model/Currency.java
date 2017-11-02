package com.xapi.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currency")
public class Currency  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ISO", nullable=false, unique=true) private String iso;
	@Column(name="NAME", nullable=false, unique=true) private String name;
	@Column(name="SYMBOL", nullable=false, unique=false) private String symbol;
	
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
