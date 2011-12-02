package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_FirstDayOfWeek {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(java.util.Calendar.TUESDAY));

  }}
