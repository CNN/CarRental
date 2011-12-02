package com.imagine.component.calendar.skins.styles;

import java.awt.*;

import com.imagine.component.calendar.data.CalendarPropertyException;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.skins.wrapper.CalendarSkinStyleAdapter;

/**
 * Defines the Gray style.
 */
public class CalendarSkinStyleGray extends CalendarSkinStyle {

  /**
   * Applies this style on the specified skin.
   * @param calendarSkin The calendarSkin on which the style is applyied.
   */
  public void applyStyle(CalendarSkin calendarSkin)  throws CalendarPropertyException {
    calendarSkin.setSkinProperty(CalendarSkin.CELL_TEXT_COLOR, new Color(0, 0, 0));
    calendarSkin.setSkinProperty(CalendarSkin.CELL_FILL_COLOR, new Color(255, 255, 255));
    calendarSkin.setSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR, new Color(128, 128, 128));
    calendarSkin.setSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR, new Color(0, 0, 0));
    
    calendarSkin.setSkinProperty(CalendarSkin.TODAY_COLOR, new Color(255, 0, 0));
    calendarSkin.setSkinProperty(CalendarSkin.DISABLED_COLOR, new Color(255, 0, 0));
    calendarSkin.setSkinProperty(CalendarSkin.GRID_COLOR, new Color(0, 64, 128));
    
    calendarSkin.setSkinProperty(CalendarSkin.SELECTED_CELL_FILL_COLOR, new Color(0, 64, 128));
    calendarSkin.setSkinProperty(CalendarSkin.SELECTED_CELL_TEXT_COLOR, new Color(255, 255, 255));
    
    calendarSkin.setSkinProperty(CalendarSkin.HOVERED_CELL_FILL_COLOR, new Color(0, 64, 128));
    calendarSkin.setSkinProperty(CalendarSkin.HOVERED_CELL_TEXT_COLOR, new Color(255, 255, 255));
    
    calendarSkin.setSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR, new Color(212, 208, 200));
    calendarSkin.setSkinProperty(CalendarSkin.HEADER_CELL_FILL_COLOR, new Color(128, 128, 128));
    
    calendarSkin.setSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR, new Color(212, 208, 200));
    calendarSkin.setSkinProperty(CalendarSkin.WEEK_CELL_FILL_COLOR, new Color(128, 128, 128));
    
    calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR, new Color(0, 0, 0));
    calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR, new Color(0, 64, 128));
    calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_FILL_COLOR, new Color(255, 255, 255));
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.CELL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.CELL_FONT_WRAPPER).setStyle(Font.PLAIN);
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.EXTRA_MONTH_CELL_FONT_WRAPPER).setStyle(Font.ITALIC);
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.WEEKEND_CELL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.WEEKEND_CELL_FONT_WRAPPER).setStyle(Font.ITALIC |Font.BOLD);
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.HEADER_CELL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.HEADER_FONT_WRAPPER).setStyle(Font.PLAIN);
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.WEEK_CELL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.WEEK_FONT_WRAPPER).setStyle(Font.PLAIN);
    
    // Set the fixed font value to null in order to be obtained a dynamical font value which is based on the component font.
    calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_FONT, null);
    // Set the font for the specified property to be the default component font but with the specified style.
    getCalendarSkinStyleAdapter().getFontWrapperProperty(CalendarSkinStyleAdapter.STATUS_PANEL_FONT_WRAPPER).setStyle(Font.BOLD);
    
    calendarSkin.setSkinProperty(CalendarSkin.BACKGROUND_IMAGE, null);
  }
  
  /**
   * Get the name of the style.
   * @return The name of the style.
   */
  public String getName() {
    return "Gray";
  }

}
