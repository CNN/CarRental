package com.imagine.component.calendar.example;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.events.*;

public class CalendarExamples_CalendarComponentListener {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.addCalendarComponentListener(new CalendarComponentListener() {
  public void processCalendarEvent(CalendarEvent calendarEvent) {
    if (calendarEvent instanceof CalendarEventFirstDayOfWeekChanged) {
      CalendarEventFirstDayOfWeekChanged calendarEventFirstDayOfWeekChanged = (CalendarEventFirstDayOfWeekChanged)calendarEvent;
      System.out.println("First day of week:" + calendarEventFirstDayOfWeekChanged.getFirstDayOfWeek());
      // some java code here
    } else if (calendarEvent instanceof CalendarEventMaxMonthsNamesLengthChanged) {
      CalendarEventMaxMonthsNamesLengthChanged calendarEventMaxMonthsNamesLengthChanged = (CalendarEventMaxMonthsNamesLengthChanged)calendarEvent;
      System.out.println("Max Months Names Length:" + calendarEventMaxMonthsNamesLengthChanged.getMaxMonthsNamesLength());
      // other java code here
    }
  }

  public Class[] getEventsListened() {
    return new Class[] {
        CalendarEventFirstDayOfWeekChanged.class,
        CalendarEventMaxMonthsNamesLengthChanged.class
    };
  }
});
  }}
