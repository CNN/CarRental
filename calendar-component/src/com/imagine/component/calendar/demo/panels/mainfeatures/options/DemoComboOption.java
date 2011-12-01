package com.imagine.component.calendar.demo.panels.mainfeatures.options;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.demo.panels.mainfeatures.CalendarDemoPanelMainFeatures;

public abstract class DemoComboOption extends DemoOption {
	
	private JLabel label = null;
	private JComboBox combo = null;
	
	private String text;
	private char displayedMnemonic;
	private Object[] values;
	private int initialSelectedIndex = 0;
	
	public DemoComboOption(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures, String helpPath, String text, char displayedMnemonic, Object[] values) {
		super(calendarDemoPanelMainFeatures, helpPath);
		this.text = text;
		this.displayedMnemonic = displayedMnemonic;
		this.values = values;
  }

	public DemoComboOption(CalendarDemoPanelMainFeatures calendarDemoPanelMainFeatures, String helpPath, String text, char displayedMnemonic, Object[] values, int initialSelectedIndex) {
		super(calendarDemoPanelMainFeatures, helpPath);
		this.text = text;
		this.displayedMnemonic = displayedMnemonic;
		this.values = values;
		this.initialSelectedIndex = initialSelectedIndex;
  }

	public void initialize() {
		combo = new JComboBox(values);
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					JComboBox source = ((JComboBox)itemEvent.getSource());
					int selectedIndex = source.getSelectedIndex();
					if (selectedIndex < 0 || selectedIndex >= source.getItemCount()) {
						selectedIndex = 0;
					}
					comboOptionSelected(selectedIndex);
				}
			}
		});
		combo.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				
				if (value != null) {
					setText(getDisplayString(value));
				} else {
					setText("null");
				}
				
				return label;
			}
		});
		combo.setSelectedIndex(initialSelectedIndex);
		comboOptionSelected(initialSelectedIndex);

		combo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				calendarDemoPanelMainFeatures.setContextSensitiveHelp(getHelp());
			}
		});
		
		label = new JLabel(text);
		label.setDisplayedMnemonic(displayedMnemonic);
		label.setLabelFor(combo);
		
  }

	public abstract void comboOptionSelected(int selectedIndex);

	public String getDisplayString(Object value) {
    return value.toString();
	}

	public JComboBox getCombo() {
  	return combo;
  }

	public JLabel getLabel() {
  	return label;
  }

	public String getText() {
  	return text;
  }

	public Object[] getValues() {
  	return values;
  }
	
}
