package com.imagine.component.calendar.demo.panels.eventsmanager.visualentities;

import java.awt.*;

public class VisualEntityDataField extends VisualEntity {

	private DataField dataField;
	
	public VisualEntityDataField(VisualEntitiesManager visualEntitiesManager, DataField dataField) {
	  super(visualEntitiesManager);
	  
	  this.dataField = dataField;
  }

	public DataField getDataField() {
  	return dataField;
  }

	public void setDataField(DataField dataField) {
  	this.dataField = dataField;
  }

	public void paint(Graphics g) {
		Color color = g.getColor();
		if (this.isHovered()) {
			g.setColor(Color.GREEN);
			visualEntitiesManager.setHoveredVisualEntity(this);
		}
		String text = dataField.getName();
		if (text != null) {
			g.drawRect(location.x, location.y, size.width, size.height);
			Shape clip = g.getClip();
			g.setClip(location.x, location.y, size.width, size.height);
			g.drawString(text, location.x + 2, location.y + size.height - 2);
			g.setClip(clip);
		}
		g.setColor(color);
  }
}
