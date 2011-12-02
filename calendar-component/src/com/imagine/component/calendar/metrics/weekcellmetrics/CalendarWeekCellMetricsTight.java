package com.imagine.component.calendar.metrics.weekcellmetrics;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the tight week cell metrics object. 
 */
public class CalendarWeekCellMetricsTight implements CalendarWeekCellMetrics {
	
	private Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarWeekCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( "AA  ");
  	dimension.height = fontMetrics.getAscent() + 4;
  	
  	return dimension;
	}
	
  /**
   * Get the FontMetrics object used for computations.
   * @param calendarComponent Thje parent calendarComponent object.
   * @param calendarWeekCellInfo The calendarWeekCellInfo object used for computations.
   * @return The FontMetrics object used for computations.
   */
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEK_CELL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}
}
