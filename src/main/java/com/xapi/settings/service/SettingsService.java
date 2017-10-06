package com.xapi.settings.service;

import java.util.Collection;

import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xapi.data.model.Country;

public interface SettingsService {
 // new RestTemplateBuilder().build();
	
	public String allCountrySettings();
	public Country get(String countryId);
	
	// TO BE AGREED, MIGHT BE OBSOLETE
	public Collection<?> getAll();
	public Collection<Country> getList();
	public Collection<?> get();
}
