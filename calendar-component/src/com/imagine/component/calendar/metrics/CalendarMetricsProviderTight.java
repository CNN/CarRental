package com.imagine.component.calendar.metrics;

import com.imagine.component.calendar.data.*;
import com.imagine.component.calendar.metrics.headercellmetrics.*;
import com.imagine.component.calendar.metrics.statuspanelmetrics.*;
import com.imagine.component.calendar.metrics.tablecellmetrics.*;
import com.imagine.component.calendar.metrics.weekcellmetrics.*;

/**
 * Define the tight metrics provider.
 */
public class CalendarMetricsProviderTight extends CalendarMetricsProvider {
  
  private CalendarTableCellMetrics calendarTableCellMetricsTight = new CalendarTableCellMetricsTight();
  
  private CalendarHeaderCellMetrics calendarHeaderCellMetricsTight = new CalendarHeaderCellMetricsTight();
  
  private CalendarWeekCellMetrics calendarWeekCellMetricsTight = new CalendarWeekCellMetricsTight();
  
  private CalendarStatusPanelMetrics calendarStatusPanelMetricsTight = new CalendarStatusPanelMetricsTight();
  
  /**
   * @inheritDoc
   */
  public CalendarTableCellMetrics getTableCellMetrics(CalendarTableCellInfo calendarTableCellInfo) {
    return calendarTableCellMetricsTight;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarWeekCellMetrics getWeekCellMetrics(CalendarWeekCellInfo calendarWeekCellInfo) {
    return calendarWeekCellMetricsTight;
  }
  
  /**
   * @inheritDoc
   */
  public CalendarHeaderCellMetrics getHeaderCellMetrics(CalendarHeaderCellInfo calendarHeaderCellInfo) {
    return calendarHeaderCellMetricsTight;
  }
  
  /**
   * @inheritDoc
   */
	public CalendarStatusPanelMetrics getStatusPanelMetrics(CalendarStatusPanelInfo calendarStatusPanelInfo) {
		return calendarStatusPanelMetricsTight;
	}
	
  
  /**
   * @inheritDoc
   */
  public String getName() {
    return "Tight";
  }

}
