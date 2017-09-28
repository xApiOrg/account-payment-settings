package com.xapi.data.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyClass;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name="section")
public class SettingsSection  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue 								
	private Long id;
	
	@Column(name="NAME", nullable=false, unique=true)
	String name;
	
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name="FIELDS_NAME", table="fields", unique=true)
	@MapKeyClass(String.class)
	@Column(name="FIELDS_VALUE", table = "fields")
	@CollectionTable(name="fields", joinColumns = @JoinColumn (name="ID"))
	List<Map<String, String>> fields = new LinkedList<>(); 
//	LinkedHashMap<String, String>[] fields; // Alternative way, slightly unmanageable
	
	public SettingsSection(){}
	
	public SettingsSection(String name){ this.name = name; }
	
	public SettingsSection(String name, List<Map<String, String>> fields){ // LinkedHashMap<String, String>[]	List<Map<String, String>> 
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
