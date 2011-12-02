/**
 * 
 */
package com.imagine.component.calendar.demo.panels.skineditor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

class SkinFileFilter extends FileFilter {
	public boolean accept(File pathname) {
		if (pathname.isDirectory()) {
			return true;
		}
		if (pathname.getName().toLowerCase().endsWith(".skin")) {
			return true;
		}
		return false;
	}
	
	public String getDescription() {
		return "Calendar Skin Files (*.skin)";
	}
}