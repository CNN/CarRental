package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.data.CalendarTableCellInfo;
import com.imagine.component.calendar.demo.panels.eventsmanager.CalendarDemoPanelEventsManager;
import com.imagine.component.calendar.util.WindowUtilities;

public class EditEventDialog {
	
	private JDialog dialog = null;
	private JPanel rootPanel = new JPanel();
	
	private JButton okJButton = new JButton();
	private JButton cancelJButton = new JButton();
	private JLabel titleLabel = new JLabel("Title");
	private JTextField titleTextField = new JTextField(20);
	private JLabel descriptionLabel = new JLabel("Description");
	private JTextField descriptionTextField = new JTextField(20);
	private VisualEntityDataField visualEntityDataField = null;
	
	public final static int ADD_EVENT = 1;
	public final static int EDIT_EVENT = 2;
	
	private int action = ADD_EVENT;
	
	private CalendarTableCellInfo calendarTableCellInfo = null;
	
	private CalendarDemoPanelEventsManager calendarDemoPanelEventsManager;
	
	public EditEventDialog(CalendarDemoPanelEventsManager calendarDemoPanelEventsManager) {
		this.calendarDemoPanelEventsManager = calendarDemoPanelEventsManager;
		dialog = WindowUtilities.createDialog(WindowUtilities.getAncestorWindow(calendarDemoPanelEventsManager));
		
		initialize();
	}

	private void initialize() {
		rootPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
		
		okJButton.setText("Ok");
		okJButton.setMnemonic('O');
		ActionListener okActionListener = new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okJButton_actionPerformed();
			}
		};
		okJButton.addActionListener(okActionListener);
		cancelJButton.setText("Cancel");
		cancelJButton.setMnemonic('C');
		ActionListener cancelActionListener = new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelJButton_actionPerformed();
			}
		};
		KeyListener keyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancelJButton_actionPerformed();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					okJButton_actionPerformed();
				}
			}
		};
		titleTextField.addKeyListener(keyListener);
		descriptionTextField.addKeyListener(keyListener);
		
		cancelJButton.addActionListener(cancelActionListener);
		VisualEntitiesAlignedButtonsPanel alignedButtonsPanel = new VisualEntitiesAlignedButtonsPanel(new JButton[] { okJButton, cancelJButton });
		
		
		int count = 0;
		rootPanel.setLayout(new GridBagLayout());
		rootPanel.add(titleLabel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		rootPanel.add(titleTextField, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		count++;
		rootPanel.add(descriptionLabel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		rootPanel.add(descriptionTextField, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		count++;
		
		Container contentPane = dialog.getContentPane();
		
		contentPane.setLayout(new GridBagLayout());
		contentPane.add(rootPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		contentPane.add(alignedButtonsPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		dialog.pack();
  }
	
	private void okJButton_actionPerformed() {
		dialog.setVisible(false);
		String key = calendarDemoPanelEventsManager.calculateKey(calendarTableCellInfo);
		VisualEntitiesManager visualEntitiesManager = calendarDemoPanelEventsManager.getVisualEntitiesManager(key);
		if (visualEntitiesManager == null) {
			visualEntitiesManager = new VisualEntitiesManager(calendarDemoPanelEventsManager, key);
			calendarDemoPanelEventsManager.putVisualEntitiesManager(visualEntitiesManager);
		}
		if (action == EditEventDialog.ADD_EVENT) {
			DataField dataField = new DataField(titleTextField.getText(), descriptionTextField.getText());
			visualEntitiesManager.addVisualEntity(new VisualEntityDataField(visualEntitiesManager, dataField));
		} else if (action == EditEventDialog.EDIT_EVENT) {
			visualEntityDataField.getDataField().setName(titleTextField.getText());
			visualEntityDataField.getDataField().setDescription(descriptionTextField.getText());
		}
  }
	
	protected void cancelJButton_actionPerformed() {
		dialog.setVisible(false);
  }

	public JDialog getDialog() {
		return dialog;
	}
	
	public void setVisible(boolean visible) {
		dialog.setVisible(visible);
		titleTextField.requestFocus();
	}

	public CalendarTableCellInfo getCalendarTableCellInfo() {
  	return calendarTableCellInfo;
  }

	public void setCalendarTableCellInfo(CalendarTableCellInfo calendarTableCellInfo) {
  	this.calendarTableCellInfo = calendarTableCellInfo;
  }
	
	public void reset(int action, VisualEntityDataField visualEntityDataField) {
		this.action = action;
		this.visualEntityDataField = visualEntityDataField;
		
		if (visualEntityDataField == null) {
			titleTextField.setText("");
			descriptionTextField.setText("");
		} else {
			titleTextField.setText(visualEntityDataField.getDataField().getName());
			descriptionTextField.setText(visualEntityDataField.getDataField().getDescription());
		}
	}
	
//	public String getText() {
//		return titleTextField.getText();
//	}
//	
//	public void setText(String text) {
//		titleTextField.setText(text);
//	}
//	
//	public String getDescription() {
//		return descriptionTextField.getText();
//	}
//	
//	public void setDescription(String description) {
//		descriptionTextField.setText(description);
//	}

	public void setVisualEntityDataField(VisualEntityDataField visualEntityDataField) {
		this.visualEntityDataField = visualEntityDataField;
  }

//	public int getAction() {
//  	return action;
//  }
//
//	public void setAction(int action) {
//  	this.action = action;
//  }
}
