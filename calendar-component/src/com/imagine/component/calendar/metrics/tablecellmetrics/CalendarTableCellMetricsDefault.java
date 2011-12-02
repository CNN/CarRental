package com.imagine.component.calendar.metrics.tablecellmetrics;

import java.awt.*;
import java.util.Calendar;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the default table cell metrics object. 
 */
public class CalendarTableCellMetricsDefault implements CalendarTableCellMetrics {
	
	private Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarTableCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( (String)calendarTableCellInfo.getValue() + "  ") + 8;
  	dimension.height = fontMetrics.getAscent() + 4;
  	
  	return dimension;
	}
  
  /**
   * Get the FontMetrics object used for computations.
   * @param calendarComponent Thje parent calendarComponent object.
   * @param calendarTableCellInfo The calendarTableCellInfo object used for computations.
   * @return The FontMetrics object used for computations.
   */
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
	  Font font = calendarComponent.getFont();
	  int dayOfWeek = calendarTableCellInfo.getDayOfWeek();
	  CalendarMonthType calendarMonthType = calendarTableCellInfo.getMonthType();
	  boolean isFromWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
	  if (calendarMonthType != CalendarMonthType.CURRENT_MONTH) {
	    font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT); 
	  } else if (isFromWeekend) {
	    font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEKEND_CELL_FONT);
	  } else {
	    font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.CELL_FONT);
	  }
	  return calendarComponent.getFontMetrics(font);
	}
}
