package com.imagine.component.calendar.example;

import java.util.Date;

import com.imagine.component.calendar.*;

public class CalendarExamples_DateListener {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.addDateListener(new DateListener() {
  public void dateChanged(Date date) {
    // some java code here
  }
});

  }}
