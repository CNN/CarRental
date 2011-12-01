package com.imagine.component.calendar.metrics.headercellmetrics;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarHeaderCellInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the tight header cell metrics object. 
 */
public class CalendarHeaderCellMetricsTight implements CalendarHeaderCellMetrics {
	
	private Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarHeaderCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( (String)calendarHeaderCellInfo.getValue() + "  ") + 4;
  	dimension.height = fontMetrics.getAscent() + 4;
  	
  	return dimension;
	}
  
  /**
   * Get the FontMetrics object used for computations.
   * @param calendarComponent Thje parent calendarComponent object.
   * @param calendarHeaderCellInfo The calendarHeaderCellInfo object used for computations.
   * @return The FontMetrics object used for computations.
   */
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.HEADER_CELL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}
}
