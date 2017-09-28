package com.xapi.data.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class Country implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	private long id; // NO NEED, as far as it's not Entity and persisted on this stage
	private String name;
	private String code;
	private String flag;
	private String currency;
	private List<Section> sections = new LinkedList<>();
	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
	
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
	
	public List<Section> getSections(){
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}		
	
	public boolean addSection(Section section){
		return sections == null? false: sections.add(section);
	}
	
	public boolean removeSection(Section section){
		return  sections == null? false: sections.remove(section);
	}

	@Override
	public String toString(){
		StringBuffer toString = new StringBuffer();
		for(Field field: this.getClass().getDeclaredFields())
			try {
				toString.append( field.getName() + " = " ).append( field.get( this ) ).append("\n");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
		return toString.toString();
	}	
}
