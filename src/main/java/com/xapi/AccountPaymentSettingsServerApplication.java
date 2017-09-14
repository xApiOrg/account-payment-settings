package com.xapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// @RefreshScope
// @EnableDiscoveryClient
// @EnableCircuitBreaker
// @EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.xapi.account","com.xapi.payment","com.xapi.settings"})
public class AccountPaymentSettingsServerApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccountPaymentSettingsServerApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(AccountPaymentSettingsServerApplication.class, args);
	}
}
