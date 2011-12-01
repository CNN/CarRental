package com.imagine.component.calendar.renderers;

import java.util.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.renderers.backgroundrenderers.*;
import com.imagine.component.calendar.renderers.headercellrenderers.*;
import com.imagine.component.calendar.renderers.statuspanelrenderers.*;
import com.imagine.component.calendar.renderers.tablecellrenderers.*;
import com.imagine.component.calendar.renderers.weekcellrenderers.*;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Define the default renderer provider.
 */
public class CalendarRendererProviderDefault extends CalendarRendererProvider {
  
  private GregorianCalendar calendar = new GregorianCalendar();
  
  /**
   * These variable are declared protected and not private in order to be possible for who wants to define another provider
   * with the same behaviour but with different drawing to just set in constructor another value for one of these
   * and not to be forced to rewrite  the provider functions.
   */
  protected CalendarTableCellRenderer calendarTableCellRendererHovered = new CalendarTableCellRendererHoveredRect();
  protected CalendarTableCellRenderer calendarTableCellRendererExtraMonth = new CalendarTableCellRendererExtraMonth();
  protected CalendarTableCellRenderer calendarTableCellRendererToday = new CalendarTableCellRendererToday();
  protected CalendarTableCellRenderer calendarTableCellRendererSelected = new CalendarTableCellRendererSelectedRect();
  protected CalendarTableCellRenderer calendarTableCellRendererWeekend = new CalendarTableCellRendererWeekend();
  protected CalendarTableCellRenderer calendarTableCellRendererDefault = new CalendarTableCellRendererDefault();
  
  protected CalendarHeaderCellRenderer calendarHeaderCellRendererDefault = new CalendarHeaderCellRendererDefault();
  protected CalendarWeekCellRenderer calendarWeekCellRendererDefault = new CalendarWeekCellRendererDefault();
  protected CalendarBackgroundRenderer calendarBackgroundRendererDefault = new CalendarBackgroundRendererDefault();
  
  protected CalendarStatusPanelRenderer calendarStatusPanelRendererDefault = new CalendarStatusPanelRendererDefault();
  
  
  /**
   * @inheritDoc
   */
  public CalendarTableCellRenderer getTableCellRenderer(CalendarTableCellInfo calendarTableCellInfo) {
    if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
      if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS).booleanValue()) {
        if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_HOVER).booleanValue() && calendarTableCellInfo.isHovered()) {
          return calendarTableCellRendererHovered;
        }
        if (calendarTableCellInfo.isSelected()) {
        	return calendarTableCellRendererSelected;
        }
      }
      return calendarTableCellRendererExtraMonth;
    }
    
    if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_HOVER).booleanValue() && calendarTableCellInfo.isHovered()) {
      return calendarTableCellRendererHovered;
    }
    
    calendar.setTimeInMillis(System.currentTimeMillis());
    if (calendar.get(Calendar.YEAR) == calendarTableCellInfo.getYear() && 
        calendar.get(Calendar.MONTH) == calendarTableCellInfo.getMonth() &&        
        calendar.get(Calendar.DAY_OF_MONTH) == calendarTableCellInfo.getDayOfMonth()        
    ) {
      return calendarTableCellRendererToday;
    }
    
    if (calendarTableCellInfo.isSelected()) {
      return calendarTableCellRendererSelected;
    }
    
    if (calendarTableCellInfo.isFromWeekend()) {
      return calendarTableCellRendererWeekend;
    }
    
    return calendarTableCellRendererDefault;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarWeekCellRenderer getWeekCellRenderer(CalendarWeekCellInfo calendarWeekCellInfo) {
    return calendarWeekCellRendererDefault;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarHeaderCellRenderer getHeaderCellRenderer(CalendarHeaderCellInfo calendarHeaderCellInfo) {
    return calendarHeaderCellRendererDefault;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarBackgroundRenderer getBackgroundRenderer(CalendarBackgroundInfo calendarBackgroundInfo) {
    if (calendarComponent.getCalendarSkin().getImageSkinProperty(CalendarSkin.BACKGROUND_IMAGE) != null) {
      return calendarBackgroundRendererDefault;
    }
    return null;
  }
  
  /**
   * @inheritDoc
   */
  public boolean hasBackgroundRenderer() {
    if (calendarComponent.getCalendarSkin().getImageSkinProperty(CalendarSkin.BACKGROUND_IMAGE) != null) {
      return true;
    }
    return false;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarStatusPanelRenderer getStatusPanelRenderer(CalendarStatusPanelInfo calendarStatusPanelInfo) {
	  return calendarStatusPanelRendererDefault;
  }
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Default";
  }
}
