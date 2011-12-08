package carrental;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * This is the main panel regarding vehicles.
 * It contains JPanels for every relevant screen, when dealing with vehicles.
 * These are implemented as inner classes.
 * @author CNN
 */
public class VehiclePanel extends SuperPanel {

    private JScrollPane centerScrollPane_View;
    private Vehicle vehicleToView; //specific vehicle, used to view details
    private VehicleType vehicleTypeToView; //specific vehicle, used to view details
    private ArrayList<Vehicle> vehicleList;
    private ArrayList<VehicleType> vehicleTypes;
    private ArrayList<Booking> bookings;               //Bookings are requested from the database -
    private ArrayList<Reservation> reservations;      //The bookings are then sorted into reservations - 
    private ArrayList<Maintenance> maintenances;     // and maintenances in the code - making 2 arraylists from one SQL query.
    private ArrayList<MaintenanceType> maintenanceTypes;
    private ArrayList<Customer> customers;
    //VehicleTypePanel to get the addType-panel from the createPanel of the VehicleTypePanel class - to avoid some code duplication
    private VehicleTypePanel vehicleTypeInstance;
    private GraphicAlternate graph;
    final private ViewVehiclePanel viewVehiclePanel = new ViewVehiclePanel();

    public VehiclePanel() {
        vehicleList = CarRental.getInstance().requestVehicles();
        vehicleTypes = CarRental.getInstance().requestVehicleTypes();
        vehicleTypeInstance = new VehicleTypePanel();
        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), new CreatePanel(), viewVehiclePanel, new AddTypePanel(), new ListPanel());
        this.setPreferredSize(new Dimension(800, 600));

        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        showMainScreenPanel();
    }

    @Override
    public void showViewEntityPanel() {
        viewVehiclePanel.update();
        super.showViewEntityPanel();
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
    }

    public class MainScreenPanel extends JPanel {

        public MainScreenPanel() { //TODO Claus skriv her.. Du kan teste ved at klikke shift+f6 :)           
            graph = new GraphicAlternate();
            graph.setPreferredSize(new Dimension(800, 600));
            add(graph);
        }
    }

    public class CreatePanel extends JPanel {

        private JPanel centerPanel, buttonPanel, vehicleTypePanel, descriptionPanel, licensePlatePanel, vinPanel, drivenPanel, additionalPanel;
        private JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel, additionalLabel;
        DefaultComboBoxModel vehicleTypeComboModel;
        private JComboBox vehicleTypeCombo;
        private JTextField descriptionField, licensePlateField, vinField, drivenField;
        private JTextArea additionalArea;
        private JScrollPane centerScrollPane;
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

            //Center ScrollPane
            centerScrollPane = new JScrollPane(centerPanel);

            add(centerScrollPane, BorderLayout.CENTER);

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
            additionalArea = new JTextArea(3, 30);

            additionalArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            additionalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            additionalPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            additionalPanel.add(additionalLabel);

            additionalPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            additionalPanel.add(additionalArea);

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
                            if (descriptionField.getText().trim().length() > 0
                                    && licensePlateField.getText().trim().length() > 0
                                    && vinField.getText().trim().length() > 0
                                    && drivenField.getText().trim().length() > 0) {
                                //Currently does not check if VIN number is in use already etc.
                                try {
                                    Vehicle newVehicle = new Vehicle(vehicleList.size() + 1, vehicleTypes.get(vehicleTypeCombo.getSelectedIndex()).getID(),
                                            descriptionField.getText().trim(), licensePlateField.getText().trim(),
                                            vinField.getText().trim(), Integer.parseInt(drivenField.getText().trim()), additionalArea.getText().trim());

                                    CarRental.getInstance().saveVehicle(newVehicle);
                                    CarRental.getInstance().appendLog("Vehicle \"" + descriptionField.getText() + "\" added to the database");
                                    vehicleList.add(newVehicle);
                                } catch (NumberFormatException ex) {
                                    System.out.println("Your \"Distance driven\" field does not consist of numbers only. The vehicle wasn't created");
                                }

                            }
                        }
                    });
            buttonPanel.add(createButton);
        }

        public void update() {
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

        JPanel centerPanel, reservationPanel, maintenancePanel, buttonPanel, vehicleTypePanel, descriptionPanel, licensePlatePanel, vinPanel, drivenPanel, additionalPanel;
        JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel, additionalLabel;
        JTextField vehicleTypeField, descriptionField, licensePlateField, vinField, drivenField;
        JTextArea additionalArea;
        JButton backButton;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;
        //some temporary strings for testing the GUI
        String vehicleTypeString, descriptionString, licensePlateString, vinString, drivenString, additionalString;
        DefaultTableModel reservationTableModel, maintenanceTableModel;
        JTable reservationTable, maintenanceTable;
        JScrollPane reservationScrollPane, maintenanceScrollPane;
        String[] tableColumn;
        String[] tableRow;

        public ViewVehiclePanel() {
            //Panel settings
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewing Vehicle"));
            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));

            //Center ScrollPane
            centerScrollPane_View = new JScrollPane(centerPanel);
            add(centerScrollPane_View, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //Vehicle Type
            vehicleTypeLabel = new JLabel("Vehicle Type");
            vehicleTypeField = new JTextField(defaultJTextFieldColumns);
            vehicleTypeField.setEditable(false);
            vehicleTypeField.setBackground(Color.WHITE);
            vehicleTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vehicleTypePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vehicleTypePanel.add(vehicleTypeLabel);
            vehicleTypePanel.add(Box.createRigidArea(new Dimension(48 + strutDistance, 0)));
            vehicleTypePanel.add(vehicleTypeField);
            centerPanel.add(vehicleTypePanel);

            //Name
            descriptionLabel = new JLabel("Description");
            descriptionField = new JTextField(defaultJTextFieldColumns);
            descriptionField.setEditable(false);
            descriptionField.setBackground(Color.WHITE);
            descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            descriptionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            descriptionPanel.add(descriptionLabel);
            descriptionPanel.add(Box.createRigidArea(new Dimension(55 + strutDistance, 0)));
            descriptionPanel.add(descriptionField);
            centerPanel.add(descriptionPanel);


            //LicensePlate
            licensePlateLabel = new JLabel("License Plate");
            licensePlateField = new JTextField(defaultJTextFieldColumns);
            licensePlateField.setEditable(false);
            licensePlateField.setBackground(Color.WHITE);
            licensePlatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            licensePlatePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            licensePlatePanel.add(licensePlateLabel);
            licensePlatePanel.add(Box.createRigidArea(new Dimension(43 + strutDistance, 0)));
            licensePlatePanel.add(licensePlateField);
            centerPanel.add(licensePlatePanel);

            //VIN
            vinLabel = new JLabel("VIN");
            vinField = new JTextField(defaultJTextFieldColumns);
            vinField.setEditable(false);
            vinField.setBackground(Color.WHITE);
            vinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            vinPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            vinPanel.add(vinLabel);
            vinPanel.add(Box.createRigidArea(new Dimension(101 + strutDistance, 0)));
            vinPanel.add(vinField);
            centerPanel.add(vinPanel);

            //Driven
            drivenLabel = new JLabel("Distance driven");
            drivenField = new JTextField(defaultJTextFieldColumns);
            drivenField.setEditable(false);
            drivenField.setBackground(Color.WHITE);
            drivenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            drivenPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            drivenPanel.add(drivenLabel);
            drivenPanel.add(Box.createRigidArea(new Dimension(32 + strutDistance, 0)));
            drivenPanel.add(drivenField);
            centerPanel.add(drivenPanel);

            //Additional Comment
            additionalLabel = new JLabel("Additional comments");
            additionalArea = new JTextArea(3, 30);
            additionalArea.setEditable(false);
            additionalArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            additionalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            additionalPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            additionalPanel.add(additionalLabel);
            additionalPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            additionalPanel.add(additionalArea);
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
            tableColumn = new String[]{"Maintenance Type", "Service check", "From", "To"};
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
                    //note that there´s no need to reset the fields, as update() is called every time this panel is shown,
                    //this is due to the overriden showEntityPanel()-method in VehiclePanel.
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(backButton);

        }

        public void update() {
            bookings = CarRental.getInstance().requestBookings();
            customers = CarRental.getInstance().requestCustomers();
            maintenanceTypes = CarRental.getInstance().requestMaintenanceTypes();
            reservations = new ArrayList<Reservation>();
            maintenances = new ArrayList<Maintenance>();
            

            for (Booking booking : bookings) {
                if (booking.getVehicleID() == vehicleToView.getID()) {
                    if (!booking.isMaintenance()) {
                        reservations.add((Reservation) booking);
                    } else {
                        maintenances.add((Maintenance) booking);
                    }
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
            assert (reservations.size() == reservationTableModel.getRowCount()) : "size: " + reservations.size() + " row: " + reservationTableModel.getRowCount();
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
            assert (maintenances.size() == maintenanceTableModel.getRowCount()) : "size: " + maintenances.size() + " row: " + maintenanceTableModel.getRowCount();





            //Sets all text fields blank
            vehicleTypeField.setText(vehicleTypes.get(vehicleToView.getVehicleType() - 1).getName());
            descriptionField.setText(vehicleToView.getDescription());
            licensePlateField.setText(vehicleToView.getLicensePlate());
            vinField.setText(vehicleToView.getVin());
            drivenField.setText(Integer.toString(vehicleToView.getOdo()));
            additionalArea.setText(vehicleToView.getAdditional());
        }
    }

    public class AddTypePanel extends JPanel { //TODO Try to fix code duplication

        public AddTypePanel() {
            //The functionality here is pretty much implemented in VehicleType's createPanel()-method. I'm using the centerpanel from there, but I'm writing the 
            //buttons + border etc. again, as I can't figure out how to reuse it all (Cancel has a new function here) 
            JPanel buttonPanel, vehicleTypePanel;
            JScrollPane scrollPane;
            JButton cancelButton, createButton;

            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle type"));
            //Colors
            setBackground(new Color(216, 216, 208));
            //Get the centerPanel used to create a VehicleType from VehicleTypePanel.
            vehicleTypePanel = vehicleTypeInstance.getCenterJPanel_create();
            vehicleTypePanel.setVisible(true);
            scrollPane = new JScrollPane(vehicleTypePanel);
            add(scrollPane, BorderLayout.CENTER);


            //ButtonPanels
            buttonPanel = new JPanel();
            //Add the scrollpane to the mainPanel of the Create-functionality
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());
            //cancel-Button
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO NICLASONLY Set text() for all fields aka blank
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
                    //TODO NICLASONLY make the database update here
                }
            });
            buttonPanel.add(createButton);
        }
    }

    public class ListPanel extends JPanel {

        JPanel centerPanel, vehicleListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
        JScrollPane scrollPane;
        JTable vehicleTable;
        DefaultTableModel vehicleTableModel;
        JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel; // make "additional" search filter too?
        JComboBox vehicleTypeCombo;
        DefaultComboBoxModel vehicleTypeComboModel;
        JTextField descriptionField, licensePlateField, vinField, drivenField;
        JButton filterButton, cancelButton, viewButton;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;

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
            for (Vehicle vehicle : vehicleList) {
                vehicleTableModel.addRow(new Object[]{vehicleTypes.get(vehicle.getVehicleType() - 1).getName(),
                            vehicle.getDescription(), vehicle.getLicensePlate(), vehicle.getVin(), vehicle.getOdo()});
            }
            assert (vehicleList.size() == vehicleTableModel.getRowCount());

            scrollPane = new JScrollPane(vehicleTable);
            //Setting the default size for the table in this scrollpane
            vehicleTable.setPreferredScrollableViewportSize(new Dimension(700, 200));
            vehicleListPanel.add(scrollPane);
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
            for (VehicleType vehicleType : vehicleTypes) {
                vehicleTypeComboModel.addElement(vehicleType.getName());
            }
            
            topFilterPanel.add(vehicleTypeLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(16 + strutDistance, 0)));
            topFilterPanel.add(vehicleTypeCombo);
            topFilterPanel.add(Box.createRigidArea(new Dimension(91, 0)));

            //Name
            descriptionLabel = new JLabel("Description");
            descriptionField = new JTextField(defaultJTextFieldColumns);

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

            middleFilterPanel.add(licensePlateLabel);
            middleFilterPanel.add(Box.createRigidArea(new Dimension(11 + strutDistance, 0)));
            middleFilterPanel.add(licensePlateField);


            //VIN
            vinLabel = new JLabel("VIN");
            vinField = new JTextField(defaultJTextFieldColumns);

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

            bottomFilterPanel.add(drivenLabel);
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            bottomFilterPanel.add(drivenField);

            //filter-button
            filterButton = new JButton("Filter");
            filterButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO NICLASONLY make the database update here
                }
            });
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(285, 0)));
            bottomFilterPanel.add(filterButton);


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
                    vehicleToView = vehicleList.get(vehicleTable.getSelectedRow());
                    showViewEntityPanel();
                    CarRental.getInstance().appendLog("Showing \"" + vehicleToView.getDescription() + "\" now.");
                }
            });
            buttonPanel.add(viewButton);
        }

        public void update() {
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
        }
    }
}
