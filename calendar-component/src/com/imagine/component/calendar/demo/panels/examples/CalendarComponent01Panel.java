package com.imagine.component.calendar.demo.panels.examples;

import com.imagine.component.calendar01.CalendarComponent01;

public class CalendarComponent01Panel extends TemplatePanel {

	private CalendarDemoPanelExamples calendarDemoPanelExamples;
	
	public CalendarComponent01Panel(CalendarDemoPanelExamples calendarDemoPanelExamples) {
		this.calendarDemoPanelExamples = calendarDemoPanelExamples;
		this.component = new CalendarComponent01();
		this.name = calendarDemoPanelExamples.getHelp("/resources/calendarcomponent01/name.txt");
		this.description = calendarDemoPanelExamples.getHelp("/resources/calendarcomponent01/description.txt");
		super.initialize();
	}
	
}
