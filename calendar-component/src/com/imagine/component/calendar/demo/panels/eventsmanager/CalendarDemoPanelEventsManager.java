package com.imagine.component.calendar.demo.panels.eventsmanager;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.data.CalendarTableCellInfo;
import com.imagine.component.calendar.demo.CalendarDemoController;
import com.imagine.component.calendar.demo.panels.CalendarDemoPanel;
import com.imagine.component.calendar.demo.panels.eventsmanager.metrics.CalendarMetricsProviderCustom;
import com.imagine.component.calendar.demo.panels.eventsmanager.renderer.CalendarRendererProviderCustom;
import com.imagine.component.calendar.demo.panels.eventsmanager.visualentities.*;

public class CalendarDemoPanelEventsManager extends CalendarDemoPanel {
	
	private CalendarComponent calendarComponentEventsManager = new CalendarComponent();
	
	private Hashtable visualEntitiesManagers = new Hashtable();
	
	private EditEventDialog editEventDialog = null;
	
	private JPanel calendarEventsManagerPanel = new JPanel();
	
	private JPanel contextSensitiveHelpPanel = null;
	private JScrollPane contextSensitiveHelpScrollPane = null;
	private JTextArea contextSensitiveHelpTextArea = null;
	
	private JPanel eventDetailsPanel = null;
	private JScrollPane eventDetailsScrollPane = null;
	private JTextArea eventDetailsTextArea = null;
	
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	JPanel fileEventsPanel = new JPanel();
	JTextArea commentsTextArea = new JTextArea();
	JPanel commentsPanel = new JPanel();
	
	private String commentsHelpPath = "/resources/help/EventsManagerHelp.txt";
	
	private String commentsHelp = null;
	
	private String dataFieldDetails = "";
	
	public CalendarDemoPanelEventsManager(CalendarDemoController calendarDemoController) {
		super(calendarDemoController);
		
		initialize();
	}
	
	
	private void initialize() {
		editEventDialog = new EditEventDialog(this);
		
		createLeftPanel();
		createRightPanel();
		
		this.setLayout(new GridBagLayout());
		this.add(leftPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
		this.add(rightPanel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	private void createLeftPanel() {
		createCommentsPanel();
		createLoadSavePanel();
		createContextSensitiveHelp();
		createEventDetailsPanel();
		
		int count = 0;
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.add(commentsPanel, new GridBagConstraints(0, count, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		leftPanel.add(fileEventsPanel, new GridBagConstraints(0, count, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		leftPanel.add(contextSensitiveHelpPanel, new GridBagConstraints(0, count, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 150, 0));
		count++;
		leftPanel.add(eventDetailsPanel, new GridBagConstraints(0, count, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 30));
		count++;
	}
	
	private void createLoadSavePanel() {
		JButton loadFromFileButton = new JButton("Load Events From File");
		JButton saveToFileButton = new JButton("Save Events To File");
		
		fileEventsPanel.setBorder(BorderFactory.createTitledBorder("File"));
		fileEventsPanel.setLayout(new GridBagLayout());
		fileEventsPanel.add(loadFromFileButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		fileEventsPanel.add(saveToFileButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		loadFromFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JFileChooser fileChooser = new JFileChooser("./eventsManager");
					if (fileChooser.showOpenDialog(calendarDemoController.getCalendarDemo().getFrame()) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							loadEventsFromFile(file);
						} catch (Exception ex) {
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(CalendarDemoPanelEventsManager.this, 
							"<html>This function is not available when this demo is run through JNLP for security reasons. <br>" + 
							" If you want to view this feature please download the demo from the download section <br>" +
							" of the site and run the demo locally on your machine. " +
							"</html>"
					); 
				}
			}
		}
		);
		
		saveToFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JFileChooser fileChooser = new JFileChooser("./eventsManager");
					if (fileChooser.showSaveDialog(calendarDemoController.getCalendarDemo().getFrame()) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							saveEventsToFile(file);
						} catch (Exception ex) {
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(CalendarDemoPanelEventsManager.this, 
							"<html>This function is not available when this demo is run through JNLP for security reasons. <br>" + 
							" If you want to view this feature please download the demo from the download section <br>" +
							" of the site and run the demo locally on your machine. " +
							"</html>"
					); 
				}
			}
		}
		);
	}
	
	
	protected void saveEventsToFile(File file) {
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader("<EVENTS_MANAGER></EVENTS_MANAGER>")));
			Element root = document.getDocumentElement();
			Enumeration enumeration = visualEntitiesManagers.keys();
			while(enumeration.hasMoreElements()) {
				String key =  (String)enumeration.nextElement();
				Element keyElement = document.createElement("KEY");
				keyElement.setAttribute("value", key);
				root.appendChild(keyElement);
				
				VisualEntitiesManager visualEntitiesManager = (VisualEntitiesManager)visualEntitiesManagers.get(key);
				Vector visualEntities = (Vector)visualEntitiesManager.getVisualEntities();
				for (int i = 0, count = visualEntities.size(); i < count; i++) {
					VisualEntityDataField visualEntityDataField = (VisualEntityDataField)visualEntities.elementAt(i);
					Element eventElement = document.createElement("EVENT");
					keyElement.appendChild(eventElement);
					Element nameElement = document.createElement("NAME");
					eventElement.appendChild(nameElement);
					nameElement.appendChild(document.createTextNode(visualEntityDataField.getDataField().getName()));
					Element descriptionElement = document.createElement("DESCRIPTION");
					eventElement.appendChild(descriptionElement);
					descriptionElement.appendChild(document.createTextNode(visualEntityDataField.getDataField().getDescription()));
				}
			}
		    ((XmlDocument)document).write(outputStream);
		    outputStream.flush();
		    outputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(calendarEventsManagerPanel, "Error occured while saving the Events file: " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	private void loadEventsFromFile(File file) {
		try {
			// Empty the existing visual entities.
			visualEntitiesManagers.clear();
			
			InputStream inputStream = new FileInputStream(file);
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputStream));
			Element root = document.getDocumentElement();
			
			NodeList keyNodeList = root.getElementsByTagName("KEY");
			for (int i = 0, count = keyNodeList.getLength(); i < count; i++) {
				Element currentKeyElement = (Element)keyNodeList.item(i);
				String key = currentKeyElement.getAttribute("value");
				
				VisualEntitiesManager visualEntitiesManager = new VisualEntitiesManager(this, key);
				this.putVisualEntitiesManager(visualEntitiesManager);
				NodeList eventNodeList = currentKeyElement.getElementsByTagName("EVENT");
				for (int j = 0, eventCount = eventNodeList.getLength(); j < eventCount; j++) {
					Element currentEventElement = (Element)eventNodeList.item(j);
					String name = "";
					String description = "";
					name = currentEventElement.getElementsByTagName("NAME").item(0).getFirstChild().getNodeValue();
					description = currentEventElement.getElementsByTagName("DESCRIPTION").item(0).getFirstChild().getNodeValue();
					visualEntitiesManager.addVisualEntity(new VisualEntityDataField(visualEntitiesManager, new DataField(name, description)));
				}
			}
			inputStream.close();
			this.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(calendarEventsManagerPanel, "Invalid Events file: " + e.getMessage());
			e.printStackTrace();
		}    
	}
	
	
	private void createCommentsPanel() {
		commentsTextArea.setOpaque(true);
		commentsTextArea.setEditable(false);
		commentsTextArea.setColumns(20);
		commentsTextArea.setWrapStyleWord(true);
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setText(getComentsHelp());
		
		commentsPanel.setBorder(BorderFactory.createTitledBorder("Description"));
		commentsPanel.setLayout(new GridBagLayout());
		commentsPanel.add(commentsTextArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
	}
	
	private String getComentsHelp() {
		if (commentsHelp == null) {
			commentsHelp = getHelp(commentsHelpPath);
		}
		return commentsHelp;
	}
	
	private void createRightPanel() {
		createCalendarEventsPanel();
		
		int count = 0;
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.add(calendarEventsManagerPanel, new GridBagConstraints(0, count, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
	}
	
	private void createEventDetailsPanel() {
		eventDetailsTextArea = new JTextArea();
		eventDetailsTextArea.setRows(4);
		eventDetailsTextArea.setColumns(20);
		eventDetailsTextArea.setWrapStyleWord(true);
		
		eventDetailsPanel = new JPanel();
		eventDetailsScrollPane = new JScrollPane(eventDetailsTextArea);
		eventDetailsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventDetailsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		eventDetailsPanel.setBorder(BorderFactory.createTitledBorder("Event Details"));
		eventDetailsPanel.setLayout(new GridBagLayout());
		eventDetailsPanel.add(eventDetailsScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	
	private void createCalendarEventsPanel() {
		CalendarComponentListener calendarComponentListenerDisplayEvents = new CalendarComponentListenerDisplayEvents(this);
		calendarComponentEventsManager.addCalendarComponentListener(calendarComponentListenerDisplayEvents);
		
		CalendarComponentListener calendarComponentListenerVisualEntities = new CalendarComponentListenerVisualEntities(this);
		calendarComponentEventsManager.addCalendarComponentListener(calendarComponentListenerVisualEntities);
		
		calendarComponentEventsManager.setCalendarRendererProvider(new CalendarRendererProviderCustom(this));
		calendarComponentEventsManager.setCalendarMetricsProvider(new CalendarMetricsProviderCustom());
		calendarComponentEventsManager.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean (true));
		calendarComponentEventsManager.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean (true));
		calendarComponentEventsManager.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean (true));
		
		calendarEventsManagerPanel.setBorder(BorderFactory.createTitledBorder("Events Manager"));
		calendarEventsManagerPanel.setLayout(new GridBagLayout());
		calendarEventsManagerPanel.add(calendarComponentEventsManager, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
	}
	
	private void createContextSensitiveHelp() {
		contextSensitiveHelpTextArea = new JTextArea();
		contextSensitiveHelpTextArea.setRows(12);
		contextSensitiveHelpTextArea.setColumns(20);
		contextSensitiveHelpTextArea.setWrapStyleWord(true);
		
		contextSensitiveHelpPanel = new JPanel();
		contextSensitiveHelpScrollPane = new JScrollPane(contextSensitiveHelpTextArea);
		contextSensitiveHelpScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contextSensitiveHelpScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contextSensitiveHelpPanel.setBorder(BorderFactory.createTitledBorder("Context Sensitive Help"));
		contextSensitiveHelpPanel.setLayout(new GridBagLayout());
		contextSensitiveHelpPanel.add(contextSensitiveHelpScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	public String getTitle() {
		return "Events Manager";
	}
	
	public void setContextSensitiveHelp(String text) {
		contextSensitiveHelpTextArea.setText(text);
	}
	
	public CalendarComponent getCalendarComponentEventsManager() {
		return calendarComponentEventsManager;
	}
	
	public String calculateKey(CalendarTableCellInfo calendarTableCellInfo) {
		int month = (calendarTableCellInfo.getMonth() + 1);
		int dayOfMonth = calendarTableCellInfo.getDayOfMonth();
		StringBuffer result = new StringBuffer();
		if (month < 10) {
			result.append("0");
		}
		result.append(month);
		result.append(".");
		if (dayOfMonth < 10) {
			result.append("0");
		}
		result.append(dayOfMonth);
		return result.toString();
	}
	
	public void putVisualEntitiesManager(VisualEntitiesManager visualEntitiesManager) {
		visualEntitiesManagers.put(visualEntitiesManager.getKey(), visualEntitiesManager);
	}
	
	public VisualEntitiesManager getVisualEntitiesManager(String key) {
		return (VisualEntitiesManager)visualEntitiesManagers.get(key);
	}
	
	public void removeVisualEntitiesManager(String key) {
		visualEntitiesManagers.remove(key);
	}
	
	public EditEventDialog getEditEventDialog() {
		return editEventDialog;
	}
	
	/**
	 * Update the UI.
	 *
	 */
	public void updateUI() {
		super.updateUI();
		
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					JDialog dialog = editEventDialog.getDialog();
					SwingUtilities.updateComponentTreeUI(dialog);
					dialog.pack();
				} catch (Exception ex) {
				}
			}
		};
		SwingUtilities.invokeLater(runnable);
	}


	public void showDataFieldDetails(String text) {
		eventDetailsTextArea.setText(text);
	}
	
	public void setHoveredVisualEntity(VisualEntityDataField hoveredVisualEntity) {
		if (hoveredVisualEntity != null) {
			DataField dataField = hoveredVisualEntity.getDataField();
			String text = "Name: " + dataField.getName() + "\nDescription:" +  dataField.getDescription();
			this.showDataFieldDetails(text);
		} else {
			this.showDataFieldDetails("");
		}
	}
	
}
