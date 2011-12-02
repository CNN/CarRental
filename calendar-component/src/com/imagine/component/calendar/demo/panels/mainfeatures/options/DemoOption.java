package com.imagine.component.calendar.demo.panels.mainfeatures.options;

import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;

public abstract class DemoOption {

	protected CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures;
	private String helpPath;
	String help = null;
	
	public DemoOption(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures, String helpPath) {
		this.calendarDemoPanelMainFeatures = calendarDemoPanelMainFeatures;
		this.helpPath = helpPath;
  }
	
	public abstract void initialize();
	
	public String getHelp() {
		if (help == null) {
			help = calendarDemoPanelMainFeatures.getHelp(helpPath);
		}
		return help;
	}
}
