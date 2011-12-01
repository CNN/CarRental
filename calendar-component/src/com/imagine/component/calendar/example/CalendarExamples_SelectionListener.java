package com.imagine.component.calendar.example;

import java.util.Date;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.selection.*;

public class CalendarExamples_SelectionListener {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.getCalendarSelectionModel().addCalendarSelectionListener(new CalendarSelectionListener() {

	public void selectionChanged(CalendarSelectionModel calendarSelectionModel) {
		Date[] selectedDates = calendarSelectionModel.getSelectedDates();
		
		// Process the selected dates.
	}

});
  }
  
}
