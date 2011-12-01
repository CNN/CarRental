package com.imagine.component.calendar.demo.panels.monthlycalendar;

import java.awt.*;
import java.util.Date;

import javax.swing.JPanel;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.demo.CalendarDemoController;
import com.imagine.component.calendar.demo.panels.CalendarDemoPanel;
import com.imagine.component.calendar.renderers.CalendarRendererProviderTransparent;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarDemoPanelMonthlyCalendar extends CalendarDemoPanel {

	private CalendarComponent calendarComponent = new CalendarComponent();

	private MonthlyImagePanel monthlyImagePanel = new MonthlyImagePanel();


	public CalendarDemoPanelMonthlyCalendar(CalendarDemoController calendarDemoController) {
		super(calendarDemoController);

		initialize();
	}


	private void initialize() {

		calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderTransparent());
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.CHANGE_CURRENT_DATE_WHEN_YEAR_OR_MONTH_CHANGES, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(false);

		calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.CELL_TEXT_COLOR, new Color(255, 255, 255));
		calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.WEEK_CELL_TEXT_COLOR, new Color(255, 255, 255));
		calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.HEADER_CELL_TEXT_COLOR, new Color(255, 255, 255));
		calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.WEEKEND_CELL_TEXT_COLOR, new Color(255, 255, 255));
		calendarComponent.getCalendarSkin().setSkinProperty(CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR, new Color(255, 255, 255));

		monthlyImagePanel.setDate(calendarComponent.getDate());

		calendarComponent.addDateListener(new DateListener() {
			public void dateChanged(Date date) {
				if (date == null) {
					return;
				}
				monthlyImagePanel.setDate(date);
			}
		});
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		panel1.setOpaque(false);
		panel2.setOpaque(false);

		monthlyImagePanel.setLayout(new GridBagLayout());
		monthlyImagePanel.add(calendarComponent, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		monthlyImagePanel.add(panel1, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		monthlyImagePanel.add(panel2, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		this.setLayout(new GridBagLayout());
		this.add(monthlyImagePanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(new JPanel(), new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		this.add(new JPanel(), new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	public String getTitle() {
		return "Monthly Calendar";
	}
}

