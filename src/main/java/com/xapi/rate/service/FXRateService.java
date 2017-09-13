package com.xapi.rate.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xapi.rate.model.Rates;

@Service
public class FXRateService {
	public static final String FX_RATE_URL = "http://api.fixer.io/latest?base={currency}";
	private static final Map<String, Rates> rates = new HashMap<>();
	private static final RestTemplate restTemplate = new RestTemplateBuilder().build();
	
	private static final Logger logger = LoggerFactory.getLogger(FXRateService.class);
	
	static{
		long start = System.currentTimeMillis();
		logger.info("Initialising FX Rates hash map"); 
		
		ResponseEntity<Rates> response = restTemplate.getForEntity("http://api.fixer.io/latest", Rates.class);
		Rates eur = response.getBody();
		rates.put(eur.getBase(), eur);
		
		for(String currency: eur.getRates().keySet())
			if( ! rates.containsKey(currency)){
				response = restTemplate.getForEntity( FX_RATE_URL, Rates.class, currency );
				if(response != null && response.getBody() != null ) // && response.getBody().getRates() != null
					rates.put(response.getBody().getBase(), response.getBody());
				else
					rates.put(currency, new Rates());
			}		

		logger.info("FX Rates hash map Initialised for " + (System.currentTimeMillis() - start) + "ms");
	}
	
	public static Rates getRates(RestTemplate rt, String url, Class responseType, String currency){
		return null;
	}
	
	public Double getRate(String currency){
		return null;		
	}
	
	public  static void main(String[] args){
		System.out.println("Start");

		System.out.println("Start");
	}
}
