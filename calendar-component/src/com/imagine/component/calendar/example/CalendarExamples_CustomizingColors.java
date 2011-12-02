package com.imagine.component.calendar.example;

import java.awt.Color;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomizingColors {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.CELL_TEXT_COLOR, new Color(18, 230, 100));

  }
  
}
