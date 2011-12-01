package com.imagine.component.calendar.renderers;

import com.imagine.component.calendar.renderers.tablecellrenderers.CalendarTableCellRendererDottedRect;

/**
 * Define the a renderer provider which displays the hovered cells with a dotted rect.
 */
public class CalendarRendererProviderDottedRect extends CalendarRendererProviderDefault {
  
  /**
   * Constructor for the CalendarRendererProviderDottedRect object.
   */
	public CalendarRendererProviderDottedRect() {
  	this.calendarTableCellRendererHovered = new CalendarTableCellRendererDottedRect();
  }
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Dotted Rect";
  }
}
