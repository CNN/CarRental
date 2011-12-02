package com.imagine.component.calendar.applet;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import netscape.javascript.JSObject;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.applet.util.ParameterReader;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * A  calendar applet that can be used to select a date in your web pages.
 * The selected date is provided through the call of a javascript function. The name of the javascript function 
 * is set through the applet parameter javascriptFunction. The format in which the date is provided is set through the applet
 * parameter dateFormat.
 *
 */
public class CalendarApplet extends JApplet {
	
	/**
	 * The date format in which this applet provides the date as parameter to the javascript function.
	 */
	private SimpleDateFormat dateFormat = null;

	/**
	 * The name of the javascript function which is called when a date is chosen.
	 */
	private String javascriptFunction = null;

  /**
   * The calendar component.
   */
	private CalendarComponent calendarComponent;
  
  public void init() {
  }
  
  public void start() {
    try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    initializeCalendarComponent();
    readParameters();
    createUI();
  }
  
  private void initializeCalendarComponent() {
		dateFormat = new SimpleDateFormat(ParameterReader.getStringParameter(this, "DATE_FORMAT", "yyyy.MM.dd"));
    javascriptFunction = ParameterReader.getStringParameter(this, "JAVASCRIPT_FUNCTION", "setDate");
    
    String initialDate = ParameterReader.getStringParameter(this, "INITIAL_DATE", "");
  	
    Date date  = null;
    try {
	    date = dateFormat.parse(initialDate);
    } catch (Exception e) {
    	date =  new Date();
    }
    
    calendarComponent = new CalendarComponent(date);
    calendarComponent.addDateListener(new DateListener() {
			public void dateChanged(Date date) {
				javascriptFunctionCall(date);
			}
		});
  }

	private void createUI() {
    JPanel fillPanel = new JPanel();
    fillPanel.setOpaque(false);
    
    Container panel = this.getContentPane(); 
    panel.setBackground(ParameterReader.getColor(this, "BACKGROUND_COLOR", 0xffffff));
    panel.setLayout(new GridBagLayout());
		panel.add(calendarComponent, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(fillPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

	public void stop() {
  }
  
  public void destroy() {
  }
  
  private void readParameters() {
    String firstDayOfWeekString = ParameterReader.getStringParameter(this, "FIRST_DAY_OF_WEEK", "SUNDAY");

    int firstDayOfWeek = Calendar.SUNDAY;
    if (firstDayOfWeekString.equals("SUNDAY")) {
    	firstDayOfWeek = Calendar.SUNDAY;
    } else if (firstDayOfWeekString.equals("MONDAY")) {
      	firstDayOfWeek = Calendar.MONDAY;
    } else if (firstDayOfWeekString.equals("TUESDAY")) {
    	firstDayOfWeek = Calendar.TUESDAY;
    } else if (firstDayOfWeekString.equals("WEDNESDAY")) {
    	firstDayOfWeek = Calendar.WEDNESDAY;
    } else if (firstDayOfWeekString.equals("THURSDAY")) {
    	firstDayOfWeek = Calendar.THURSDAY;
    } else if (firstDayOfWeekString.equals("FRIDAY")) {
    	firstDayOfWeek = Calendar.FRIDAY;
    } else if (firstDayOfWeekString.equals("SATURDAY")) {
    	firstDayOfWeek = Calendar.SATURDAY;
    }
  	calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(firstDayOfWeek));
    
    calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(ParameterReader.getBoolean(this, "SHOW_WEEK", true)));
    calendarComponent.setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(ParameterReader.getBoolean(this, "SHOW_HOVER", true)));
    calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(ParameterReader.getBoolean(this, "SHOW_STATUS_PANEL", true)));
    calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(ParameterReader.getBoolean(this, "SHOW_GRID", true)));
    calendarComponent.setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(ParameterReader.getBoolean(this, "SHOW_EXTRA_MONTH_DAYS", true)));
    
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.CELL_TEXT_COLOR, ParameterReader.getColor(this, "CELL_TEXT_COLOR", 0x023853));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.CELL_FILL_COLOR, ParameterReader.getColor(this, "CELL_FILL_COLOR", 0xffffff));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.SELECTED_CELL_TEXT_COLOR, ParameterReader.getColor(this, "SELECTED_CELL_TEXT_COLOR", 0xffffff));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.SELECTED_CELL_FILL_COLOR, ParameterReader.getColor(this, "SELECTED_CELL_FILL_COLOR", 0x3a88fb));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR, ParameterReader.getColor(this, "EXTRA_MONTH_CELL_TEXT_COLOR", 0x84bffa));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR, ParameterReader.getColor(this, "WEEKEND_CELL_TEXT_COLOR", 0x4091e3));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.TODAY_COLOR, ParameterReader.getColor(this, "TODAY_COLOR", 0xff6600));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.DISABLED_COLOR, ParameterReader.getColor(this, "DISABLED_COLOR", 0xff6600));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.GRID_COLOR, ParameterReader.getColor(this, "GRID_COLOR", 0x7cb4fa));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR, ParameterReader.getColor(this, "WEEK_CELL_TEXT_COLOR", 0xffffff));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.WEEK_CELL_FILL_COLOR, ParameterReader.getColor(this, "WEEK_CELL_FILL_COLOR", 0x41b1f5));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR, ParameterReader.getColor(this, "HEADER_CELL_TEXT_COLOR", 0xffffff));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.HEADER_CELL_FILL_COLOR, ParameterReader.getColor(this, "HEADER_CELL_FILL_COLOR", 0x287dfc));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.HOVERED_CELL_TEXT_COLOR, ParameterReader.getColor(this, "HOVERED_CELL_TEXT_COLOR", 0xffffff));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.HOVERED_CELL_FILL_COLOR, ParameterReader.getColor(this, "HOVERED_CELL_FILL_COLOR", 0x41b1f5));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR, ParameterReader.getColor(this, "STATUS_PANEL_TEXT_COLOR", 0x3282fb));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR, ParameterReader.getColor(this, "STATUS_PANEL_HOVERED_TEXT_COLOR", 0x7fb4f6));
    calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.STATUS_PANEL_FILL_COLOR, ParameterReader.getColor(this, "STATUS_PANEL_FILL_COLOR", 0xfcfcf4));
  }
  
  public String getDate() {
  	return dateFormat.format(calendarComponent.getDate());
  }
  
	private void javascriptFunctionCall(Date date) {
    try {
      JSObject win = JSObject.getWindow(CalendarApplet.this);
      win.call(javascriptFunction, new Object[] {dateFormat.format(date)});
    } catch (Exception e) {
    	e.printStackTrace();
    }				
  }
}
