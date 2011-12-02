package com.imagine.component.calendar.example;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarHeaderCellInfo;
import com.imagine.component.calendar.metrics.CalendarMetricsProviderDefault;
import com.imagine.component.calendar.metrics.headercellmetrics.CalendarHeaderCellMetrics;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomHeaderCellMetrics {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.setCalendarMetricsProvider(new CalendarMetricsProviderCustomHeader());
  }
  
}

class CalendarMetricsProviderCustomHeader extends CalendarMetricsProviderDefault {

  CalendarHeaderCellMetricsCustom calendarHeaderCellMetricsCustom = new CalendarHeaderCellMetricsCustom();

  public CalendarHeaderCellMetrics getHeaderCellMetrics(CalendarHeaderCellInfo calendarHeaderCellInfo) {
    return calendarHeaderCellMetricsCustom;
  }
  
  public String getName() {
    return "Custom";
  }
}

class CalendarHeaderCellMetricsCustom implements CalendarHeaderCellMetrics {
	Dimension dimension = new Dimension(0, 0);
	
	/**
	 * @inheritDoc
	 */
  public Dimension getPreferredSize(CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
  	FontMetrics fontMetrics = getFontMetrics(calendarComponent, calendarHeaderCellInfo);
  	
  	dimension.width = fontMetrics.stringWidth( (String)calendarHeaderCellInfo.getValue() + " ") + 14;
  	dimension.height = fontMetrics.getAscent() + 14;
  	
  	return dimension;
	}
  
	private FontMetrics getFontMetrics(CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
  	Font font = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.HEADER_CELL_FONT);
	  return calendarComponent.getFontMetrics(font);
	}

  public String toString() {
    return "Custom";
  }
}  
