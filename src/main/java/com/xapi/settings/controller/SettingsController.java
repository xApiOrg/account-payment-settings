package com.xapi.settings.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xapi.data.model.Country;
import com.xapi.settings.service.SettingsService;

@RestController
@RequestMapping("/settings")
public class SettingsController {
	public static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);
	
	@Autowired private SettingsService settingsService;
/**

		/settings/country				GET		- getAllCountrySettings		All Country Settings		http://localhost:10001/ipay/settings/country
		/settings/country/{country}		GET		- getCountrySettingsById	One Country Settings		http://localhost:10001/ipay/settings/country/10

NB!!! New ONES. Needs to be discussed and agreed
		/settings/list/country			GET		- getCountryList			List of all countries		http://localhost:10001/ipay/settings/country/list
		/settings/						GET		- getElements				All elements and country	http://localhost:10001/ipay/settings

*/	
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();	
	private final RestTemplate restTemplate = new RestTemplate(); // new RestTemplateBuilder().build();
	
	@CrossOrigin
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCountrySettings(){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "\nMetod getAllCountrySettings()" + //" NOT IMPLEMENTED YET" + 
				"\nGet ALL country settings" + " NO PARAMETERS";
		
		LOGGER.info(info);
		
//		Collection<?> allCountrySettings = settingsService.get(); // FIXME, settingsService.get()
		String settings = settingsService.allCountrySettings();
		LOGGER.info(settings == null || settings.isEmpty()? "NO SETTINGS FOUND": settings);
		
		return new ResponseEntity<String>(settings, settings == null || settings.isEmpty()? HttpStatus.NOT_FOUND: HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
	public ResponseEntity<?> getCountrySettingsById(@PathVariable("country") String country){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "\nMetod getCountrySettingsById( String country )" + //" NOT IMPLEMENTED YET" + 
				"\nGet country settings by country Id" + "\n Parameters, country Code = " + country;
		
		LOGGER.info(info);
		
//		Collection<?> countrySettings = settingsService.get( country ); // FIXME, settingsService.get(country_id)
		Country countrySettings = settingsService.get( country );
		LOGGER.info(countrySettings == null? "NO SETTINGS FOUND FOR COUNTRY " + country: countrySettings.toString());
		
		return new ResponseEntity<Country>(countrySettings, countrySettings == null? HttpStatus.NOT_FOUND: HttpStatus.OK);
	}
	
	// FIXME, agree with the rest
	// FIXME, ambiguous with the above, i.e. the call /country/list is treated like /country/{list} and is 
	// looking for a country with code list
	@CrossOrigin
//	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	@RequestMapping(value = "/list/country", method = RequestMethod.GET)
	public ResponseEntity<?> getCountryList(){ 
		String info = "\nMetod getCountryList() NOT IMPLEMENTED YET" + 
				"\nGet ALL country LIST" + "\n NO Parameters";		
			LOGGER.info(info);
		
		Collection<Country> allCountriesList = settingsService.getList();
			LOGGER.info(allCountriesList == null || allCountriesList.isEmpty()? "NO COUNTRIES FOUND": allCountriesList.toString());
		
		return new ResponseEntity<Collection<Country>>(allCountriesList, 
				allCountriesList == null || allCountriesList.isEmpty()? HttpStatus.NOT_FOUND: HttpStatus.OK);
	}
	
	// FIXME, agree with the rest
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)//value = "/country/list", 
	public ResponseEntity<?> getElements(){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod getElements() NOT IMPLEMENTED YET" + 
				"\nGet all ELEMENTS settings" + "\n NO Parameters";
		
		LOGGER.info(info);
		
		Collection<?> allElementsSettings = settingsService.getAll(); // FIXME, settingsService.getAll()
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
}
