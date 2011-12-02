package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;

public class CalendarExamples_WeekCellInformation {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

int row = 4;
CalendarWeekCellInfo calendarWeekCellInfo = calendarComponent.getCalendarView().getMonthView().getWeekCellInfo(row);
  }}
