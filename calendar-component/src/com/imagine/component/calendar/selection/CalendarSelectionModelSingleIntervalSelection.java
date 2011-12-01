package com.imagine.component.calendar.selection;

import java.awt.event.*;
import java.util.*;

import com.imagine.component.calendar.CalendarComponent;
import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.events.*;
import com.imagine.util.events.*;

/**
 * Defines the selection model for single interval selection.
 *
 */
public class CalendarSelectionModelSingleIntervalSelection extends CalendarSelectionModel {

	private CalendarDate anchorCalendarDate = new CalendarDate();
	private CalendarDate leadCalendarDate = anchorCalendarDate;
	private CalendarSelectionModelAdapter calendarSelectionModelAdapter = new CalendarSelectionModelAdapter();

	private boolean dateAlreadySelectedAtMousePressTime = false;
	private CalendarDate calendarDateAtMousePressedTime = null;
	
	private final static int ACTION_NONE = 0;
	private final static int ACTION_ADJUST_SELECTION_INTERVAL = 1;
	private final static int ACTION_RESET_SELECTION_TO_SINGLE_ITEM = 2;
	private final static int ACTION_ADD_ITEM_TO_SELECTION = 3;
	private final static int ACTION_INVERT_CURRENT_ITEM_SELECTION = 4;
	private final static int ACTION_END_OF_DRAG_OR_CLICK = 5;
	
	private int internalAction = ACTION_NONE;

	/**
	 * Process the specified event received from the calendar component.
	 * @param calendarEventKeyAction The calendar input event.
	 * @return true if the event was processed.
	 */
	public boolean processKeyEvent(CalendarEventKeyAction calendarEventKeyAction) {
		boolean result = false;
		internalAction = ACTION_NONE;
		CalendarComponent calendarComponent = this.getCalendarComponent();
		CalendarPartInfo currentCalendarPartInfo = calendarComponent.getCalendarView().getMonthView().getCurrentCalendarPartInfo();

		if (currentCalendarPartInfo instanceof  CalendarTableCellInfo) {
			CalendarTableCellInfo currentCalendarTableCellInfo = (CalendarTableCellInfo)currentCalendarPartInfo;

			if (
				((currentCalendarTableCellInfo.getModifiers() & InputEvent.SHIFT_MASK) != 0) ||
				((currentCalendarTableCellInfo.getModifiers() & InputEvent.CTRL_MASK) != 0)
			) {
				internalAction = ACTION_ADJUST_SELECTION_INTERVAL;
			} else {
				internalAction = ACTION_RESET_SELECTION_TO_SINGLE_ITEM;
			}
			
			int MAX_ADVANCE = calendarComponent.getIntegerCalendarProperty(CalendarComponent.MAX_ADVANCE_OVER_NON_SELECTABLE_DATES).intValue();
			KeyEvent keyEvent = calendarEventKeyAction.getKeyEvent();
			int keyCode = keyEvent.getKeyCode();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(currentCalendarTableCellInfo.getDate());

			KeyEventType keyEventType = calendarEventKeyAction.getKeyEventType();

			if (keyEventType ==  KeyEventType.KEY_PRESSED) {

				int advanceAmount = 0;
				if (keyCode == KeyEvent.VK_BACK_SPACE && calendarComponent.getCalendarSelectionModel().isEmptySelectionAllowed()) {

					calendarComponent.getCalendarSelectionModel().setSelectedDates(new Date[] {});
				} else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {

					advanceAmount = -7;
				} else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {

					advanceAmount = 7;
				} else if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {

					advanceAmount = -1;
				} else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {

					advanceAmount = 1;
				}

				if (advanceAmount != 0) {
					gregorianCalendar.add(Calendar.DAY_OF_MONTH, advanceAmount);

					int count = 0;
					for (; count <= MAX_ADVANCE && !isDateSelectable(gregorianCalendar.getTime()); count++) {
						gregorianCalendar.add(Calendar.DAY_OF_MONTH, advanceAmount);
					}
					if (count == MAX_ADVANCE) {
						return false;
					}

					calendarComponent.getCalendarView().getMonthView().setMonthViewCurrentDate(gregorianCalendar.getTime());

					CalendarPartInfo calendarPartInfo = calendarComponent.getCalendarView().getMonthView().getCurrentCalendarPartInfo();
					if (calendarPartInfo instanceof CalendarTableCellInfo){
						CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;

						result = cellAdjustment(calendarTableCellInfo.getDate(), calendarTableCellInfo.getModifiers());
						if (result) {
							this.getCalendarComponent().processCalendarEvent(new CalendarEventDateSelectedByKey(this.getCalendarComponent(), calendarTableCellInfo.getMonthType(), this.getLeadSelectionDate()));
						}
					} else if (calendarPartInfo instanceof CalendarHeaderCellInfo){
					} else if (calendarPartInfo instanceof CalendarWeekCellInfo){
					}
				}
			}
		}
		return result;
	}

	/**
	 * Process the specified event received from the calendar component.
	 * @param calendarEventMouseAction The calendar input event.
	 * @return true if the event was processed.
	 */
	public boolean processMouseEvent(CalendarEventMouseAction calendarEventMouseAction) {
		boolean result = false;
		internalAction = ACTION_NONE;
		
		CalendarPartInfo calendarPartInfo = calendarEventMouseAction.getRelatedCalendarPartInfo();
		if (calendarPartInfo instanceof CalendarTableCellInfo){
			CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;

			if (calendarEventMouseAction.getMouseEventType() == MouseEventType.MOUSE_RELEASED) {
				if ((calendarTableCellInfo.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
					internalAction = ACTION_NONE;
				} else if ((calendarTableCellInfo.getModifiers() & InputEvent.CTRL_MASK) != 0) {
					internalAction = ACTION_NONE;
				} else {
					internalAction = ACTION_END_OF_DRAG_OR_CLICK;
				}
			} else if (calendarEventMouseAction.getMouseEventType() == MouseEventType.MOUSE_CLICKED) {
				CalendarDate calendarDateAtMouseReleasedTime = new CalendarDate(calendarTableCellInfo.getDate());
				if (calendarDateAtMouseReleasedTime.compareTo(calendarDateAtMousePressedTime) == 0) {
					// The click action has sense only if the current item did not changed.
					// The current item may change only if the current month is changed from the time the MOUSE_PRESSED event occurred.
					if ((calendarTableCellInfo.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
						// The selection was already extended at MOUSE_PRESSED.
						internalAction = ACTION_NONE;
					} else if ((calendarTableCellInfo.getModifiers() & InputEvent.CTRL_MASK) != 0) {
						if (dateAlreadySelectedAtMousePressTime) {
							internalAction = ACTION_INVERT_CURRENT_ITEM_SELECTION;
						}
					} else {
						internalAction = ACTION_RESET_SELECTION_TO_SINGLE_ITEM;
					}
				}
			} else if (calendarEventMouseAction.getMouseEventType() == MouseEventType.MOUSE_PRESSED) {
				dateAlreadySelectedAtMousePressTime = isDateSelected(calendarTableCellInfo.getDate());
				calendarDateAtMousePressedTime = new CalendarDate(calendarTableCellInfo.getDate());
				
				if ((calendarTableCellInfo.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
					internalAction = ACTION_ADJUST_SELECTION_INTERVAL;
				} else if ((calendarTableCellInfo.getModifiers() & InputEvent.CTRL_MASK) != 0) {
					internalAction = ACTION_ADD_ITEM_TO_SELECTION;
				} else {
					internalAction = ACTION_RESET_SELECTION_TO_SINGLE_ITEM;
				}
			}
			
			if (internalAction != ACTION_NONE) {
				result = cellAdjustment(calendarTableCellInfo.getDate(), calendarTableCellInfo.getModifiers());
			}
		}

		if (calendarEventMouseAction.getMouseEventType() == MouseEventType.MOUSE_PRESSED ||
				calendarEventMouseAction.getMouseEventType() == MouseEventType.MOUSE_RELEASED) {
			
			if (calendarPartInfo instanceof CalendarTableCellInfo){
				CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventDateSelectedByMouse(this.getCalendarComponent(), calendarTableCellInfo.getMonthType(), this.getLeadSelectionDate()));
			} else if (calendarPartInfo instanceof CalendarHeaderCellInfo){
				CalendarHeaderCellInfo calendarHeaderCellInfo = (CalendarHeaderCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventHeaderCellSelectedByMouse(this.getCalendarComponent(), calendarHeaderCellInfo.getDayOfWeek()));			
			} else if (calendarPartInfo instanceof CalendarWeekCellInfo){
				CalendarWeekCellInfo calendarWeekCellInfo = (CalendarWeekCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventWeekCellSelectedByMouse(this.getCalendarComponent(), calendarWeekCellInfo.getWeekOfYear()));			
			}
		}
		return result;
	}

	/**
	 * Process the specified event received from the calendar component.
	 * @param calendarEventMouseMotionAction The calendar input event.
	 * @return true if the event was processed.
	 */
	public boolean processMouseMotionEvent(CalendarEventMouseMotionAction calendarEventMouseMotionAction) {
		boolean result = false;
		internalAction = ACTION_NONE;
		if (calendarEventMouseMotionAction.getMouseMotionEventType() == MouseMotionEventType.MOUSE_DRAGGED) {
			CalendarPartInfo calendarPartInfo = calendarEventMouseMotionAction.getRelatedCalendarPartInfo();
			
			if (calendarPartInfo instanceof CalendarTableCellInfo){
				CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;

				internalAction = ACTION_ADJUST_SELECTION_INTERVAL;
				
				if (internalAction != ACTION_NONE) {
					result = cellAdjustment(calendarTableCellInfo.getDate(), calendarTableCellInfo.getModifiers());
				}
			}
			
			if (calendarPartInfo instanceof CalendarTableCellInfo){
				CalendarTableCellInfo calendarTableCellInfo = (CalendarTableCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventDateSelectedByMouse(this.getCalendarComponent(), calendarTableCellInfo.getMonthType(), this.getLeadSelectionDate()));
			} else if (calendarPartInfo instanceof CalendarHeaderCellInfo){
				CalendarHeaderCellInfo calendarHeaderCellInfo = (CalendarHeaderCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventHeaderCellSelectedByMouse(this.getCalendarComponent(), calendarHeaderCellInfo.getDayOfWeek()));			
			} else if (calendarPartInfo instanceof CalendarWeekCellInfo){
				CalendarWeekCellInfo calendarWeekCellInfo = (CalendarWeekCellInfo)calendarPartInfo;

				this.getCalendarComponent().processCalendarEvent(new CalendarEventWeekCellSelectedByMouse(this.getCalendarComponent(), calendarWeekCellInfo.getWeekOfYear()));			
			}
		}
		return result;
	}

	/**
	 * Internally used to adjust the selections of the dates.
	 * @param date The new date.
	 * @param modifiers The modifiers (shift, ctrl, etc).
	 */
	private boolean cellAdjustment(Date date, int modifiers) {
		if (!isDateSelectable(date)) {
			return false;
		}
		CalendarDate newCalendarDate = new CalendarDate(date);

		if (this.internalAction == ACTION_ADJUST_SELECTION_INTERVAL) {
			// Adjust cell selection.
			leadCalendarDate = newCalendarDate;
			
			this.getCalendarComponent().getCalendarView().getMonthView().setMonthViewCurrentDate(this.getLeadSelectionDate());
		} else if (this.internalAction == ACTION_ADD_ITEM_TO_SELECTION) {
			if (anchorCalendarDate.wrapsNullDate() || leadCalendarDate.wrapsNullDate()) {
				anchorCalendarDate = leadCalendarDate = newCalendarDate;
			} else if (this.isEmptySelectionAllowed() && anchorCalendarDate.equals(leadCalendarDate) && anchorCalendarDate.equals(newCalendarDate)) {
				;
			} else {
				if ((anchorCalendarDate.compareTo(newCalendarDate) * newCalendarDate.compareTo(leadCalendarDate) < 0 )) {
					if (anchorCalendarDate.compareTo(leadCalendarDate) <= 0) {
						if (newCalendarDate.add(1).equals(anchorCalendarDate)) {
							anchorCalendarDate = newCalendarDate;
						} else if (newCalendarDate.add(-1).equals(leadCalendarDate)) {
							leadCalendarDate = newCalendarDate;
						} else {
							anchorCalendarDate = leadCalendarDate = newCalendarDate;
						}
					} else if (leadCalendarDate.compareTo(anchorCalendarDate) < 0) {
						if (newCalendarDate.add(1).equals(leadCalendarDate)) {
							leadCalendarDate = newCalendarDate;
						} else if (newCalendarDate.add(-1).equals(anchorCalendarDate)) {
							anchorCalendarDate = newCalendarDate;
						} else {
							leadCalendarDate = anchorCalendarDate = newCalendarDate;
						}
					}
				}
			}
			
			this.getCalendarComponent().getCalendarView().getMonthView().setMonthViewCurrentDate(this.getLeadSelectionDate());
		} else if (this.internalAction == ACTION_INVERT_CURRENT_ITEM_SELECTION) {
			if (anchorCalendarDate.wrapsNullDate() || leadCalendarDate.wrapsNullDate()) {
				anchorCalendarDate = leadCalendarDate = newCalendarDate;
			} else if (this.isEmptySelectionAllowed() && anchorCalendarDate.equals(leadCalendarDate) && anchorCalendarDate.equals(newCalendarDate)) {
				anchorCalendarDate = leadCalendarDate = new CalendarDate((Date)null);
			} else {
				if ((anchorCalendarDate.compareTo(newCalendarDate) * newCalendarDate.compareTo(leadCalendarDate) >= 0 )) {
					// The date is already selected.
					if (anchorCalendarDate.equals(newCalendarDate)) {
						if (anchorCalendarDate.compareTo(leadCalendarDate) < 0) {
							anchorCalendarDate = anchorCalendarDate.add(1);
						} else if (anchorCalendarDate.compareTo(leadCalendarDate) > 0) {
							anchorCalendarDate = anchorCalendarDate.add(-1);
						}
					} else if (leadCalendarDate.equals(newCalendarDate)) {
						if (leadCalendarDate.compareTo(anchorCalendarDate) < 0) {
							leadCalendarDate = leadCalendarDate.add(1);
						} else if (leadCalendarDate.compareTo(anchorCalendarDate) > 0) {
							leadCalendarDate = leadCalendarDate.add(-1);
						}
					} else {
						anchorCalendarDate = leadCalendarDate = newCalendarDate;
					}
				}
			}
			
			this.getCalendarComponent().getCalendarView().getMonthView().setMonthViewCurrentDate(this.getLeadSelectionDate());
		} else if (this.internalAction == ACTION_END_OF_DRAG_OR_CLICK) { 
			
			this.getCalendarComponent().getCalendarView().getMonthView().setMonthViewCurrentDate(this.getLeadSelectionDate());
		} else if (this.internalAction == ACTION_RESET_SELECTION_TO_SINGLE_ITEM) { 
			anchorCalendarDate = leadCalendarDate = newCalendarDate;
			
			this.getCalendarComponent().getCalendarView().getMonthView().setMonthViewCurrentDate(this.getLeadSelectionDate());
		}
		calendarSelectionModelAdapter.selectionChanged(this);
		return true;
	}

	private void addSelectableCalendarDatesInInterval (Vector whereToAdd, CalendarDate firstCalendarDate, CalendarDate secondCalendarDate) {
		if (firstCalendarDate.wrapsNullDate() && secondCalendarDate.wrapsNullDate()) {
			//Just return. The vector remains empty.
			;
		} else if (firstCalendarDate.wrapsNullDate()) {
			if (!whereToAdd.contains(secondCalendarDate)  && (isDateSelectable(secondCalendarDate.createDate()))) {
				whereToAdd.add(secondCalendarDate);
			}
		} else if (secondCalendarDate.wrapsNullDate()) {
			if (!whereToAdd.contains(firstCalendarDate) && (isDateSelectable(firstCalendarDate.createDate()))) {
				whereToAdd.add(firstCalendarDate);
			}
		} else {
			int result = secondCalendarDate.compareTo(firstCalendarDate);
			if (result == 0) {
				if (!whereToAdd.contains(firstCalendarDate) && (isDateSelectable(firstCalendarDate.createDate()))) {
					whereToAdd.add(firstCalendarDate);
				}
			} else if (result < 0) {
				// the new date is smaller than the current date
				while (result <= 0) {
					if (!whereToAdd.contains(firstCalendarDate) && (isDateSelectable(firstCalendarDate.createDate()))) {
						whereToAdd.add(firstCalendarDate);
					}
					firstCalendarDate = firstCalendarDate.add(-1);
					result = secondCalendarDate.compareTo(firstCalendarDate);
				}

			} else if (result > 0) {
				// the new date is greater than the current date
				while (result >= 0) {
					if (!whereToAdd.contains(firstCalendarDate) && (isDateSelectable(firstCalendarDate.createDate()))) {
						whereToAdd.add(firstCalendarDate);
					}
					firstCalendarDate = firstCalendarDate.add(1);
					result = secondCalendarDate.compareTo(firstCalendarDate);
				}
			}
		}
	}



	/**
	 * @inheritDoc
	 */
	public boolean isDateSelected(Date date) {
		CalendarDate tempCalendarDate = new CalendarDate(date);
		return isDateSelectable(date) && (anchorCalendarDate.compareTo(tempCalendarDate) * tempCalendarDate.compareTo(leadCalendarDate) >= 0 );
	}

	/**
	 * @inheritDoc
	 */
	public void setSelectedDates(Date[] dates) {
		if (dates.length == 0) {
			if (this.isEmptySelectionAllowed()) {
				anchorCalendarDate = leadCalendarDate =  new CalendarDate((Date)null);
			}
		} else {
			Date minDate = dates[0];
			Date maxDate = dates[0];
			for (int i = 1; i < dates.length; i++) {
				if (!isDateSelectable(dates[i])) {
					continue;
				}
				if (dates[i].getTime() < minDate.getTime()) {
					minDate = dates[i];
				}
				if (dates[i].getTime() > maxDate.getTime()) {
					maxDate = dates[i];
				}
			}
			anchorCalendarDate = new CalendarDate(minDate);
			leadCalendarDate = new CalendarDate(maxDate);
		}

		calendarSelectionModelAdapter.selectionChanged(this);		
	}

	/**
	 * @inheritDoc
	 */
	public  Date[] getSelectedDates() {
		Vector dates = new Vector();
		addSelectableCalendarDatesInInterval(dates, anchorCalendarDate, leadCalendarDate);
		if (dates.size() == 0) {
			return new Date[] {};
		}
		Date[] resultDates = new Date[dates.size()];
		for (int i = 0; i < resultDates.length; i++) {
			resultDates[i] = ((CalendarDate)dates.elementAt(i)).createDate();
		}
		return resultDates;
	}

	/**
	 * @inheritDoc
	 */
	public Date getAnchorSelectionDate() {
		return anchorCalendarDate.createDate();
	}

	/**
	 * @inheritDoc
	 */
	public void setAnchorSelectionDate(Date date) {
		if (!isDateSelectable(date)) {
			return;
		}
		this.anchorCalendarDate = new CalendarDate(date);
		calendarSelectionModelAdapter.selectionChanged(this);		
	}

	/**
	 * @inheritDoc
	 */
	public Date getLeadSelectionDate() {
		return leadCalendarDate.createDate();
	}

	/**
	 * @inheritDoc
	 */
	public void setLeadSelectionDate(Date date) {
		if (!isDateSelectable(date)) {
			return;
		}
		this.leadCalendarDate = new CalendarDate(date);
		calendarSelectionModelAdapter.selectionChanged(this);
	}


	/**
	 * @inheritDoc
	 */
	public void addCalendarSelectionListener(CalendarSelectionListener calendarSelectionListener) {
		calendarSelectionModelAdapter.addCalendarSelectionListener(calendarSelectionListener);
	}

	/**
	 * @inheritDoc
	 */
	public boolean removeCalendarSelectionListener(CalendarSelectionListener calendarSelectionListener) {
		return calendarSelectionModelAdapter.removeCalendarSelectionListener(calendarSelectionListener);
	}

	/**
	 * @inheritDoc
	 */
	public String getName() {
		return "Single Interval Selection";
	}
}
