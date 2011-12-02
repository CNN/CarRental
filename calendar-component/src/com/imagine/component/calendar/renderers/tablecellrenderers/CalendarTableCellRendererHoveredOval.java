package com.imagine.component.calendar.renderers.tablecellrenderers;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines an Oval renderer for a hovered cell. 
 */
public class CalendarTableCellRendererHoveredOval implements CalendarTableCellRenderer {
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		}
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HOVERED_CELL_FILL_COLOR));
		g.fillOval(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HOVERED_CELL_TEXT_COLOR));
		
		Font cellFont = null;
		if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT);
		} else if (calendarTableCellInfo.isFromWeekend()) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEKEND_CELL_FONT);
		} else {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.CELL_FONT);
		}
		g.setFont(cellFont);
		GUIUtilities.drawCenteredString(g, 0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight(), calendarTableCellInfo.getValue().toString());
		if (calendarTableCellInfo.isDisabled()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.DISABLED_COLOR));
			int count = 3;
			g.drawLine(count, count, calendarTableCellInfo.getWidth() - count, calendarTableCellInfo.getHeight() - count);
			g.drawLine(count, calendarTableCellInfo.getHeight() - count, calendarTableCellInfo.getWidth() - count, count);
		}
	}
	
}
