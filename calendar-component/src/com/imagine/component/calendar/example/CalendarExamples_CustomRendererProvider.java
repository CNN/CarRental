package com.imagine.component.calendar.example;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.renderers.CalendarRendererProviderDefault;
import com.imagine.component.calendar.renderers.tablecellrenderers.CalendarTableCellRenderer;
import com.imagine.component.calendar.skins.CalendarSkin;

public class CalendarExamples_CustomRendererProvider {

  public static void main(String[] args) {
  	CalendarComponent calendarComponent = new CalendarComponent();
  	calendarComponent.setCalendarRendererProvider(new CalendarRendererProviderCustom());
  	  }
  	  
  	}


  	class CalendarTableCellRendererCustom implements CalendarTableCellRenderer {

  	  public void paint(Graphics g, CalendarComponent calendarComponent, CalendarTableCellInfo calendarTableCellInfo) {
  	  	// If the calendar has a background renderer then this will actually paint the calendar background. 
  	  	// So this table cell renderer will no longer paint the cell background in this case. 
  	    if (!calendarComponent.getCalendarRendererProvider().hasBackgroundRenderer()) {
  	      g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.CELL_FILL_COLOR));
  	      g.fillRect(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
  	    }
  	    
  	    g.setColor(new Color(255, 0, 0));
  	    g.fillOval(0, 0, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight());
  	    
  	    g.setColor(new Color(0, 0, 255));
  	    drawCenteredString(g, calendarTableCellInfo.getWidth(), calendarTableCellInfo.getHeight(), (String)calendarTableCellInfo.getValue());
  		if (calendarTableCellInfo.isDisabled()) {
  			g.setColor(calendarComponent.getCalendarSkin().getColorSkinProperty(CalendarSkin.DISABLED_COLOR));
  			int count = 3;
  			g.drawLine(count, count, calendarTableCellInfo.getWidth() - count, calendarTableCellInfo.getHeight() - count);
  			g.drawLine(count, calendarTableCellInfo.getHeight() - count, calendarTableCellInfo.getWidth() - count, count);
  		}
  	  }

  	  public String toString() {
  	    return "Custom";
  	  }
  	  
  	  public static void drawCenteredString(Graphics g, int width, int height, String text) {
  	    FontMetrics fontMetrics = g.getFontMetrics();
  	    int currentWidth = fontMetrics.stringWidth(text);
  	    int currentHeight = fontMetrics.getAscent() - 3;
  	    
  	    int x = (width - currentWidth) / 2;
  	    if (x < 0) {
  	      x = 0;
  	    }
  	    int y = (height - currentHeight) / 2;
  	    if (y < 0) {
  	      y = 0;
  	    }
  	    y = height - y;
  	    
  	    g.drawString(text, x, y);
  	  }
  	}  

  	class CalendarRendererProviderCustom extends CalendarRendererProviderDefault {

  	  CalendarTableCellRendererCustom calendarTableCellRendererCustom = new CalendarTableCellRendererCustom();

  	  public CalendarTableCellRenderer getTableCellRenderer(CalendarTableCellInfo calendarTableCellInfo) {
  	    if (calendarTableCellInfo.getMonthType() == CalendarMonthType.CURRENT_MONTH && calendarTableCellInfo.getDayOfMonth() == 12 || calendarTableCellInfo.getDayOfMonth() == 21) {
  	      return calendarTableCellRendererCustom;
  	    }
  	    
  	    return super.getTableCellRenderer(calendarTableCellInfo);
  	  }
  	  
  	  
  	  public String getName() {
  	    return "Custom";
  	  }
  	}
