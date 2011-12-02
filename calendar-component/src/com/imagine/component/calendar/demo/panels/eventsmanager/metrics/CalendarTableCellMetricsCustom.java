package com.imagine.component.calendar.demo.panels.eventsmanager.metrics;

import java.awt.Dimension;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarTableCellInfo;
import com.imagine.component.calendar.metrics.tablecellmetrics.CalendarTableCellMetrics;

public class CalendarTableCellMetricsCustom implements CalendarTableCellMetrics {

	public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		return new Dimension(70, 70);
	}

}
