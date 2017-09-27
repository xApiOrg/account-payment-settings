package com.xapi.settings.service;

import java.io.FileNotFoundException;

//import org.apache.commons

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xapi.data.model.Country;
import com.xapi.data.model.Settings;

@Service("settingsService")
public class SettingsServiceImpl implements SettingsService {
	public static final Logger logger = LoggerFactory.getLogger(SettingsServiceImpl.class);

	private static List<Country> countries = new ArrayList<>();//	Country[] countries; // = new Country[ 1 ]; 

	@Override
	public String allCountrySettings() {
		Object object = "";
		
		// put file listener, check the last read
        try ( FileReader file = new FileReader("./src/main/resources/settings.json") ) {
            object = SettingsService.JSON_PARSER.parse( file );
            countries = SettingsService.JSON_MAPPER.readValue(object.toString(), 
							SettingsService.JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, Country.class));
//            		Arrays.asList(SettingsService.JSON_MAPPER.readValue(file, Country[].class)); // DOESN'T WORK IN HERE, but it does below
//            		JSON_MAPPER.readValue(file, new TypeReference<List<Country>>(){}); // DOESN'T WORK IN HERE, but it does below
        }catch ( IOException | ParseException e) { // 
            e.printStackTrace(); // read the settings from db instead to throw exception
        }
        
        logger.info(object.toString());
        
		return object.toString();
	}

	@Override
	public Country get(String countryId) {
		for(Country country: countries)
			if(country.getCode().equals(countryId))
				return country;
		
		return null;
	}
	
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
    	countries = JSON_MAPPER.readValue(file, new TypeReference<List<Country>>(){});
    	
    	file = new FileReader("./src/main/resources/settings.json");
    	Country[] countriesArray = JSON_MAPPER.readValue(file, Country[].class);
    	
    	file = new FileReader("./src/main/resources/settings.json");
    	List<Settings> settingsList = Arrays.asList(SettingsService.JSON_MAPPER.readValue(file, Settings[].class));
//      countries =  SettingsService.JSON_MAPPER.readValue(object.toString(), 
//						SettingsService.JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, Country.class));
        
        System.out.println( countriesArray.toString() );
        System.out.println( settingsList.toString() );
        System.out.println( settings.toString() );
	}

}
