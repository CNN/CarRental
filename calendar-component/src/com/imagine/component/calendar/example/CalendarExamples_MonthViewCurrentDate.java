package com.imagine.component.calendar.example;

import java.util.*;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_MonthViewCurrentDate {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

// Get the month view current date. 
// The current date is the date from which the key operations are started. 
Date currentDate = calendarComponent.getCalendarView().getMonthView().getMonthViewCurrentDate();


// Set the date displayed by the month view.
// This become the date from which the key operations are started.
// This is not usually the calendar date. The calendar may display a month but it can have selected dates in other months.
GregorianCalendar calendar = new GregorianCalendar();
calendar.set(Calendar.YEAR, 1990);
calendar.set(Calendar.MONTH, Calendar.AUGUST);
calendar.set(Calendar.DAY_OF_MONTH, 15);

calendarComponent.getCalendarView().getMonthView().setMonthViewCurrentDate(calendar.getTime());
  }}
