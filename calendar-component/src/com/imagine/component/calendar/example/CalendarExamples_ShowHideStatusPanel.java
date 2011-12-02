package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_ShowHideStatusPanel {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    show status panel
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
//    hide status panel
calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(false));

  }}
