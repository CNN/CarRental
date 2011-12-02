package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_ShowHideGrid {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    show grid
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(true));
//    hide grid
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(false));

  }}
