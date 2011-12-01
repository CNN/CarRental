package com.imagine.component.calendar.example;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderDefault;
import com.imagine.component.calendar.metrics.weekcellmetrics.CalendarWeekCellMetrics;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomWeekCellMetrics {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarMetricsProvider(new CalendarMetricsProviderCustomWeek());
  }
  
}

class CalendarMetricsProviderCustomWeek extends CalendarMetricsProviderDefault {

  CalendarWeekCellMetricsCustom calendarWeekCellMetricsCustom = new CalendarWeekCellMetricsCustom();


  public CalendarWeekCellMetrics getWeekCellMetrics(CalendarWeekCellInfo calendarWeekCellInfo) {
    return calendarWeekCellMetricsCustom;
  }
  
  public String getName() {
    return "Custom";
  }
}

class CalendarWeekCellMetricsCustom implements CalendarWeekCellMetrics {
	Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarWeekCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( "AA") + 14;
  	dimension.height = fontMetrics.getAscent() + 14;
  	
  	return dimension;
	}
	
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEK_CELL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}

  public String toString() {
    return "Custom";
  }
}  
