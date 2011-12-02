package com.imagine.component.calendar.renderers.backgroundrenderers;

import java.awt.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.CalendarBackgroundInfo;
import com.imagine.component.calendar.skins.CalendarSkin;

/**
 * Defines the default background renderer. 
 */
public class CalendarBackgroundRendererDefault implements CalendarBackgroundRenderer {
	
	Dimension size = new Dimension();
	Image backgroundImage = null;
	Image oldBackgroundImageReference = null;
	
	/**
	 * @inheritDoc
	 */
	public void paint(Graphics g, CalendarComponent calendarComponent, CalendarBackgroundInfo calendarBackgroundInfo) {
		Image latestBackgroundImageReference = calendarComponent.getCalendarSkin().getImageSkinProperty(CalendarSkin.BACKGROUND_IMAGE);
		if (latestBackgroundImageReference != oldBackgroundImageReference) {
			oldBackgroundImageReference = latestBackgroundImageReference;
			
			// Reset the background image.
			backgroundImage = latestBackgroundImageReference;
			// Reset the size.
			size.width = 0;
			size.height = 0;
		}
		if (backgroundImage != null) {
			if (calendarBackgroundInfo.getWidth() != size.width ||
					calendarBackgroundInfo.getHeight() != size.height
			) {
				size.width = calendarBackgroundInfo.getWidth();
				size.height = calendarBackgroundInfo.getHeight();
				
				// Reset the background image
				backgroundImage = latestBackgroundImageReference.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
				// Completely load the image.
				MediaTracker mediaTracker = new MediaTracker(calendarComponent);
				mediaTracker.addImage(backgroundImage, 1);
				try {
					mediaTracker.waitForAll();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}
	
}
