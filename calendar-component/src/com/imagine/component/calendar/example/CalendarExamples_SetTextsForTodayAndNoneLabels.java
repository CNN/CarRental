package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_SetTextsForTodayAndNoneLabels {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
//    set text for today label
calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Hoy: {MM.dd.yyyy}");
//    set text for none label
calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Ninguno");

  }}
