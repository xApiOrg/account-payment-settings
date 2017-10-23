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

@Service("fxRateService") // N.B. TODO FIXME Make it normal service and populate the RATES in DB
public class FXRateService implements RateService{
	public static final String FX_RATE_URL = "http://api.fixer.io/latest?base={currency}";
	private static final Map<String, Rates> RATES = new ConcurrentHashMap<>();
	private static final RestTemplate REST_TEMPLATE = new RestTemplateBuilder().build();
	
	private static final Logger logger = LoggerFactory.getLogger(FXRateService.class);
	
//	static{
//		long start = System.currentTimeMillis();
//		logger.info("Initialising FX Rates hash map"); 
//		
////		ResponseEntity<Rates> response = REST_TEMPLATE.getForEntity("http://api.fixer.io/latest", Rates.class);
//		Rates eurRates = getRates( REST_TEMPLATE, FX_RATE_URL, Rates.class, "EUR");
//		RATES.put( eurRates.getBase(), eurRates );
//		
//		for(String currency: eurRates.getRates().keySet())
//			if( ! RATES.containsKey(currency)){
//				Rates currencyRates = getRates( REST_TEMPLATE, FX_RATE_URL, Rates.class, currency);
//				if(currencyRates != null && currencyRates.getBase() != null)
//					RATES.put( currencyRates.getBase(), currencyRates );
//				else
//					RATES.put(currency, new Rates() );
//			}		
//
//		logger.info("FX Rates hash map Initialised for " + (System.currentTimeMillis() - start) + "ms");
//	}
	
	public FXRateService(){}
	
//	public void init(){
//		logger.info("FX Rates Initialising");
//	}
	
	private static Rates getRates(RestTemplate rt, String url, Class responseType, String currency){
		ResponseEntity<Rates> response = REST_TEMPLATE.getForEntity( FX_RATE_URL, Rates.class, currency );
		if( response != null && response.getBody() != null )
			return response.getBody();
		
		return new Rates();
	}
	
	public Double getRate(String currency, String currencyTo){
		if(currency == null || currencyTo == null || currency.isEmpty() || currencyTo.isEmpty()){
			System.out.println("Invalid Currency: currency = " + currency + ", currencyTo = " + currencyTo);
			return 1.0;
		}
//			throw new Exception(); // TODO FIXME, Change above block with this and create new CurrencyException
		
		if(currency.equalsIgnoreCase(currencyTo))
			return 1.0;
		
		Rates currencyRates = RATES.get(currency);
		if(currencyRates == null || currencyRates.getRates().isEmpty()){
			currencyRates = getRates( REST_TEMPLATE, FX_RATE_URL, Rates.class, currency);
			if(currencyRates != null && currencyRates.getBase() != null)
				RATES.put( currencyRates.getBase(), currencyRates );
			else
				RATES.put(currency, new Rates() );
		}
			
		if(currencyRates != null && currencyRates.getRates() != null)
			return currencyRates.getRates().get(currencyTo);
		
		return 1.0;		
	}
	
//	public  static void main(String[] args){
//		System.out.println("Start");
//
//		System.out.println("Start");
//	}
}
