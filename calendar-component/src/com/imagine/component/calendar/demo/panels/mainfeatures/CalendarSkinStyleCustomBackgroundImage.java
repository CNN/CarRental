package com.imagine.component.calendar.demo.panels.mainfeatures;

import java.awt.MediaTracker;

import javax.swing.ImageIcon;

import com.imagine.component.calendar.data.CalendarPropertyException;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleAqua;

public class CalendarSkinStyleCustomBackgroundImage extends CalendarSkinStyleAqua {
  
  ImageIcon imageIcon = null;
  
  /**
   * Applies this style on the specified skin.
   * @param calendarSkin The calendarSkin on which the style is applyied.
   * @throws CalendarPropertyException 
   */
  public void applyStyle(CalendarSkin calendarSkin) throws CalendarPropertyException {
    super.applyStyle(calendarSkin);
    if (imageIcon == null) {
      try {
	      imageIcon = new ImageIcon(this.getClass().getResource("/resources/images/calendar.gif"));
	      MediaTracker mediaTracker = new MediaTracker(calendarSkin.getCalendarComponent());
	      mediaTracker.addImage(imageIcon.getImage(), 1);
	      mediaTracker.waitForAll();
      } catch (Exception e) {
	      e.printStackTrace();
      }    	
    }
    calendarSkin.setSkinProperty(CalendarSkin.BACKGROUND_IMAGE, imageIcon.getImage());
  }
  
  /**
   * Get the name of the style.
   * @return The name of the style.
   */
  public String getName() {
    return "(Custom) Background Image";
  }

}
