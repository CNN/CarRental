package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarHeaderCellInfo;

public class CalendarExamples_HeaderCellInformation {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

int column = 4;
CalendarHeaderCellInfo calendarHeaderCellInfo = calendarComponent.getCalendarView().getMonthView().getHeaderCellInfo(column);
  }}
