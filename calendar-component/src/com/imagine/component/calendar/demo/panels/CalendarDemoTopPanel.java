package com.imagine.component.calendar.demo.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.demo.CalendarDemoController;

public class CalendarDemoTopPanel extends JPanel {

  private CalendarDemoController calendarDemoController;
  
  private JLabel lookAndFeelText = null;

  private JComboBox lookAndFeelValue = null;
  
  
  public CalendarDemoTopPanel(CalendarDemoController calendarDemoController) {
    this.calendarDemoController = calendarDemoController;
    initialize();
  }

  private void initialize() {
    // Look And Feel
    
    int index = 0;
    String chosenLookAndFeel = calendarDemoController.getSystemLookAndFeel();
    for (int i = 0; i < calendarDemoController.getLookAndFeelInfos().length; i++) {
      if (calendarDemoController.getLookAndFeelInfos()[i].getClassName().equals(chosenLookAndFeel)) {
        index = i;
        break;
      }
    }

    lookAndFeelValue = new JComboBox(calendarDemoController.getLookAndFeelInfos());
    lookAndFeelValue.addItemListener(new ItemListener() {
      public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					Runnable runnable = new Runnable() {
						public void run() {
							try {
								JComboBox source = ((JComboBox)itemEvent.getSource());
								Frame frame = JOptionPane.getFrameForComponent(source);
								UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) source.getSelectedItem();
								if (info != null) {
									UIManager.setLookAndFeel(info.getClassName());
									SwingUtilities.updateComponentTreeUI(frame);
									frame.pack();
								}
							} catch (Exception ex) {
//              ex.printStackTrace();
							}
						}
					};
          SwingUtilities.invokeLater(runnable);
        }
      }
    });
    lookAndFeelValue.setSelectedIndex(index);
    lookAndFeelValue.setRenderer(new DefaultListCellRenderer() {
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (value != null) {
          UIManager.LookAndFeelInfo lookAndFeelInfo = (UIManager.LookAndFeelInfo) value;
          setText(lookAndFeelInfo.getName());
        }

        return label;
        
      }
    });
    
    lookAndFeelText = new JLabel("Look And Feel:");
    lookAndFeelText.setDisplayedMnemonic('F');
    lookAndFeelText.setLabelFor(lookAndFeelValue);
    
    JPanel panel = new JPanel();
    
    int count = 0;
    panel.setLayout(new GridBagLayout());
    panel.add(lookAndFeelText, new GridBagConstraints(0, count, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    panel.add(lookAndFeelValue, new GridBagConstraints(1, count, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    count++;
    
    this.setLayout(new GridBagLayout());
    this.add(panel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }
}
