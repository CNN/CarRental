package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;
import java.util.Date;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;
import com.imagine.component.calendar.selection.*;


public class DemoSelectionProperties extends DemoProperties {

	private DemoCheckboxOption demoCheckboxOptionEmptySelectionAllowed = null;
	
	private DemoOption[] demoOptionsArray = null;
	
	public DemoSelectionProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		//empty selection allowed
		
		demoCheckboxOptionEmptySelectionAllowed = new DemoCheckboxOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetEmptySelectionAllowedHelp.txt", "Empty Selection Allowed", 'A', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				boolean code = true;
				if (code) {
					code = updateEmptySelectionAllowed(calendarDemoPanelMainFeatures.getCalendarComponent(), "CalendarComponent");
				}
				if (code) {
					code = updateEmptySelectionAllowed(calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent(), "CalendarComboBox");
				}
				if (code) {
					code = updateEmptySelectionAllowed(calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent(), "TableCalendarComboBox");
				}
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// selected selection models
		
		DemoComboOption demoComboOptionSkin = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetCalendarSelectionModelHelp.txt", "Calendar Selection Model", 'M', calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarSelectionModels()) {
			public void comboOptionSelected(int selectedIndex) {
				CalendarSelectionModel calendarSelectionModel = (CalendarSelectionModel)calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarSelectionModels()[selectedIndex];
				updateSelectionModel(calendarDemoPanelMainFeatures.getCalendarComponent(), calendarSelectionModel);
				updateSelectionModel(calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent(), calendarSelectionModel);
				updateSelectionModel(calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent(), calendarSelectionModel);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		demoOptionsArray = new DemoOption[] {
				demoCheckboxOptionEmptySelectionAllowed,
				demoComboOptionSkin,
		};
		
		this.setLayout(new GridBagLayout());
		int count = 0;
		demoComboOptionSkin.initialize();
		this.add(demoComboOptionSkin.getCombo(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		this.add(demoComboOptionSkin.getLabel(), new GridBagConstraints(1, count, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		count++;
		
		demoCheckboxOptionEmptySelectionAllowed.initialize();
		this.add(demoCheckboxOptionEmptySelectionAllowed.getCheckBox(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		this.add(demoCheckboxOptionEmptySelectionAllowed.getLabel(), new GridBagConstraints(1, count, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		count++;
		
		this.add(new JPanel(), new GridBagConstraints(1, count, 2, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
	}
	
	private boolean updateEmptySelectionAllowed(CalendarComponent calendarComponent, String text) {
		try {
			calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(demoCheckboxOptionEmptySelectionAllowed.isCheckBoxSelected());
			return true;
		} catch (EmptySelectionNotAllowedException ex) {
			calendarDemoPanelMainFeatures.addEventHelp(text + "" + ex.getMessage());
			demoCheckboxOptionEmptySelectionAllowed.getCheckBox().setSelected(demoCheckboxOptionEmptySelectionAllowed.isCheckBoxSelected());
			return false;
		}
	}
	
	private void updateSelectionModel(CalendarComponent calendarComponent, CalendarSelectionModel templateCalendarSelectionModel) {
		try {
			Date[] dates = calendarComponent.getCalendarSelectionModel().getSelectedDates();
			calendarComponent.setCalendarSelectionModel((CalendarSelectionModel) templateCalendarSelectionModel.getClass().newInstance());
			calendarComponent.getCalendarSelectionModel().setSelectedDates(dates);
			calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(demoCheckboxOptionEmptySelectionAllowed.isCheckBoxSelected());
		} catch (Exception e) {
		}
	}
	
	public String getTitle() {
	  return "Selection";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoOptionsArray;
	}
}
