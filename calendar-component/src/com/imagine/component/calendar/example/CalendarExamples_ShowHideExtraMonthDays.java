package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_ShowHideExtraMonthDays {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    show extra month days
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(true));
//    hide extra month days
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(false));
  }}
