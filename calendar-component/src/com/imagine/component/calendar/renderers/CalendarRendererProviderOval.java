package com.imagine.component.calendar.renderers;

import com.imagine.component.calendar.renderers.tablecellrenderers.*;

/**
 * Define an Oval renderer provider.
 */
public class CalendarRendererProviderOval extends CalendarRendererProviderDefault {
  
  /**
   * Constructor for the CalendarRendererProviderDottedRect object.
   */
	public CalendarRendererProviderOval() {
  	this.calendarTableCellRendererSelected = new CalendarTableCellRendererSelectedOval();
		this.calendarTableCellRendererHovered = new CalendarTableCellRendererHoveredOval();
  }
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Oval";
  }
}
