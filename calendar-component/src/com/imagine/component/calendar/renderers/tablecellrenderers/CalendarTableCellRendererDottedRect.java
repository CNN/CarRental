package com.imagine.component.calendar.renderers.tablecellrenderers;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines an dotted line renderer for a selected cell. 
 */
public class CalendarTableCellRendererDottedRect implements CalendarTableCellRenderer {
	
	Stroke defaultStroke = null;
	BasicStroke dottedStroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[] {2f}, 0f);
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		}
		
		Color cellColor = null;
		if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
			cellColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR);
		} else if (calendarTableCellInfo.isFromWeekend()) {
			cellColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR);
		} else {
			cellColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_TEXT_COLOR);
		}
		g.setColor(cellColor);
		Font cellFont = null;
		if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT);
		} else if (calendarTableCellInfo.isFromWeekend()) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEKEND_CELL_FONT);
		} else {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.CELL_FONT);
		}
		g.setFont(cellFont);
		GUIUtilities.drawCenteredString(g, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight(), calendarTableCellInfo.getValue().toString());
		
		if (calendarTableCellInfo.isDisabled()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.DISABLED_COLOR));
			int count = 3;
			g.drawLine(count, count, calendarTableCellInfo.getWidth() - count, calendarTableCellInfo.getHeight() - count);
			g.drawLine(count, calendarTableCellInfo.getHeight() - count, calendarTableCellInfo.getWidth() - count, count);
		}
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HOVERED_CELL_FILL_COLOR));
		Graphics2D g2d = (Graphics2D)g;
		
		defaultStroke = g2d.getStroke();
		Rectangle2D rectangle = new Rectangle2D.Double (0, 0, calendarTableCellInfo.getWidth() - 1, calendarTableCellInfo.getHeight() - 1);
		g2d.setStroke (dottedStroke);
		g2d.draw(rectangle);
		g2d.setStroke(defaultStroke);
	}
	
}
