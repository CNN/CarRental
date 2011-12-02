package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;
import com.imagine.component.calendar.metrics.CalendarMetricsProvider;


public class DemoMetricsProperties extends DemoProperties {

	private DemoComboOption[] demoComboOptionsArray = null;
	
	public DemoMetricsProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		// selected calendar metrics provider
		
		DemoComboOption demoComboOptionCalendarMetricsProviders = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetCalendarMetricsProviderHelp.txt", "Calendar Metrics Provider", 'M', calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarMetricsProviders()) {
			public void comboOptionSelected(int selectedIndex) {
				CalendarMetricsProvider calendarMetricsProvider = (CalendarMetricsProvider)calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarMetricsProviders()[selectedIndex];
				updateMetricsProvider(calendarDemoPanelMainFeatures.getCalendarComponent(), calendarMetricsProvider);
				updateMetricsProvider(calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent(), calendarMetricsProvider);
				updateMetricsProvider(calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent(), calendarMetricsProvider);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		demoComboOptionsArray = new DemoComboOption[] {
				demoComboOptionCalendarMetricsProviders,
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
	
	private void updateMetricsProvider(CalendarComponent calendarComponent, CalendarMetricsProvider templateCalendarMetricsProvider) {
		try {
			calendarComponent.setCalendarMetricsProvider((CalendarMetricsProvider) templateCalendarMetricsProvider.getClass().newInstance());
		} catch (Exception e) {
		}
	}
	
	public String getTitle() {
	  return "Metrics";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoComboOptionsArray;
	}
	
}
