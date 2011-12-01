package com.imagine.component.calendar.demo;

import java.util.*;

import javax.swing.UIManager;

import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.demo.panels.mainfeatures.*;
import com.imagine.component.calendar.metrics.*;
import com.imagine.component.calendar.renderers.*;
import com.imagine.component.calendar.selection.*;
import com.imagine.component.calendar.skins.styles.*;

public class CalendarDemoController {
  
  private Date date = null;
  
  private Locale[] locales = Locale.getAvailableLocales();
  
  private CalendarRendererProvider[] calendarRendererProviders = { 
      new CalendarRendererProviderDefault(), 
      new CalendarRendererProviderOval(), 
      new CalendarRendererProviderRoundRect(), 
      new CalendarRendererProviderDottedRect(),
      new CalendarRendererProviderHoveredLine(), 
      new CalendarRendererProviderHoveredColumn(), 
      new CalendarRendererProviderHoveredIntersection(), 
  };
  
  private CalendarMetricsProvider[] calendarMetricsProviders = { 
      new CalendarMetricsProviderDefault(), 
      new CalendarMetricsProviderTight(), 
  };
  
  private CalendarSelectionModel[] calendarSelectionModels = { 
	      new CalendarSelectionModelSingleSelection(), 
	      new CalendarSelectionModelSingleIntervalSelection(), 
	      new CalendarSelectionModelMultipleIntervalSelection(), 
	      new CalendarSelectionModelDisabledWeekends(),
	  };
	  
  private Object[] calendarNavigationControllers = { 
	      new CalendarNavigationControllerDefault(),
	      new CalendarNavigationControllerSimpleNavigation(),
	      "No Navigation Controller"
	  };
	  
  private CalendarSkinStyle[] skinStyles = {
    new CalendarSkinStyleDefault(),
    new CalendarSkinStyleGray(),
    new CalendarSkinStyleForest(),
    new CalendarSkinStyleAqua(),
    new CalendarSkinStyleLight(),
    new CalendarSkinStyleDark(),
    new CalendarSkinStyleCustomBackgroundImage(),
  };
  
  private String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
  
  UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
  
  private CalendarDemo calendarDemo;
  
  public CalendarDemoController(CalendarDemo calendarDemo) {
    this.calendarDemo = calendarDemo;
    
    try {
      UIManager.setLookAndFeel(systemLookAndFeel);
    } catch (Exception e) {
    }
  }
  
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public Locale[] getLocales() {
    return locales;
  }
  
  public CalendarRendererProvider[] getCalendarRendererProviders() {
    return calendarRendererProviders;
  }
  
  public CalendarSkinStyle[] getSkinStyles() {
    return skinStyles;
  }
  
  public String getSystemLookAndFeel() {
    return systemLookAndFeel;
  }
  
  public UIManager.LookAndFeelInfo[] getLookAndFeelInfos() {
    return lookAndFeelInfos;
  }
  
  public CalendarDemo getCalendarDemo() {
    return calendarDemo;
  }

  public CalendarSelectionModel[] getCalendarSelectionModels() {
	  return calendarSelectionModels;
  }

  public Object[] getCalendarNavigationControllers() {
	  return calendarNavigationControllers;
  }

	public Object[] getCalendarMetricsProviders() {
	  return calendarMetricsProviders;
  }
}
