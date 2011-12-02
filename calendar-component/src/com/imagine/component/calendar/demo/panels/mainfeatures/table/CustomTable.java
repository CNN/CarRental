package com.imagine.component.calendar.demo.panels.mainfeatures.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.*;

import com.imagine.component.calendar.CalendarTableCellEditor;

public class CustomTable extends JTable {
	
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
