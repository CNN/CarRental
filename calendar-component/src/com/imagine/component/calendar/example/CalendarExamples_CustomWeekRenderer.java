package com.imagine.component.calendar.example;

import java.awt.Graphics;

import javax.swing.JFrame;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;
import com.imagine.component.calendar.renderers.CalendarRendererProviderDefault;
import com.imagine.component.calendar.renderers.weekcellrenderers.CalendarWeekCellRenderer;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.util.GUIUtilities;

public class CalendarExamples_CustomWeekRenderer {

	public static void main(String[] args) {
		CalendarComponent calendarComponent = new CalendarComponent();
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderCustomWeek());
		calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
		
		JFrame frame = new JFrame("Calendar Test");
		frame.getContentPane().add(calendarComponent);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}

class CalendarRendererProviderCustomWeek extends CalendarRendererProviderDefault {

	public CalendarRendererProviderCustomWeek() {
		this.calendarWeekCellRendererDefault = new CalendarWeekCellRendererCustom();
	}

	public String getName() {
		return "Custom";
	}
}

class CalendarWeekCellRendererCustom implements CalendarWeekCellRenderer {

	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
		if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEK_CELL_FILL_COLOR));
			g.fillRect(0, 0, calendarWeekCellInfo.getWidth(), calendarWeekCellInfo.getHeight());
		}
		
		g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR));
		g.setFont(calendarComponent.getCalendarSkin().getFontSkinProperty(CalendarSkin.WEEK_CELL_FONT));
		GUIUtilities.drawCenteredString(g, calendarWeekCellInfo.getWidth(), calendarWeekCellInfo.getHeight(), "" + (calendarWeekCellInfo.getWeekOfYear() - 1));
	}

	public String toString() {
		return "Custom";
	}
}  
