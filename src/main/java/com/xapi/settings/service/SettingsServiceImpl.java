package com.xapi.settings.service;

import java.io.File;
import java.io.FileNotFoundException;

//import org.apache.commons

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xapi.data.model.Country;
import com.xapi.data.model.Settings;

@Service("settingsService")
public class SettingsServiceImpl implements SettingsService {
	private static final Logger logger = LoggerFactory.getLogger(SettingsServiceImpl.class);
	
	private static final String SETTINGS_FILE_PATH = "./src/main/resources/settings.json";
	private static final File SETTINGS_FILE = new File(SETTINGS_FILE_PATH);
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private static final JSONParser JSON_PARSER = new JSONParser();
	
	private static Long SETTINGS_LAST_MODIFIED = new Long( 0l );
	private static String ALL_COUNTRY_SETTINGS = new String();
	
//	private static final RestTemplate REST_TEMPLATE = new RestTemplate();	

	private static final List<Country> COUNTRIES = new ArrayList<>();//	Country[] COUNTRIES = new Country[ 1 ];
	
	private void reloadSettings(){
		if(SETTINGS_LAST_MODIFIED < SETTINGS_FILE.lastModified() || 
				SETTINGS_LAST_MODIFIED == 0 || ALL_COUNTRY_SETTINGS.isEmpty() ){
				logger.info("Last SETTINGS_LAST_MODIFIED = " + SETTINGS_LAST_MODIFIED.toString());
			SETTINGS_LAST_MODIFIED = SETTINGS_FILE.lastModified();
				logger.info("Current SETTINGS_LAST_MODIFIED = " + SETTINGS_LAST_MODIFIED.toString());
			try ( FileReader fileReader = new FileReader( SETTINGS_FILE ) ) {
				Object object = JSON_PARSER.parse( fileReader );
				ALL_COUNTRY_SETTINGS = object.toString();
					logger.info("ALL_COUNTRY_SETTINGS = " + ALL_COUNTRY_SETTINGS);
	            List<Country> countries = JSON_MAPPER.readValue(object.toString(), 
						JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, Country.class));
//	            		Arrays.asList(SettingsService.JSON_MAPPER.readValue(file, Country[].class)); // DOESN'T WORK IN HERE, but it does below
//	            		JSON_MAPPER.readValue(file, new TypeReference<List<Country>>(){}); // DOESN'T WORK IN HERE, but it does below
	            COUNTRIES.clear(); COUNTRIES.addAll(countries);
	            logger.info("COUNTRIES = " + COUNTRIES);
			}catch ( IOException | ParseException e) { e.printStackTrace(); }
		}
	}

	@Override
	public String allCountrySettings() {
		reloadSettings();
		
        logger.info(ALL_COUNTRY_SETTINGS);
        
		return ALL_COUNTRY_SETTINGS;
	}

	@Override
	public Country get(String countryId) {
		reloadSettings();
		
		for(Country country: COUNTRIES)
			if(country.getCode().equals(countryId))
				return country;
		
		return null;
	}
	
//	TO BE AGREED, MIGHT BE OBSOLETE: BEGIN
	private static final String APPLICATION_SETTINGS = "APPLICATION";
	
	@Override
	public Collection<?> getAll() {
		
		return null;
	}

	@Override
	public Collection<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> get() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	TO BE AGREED, MIGHT BE OBSOLETE: END
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		SettingsService settingsService = new SettingsServiceImpl();
		System.out.println(settingsService.allCountrySettings());
		
		Country spain = settingsService.get( "ES" );
		System.out.println( spain == null? null: spain.toString() );
		
		Settings[] settings = new Settings[1];
		
        try ( FileReader file = new FileReader("./src/main/resources/settings.json") ) {
        	settings =  JSON_MAPPER.readValue(file, Settings[].class);
        }catch ( IOException e) { // 
            e.printStackTrace(); // read the settings from db
        }
        
        FileReader file = new FileReader("./src/main/resources/settings.json");
//    	countries = JSON_MAPPER.readValue(file, Country[].class);
//      countries = Arrays.asList(SettingsService.JSON_MAPPER.readValue(file, Country[].class));
    	List<Country> countries = JSON_MAPPER.readValue(file, new TypeReference<List<Country>>(){});
    		COUNTRIES.clear(); COUNTRIES.addAll(countries);
    	
    	file = new FileReader("./src/main/resources/settings.json");
    	Country[] countriesArray = JSON_MAPPER.readValue(file, Country[].class);
    	
    	file = new FileReader("./src/main/resources/settings.json");
    	List<Settings> settingsList = Arrays.asList(JSON_MAPPER.readValue(file, Settings[].class));
//      countries =  SettingsService.JSON_MAPPER.readValue(object.toString(), 
//						SettingsService.JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, Country.class));
        
        System.out.println( countriesArray.toString() );
        System.out.println( settingsList.toString() );
        System.out.println( settings.toString() );
	}

}
