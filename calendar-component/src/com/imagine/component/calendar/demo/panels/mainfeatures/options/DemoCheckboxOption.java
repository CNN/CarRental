package com.imagine.component.calendar.demo.panels.mainfeatures.options;

import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;

public abstract class DemoCheckboxOption extends DemoOption {
	private JLabel label = null;
	private JCheckBox checkbox = null;
	
	private String text;
	private char displayedMnemonic;
	private boolean value;
	
	public DemoCheckboxOption(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures, String helpPath, String text, char displayedMnemonic, boolean value) {
		super(calendarDemoPanelMainFeatures, helpPath);
		this.text = text;
		this.displayedMnemonic = displayedMnemonic;
		this.value = value;
  }

	public void initialize() {
		checkbox = new JCheckBox("", value);
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxOperationPerformed(checkbox.isSelected());
			}
		});
		checkBoxOperationPerformed(checkbox.isSelected());
		
		label = new JLabel(text);
		label.setDisplayedMnemonic(displayedMnemonic);
		label.setLabelFor(checkbox);
		
		checkbox.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				calendarDemoPanelMainFeatures.setContextSensitiveHelp(getHelp());
			}
		});
  }

	public abstract void checkBoxOperationPerformed(boolean isSelected);

	public JCheckBox getCheckBox() {
  	return checkbox;
  }

	public JLabel getLabel() {
  	return label;
  }

	public String getText() {
  	return text;
  }

	public boolean isCheckBoxSelected() {
  	return checkbox.isSelected();
  }

}
