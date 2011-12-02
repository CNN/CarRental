package com.imagine.component.calendar.demo.panels.eventsmanager.metrics;

import com.imagine.component.calendar.data.CalendarTableCellInfo;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderDefault;
import com.imagine.component.calendar.metrics.tablecellmetrics.CalendarTableCellMetrics;

public class CalendarMetricsProviderCustom extends CalendarMetricsProviderDefault {

	CalendarTableCellMetrics calendarTableCellMetrics = new CalendarTableCellMetricsCustom();
	
	public CalendarTableCellMetrics getTableCellMetrics(CalendarTableCellInfo calendarTableCellInfo) {
		return calendarTableCellMetrics;
	}

	public String getName() {
		return "Custom";
	}

}
