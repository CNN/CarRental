package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarPartInfo;

public class CalendarExamples_HoveredCalendarPart {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

CalendarPartInfo calendarPartInfo = calendarComponent.getCalendarView().getMonthView().getHoveredCalendarPartInfo();
  }}
