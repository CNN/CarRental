
package com.imagine.component.calendar.demo.panels.skineditor;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.imagine.component.calendar.data.CalendarProperty;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.skins.wrapper.FontWrapper;


public class FontComponent extends JPanel {
  
  private FontWrapper fontWrapper = null;
  private CalendarSkin calendarSkin;
  
  private TitledBorder border = BorderFactory.createTitledBorder("");
  
  private JPanel fontStylePanel = new JPanel();
  private JLabel fontStyleLabel = new JLabel("Font Style");
  private JComboBox fontStyleComboBox = null;
  
  private JPanel fontsPanel = new JPanel();
  private ButtonGroup radioGroup = new ButtonGroup();
  private JLabel defaultFontLabel = new JLabel("Default Component Font Family and Size");
  private JRadioButton defaultFontRadioButton = new JRadioButton();
  private JRadioButton specificFontRadioButton = new JRadioButton();
  
  private JComboBox fontsComboBox = null;
  private JTextField fontSizeTextField = new JTextField(5);
  
  Font[] fonts = new Font[0];
  Integer[] fontStylesCodes = new Integer[] {
      new Integer(Font.PLAIN),
      new Integer(Font.ITALIC),
      new Integer(Font.BOLD),
      new Integer(Font.ITALIC | Font.BOLD),
  };
  
  String[] fontStylesNames = new String[] {
      "PLAIN",
      "ITALIC",
      "BOLD",
      "ITALIC AND BOLD",
  };
  int selectedFontStyleIndex = 0;
  int selectedFontIndex = 0;
    
  boolean initialized = false;

  public FontComponent(CalendarSkin calendarSkin, CalendarProperty calendarSkinFontProperty, String labelText) {
  	this.calendarSkin = calendarSkin;
  	fontWrapper = new FontWrapper(calendarSkinFontProperty);
    border.setTitle(labelText);
    initialize();
  }

  private void initialize() {
    createFontStylesPanel();
    createFontsPanel();
		
    int count = 0;
    this.setBorder(border);
		this.setLayout(new GridBagLayout());
		this.add(fontStylePanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		this.add(fontsPanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		count++;
    
    initialized = true;
  }

	private void createFontStylesPanel() {
    fontStyleComboBox = new JComboBox(fontStylesNames);
    
    fontStyleComboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
          JComboBox source = ((JComboBox)itemEvent.getSource());
          int selectedIndex = source.getSelectedIndex();
          if (selectedIndex < 0 || selectedIndex >= source.getItemCount()) {
            selectedIndex = 0;
          }
          
          selectedFontStyleIndex = selectedIndex;
          int fontStyle = fontStylesCodes[selectedFontStyleIndex].intValue();
          fontWrapper.setStyle(fontStyle);
          
          updateValueInCalendarSkin();
        }
      }
    });
    
    int count = 0;
		
    fontStylePanel.setLayout(new GridBagLayout());
		fontStylePanel.add(fontStyleLabel, new GridBagConstraints(count, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		fontStylePanel.add(fontStyleComboBox, new GridBagConstraints(count, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		count++;
		fontStylePanel.add(new JPanel(), new GridBagConstraints(count, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		count++;
  }
  
  private void createFontsPanel() {
    String[] fontsNamesList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    fonts = new Font[fontsNamesList.length];
    for (int i = 0; i < fontsNamesList.length; i++) {
      fonts[i] = new Font(fontsNamesList[i], Font.PLAIN, fontWrapper.getSize());
    }
    fontsComboBox = new JComboBox(fonts);
    
    fontsComboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
          JComboBox source = ((JComboBox)itemEvent.getSource());
          int selectedIndex = source.getSelectedIndex();
          if (selectedIndex < 0 || selectedIndex >= source.getItemCount()) {
            selectedIndex = 0;
          }
          
          selectedFontIndex = selectedIndex;
          Font currentFont = fonts[selectedFontIndex];
          fontWrapper.setFamily(currentFont.getFamily());
          
          updateValueInCalendarSkin();
        }
      }
    });
    fontsComboBox.setRenderer(new DefaultListCellRenderer() {
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        Font currentFont = (Font)value;
        label.setFont(currentFont);
        if (value != null) {
          setText(currentFont.getFamily());
        }
        
        return label;
      }
    });
    
    ItemListener itemListener = new ItemListener() {
		
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem() == defaultFontRadioButton) {
						fontsComboBox.setEnabled(false);
						fontSizeTextField.setEnabled(false);
						fontWrapper.setDefaultFamilyAndSize(true);
					} else if (e.getItem() == specificFontRadioButton) {
						fontsComboBox.setEnabled(true);
						fontSizeTextField.setEnabled(true);
						fontWrapper.setDefaultFamilyAndSize(false);
					}
					updateValueInCalendarSkin();
				}
			}
		};
		
    defaultFontRadioButton.addItemListener(itemListener);
    specificFontRadioButton.addItemListener(itemListener);
    
    defaultFontRadioButton.setSelected(true);
    
    radioGroup.add(defaultFontRadioButton);
    radioGroup.add(specificFontRadioButton);
    
    fontSizeTextField.setText("" + fontWrapper.getSize());
    
    int count = 0;
		fontsPanel.setLayout(new GridBagLayout());
		fontsPanel.add(defaultFontRadioButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		fontsPanel.add(defaultFontLabel, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		fontsPanel.add(specificFontRadioButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		fontsPanel.add(fontsComboBox, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
		fontsPanel.add(fontSizeTextField, new GridBagConstraints(2, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
		count++;
    
  }

  public FontWrapper getFontWrapper() {
    return fontWrapper;
  }
  
  public void setFont(String family, int style, int size, boolean defaultFontFamilyAndSize) {
    if (initialized) {
      fontWrapper.setFamily(family);
      fontWrapper.setStyle(style);
      fontWrapper.setSize(size);
      fontWrapper.setDefaultFamilyAndSize(defaultFontFamilyAndSize);
      
      int selectedIndex = 0;
      for (int i = 0; i < fonts.length; i++) {
        if (fonts[i].getFamily().equalsIgnoreCase(family)) {
          selectedIndex = i;
          break;
        }
      }
      selectedFontIndex = selectedIndex;
      fontsComboBox.setSelectedIndex(selectedFontIndex);
      
      for (int i = 0; i < fontStylesCodes.length; i++) {
        if (fontStylesCodes[i].intValue() == style) {
          selectedIndex = i;
          break;
        }
      }
      selectedFontStyleIndex = selectedIndex;
      fontStyleComboBox.setSelectedIndex(selectedFontStyleIndex);
      
      fontSizeTextField.setText("" + size);
      if (defaultFontFamilyAndSize) {
      	defaultFontRadioButton.setSelected(true);
      } else {
      	specificFontRadioButton.setSelected(true);
      }
    }
  }
  
  public void updateValueInCalendarSkin() {
  	CalendarProperty calendarSkinFontProperty = fontWrapper.getWrappedCalendarProperty();
  	FontWrapper calendarSkinFontWrapper = calendarSkin.getCalendarSkinStyle().getCalendarSkinStyleAdapter().getFontWrapperForSkinFontProperty(calendarSkinFontProperty);
  	
  	calendarSkinFontWrapper.setFamily(fontWrapper.getFamily()); 
  	calendarSkinFontWrapper.setStyle(fontWrapper.getStyle()); 
  	calendarSkinFontWrapper.setSize(fontWrapper.getSize()); 
  	calendarSkinFontWrapper.setDefaultFamilyAndSize(fontWrapper.isDefaultFamilyAndSize());
  	// Always save dynamical values.
  	calendarSkin.setSkinProperty(calendarSkinFontProperty, null);
  	calendarSkin.getCalendarComponent().invalidate();
  	calendarSkin.getCalendarComponent().repaint();
  }

  public void updateFontComponentInterface() {
  	CalendarProperty calendarSkinFontProperty = fontWrapper.getWrappedCalendarProperty();
    if (calendarSkin.isSkinPropertyObtainedDynamically(calendarSkinFontProperty)) {
    	FontWrapper calendarSkinFontWrapper = calendarSkin.getCalendarSkinStyle().getCalendarSkinStyleAdapter().getFontWrapperForSkinFontProperty(calendarSkinFontProperty);
    	
    	this.setFont(calendarSkinFontWrapper.getFamily(), calendarSkinFontWrapper.getStyle(), calendarSkinFontWrapper.getSize(), calendarSkinFontWrapper.isDefaultFamilyAndSize());
    } else {
    	Font font = calendarSkin.getFontSkinProperty(calendarSkinFontProperty);
    	
    	this.setFont(font.getFamily(), font.getStyle(), font.getSize(), false);
    }
  }

}
