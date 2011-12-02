package com.imagine.component.calendar.demo.panels.mainfeatures;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.*;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.demo.CalendarDemoController;
import com.imagine.component.calendar.demo.panels.CalendarDemoPanel;
import com.imagine.component.calendar.demo.panels.mainfeatures.options.DemoCheckboxOption;
import com.imagine.component.calendar.demo.panels.mainfeatures.properties.*;
import com.imagine.component.calendar.demo.panels.mainfeatures.table.CustomTable;
import com.imagine.component.calendar.util.CalendarUtils;

public class CalendarDemoPanelMainFeatures extends CalendarDemoPanel {
	

	private CalendarComponent calendarComponent = new CalendarComponent();
	
	private CalendarComboBox calendarComboBox = new CalendarComboBox();
	
	private String comboBoxHelpPath = "/resources/help/CalendarComboBoxHelp.txt";
		
	private String comboBoxHelp = null;
	
	private String tableCellEditorHelpPath = "/resources/help/JTableCellEditorCalendarHelp.txt";
	
	private String tableCellEditorHelp = null;
	
	private String eventsHelpPath = "/resources/help/CalendarEventsHelp.txt";
	
	private String eventsHelp = null;
	
	private String calendarComponentHelpPath = "/resources/help/CalendarComponentHelp.txt";
	
	private String calendarComponentHelp = null;
	
	private CalendarComboBox customTableCalendarComboBox = null;
	
	private DemoProperties[] demoProperties = new DemoProperties[] {
		new DemoBooleanProperties(this),
		new DemoOtherProperties(this),
		new DemoRenderingProperties(this),
		new DemoSelectionProperties(this),
		new DemoMetricsProperties(this),
		new DemoNavigationProperties(this),
	};
	
	private String[] monthsNames = CalendarUtils.getMonthsNames(Locale.ENGLISH);
	
	private JLabel chosenDateText = null;
	
	private JPanel eventsPanel = null;
	
	private JScrollPane eventsScrollPane = null;
	
	private JTextArea eventsTextArea = null;
	
	private JPanel contextSensitiveHelpPanel = null;
	
	private JScrollPane contextSensitiveHelpScrollPane = null;
	
	private JTextArea contextSensitiveHelpTextArea = null;
	
	private JPanel datePanel = new JPanel();
	private JPanel calendarComboPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JTabbedPane demoPropertiesTabbedPane = new JTabbedPane();
	private JPanel comboboxPropertiesPanel = new JPanel();
	private CustomTable customTable = new CustomTable();
	
	public CalendarDemoPanelMainFeatures(CalendarDemoController calendarDemoController) {
		super(calendarDemoController);
		
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void initialize() {
		createDatePanel();
		createComboPanel();
		createTablePanel();
		
		createPropertiesPanel();
		createComboPropertiesPanel();
		createEventsPanel();
		createContextSensitiveHelp();
		
		// Start puting the components together.
		
		JPanel leftPanel = new JPanel();
		
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.add(datePanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		leftPanel.add(calendarComboPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		leftPanel.add(tablePanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		int count = 0;
		this.setLayout(new GridBagLayout());
		this.add(leftPanel, new GridBagConstraints(0, 0, 1, 3, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.add(demoPropertiesTabbedPane, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		this.add(comboboxPropertiesPanel, new GridBagConstraints(1, count, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		this.add(contextSensitiveHelpPanel, new GridBagConstraints(1, count, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		this.add(eventsPanel, new GridBagConstraints(0, count, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		
		CalendarComponentListener calendarComponentListener = new CustomCalendarComponentListener(this);
		calendarComponent.addCalendarComponentListener(calendarComponentListener);
		
		DateListener dateListener = new CustomDateListener(this);
		calendarComponent.addDateListener(dateListener);
		
		setContextSensitiveHelp(getCalendarComponentHelp());
	}
	

	private void createDatePanel() {
		datePanel.setBorder(BorderFactory.createTitledBorder("Date"));
		datePanel.setLayout(new GridBagLayout());
		datePanel.add(calendarComponent, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		
		datePanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setContextSensitiveHelp(getCalendarComponentHelp());
			}
		});
  }


	private void createComboPanel() {
		calendarComboBox.setDateFormat("dd MMMMMMMMMM yyyy");
		
		// Chosen date.
		
		chosenDateText = new JLabel("Date:");
		chosenDateText.setDisplayedMnemonic('D');
		chosenDateText.setLabelFor(calendarComboBox);
		
		calendarComboPanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setContextSensitiveHelp(getComboBoxHelp());
			}
		});
		
		calendarComboPanel.setBorder(BorderFactory.createTitledBorder("Calendar ComboBox"));
		calendarComboPanel.setLayout(new GridBagLayout());
		calendarComboPanel.add(chosenDateText, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		calendarComboPanel.add(calendarComboBox, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		calendarComboPanel.add(new JPanel(), new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
  }

	private String getTableCellEditorHelp() {
    if (tableCellEditorHelp == null) {
    	tableCellEditorHelp = getHelp(tableCellEditorHelpPath);
    }
		return tableCellEditorHelp;
  }
	
	private String getEventsHelp() {
    if (eventsHelp == null) {
    	eventsHelp = getHelp(eventsHelpPath);
    }
		return eventsHelp;
  }
	
	private String getCalendarComponentHelp() {
    if (calendarComponentHelp == null) {
    	calendarComponentHelp = getHelp(calendarComponentHelpPath);
    }
		return calendarComponentHelp;
  }
	
	private String getComboBoxHelp() {
    if (comboBoxHelp == null) {
    	comboBoxHelp = getHelp(comboBoxHelpPath);
    }
		return comboBoxHelp;
  }
	
	private void createTablePanel() {
		customTableCalendarComboBox = customTable.calendarTableCellEditor.getCalendarComboBox();
		
		customTable.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setContextSensitiveHelp(getTableCellEditorHelp());
			}
		});
		
		tablePanel.setBorder(BorderFactory.createTitledBorder("JTable Cell Editor Calendar"));
		tablePanel.setLayout(new GridBagLayout());
		tablePanel.add(customTable.getTableHeader(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(customTable, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(new JPanel(), new GridBagConstraints(1, 0, 1, 2, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
  }

	private void createPropertiesPanel() {
		for (int i = 0; i < demoProperties.length; i++) {
	    demoProperties[i].initialize();
	    demoPropertiesTabbedPane.add(demoProperties[i].getTitle(), demoProperties[i]);
    }
  }


	private void createComboPropertiesPanel() {
		//ComboBox Additional Properties

		DemoCheckboxOption demoCheckboxOptionComboBoxCloseWhenDateSelected = new DemoCheckboxOption(this, "/resources/help/SetCloseComboWhenDateSelectedHelp.txt", "Close When Date Selected", 'W', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarComboBox.setCalendarComboBoxProperty(CalendarComboBox.CLOSE_WHEN_DATE_SELECTED, new Boolean(this.isCheckBoxSelected()));
				customTableCalendarComboBox.setCalendarComboBoxProperty(CalendarComboBox.CLOSE_WHEN_DATE_SELECTED, new Boolean(this.isCheckBoxSelected()));
				calendarDemoController.getCalendarDemo().getFrame().pack();
			}
		};
		demoCheckboxOptionComboBoxCloseWhenDateSelected.initialize();
		
		DemoCheckboxOption demoCheckboxOptionComboBoxEnabled = new DemoCheckboxOption(this, "/resources/help/ComboBoxEnabledHelp.txt", "Enabled", 'E', true) {
			public void checkBoxOperationPerformed(boolean isSelected) {
				calendarComboBox.setEnabled(this.isCheckBoxSelected());
				customTableCalendarComboBox.setEnabled(this.isCheckBoxSelected());
				calendarDemoController.getCalendarDemo().getFrame().pack();
			}
		};
		demoCheckboxOptionComboBoxEnabled.initialize();
		
		int count = 0;
		comboboxPropertiesPanel.setBorder(BorderFactory.createTitledBorder("Calendar ComboBox Additional Properties"));
		comboboxPropertiesPanel.setLayout(new GridBagLayout());
		comboboxPropertiesPanel.add(demoCheckboxOptionComboBoxCloseWhenDateSelected.getCheckBox(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		comboboxPropertiesPanel.add(demoCheckboxOptionComboBoxCloseWhenDateSelected.getLabel(), new GridBagConstraints(1, count, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		count++;
		comboboxPropertiesPanel.add(demoCheckboxOptionComboBoxEnabled.getCheckBox(), new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		comboboxPropertiesPanel.add(demoCheckboxOptionComboBoxEnabled.getLabel(), new GridBagConstraints(1, count, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		comboboxPropertiesPanel.add(new JPanel(), new GridBagConstraints(2, 1, 1, count, 1.0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		count++;
  }

	private void createContextSensitiveHelp() {
		contextSensitiveHelpTextArea = new JTextArea();
		contextSensitiveHelpTextArea.setRows(6);
		contextSensitiveHelpTextArea.setColumns(20);
		contextSensitiveHelpTextArea.setWrapStyleWord(true);
		contextSensitiveHelpTextArea.setLineWrap(true);
		
		contextSensitiveHelpPanel = new JPanel();
		contextSensitiveHelpScrollPane = new JScrollPane(contextSensitiveHelpTextArea);
		contextSensitiveHelpScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contextSensitiveHelpScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		contextSensitiveHelpPanel.setBorder(BorderFactory.createTitledBorder("Context Sensitive Help"));
		contextSensitiveHelpPanel.setLayout(new GridBagLayout());
		contextSensitiveHelpPanel.add(contextSensitiveHelpScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
  }

	
	private void createEventsPanel() {
		eventsTextArea = new JTextArea();
		eventsTextArea.setRows(4);
		eventsTextArea.setColumns(20);
		eventsTextArea.setWrapStyleWord(true);
		
		eventsTextArea.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setContextSensitiveHelp(getEventsHelp());
			}
		});
		
		
		eventsPanel = new JPanel();
		eventsScrollPane = new JScrollPane(eventsTextArea);
		eventsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		eventsPanel.setBorder(BorderFactory.createTitledBorder("Events"));
		eventsPanel.setLayout(new GridBagLayout());
		eventsPanel.add(eventsScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	public String getTitle() {
		return "Main Features";
	}

	public CalendarComboBox getCalendarComboBox() {
  	return calendarComboBox;
  }


	public CalendarComponent getCalendarComponent() {
  	return calendarComponent;
  }


	public CalendarComboBox getCustomTableCalendarComboBox() {
  	return customTableCalendarComboBox;
  }

	public void addEventHelp(String text) {
		if (eventsTextArea.getText().length() > 0) {
			eventsTextArea.append("\n");
		}
		eventsTextArea.append(text);
	}
	
	public void setContextSensitiveHelp(String text) {
		contextSensitiveHelpTextArea.setText(text);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				contextSensitiveHelpScrollPane.getVerticalScrollBar().setValue(0);
			}
		});
	}
}
