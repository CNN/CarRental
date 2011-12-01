package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;
import java.util.Locale;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;
import com.imagine.component.calendar.util.CalendarUtils;


public class DemoOtherProperties extends DemoProperties {

	private String[] weekDays = CalendarUtils.getDaysNames(Locale.ENGLISH, CalendarUtils.getWeekDaysCodes()[0]);
	
	private Integer[] maxWeekDaysNamesLength = {
			new Integer(1),
			new Integer(2),
			new Integer(3),
			new Integer(9),
	};
	
	private Integer[] maxMonthsNamesLength = {
			new Integer(15),
			new Integer(5),
			new Integer(3),
			new Integer(1),
	};
	
	private Integer[] interRowsSpacing = {
			new Integer(0),
			new Integer(1),
			new Integer(2),
	};
	
	private Integer[] interColumnsSpacing = {
			new Integer(0),
			new Integer(1),
			new Integer(2),
	};
	
	private DemoComboOption[] demoComboOptionsArray = null;
	
	public DemoOtherProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		// First Day Of Week.
		
		DemoComboOption demoComboOptionFirstDayOfWeek = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetFirstDayOfWeekHelp.txt", "First Day Of Week", 'F', weekDays) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(CalendarUtils.getWeekDaysCodes()[selectedIndex]));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(CalendarUtils.getWeekDaysCodes()[selectedIndex]));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(CalendarUtils.getWeekDaysCodes()[selectedIndex]));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// Max Week Days Names Length
		
		DemoComboOption demoComboOptionMaxWeekDaysNamesLength = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetMaxWeekDaysLengthHelp.txt", "Max Week Days Length", 'M', maxWeekDaysNamesLength) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_WEEK_DAYS_NAMES_LENGTH, maxWeekDaysNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_WEEK_DAYS_NAMES_LENGTH, maxWeekDaysNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_WEEK_DAYS_NAMES_LENGTH, maxWeekDaysNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// Max Months Names Length
		
		DemoComboOption demoComboOptionMaxMonthsNamesLength = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetMaxMonthsNamesLengthHelp.txt", "Max Months Names Length", 'M', maxMonthsNamesLength) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_MONTHS_NAMES_LENGTH, maxMonthsNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_MONTHS_NAMES_LENGTH, maxMonthsNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.MAX_MONTHS_NAMES_LENGTH, maxMonthsNamesLength[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// Inter rows spacing
		
		DemoComboOption demoComboOptionInterRowsSpacing = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetInterRowsSpacingHelp.txt", "Inter Rows Spacing", 'R', interRowsSpacing, 1) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_ROWS_SPACING, interRowsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_ROWS_SPACING, interRowsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_ROWS_SPACING, interRowsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// Inter columns spacing
		
		DemoComboOption demoComboOptionInterColumnsSpacing = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetInterColumnsSpacingHelp.txt", "Inter Columns Spacing", 'C', interColumnsSpacing, 1) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_COLUMNS_SPACING, interColumnsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_COLUMNS_SPACING, interColumnsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.INTER_COLUMNS_SPACING, interColumnsSpacing[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// Locale
		Locale currentLocale = calendarDemoPanelMainFeatures.getCalendarComponent().getLocaleCalendarProperty(CalendarComponent.LOCALE);
		Locale[] locales = calendarDemoPanelMainFeatures.getCalendarDemoController().getLocales();
		int initialSelectedIndex = 0;
		for (int i = 0; i < locales.length; i++) {
	    if (locales[i].equals(currentLocale)) {
	    	initialSelectedIndex = i;
	    }
    }
		DemoComboOption demoComboOptionLocale = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetLocaleHelp.txt", "Locale", 'L', locales, initialSelectedIndex) {
			public void comboOptionSelected(int selectedIndex) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.LOCALE, calendarDemoPanelMainFeatures.getCalendarDemoController().getLocales()[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.LOCALE, calendarDemoPanelMainFeatures.getCalendarDemoController().getLocales()[selectedIndex]);
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.LOCALE, calendarDemoPanelMainFeatures.getCalendarDemoController().getLocales()[selectedIndex]);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
			
			public String getDisplayString(Object value) {
		    Locale locale = (Locale) value;
		    return locale.getDisplayName();
			}
		};
		
		demoComboOptionsArray = new DemoComboOption[] {
				demoComboOptionFirstDayOfWeek,
				demoComboOptionMaxWeekDaysNamesLength,
				demoComboOptionMaxMonthsNamesLength,
				demoComboOptionInterRowsSpacing,
				demoComboOptionInterColumnsSpacing,
				demoComboOptionLocale,
		};
		
		this.setLayout(new GridBagLayout());
		int count = 0;
		for (; count < demoComboOptionsArray.length; count++) {
			demoComboOptionsArray[count].initialize();
			this.add(demoComboOptionsArray[count].getCombo(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
			this.add(demoComboOptionsArray[count].getLabel(), new GridBagConstraints(1, count, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		}
		this.add(new JPanel(), new GridBagConstraints(1, count, 2, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
	}
	
	public String getTitle() {
	  return "Other";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoComboOptionsArray;
	}
	
}
