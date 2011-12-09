package carrental;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

/**
 * This is the main panel regarding vehicles.
 * It contains JPanels for every relevant screen, when dealing with vehicles.
 * These are implemented as inner classes.
 * @author CNN
 */
public class VehiclePanel extends SuperPanel {

    private Vehicle vehicleToView; //specific vehicle, used to view details
    private VehicleType vehicleTypeToView; //specific vehicle type, used to view details
    private ArrayList<Vehicle> vehicleList;
    private ArrayList<VehicleType> vehicleTypes;
    private ArrayList<Booking> bookings;
    private ArrayList<Reservation> reservations;      //The bookings are sorted into reservations -
    private ArrayList<Maintenance> maintenances;     // and maintenances in the code
    private ArrayList<MaintenanceType> maintenanceTypes;
    private ArrayList<Customer> customers;
    private GraphicAlternate graph;
    private ViewVehiclePanel viewVehiclePanel;
    private ListPanel listPanel;
    private ViewVehicleTypePanel viewVehicleTypePanel;

    public VehiclePanel() {
        vehicleList = CarRental.getInstance().requestVehicles();
        vehicleTypes = CarRental.getInstance().requestVehicleTypes();
        viewVehiclePanel = new ViewVehiclePanel();
        listPanel = new ListPanel();
        viewVehicleTypePanel = new ViewVehicleTypePanel();
        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), new CreatePanel(), viewVehiclePanel, new AddTypePanel(), viewVehicleTypePanel, listPanel);
        this.setPreferredSize(new Dimension(800, 600));
        showMainScreenPanel();
    }

    @Override
    public void showViewEntityPanel() {
        viewVehiclePanel.update();
        super.showViewEntityPanel();
    }

    @Override
    public void showListPanel() {
        listPanel.update();
        super.showListPanel();
    }

    @Override
    public void showViewTypePanel() {
        viewVehicleTypePanel.update();
        super.showViewTypePanel();
    }

    //Temporary Main
    //TODO: Remove
    public static void main(String[] args) {
        JFrame frame = new JFrame("VehicleFrame");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(new VehiclePanel());
        frame.pack();
        frame.setVisible(true);
    }

    public void setVehicleToView(Vehicle vehicle) {
        vehicleToView = vehicle;
        //Remake the relevant modules.
    }

    public void setVehicleTypeToView(VehicleType vehicle) {
        vehicleTypeToView = vehicle;
    }

    public void setVehicleList(ArrayList<Vehicle> array) {
        vehicleList = array;
    }

    public void setVehicleTypes(ArrayList<VehicleType> array) {
        vehicleTypes = array;
    }

    public void setBookings(ArrayList<Booking> array) {
        bookings = array;
        //graph.setBookings(array);
    }

    public class MainScreenPanel extends JPanel {

        public MainScreenPanel() { //TODO Claus skriv her.. Du kan teste ved at klikke shift+f6 :)
            graph = new GraphicAlternate();
            graph.setPreferredSize(new Dimension(800, 600));
            add(graph);
            System.out.println(graph.toString());


//        mainScreenPanel = new JPanel();
//        JButton createButton, addTypeButton, listButton, viewVehicleButton;
//        JPanel centerPanel, buttonFlowPanel, buttonGridPanel;
//        TitledBorder titleBorder;
//        //Panel settings
//        mainScreenPanel.setLayout(new BorderLayout());
//        titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vehicles (Overview)");
//        mainScreenPanel.setBorder(titleBorder);
//
//        centerPanel = new JPanel();
//        mainScreenPanel.add(centerPanel, BorderLayout.CENTER);
//        //Button panel with a gridlayout for vertical alignment.
//        buttonGridPanel = new JPanel();
//        buttonGridPanel.setLayout(new BoxLayout(buttonGridPanel, BoxLayout.PAGE_AXIS));
//        //extra buttonpanel with a default flowlayout, to shrink the button to minimum size,
//        buttonFlowPanel = new JPanel();
//        buttonFlowPanel.add(buttonGridPanel);
//        centerPanel.add(buttonFlowPanel);
//        //Colors
//        mainScreenPanel.setBackground(new Color(216, 216, 208));
//        centerPanel.setBackground(new Color(239, 240, 236));
//        //Create-button
//        createButton = new JButton("Create a new vehicle");
//        createButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showCreatePanel();
//            }
//        });
//
//        buttonGridPanel.add(createButton);
//        //Create some blank space between the buttons:
//        buttonGridPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//
//        //addType-Button
//        addTypeButton = new JButton("Add a new vehicle type");
//        addTypeButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showAddTypePanel();
//            }
//        });
//        buttonGridPanel.add(addTypeButton);
//        //Create some blank space between the buttons:
//        buttonGridPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//
//        //View vehicle-Button
//        viewVehicleButton = new JButton("View a vehicle");
//        viewVehicleButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showViewEntityPanel();
//            }
//        });
//        buttonGridPanel.add(viewVehicleButton);
//        //Create some blank space between the buttons:
//        buttonGridPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//
//        //Show list-Button
//        listButton = new JButton("Show a list of vehicles");
//        listButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showListPanel();
//            }
//        });
//        buttonGridPanel.add(listButton);
        }
    }

    public class CreatePanel extends JPanel {

        private JPanel centerPanel, buttonPanel, vehicleTypePanel, descriptionPanel, licensePlatePanel, vinPanel, drivenPanel, additionalPanel;
        private JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel, additionalLabel;
        private DefaultComboBoxModel vehicleTypeComboModel;
        private JComboBox vehicleTypeCombo;
        private JTextField descriptionField, licensePlateField, vinField, drivenField;
        private JTextArea additionalArea;
        private JScrollPane additionalScrollPane;
        private JButton createButton, cancelButton;
        private final int defaultJTextFieldColumns = 20, strutDistance = 0;

        public CreatePanel() {
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle"));
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
            vehicleTypeLabel = new JLabel("Vehicle Type");
            vehicleTypeComboModel = new DefaultComboBoxModel();
            vehicleTypeCombo = new JComboBox(vehicleTypeComboModel);

            for (VehicleType vehicleType : vehicleTypes) {
                vehicleTypeComboModel.addElement(vehicleType.getName());
            }
            vehicleTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehicleTypePanel.add(vehicleTypeLabel);

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehicleTypePanel.add(vehicleTypeCombo);

            centerPanel.add(vehicleTypePanel);
            //Name
            descriptionLabel = new JLabel("Description");
            descriptionField = new JTextField(defaultJTextFieldColumns);
            descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            descriptionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            descriptionPanel.add(descriptionLabel);

            descriptionPanel.add(Box.createRigidArea(new Dimension(55 + strutDistance, 0)));
            descriptionPanel.add(descriptionField);

            centerPanel.add(descriptionPanel);
            //LicensePlate
            licensePlateLabel = new JLabel("License Plate");
            licensePlateField = new JTextField(defaultJTextFieldColumns);
            licensePlatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            licensePlatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            licensePlatePanel.add(licensePlateLabel);

            licensePlatePanel.add(Box.createRigidArea(new Dimension(43 + strutDistance, 0)));
            licensePlatePanel.add(licensePlateField);

            centerPanel.add(licensePlatePanel);
            //VIN
            vinLabel = new JLabel("VIN");
            vinField = new JTextField(defaultJTextFieldColumns);
            vinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vinPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vinPanel.add(vinLabel);

            vinPanel.add(Box.createRigidArea(new Dimension(101 + strutDistance, 0)));
            vinPanel.add(vinField);

            centerPanel.add(vinPanel);
            //Driven
            drivenLabel = new JLabel("Distance driven");
            drivenField = new JTextField(defaultJTextFieldColumns);
            drivenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            drivenPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            drivenPanel.add(drivenLabel);

            drivenPanel.add(Box.createRigidArea(new Dimension(32 + strutDistance, 0)));
            drivenPanel.add(drivenField);

            centerPanel.add(drivenPanel);
            //Additional Comment
            additionalLabel = new JLabel("Additional comments");
            additionalArea = new JTextArea(5, 30);
            additionalScrollPane = new JScrollPane(additionalArea);

            additionalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            additionalPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            additionalPanel.add(additionalLabel);

            additionalPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            additionalPanel.add(additionalScrollPane);

            centerPanel.add(additionalPanel);
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
                            showMainScreenPanel();
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
                            if (!descriptionField.getText().trim().isEmpty()
                                    && !licensePlateField.getText().trim().isEmpty()
                                    && !vinField.getText().trim().isEmpty()
                                    && !drivenField.getText().trim().isEmpty()) {
                                //Currently does not check if VIN number is in use already etc.
                                try {
                                    Vehicle newVehicle = new Vehicle(vehicleList.size() + 1, vehicleTypes.get(vehicleTypeCombo.getSelectedIndex()).getID(),
                                            descriptionField.getText().trim(), licensePlateField.getText().trim(),
                                            vinField.getText().trim(), Integer.parseInt(drivenField.getText().trim()), additionalArea.getText().trim());

                                    CarRental.getInstance().saveVehicle(newVehicle);
                                    CarRental.getInstance().appendLog("Vehicle \"" + descriptionField.getText().trim() + "\" added to the database");
                                    CarRental.getView().displayError("Vehicle \"" + descriptionField.getText().trim() + "\" added to the database");
                                    vehicleList = CarRental.getInstance().requestVehicles();
                                } catch (NumberFormatException ex) {
                                    CarRental.getView().displayError("Your \"Distance driven\" field does not consist of numbers only. The vehicle wasn't created");
                                }

                            }
                        }
                    });
            buttonPanel.add(createButton);
        }

        public void update() {
            vehicleList = CarRental.getInstance().requestVehicles(); //If a car was edited from the view panel, this is needed as to have an updated list of VIN numbers
            vehicleTypes = CarRental.getInstance().requestVehicleTypes();
            //Check for an added type for the JComboBox
            vehicleTypeComboModel.removeAllElements();
            for (VehicleType vehicleType : vehicleTypes) {
                vehicleTypeComboModel.addElement(vehicleType.getName());
            }
            //Sets all text fields blank
            descriptionField.setText(null);
            licensePlateField.setText(null);
            vinField.setText(null);
            drivenField.setText(null);
            additionalArea.setText(null);
        }
    }

    public class ViewVehiclePanel extends JPanel {

        private JPanel centerPanel, reservationPanel, maintenancePanel, buttonPanel, vehicleTypePanel, descriptionPanel, licensePlatePanel, vinPanel, drivenPanel, additionalPanel;
        private JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel, additionalLabel;
        private JTextField descriptionField, licensePlateField, vinField, drivenField;
        private DefaultComboBoxModel vehicleTypeComboModel;
        private JComboBox vehicleTypeCombo;
        private JTextArea additionalArea;
        private JButton backButton, editButton, deleteButton, viewSelectedTypeButton;
        private final int defaultJTextFieldColumns = 20, strutDistance = 0;
        private DefaultTableModel reservationTableModel, maintenanceTableModel;
        private JTable reservationTable, maintenanceTable;
        private JScrollPane reservationScrollPane, maintenanceScrollPane, additionalScrollPane;
        private String[] tableColumn;
        private String[] tableRow;

        public ViewVehiclePanel() {
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewing Vehicle"));
            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //Vehicle Type
            vehicleTypeLabel = new JLabel("Vehicle Type");
            vehicleTypeComboModel = new DefaultComboBoxModel();
            vehicleTypeCombo = new JComboBox(vehicleTypeComboModel); //this JComboBox selections are added in the update() method

            viewSelectedTypeButton = new JButton("View selected Type");
            viewSelectedTypeButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (VehicleType vehicleType : vehicleTypes) {
                        if (vehicleType.getName().equals(vehicleTypeComboModel.getElementAt(vehicleTypeCombo.getSelectedIndex()))) {
                            vehicleTypeToView = vehicleType;
                            break;
                        }
                    }
                    showViewTypePanel();
                }
            });

            vehicleTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehicleTypePanel.add(vehicleTypeLabel);

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehicleTypePanel.add(vehicleTypeCombo);

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehicleTypePanel.add(viewSelectedTypeButton);

            centerPanel.add(vehicleTypePanel);
            //Description
            descriptionLabel = new JLabel("Description");
            descriptionField = new JTextField(defaultJTextFieldColumns);
            descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            descriptionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            descriptionPanel.add(descriptionLabel);

            descriptionPanel.add(Box.createRigidArea(new Dimension(55 + strutDistance, 0)));
            descriptionPanel.add(descriptionField);

            centerPanel.add(descriptionPanel);
            //LicensePlate
            licensePlateLabel = new JLabel("License Plate");
            licensePlateField = new JTextField(defaultJTextFieldColumns);
            licensePlatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            licensePlatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            licensePlatePanel.add(licensePlateLabel);

            licensePlatePanel.add(Box.createRigidArea(new Dimension(43 + strutDistance, 0)));
            licensePlatePanel.add(licensePlateField);

            centerPanel.add(licensePlatePanel);
            //VIN
            vinLabel = new JLabel("VIN");
            vinField = new JTextField(defaultJTextFieldColumns);
            vinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vinPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vinPanel.add(vinLabel);

            vinPanel.add(Box.createRigidArea(new Dimension(101 + strutDistance, 0)));
            vinPanel.add(vinField);

            centerPanel.add(vinPanel);
            //Driven
            drivenLabel = new JLabel("Distance driven");
            drivenField = new JTextField(defaultJTextFieldColumns);
            drivenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            drivenPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            drivenPanel.add(drivenLabel);

            drivenPanel.add(Box.createRigidArea(new Dimension(32 + strutDistance, 0)));
            drivenPanel.add(drivenField);

            centerPanel.add(drivenPanel);
            //Additional Comment
            additionalLabel = new JLabel("Additional comments");
            additionalArea = new JTextArea(3, 30);
            additionalScrollPane = new JScrollPane(additionalArea);

            additionalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            additionalPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            additionalPanel.add(additionalLabel);

            additionalPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            additionalPanel.add(additionalScrollPane);

            centerPanel.add(additionalPanel);

            //Adding a small rigid area
            centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            //ReservationPanel
            reservationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Reservations"));
            reservationPanel.setBackground(new Color(216, 216, 208));
            centerPanel.add(reservationPanel);

            //Creating the reservation table model
            tableColumn = new String[]{"Customer", "Phone number", "From", "To"};
            reservationTableModel = new DefaultTableModel(tableColumn, 0);
            //creating the JTable
            reservationTable = new JTable(reservationTableModel);


            reservationScrollPane = new JScrollPane(reservationTable);
            //Setting the default size for the table in this scrollpane
            reservationTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
            reservationPanel.add(reservationScrollPane);

            //Adding a small rigid area
            centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            //maintenancePanel
            maintenancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            maintenancePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Maintenances"));
            maintenancePanel.setBackground(new Color(216, 216, 208));
            centerPanel.add(maintenancePanel);

            //Creating the maintenance table model
            tableColumn = new String[]{"Maintenance type", "Service check", "From", "To"};
            maintenanceTableModel = new DefaultTableModel(tableColumn, 0);
            //creating the JTable
            maintenanceTable = new JTable(maintenanceTableModel);

            maintenanceScrollPane = new JScrollPane(maintenanceTable);
            //Setting the default size for the table in this scrollpane
            maintenanceTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
            maintenancePanel.add(maintenanceScrollPane);

            //ButtonPanels
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //cancel-Button
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
                    CarRental.getInstance().deleteVehicle(vehicleToView.getID());
                    System.out.println(""+vehicleToView.getID());
                    vehicleList = CarRental.getInstance().requestVehicles();
                    showListPanel();
                }
            });
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));



            //create-button
            editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    assert (vehicleToView != null); //VehicleToView should never be null here
                    if (!descriptionField.getText().trim().isEmpty()
                            && !licensePlateField.getText().trim().isEmpty()
                            && !vinField.getText().trim().isEmpty()
                            && !drivenField.getText().trim().isEmpty()) {
                        //Currently does not check if VIN number is in use already etc.
                        try {
                            Vehicle updatedVehicle = new Vehicle(vehicleToView.getID(), vehicleTypes.get(vehicleTypeCombo.getSelectedIndex()).getID(),
                                    descriptionField.getText().trim(), licensePlateField.getText().trim(),
                                    vinField.getText().trim(), Integer.parseInt(drivenField.getText().trim()), additionalArea.getText().trim());

                            CarRental.getInstance().saveVehicle(updatedVehicle);
//                            for (VehicleType vehicleType : vehicleTypes){
//                                System.out.println(vehicleType.getID() + vehicleType.getName());
//                            }
//
//
//                            System.out.println("you chose" + vehicleTypes.get(updatedVehicle.getVehicleType() - 1).getName());
                            CarRental.getInstance().appendLog("Vehicle \"" + descriptionField.getText().trim() + "\" changed in the database");
                            CarRental.getView().displayError("Vehicle \"" + descriptionField.getText().trim() + "\" changed in the database");
                            vehicleList = CarRental.getInstance().requestVehicles();
                        } catch (NumberFormatException ex) {
                            CarRental.getView().displayError("Your \"Distance driven\" field does not consist of numbers only. The vehicle wasn't created");
                        }
                    }
                }
            });
            buttonPanel.add(editButton);


        }

        public void update() {
            vehicleTypes = CarRental.getInstance().requestVehicleTypes();
            vehicleList = CarRental.getInstance().requestVehicles();
            customers = CarRental.getInstance().requestCustomers();
            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
            reservations = new ArrayList<Reservation>();
            maintenances = new ArrayList<Maintenance>();

            //refresh the vehicle types in the Combobox
            vehicleTypeComboModel.removeAllElements();
            for (VehicleType vehicleType : vehicleTypes) {
                vehicleTypeComboModel.addElement(vehicleType.getName());
            }
            //refresh the textfields
            vehicleTypeCombo.setSelectedIndex(vehicleToView.getVehicleType() - 1);
            descriptionField.setText(vehicleToView.getDescription());
            licensePlateField.setText(vehicleToView.getLicensePlate());
            vinField.setText(vehicleToView.getVin());
            drivenField.setText(Integer.toString(vehicleToView.getOdo()));
            additionalArea.setText(vehicleToView.getAdditional());

            //Splits bookings into reservations and maintenances
            for (Booking booking : CarRental.getInstance().requestBookingsByVehicle(vehicleToView.getID())) {
                if (!booking.isMaintenance()) {
                    reservations.add((Reservation) booking);
                } else {
                    maintenances.add((Maintenance) booking);
                }
            }
            //Removes the old rows before adding the new ones
            reservationTableModel.setRowCount(0); //TODO lol er det her virkelig den eneste måde at tømme tabellen på? reservationTable.removeAll() virker ikke, så prøv ikke den
            maintenanceTableModel.setRowCount(0);

            //Add the rows with reservations
            for (Reservation reservation : reservations) {
                tableRow = new String[]{customers.get(reservation.getCustomerID() - 1).getName(),
                    Integer.toString(customers.get(reservation.getCustomerID() - 1).getTelephone()),
                    reservation.getTStart().toString(),
                    reservation.getTEnd().toString()};
                reservationTableModel.addRow(tableRow);
            }
            assert (reservations.size() == reservationTableModel.getRowCount()) : "size: " + reservations.size() + " rows: " + reservationTableModel.getRowCount();

            //Add the rows with maintenances
            for (Maintenance maintenance : maintenances) {
                String serviceCheck;
                if (maintenanceTypes.get(maintenance.getTypeID() - 1).getIs_service()) {
                    serviceCheck = "Yes";
                } else {
                    serviceCheck = "No";
                }
                tableRow = new String[]{maintenanceTypes.get(maintenance.getTypeID() - 1).getName(),
                    serviceCheck,
                    maintenance.getTStart().toString(),
                    maintenance.getTEnd().toString()};
                maintenanceTableModel.addRow(tableRow);
            }
            assert (maintenances.size() == maintenanceTableModel.getRowCount()) : "size: " + maintenances.size() + " rows: " + maintenanceTableModel.getRowCount();



        }
    }

    public class ViewVehicleTypePanel extends JPanel {

        JButton backButton, editButton;
        VehicleTypePanel vehicleTypePanel;

        public ViewVehicleTypePanel() {
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle type"));
            setBackground(new Color(216, 216, 208));
            //Create the panel for viewing the vehicle type.
            vehicleTypePanel = new VehicleTypePanel();

            //Create the buttons needed
            //Back-button
            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    showViewEntityPanel();
                }
            });
            // Edit-button
            editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<JTextComponent> vehicleTypeTextList = new ArrayList<>();
                    if (!vehicleTypeTextList.get(0).getText().trim().isEmpty()
                            && !vehicleTypeTextList.get(1).getText().trim().isEmpty()
                            && !vehicleTypeTextList.get(2).getText().trim().isEmpty()) {
                        //Currently does not check if the type name is already in use already etc.
                        try {
                            VehicleType updatedVehicleType = new VehicleType(vehicleTypeToView.getID(), vehicleTypeTextList.get(0).getText().trim(), vehicleTypeTextList.get(2).getText().trim(),
                                    Integer.parseInt(vehicleTypeTextList.get(1).getText().trim()));

                            CarRental.getInstance().saveVehicleType(updatedVehicleType);
                            CarRental.getInstance().appendLog("Vehicle type \"" + vehicleTypeTextList.get(0).getText().trim() + "\" changed in the database");
                            CarRental.getView().displayError("Vehicle type \"" + vehicleTypeTextList.get(0).getText().trim() + "\" changed in the database");
                            vehicleTypes = CarRental.getInstance().requestVehicleTypes(); //update ment for if name check is implemented
                        } catch (NumberFormatException ex) {
                            CarRental.getView().displayError("Your \"price per day\" field does not consist of numbers only. The vehicle type wasn't created");
                        }
                    }
                }
            });
            add(vehicleTypePanel, BorderLayout.CENTER);
        }

        public void update() {
            vehicleTypePanel.setPanel(vehicleTypeToView, backButton, editButton);
            System.out.println(vehicleTypeToView.getName());
        }
    }

    public class AddTypePanel extends JPanel {

        JButton cancelButton, createButton;
        VehicleTypePanel vehicleTypePanel;

        public AddTypePanel() {
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle type"));
            setBackground(new Color(216, 216, 208));
            //Create the panel for viewing the vehicle type.
            vehicleTypePanel = new VehicleTypePanel();

            //Create the buttons needed
            //Cancel-button
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    vehicleTypePanel.setPanel(null, null, null); //resets the panel
                    showMainScreenPanel();
                }
            });
            // Create-button
            createButton = new JButton("Create");
            createButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<JTextComponent> vehicleTypeTextList = new ArrayList<>();
                    if (!vehicleTypeTextList.get(0).getText().trim().isEmpty()
                            && !vehicleTypeTextList.get(1).getText().trim().isEmpty()
                            && !vehicleTypeTextList.get(2).getText().trim().isEmpty()) {
                        //Currently does not check if the type name is already in use already etc.
                        try {
                            VehicleType newVehicleType = new VehicleType(vehicleTypes.size() + 1, vehicleTypeTextList.get(0).getText().trim(), vehicleTypeTextList.get(2).getText().trim(),
                                    Integer.parseInt(vehicleTypeTextList.get(1).getText().trim()));

                            CarRental.getInstance().saveVehicleType(newVehicleType);
                            CarRental.getInstance().appendLog("Vehicle type \"" + vehicleTypeTextList.get(0).getText().trim() + "\" added to the database");
                            CarRental.getView().displayError("Vehicle type \"" + vehicleTypeTextList.get(0).getText().trim() + "\" added to the database");
                            vehicleTypes = CarRental.getInstance().requestVehicleTypes(); //update ment for if name check is implemented
                        } catch (NumberFormatException ex) {
                            CarRental.getView().displayError("Your \"price per day\" field does not consist of numbers only. The vehicle type wasn't created");
                        }
                    }
                }
            });
            vehicleTypePanel.setPanel(null, cancelButton, createButton);
            add(vehicleTypePanel, BorderLayout.CENTER);
        }
    }

    public class ListPanel extends JPanel {

        JPanel centerPanel, vehicleListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
        JScrollPane listScrollPane;
        JTable vehicleTable;
        DefaultTableModel vehicleTableModel;
        JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel; // make "additional" search filter too?
        JComboBox vehicleTypeCombo;
        DefaultComboBoxModel vehicleTypeComboModel;
        JTextField descriptionField, licensePlateField, vinField, drivenField;
        JButton filterButton, cancelButton, viewButton;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;
        int currentVehicleTypeIndex = -1; //this is for storing the currently selected choice from the combobox.

        public ListPanel() {

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
            //creating the JTable
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
            centerPanel.add(filterPanel);

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
                    } else {
                        //do not filter() as the vehicle type was either the default "All" chosen as the first move,
                        //or the same as the one already filtered after
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
                    if (vehicleTable.getSelectedRow() >= 0) { //getSelectedRow returns -1 if no row is selected
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle.getDescription().equals(vehicleTableModel.getValueAt(vehicleTable.getSelectedRow(), 1))
                                    && vehicle.getVin().equals(vehicleTableModel.getValueAt(vehicleTable.getSelectedRow(), 3))) {
                                vehicleToView = vehicle;
                                break;
                            }
                        }
                        showViewEntityPanel();
                        CarRental.getView().displayError("Showing \"" + vehicleToView.getDescription() + "\" now.");
                    }
                }
            });
            buttonPanel.add(viewButton);
        }

        public void update() {
            //reset the selected vehicle type
            currentVehicleTypeIndex = -1;
            //Delete exisiting rows
            vehicleTableModel.setRowCount(0);

            //Add the updated rows with reservations
            for (Vehicle vehicle : vehicleList) {
                vehicleTableModel.addRow(new String[]{vehicleTypes.get(vehicle.getVehicleType() - 1).getName(),
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
                    vehicleTableModel.addRow(new String[]{vehicleTypes.get(vehicle.getVehicleType() - 1).getName(),
                                vehicle.getDescription(),
                                vehicle.getLicensePlate(),
                                vehicle.getVin(),
                                Integer.toString(vehicle.getOdo())});
                }
            }
        }
    }
}