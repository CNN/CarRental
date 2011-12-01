package com.imagine.component.calendar.example;

import javax.swing.JFrame;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarExamples_CalendarComponentUsage {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

JFrame frame = new JFrame("Calendar Test");
frame.getContentPane().add(calendarComponent);
frame.pack();
frame.setVisible(true);
  }
  
}
