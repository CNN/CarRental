package com.imagine.component.calendar.example;

import java.util.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.selection.CalendarSelectionModelMultipleIntervalSelection;

public class CalendarExamples_DisabledDates {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarSelectionModel(new CalendarSelectionModelCustom());
  }
  
}


class CalendarSelectionModelCustom extends CalendarSelectionModelMultipleIntervalSelection {

  private GregorianCalendar calendar = new GregorianCalendar();
  
  public boolean isDateDisabled(Date date) {
    calendar.setTime(date);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
  }
  
  public String getName() {
    return "Custom";
  }
}
