package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.sql.Timestamp;
import java.util.Date;

/**
 * This is the main panel regarding maintenances.
 * It contains JPanels for every relevant screen, when dealing with maintenenaces.
 * These are implemented as inner classes.
 * @author CNN
 * @version 2011-12-12
 */
public class MaintenancePanel extends SuperPanel {

    private Maintenance maintenanceToView; //specific vehicle, used to view details
    private MaintenanceType maintenanceTypeToView; //specific vehicle type, used to view details
    private ArrayList<Maintenance> maintenanceList;
    private ArrayList<MaintenanceType> maintenanceTypes;
    private ArrayList<Vehicle> vehicles;
    private CreatePanel createPanel;
    private ViewMaintenancePanel viewMaintenancePanel;
    private ViewMaintenanceTypePanel viewMaintenanceTypePanel;
    private ListPanel listPanel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Sets up the maintenance panel and all its subpanels.
     */
    public MaintenancePanel() {
        maintenanceList = CarRental.getInstance().requestMaintenances();
        maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
        vehicles = CarRental.getInstance().requestVehicles();
        createPanel = new CreatePanel();
        viewMaintenancePanel = new ViewMaintenancePanel();
        viewMaintenanceTypePanel = new ViewMaintenanceTypePanel();
        listPanel = new ListPanel();
        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), createPanel, viewMaintenancePanel, new AddTypePanel(), viewMaintenanceTypePanel, listPanel);
            //TODO: Should this (above) add the main panel? It's the same in customer and reservation!
        this.setPreferredSize(new Dimension(800, 600));
        showListPanel();
    }

    @Override
    public void showCreatePanel() {
        createPanel.update();
        super.showCreatePanel();
    }

    @Override
    public void showViewEntityPanel() {
        viewMaintenancePanel.update();
        super.showViewEntityPanel();
    }

    @Override
    public void showListPanel() {
        listPanel.update();
        super.showListPanel();
    }

    @Override
    public void showViewTypePanel() {
        viewMaintenanceTypePanel.update();
        super.showViewTypePanel();
    }
    
    public class MainScreenPanel extends JPanel {}

    /**
     * This inner class creates a JPanel with the functionality to create a maintenance.
     */
    public class CreatePanel extends JPanel {

        private JTextField dateStartField, dateEndField;
        private DefaultComboBoxModel maintenanceTypeComboModel, vehicleComboModel;
        private JCheckBox typeIsServiceCheckBox;

        /**
         * Sets up the basic functionality needed to create a vehicle.
         */
        public CreatePanel() {

            JPanel centerPanel, buttonPanel, maintenanceTypePanel, datePanel, vehiclePanel;
            JLabel maintenanceTypeLabel, fromLabel, toLabel, vehicleLabel;
            JButton createButton, cancelButton;
            final JComboBox maintenanceTypeCombo, vehicleCombo;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Schedule maintenance"));
            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(
                    new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));

            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(
                    new Color(216, 216, 208));
            centerPanel.setBackground(
                    new Color(239, 240, 236));

            //Vehicle Type
            maintenanceTypeLabel = new JLabel("Maintenance Type");
            maintenanceTypeComboModel = new DefaultComboBoxModel();
            maintenanceTypeCombo = new JComboBox(maintenanceTypeComboModel);

            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
            }
            maintenanceTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenanceTypePanel.add(maintenanceTypeLabel);

            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            maintenanceTypePanel.add(maintenanceTypeCombo);
            
            typeIsServiceCheckBox = new JCheckBox("Is service");
            typeIsServiceCheckBox.setEnabled(false);
            
            maintenanceTypeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(maintenanceTypes.size() > maintenanceTypeCombo.getSelectedIndex() && maintenanceTypeCombo.getSelectedIndex() != -1)
                        typeIsServiceCheckBox.setSelected(maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getIs_service());
                }
            });
            
            maintenanceTypePanel.add(typeIsServiceCheckBox);

            centerPanel.add(maintenanceTypePanel);

            //Date
            fromLabel = new JLabel("From:");
            dateStartField = new JTextField(defaultJTextFieldColumns);
            toLabel = new JLabel("To:");
            dateEndField = new JTextField(defaultJTextFieldColumns);
            datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            datePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            datePanel.add(fromLabel);
            datePanel.add(Box.createRigidArea(new Dimension(20 + strutDistance, 0)));
            datePanel.add(dateStartField);
            
            datePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            datePanel.add(toLabel);
            datePanel.add(Box.createRigidArea(new Dimension(20 + strutDistance, 0)));
            datePanel.add(dateEndField);

            centerPanel.add(datePanel);
            
            //Vehicle
            vehicleLabel = new JLabel("Vehicle:");
            vehicleComboModel = new DefaultComboBoxModel();
            vehicleCombo = new JComboBox(vehicleComboModel);
            
            for (Vehicle vehicle : vehicles) {
                vehicleCombo.addItem(vehicle.getDescription());
            }
            
            vehiclePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vehiclePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehiclePanel.add(vehicleLabel);

            vehiclePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehiclePanel.add(vehicleCombo);
            
            centerPanel.add(vehiclePanel);

            //ButtonPanels
            buttonPanel = new JPanel();

            add(buttonPanel, BorderLayout.SOUTH);

            buttonPanel.setLayout(
                    new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //cancel-Button
            cancelButton = new JButton("Cancel");

            cancelButton.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            update();
                            showListPanel();
                        }
                    });
            buttonPanel.add(cancelButton);

            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //create-button
            createButton = new JButton("Create");

            createButton.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!dateStartField.getText().trim().isEmpty()
                                    && !dateEndField.getText().trim().isEmpty()) {
                                try {
                                    Maintenance newMaintenance = new Maintenance(CarRental.getInstance().requestNewMaintenanceId(),
                                            vehicles.get(vehicleCombo.getSelectedIndex()).getID(),
                                            new Timestamp(dateFormat.parse(dateStartField.getText().trim()).getTime()),
                                            new Timestamp(dateFormat.parse(dateEndField.getText().trim()).getTime()),
                                            maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getID());

                                    CarRental.getInstance().saveMaintenance(newMaintenance);
                                    CarRental.getInstance().appendLog("Maintenance for vehicle #" + vehicles.get(vehicleCombo.getSelectedIndex()).getID() + " added to the database.");
                                    maintenanceList = CarRental.getInstance().requestMaintenances();
                                } catch (java.text.ParseException ex) {
                                    CarRental.getInstance().appendLog("Could not format the date entered, maintenance not scheduled",ex);
                                }
                            } else {
                                CarRental.getInstance().appendLog("The maintenance wasn't created. Please specify both start and end dates.");
                            }
                        }
                    });
            buttonPanel.add(createButton);
        }

        /**
         * Updates the panel. Textfields are set blank and the vehicle types are updated.
         */
        public void update() {
            //Check for an added type for the JComboBox(es)
            vehicleComboModel.removeAllElements();
            for(Vehicle v : vehicles) {
                vehicleComboModel.addElement(v.getDescription());
            }
            
            maintenanceTypeComboModel.removeAllElements();
            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
            }
            //Sets all text fields blank
            dateStartField.setText(null);
            dateEndField.setText(null);
        }
    }

    /**
     * This inner class creates a JPanel which shows a certain maintenance. The maintenance is selected in the ListPanel-class
     */
    public class ViewMaintenancePanel extends JPanel {
        private JTextField dateStartField, dateEndField;
        private DefaultComboBoxModel maintenanceTypeComboModel, vehicleComboModel;
        private JComboBox maintenanceTypeCombo, vehicleCombo;
        private JCheckBox typeIsServiceCheckBox;

        /**
         * Sets up the basic funtionality needed to view a maintenance.
         */
        public ViewMaintenancePanel() {
            JPanel centerPanel, buttonPanel, maintenanceTypePanel, datePanel, vehiclePanel;
            JLabel maintenanceTypeLabel, fromLabel, toLabel, vehicleLabel;
            JButton backButton, editButton, deleteButton, viewSelectedTypeButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewing Maintenance"));

            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //Vehicle Type
            maintenanceTypeLabel = new JLabel("Maintenance Type");
            maintenanceTypeComboModel = new DefaultComboBoxModel();
            maintenanceTypeCombo = new JComboBox(maintenanceTypeComboModel); //this JComboBox selections are added in the update() method

            typeIsServiceCheckBox = new JCheckBox("Is service?");
            typeIsServiceCheckBox.setEnabled(false);
            
            maintenanceTypeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(maintenanceTypes.size() > maintenanceTypeCombo.getSelectedIndex() && maintenanceTypeCombo.getSelectedIndex() != -1)
                        typeIsServiceCheckBox.setSelected(maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getIs_service());
                }
            });
            
            viewSelectedTypeButton = new JButton("View selected type");
            viewSelectedTypeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    maintenanceTypeToView = maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex());
                    showViewTypePanel();
                    CarRental.getInstance().appendLog("Showing maintenance type \"" + maintenanceTypeToView.getName() + "\" now.");
                }
            });

            maintenanceTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenanceTypePanel.add(maintenanceTypeLabel);

            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            maintenanceTypePanel.add(maintenanceTypeCombo);
            
            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenanceTypePanel.add(typeIsServiceCheckBox);

            maintenanceTypePanel.add(Box.createRigidArea(new Dimension(5 + strutDistance, 0)));
            maintenanceTypePanel.add(viewSelectedTypeButton);

            centerPanel.add(maintenanceTypePanel);
            
            //Date
            fromLabel = new JLabel("From:");
            dateStartField = new JTextField(defaultJTextFieldColumns);
            toLabel = new JLabel("To:");
            dateEndField = new JTextField(defaultJTextFieldColumns);
            datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            datePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            datePanel.add(fromLabel);
            datePanel.add(Box.createRigidArea(new Dimension(5 + strutDistance, 0)));
            datePanel.add(dateStartField);
            
            datePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            datePanel.add(toLabel);
            datePanel.add(Box.createRigidArea(new Dimension(5 + strutDistance, 0)));
            datePanel.add(dateEndField);

            centerPanel.add(datePanel);
            
            //Vehicle
            vehicleLabel = new JLabel("Vehicle:");
            vehicleComboModel = new DefaultComboBoxModel();
            vehicleCombo = new JComboBox(vehicleComboModel);
            
            for (Vehicle vehicle : vehicles) {
                vehicleCombo.addItem(vehicle.getDescription());
            }
            
            vehiclePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vehiclePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehiclePanel.add(vehicleLabel);

            vehiclePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehiclePanel.add(vehicleCombo);
            
            centerPanel.add(vehiclePanel);
            
            //ButtonPanels
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //Cancel-Button
            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    showListPanel();
                }
            });
            buttonPanel.add(backButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //Delete-Button
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    CarRental.getInstance().deleteMaintenance(maintenanceToView.getID());
                    CarRental.getInstance().appendLog("Maintenance #" + maintenanceToView.getID() + " deleted from the database.");
                    maintenanceList = CarRental.getInstance().requestMaintenances();
                    showListPanel();
                }
            });
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //Create-button
            editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    assert (maintenanceToView != null); //VehicleToView should never be null here
                    if (!dateStartField.getText().trim().isEmpty()
                                    && !dateEndField.getText().trim().isEmpty()) {
                        try {
                            Maintenance updatedMaintenance = new Maintenance(maintenanceToView.getID(),
                                            vehicles.get(vehicleCombo.getSelectedIndex()).getID(),
                                            new Timestamp(dateFormat.parse(dateStartField.getText().trim()).getTime()),
                                            new Timestamp(dateFormat.parse(dateEndField.getText().trim()).getTime()),
                                            maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getID());

                            CarRental.getInstance().saveMaintenance(updatedMaintenance);
                            CarRental.getInstance().appendLog("Maintenance #" + updatedMaintenance.getID() + " changed in the database.");
                            maintenanceList = CarRental.getInstance().requestMaintenances();
                        } catch (java.text.ParseException ex) {
                            CarRental.getInstance().appendLog("Failed to parse the dates provided, format wrong.",ex);
                        }
                    }
                }
            });
            buttonPanel.add(editButton);
        }

        /**
         * Updates the panel to show the selected vehicle. This type is selected in the ViewVehiclePanel-class
         */
        public void update() {
            //Check for an added type for the JComboBox(es)
            
            int v_index = 0;
            vehicleComboModel.removeAllElements();
            for(Vehicle v : vehicles) {
                vehicleComboModel.addElement(v.getDescription());
                if(maintenanceToView != null && v.getID() == maintenanceToView.getVehicleID())
                    vehicleCombo.setSelectedIndex(v_index);
                v_index++;
            }
            
            maintenanceTypeComboModel.removeAllElements();
            int type_index = 0;
            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
                if(maintenanceToView != null && maintenanceType.getID() == maintenanceToView.getTypeID())
                    maintenanceTypeCombo.setSelectedIndex(type_index);
                type_index++;
            }
            //Sets all text fields
            if(maintenanceToView != null) dateStartField.setText(dateFormat.format(new Date(maintenanceToView.getTStart().getTime())));
            if(maintenanceToView != null) dateEndField.setText(dateFormat.format(new Date(maintenanceToView.getTEnd().getTime())));
        }
    }

    /**
     * This inner class creates a JPanel which shows a certain vehicle type. The vehicletype is selected in the ViewVehiclePanel-class
     */
    public class ViewMaintenanceTypePanel extends JPanel {

        private JButton backButton, editButton, deleteButton;
        JTextField typeField;
        JCheckBox isServiceBox;
        final int defaultJTextFieldColumns = 15, strutDistance = 0;

        /**
         * Sets up the basic funtionalit needed to view a vehicle type.
         */
        public ViewMaintenanceTypePanel() {

            JPanel viewPanel, buttonPanel, centerPanel;
            JLabel typeLabel;
            
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewing maintenance type"));
            setBackground(new Color(216, 216, 208));
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            add(centerPanel, BorderLayout.CENTER);

            //View panel
            viewPanel = new JPanel();
            
            typeLabel = new JLabel("Name:");
            typeField = new JTextField(defaultJTextFieldColumns);
            isServiceBox = new JCheckBox("Is service?");
            
            viewPanel.add(typeLabel);
            viewPanel.add(typeField);
            viewPanel.add(isServiceBox);
            
            centerPanel.add(viewPanel);

            //Create the buttons needed
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            
            //Back-button
            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showViewEntityPanel();
                    CarRental.getInstance().appendLog("Showing maintenance #" + maintenanceToView.getID() + " now.");
                }
            });
            buttonPanel.add(backButton);

            //Delete-button
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean inUse = false;
                    for (Maintenance maintenance : maintenanceList) {
                        if (maintenance.getTypeID() == maintenanceTypeToView.getID()) {
                            inUse = true;
                        }
                    }
                    if (!inUse) {
                        CarRental.getInstance().deleteMaintenanceType(maintenanceTypeToView.getID());
                        CarRental.getInstance().appendLog("Maintenance type \"" + maintenanceTypeToView.getName() + "\" deleted from the database.");
                        maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
                        showViewEntityPanel();
                    } else {
                        CarRental.getInstance().appendLog("Maintenance type \"" + maintenanceTypeToView.getName() + "\" is in use by at least one car. Could not be deleted.");
                    }
                }
            });
            buttonPanel.add(deleteButton);

            // Edit-button
            editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!typeField.getText().trim().isEmpty()) {
                        //Checks if name is in use already
                        boolean nameTaken = false;
                        for (MaintenanceType maintenanceType : maintenanceTypes) {
                            if (maintenanceType.getName().equals(typeField.getText().trim()) && maintenanceType.getID() != maintenanceTypeToView.getID()) { //if the name is in use and it´s not from the currently viewed vehicle
                                nameTaken = true;
                            }
                        }
                        if (!nameTaken) {
                            MaintenanceType updatedMaintenanceType = new MaintenanceType(maintenanceTypeToView.getID(), typeField.getText().trim(), isServiceBox.isSelected());

                            CarRental.getInstance().saveMaintenanceType(updatedMaintenanceType);
                            CarRental.getInstance().appendLog("Maintenance type \"" + typeField.getText().trim() + "\" changed in the database.");
                            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes(); //update ment for if name check is implemented
                        } else {
                            CarRental.getInstance().appendLog("Another maintenance type with the name, \"" + typeField.getText().trim() + "\", already exists.");
                        }
                    } else {
                        CarRental.getInstance().appendLog("The maintenance type wasn't edited. All fields must be filled.");
                    }
                }
            });
            buttonPanel.add(editButton);
            
            centerPanel.add(buttonPanel);
        }

        /**
         * Updates the panel to show the selected vehicle type. This type is selected in the ViewVehiclePanel-class
         */
        public void update() {
            typeField.setText(maintenanceTypeToView.getName());
            isServiceBox.setSelected(maintenanceTypeToView.getIs_service());
        }
    }

    /**
     * This inner class creates a JPanel with the functionality to create a new vehicle type.
     */
    public class AddTypePanel extends JPanel {

        private JButton cancelButton, createButton;
        private JTextField typeField;
        private JCheckBox isServiceBox;
        private final int defaultJTextFieldColumns = 15;

        /**
         * Sets up the basic functionality needed to create a new vehicle type.
         */
        public AddTypePanel() {
            //Create the panel for viewing the vehicle type.
            JPanel viewPanel, buttonPanel, centerPanel;
            JLabel typeLabel;
            
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewing maintenance type"));
            setBackground(new Color(216, 216, 208));
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            add(centerPanel, BorderLayout.CENTER);

            //View panel
            viewPanel = new JPanel();
            
            typeLabel = new JLabel("Name:");
            typeField = new JTextField(defaultJTextFieldColumns);
            isServiceBox = new JCheckBox("Is service?");
            
            viewPanel.add(typeLabel);
            viewPanel.add(typeField);
            viewPanel.add(isServiceBox);
            
            centerPanel.add(viewPanel);
            
            //Create the buttons needed
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            
            //Cancel-button
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    showListPanel();
                }
            });
            
            buttonPanel.add(cancelButton);

            // Create-button
            createButton = new JButton("Create");
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Clean up this mess, leftover from vehiclepanel
                    if (!typeField.getText().trim().isEmpty()) {
                        //Checks if name is in use already
                        boolean nameTaken = false;
                        for (MaintenanceType maintenanceType : maintenanceTypes) {
                            if (typeField.getText().trim().equals(maintenanceType.getName())) {
                                nameTaken = true;
                            }
                        }
                        if (!nameTaken) {
                            MaintenanceType newMaintenanceType = new MaintenanceType(CarRental.getInstance().requestNewMaintenanceTypeId(), typeField.getText().trim(), isServiceBox.isSelected());

                            CarRental.getInstance().saveMaintenanceType(newMaintenanceType);
                            CarRental.getInstance().appendLog("Maintenance type \"" + typeField.getText().trim() + "\" added to the database.");
                            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
                        } else {
                            CarRental.getInstance().appendLog("A vehicle type with the name \"" + typeField.getText().trim() + "\" already exists.");
                        }
                    } else {
                        CarRental.getInstance().appendLog("The maintenance type wasn't created. You need to enter text in all the fields.");
                    }
                }
            });
            
            buttonPanel.add(createButton);
            centerPanel.add(buttonPanel);
        }
        
        public void update() {
            typeField.setText(null);
            isServiceBox.setSelected(false);
        }
    }
    
    /**
     * This inner class creates a JPanel with a list of vehicles. It is possible to search in the list.
     */
    public class ListPanel extends JPanel {

        private DefaultTableModel maintenanceTableModel;
        private JComboBox maintenanceTypeCombo;
        private DefaultComboBoxModel maintenanceTypeComboModel;
        private JTextField dateStartField, dateEndField;
        private JCheckBox typeIsServiceBox;
        private int currentVehicleTypeIndex = -1; //this is for storing the currently selected choice from the combobox.

        /**
         * Sets up the basic functionality needed to show the list of vehicles.
         */
        public ListPanel() {

            JPanel centerPanel, maintenanceListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
            JLabel maintenanceTypeLabel, dateStartLabel, dateEndLabel;
            JButton cancelButton, viewButton;
            final JTable maintenanceTable;
            JScrollPane listScrollPane;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "List of maintenances"));

            //CenterPanel
            centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //VehicleListPanel.
            maintenanceListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //Colors
            setBackground(new Color(216, 216, 208));

            //Creating the table model
            maintenanceTableModel = new DefaultTableModel(new Object[]{"Vehicle", "Type", "Is service?", "Date start", "Date end"}, 0);

            //Creating the JTable
            maintenanceTable = new JTable(maintenanceTableModel);

            listScrollPane = new JScrollPane(maintenanceTable);
            //Setting the default size for the table in this scrollpane
            maintenanceTable.setPreferredScrollableViewportSize(new Dimension(700, 200));
            maintenanceListPanel.add(listScrollPane);
            centerPanel.add(maintenanceListPanel);

            //FilterPanel
            filterPanel = new JPanel();
            filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
            filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Filters"));
            centerPanel.add(filterPanel);

            //top row of filters
            topFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(topFilterPanel);

            //Vehicle Type
            maintenanceTypeLabel = new JLabel("Maintenance Type");
            maintenanceTypeComboModel = new DefaultComboBoxModel();
            maintenanceTypeCombo = new JComboBox(maintenanceTypeComboModel);
            maintenanceTypeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentVehicleTypeIndex == -1 && maintenanceTypeCombo.getSelectedIndex() > 0) { //if the current selection hasn't been set and it was not just set to "All"
                        filter();
                        currentVehicleTypeIndex = maintenanceTypeCombo.getSelectedIndex();
                    } else if (currentVehicleTypeIndex > -1 && currentVehicleTypeIndex != maintenanceTypeCombo.getSelectedIndex()) {
                        filter();
                        currentVehicleTypeIndex = maintenanceTypeCombo.getSelectedIndex();
                    }
                }
            });
            topFilterPanel.add(maintenanceTypeLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(16 + strutDistance, 0)));
            topFilterPanel.add(maintenanceTypeCombo);
            topFilterPanel.add(Box.createRigidArea(new Dimension(91, 0)));

            //Checkbox
            typeIsServiceBox = new JCheckBox("Is service?");
            typeIsServiceBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filter();
                }
            });
            
            topFilterPanel.add(Box.createRigidArea(new Dimension(45 + strutDistance, 0)));
            topFilterPanel.add(typeIsServiceBox);
            topFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //Middle Filter panel
            middleFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(middleFilterPanel);

            //Date start
            dateStartLabel = new JLabel("From:");
            dateStartField = new JTextField(defaultJTextFieldColumns);
            dateStartField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    filter();
                }
            });
            
            middleFilterPanel.add(dateStartLabel);
            middleFilterPanel.add(Box.createRigidArea(new Dimension(11 + strutDistance, 0)));
            middleFilterPanel.add(dateStartField);
            
            //Date End
            dateEndLabel = new JLabel("To:");
            dateEndField = new JTextField(defaultJTextFieldColumns);
            dateEndField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    filter();
                }
            });
            middleFilterPanel.add(dateEndLabel);
            middleFilterPanel.add(Box.createRigidArea(new Dimension(11 + strutDistance, 0)));
            middleFilterPanel.add(dateEndField);

            //ButtonPanels
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //cancel-Button
            cancelButton = new JButton("Back");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    update();
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //View-button
            viewButton = new JButton("View selected");
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (maintenanceTable.getSelectedRow() >= 0) { //getSelectedRow returns -1 if no row is selected
                        for (Maintenance maintenance : maintenanceList) { //TODO: Fix comparison here
                            if (dateFormat.format(maintenance.getTStart().getTime()).equals(maintenanceTableModel.getValueAt(maintenanceTable.getSelectedRow(), 3)) && 
                                    dateFormat.format(maintenance.getTEnd().getTime()).equals(maintenanceTableModel.getValueAt(maintenanceTable.getSelectedRow(),4))) {
                                maintenanceToView = maintenance;
                                break;
                            }
                        }
                        showViewEntityPanel();
                        CarRental.getInstance().appendLog("Showing maintenance #" + maintenanceToView.getID() + " now.");
                    }
                }
            });
            buttonPanel.add(viewButton);
        }

        /**
         * Updates the panel to show an updated list of vehicles.
         */
        public void update() {

            //reset the selected vehicle type
            currentVehicleTypeIndex = -1;

            //Delete exisiting rows
            maintenanceTableModel.setRowCount(0);

            //Add the updated rows with vehicles
            for (Maintenance maintenance : maintenanceList) {
                maintenanceTableModel.addRow(new String[]{
                    CarRental.getInstance().requestVehicle(maintenance.getVehicleID()).getDescription(),
                    CarRental.getInstance().requestMaintenanceType(maintenance.getTypeID()).getName(),
                    (CarRental.getInstance().requestMaintenanceType(maintenance.getTypeID()).getIs_service() ? "Yes" : "No"),
                    dateFormat.format(new Date(maintenance.getTStart().getTime())),
                    dateFormat.format(new Date(maintenance.getTEnd().getTime()))
                });
            }
            assert (maintenanceList.size() == maintenanceTableModel.getRowCount()) : "size: " + maintenanceList.size() + " rows: " + maintenanceTableModel.getRowCount();

            //Update the JComboBox
            maintenanceTypeComboModel.removeAllElements();
            maintenanceTypeComboModel.addElement("All");
            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
            }

            //Sets all text fields blank
            dateStartField.setText(null);
            dateEndField.setText(null);
            typeIsServiceBox.setSelected(false);
        }

        //TODO: Fix mass parse exception on changing date - only update when date is completely filled in.
        /**
         * Rearranges the list of vehicles so that only entries matching the filters will be shown.
         */
        public void filter() {

            //Delete exisiting rows
            maintenanceTableModel.setRowCount(0);

            //Add the rows that match the filter
            for (Maintenance maintenance : maintenanceList) {
                try {

                //As long as -
                    if (((maintenanceTypeCombo.getSelectedIndex() == -1 || maintenanceTypeCombo.getSelectedIndex() == 0) || //maintenance type is not chosen or set to "All" OR
                            maintenance.getTypeID() == maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex() - 1).getID()) && //Maintenance's type is the maintenance type chosen
                            (dateStartField.getText().trim().isEmpty() || //date start field is empty OR
                            maintenance.getTStart().after(new Timestamp(dateFormat.parse(dateStartField.getText().trim()).getTime()))) && //date start equals the date given AND
                            (dateEndField.getText().trim().isEmpty() || //date end is empty OR
                            maintenance.getTEnd().before(new Timestamp(dateFormat.parse(dateEndField.getText().trim()).getTime()))) && //date end equals the date given AND
                            (!typeIsServiceBox.isSelected() || //is service is empty OR
                            typeIsServiceBox.isSelected() && CarRental.getInstance().requestMaintenanceType(maintenance.getTypeID()).getIs_service()) //maintenance is servce
                            ) {

                        // - does the vehicle match the filter, and following row is added to the table
                        maintenanceTableModel.addRow(new String[]{
                            CarRental.getInstance().requestMaintenanceType(maintenance.getTypeID()).getName(),
                            (CarRental.getInstance().requestMaintenanceType(maintenance.getTypeID()).getIs_service() ? "Yes" : "No"),
                            dateFormat.format(new Date(maintenance.getTStart().getTime())),
                            dateFormat.format(new Date(maintenance.getTEnd().getTime()))
                        });
                    }
                } catch (java.text.ParseException e) {
                    CarRental.getInstance().appendLog("Could not parse date entered in date field. Format should be DD-MM-YYYY",e);
                }
            }
        }
    }
}