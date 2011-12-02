package com.imagine.component.calendarwrapper01;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.CalendarDayTimeComponent;


/**
 * This is a class that includes a CalendarComponent and a CalendarDayTimeComponent.
 * 
 */
public class CalendarWrapper01 extends JPanel {
	CalendarComponent calendarComponent = new CalendarComponent();
	CalendarDayTimeComponent calendarDayTimeComponent = new CalendarDayTimeComponent();
	
	
	/**
	 * Default constructor.
	 */
	public CalendarWrapper01() {
		
		calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(false));
		
		int count = 0;
		this.setLayout(new GridBagLayout());
		this.add(calendarComponent, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
		count++;
		this.add(calendarDayTimeComponent, new GridBagConstraints(0, count, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		
	}

	/**
	 * Get the calendar component associated.
	 * @return the calendar component associated.
	 */
	public CalendarComponent getCalendarComponent() {
		return calendarComponent;
	}


	/**
	 * Get the calendarDayTimeComponent object associated.
	 * @return the calendarDayTimeComponent object associated.
	 */
	public CalendarDayTimeComponent getCalendarDayTimeComponent() {
		return calendarDayTimeComponent;
	}
	
}