package com.imagine.component.calendar.renderers;

import com.imagine.component.calendar.renderers.tablecellrenderers.*;

/**
 * Define an RoundRect renderer provider.
 */
public class CalendarRendererProviderRoundRect extends CalendarRendererProviderDefault {
  
  /**
   * Constructor for the CalendarRendererProviderDottedRect object.
   */
	public CalendarRendererProviderRoundRect() {
  	this.calendarTableCellRendererSelected = new CalendarTableCellRendererSelectedRoundRect();
  	this.calendarTableCellRendererHovered = new CalendarTableCellRendererHoveredRoundRect();
  }
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "RoundRect";
  }
}
