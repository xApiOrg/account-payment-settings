package com.xapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

// @RefreshScope
// @EnableDiscoveryClient
// @EnableCircuitBreaker
@EnableAutoConfiguration
@Configuration
@PropertySources({
@PropertySource(value = { "classpath:/settings.json" }, factory=AccountPaymentSettingsServerApplication.JsonLoader.class ) 	}) // from PropertySource value , "", ""

@SpringBootApplication(scanBasePackages={"com.xapi.account","com.xapi.payment","com.xapi.settings","com.xapi.rate"})
public class AccountPaymentSettingsServerApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccountPaymentSettingsServerApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(AccountPaymentSettingsServerApplication.class, args);
	}
	
	public static class JsonLoader implements PropertySourceFactory {
        @Override
        public org.springframework.core.env.PropertySource<?> createPropertySource(String name,
                EncodedResource resource) throws IOException {
        	Map<String, Object> mapSource = new HashMap<>();
        	try {
				Object object = new JSONParser().parse( new InputStreamReader( resource.getInputStream() ) );
				mapSource.put("ALL_COUNTRY_SETTINGS", object.toString());
			} catch (ParseException e) { e.printStackTrace(); }
//            Map<String, Object> readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
        	
            return new MapPropertySource("json-source", mapSource);
        }
	}
}
