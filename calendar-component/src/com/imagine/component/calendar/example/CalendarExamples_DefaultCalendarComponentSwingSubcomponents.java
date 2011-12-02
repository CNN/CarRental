package com.imagine.component.calendar.example;

import javax.swing.*;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.data.*;

public class CalendarExamples_DefaultCalendarComponentSwingSubcomponents {

  public static void main(String[] args) {
CalendarComponent calendarComponent = new CalendarComponent();

CalendarNavigationController calendarNavigationController = calendarComponent.getCalendarView().getNavigationController();
if (calendarNavigationController instanceof CalendarNavigationControllerDefault) {
	CalendarNavigationControllerDefault calendarNavigationControllerDefault = ((CalendarNavigationControllerDefault)calendarNavigationController);
	
	JComboBox monthComboBox = calendarNavigationControllerDefault.getMonthComboBox();
	JTextField yearTextField = calendarNavigationControllerDefault.getYearTextField();
	JButton yearButtonUp = calendarNavigationControllerDefault.getYearButtonUp();
	JButton yearButtonDown = calendarNavigationControllerDefault.getYearButtonDown();
}

CalendarComboBox calendarComboBox = new CalendarComboBox();
CalendarComboBoxView calendarComboBoxView = calendarComboBox.getCalendarComboBoxView();
if (calendarComboBoxView instanceof CalendarComboBoxViewDefault) {
	CalendarComboBoxViewDefault calendarComboBoxViewDefault = ((CalendarComboBoxViewDefault)calendarComboBoxView);
	
	JTextField textField = calendarComboBoxViewDefault.getTextField();
	JButton buttonDown = calendarComboBoxViewDefault.getButtonDown();
}

}}
