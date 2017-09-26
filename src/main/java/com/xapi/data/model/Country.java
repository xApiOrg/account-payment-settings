package com.xapi.data.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Country implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String code;
	private String flag;
	private String currency;
	private List<Section> sections; // = new LinkedList<>();
	
//	public Country(){}
//	
//	public Country(String name, String code, String flag, String currency){
//		this.name = name; this.code = code; this.flag = flag; this.currency = currency;
//	}
	
	public boolean setSections(List<Section> sections){
		if( this.sections == null )
			sections = new LinkedList<>();
		
		return sections.addAll(sections);
	}
	
	public List<Section> getSections(){
		return sections;
	}
	
	public boolean addSection(Section section){
		return sections.add(section);
	}
	
	public boolean removeSection(Section section){
		return sections.remove(section);
	}
	
//	private Map<String, Map<String, Object>> sections = new HashMap<>();
	
//	public Map<String, Object> addSection(String sectionName, Map<String, Object> properties){
//		return sections.put(sectionName, properties);
//	}
	
//	public Object addSectionProperty(String sectionName, String propertyName, Object propertyValue){
//		Map<String, Object> section = sections.get(sectionName);
//		if(section == null){
//			section = new HashMap<String, Object>();
//			addSection(sectionName, section);
//		}
//		return section.put(propertyName, propertyValue);
//	}
	
//	public Map<String, Map<String, Object>> getSections() {
//		return sections;
//	}

//	public void setSections(Map<String, Map<String, Object>> sections) {
//		this.sections = sections;
//	}
	
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
}
