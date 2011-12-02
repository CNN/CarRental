package com.imagine.component.calendar.renderers.weekcellrenderers;

import java.awt.Graphics;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines the hovered week renderer. 
 */
public class CalendarWeekCellRendererHoveredRect implements CalendarWeekCellRenderer {
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEK_CELL_FILL_COLOR).brighter());
		g.fillRect(0, 0, calendarWeekCellInfo.getWidth(), calendarWeekCellInfo.getHeight());
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR).brighter());
		g.setFont(calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEK_CELL_FONT));
		GUIUtilities.drawCenteredString(g, calendarWeekCellInfo.getWidth(), calendarWeekCellInfo.getHeight(), calendarWeekCellInfo.getValue().toString());
	}
}
