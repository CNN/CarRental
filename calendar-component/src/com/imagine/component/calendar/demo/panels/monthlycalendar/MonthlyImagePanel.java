package com.imagine.component.calendar.demo.panels.monthlycalendar;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class MonthlyImagePanel extends JLabel {
  private ImageIcon[] monthsImages = null;
  
  private int monthIndex = -1;
  
  private GregorianCalendar calendar = new GregorianCalendar();
  
  private Dimension preferredSize = null;
  
  public MonthlyImagePanel() {
    super();
    initialize();
  }
  
  
  private void initialize() {

    monthsImages = new ImageIcon[] {
      new ImageIcon(this.getClass().getResource("/resources/images/months/01january.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/02february.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/03march.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/04april.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/05may.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/06june.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/07july.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/08august.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/09september.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/10october.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/11november.jpg")),
      new ImageIcon(this.getClass().getResource("/resources/images/months/12december.jpg")),
    };
    
    MediaTracker mediaTracker = new MediaTracker(this);
    for (int i = 0; i < monthsImages.length; i++) {
      mediaTracker.addImage(monthsImages[i].getImage(), i);
    }
    try {
      mediaTracker.waitForAll();
    } catch (Exception e) {}
    
    preferredSize = new Dimension(monthsImages[0].getIconWidth(), monthsImages[0].getIconHeight());
  }
  
  public Dimension getPreferredSize() {
    return preferredSize;
  }
  
  public int getMonthIndex() {
    return monthIndex;
  }
  
  public void setMonthIndex(int monthIndex) {
    this.monthIndex = monthIndex;
  }


  public void setDate(Date date) {
    calendar.setTime(date);
    if (monthIndex != calendar.get(Calendar.MONTH)) {
      monthIndex = calendar.get(Calendar.MONTH);
      this.setIcon(monthsImages[monthIndex]);
    }
  }
  
}
