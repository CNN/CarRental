package com.imagine.component.calendar.demo.panels.mainfeatures;

import java.util.*;

import com.imagine.component.calendar.CalendarComponentListener;
import com.imagine.component.calendar.events.*;
import com.imagine.component.calendar.selection.CalendarSelectionModel;


public class CustomCalendarComponentListener implements CalendarComponentListener {
	private CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures;	

	public CustomCalendarComponentListener(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		this.calendarDemoPanelMainFeatures = calendarDemoPanelMainFeatures;
	}
	
	  public Class[] getEventsListened() {
	  	return new Class[] {
	  			CalendarEventFirstDayOfWeekChanged.class,
	  			CalendarEventGridVisibleStatusChanged.class,
	  			CalendarEventLocaleChanged.class,
	  			CalendarEventMaxMonthsNamesLengthChanged.class,
	  			CalendarEventMaxWeekDaysNamesLengthChanged.class,
	  			CalendarEventExtraMonthDaysVisibleStatusChanged.class,
	  			CalendarEventCalendarRendererProviderChanged.class,
	  			CalendarEventSkinPropertyChanged.class,
	  			CalendarEventSkinStyleChanged.class,
	  			CalendarEventHoverEffectStatusChanged.class,
	  			CalendarEventStatusPanelVisibleValueChanged.class,
	  			CalendarEventWeekVisibleStatusChanged.class,
	  			CalendarEventSelectionModelChanged.class,
	  			CalendarEventInterRowsSpacingChanged.class,
	  			CalendarEventInterColumnsSpacingChanged.class,
	  			CalendarEventCalendarMetricsProviderChanged.class,
	  	};
	  }

	  public void processCalendarEvent(CalendarEvent calendarEvent) {
	  	if (calendarEvent instanceof CalendarEventFirstDayOfWeekChanged) {
	  		int firstDayOfWeek = ((CalendarEventFirstDayOfWeekChanged)calendarEvent).getFirstDayOfWeek();
	  		String message = "First day of week changed to:";
	  		switch(firstDayOfWeek) {
	  		case Calendar.SUNDAY: 
	  			message += "SUNDAY";
	  			break;
	  		case Calendar.MONDAY: 
	  			message += "MONDAY";
	  			break;
	  		case Calendar.TUESDAY: 
	  			message += "TUESDAY";
	  			break;
	  		case Calendar.WEDNESDAY: 
	  			message += "WEDNESDAY";
	  			break;
	  		case Calendar.THURSDAY: 
	  			message += "THURSDAY";
	  			break;
	  		case Calendar.FRIDAY: 
	  			message += "FRIDAY";
	  			break;
	  		case Calendar.SATURDAY: 
	  			message += "SATURDAY";
	  			break;
	  		};
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventGridVisibleStatusChanged) {
	  		boolean showGrid = ((CalendarEventGridVisibleStatusChanged)calendarEvent).getShowGrid();
	  		String message = "Now the calendar grid is:";
	  		if (showGrid) {
	  			message += "visible";
	  		} else {
	  			message += "not visible";
	  		}
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventExtraMonthDaysVisibleStatusChanged) {
	  		boolean extraMonthDaysVisible = ((CalendarEventExtraMonthDaysVisibleStatusChanged)calendarEvent).getExtraMonthDaysVisible();
	  		String message = "Now the extra month days are:";
	  		if (extraMonthDaysVisible) {
	  			message += "visible";
	  		} else {
	  			message += "not visible";
	  		}
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventLocaleChanged) {
	  		Locale locale = ((CalendarEventLocaleChanged)calendarEvent).getLocale();
	  		String message = "Now the calendar locale is:" + locale.getDisplayName();
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventMaxMonthsNamesLengthChanged) {
	  		int maxMonthsNamesLength = ((CalendarEventMaxMonthsNamesLengthChanged)calendarEvent).getMaxMonthsNamesLength();
	  		String message = "Now the maximum months names length is:" + maxMonthsNamesLength;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventMaxWeekDaysNamesLengthChanged) {
	  		int maxWeekDaysNamesLength = ((CalendarEventMaxWeekDaysNamesLengthChanged)calendarEvent).getMaxWeekDaysNamesLength();
	  		String message = "Now the maximum week days names length is:" + maxWeekDaysNamesLength;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventSkinPropertyChanged) {
	  		String propertyName = ((CalendarEventSkinPropertyChanged)calendarEvent).getCalendarProperty().getName();
	  		Object propertyValue = ((CalendarEventSkinPropertyChanged)calendarEvent).getPropertyValue();
	  		String message = "The \"" + propertyName + "\" property has changed to the value: " + propertyValue;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventSkinStyleChanged) {
	  		String calendarSkinStyleName = ((CalendarEventSkinStyleChanged)calendarEvent).getCalendarSkinStyle().getName();
	  		String message = "The calendar skin style has changed into:" + calendarSkinStyleName;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventCalendarRendererProviderChanged) {
	  		String text = ((CalendarEventCalendarRendererProviderChanged)calendarEvent).getCalendarRendererProvider().getName();
	  		String message = "The calendar renderer provider has changed into:" + text;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventCalendarMetricsProviderChanged) {
	  		String text = ((CalendarEventCalendarMetricsProviderChanged)calendarEvent).getCalendarMetricsProvider().getName();
	  		String message = "The calendar metrics provider has changed into:" + text;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventHoverEffectStatusChanged) {
	  		boolean showHover = ((CalendarEventHoverEffectStatusChanged)calendarEvent).getHoverEffectStatus();
	  		String message = "Now the hover effect is:";
	  		if (showHover) {
	  			message += "enabled";
	  		} else {
	  			message += "disabled";
	  		}
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventStatusPanelVisibleValueChanged) {
	  		boolean statusPanelVisible = ((CalendarEventStatusPanelVisibleValueChanged)calendarEvent).getStatusPanelVisible();
	  		String message = "Now the status panel is:";
	  		if (statusPanelVisible) {
	  			message += "visible";
	  		} else {
	  			message += "not visible";
	  		}
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventWeekVisibleStatusChanged) {
	  		boolean showWeek = ((CalendarEventWeekVisibleStatusChanged)calendarEvent).getWeekVisibleStatus();
	  		String message = "Now the week is:";
	  		if (showWeek) {
	  			message += "visible";
	  		} else {
	  			message += "not visible";
	  		}
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventSelectionModelChanged) {
	  		CalendarSelectionModel calendarSelectionModel = ((CalendarEventSelectionModelChanged)calendarEvent).getCalendarSelectionModel();
	  		String message = "The calendar selection model was changed to:" + calendarSelectionModel.getName();
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventInterRowsSpacingChanged) {
	  		int interRowsSpacing = ((CalendarEventInterRowsSpacingChanged)calendarEvent).getInterRowsSpacing();
	  		String message = "Now the inter rows spacing is:" + interRowsSpacing;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventInterColumnsSpacingChanged) {
	  		int interColumnsSpacing = ((CalendarEventInterColumnsSpacingChanged)calendarEvent).getInterColumnsSpacing();
	  		String message = "Now the inter columns spacing is:" + interColumnsSpacing;
	  		calendarDemoPanelMainFeatures.addEventHelp(message);
	  	}
	  }
  }
