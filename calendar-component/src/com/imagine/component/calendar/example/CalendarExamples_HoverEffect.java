package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_HoverEffect {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//enable hover effect
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(true));
//disable hover effect
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(false));
  }}
