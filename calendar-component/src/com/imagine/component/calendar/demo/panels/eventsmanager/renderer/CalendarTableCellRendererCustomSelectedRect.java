package com.imagine.component.calendar.demo.panels.eventsmanager.renderer;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.demo.panels.eventsmanager.visualentities.VisualEntitiesManager;
import com.imagine.component.calendar.renderers.tablecellrenderers.CalendarTableCellRenderer;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines an Rect renderer for a selected cell. 
 */
public class CalendarTableCellRendererCustomSelectedRect implements CalendarTableCellRenderer {
	
	private CalendarRendererProviderCustom calendarRendererProviderCustom;
	
	public CalendarTableCellRendererCustomSelectedRect(CalendarRendererProviderCustom calendarRendererProviderCustom) {
		this.calendarRendererProviderCustom = calendarRendererProviderCustom;
  }
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		}
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.SELECTED_CELL_FILL_COLOR));
		g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.SELECTED_CELL_TEXT_COLOR));
		
		Font cellFont = null;
		if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT);
		} else if (calendarTableCellInfo.isFromWeekend()) {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEKEND_CELL_FONT);
		} else {
			cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.CELL_FONT);
		}
		g.setFont(cellFont);
		CustomGUIUtilities.drawTopLeftString(g, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight(), calendarTableCellInfo.getValue().toString());
		
		CalendarDemoPanelEventsManager calendarDemoPanelEventsManager = calendarRendererProviderCustom.getCalendarDemoPanelEventsManager();
		VisualEntitiesManager visualEntitiesManager = calendarDemoPanelEventsManager.getVisualEntitiesManager(calendarDemoPanelEventsManager.calculateKey(calendarTableCellInfo));
		if (visualEntitiesManager != null) {
			visualEntitiesManager.paint(g);
		}
		
		if (calendarTableCellInfo.isDisabled()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.DISABLED_COLOR));
			int count = 3;
			g.drawLine(count, count, calendarTableCellInfo.getWidth() - count, calendarTableCellInfo.getHeight() - count);
			g.drawLine(count, calendarTableCellInfo.getHeight() - count, calendarTableCellInfo.getWidth() - count, count);
		}
	}
	
}
