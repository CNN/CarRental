package com.imagine.component.calendar.demo.panels.examples;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTabbedPane;

import com.imagine.component.calendar.demo.CalendarDemoController;
import com.imagine.component.calendar.demo.panels.CalendarDemoPanel;

public class CalendarDemoPanelExamples extends CalendarDemoPanel {

	public CalendarDemoPanelExamples(CalendarDemoController calendarDemoController) {
		super(calendarDemoController);

		initialize();
	}

	private void initialize() {
		TemplatePanel[] exampleTemplatePanel = new TemplatePanel[] {
			new CalendarComponent01Panel(this),	
			new CalendarComponent02Panel(this),
			new CalendarWrapper01Panel(this),
		};
		JTabbedPane tabbedPane = new JTabbedPane();
		for (int i = 0; i < exampleTemplatePanel.length; i++) {
			tabbedPane.addTab(exampleTemplatePanel[i].getName(), exampleTemplatePanel[i]);
		}
		this.setLayout(new GridBagLayout());
		this.add(tabbedPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	public String getTitle() {
		return "Examples";
	}
}

