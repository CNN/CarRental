package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderTight;

public class CalendarExamples_ChangingMetricsProvider {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarMetricsProvider(new CalendarMetricsProviderTight());
  }
  
}
