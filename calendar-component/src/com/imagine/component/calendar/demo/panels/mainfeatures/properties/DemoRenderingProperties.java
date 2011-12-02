package com.imagine.component.calendar.demo.panels.mainfeatures.properties;

import java.awt.*;

import javax.swing.JPanel;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.*;
import com.imagine.component.calendar.renderers.CalendarRendererProvider;


public class DemoRenderingProperties extends DemoProperties {

	private DemoComboOption[] demoComboOptionsArray = null;
	
	public DemoRenderingProperties(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures) {
		super(calendarDemoPanelMainFeatures);
  }
	
	public void initialize() {
		// selected calendar renderer provider
		
		DemoComboOption demoComboOptionCalendarRendererProviders = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetCalendarRendererProviderHelp.txt", "Calendar Renderer Provider", 'R', calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarRendererProviders()) {
			public void comboOptionSelected(int selectedIndex) {
				CalendarRendererProvider calendarRendererProvider = (CalendarRendererProvider)calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarRendererProviders()[selectedIndex];
				updateRendererProvider(calendarDemoPanelMainFeatures.getCalendarComponent(), calendarRendererProvider);
				updateRendererProvider(calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent(), calendarRendererProvider);
				updateRendererProvider(calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent(), calendarRendererProvider);
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		// skins
		
		DemoComboOption demoComboOptionSkin = new DemoComboOption(this.calendarDemoPanelMainFeatures, "/resources/help/SetSkinStyleHelp.txt", "Skin Style", 'y', calendarDemoPanelMainFeatures.getCalendarDemoController().getSkinStyles()) {
			public void comboOptionSelected(int selectedIndex) {
				try {
					calendarDemoPanelMainFeatures.getCalendarComponent().getCalendarSkin().applyCalendarSkinStyle(calendarDemoPanelMainFeatures.getCalendarDemoController().getSkinStyles()[selectedIndex]);
					calendarDemoPanelMainFeatures.getCalendarComboBox().getCalendarComponent().getCalendarSkin().applyCalendarSkinStyle(calendarDemoPanelMainFeatures.getCalendarDemoController().getSkinStyles()[selectedIndex]);
					calendarDemoPanelMainFeatures.getCustomTableCalendarComboBox().getCalendarComponent().getCalendarSkin().applyCalendarSkinStyle(calendarDemoPanelMainFeatures.getCalendarDemoController().getSkinStyles()[selectedIndex]);
				} catch (Exception e) {
				}
				calendarDemoPanelMainFeatures.getCalendarDemoController().getCalendarDemo().getFrame().pack();
			}
		};
		
		demoComboOptionsArray = new DemoComboOption[] {
				demoComboOptionCalendarRendererProviders,
				demoComboOptionSkin
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
	
	private void updateRendererProvider(CalendarComponent calendarComponent, CalendarRendererProvider templateCalendarRendererProvider) {
		try {
			calendarComponent.setCalendarRendererProvider((CalendarRendererProvider) templateCalendarRendererProvider.getClass().newInstance());
		} catch (Exception e) {
		}
	}
	
	public String getTitle() {
	  return "Rendering";
  }
	
	public DemoOption[] getDemoOptionsArray() {
		return demoComboOptionsArray;
	}
	
}
