package com.imagine.component.calendar.renderers.statuspanelrenderers;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarStatusPanelInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the default status panel renderer. 
 */
public class CalendarStatusPanelRendererDefault implements CalendarStatusPanelRenderer {
	
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
		
		int x = getLeftPadding();
		
		if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_HOVER).booleanValue() && isTodayLabelHovered(g, calendarComponent, calendarStatusPanelInfo)) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR));
		} else {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR));
		}
		g.drawString(calendarStatusPanelInfo.getTodayLabel(), x, y);
		
		int currentWidth = fontMetrics.stringWidth(calendarStatusPanelInfo.getNoneLabel());
		x = calendarStatusPanelInfo.getWidth() - currentWidth - getRightPadding();
		if (x < 0) {
			x = 0;
		}
		if (calendarComponent.getBooleanCalendarProperty(CalendarComponent.SHOW_HOVER).booleanValue() && isNoneLabelHovered(g, calendarComponent, calendarStatusPanelInfo)) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR));
		} else {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR));
		}
		g.drawString(calendarStatusPanelInfo.getNoneLabel(), x, y);
	}
	
	/**
	 * @inheritDoc
	 */
	public boolean isTodayLabelHovered(Graphics g, CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
		int mouseX = calendarStatusPanelInfo.getMouseX();
		int mouseY = calendarStatusPanelInfo.getMouseY();
		return getLeftPadding() <= mouseX && mouseX < getLeftPadding() + g.getFontMetrics().stringWidth(calendarStatusPanelInfo.getTodayLabel()) && 0 <= mouseY && mouseY < calendarStatusPanelInfo.getHeight();
	}
	
	/**
	 * @inheritDoc
	 */
	public boolean isNoneLabelHovered(Graphics g, CalendarComponent calendarComponent, CalendarStatusPanelInfo calendarStatusPanelInfo) {
		int mouseX = calendarStatusPanelInfo.getMouseX();
		int mouseY = calendarStatusPanelInfo.getMouseY();
		return calendarStatusPanelInfo.getWidth() - g.getFontMetrics().stringWidth(calendarStatusPanelInfo.getNoneLabel()) - getRightPadding() <= mouseX && mouseX < calendarStatusPanelInfo.getWidth() && 0 <= mouseY && mouseY < calendarStatusPanelInfo.getHeight();
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