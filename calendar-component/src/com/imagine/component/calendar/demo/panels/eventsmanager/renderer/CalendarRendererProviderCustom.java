package com.imagine.component.calendar.demo.panels.eventsmanager.renderer;

import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.renderers.CalendarRendererProviderDefault;

public class CalendarRendererProviderCustom extends CalendarRendererProviderDefault {
  
  private CalendarDemoPanelEventsManager calendarDemoPanelEventsManager;
  
  public CalendarRendererProviderCustom(CalendarDemoPanelEventsManager calendarDemoPanelEventsManager) {
  	this.calendarDemoPanelEventsManager = calendarDemoPanelEventsManager;
  	
  	this.calendarTableCellRendererDefault = new CalendarTableCellRendererCustom(this);
  	this.calendarTableCellRendererHovered = new CalendarTableCellRendererCustomHoveredRect(this);
  	this.calendarTableCellRendererExtraMonth = new CalendarTableCellRendererCustomExtraMonth(this);
  	this.calendarTableCellRendererToday = new CalendarTableCellRendererCustomToday(this);
  	this.calendarTableCellRendererSelected = new CalendarTableCellRendererCustomSelectedRect(this);
  	this.calendarTableCellRendererWeekend = new CalendarTableCellRendererCustomWeekend(this);
  }
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Custom";
  }

  public CalendarDemoPanelEventsManager getCalendarDemoPanelEventsManager() {
	  return calendarDemoPanelEventsManager;
  }
}
