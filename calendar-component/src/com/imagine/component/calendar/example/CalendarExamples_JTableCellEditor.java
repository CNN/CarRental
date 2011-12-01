package com.imagine.component.calendar.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.imagine.component.calendar.CalendarTableCellEditor;

public class CalendarExamples_JTableCellEditor {

  public static void main(String[] args) {
final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

JTable table = new JTable(new Object[][] {{"First Event", new Date()}, {"Second Event", new Date()}}, new Object[] {"Event Name", "Date"});
table.setDefaultEditor(Date.class, new CalendarTableCellEditor());
table.setDefaultRenderer(Date.class, new DefaultTableCellRenderer() {
	protected void setValue(Object value) {
		if (value == null) {
			setText("null");
		} else {
			setText(simpleDateFormat.format((Date) value));
		}
	}
});
  }
}
