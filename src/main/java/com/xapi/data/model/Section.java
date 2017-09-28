package com.xapi.data.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Section  implements Serializable{
	private static final long serialVersionUID = 1L;

	String name;
	List<Map<String, String>> fields = new LinkedList<>(); 
//	LinkedHashMap<String, String>[] fields; // Alternative way, slightly unmanageable
	
	public Section(){}
	
	public Section(String name){ this.name = name; }
	
	public Section(String name, List<Map<String, String>> fields){ // LinkedHashMap<String, String>[]	List<Map<String, String>> 
		this.name = name;
		this.fields = fields;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Map<String, String>> getFields() {	//	LinkedHashMap<String, String>[]	List<Map<String, String>>
		return fields;
	}

	public void setFields(List<Map<String, String>> fields) {	//	LinkedHashMap<String, String>[]	List<Map<String, String>>
		this.fields = fields;
	}
	
	@Override
	public String toString(){
		return name + " = " + fields.toString();
	}
	
	public boolean addFields(Map<String, String> fields){
		return this.fields == null? false: this.fields.add(fields);
	}
	
	public boolean removeFields(Map<String, String> fields){
		return this.fields == null? false: this.fields.remove( fields );
	}
}
