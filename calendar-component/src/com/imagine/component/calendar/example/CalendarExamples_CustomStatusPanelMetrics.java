package com.imagine.component.calendar.example;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarStatusPanelInfo;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderDefault;
import com.imagine.component.calendar.metrics.statuspanelmetrics.CalendarStatusPanelMetrics;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomStatusPanelMetrics {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarMetricsProvider(new CalendarMetricsProviderCustomStatusPanel());
  }
  
}

class CalendarMetricsProviderCustomStatusPanel extends CalendarMetricsProviderDefault {

  CalendarStatusPanelMetricsCustom calendarStatusPanelMetricsCustom = new CalendarStatusPanelMetricsCustom();

  
  public CalendarStatusPanelMetrics getStatusPanelMetrics(CalendarStatusPanelInfo calendarStatusPanelInfo) {
    return calendarStatusPanelMetricsCustom;
  }
  
  public String getName() {
    return "Custom";
  }
}

class CalendarStatusPanelMetricsCustom implements CalendarStatusPanelMetrics {
	
	Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarStatusPanelInfo);
  	
  	dimension.width = fontMetrics.stringWidth(calendarStatusPanelInfo.getTodayLabel() + " " + calendarStatusPanelInfo.getNoneLabel() + "  ") + 14;
  	dimension.height = fontMetrics.getAscent() + 14;
  	
  	return dimension;
	}
  
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.STATUS_PANEL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}
  
	
  public String toString() {
    return "Custom";
  }
}  
