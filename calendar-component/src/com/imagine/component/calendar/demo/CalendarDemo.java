
package com.imagine.component.calendar.demo;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.demo.panels.*;
import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.demo.panels.examples.CalendarDemoPanelExamples;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.monthlycalendar.CalendarDemoPanelMonthlyCalendar;
import com.imagine.component.calendar.demo.panels.skineditor.CalendarDemoPanelSkinEditor;

public class CalendarDemo {
  private JFrame frame = new JFrame();
  private CalendarDemoController calendarDemoController = new CalendarDemoController(this);

  public CalendarDemo() {
    initialize();
  }

  public static void main(String[] args) {
    CalendarDemo calendarDemo = new CalendarDemo();
    calendarDemo.setVisible(true);
  }

  private void initialize() {
    frame.setTitle("Calendar Component v5.1 Professional");
    
    JPanel panel = new JPanel();
    CalendarDemoTopPanel calendarDemoTopPanel = new CalendarDemoTopPanel(calendarDemoController);
    
    CalendarDemoPanel[] panels = new CalendarDemoPanel[] {
        new CalendarDemoPanelMainFeatures(calendarDemoController),
        new CalendarDemoPanelSkinEditor(calendarDemoController),
        new CalendarDemoPanelEventsManager(calendarDemoController),
        new CalendarDemoPanelMonthlyCalendar(calendarDemoController),
        new CalendarDemoPanelExamples(calendarDemoController),
    };
    
    JTabbedPane tabbedPane = new JTabbedPane();
    for (int i = 0; i < panels.length; i++) {
      tabbedPane.add(panels[i].getTitle(), panels[i]);
    }
    int count = 0;
    panel.setLayout(new GridBagLayout());
    panel.add(calendarDemoTopPanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    count++;
    panel.add(new JSeparator(), new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    count++;
    panel.add(tabbedPane, new GridBagConstraints(0, count, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    count++;
    
    frame.getContentPane().setLayout(new GridBagLayout());
    frame.getContentPane().add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    frame.pack();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    centerWindowOnScreen(frame);
    
  }

  public void setVisible(boolean visible) {
    frame.setVisible(visible);
  }

  /**
   * This function will center the specified window on screen.
   */
  public static void centerWindowOnScreen(Window window) {
    Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimWindow = window.getSize();
    window.setBounds((dimScreen.width - dimWindow.width) / 2, (dimScreen.height - dimWindow.height) / 2, dimWindow.width, dimWindow.height);
  }

  public JFrame getFrame() {
    return frame;
  }
}