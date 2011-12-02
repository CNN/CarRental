package com.imagine.component.calendar.example;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import com.imagine.component.calendar.CalendarTableCellEditor;

public class CalendarExamples_CalendarTableCellEditorUsage {

  public static void main(String[] args) {
CustomTable customTable = new CustomTable();

JFrame frame = new JFrame("Calendar Table Cell Editor");
JPanel panel = new JPanel();
panel.setLayout(new FlowLayout());
panel.add(customTable);
frame.getContentPane().add(panel);
frame.pack();
frame.setVisible(true);
  }
}

class CustomTable extends JTable {
	
	public CalendarTableCellEditor calendarTableCellEditor = new CalendarTableCellEditor();
	
	public CustomTable() {
		this.setModel(new CustomTableModel());
		this.setRowHeight(20);
		TableColumnModel tableColumnModel = this.getColumnModel();
		tableColumnModel.getColumn(0).setMinWidth(100);
		tableColumnModel.getColumn(1).setMinWidth(200);
		
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		this.setDefaultEditor(Date.class, calendarTableCellEditor);
		this.setDefaultRenderer(Date.class, new DefaultTableCellRenderer() {
			protected void setValue(Object value) {
				if (value == null) {
					setText("null");
				} else {
					setText(simpleDateFormat.format((Date) value));
				}
			}
		});
	}
	
	public void updateUI() {
		super.updateUI();
		
		if (calendarTableCellEditor != null) {
			calendarTableCellEditor.updateUI();
		}
	}
}

class CustomTableModel extends AbstractTableModel {
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


class TableLine {
  String eventName;
  Date date;
  
  public TableLine(String eventName, Date date) {
    this.eventName = eventName;
    this.date = date;
  }
}
