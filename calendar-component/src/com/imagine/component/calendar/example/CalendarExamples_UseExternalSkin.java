package com.imagine.component.calendar.example;

import java.io.File;
import java.net.URL;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.skins.external.*;

public class CalendarExamples_UseExternalSkin {

  public static void main(String[] args) {
try {
CalendarComponent calendarComponent = new CalendarComponent();
calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleExternalFile(new File("./skins/skin01.skin")));

calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleExternalURL(new URL("http://www.calendarcomponent.com/skins/skin01.skin")));

CalendarSkinStyleExternalFile.exportSkin(calendarComponent.getCalendarSkin(), new File("./skins/test.skin"));
} catch (Exception e) {
}
  }
  }
