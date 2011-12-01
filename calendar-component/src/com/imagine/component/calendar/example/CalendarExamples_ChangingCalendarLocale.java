package com.imagine.component.calendar.example;

import java.util.Locale;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_ChangingCalendarLocale {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarProperty(CalendarComponent.LOCALE, Locale.FRANCE);

  }}
