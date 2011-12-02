package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarBackgroundInfo;

public class CalendarExamples_CalendarBackgroundInformation {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

CalendarBackgroundInfo calendarBackgroundInfo = calendarComponent.getCalendarView().getMonthView().getCalendarBackgroundInfo();
  }}
