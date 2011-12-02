package com.imagine.component.calendar.demo.panels.eventsmanager;

import java.awt.event.MouseEvent;

import com.imagine.component.calendar.CalendarComponentListener;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.events.*;
import com.imagine.util.events.*;


public class CalendarComponentListenerDisplayEvents implements CalendarComponentListener {
	private CalendarDemoPanelEventsManager calendarDemoPanelEventsManager;	

	public CalendarComponentListenerDisplayEvents(CalendarDemoPanelEventsManager calendarDemoPanelEventsManager) {
		this.calendarDemoPanelEventsManager = calendarDemoPanelEventsManager;
	}
	
	  public Class[] getEventsListened() {
	  	return new Class[] {
	  			CalendarEventMouseAction.class,
	  			CalendarEventMouseMotionAction.class,
	  	};
	  }

	  public void processCalendarEvent(CalendarEvent calendarEvent) {
	  	if (calendarEvent instanceof CalendarEventMouseAction) {
	  		CalendarEventMouseAction calendarEventCalendarPartMouseAction = (CalendarEventMouseAction)calendarEvent;
	  		CalendarPartInfo calendarPartInfo = calendarEventCalendarPartMouseAction.getRelatedCalendarPartInfo();
	  		MouseEventType mouseEventType = calendarEventCalendarPartMouseAction.getMouseEventType();
	  		String message = "";
	  		if (calendarPartInfo instanceof CalendarTableCellInfo) {
	  			CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;
	  			message += "Type of Hovered Calendar Part: Table Cell";
	  			message += "\n(Year;Month;Day of month):(" + calendarTableCellInfo.getYear() + ";" + (calendarTableCellInfo.getMonth() + 1) + ";" + calendarTableCellInfo.getDayOfMonth() + ")";
	  			message += "\nCell (Row;Column): (" + calendarTableCellInfo.getRow() + ";" + calendarTableCellInfo.getColumn() + ")";
	  		} else if (calendarPartInfo instanceof CalendarHeaderCellInfo) {
	  			CalendarHeaderCellInfo calendarHeaderCellInfo = (CalendarHeaderCellInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Header Cell";
	  				message += "\nDay Of Week:" + calendarHeaderCellInfo.getDayOfWeek();
	  				message += "\nCell Column: " + calendarHeaderCellInfo.getColumn() + "";
	  		} else if (calendarPartInfo instanceof CalendarWeekCellInfo) {
	  			CalendarWeekCellInfo calendarWeekCellInfo = (CalendarWeekCellInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Week Cell";
	  				message += "\n(Year;Month;Week of Year):(" + calendarWeekCellInfo.getYear() + ";" + (calendarWeekCellInfo.getMonth() + 1) + ";" + calendarWeekCellInfo.getWeekOfYear() + ")";
	  				message += "\nCell Row: " + calendarWeekCellInfo.getRow() + "";
	  		} else if (calendarPartInfo instanceof CalendarStatusPanelInfo) {
	  			CalendarStatusPanelInfo calendarStatusPanelInfo = (CalendarStatusPanelInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Status Panel";
	  				message += "\n(Today Label;None Label):(" + calendarStatusPanelInfo.getTodayLabel() + ";" + calendarStatusPanelInfo.getNoneLabel() + ")";
	  		}
	  		message += "\nCell (Width;Height): (" + calendarPartInfo.getWidth() + ";" + calendarPartInfo.getHeight() + ")";
	  		message += "\n(MouseX;MouseY): (" + calendarPartInfo.getMouseX() + ";" + calendarPartInfo.getMouseY() + ")";
	  		String mouseButtonPressed = "";
	  		int modifiers = calendarPartInfo.getModifiers();
	  		if ((modifiers & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON1";
	  		}
	  		if ((modifiers & MouseEvent.BUTTON2_MASK) == MouseEvent.BUTTON2_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON2";
	  		}
	  		if ((modifiers & MouseEvent.BUTTON3_MASK) == MouseEvent.BUTTON3_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON3";
	  		}
  			if (mouseButtonPressed.length() == 0) {
  				mouseButtonPressed = "NOBUTTON";
  			}
	  		message += "\nMouse Button Pressed: " + mouseButtonPressed;
	  		message += "\nClick Count: " + calendarPartInfo.getMouseClickCount();
	  		message += "\nShift Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK); 
	  		message += "\nCtrl Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK);
	  		message += "\nAlt Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.ALT_MASK) == MouseEvent.ALT_MASK);
	  		message += "\nMouseEventType: " + mouseEventType.getName();
	  		calendarDemoPanelEventsManager.setContextSensitiveHelp(message);
	  	} else if (calendarEvent instanceof CalendarEventMouseMotionAction) {
	  		CalendarEventMouseMotionAction calendarEventCalendarPartMouseMotionAction = (CalendarEventMouseMotionAction)calendarEvent;
	  		CalendarPartInfo calendarPartInfo = calendarEventCalendarPartMouseMotionAction.getRelatedCalendarPartInfo();
	  		MouseMotionEventType mouseMotionEventType = calendarEventCalendarPartMouseMotionAction.getMouseMotionEventType();
	  		String message = "";
	  		if (calendarPartInfo instanceof CalendarTableCellInfo) {
	  			CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;
	  			message += "Type of Hovered Calendar Part: Table Cell";
	  			message += "\n(Year;Month;Day of month):(" + calendarTableCellInfo.getYear() + ";" + (calendarTableCellInfo.getMonth() + 1) + ";" + calendarTableCellInfo.getDayOfMonth() + ")";
	  			message += "\nCell (Row;Column): (" + calendarTableCellInfo.getRow() + ";" + calendarTableCellInfo.getColumn() + ")";
	  		} else if (calendarPartInfo instanceof CalendarHeaderCellInfo) {
	  			CalendarHeaderCellInfo calendarHeaderCellInfo = (CalendarHeaderCellInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Header Cell";
	  				message += "\nDay Of Week:" + calendarHeaderCellInfo.getDayOfWeek();
	  				message += "\nCell Column: " + calendarHeaderCellInfo.getColumn() + "";
	  		} else if (calendarPartInfo instanceof CalendarWeekCellInfo) {
	  			CalendarWeekCellInfo calendarWeekCellInfo = (CalendarWeekCellInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Week Cell";
	  				message += "\n(Year;Month;Week of Year):(" + calendarWeekCellInfo.getYear() + ";" + (calendarWeekCellInfo.getMonth() + 1) + ";" + calendarWeekCellInfo.getWeekOfYear() + ")";
	  				message += "\nCell Row: " + calendarWeekCellInfo.getRow() + "";
	  		} else if (calendarPartInfo instanceof CalendarStatusPanelInfo) {
	  			CalendarStatusPanelInfo calendarStatusPanelInfo = (CalendarStatusPanelInfo)calendarPartInfo;
	  				message += "Type of Hovered Calendar Part: Status Panel";
	  				message += "\n(Today Label;None Label):(" + calendarStatusPanelInfo.getTodayLabel() + ";" + calendarStatusPanelInfo.getNoneLabel() + ")";
	  		}
	  		message += "\nCell (Width;Height): (" + calendarPartInfo.getWidth() + ";" + calendarPartInfo.getHeight() + ")";
	  		message += "\nCell (MouseX;MouseY): (" + calendarPartInfo.getMouseX() + ";" + calendarPartInfo.getMouseY() + ")";
	  		String mouseButtonPressed = "";
	  		int modifiers = calendarPartInfo.getModifiers();
	  		if ((modifiers & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON1";
	  		}
	  		if ((modifiers & MouseEvent.BUTTON2_DOWN_MASK) == MouseEvent.BUTTON2_DOWN_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON2";
	  		}
	  		if ((modifiers & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK) {
	  			if (mouseButtonPressed.length() > 0) {
	  				mouseButtonPressed += "|";
	  			}
	  			mouseButtonPressed = "BUTTON3";
	  		}
  			if (mouseButtonPressed.length() == 0) {
  				mouseButtonPressed = "NOBUTTON";
  			}
	  		message += "\nMouse Button Pressed: " + mouseButtonPressed;
	  		message += "\nClick Count: " + calendarPartInfo.getMouseClickCount();
	  		message += "\nShift Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK); 
	  		message += "\nCtrl Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK);
	  		message += "\nAlt Pressed: " + ((calendarPartInfo.getModifiers() & MouseEvent.ALT_MASK) == MouseEvent.ALT_MASK);
	  		message += "\nMouseMotionEventType: " + mouseMotionEventType.getName();
	  		calendarDemoPanelEventsManager.setContextSensitiveHelp(message);
	  	}
	  }
  }
