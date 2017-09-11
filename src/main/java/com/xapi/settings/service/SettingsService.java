package com.xapi.settings.service;

import java.util.Collection;

public interface SettingsService {
	public Collection<?> getAll();
	public Collection<?> getList();
	public Collection<?> get();
	public Collection<?> get(String countryId);

}
