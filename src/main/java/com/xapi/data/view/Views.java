package com.xapi.data.view;

public class Views {
	public interface Basic{}
	
	public interface Public extends Basic{}
	
	public interface Protected extends Public{}
	
	public interface Restricted extends Protected{}
}
