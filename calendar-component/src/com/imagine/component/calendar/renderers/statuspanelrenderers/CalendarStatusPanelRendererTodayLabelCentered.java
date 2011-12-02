package com.imagine.component.calendar.renderers.statuspanelrenderers;

import java.awt.FontMetrics;
import java.awt.Graphics;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarStatusPanelInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines a status panel renderer which displays the today label centered. 
 */
public class CalendarStatusPanelRendererTodayLabelCentered implements CalendarStatusPanelRenderer {
	
	private int leftPadding = 5;
	
	private int rightPadding = 5;
	
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_FILL_COLOR));
			g.fillRect(0, 0, calendarStatusPanelInfo.getWidth(), calendarStatusPanelInfo.getHeight());
		}
		
		g.setFont(calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.STATUS_PANEL_FONT));
		FontMetrics fontMetrics = g.getFontMetrics();
		int currentHeight = fontMetrics.getAscent() - 3;
		int height = calendarStatusPanelInfo.getHeight();
		
		int y = (height - currentHeight) / 2;
		if (y < 0) {
			y = 0;
		}
		y = height - y;
		
		int todayLabelWidth = g.getFontMetrics().stringWidth(calendarStatusPanelInfo.getTodayLabel());
		int statusPanelWidth = calendarStatusPanelInfo.getWidth();
		int x = (statusPanelWidth - todayLabelWidth + leftPadding - rightPadding) / 2;
		
		if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_HOVER).booleanValue() && isTodayLabelHovered(g, calendarComponent, calendarStatusPanelInfo)) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR));
		} else {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR));
		}
		g.drawString(calendarStatusPanelInfo.getTodayLabel(), x, y);
		
	}
	
	/**
	 * @inheritDoc
	 */
	public boolean isTodayLabelHovered(Graphics g, CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
		int mouseX = calendarStatusPanelInfo.getMouseX();
		int mouseY = calendarStatusPanelInfo.getMouseY();
		int todayLabelWidth = g.getFontMetrics().stringWidth(calendarStatusPanelInfo.getTodayLabel());
		int statusPanelWidth = calendarStatusPanelInfo.getWidth();
		int leftEdgeWidth = (statusPanelWidth - todayLabelWidth + leftPadding - rightPadding) / 2;
		int rightEdgeWidth = leftEdgeWidth + todayLabelWidth;
		
		return (leftEdgeWidth <= mouseX && mouseX < rightEdgeWidth) && (0 <= mouseY && mouseY < calendarStatusPanelInfo.getHeight());
	}
	
	/**
	 * @inheritDoc
	 */
	public boolean isNoneLabelHovered(Graphics g, CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
		return false;
	}
	
	/**
	 * Get the left padding. This is the distance between the left side of panel and the start of painting the "Today" label.
	 * 
	 * @return Returns the left padding. This is the distance between the left side of panel and the start of painting the "Today" label.
	 */
	public int getLeftPadding() {
		return leftPadding;
	}

	/**
	 * Set the left padding. This is the distance between the left side of panel and the start of painting the "Today" label.
	 * 
	 * @param leftPadding The left padding. This is the distance between the left side of panel and the start of painting the "Today" label.
	 */
	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}

	/**
	 * Get the right padding. This is the distance between the right side of panel and the end of painting the "None" label.
	 * 
	 * @return Returns the right padding. This is the distance between the right side of panel and the end of painting the "None" label.
	 */
	public int getRightPadding() {
		return rightPadding;
	}

	/**
	 * Set the right padding. This is the distance between the right side of panel and the end of painting the "None" label.
	 * 
	 * @param rightPadding The right padding. This is the distance between the right side of panel and the end of painting the "None" label.
	 */
	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}
}