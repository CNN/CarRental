package com.imagine.component.calendar.renderers.headercellrenderers;

import java.awt.Graphics;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarHeaderCellInfo;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines the default header renderer. 
 */
public class CalendarHeaderCellRendererDefault implements CalendarHeaderCellRenderer {
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HEADER_CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarHeaderCellInfo.getWidth(), calendarHeaderCellInfo.getHeight());
		}
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR));
		g.setFont(calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.HEADER_CELL_FONT));
		GUIUtilities.drawCenteredString(g, calendarHeaderCellInfo.getWidth(), calendarHeaderCellInfo.getHeight(), calendarHeaderCellInfo.getValue().toString());
	}
	
}
