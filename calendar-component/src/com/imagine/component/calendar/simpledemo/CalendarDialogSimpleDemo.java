package com.imagine.component.calendar.simpledemo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.renderers.CalendarRendererProviderRoundRect;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleLight;

public class CalendarDialogSimpleDemo {

  public static void main(String[] args) {
    JButton button  = new JButton("Open Dialog");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Date date = new Date();
          CalendarDialog calendarDialog = new CalendarDialog(null);

          CalendarComponent calendarComponent = calendarDialog.getCalendarComponent();
          calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(java.util.Calendar.MONDAY));
          calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleLight());
          calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
          calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
          calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(true));
          calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
          calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderRoundRect());

          calendarDialog.getDialog().setTitle("Choose the date of the event");
          calendarDialog.setVisible(true);

          date = calendarDialog.getDate();
          if (calendarDialog.getChosedOption() == CalendarDialog.OPTION_OK) {
        	  String message = "";
        	  if (date == null) {
        		  message = "Chosen Date: null";
        	  } else {
        		  GregorianCalendar calendar = new GregorianCalendar();
        		  calendar.setTime(date);
        		  message = "Chosen Date: " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        	  }
        	  JOptionPane.showMessageDialog(null, message);
          } else {
        	  JOptionPane.showMessageDialog(null, "The date selection was cancelled. No date was selected.");
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "The follwoing error occured: " + ex.getMessage());
        }
      }
    });
    
    JFrame frame = new JFrame("Calendar Dialog Test");
    frame.getContentPane().add(button);
    frame.setSize(200, 200);
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