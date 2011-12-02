package com.imagine.component.calendar.example;

import java.util.Date;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.renderers.CalendarRendererProviderRoundRect;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleAqua;

public class CalendarExamples_CalendarDialogUsage {

  public static void main(String[] args) {
CalendarDialog calendarDialog = new CalendarDialog(null);
CalendarComponent calendarComponent = calendarDialog.getCalendarComponent();
calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(java.util.Calendar.MONDAY));
calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleAqua());
calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderRoundRect());
calendarDialog.getDialog().setTitle("Choose the date of the event");
calendarDialog.getDialog().pack();
calendarDialog.setVisible(true);
Date date = calendarDialog.getDate();

  }}
