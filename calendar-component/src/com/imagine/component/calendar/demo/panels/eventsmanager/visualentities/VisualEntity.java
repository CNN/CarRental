package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.*;

import com.imagine.component.calendar.data.CalendarTableCellInfo;

public abstract class VisualEntity {
	
	Point location = new Point(0, 0);
	Dimension size = new Dimension(0, 0);
	protected boolean selected = false;
	protected VisualEntitiesManager visualEntitiesManager = null;
	
	
	public VisualEntity(VisualEntitiesManager visualEntitiesManager) {
		this.visualEntitiesManager = visualEntitiesManager;
	}
	
	public int getMouseX() {
		CalendarTableCellInfo calendarTableCellInfo  = visualEntitiesManager.getCalendarTableCellInfo();
		if (calendarTableCellInfo != null) {
			return calendarTableCellInfo.getMouseX() - location.x;
		}
		return -1;
	}

	public int getMouseY() {
		CalendarTableCellInfo calendarTableCellInfo  = visualEntitiesManager.getCalendarTableCellInfo();
		if (calendarTableCellInfo != null) {
			return calendarTableCellInfo.getMouseY() - location.y;
		}
		return -1;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location.x = location.x;
		this.location.y = location.y;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size.width = size.width;
		this.size.height = size.height;
	}
	
	public boolean containsPoint(Point point) {
		return (location.x <= point.x && point.x <= location.x + size.width) && (location.y <= point.y && point.y <= location.y + size.height);
	}
	
	public boolean containsPoint(int x, int y) {
		return (location.x <= x && x <= location.x + size.width) && (location.y <= y && y <= location.y + size.height);
	}
	
	public boolean isHovered() {
		CalendarTableCellInfo calendarTableCellInfo  = visualEntitiesManager.getCalendarTableCellInfo();
		if (calendarTableCellInfo != null) {
			return this.containsPoint(calendarTableCellInfo.getMouseX(), calendarTableCellInfo.getMouseY());
		}
		return false;
	}
	
	public Point relativePosition(Point point) {
		return new Point(point.x - location.x, point.y - location.y);
	}
	
	public int relativePositionX(int x) {
		return x - location.x;
	}
	
	public int relativePositionY(int y) {
		return y - location.y;
	}
	
  public Font getFont() {
  	return visualEntitiesManager.getFont();
  }
  
  public FontMetrics getFontMetrics() {
  	return visualEntitiesManager.getFontMetrics();
  }
  
  public int getPreferredStringWidth(String value) {
  	return getFontMetrics().stringWidth(value);
  }
  
  public int getPreferredStringHeight(String value) {
  	return getFontMetrics().getAscent();
  }

	public boolean isSelected() {
  	return selected;
  }

	public void setSelected(boolean selected) {
  	this.selected = selected;
  }

	public abstract void paint(Graphics g);
}
