package com.imagine.component.calendar.demo.panels.examples;

import com.imagine.component.calendar02.CalendarComponent02;

public class CalendarComponent02Panel extends TemplatePanel {

	private CalendarDemoPanelExamples calendarDemoPanelExamples;
	
	public CalendarComponent02Panel(CalendarDemoPanelExamples calendarDemoPanelExamples) {
		this.calendarDemoPanelExamples = calendarDemoPanelExamples;
		this.component = new CalendarComponent02();
		this.name = calendarDemoPanelExamples.getHelp("/resources/calendarcomponent02/name.txt");
		this.description = calendarDemoPanelExamples.getHelp("/resources/calendarcomponent02/description.txt");
		super.initialize();
	}
	
}
