package com.imagine.component.calendar.simpledemo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import com.imagine.component.calendar.CalendarComponent;

public class CalendarSimpleDemo {

  public static void main(String[] args) {
    CalendarComponent calendarComponent = new CalendarComponent();
    
    JFrame frame = new JFrame("Calendar Test");
    frame.getContentPane().add(calendarComponent);
    frame.pack();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent arg0) {
        System.exit(0);
      }
    });
    centerWindowOnScreen(frame);
    frame.setVisible(true);
  }

  /**
   * This function will center the specified window on screen.
   */
  public static void centerWindowOnScreen(Window window) {
    Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimWindow = window.getSize();
    window.setBounds((dimScreen.width - dimWindow.width) / 2, (dimScreen.height - dimWindow.height) / 2, dimWindow.width, dimWindow.height);
  }

}