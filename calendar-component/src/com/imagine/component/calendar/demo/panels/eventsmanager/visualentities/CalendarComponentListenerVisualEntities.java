package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.CalendarComponentListener;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.events.*;
import com.imagine.component.calendar.util.WindowUtilities;
import com.imagine.util.events.MouseEventType;


public class CalendarComponentListenerVisualEntities implements CalendarComponentListener {
	private CalendarDemoPanelEventsManager calendarDemoPanelEventsManager;	
	private JPopupMenu popupMenu = null;
	private Point point = new Point();
	private CalendarTableCellInfo calendarTableCellInfo = null;

	public CalendarComponentListenerVisualEntities(CalendarDemoPanelEventsManager calendarDemoPanelEventsManager) {
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
	  		if (calendarPartInfo instanceof CalendarTableCellInfo) {
	  			calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;
	  			VisualEntitiesManager visualEntitiesManager  = calendarDemoPanelEventsManager.getVisualEntitiesManager(calendarDemoPanelEventsManager.calculateKey(calendarTableCellInfo));
	  			boolean eventDispatched = false;
	  			if (visualEntitiesManager != null) {
	  				visualEntitiesManager.dispatchMouseEvent(calendarEventCalendarPartMouseAction);
	  				if (calendarEventCalendarPartMouseAction.isConsumed()) {
	  					eventDispatched = true;
	  				}
	  			}
	  			if (!eventDispatched) {
	  				if (calendarEventCalendarPartMouseAction.getMouseEventType() == MouseEventType.MOUSE_RELEASED) {
	  		  		int modifiers = calendarPartInfo.getModifiers();
	  		  		if ((modifiers & MouseEvent.BUTTON3_MASK) == MouseEvent.BUTTON3_MASK) {
	  		  			// The right button is clicked.
	  		  			showPopUpMenu();
	  		  			calendarEventCalendarPartMouseAction.consume();
	  		  		}
	  				}
	  			}
	  		}
	  	} else if (calendarEvent instanceof CalendarEventMouseMotionAction) {
	  		CalendarEventMouseMotionAction calendarEventCalendarPartMouseMotionAction = (CalendarEventMouseMotionAction)calendarEvent;
	  		CalendarPartInfo calendarPartInfo = calendarEventCalendarPartMouseMotionAction.getRelatedCalendarPartInfo();
	  		if (calendarPartInfo instanceof CalendarTableCellInfo) {
	  			calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;
	  			VisualEntitiesManager visualEntitiesManager  = calendarDemoPanelEventsManager.getVisualEntitiesManager(calendarDemoPanelEventsManager.calculateKey(calendarTableCellInfo));
	  			if (visualEntitiesManager != null) {
	  				visualEntitiesManager.dispatchMouseMotionEvent(calendarEventCalendarPartMouseMotionAction);
	  			}
	  		}
	  	}
	  }

		private void showPopUpMenu() {
			JComponent parent = calendarDemoPanelEventsManager.getCalendarComponentEventsManager();
			if (popupMenu == null) {
				popupMenu = new JPopupMenu("Options");
				parent.add(popupMenu);
				JMenuItem menuItem = new JMenuItem("Add Event");
				menuItem.addActionListener(new ActionListener() {
					
			    /**
			     * Invoked when an action occurs.
			     */
			    public void actionPerformed(ActionEvent e) {
			    	popupMenu.setVisible(false);
						VisualEntitiesManager visualEntitiesManager = calendarDemoPanelEventsManager.getVisualEntitiesManager(calendarDemoPanelEventsManager.calculateKey(calendarTableCellInfo)); 
						if (visualEntitiesManager != null && visualEntitiesManager.visualEntities.size() >= 3) {
							JOptionPane.showMessageDialog(calendarDemoPanelEventsManager, "A maximum of 3 events can be added!");
						} else {
							EditEventDialog editEventDialog = calendarDemoPanelEventsManager.getEditEventDialog();
							JDialog dialog = editEventDialog.getDialog();
							editEventDialog.setCalendarTableCellInfo(calendarTableCellInfo);
							
							dialog.setTitle("Add Event");
							editEventDialog.reset(EditEventDialog.ADD_EVENT, null);
							WindowUtilities.centerWindowAroundPoint(dialog, point);
							editEventDialog.setVisible(true);
						}
			    }
			   });
				popupMenu.add(menuItem);
			}
			point.x = calendarTableCellInfo.getMouseX() + calendarTableCellInfo.getLocationOnScreenX();
			point.y = calendarTableCellInfo.getMouseY() + calendarTableCellInfo.getLocationOnScreenY();
			popupMenu.setLocation(point);
			popupMenu.updateUI();
			popupMenu.setVisible(true);
    }
  }
