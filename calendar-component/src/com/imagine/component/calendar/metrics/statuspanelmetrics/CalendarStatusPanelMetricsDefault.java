package com.imagine.component.calendar.metrics.statuspanelmetrics;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarStatusPanelInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the default status panel metrics object. 
 */
public class CalendarStatusPanelMetricsDefault implements CalendarStatusPanelMetrics {
	
	private Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarStatusPanelInfo);
  	
  	dimension.width = fontMetrics.stringWidth(calendarStatusPanelInfo.getTodayLabel() + " " + calendarStatusPanelInfo.getNoneLabel() + "  ") + 8;
  	dimension.height = fontMetrics.getAscent() + 4;
  	
  	return dimension;
	}
  
  /**
   * Get the FontMetrics object used for computations.
   * @param calendarComponent Thje parent calendarComponent object.
   * @param calendarStatusPanelInfo The calendarStatusPanelInfo object used for computations.
   * @return The FontMetrics object used for computations.
   */
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.STATUS_PANEL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}
}
