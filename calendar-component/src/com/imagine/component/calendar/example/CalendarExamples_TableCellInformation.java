package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarTableCellInfo;

public class CalendarExamples_TableCellInformation {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

int row = 0;
int column = 3;
CalendarTableCellInfo calendarTableCellInfo = calendarComponent.getCalendarView().getMonthView().getTableCellInfo(row, column);
  }}
