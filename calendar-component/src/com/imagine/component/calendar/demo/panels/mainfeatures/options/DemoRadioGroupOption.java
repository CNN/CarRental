package com.imagine.component.calendar.demo.panels.mainfeatures.options;

import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;

public abstract class DemoRadioGroupOption extends DemoOption {
	
	private JLabel label = null;
	private JRadioButton[] radioButtonArray = null;
	private ButtonGroup buttonGroup = null;
	
	private String text;
	private char displayedMnemonic;
	private Object[] values;
	
	public DemoRadioGroupOption(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures, String helpPath, String text, char displayedMnemonic, Object[] values) {
		super(calendarDemoPanelMainFeatures, helpPath);
		this.text = text;
		this.displayedMnemonic = displayedMnemonic;
		this.values = values;
  }

	public void initialize() {
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				calendarDemoPanelMainFeatures.setContextSensitiveHelp(getHelp());
			}
		};
		
		radioButtonArray = new JRadioButton[values.length];
		for (int i = 0; i < values.length; i++) {
			radioButtonArray[i] = new JRadioButton(values[i].toString());
			radioButtonArray[i].addMouseListener(mouseListener);
			
			buttonGroup.add(radioButtonArray[i]);
			radioButtonArray[i].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					JRadioButton source = ((JRadioButton)itemEvent.getSource());
					int selectedIndex = 0;
					for (int j = 0; j < radioButtonArray.length; j++) {
	          if (radioButtonArray[j] == source) {
	          	selectedIndex = j;
	          }
          }
					radioOptionSelected(selectedIndex);
				}
			});
    }
		radioOptionSelected(0);
		
		label = new JLabel(text);
		label.setDisplayedMnemonic(displayedMnemonic);
  }

	public abstract void radioOptionSelected(int selectedIndex);

	public JLabel getLabel() {
  	return label;
  }

	public JRadioButton[] getRadioButtonArray() {
  	return radioButtonArray;
  }

	public String getText() {
  	return text;
  }

	public Object[] getValues() {
  	return values;
  }
	
}
