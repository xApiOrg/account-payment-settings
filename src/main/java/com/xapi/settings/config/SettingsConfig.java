package com.xapi.settings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class SettingsConfig {
    @Value("${ALL_COUNTRY_SETTINGS}")
    private String allCountrySettings;

	public String getAllCountrySettings() {
		return allCountrySettings;
	}
}
