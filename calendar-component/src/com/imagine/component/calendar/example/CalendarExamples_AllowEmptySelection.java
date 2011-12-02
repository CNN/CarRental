package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_AllowEmptySelection {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//  allow empty selection
calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
//  not allow empty selection
calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(false);
  }}
