package com.imagine.component.calendar.example;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleAqua;

public class CalendarExamples_ApplyingStyle {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleAqua());
  }
  }
