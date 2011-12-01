package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.selection.CalendarSelectionModelSingleIntervalSelection;

public class CalendarExamples_ChangingSelectionModel {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarSelectionModel(new CalendarSelectionModelSingleIntervalSelection());
  }
  
}
