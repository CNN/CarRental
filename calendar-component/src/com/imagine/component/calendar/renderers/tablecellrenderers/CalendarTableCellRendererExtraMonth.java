package com.imagine.component.calendar.renderers.tablecellrenderers;

import java.awt.Graphics;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarTableCellInfo;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines the renderer for the extra month days. 
 */
public class CalendarTableCellRendererExtraMonth implements CalendarTableCellRenderer {
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		}
		
		
		if ((calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS).booleanValue())) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR));
			g.setFont(calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT));
			GUIUtilities.drawCenteredString(g, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight(), calendarTableCellInfo.getValue().toString());
		}
		if (calendarTableCellInfo.isDisabled()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.DISABLED_COLOR));
			int count = 3;
			g.drawLine(count, count, calendarTableCellInfo.getWidth() - count, calendarTableCellInfo.getHeight() - count);
			g.drawLine(count, calendarTableCellInfo.getHeight() - count, calendarTableCellInfo.getWidth() - count, count);
		}
	}
	
}
