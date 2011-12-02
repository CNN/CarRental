package com.imagine.component.calendar.demo.panels.eventsmanager.renderer;

import java.awt.*;

public class CustomGUIUtilities {

  public static void drawTopLeftString(Graphics g, int width, int height, String text) {
    FontMetrics fontMetrics = g.getFontMetrics();
    int currentHeight = fontMetrics.getAscent() + 4;
    
    int x = 4;
    int y = currentHeight;
    
    g.drawString(text, x, y);
  }
}
