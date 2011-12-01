package com.imagine.component.calendar.demo.panels.mainfeatures.table;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
  Vector data = new Vector();
  
  public CustomTableModel() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(2005, Calendar.JANUARY, 12);
    data.add(new TableLine("First Event", calendar.getTime()));
    calendar.set(1989, Calendar.DECEMBER, 22);
    data.add(new TableLine("Second Event", calendar.getTime()));
    calendar.set(1995, Calendar.MARCH, 23);
    data.add(new TableLine("Third Event", calendar.getTime()));
  }

  public int getColumnCount() {
    return 2;
  }
  
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

  public int getRowCount() {
    return data.size();
  }
  
  public String getColumnName(int column) {
    if (column == 0) {
      return "Event Name";
    } else {
      return "Date";
    }
  }
  
  public Class getColumnClass(int columnIndex) {
    if (columnIndex == 0) {
      return String.class;
    } else {
      return Date.class;
    }
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    Object value = null;
    TableLine tableLine = (TableLine)data.elementAt(rowIndex);
    if (columnIndex == 0) {
      value = tableLine.eventName;
    } else if (columnIndex == 1) {
      value = tableLine.date;
    }
    return value;
  }
  
  public void setValueAt(Object value, int rowIndex, int columnIndex) {
    TableLine tableLine = (TableLine)data.elementAt(rowIndex);
    if (columnIndex == 0) {
      tableLine.eventName = (String)value;
    } else if (columnIndex == 1) {
      tableLine.date = (Date)value;
    }
  }
}
