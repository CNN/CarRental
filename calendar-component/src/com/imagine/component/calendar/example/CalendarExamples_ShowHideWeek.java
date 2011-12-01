package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_ShowHideWeek {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    show week
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
//    hide week
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(false));

  }}
