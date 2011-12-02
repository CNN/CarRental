package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

public class DataField {

	private String name = null;
	private String description = null;
	
	public DataField(String name, String description) {
		this.name = name;
		this.description = description;
  }
	
	public String getDescription() {
  	return description;
  }
	
	public void setDescription(String description) {
  	this.description = description;
  }
	
	public String getName() {
  	return name;
  }
	
	public void setName(String name) {
  	this.name = name;
  }
}
