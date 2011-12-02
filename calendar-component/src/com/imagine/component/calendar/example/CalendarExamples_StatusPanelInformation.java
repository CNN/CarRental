package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarStatusPanelInfo;

public class CalendarExamples_StatusPanelInformation {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

CalendarStatusPanelInfo calendarStatusPanelInfo = calendarComponent.getCalendarView().getMonthView().getCalendarStatusPanelInfo();
  }}
