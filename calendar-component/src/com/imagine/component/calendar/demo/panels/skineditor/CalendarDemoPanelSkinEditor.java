package com.imagine.component.calendar.demo.panels.skineditor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.*;

import org.xml.sax.SAXException;

import com.imagine.component.calendar.*;
import com.imagine.component.calendar.data.CalendarNavigationController;
import com.imagine.component.calendar.demo.CalendarDemoController;
import com.imagine.component.calendar.demo.panels.CalendarDemoPanel;
import com.imagine.component.calendar.events.*;
import com.imagine.component.calendar.selection.CalendarSelectionModelMultipleIntervalSelection;
import com.imagine.component.calendar.skins.CalendarSkin;
import com.imagine.component.calendar.skins.external.*;
import com.imagine.component.calendar.skins.styles.CalendarSkinStyleDefault;

public class CalendarDemoPanelSkinEditor extends CalendarDemoPanel {
	
	private CalendarComponent calendarComponent = null;
	
	JLabel skinNameLabel;
	JTextField skinNameTextField;
	
	JLabel navigationControllerStyleLabel;
	JComboBox navigationControllerStyleComboBox;
	
	ColorComponent[]  colorComponents = new ColorComponent[0];
	FontComponent[]  fontComponents = new FontComponent[0];
	ImageComponent[] imageComponents = new ImageComponent[0];
	
	JPanel fileSkinsPanel = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel urlSkinsPanel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel skinNamePanel = new JPanel();
	JPanel navigationControllerStylePanel = new JPanel();
	JPanel colorsPanel = new JPanel();
	JPanel fontsPanel = new JPanel();
	JPanel imagesPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	public CalendarDemoPanelSkinEditor(CalendarDemoController calendarDemoController) {
		super(calendarDemoController);
		
		initialize();
	}
	
	public void initialize() {
		JPanel panel = new JPanel();
		
		createLeftPanel();
		createRightPanel();
		
		this.setLayout(new GridBagLayout());
		this.add(leftPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(15, 5, 5, 5), 0, 0));
		this.add(rightPanel, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(25, 5, 5, 5), 0, 0));
		this.add(new JPanel(), new GridBagConstraints(2, 0, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleDefault());
		updateInterface();
	}
	
	private void createLeftPanel() {
		createDatePanel();
		createNavigationControllerStylePanel();
		createFileSkinsPanel();
		createUrlSkinsPanel();
		
		int count = 0;
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.add(datePanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		leftPanel.add(navigationControllerStylePanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		leftPanel.add(fileSkinsPanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		leftPanel.add(urlSkinsPanel, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		
	}
	
	private void createRightPanel() {
		createTopPanel();
		createColorsPanel();
		createFontsPanel();
		createImagesPanel();
		
		updateInterface();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.add(topPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		rightPanel.add(tabbedPane, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		tabbedPane.add(colorsPanel, "Colors");
		tabbedPane.add(fontsPanel, "Fonts");
		tabbedPane.add(imagesPanel, "Images");
	}
	
	private void createImagesPanel() {
		CalendarSkin calendarSkin = calendarComponent.getCalendarSkin();
		
		imageComponents = new ImageComponent[] {
				new ImageComponent(calendarSkin, CalendarSkin.BACKGROUND_IMAGE, "Background Image"),
		};
		
		int count = 0;
		int i = 0;
		imagesPanel.setLayout(new GridBagLayout());
		for (; count < imageComponents.length; count++) {
			imagesPanel.add(imageComponents[count], new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		}
		imagesPanel.add(new JPanel(), new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	private void createFontsPanel() {
		CalendarSkin calendarSkin = calendarComponent.getCalendarSkin();
		
		fontComponents = new FontComponent[] {
				new FontComponent(calendarSkin, CalendarSkin.CELL_FONT, "Cell Font"),
				new FontComponent(calendarSkin, CalendarSkin.WEEKEND_CELL_FONT, "Weekend Cell Font"),
				new FontComponent(calendarSkin, CalendarSkin.EXTRA_MONTH_CELL_FONT, "Extra Month Cell Font"),
				new FontComponent(calendarSkin, CalendarSkin.HEADER_CELL_FONT, "Header Font"),
				new FontComponent(calendarSkin, CalendarSkin.WEEK_CELL_FONT, "Week Font"),
				new FontComponent(calendarSkin, CalendarSkin.STATUS_PANEL_FONT, "Status Panel Font"),
		};
		
		int count = 0;
		int middle = (fontComponents.length + 1) / 2;
		int i = 0;
		int j = 0;
		fontsPanel.setLayout(new GridBagLayout());
		for (; count < fontComponents.length; count++) {
			fontsPanel.add(fontComponents[count], new GridBagConstraints(i, j, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			j++;
			if (j >= middle) {
				j = 0;
				i++;
			}
		}
		fontsPanel.add(new JPanel(), new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}
	
	private void createColorsPanel() {
		CalendarSkin calendarSkin = calendarComponent.getCalendarSkin();
		
		colorComponents = new ColorComponent[] {
				new ColorComponent(calendarSkin, CalendarSkin.CELL_TEXT_COLOR, "Cell Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.CELL_FILL_COLOR, "Cell Fill Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.WEEKEND_CELL_TEXT_COLOR, "Weekend Cell Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.EXTRA_MONTH_CELL_TEXT_COLOR, "Extra Month Cell Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.TODAY_COLOR, "Today Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.DISABLED_COLOR, "Disabled Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.GRID_COLOR, "Grid Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.SELECTED_CELL_TEXT_COLOR, "Selected Cell Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.SELECTED_CELL_FILL_COLOR, "Selected Cell Fill Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.HOVERED_CELL_TEXT_COLOR, "Hovered Cell Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.HOVERED_CELL_FILL_COLOR, "Hovered Cell Fill Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.HEADER_CELL_TEXT_COLOR, "Header Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.HEADER_CELL_FILL_COLOR, "Header Fill Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.WEEK_CELL_TEXT_COLOR, "Week Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.WEEK_CELL_FILL_COLOR, "Week Fill Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.STATUS_PANEL_TEXT_COLOR, "Status Panel Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.STATUS_PANEL_HOVERED_TEXT_COLOR, "Status Panel Hovered Text Color:"),
				new ColorComponent(calendarSkin, CalendarSkin.STATUS_PANEL_FILL_COLOR, "Status Panel Fill Color:"),
		};
		
		colorsPanel.setLayout(new GridBagLayout());
		int count = 0;
		for (; count < colorComponents.length; count++) {
			colorsPanel.add(colorComponents[count].getLabel(), new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			colorsPanel.add(colorComponents[count], new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		colorsPanel.add(new JPanel(), new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	private void createTopPanel() {
		createSkinPanel();
		
		topPanel.setLayout(new GridBagLayout());
		topPanel.add(skinNamePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	private void createSkinPanel() {
		skinNameLabel = new JLabel("Skin Name:");
		skinNameTextField = new JTextField(10);
		
		skinNamePanel.setBorder(BorderFactory.createTitledBorder("Skin Name"));
		skinNamePanel.setLayout(new GridBagLayout());
		skinNamePanel.add(skinNameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		skinNamePanel.add(skinNameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		skinNameTextField.setText(calendarComponent.getCalendarSkin().getName());
	}

	private void createNavigationControllerStylePanel() {
		navigationControllerStyleLabel = new JLabel("Navigation Controller Style:");
		navigationControllerStyleComboBox = new JComboBox(calendarDemoController.getCalendarNavigationControllers());
		
		navigationControllerStyleComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					JComboBox source = ((JComboBox)itemEvent.getSource());
					int selectedIndex = source.getSelectedIndex();
					if (selectedIndex < 0 || selectedIndex >= source.getItemCount()) {
						selectedIndex = 0;
					}
					Object currentItem = calendarDemoController.getCalendarNavigationControllers()[selectedIndex];
					CalendarNavigationController calendarNavigationController = null;
					if (currentItem instanceof CalendarNavigationController) {
						calendarNavigationController = (CalendarNavigationController)currentItem;
					} else {
						calendarNavigationController = null;
					}
					try {
						if (calendarNavigationController == null) {
							calendarComponent.getCalendarView().setNavigationController(null);
						} else {
							calendarComponent.getCalendarView().setNavigationController((CalendarNavigationController) calendarNavigationController.getClass().newInstance());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					calendarDemoController.getCalendarDemo().getFrame().pack();
				}
			}
		});
		navigationControllerStyleComboBox.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				
				if (value != null) {
					setText(value.toString());
				} else {
					setText("null");
				}
				
				return label;
			}
		});
		
		
		navigationControllerStylePanel.setBorder(BorderFactory.createTitledBorder("Navigation Controller"));
		navigationControllerStylePanel.setLayout(new GridBagLayout());
		navigationControllerStylePanel.add(navigationControllerStyleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		navigationControllerStylePanel.add(navigationControllerStyleComboBox, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	private void createUrlSkinsPanel() {
		String[] urls = new String[] {
				" ",  
				"http://www.calendarcomponent.com/skins/skin01.skin",  
				"http://www.calendarcomponent.com/skins/skin02.skin",  
				"http://www.calendarcomponent.com/skins/skin03.skin",  
//				"http://www.calendarcomponent.com/skins/skin04.skin"  
		};
		try {
			Vector urlsVector = new Vector();
			urlsVector.add(" ");
			URLConnection connection = new URL("http://www.calendarcomponent.com/skins/skins.php").openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				urlsVector.add(line.trim());
			}
			((HttpURLConnection)connection).disconnect();
			urls = (String[])urlsVector.toArray(new String[urlsVector.size()]); 
		} catch (Exception ex) {
		}
		JComboBox urlComboBox = new JComboBox(urls);
		urlComboBox.setEditable(true);
		urlComboBox.setMaximumRowCount(5);
		
		urlComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox)e.getSource();
				String urlText = (String)comboBox.getSelectedItem();
				try {
					URL url = new URL(urlText);
					calendarComponent.getCalendarSkin().applyCalendarSkinStyle(new CalendarSkinStyleExternalURL(url));
					updateInterface();          
				} catch (MalformedURLException ex) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Invalid URL entered: " + ex.getMessage()); 
				} catch (FileNotFoundException ex2) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "The skin specified was not found: " + ex2.getMessage()); 
				} catch (SAXException ex3) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Invalid skin: " + ex3.getMessage()); 
				} catch (IOException ex4) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "The skin specified was not found: " + ex4.getMessage()); 
				} catch (ParserConfigurationException ex5) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Invalid skin: " + ex5.getMessage()); 
				} catch (FactoryConfigurationError ex6) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Invalid skin: " + ex6.getMessage()); 
				} catch (NullPointerException ex7) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Invalid URL entered: " + ex7.getMessage()); 
				} catch (Exception ex8) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, "Skin could not be loaded: " + ex8.getMessage()); 
				}
			}
		});
		
		urlSkinsPanel.setBorder(BorderFactory.createTitledBorder("URL"));
		urlSkinsPanel.setLayout(new GridBagLayout());
		urlSkinsPanel.add(urlComboBox, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	private void createDatePanel() {
		calendarComponent = new CalendarComponent();
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_GRID, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_WEEK, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_HOVER, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_EXTRA_MONTH_DAYS, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.SHOW_STATUS_PANEL, new Boolean(true));
		calendarComponent.setCalendarProperty(CalendarComponent.FIRST_DAY_OF_WEEK, new Integer(Calendar.MONDAY));
		calendarComponent.setCalendarProperty(CalendarComponent.MAX_WEEK_DAYS_NAMES_LENGTH, new Integer(3));
		
		calendarComponent.setCalendarSelectionModel(new CalendarSelectionModelMultipleIntervalSelection() {
			private GregorianCalendar calendar = new GregorianCalendar();
			
			public boolean isDateDisabled(Date date) {
				calendar.setTime(date);
				return (calendar.get(Calendar.DAY_OF_MONTH) == 15);
			}
		});
		calendarComponent.getCalendarSelectionModel().setEmptySelectionAllowed(true);
		
		calendarComponent.addCalendarComponentListener(new CalendarComponentListener() {
			public void processCalendarEvent(CalendarEvent calendarEvent) {
				if (calendarEvent instanceof CalendarEventSkinPropertyChanged) {
					CalendarEventSkinPropertyChanged calendarEventSkinPropertyChanged = (CalendarEventSkinPropertyChanged)calendarEvent;
					if (calendarEventSkinPropertyChanged.getCalendarProperty().getType().equals(Font.class)) {
						CalendarDemoPanelSkinEditor.this.getCalendarDemoController().getCalendarDemo().getFrame().pack();
					}
				}
			}
			
			public Class[] getEventsListened() {
				return new Class[] {
						CalendarEventSkinPropertyChanged.class,
				};
			}
		});
		
		datePanel.setLayout(new GridBagLayout());
		datePanel.add(calendarComponent, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		datePanel.setBorder(BorderFactory.createTitledBorder("Date"));
	}
	
	private void createFileSkinsPanel() {
		JButton loadFromFileButton = new JButton("Load Skin From File");
		loadFromFileButton.setHorizontalTextPosition(SwingConstants.LEFT);
		loadFromFileButton.setMnemonic('L');
		
		JButton saveToFileButton = new JButton("Save Skin To File");
		saveToFileButton.setHorizontalTextPosition(SwingConstants.LEFT);
		saveToFileButton.setMnemonic('S');
		
		JButton saveSkinPictureToFileButton = new JButton("Save Skin Picture To File");
		saveSkinPictureToFileButton.setHorizontalTextPosition(SwingConstants.LEFT);
		saveSkinPictureToFileButton.setMnemonic('P');
		
		fileSkinsPanel.setBorder(BorderFactory.createTitledBorder("File"));
		fileSkinsPanel.setLayout(new GridBagLayout());
		int count = 0;
		fileSkinsPanel.add(loadFromFileButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		fileSkinsPanel.add(saveToFileButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		fileSkinsPanel.add(saveSkinPictureToFileButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		count++;
		
		loadFromFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					FileFilter fileFilter = new SkinFileFilter();
					JFileChooser fileChooser = new JFileChooser("./skins");
					fileChooser.setFileFilter(fileFilter);
					fileChooser.setSelectedFile(new File(fileChooser.getCurrentDirectory(), skinNameTextField.getText() + ".skin"));
					
					if (fileChooser.showOpenDialog(calendarDemoController.getCalendarDemo().getFrame()) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							CalendarSkinStyleExternalFile calendarSkinStyle = new CalendarSkinStyleExternalFile(file);
							calendarComponent.getCalendarSkin().applyCalendarSkinStyle(calendarSkinStyle);
							
							updateInterface();
						} catch (Exception ex) {
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, 
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
					FileFilter fileFilter = new SkinFileFilter();
					JFileChooser fileChooser = new JFileChooser("./skins");
					fileChooser.setFileFilter(fileFilter);
					fileChooser.setSelectedFile(new File(fileChooser.getCurrentDirectory(), skinNameTextField.getText() + ".skin"));
					
					if (fileChooser.showSaveDialog(calendarDemoController.getCalendarDemo().getFrame()) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							calendarComponent.getCalendarSkin().setName(skinNameTextField.getText());
							CalendarSkinStyleExternalFile.exportSkin(calendarComponent.getCalendarSkin(), file);
						} catch (Exception ex) {
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, 
							"<html>This function is not available when this demo is run through JNLP for security reasons. <br>" + 
							" If you want to view this feature please download the demo from the download section <br>" +
							" of the site and run the demo locally on your machine. " +
							"</html>"
					); 
				}
			}
		}
		);
		
		saveSkinPictureToFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					FileFilter fileFilter = new PNGFileFilter();
					JFileChooser fileChooser = new JFileChooser("./skins/pictures");
					fileChooser.setFileFilter(fileFilter);
					fileChooser.setSelectedFile(new File(fileChooser.getCurrentDirectory(), skinNameTextField.getText() + ".png"));
					if (fileChooser.showSaveDialog(calendarDemoController.getCalendarDemo().getFrame()) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						Dimension size = datePanel.getSize();
						BufferedImage buferredImage = new BufferedImage(size.width, size.height, BufferedImage.SCALE_DEFAULT);
						Graphics g = buferredImage.createGraphics();
						datePanel.paintAll(g);
						
						Iterator iterator = ImageIO.getImageWritersByFormatName("png");
						if (iterator.hasNext()) {
							ImageWriter imageWriter = (ImageWriter) iterator.next();
							FileImageOutputStream outputStream = new FileImageOutputStream(file);
							imageWriter.setOutput(outputStream);
							imageWriter.write(buferredImage);
							
							outputStream.flush();
							outputStream.close();
						} else {
							JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, 
									"<html>Unable to encode the image. The \"png\" format is not supported <br>" +
									"by your current version of Java Virtual Machine." + 
									"<html>"
							); 
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(CalendarDemoPanelSkinEditor.this, 
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
	
	private void updateInterface() {
		skinNameTextField.setText(calendarComponent.getCalendarSkin().getName());
		
		for (int i = 0; i < colorComponents.length; i++) {
			colorComponents[i].updateColorComponentInterface();
		}
		for (int i = 0; i < fontComponents.length; i++) {
			fontComponents[i].updateFontComponentInterface();
		}
		
		for (int i = 0; i < imageComponents.length; i++) {
			imageComponents[i].updateImageComponentInterface();
		}
	}
	
	public String getTitle() {
		return "Skin Editor";
	}
}
