package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.renderers.CalendarRendererProviderRoundRect;

public class CalendarExamples_CustomizeCellsRendering {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderRoundRect());

  }}
