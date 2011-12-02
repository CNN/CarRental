package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.events.*;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.WindowUtilities;
import com.imagine.util.events.*;

public class VisualEntitiesManager {
	
	private CalendarDemoPanelEventsManager calendarDemoPanelEventsManager;
	protected Vector visualEntities = new Vector();
	private CalendarTableCellInfo calendarTableCellInfo = null;
	
	private Point point = new Point();
	private Dimension size = new Dimension();
	int top = 10;
	int left = 20;
	int interVisualEntitiesSpacing = 3;
	private String displayedTextComputer = "AAAAA";
	private int displayedTextLength = displayedTextComputer.length();
	private JPopupMenu popupMenu = null;
	private VisualEntity currentVisualEntity = null;
	private String key = "";
	
	public VisualEntitiesManager(CalendarDemoPanelEventsManager calendarDemoPanelEventsManager, String key) {
		this.calendarDemoPanelEventsManager = calendarDemoPanelEventsManager;
		this.key = key;
	}
	
	public void addVisualEntity(VisualEntity visualEntity) {
		visualEntities.add(visualEntity);
		setVisualEntitiesBounds();
	}
	
	public boolean removeVisualEntity(VisualEntity visualEntity) {
		boolean removed =  visualEntities.remove(visualEntity);
		setVisualEntitiesBounds();
		if (visualEntities.size() == 0) {
			calendarDemoPanelEventsManager.getVisualEntitiesManager(key);
		}
		return removed;
	}
	
	private void setVisualEntitiesBounds() {
		FontMetrics fontMetrics = getFontMetrics();
		int width = fontMetrics.stringWidth(displayedTextComputer) + 4;
		int height = fontMetrics.getAscent() + 3;
		size.width = width;
		size.height = height;
		for (int i = 0, count = visualEntities.size(); i < count; i++) {
			VisualEntity visualEntity = (VisualEntity)visualEntities.elementAt(i);
			point.x = left;
			point.y = top + (interVisualEntitiesSpacing + height) * i;
			visualEntity.setLocation(point);
			visualEntity.setSize(size);
		}
	}
	
	public void dispatchMouseEvent(CalendarEventMouseAction calendarEventCalendarPartMouseAction) {
		MouseEventType mouseEventType = calendarEventCalendarPartMouseAction.getMouseEventType();
		calendarTableCellInfo = (CalendarTableCellInfo)calendarEventCalendarPartMouseAction.getRelatedCalendarPartInfo();
		
		int mouseX = calendarTableCellInfo.getMouseX();
		int mouseY = calendarTableCellInfo.getMouseY();
		
		VisualEntity visualEntity = this.getVisualEntityAt(mouseX, mouseY);
		if (visualEntity != null) {
			if (calendarEventCalendarPartMouseAction.getMouseEventType() == MouseEventType.MOUSE_RELEASED) {
				int modifiers = calendarTableCellInfo.getModifiers();
				if ((modifiers & MouseEvent.BUTTON3_MASK) == MouseEvent.BUTTON3_MASK) {
					// The right button is clicked.
					showPopUpMenu(visualEntity);
					calendarEventCalendarPartMouseAction.consume();
				}
			}
		}
	}
	
	public void dispatchMouseMotionEvent(CalendarEventMouseMotionAction calendarEventCalendarPartMouseMotionAction) {
		MouseMotionEventType mouseMotionEventType = calendarEventCalendarPartMouseMotionAction.getMouseMotionEventType();
		calendarTableCellInfo = (CalendarTableCellInfo)calendarEventCalendarPartMouseMotionAction.getRelatedCalendarPartInfo();
		
//		calendarEventCalendarPartMouseMotionAction.consume();
	}
	
	public CalendarDemoPanelEventsManager getCalendarDemoPanelEventsManager() {
		return calendarDemoPanelEventsManager;
	}
	
	
	public Font getFont() {
		CalendarComponent calendarComponent = calendarDemoPanelEventsManager.getCalendarComponentEventsManager();
		Font cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.CELL_FONT);
		if (calendarTableCellInfo != null) {
			if (calendarTableCellInfo.getMonthType() != CalendarMonthType.CURRENT_MONTH) {
				cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_FONT);
			} else if (calendarTableCellInfo.isFromWeekend()) {
				cellFont = calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEKEND_CELL_FONT);
			}
		}
		return cellFont;
	}
	
	
	public FontMetrics getFontMetrics() {
		return calendarDemoPanelEventsManager.getFontMetrics(getFont());
	}
	
	public CalendarTableCellInfo getCalendarTableCellInfo() {
		return calendarTableCellInfo;
	}
	
	/**
	 * Paint the VisualEntities on the specified Graphics object using the current set color.
	 * @param g The Graphics on which the paint will be performed.
	 */
	public void paint(Graphics g) {
		for (int i = 0, count = visualEntities.size(); i < count; i++) {
			VisualEntity visualEntity = (VisualEntity)visualEntities.elementAt(i);
			visualEntity.paint(g);
		}
	}
	
	public int getDisplayedTextLength() {
		return displayedTextLength;
	}
	
	public VisualEntity getVisualEntityAt(int x, int y) {
		VisualEntity visualEntity = null;
		for (int i = 0, count = visualEntities.size(); visualEntity  == null && i < count; i++) {
			VisualEntity currentVisualEntity = (VisualEntity)visualEntities.elementAt(i);
			if (currentVisualEntity.containsPoint(x, y)) {
				visualEntity = currentVisualEntity;
			}
		}
		return visualEntity;
	}
	
	private void showPopUpMenu(VisualEntity visualEntity) {
		currentVisualEntity = visualEntity;
		JComponent parent = calendarDemoPanelEventsManager.getCalendarComponentEventsManager();
		if (popupMenu == null) {
			popupMenu = new JPopupMenu("Options");
			parent.add(popupMenu);
			JMenuItem editMenuItem = new JMenuItem("Edit Event");
			editMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					popupMenu.setVisible(false);
					EditEventDialog editEventDialog = calendarDemoPanelEventsManager.getEditEventDialog();
					JDialog dialog = editEventDialog.getDialog();
					editEventDialog.setCalendarTableCellInfo(calendarTableCellInfo);
					
					dialog.setTitle("Edit Event");
					VisualEntityDataField visualEntityDataField = (VisualEntityDataField)currentVisualEntity;
					editEventDialog.reset(EditEventDialog.EDIT_EVENT, visualEntityDataField);
					
					WindowUtilities.centerWindowAroundPoint(dialog, point);
					editEventDialog.setVisible(true);
				}
			});
			popupMenu.add(editMenuItem);
			
			JMenuItem deleteMenuItem = new JMenuItem("Delete Event");
			deleteMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					popupMenu.setVisible(false);
					VisualEntitiesManager.this.removeVisualEntity(currentVisualEntity);
				}
			});
			popupMenu.add(deleteMenuItem);
		}
		if (calendarTableCellInfo != null) {
			point.x = calendarTableCellInfo.getMouseX() + calendarTableCellInfo.getLocationOnScreenX();
			point.y = calendarTableCellInfo.getMouseY() + calendarTableCellInfo.getLocationOnScreenY();
		}
		popupMenu.setLocation(point);
		popupMenu.updateUI();
		popupMenu.setVisible(true);
	}
	
	public String getKey() {
		return key;
	}
	
	public void setHoveredVisualEntity(VisualEntityDataField hoveredVisualEntity) {
		calendarDemoPanelEventsManager.setHoveredVisualEntity(hoveredVisualEntity);
	}

	public Vector getVisualEntities() {
		return visualEntities;
	}
}
