package com.imagine.component.calendar.example;

import java.util.Locale;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.events.*;

public class CalendarExamples_AutomaticallyChangeTextsForTodayAndNoneLabelsWhenLocaleChanges {

  public static void main(String[] args) {
final CalendarComponent calendarComponent = new CalendarComponent();

calendarComponent.addCalendarComponentListener(new CalendarComponentListener() {
  
  public Class[] getEventsListened() {
	  return new Class[]{CalendarEventLocaleChanged.class};
  }
  
  public void processCalendarEvent(CalendarEvent calendarEvent) {
	  Locale locale = calendarComponent.getLocaleCalendarProperty(CalendarComponent.LOCALE);
	  if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Today: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "None");
	  } else if (locale.getLanguage().equals(Locale.GERMAN.getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Heute: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Kein");
	  } else if (locale.getLanguage().equals(Locale.FRENCH.getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Aujourd''hui: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Aucun");
	  } else if (locale.getLanguage().equals(Locale.ITALIAN.getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Oggi: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Nessuno");
	  } else if (locale.getLanguage().equals(new Locale("es", "", "").getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Hoy: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Ninguno");
	  } else if (locale.getLanguage().equals(new Locale("ro", "", "").getLanguage())) {
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_TODAY_LABEL, "Astazi: {MM.dd.yyyy}");
		  calendarComponent.setCalendarProperty(CalendarComponent.STATUS_PANEL_NONE_LABEL, "Nimic");
	  }
  }
  
});

  }}
