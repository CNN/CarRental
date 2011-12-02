package com.imagine.component.calendar.renderers.tablecellrenderers;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.GregorianCalendar;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

/**
 * Defines the renderer for today. 
 */
public class CalendarTableCellRendererToday implements CalendarTableCellRenderer {
	private GregorianCalendar calendar = new GregorianCalendar();
	
	private int oldWidth = -1;
	private int oldHeight = -1;
	
	private GeneralPath generalPath = null;
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		try {
			int height = calendarTableCellInfo.getHeight();
			int width = calendarTableCellInfo.getWidth();
			if (generalPath == null || height != oldHeight || width != oldWidth) {
				oldHeight = height;
				oldWidth = width;
				generalPath = new GeneralPath();
//				generalPath.moveTo((float)0.47 * width, (float)0.2 * height);
//				generalPath.quadTo((float)0.23 * width, (float)0.4 * height, (float)0.1 * width, (float)0.6 * height);
//				generalPath.quadTo((float)0.3 * width, (float)0.9 * height, (float)0.7 * width, (float)0.9 * height);
//				generalPath.quadTo((float)0.87 * width, (float)0.6 * height, (float)0.5 * width, (float)0.08 * height);
//				generalPath.lineTo((float)0.07 * width, (float)0.08 * height);
				
				int delta = 0;
				generalPath.moveTo(0, delta);
				generalPath.lineTo((float)0, (float)(height - delta - 1));
				generalPath.lineTo((float)width - delta - 1, (float)height - delta - 1);
				generalPath.lineTo((float)width - delta - 1, (float)delta);
				generalPath.lineTo((float)0, (float)delta);
			}
			
			if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
				Color fillColor = null;
				if (calendarTableCellInfo.isSelected()) {
					fillColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.SELECTED_CELL_FILL_COLOR);
				} else {
					fillColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR);
				}
				g.setColor(fillColor);
				g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
			} else {
				if (calendarTableCellInfo.isSelected()) {
					g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.SELECTED_CELL_FILL_COLOR));
					g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
				}
			}
			
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.TODAY_COLOR));
			((Graphics2D) g).draw(generalPath);
			
			
			Color textColor = null;
			if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
				textColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR);
			} else if (calendarTableCellInfo.isSelected()) {
				textColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.SELECTED_CELL_TEXT_COLOR);
			} else if (calendarTableCellInfo.isFromWeekend()) {
				textColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR);
			} else {
				textColor = calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_TEXT_COLOR);
			}
			g.setColor(textColor);
			
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
		} catch (Throwable e) {
		}
	}
	
}
