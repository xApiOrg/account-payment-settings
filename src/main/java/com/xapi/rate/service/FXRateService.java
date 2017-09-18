package com.xapi.rate.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xapi.data.model.Rates;

@Service("fxRateService")
public class FXRateService {
	public static final Double PERCENTAGE_CHARGE = 1.0;
	public static final Set<String> EUR_CURRENCIES = new HashSet<>(Arrays.asList(
			"BGN", "CHF", "CZK ", "DKK", "GBP", "HRK", "HUF", "NOK", "PLN", "RON", "SEK", "EUR"));
	public static final String FX_RATE_URL = "http://api.fixer.io/latest?base={currency}";
	private static final Map<String, Rates> rates = new ConcurrentHashMap<>();
	private static final RestTemplate restTemplate = new RestTemplateBuilder().build();
	
	private static final Logger logger = LoggerFactory.getLogger(FXRateService.class);
	
	static{
		long start = System.currentTimeMillis();
		logger.info("Initialising FX Rates hash map"); 
		
//		ResponseEntity<Rates> response = restTemplate.getForEntity("http://api.fixer.io/latest", Rates.class);
		Rates eurRates = getRates( restTemplate, FX_RATE_URL, Rates.class, "EUR");
		rates.put( eurRates.getBase(), eurRates );
		
		for(String currency: eurRates.getRates().keySet())
			if( ! rates.containsKey(currency)){
				Rates currencyRates = getRates( restTemplate, FX_RATE_URL, Rates.class, currency);
				if(currencyRates != null && currencyRates.getBase() != null)
					rates.put( currencyRates.getBase(), currencyRates );
				else
					rates.put(currency, new Rates() );
			}		

		logger.info("FX Rates hash map Initialised for " + (System.currentTimeMillis() - start) + "ms");
	}
	
	public static Rates getRates(RestTemplate rt, String url, Class responseType, String currency){
		ResponseEntity<Rates> response = restTemplate.getForEntity( FX_RATE_URL, Rates.class, currency );
		if( response != null && response.getBody() != null )
			return response.getBody();
		
		return new Rates();
	}
	
	public static Double getRate(String currency, String currencyTo){
		Rates currencyRates = rates.get(currency);
		if(currencyRates != null && currencyRates.getRates() != null)
			return currencyRates.getRates().get(currencyTo);
		
		return 1.0;		
	}
	
	public static Double getCharge(String currencyFrom, String currencyTo, Double amount){
		if(! (EUR_CURRENCIES.contains(currencyFrom) && EUR_CURRENCIES.contains(currencyTo)) ){
			return (amount * PERCENTAGE_CHARGE)/100;
		}			
		
		return 0.0;
	}
	
	public  static void main(String[] args){
		System.out.println("Start");

		System.out.println("Start");
	}
}
