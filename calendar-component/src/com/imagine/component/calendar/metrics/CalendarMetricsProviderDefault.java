package com.imagine.component.calendar.metrics;

import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.metrics.headercellmetrics.*;
import com.imagine.component.calendar.metrics.statuspanelmetrics.*;
import com.imagine.component.calendar.metrics.tablecellmetrics.*;
import com.imagine.component.calendar.metrics.weekcellmetrics.*;

/**
 * Define the default metrics provider.
 */
public class CalendarMetricsProviderDefault extends CalendarMetricsProvider {
  
  /**
   * These variable are declared protected and not private in order to be possible for who wants to define another provider
   * with the same behaviour but with different metrics to just set in constructor another value for one of these
   * and not to be forced to rewrite the provider functions.
   */
	protected CalendarTableCellMetrics calendarTableCellMetricsDefault = new CalendarTableCellMetricsDefault();
	protected CalendarHeaderCellMetrics calendarHeaderCellMetricsDefault = new CalendarHeaderCellMetricsDefault();
	protected CalendarWeekCellMetrics calendarWeekCellMetricsDefault = new CalendarWeekCellMetricsDefault();
	protected CalendarStatusPanelMetrics calendarStatusPanelMetricsDefault = new CalendarStatusPanelMetricsDefault();
  
  /**
   * @inheritDoc
   */
  public CalendarTableCellMetrics getTableCellMetrics(CalendarTableCellInfo calendarTableCellInfo) {
    return calendarTableCellMetricsDefault;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarWeekCellMetrics getWeekCellMetrics(CalendarWeekCellInfo calendarWeekCellInfo) {
    return calendarWeekCellMetricsDefault;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarHeaderCellMetrics getHeaderCellMetrics(CalendarHeaderCellInfo calendarHeaderCellInfo) {
    return calendarHeaderCellMetricsDefault;
  }
  
  /**
   * @inheritDoc
   */
	public CalendarStatusPanelMetrics getStatusPanelMetrics(CalendarStatusPanelInfo calendarStatusPanelInfo) {
		return calendarStatusPanelMetricsDefault;
	}
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Default";
  }
}
