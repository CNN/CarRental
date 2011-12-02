package com.imagine.component.calendar.demo.panels.mainfeatures;

import java.util.*;

import com.imagine.component.calendar.selection.CalendarSelectionModelMultipleIntervalSelection;

public class CalendarSelectionModelDisabledWeekends extends CalendarSelectionModelMultipleIntervalSelection {

	private GregorianCalendar calendar = new GregorianCalendar();
	
	public boolean isDateDisabled(Date date) {
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
	}
	
	public String getName() {
		return "(Custom) Disabled Weekends";
	}
}
