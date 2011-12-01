
package com.imagine.component.calendar.demo.panels.skineditor;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.imagine.component.calendar.data.CalendarProperty;
import com.imagine.component.calendar.skins.CalendarSkin;


public class ColorComponent extends JPanel {
  
  private CalendarSkin calendarSkin;
  private CalendarProperty calendarSkinColorProperty;
  
  private Color color = new Color(0, 0, 0);
  private JLabel label = new JLabel();
  private JTextField textField = new JTextField();
  private JPanel showColor = new JPanel() {
  	public Dimension getPreferredSize() {
  	  return new Dimension(0,0);
  	}
  };
  private JButton chooseButton = new JButton("Choose Color");

  public ColorComponent(CalendarSkin calendarSkin, CalendarProperty calendarSkinColorProperty, String labelText) {
  	this.calendarSkin = calendarSkin;
  	this.calendarSkinColorProperty = calendarSkinColorProperty;
  	label.setText(labelText);
    initialize();
  }

  private void initialize() {
    this.setLayout(new GridLayout(1, 3, 5, 0));
//    this.add(label);
    this.add(textField);
    this.add(showColor);
    this.add(chooseButton);
    
    textField.setFont(new Font("Monospaced", Font.PLAIN, 11));
    
    textField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setColor(parseColorValue(textField.getText()));
        try {
          updateValueInCalendarSkin();
        } catch (Exception ex) {
        }
      }
    });
    
    chooseButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(ColorComponent.this, "Choose Color", color);
        if (newColor != null) {
          setColor(newColor);
          try {
            updateValueInCalendarSkin();
          } catch (Exception ex) {
          }
        }
      }
    });
    
  }
  
  public Color getColor() {
    return color;
  }
  
  public void setColor(Color color) {
    this.color = color;
    textField.setText(formatColorValue(color));
    showColor.setBackground(color);
  }
  
  private String formatColorValue(Color color) {
    return "#" + formatHex(Integer.toString(color.getRed(), 16)) + formatHex(Integer.toString(color.getGreen(), 16)) + formatHex(Integer.toString(color.getBlue(), 16));
  }
  
  private String formatHex(String hex) {
    int length = hex.length();
    for (int i = 0; i < 2 - length; i++) {
      hex = "0" + hex;
    }
    return hex;
  }
  
  private Color parseColorValue(String text) {
    int first, last;
    
    int r = 0, g = 0, b = 0;
    
    first = 0;
    if (text.charAt(first) == '#') {
      first++;
    }
    last = first + 2;
    if (last <= text.length()) {
      r = Integer.parseInt(text.substring(first, last), 16);
    }
    first = last;
    last = first + 2;
    if (last <= text.length()) {
      g = Integer.parseInt(text.substring(first, last), 16);
    }
    first = last;
    last = first + 2;
    if (last <= text.length()) {
      b = Integer.parseInt(text.substring(first, last), 16);
    }
    first = last;
    return new Color(r, g, b);
  }
  
  public void updateValueInCalendarSkin() {
    calendarSkin.setSkinProperty(calendarSkinColorProperty, color);
    calendarSkin.getCalendarComponent().repaint();
  }

  public void updateColorComponentInterface() {
    this.setColor(calendarSkin.getColorSkinProperty(calendarSkinColorProperty));
  }

	public JLabel getLabel() {
  	return label;
  }

}
