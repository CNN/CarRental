package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.GridLayout;

import javax.swing.*;

public class VisualEntitiesAlignedButtonsPanel extends JPanel {

  public VisualEntitiesAlignedButtonsPanel(JButton[] buttons) {
  	GridLayout gridLayout = new GridLayout();
  	gridLayout.setHgap(4);
  	this.setLayout(gridLayout);
  	for (int i = 0; i < buttons.length; i++) {
      this.add(buttons[i]);
    }
  }
}