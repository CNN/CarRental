package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_EnableDisableNullDates {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    enable null dates
calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
//    disable null dates 
calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(false);

  }}
