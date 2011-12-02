package com.imagine.component.calendar.demo.panels.examples;

import com.imagine.component.calendarwrapper01.CalendarWrapper01;

public class CalendarWrapper01Panel extends TemplatePanel {

	private CalendarDemoPanelExamples calendarDemoPanelExamples;
	
	public CalendarWrapper01Panel(CalendarDemoPanelExamples calendarDemoPanelExamples) {
		this.calendarDemoPanelExamples = calendarDemoPanelExamples;
		this.component = new CalendarWrapper01();
		this.name = calendarDemoPanelExamples.getHelp("/resources/calendarwrapper01/name.txt");
		this.description = calendarDemoPanelExamples.getHelp("/resources/calendarwrapper01/description.txt");
		super.initialize();
	}
	
}
