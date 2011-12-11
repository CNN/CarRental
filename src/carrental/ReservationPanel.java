package carrental;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Locale;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This is the main panel for reservations
 * It contains JPanels for every relevant screen, when dealing with reservations.
 * @author CNN
 * @version 2011-12-09
 */
public class ReservationPanel extends SuperPanel {

    private Booking bookingToView; //specific customer, used to view details
    private ArrayList<Booking> bookings; //array of costumers
    private final CreatePanel createPanel = new CreatePanel();
    private final ViewEntityPanel viewEntityPanel = new ViewEntityPanel();
    private final ListPanel listPanel = new ListPanel();
    private final AddTypePanel addTypePanel = new AddTypePanel();
    private final ViewTypePanel viewTypePanel = new ViewTypePanel();

    public ReservationPanel() {
        this.bookings = CarRental.getInstance().requestBookings();
        if (bookings.get(0) != null) {
            bookingToView = bookings.get(0);
        } else {
            bookingToView = CarRental.getInstance().requestReservation();
        }

        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), createPanel, viewEntityPanel, addTypePanel, viewTypePanel, listPanel);
        this.setPreferredSize(new Dimension(800, 600));

        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //For testing: Choose your panel
        //showCreatePanel();
        //showMainScreenPanel();
        //showViewEntityPanel();
        showListPanel();
    }

    //Temporary Main
    //TODO: Remove main:
    public static void main(String[] args) {
        JFrame frame = new JFrame("ReservationPanel");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(new CustomerPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public void setBookingToView(Reservation reservation) {
        bookingToView = reservation;
    }

    public void setBookingsList(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Updates entire panel
     */
    public void updateReservationPanel() {
        bookings = CarRental.getInstance().requestBookings();
        if (bookings.get(0) != null) {
            bookingToView = bookings.get(0);
        } else {
            bookingToView = CarRental.getInstance().requestReservation();
        }

        createPanel.updateCreatePanel();
        viewEntityPanel.updateViewEntityPanel();
        listPanel.updateListPanel();
        addTypePanel.updateAddTypePanel();
        viewTypePanel.updateViewTypePanel();
    }

    public class MainScreenPanel extends JPanel {

        public MainScreenPanel() {
            //Fields
            TitledBorder titleBorder;

            setLayout(new BorderLayout());
            titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Reservations");
            setBorder(titleBorder);
            add(new ViewEntityPanel());
            add(new ListPanel());
        }
    }

    public class CreatePanel extends JPanel {

        //This somehow gets a customer (another panel?)
        //Uses Calendar libary to create Timestamps
        //Dropdown of VehicleTypes
        JTextField vehicleIDTextField, reservationIDTextField, customerIDTextField, startDateTextField, endDateTextField;
        JCheckBox maintenanceCheckBox;
        JComboBox maintenanceTypeCombo;
        ArrayList<MaintenanceType> maintenanceTypes;
        DefaultComboBoxModel maintenanceTypeComboModel;

        public CreatePanel() {
            //Fields
            JPanel maintenancePanel, vehiclePanel, endDatePanel, startDatePanel, reservationIDPanel, customerPanel, centerPanel, buttonPanel;
            JLabel maintenanceLabel, vehicleIDLabel, dateFormatLabel, reservationIDLabel, customerIDLabel, startDateLabel, endDateLabel;
            JButton findCustomerButton, createButton, cancelButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //createPanel
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Make a reservation"));

            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //Reservation ID
            reservationIDLabel = new JLabel("Reservation ID");
            reservationIDTextField = new JTextField(" Automaticly generated", defaultJTextFieldColumns);
            reservationIDTextField.setEditable(false);
            reservationIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            reservationIDPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            reservationIDPanel.add(reservationIDLabel);
            reservationIDPanel.add(Box.createRigidArea(new Dimension(87 + strutDistance, 0)));
            reservationIDPanel.add(reservationIDTextField);
            centerPanel.add(reservationIDPanel);

            //Maintenance
            maintenanceLabel = new JLabel("Maintenance");
            maintenanceCheckBox = new JCheckBox();
            maintenanceTypeComboModel = new DefaultComboBoxModel();
            maintenanceTypeCombo = new JComboBox(maintenanceTypeComboModel);
            maintenanceTypeCombo.setEnabled(false);

            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();

            maintenanceCheckBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (maintenanceCheckBox.isEnabled()) {
                        maintenanceTypeCombo.setEnabled(true);
                        customerIDTextField.setText(null);
                    } else {
                        maintenanceTypeCombo.setEnabled(false);
                    }
                }
            });

            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
            }

            maintenancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceLabel);
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceCheckBox);
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceTypeCombo);
            centerPanel.add(maintenancePanel);

            //Customer ID
            customerIDLabel = new JLabel("Customer ID");
            customerIDTextField = new JTextField("", defaultJTextFieldColumns);
            customerIDTextField.setEditable(false);
            customerIDTextField.setBackground(Color.WHITE);
            findCustomerButton = new JButton("Find Customer");
            findCustomerButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO Find customer
                }
            });
            customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            customerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            customerPanel.add(customerIDLabel);
            customerPanel.add(Box.createRigidArea(new Dimension(50 + strutDistance, 0)));
            customerPanel.add(customerIDTextField);
            customerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            customerPanel.add(findCustomerButton);
            customerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            centerPanel.add(customerPanel);

            //Vehicle ID
            vehicleIDLabel = new JLabel("Vehicle ID");
            vehicleIDTextField = new JTextField(defaultJTextFieldColumns);

            vehiclePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            vehiclePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehiclePanel.add(vehicleIDLabel);
            vehiclePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            vehiclePanel.add(vehicleIDTextField);
            centerPanel.add(vehiclePanel);

            //Start date
            startDateLabel = new JLabel("Reserved from");
            startDateTextField = new JTextField(defaultJTextFieldColumns);
            dateFormatLabel = new JLabel("yyyy-mm-dd");

            startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            startDatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            startDatePanel.add(startDateLabel);
            startDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            startDatePanel.add(startDateTextField);
            startDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            startDatePanel.add(dateFormatLabel);
            centerPanel.add(startDatePanel);

            //End date
            endDateLabel = new JLabel("Reserved till");
            endDateTextField = new JTextField(defaultJTextFieldColumns);

            endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            endDatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            endDatePanel.add(endDateLabel);
            endDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            endDatePanel.add(endDateTextField);
            endDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            endDatePanel.add(dateFormatLabel);
            centerPanel.add(endDatePanel);

            //ButtonPanel
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //cancelButton
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    updateCreatePanel();
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //create-button
            createButton = new JButton("Create");
            createButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timestamp tStart = new Timestamp(0);
                    Timestamp tEnd = new Timestamp(0);
                    if (reservationIDTextField.getText().trim().length() > 0
                            && vehicleIDTextField.getText().trim().length() > 0
                            && startDateTextField.getText().trim().length() > 0
                            && endDateTextField.getText().trim().length() > 0) {
                        try {
                            tStart = Timestamp.valueOf(startDateTextField.getText());
                            tEnd = Timestamp.valueOf(endDateTextField.getText());
                        } catch (IllegalArgumentException ex) {
                            CarRental.getView().displayError("Time fields must be in format yyyy-mm-dd hh:mm:ss");
                        }
                        if (maintenanceCheckBox.isEnabled()) {
                            try {
                                CarRental.getInstance().saveMaintenance(new Maintenance(
                                        CarRental.getInstance().requestNewMaintenanceId(),
                                        Integer.parseInt(vehicleIDTextField.getText()),
                                        tStart,
                                        tEnd,
                                        maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getID());
                                CarRental.getInstance().appendLog("Reservation " + reservationIDTextField.getText() + " edited");
                                CarRental.getView().displayError("Reservation " + customerIDTextField.getText() + " edited");
                            } catch (NumberFormatException ex) {
                                CarRental.getView().displayError("Vehicle ID must be numbers only");
                            }
                        } else {
                            try {
                                CarRental.getInstance().saveReservation(new Reservation(
                                        CarRental.getInstance().requestNewReservationId(),
                                        Integer.parseInt(vehicleIDTextField.getText()),
                                        tStart,
                                        tEnd,
                                        Integer.parseInt(customerIDTextField.getText())));
                                CarRental.getInstance().appendLog("Reservation " + reservationIDTextField.getText() + " edited");
                                CarRental.getView().displayError("Reservation " + customerIDTextField.getText() + " edited");
                            } catch (NumberFormatException ex) {
                                CarRental.getView().displayError("Vehicle ID number must be numbers only");
                            }
                            bookings = CarRental.getInstance().requestBookings();
                            updateCreatePanel();
                            showMainScreenPanel();
                        }
                    } else { //A TextFild is empty
                        CarRental.getView().displayError("A text field is empty");
                    }
                    updateCreatePanel();
                }
            });
            buttonPanel.add(createButton);
        }

        public void updateCreatePanel() {
            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
            reservationIDTextField.setText(" Automaticly generated");
            vehicleIDTextField.setText("");
            customerIDTextField.setText("");
            startDateTextField.setText("");
            endDateTextField.setText("");
        }
    }

    public class ViewEntityPanel extends JPanel {

        String customerID, vehicleID, reservationID, startDate, endDate;
        JTextField vehicleIDTextField, reservationIDTextField, customerIDTextField, startDateTextField, endDateTextField;
        JCheckBox maintenanceCheckBox;
        JComboBox maintenanceTypeCombo;
        DefaultComboBoxModel maintenanceTypeComboModel;
        ArrayList<MaintenanceType> maintenanceTypes;

        public ViewEntityPanel() {
            //Fields
            JPanel maintenancePanel, vehiclePanel, endDatePanel, startDatePanel, reservationIDPanel, customerPanel, centerPanel, buttonPanel;
            JLabel maintenanceLabel, vehicleIDLabel, dateFormatLabel, reservationIDLabel, customerIDLabel, startDateLabel, endDateLabel;
            JButton deleteButton, editButton, cancelButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //createPanel
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Make a reservation"));

            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //Reservation ID
            reservationIDLabel = new JLabel("Reservation ID");
            reservationIDTextField = new JTextField(" Automaticly generated", defaultJTextFieldColumns);
            reservationIDTextField.setEditable(false);
            reservationIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            reservationIDPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            reservationIDPanel.add(reservationIDLabel);
            reservationIDPanel.add(Box.createRigidArea(new Dimension(87 + strutDistance, 0)));
            reservationIDPanel.add(reservationIDTextField);
            centerPanel.add(reservationIDPanel);

            //Maintenance
            maintenanceLabel = new JLabel("Maintenance");
            maintenanceCheckBox = new JCheckBox();
            maintenanceTypeComboModel = new DefaultComboBoxModel();
            maintenanceTypeCombo = new JComboBox(maintenanceTypeComboModel);
            maintenanceTypeCombo.setEnabled(false);

            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();

            maintenanceCheckBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (maintenanceCheckBox.isEnabled()) {
                        maintenanceTypeCombo.setEnabled(true);
                        customerIDTextField.setText(null);
                    } else {
                        maintenanceTypeCombo.setEnabled(false);
                    }
                }
            });

            for (MaintenanceType maintenanceType : maintenanceTypes) {
                maintenanceTypeComboModel.addElement(maintenanceType.getName());
            }

            maintenancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceLabel);
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceCheckBox);
            maintenancePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            maintenancePanel.add(maintenanceTypeCombo);
            centerPanel.add(maintenancePanel);

            //Customer ID
            customerIDLabel = new JLabel("Customer ID");
            customerIDTextField = new JTextField("", defaultJTextFieldColumns);
            customerIDTextField.setEditable(false);
            customerIDTextField.setBackground(Color.WHITE);

            customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            customerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            customerPanel.add(customerIDLabel);
            customerPanel.add(Box.createRigidArea(new Dimension(50 + strutDistance, 0)));
            customerPanel.add(customerIDTextField);
            customerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            centerPanel.add(customerPanel);

            //Vehicle ID
            vehicleIDLabel = new JLabel("Vehicle ID");
            vehicleIDTextField = new JTextField(defaultJTextFieldColumns);

            vehiclePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            vehiclePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehiclePanel.add(vehicleIDLabel);
            vehiclePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            vehiclePanel.add(vehicleIDTextField);
            centerPanel.add(vehiclePanel);

            //Start date
            startDateLabel = new JLabel("Reserved from");
            startDateTextField = new JTextField(defaultJTextFieldColumns);
            dateFormatLabel = new JLabel("yyyy-mm-dd");

            startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            startDatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            startDatePanel.add(startDateLabel);
            startDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            startDatePanel.add(startDateTextField);
            startDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            startDatePanel.add(dateFormatLabel);
            centerPanel.add(startDatePanel);

            //End date
            endDateLabel = new JLabel("Reserved till");
            endDateTextField = new JTextField(defaultJTextFieldColumns);

            endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            endDatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            endDatePanel.add(endDateLabel);
            endDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            endDatePanel.add(endDateTextField);
            endDatePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            endDatePanel.add(dateFormatLabel);
            centerPanel.add(endDatePanel);

            //ButtonPanel
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //deleteButton
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = Integer.toString(bookingToView.getID());
                    delete(bookingToView);
                    CarRental.getInstance().appendLog("Succesfully deleted reservation " + id);
                    CarRental.getView().displayError("Succesfully deleted reservation " + id);
                    updateViewEntityPanel();
                }
            });
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //editButton
            editButton = new JButton("Save edits");
            editButton.addActionListener(new ActionListener() {
                //TODO Fix this:

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timestamp tStart = new Timestamp(0);
                    Timestamp tEnd = new Timestamp(0);
                    if (reservationIDTextField.getText().trim().length() > 0
                            && vehicleIDTextField.getText().trim().length() > 0
                            && startDateTextField.getText().trim().length() > 0
                            && endDateTextField.getText().trim().length() > 0) {
                        try {
                            tStart = Timestamp.valueOf(startDateTextField.getText());
                            tEnd = Timestamp.valueOf(endDateTextField.getText());
                        } catch (IllegalArgumentException ex) {
                            CarRental.getView().displayError("Time fields must be in format yyyy-mm-dd hh:mm:ss");
                        }
                        if (maintenanceCheckBox.isEnabled()) {
                            try {
                                CarRental.getInstance().saveMaintenance(new Maintenance(
                                        Integer.parseInt(reservationIDTextField.getText()),
                                        Integer.parseInt(vehicleIDTextField.getText()),
                                        tStart,
                                        tEnd,
                                        maintenanceTypes.get(maintenanceTypeCombo.getSelectedIndex()).getID());
                                bookings = CarRental.getInstance().requestBookings();
                                CarRental.getInstance().appendLog("Booking " + reservationIDTextField.getText() + " edited");
                                CarRental.getView().displayError("Bookings " + reservationIDTextField.getText() + " edited");
                                updateViewEntityPanel();
                                showViewEntityPanel();
                            } catch (NumberFormatException ex) {
                                CarRental.getView().displayError("Time fields must be in format yyyy-mm-dd hh:mm:ss");
                            }
                        } else {
                            try {
                                CarRental.getInstance().saveReservation(new Reservation(
                                        Integer.parseInt(reservationIDTextField.getText()),
                                        Integer.parseInt(vehicleIDTextField.getText()),
                                        tStart,
                                        tEnd,
                                        Integer.parseInt(customerIDTextField.getText())));
                                bookings = CarRental.getInstance().requestBookings();
                                CarRental.getInstance().appendLog("Bookings " + reservationIDTextField.getText() + " edited");
                                CarRental.getView().displayError("Booking " + reservationIDTextField.getText() + " edited");
                                updateViewEntityPanel();
                                showViewEntityPanel();
                            } catch (NumberFormatException ex) {
                                CarRental.getView().displayError("Time fields must be in format yyyy-mm-dd hh:mm:ss");
                            }
                        }
                    } else { //A TextFild is empty
                        CarRental.getView().displayError("A text field is empty");
                    }
                }
            });
            buttonPanel.add(editButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //cancelButton
            cancelButton = new JButton("Back");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    updateViewEntityPanel();
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        }

        private void delete(Booking booking) {
            if (booking instanceof Reservation) {
                CarRental.getInstance().deleteReservation(booking.getID());
            } else if (booking instanceof Maintenance) {
                CarRental.getInstance().deleteMaintenance(booking.getID());
            }
        }

        public void setReservationTextFields(Booking booking) {
            if (booking == null) {
                booking = CarRental.getInstance().requestReservation();
            }
            if (booking.isMaintenance()) {
                maintenanceTypeCombo.setEnabled(true);
                maintenanceTypeCombo.setSelectedIndex(booking.getID());
                customerID = "";
            } else {
                ArrayList<Reservation> reservations = CarRental.getInstance().requestReservations();
                customerID = "" + reservations.get(booking.getID()).getCustomerID();
            }

            reservationID = "" + booking.getID();
            vehicleID = "" + booking.getVehicleID();
            startDate = booking.getTStart().toString();
            endDate = booking.getTEnd().toString();
            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
            maintenanceCheckBox.setEnabled(booking.isMaintenance()); //TODO Don't think this works...

        }

        public void updateViewEntityPanel() {
            setReservationTextFields(bookingToView);
            customerIDTextField.setText(" " + customerID);
            reservationIDTextField.setText(" " + reservationID);
            vehicleIDTextField.setText(" " + vehicleID);
            startDateTextField.setText(" " + startDate);
            endDateTextField.setText(" " + endDate);
        }
    }

    public class AddTypePanel extends JPanel {

        public AddTypePanel() {
        }

        public void updateAddTypePanel() {
        }
    }
    
    public class ViewTypePanel extends JPanel{
        
        public ViewTypePanel(){
            
        }
        
        public void updateViewTypePanel(){
            
        }
    }

    public class ListPanel extends JPanel {

        JTextField filterMaintenanceTextField, filterCustomerIDTextField, filterReservationIDTextField, filterVehicleIDTextField, filterStartDateTextField, filterEndDateTextField;
        JTable reservationTable;
        DefaultTableModel reservationTableModel;

        public ListPanel() {
            //Fields
            JPanel centerPanel, reservationListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
            JScrollPane scrollPane;
            JButton cancelButton, filterButton, viewButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //listPanel
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "List of customers"));

            //centerPanel
            centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //customerListPanel.
            reservationListPanel = new JPanel();

            //Colors
            setBackground(new Color(216, 216, 208));

            //Creating the table model
            reservationTableModel = new DefaultTableModel(new Object[]{"ID", "VehicleID", "Start", "End", "CustomerID", "MaintenanceType"}, 0);
            setReservationTable();

            //Creating the table
            reservationTable = new JTable(reservationTableModel);
            //adding it to it's own scrollpane
            scrollPane = new JScrollPane(reservationTable);
            //Setting the default size for the scrollpane
            reservationTable.setPreferredScrollableViewportSize(new Dimension(680, 200));
            reservationListPanel.add(scrollPane);
            centerPanel.add(reservationListPanel);

            //FilterPanel
            JLabel filterReservationIDLabel, filterMaintenanceLabel, filterCustomerIDLabel, filterVehicleIDLabel, filterStartDateLabel, filterEndDateLabel, filterMaintenanceTypeLabel;

            filterPanel = new JPanel();
            filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
            filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Filters"));
            centerPanel.add(filterPanel);

            //top row of filters
            topFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(topFilterPanel);

            //Reservation ID
            filterReservationIDLabel = new JLabel("Reservation ID");
            filterReservationIDTextField = new JTextField(defaultJTextFieldColumns);

            topFilterPanel.add(Box.createRigidArea(new Dimension(0, 0)));
            topFilterPanel.add(filterReservationIDLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(68 + strutDistance, 0)));
            topFilterPanel.add(filterReservationIDTextField);

            //Vehicle ID
            filterVehicleIDLabel = new JLabel("Vehicle ID");
            filterVehicleIDTextField = new JTextField(defaultJTextFieldColumns);

            topFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            topFilterPanel.add(filterVehicleIDLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(12 + strutDistance, 0)));
            topFilterPanel.add(filterVehicleIDTextField);

            //Middle filter panel
            middleFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(middleFilterPanel);

            //Maintenance
            filterMaintenanceLabel = new JLabel("Maintenance Type");
            filterMaintenanceTextField = new JTextField(defaultJTextFieldColumns);

            middleFilterPanel.add(filterMaintenanceLabel);
            middleFilterPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            middleFilterPanel.add(filterMaintenanceTextField);

            //Customer ID
            filterCustomerIDLabel = new JLabel("Customer ID");
            filterCustomerIDTextField = new JTextField(defaultJTextFieldColumns);

            middleFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            middleFilterPanel.add(filterCustomerIDLabel);
            middleFilterPanel.add(Box.createRigidArea(new Dimension(12 + strutDistance, 0)));
            middleFilterPanel.add(filterCustomerIDTextField);

            //Bottom Filter panel
            bottomFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(bottomFilterPanel);

            //Start Date
            filterStartDateLabel = new JLabel("Start");
            filterStartDateTextField = new JTextField(defaultJTextFieldColumns);

            bottomFilterPanel.add(filterStartDateLabel);
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            bottomFilterPanel.add(filterStartDateTextField);

            //End Date
            filterEndDateLabel = new JLabel("End");
            filterEndDateTextField = new JTextField(defaultJTextFieldColumns);

            bottomFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            bottomFilterPanel.add(filterStartDateLabel);
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(5 + strutDistance, 0)));
            bottomFilterPanel.add(filterStartDateTextField);

            //ButtonPanels
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

            //filter-button
            filterButton = new JButton("Filter");
            filterButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    filter();
                }
            });
            bottomFilterPanel.add(filterButton);

            //cancel-Button
            cancelButton = new JButton("Back");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setFilterTextFields();
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
                    if (reservationTable.getSelectedRow() >= 0) {
                        bookingToView = bookings.get(reservationTable.getSelectedRow());
                        showViewEntityPanel();
                        CarRental.getInstance().appendLog("Showing booking \"" + bookingToView.getID() + "\" now.");
                    }
                }
            });
            buttonPanel.add(viewButton);
        }

        public void setReservationTable() {
            bookings = CarRental.getInstance().requestBookings(); //update customers array
            if (bookings.get(0) != null) {
                bookingToView = bookings.get(0);
            } else {
                bookingToView = CarRental.getInstance().requestReservation();
            }

            ArrayList<Maintenance> maintenances = CarRental.getInstance().requestMaintenances();
            ArrayList<Reservation> reservations = CarRental.getInstance().requestReservations();

            for (Booking booking : bookings) { //update table
                int maintenance = 0;
                int customer = 0;
                if (booking.isMaintenance()) {
                    maintenance = maintenances.get(booking.getID()).getTypeID();
                } else {
                    customer = reservations.get(booking.getID()).getCustomerID();
                }
                reservationTableModel.addRow(new Object[]{
                            booking.getID(), //ID
                            booking.getVehicleID(), //Vehicle ID
                            booking.getTStart().toString(), //TStart
                            booking.getTEnd().toString(), //TEnd
                            customer,
                            maintenance //Maintenance type
                        });
            }
        }

        public void updateListPanel() {
            setFilterTextFields();
            setReservationTable();
        }

        public void setFilterTextFields() {
            filterCustomerIDTextField.setText("");
            filterEndDateTextField.setText("");
            filterMaintenanceTextField.setText("");
            filterReservationIDTextField.setText("");
            filterStartDateTextField.setText("");
            filterVehicleIDTextField.setText("");
        }

        public void filter() {
            //Delete exisiting rows
            reservationTableModel.setRowCount(0);
            //Add the rows that match the filter
            ArrayList<Maintenance> maintenances = CarRental.getInstance().requestMaintenances();
            ArrayList<Reservation> reservations = CarRental.getInstance().requestReservations();

            for (Booking booking : bookings) {
                int maintenance = 0;
                int customer = 0;
                if (booking.isMaintenance()) {
                    maintenance = maintenances.get(booking.getID()).getTypeID();
                } else {
                    customer = reservations.get(booking.getID()).getCustomerID();
                }

                Timestamp tStart = new Timestamp(0);
                Timestamp tEnd = new Timestamp(0);
                if (!(filterStartDateTextField.getText().trim().isEmpty()) || !(filterEndDateTextField.getText().trim().isEmpty())) {
                    try {
                        tStart = Timestamp.valueOf(filterStartDateTextField.getText());
                        tEnd = Timestamp.valueOf(filterEndDateTextField.getText());
                    } catch (IllegalArgumentException ex) {
                        CarRental.getView().displayError("Time fields must be in format yyyy-mm-dd hh:mm:ss");
                    }
                }

                //parameters
                if (filterReservationIDTextField.getText().trim().isEmpty() || //Filter ID is empty OR
                        Integer.toString(booking.getID()).trim().toLowerCase(Locale.ENGLISH).contains(filterReservationIDTextField.getText().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                        filterMaintenanceTextField.getText().trim().isEmpty() || //Filter name is empty OR
                        maintenance == Integer.parseInt(filterMaintenanceTextField.getText().trim().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                        filterCustomerIDTextField.getText().trim().isEmpty() || //Filter Phone is empty OR
                        customer == Integer.parseInt(filterCustomerIDTextField.getText().trim().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                        filterVehicleIDTextField.getText().trim().isEmpty() || //Adress field is empty OR
                        Integer.toString(booking.getVehicleID()).trim().toLowerCase(Locale.ENGLISH).contains(filterVehicleIDTextField.getText().trim().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                        filterStartDateTextField.getText().trim().isEmpty()
                        || tStart.before(booking.getTEnd())
                        && filterEndDateTextField.getText().trim().isEmpty()
                        || tEnd.before(booking.getTStart())) {

                    reservationTableModel.addRow(new Object[]{
                                booking.getID(), //ID
                                booking.getVehicleID(), //Vehicle ID
                                booking.getTStart().toString(), //TStart
                                booking.getTEnd().toString(), //TEnd
                                customer,
                                maintenance //Maintenance type
                            });
                }
            }
        }
    }
}
