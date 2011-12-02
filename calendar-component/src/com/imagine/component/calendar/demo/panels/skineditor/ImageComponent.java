package com.imagine.component.calendar.demo.panels.skineditor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.*;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import com.imagine.component.calendar.data.CalendarProperty;
import com.imagine.component.calendar.skins.CalendarSkin;


public class ImageComponent extends JPanel {
	
	private CalendarSkin calendarSkin;
	private CalendarProperty calendarSkinImageProperty;
	
	private TitledBorder border = BorderFactory.createTitledBorder("");
	
	private ButtonGroup radioGroup = new ButtonGroup();
	private JLabel noImageLabel = new JLabel("No Image");
	private JRadioButton noImageRadioButton = new JRadioButton();
	private JRadioButton imageRadioButton = new JRadioButton();
	
	private JButton imageChooseButton = new JButton("Choose Image");
	File lastImageLoaded = null;
	
	private ImagePanel imagePanel = new ImagePanel();
	
	public ImageComponent(CalendarSkin calendarSkin, CalendarProperty calendarSkinImageProperty, String labelText) {
		this.calendarSkin = calendarSkin;
		this.calendarSkinImageProperty = calendarSkinImageProperty;
		border.setTitle(labelText);
		initialize();
	}
	
	private void initialize() {
		imageChooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileFilter fileFilter = new PNGFileFilter();
					JFileChooser fileChooser = new JFileChooser("./");
					fileChooser.setFileFilter(fileFilter);
					if (lastImageLoaded != null) {
						fileChooser.setCurrentDirectory(lastImageLoaded.getParentFile());
						fileChooser.setSelectedFile(lastImageLoaded);
					}
					if (fileChooser.showOpenDialog(ImageComponent.this) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						lastImageLoaded = file;
						
						Iterator iterator = ImageIO.getImageReadersByFormatName("png");
						if (iterator.hasNext()) {
							FileImageInputStream inputStream = new FileImageInputStream(file);
							ImageReader imageReader = (ImageReader) iterator.next();
							imageReader.setInput(inputStream, true);
							BufferedImage image = imageReader.read(0);
							imageReader.dispose();
							inputStream.close();
							
							imagePanel.setImage(image);
							updateValueInCalendarSkin();
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(ImageComponent.this, 
							"<html>This function is not available when this demo is run through JNLP for security reasons. <br>" + 
							" If you want to view this feature please download the demo from the download section <br>" +
							" of the site and run the demo locally on your machine. " +
							"</html>"
					); 
				}
			}
		}
		);
		
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem() == noImageRadioButton) {
						imageChooseButton.setEnabled(false);
						imagePanel.setImage(null);
					} else if (e.getItem() == imageRadioButton) {
						imageChooseButton.setEnabled(true);
					}
					updateValueInCalendarSkin();
				}
			}
		};
		
		noImageRadioButton.addItemListener(itemListener);
		imageRadioButton.addItemListener(itemListener);
		
		noImageRadioButton.setSelected(true);
		
		radioGroup.add(noImageRadioButton);
		radioGroup.add(imageRadioButton);
		
		this.setBorder(border);
		int count = 0;
		this.setLayout(new GridBagLayout());
		this.add(noImageRadioButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(noImageLabel, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		this.add(imageRadioButton, new GridBagConstraints(0, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(imageChooseButton, new GridBagConstraints(1, count, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
		count++;
		this.add(imagePanel, new GridBagConstraints(0, count, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 400, 300));
		count++;
	}
	
	public void setImage(Image image) {
		imagePanel.setImage(image);
	}
	
	public void updateValueInCalendarSkin() {
		calendarSkin.setSkinProperty(calendarSkinImageProperty, imagePanel.getImage());
		calendarSkin.getCalendarComponent().repaint();
	}
	
	public void updateImageComponentInterface() {
		this.setImage(calendarSkin.getImageSkinProperty(calendarSkinImageProperty));
	}
	
	class ImagePanel extends JPanel {
		boolean dirty = true;
		Image image = null;
		Image scaledImage = null;
		
		public ImagePanel() {
			this.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					dirty = true;
				}
			});
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			if (image != null) {
				if (dirty) {
					Dimension size = this.getSize();
					scaledImage = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
					// Completely load the image.
					MediaTracker mediaTracker = new MediaTracker(this);
					mediaTracker.addImage(scaledImage, 1);
					try {
						mediaTracker.waitForAll();
					} catch (Exception e) {
						e.printStackTrace();
					}				
					dirty = false;
				}
				g.drawImage(scaledImage, 0, 0, null);
			}
		}
		
		public Image getImage() {
			return image;
		}
		
		public void setImage(Image image) {
			this.image = image;
			if (image == null) {
				noImageRadioButton.setSelected(true);
			} else {
				imageRadioButton.setSelected(true);
			}
			
			dirty = true;
			this.repaint();
		}
	}
}
