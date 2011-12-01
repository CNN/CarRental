package com.imagine.component.calendar01;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.renderers.CalendarRendererProviderDefault;
import com.imagine.component.calendar.renderers.statuspanelrenderers.CalendarStatusPanelRendererTodayLabelCentered;


/**
 * This is a class that extends the CalendarComponent class and customize its footer to display only the Today label centered.
 * 
 */
public class CalendarComponent01 extends CalendarComponent {
	
	/**
	 * Default constructor.
	 */
	public CalendarComponent01() {
		this.setCalendarRendererProvider(new CalendarRendererProviderDefault() {
			{
				this.calendarStatusPanelRendererDefault = new CalendarStatusPanelRendererTodayLabelCentered();
			}
		});
		
		this.getCalendarSelectionModel().setEmptySelectionAllowed(false);
		this.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		this.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(false));
		this.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(false));
	}
	
}