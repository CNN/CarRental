package com.imagine.component.calendar.demo.panels.mainfeatures;

import java.util.*;

import com.imagine.component.calendar.DateListener;


public class CustomDateListener implements DateListener {
	private CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures;	

	public CustomDateListener(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		this.calendarDemoPanelMainFeatures = calendarDemoPanelMainFeatures;
	}
	
  public void dateChanged(Date date) {
  	String message = "";
  	if (date == null) {
  		message= "Date changed to: null";
  		calendarDemoPanelMainFeatures.addEventHelp(message);
  		calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
  		return;
  	}
  	GregorianCalendar calendar = new GregorianCalendar();
  	calendar.setTime(date);
  	
  	message = "Date changed to:" + calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH);
  	calendarDemoPanelMainFeatures.addEventHelp(message);
  	calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
  }
}

