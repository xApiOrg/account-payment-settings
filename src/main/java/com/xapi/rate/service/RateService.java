package com.xapi.rate.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface RateService {
	public static final Double PERCENTAGE_CHARGE = 1.0;
	public static final Set<String> EUR_CURRENCIES = new HashSet<>(Arrays.asList(
			"BGN", "CHF", "CZK ", "DKK", "GBP", "HRK", "HUF", "NOK", "PLN", "RON", "SEK", "EUR"));
	
	public static Double getCharge(String currencyFrom, String currencyTo, Double amount){
		if(! (EUR_CURRENCIES.contains(currencyFrom) && EUR_CURRENCIES.contains(currencyTo)) ){
			return (amount * PERCENTAGE_CHARGE)/100;
		}			
		
		return 0.0;
	}
	
	public Double getRate(String currency, String currencyTo);
}
