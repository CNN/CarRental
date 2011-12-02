package com.imagine.component.calendar.demo.panels;

import java.io.*;

import javax.swing.JPanel;

import com.imagine.component.calendar.demo.CalendarDemoController;

public abstract class CalendarDemoPanel extends JPanel {
  
  protected CalendarDemoController calendarDemoController;
  
  public CalendarDemoPanel(CalendarDemoController calendarDemoController) {
    this.calendarDemoController = calendarDemoController;
  }
  
  public abstract String getTitle();

  public CalendarDemoController getCalendarDemoController() {
    return calendarDemoController;
  }
  
	public String getHelp(String helpPath) {
		try {
			StringBuffer helpBuffer = new StringBuffer();
			InputStream inputStream = this.getClass().getResourceAsStream(helpPath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			char[] chars = new char[1024];
			int n;
			while ((n = reader.read(chars)) != -1) {
				helpBuffer.append(chars, 0, n);
			}
			reader.close();
			inputStream.close();
			
			return helpBuffer.toString();
		} catch (Exception e) {
		}
		return "";
	}
	
  
}
