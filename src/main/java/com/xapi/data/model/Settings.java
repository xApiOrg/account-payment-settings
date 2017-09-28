package com.xapi.data.model;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String code;
	private String flag;
	private String currency;
	private List<SettingsSection> sections;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<SettingsSection> getSections() {
		return sections;
	}

	public void setSections(List<SettingsSection> sections) {
		this.sections = sections;
	}
}
