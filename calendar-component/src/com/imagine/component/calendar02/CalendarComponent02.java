package com.imagine.component.calendar02;

import java.awt.Color;
import java.awt.Graphics;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarHeaderCellInfo;
import com.imagine.component.calendar.data.CalendarNavigationControllerSimpleNavigation;
import com.imagine.component.calendar.data.CalendarWeekCellInfo;
import com.imagine.component.calendar.renderers.CalendarRendererProviderDefault;
import com.imagine.component.calendar.renderers.headercellrenderers.CalendarHeaderCellRendererDefault;
import com.imagine.component.calendar.renderers.weekcellrenderers.CalendarWeekCellRendererDefault;
import com.imagine.component.calendar.skins.CalendarSkin;


/**
 * This is a class that extends the CalendarComponent class and customize it to resemble the Microsoft Look and Feel.
 * 
 */
public class CalendarComponent02 extends CalendarComponent {

	/**
	 * Default constructor.
	 */
	public CalendarComponent02() {
		this.getCalendarSelectionModel().setEmptySelectionAllowed(true);
		this.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		this.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
		this.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(false));
		this.setCalendarProperty(CalendarComponent.INTER_ROWS_SPACING, new Integer(0));
		this.setCalendarProperty(CalendarComponent.INTER_COLUMNS_SPACING, new Integer(0));
		this.setCalendarProperty(CalendarComponent.MAX_WEEK_DAYS_NAMES_LENGTH, new Integer(3));

		CalendarNavigationControllerSimpleNavigation calendarNavigationController = new CalendarNavigationControllerSimpleNavigation();
		this.getCalendarView().setNavigationController(calendarNavigationController);

		calendarNavigationController.getBackgroundPanel().setOpaque(true);
		calendarNavigationController.getBackgroundPanel().setBackground(new Color(0x00, 0x54, 0xe3));
		calendarNavigationController.getDateLabel().setForeground(new Color(0xff, 0xff, 0xff));
		
		this.setCalendarRendererProvider(new CalendarRendererProviderHeaderAndWeekLines());

		CalendarSkin calendarSkin = this.getCalendarSkin();

		calendarSkin.setSkinProperty(CalendarSkin.CELL_FILL_COLOR, new Color(0xff, 0xff, 0xff));
		calendarSkin.setSkinProperty(CalendarSkin.CELL_TEXT_COLOR, new Color(0x00, 0x00, 0x00));

		calendarSkin.setSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR, new Color(0x00, 0x00, 0x00));
		calendarSkin.setSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR, new Color(0xac, 0xa8, 0x99));


		calendarSkin.setSkinProperty(CalendarSkin.HEADER_CELL_FILL_COLOR, new Color(0xff, 0xff, 0xff));
		calendarSkin.setSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR, new Color(0x00, 0x54, 0xe3));

		calendarSkin.setSkinProperty(CalendarSkin.WEEK_CELL_FILL_COLOR, new Color(0xff, 0xff, 0xff));
		calendarSkin.setSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR, new Color(0x00, 0x54, 0xe3));

		calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_FILL_COLOR, new Color(0xff, 0xff, 0xff));
		calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_TEXT_COLOR, new Color(0x00, 0x00, 0x00));
		calendarSkin.setSkinProperty(CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR, new Color(0x40, 0x40, 0x40));
	}

	public class CalendarRendererProviderHeaderAndWeekLines extends CalendarRendererProviderDefault {

		public CalendarRendererProviderHeaderAndWeekLines() {
			this.calendarHeaderCellRendererDefault = new CalendarHeaderCellRendererLines();
			this.calendarWeekCellRendererDefault = new CalendarWeekCellRendererLines();
		}

		/**
		 * @inheritDoc
		 */
		public String getName() {
			return "Custom";
		}
	}

	public class CalendarHeaderCellRendererLines extends CalendarHeaderCellRendererDefault {

		public void paint(Graphics g, CalendarComponent calendarComponent, CalendarHeaderCellInfo calendarHeaderCellInfo) {
			super.paint(g, calendarComponent, calendarHeaderCellInfo);
			
			int width = calendarHeaderCellInfo.getWidth();
			int height = calendarHeaderCellInfo.getHeight();
			int x1 = 0;
			int y1 = height - 1;
			int x2 = width;
			int y2 = height - 1;
			
			int column = calendarHeaderCellInfo.getColumn();
			
			if (column == 0) {
				x1 += 5;
			} else if (column == 6) {
				x2 -= 5;
			}
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR));
			g.drawLine(x1, y1, x2, y2);
		}

	}

	public class CalendarWeekCellRendererLines extends	CalendarWeekCellRendererDefault {

		public void paint(Graphics g, CalendarComponent calendarComponent, CalendarWeekCellInfo calendarWeekCellInfo) {
			super.paint(g, calendarComponent, calendarWeekCellInfo);
			
			int width = calendarWeekCellInfo.getWidth();
			int height = calendarWeekCellInfo.getHeight();
			int x1 = width - 1;
			int y1 = 0;
			int x2 = width - 1;
			int y2 = height;
			
			int row = calendarWeekCellInfo.getRow();
			
			if (row == 0) {
				y1 += 5;
			} else if (row == 5) {
				y1 -= 5;
			}
			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR));
			g.drawLine(x1, y1, x2, y2);
		}

	}

}