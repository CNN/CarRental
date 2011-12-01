package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;


public class DemoBooleanProperties extends DemoProperties {

	private DemoCheckboxOption[] demoCheckboxOptionsArray = null;

	
	public DemoBooleanProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		//show grid
		
		DemoCheckboxOption demoCheckboxOptionShowGrid = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/ShowGridHelp.txt", "Show Grid", 'S', false) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		//show week
		
		DemoCheckboxOption demoCheckboxOptionShowWeek = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/ShowWeekHelp.txt" , "Show Week", 'W', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		//show hover
		
		DemoCheckboxOption demoCheckboxOptionShowHover = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/ShowHoverHelp.txt", "Show Hover", 'H', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// show extra month days
		
		DemoCheckboxOption demoCheckboxOptionShowExtraMonthDays = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/ShowExtraMonthDaysHelp.txt", "Show Extra Month Days", 'E', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		//show status panel
		
		DemoCheckboxOption demoCheckboxOptionShowStatusPanel = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/ShowStatusPanelHelp.txt", "Show Status Panel", 'P', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarDemoPanelMainFeatures.getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(this.isCheckBoxSelected()));
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		demoCheckboxOptionsArray = new DemoCheckboxOption[] {
			demoCheckboxOptionShowGrid,
			demoCheckboxOptionShowWeek,
			demoCheckboxOptionShowHover,
			demoCheckboxOptionShowExtraMonthDays,
			demoCheckboxOptionShowStatusPanel,
		};
		
		this.setLayout(new GridBagLayout());
		int count = 0;
		for (; count < demoCheckboxOptionsArray.length; count++) {
			demoCheckboxOptionsArray[count].initialize();
			this.add(demoCheckboxOptionsArray[count].getCheckBox(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
			this.add(demoCheckboxOptionsArray[count].getLabel(), new GridBagConstraints(1, count, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
    }
		this.add(new JPanel(), new GridBagConstraints(1, count, 2, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
	}

	public String getTitle() {
	  return "Boolean";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoCheckboxOptionsArray;
	}
	
}
