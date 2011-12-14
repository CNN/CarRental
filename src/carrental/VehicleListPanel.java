/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class VehicleListPanel extends JPanel {

    private DefaultTableModel vehicleTableModel;
    private JComboBox vehicleTypeCombo;
    private DefaultComboBoxModel vehicleTypeComboModel;
    private JTextField descriptionField, licensePlateField, vinField, drivenField;
    private int currentVehicleTypeIndex = -1; //this is for storing the currently selected choice from the combobox.
    private ArrayList<Vehicle> vehicleList;
    private Vehicle vehicleToView;
    private ArrayList<VehicleType> vehicleTypes;
    private JButton cancelButton, viewButton;
    private final JTable vehicleTable;

    /**
     * Sets up the basic functionality needed to show the list of vehicles.
     */
    public VehicleListPanel() {
        vehicleList = CarRental.getInstance().requestVehicles();
        vehicleTypes = CarRental.getInstance().requestVehicleTypes();

        JPanel centerPanel, vehicleListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
        JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel;
        JScrollPane listScrollPane;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "List of vehicles"));

        //CenterPanel
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
        add(centerPanel, BorderLayout.CENTER);

        //VehicleListPanel.
        vehicleListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //Colors
        setBackground(new Color(216, 216, 208));

        //Creating the table model
        vehicleTableModel = new DefaultTableModel(new Object[]{"Type", "Description", "LicensePlate", "VIN", "Distance driven"}, 0);

        //Creating the JTable
        vehicleTable = new JTable(vehicleTableModel);

        listScrollPane = new JScrollPane(vehicleTable);
        //Setting the default size for the table in this scrollpane
        vehicleTable.setPreferredScrollableViewportSize(new Dimension(700, 200));
        vehicleListPanel.add(listScrollPane);
        centerPanel.add(vehicleListPanel);

        //FilterPanel
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Filters"));
        //centerPanel.add(filterPanel);

        //top row of filters
        topFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(topFilterPanel);

        //Vehicle Type
        vehicleTypeLabel = new JLabel("Vehicle Type");
        vehicleTypeComboModel = new DefaultComboBoxModel();
        vehicleTypeCombo = new JComboBox(vehicleTypeComboModel);
        vehicleTypeCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentVehicleTypeIndex == -1 && vehicleTypeCombo.getSelectedIndex() > 0) { //if the current selection hasn't been set and it was not just set to "All"
                    filter();
                    currentVehicleTypeIndex = vehicleTypeCombo.getSelectedIndex();
                } else if (currentVehicleTypeIndex > -1 && currentVehicleTypeIndex != vehicleTypeCombo.getSelectedIndex()) {
                    filter();
                    currentVehicleTypeIndex = vehicleTypeCombo.getSelectedIndex();
                }
            }
        });
        topFilterPanel.add(vehicleTypeLabel);
        topFilterPanel.add(Box.createRigidArea(new Dimension(16 + strutDistance, 0)));
        topFilterPanel.add(vehicleTypeCombo);
        topFilterPanel.add(Box.createRigidArea(new Dimension(91, 0)));

        //Description
        descriptionLabel = new JLabel("Description");
        descriptionField = new JTextField(defaultJTextFieldColumns);
        descriptionField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                filter();
            }
        });
        topFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        topFilterPanel.add(descriptionLabel);
        topFilterPanel.add(Box.createRigidArea(new Dimension(45 + strutDistance, 0)));
        topFilterPanel.add(descriptionField);

        //Middle Filter panel
        middleFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(middleFilterPanel);

        //LicensePlate
        licensePlateLabel = new JLabel("License Plate");
        licensePlateField = new JTextField(defaultJTextFieldColumns);
        licensePlateField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                filter();
            }
        });
        middleFilterPanel.add(licensePlateLabel);
        middleFilterPanel.add(Box.createRigidArea(new Dimension(11 + strutDistance, 0)));
        middleFilterPanel.add(licensePlateField);


        //VIN
        vinLabel = new JLabel("VIN");
        vinField = new JTextField(defaultJTextFieldColumns);
        vinField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                filter();
            }
        });

        middleFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        middleFilterPanel.add(vinLabel);
        middleFilterPanel.add(Box.createRigidArea(new Dimension(90 + strutDistance, 0)));
        middleFilterPanel.add(vinField);

        //Bottom Filter panel
        bottomFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(bottomFilterPanel);

        //Driven
        drivenLabel = new JLabel("Distance driven");
        drivenField = new JTextField(defaultJTextFieldColumns);
        drivenField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                filter();
            }
        });
        bottomFilterPanel.add(drivenLabel);
        bottomFilterPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
        bottomFilterPanel.add(drivenField);

        //ButtonPanels
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
        buttonPanel.add(Box.createHorizontalGlue());

        //cancel-Button
        cancelButton = new JButton("Back");
        cancelButton.setVisible(false);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        //View-button
        viewButton = new JButton("View selected");
        viewButton.setVisible(false);
        buttonPanel.add(viewButton);
    }

    /**
     * Updates the panel to show an updated list of vehicles.
     */
    public void update() {

        //reset the selected vehicle type
        currentVehicleTypeIndex = -1;

        //Delete exisiting rows
        vehicleTableModel.setRowCount(0);

        //Add the updated rows with vehicles
        for (Vehicle vehicle : vehicleList) {
            vehicleTableModel.addRow(new String[]{
                        CarRental.getInstance().requestVehicleType(vehicle.getVehicleType()).getName(),
                        vehicle.getDescription(),
                        vehicle.getLicensePlate(),
                        vehicle.getVin(),
                        Integer.toString(vehicle.getOdo())});
        }
        assert (vehicleList.size() == vehicleTableModel.getRowCount()) : "size: " + vehicleList.size() + " rows: " + vehicleTableModel.getRowCount();

        //Update the JComboBox
        vehicleTypeComboModel.removeAllElements();
        vehicleTypeComboModel.addElement("All");
        for (VehicleType vehicleType : vehicleTypes) {
            vehicleTypeComboModel.addElement(vehicleType.getName());
        }

        //Sets all text fields blank
        descriptionField.setText(null);
        licensePlateField.setText(null);
        vinField.setText(null);
        drivenField.setText(null);
    }

    /**
     * Rearranges the list of vehicles so that only entries matching the filters will be shown.
     */
    public void filter() {

        //Delete exisiting rows
        vehicleTableModel.setRowCount(0);

        //Add the rows that match the filter
        for (Vehicle vehicle : vehicleList) {

            //As long as -
            if (((vehicleTypeCombo.getSelectedIndex() == -1 || vehicleTypeCombo.getSelectedIndex() == 0) || //vehicle type is not chosen or set to "All" OR
                    vehicle.getVehicleType() == vehicleTypes.get(vehicleTypeCombo.getSelectedIndex() - 1).getID()) && //Vehicle's type is the vehicle type chosen AND
                    (descriptionField.getText().trim().isEmpty() || //description field is empty OR
                    vehicle.getDescription().toLowerCase(Locale.ENGLISH).contains(descriptionField.getText().trim().toLowerCase(Locale.ENGLISH))) && //vehicles descripton equals the description given AND
                    (licensePlateField.getText().trim().isEmpty() || //License plate field is empty OR
                    vehicle.getLicensePlate().toLowerCase(Locale.ENGLISH).contains(licensePlateField.getText().trim().toLowerCase(Locale.ENGLISH))) && //vehicles license plate number equals the license plate number given AND
                    (vinField.getText().trim().isEmpty() || //VIN field is empty OR
                    vehicle.getVin().toLowerCase(Locale.ENGLISH).contains(vinField.getText().trim().toLowerCase(Locale.ENGLISH))) && //vehicles VIN equals the VIN given AND
                    (drivenField.getText().trim().isEmpty() || //driven field is empty OR
                    Integer.toString(vehicle.getOdo()).toLowerCase(Locale.ENGLISH).contains(drivenField.getText().trim().toLowerCase(Locale.ENGLISH)))) { //vehicles ODO equals the "distance driven" given

                // - does the vehicle match the filter, and following row is added to the table
                vehicleTableModel.addRow(new String[]{
                            CarRental.getInstance().requestVehicleType(vehicle.getVehicleType()).getName(),
                            vehicle.getDescription(),
                            vehicle.getLicensePlate(),
                            vehicle.getVin(),
                            Integer.toString(vehicle.getOdo())});
            }
        }
    }
    public void setButtons(JButton cancelButton, JButton viewButton) {
        if (cancelButton != null) {
            this.cancelButton.setText(cancelButton.getText());
            for (ActionListener a : cancelButton.getActionListeners()) {
                this.cancelButton.addActionListener(a);
            }
            this.cancelButton.setVisible(true);
        }
        if (viewButton != null) {
            this.viewButton.setText(viewButton.getText());
            for (ActionListener a : viewButton.getActionListeners()) {
                this.viewButton.addActionListener(a);
            }
            this.viewButton.setVisible(true);
        }
    }
    
    public void setTableSize(int width, int height){
        vehicleTable.setPreferredScrollableViewportSize(new Dimension(width, height));
    }
}
