package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import javax.swing.JPanel;

import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.DemoOption;

public abstract class DemoProperties extends JPanel {

	protected CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures;
	
	public DemoProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		this.calendarDemoPanelMainFeatures = calendarDemoPanelMainFeatures;
  }
	
	public abstract void initialize();
	
	public abstract String getTitle();
	
	public abstract DemoOption[] getDemoOptionsArray();
}
