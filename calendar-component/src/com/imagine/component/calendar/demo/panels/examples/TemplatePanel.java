package com.imagine.component.calendar.demo.panels.examples;

import java.awt.*;

import javax.swing.*;

import com.imagine.component.calendar.CalendarComponent;

public class TemplatePanel extends JPanel {
	
	protected JComponent component = null;
	protected String name =  null;
	protected String description =  null;
	private JPanel datePanel = null;
	private JPanel descriptionPanel = null;
	private JTextArea descriptionTextArea = null;
	
	public TemplatePanel() {
	}
	
	public void initialize() {
		createDatePanel();
		createDescriptionPanel();
		
		// Start puting the components together.
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.add(datePanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.add(descriptionPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
		
		this.setLayout(new GridBagLayout());
		this.add(leftPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.add(rightPanel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

	private void createDatePanel() {
		datePanel = new JPanel();
		datePanel.setBorder(BorderFactory.createTitledBorder("Date"));
		datePanel.setLayout(new GridBagLayout());
		datePanel.add(component, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
	}
	
	private void createDescriptionPanel() {
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setOpaque(false);
		
		descriptionTextArea.setText(description);
		
		descriptionPanel = new JPanel();
		descriptionPanel.setOpaque(false);
		descriptionPanel.setLayout(new GridBagLayout());
		descriptionPanel.add(descriptionTextArea, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}

	public JComponent getComponent() {
		return component;
	}

	public void setCalendarComponent(CalendarComponent calendarComponent) {
		this.component = calendarComponent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
