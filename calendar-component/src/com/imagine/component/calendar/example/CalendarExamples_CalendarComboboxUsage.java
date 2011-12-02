package com.imagine.component.calendar.example;

import java.awt.FlowLayout;

import javax.swing.*;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.renderers.CalendarRendererProviderRoundRect;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleAqua;

public class CalendarExamples_CalendarComboboxUsage {

  public static void main(String[] args) {
CalendarComboBox calendarComboBox = new CalendarComboBox();

CalendarComponent calendarComponent = calendarComboBox.getCalendarComponent();
calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(java.util.Calendar.MONDAY));
calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleAqua());
calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderRoundRect());

JFrame frame = new JFrame("Calendar Test");
JPanel panel = new JPanel();
panel.setLayout(new FlowLayout());
panel.add(new JLabel("Enter a date:"));
panel.add(calendarComboBox);
frame.getContentPane().add(panel);
frame.pack();
frame.setVisible(true);
  }
}
