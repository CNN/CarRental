package com.imagine.component.calendar.simpledemo;

import javax.swing.JApplet;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarAppletSimpleDemo extends JApplet {
  
  CalendarComponent calendarComponent;
  
  public void init() {
    calendarComponent = new CalendarComponent();
    this.getContentPane().add(calendarComponent);
  }
  
  public void start() {
  }
  
  public void stop() {
  }
  
  public void destroy() {
  }
  
}
