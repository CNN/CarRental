package com.imagine.component.calendar.renderers;

import com.imagine.component.calendar.data.CalendarBackgroundInfo;
import com.imagine.component.calendar.renderers.backgroundrenderers.*;

/**
 * Defines a transparent renderer provider.
 */
public class CalendarRendererProviderTransparent extends CalendarRendererProviderDefault {
  
  CalendarBackgroundRendererTransparent calendarBackgroundRendererTransparent = new CalendarBackgroundRendererTransparent();
  
  /**
   * @inheritDoc
   */
  public CalendarBackgroundRenderer getBackgroundRenderer(CalendarBackgroundInfo calendarBackgroundInfo) {
    return calendarBackgroundRendererTransparent;
  }
  
  /**
   * @inheritDoc
   */
  public boolean hasBackgroundRenderer() {
    return true;
  }
  
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Transparent";
  }
}
