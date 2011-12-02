package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarNavigationController;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;


public class DemoNavigationProperties extends DemoProperties {

	private DemoComboOption[] demoComboOptionsArray = null;
	
	public DemoNavigationProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		// selected calendar navigation contoller
		
		DemoComboOption demoComboOptionCalendarNavigationControllers = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetCalendarNavigationControllerHelp.txt", "Calendar Navigation Controller", 'N', calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarNavigationControllers()) {
			public void comboOptionSelected(int selectedIndex) {
				Object currentItem = calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarNavigationControllers()[selectedIndex];
				CalendarNavigationController calendarNavigationController = null;
				if (currentItem instanceof CalendarNavigationController) {
					calendarNavigationController = (CalendarNavigationController)currentItem;
				} else {
					calendarNavigationController = null;
				}
				updateNavigationController(calendarDemoPanelMainFeatures.getCalendarComponent(), calendarNavigationController);
				updateNavigationController(calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent(), calendarNavigationController);
				updateNavigationController(calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent(), calendarNavigationController);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		demoComboOptionsArray = new DemoComboOption[] {
				demoComboOptionCalendarNavigationControllers,
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
	
	private void updateNavigationController(CalendarComponent calendarComponent, CalendarNavigationController templateCalendarNavigationController) {
		try {
			if (templateCalendarNavigationController == null) {
				calendarComponent.getCalendarView().setNavigationController(null);
			} else {
				calendarComponent.getCalendarView().setNavigationController((CalendarNavigationController) templateCalendarNavigationController.getClass().newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
	  return "Navigation Controller";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoComboOptionsArray;
	}
	
}
