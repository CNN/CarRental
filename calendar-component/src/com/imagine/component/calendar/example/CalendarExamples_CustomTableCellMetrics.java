package com.imagine.component.calendar.example;

import java.awt.*;
import java.util.Calendar;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderDefault;
import com.imagine.component.calendar.metrics.tablecellmetrics.CalendarTableCellMetrics;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomTableCellMetrics {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarMetricsProvider(new CalendarMetricsProviderCustomTable());
  }
  
}

class CalendarMetricsProviderCustomTable extends CalendarMetricsProviderDefault {

  CalendarTableCellMetricsCustom calendarTableCellMetricsCustom = new CalendarTableCellMetricsCustom();

  public CalendarTableCellMetrics getTableCellMetrics(CalendarTableCellInfo calendarTableCellInfo) {
    return calendarTableCellMetricsCustom;
  }
  
  public String getName() {
    return "Custom";
  }
}

class CalendarTableCellMetricsCustom implements CalendarTableCellMetrics {
	Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarTableCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( (String)calendarTableCellInfo.getValue() + "  ") + 20;
  	dimension.height = fontMetrics.getAscent() + 15;
  	
  	return dimension;
	}
  
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

  public String toString() {
    return "Custom";
  }
}  
