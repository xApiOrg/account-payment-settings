package com.xapi.settings.service;

import java.util.Collection;

import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xapi.data.model.Country;

public interface SettingsService {
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	public static final JSONParser JSON_PARSER = new JSONParser();
	public static final RestTemplate REST_TEMPLATE = new RestTemplate(); // new RestTemplateBuilder().build();
	
	public String allCountrySettings();
	public Collection<?> getAll();
	public Collection<?> getList();
	public Collection<?> get();
	public Country get(String countryId);

}
