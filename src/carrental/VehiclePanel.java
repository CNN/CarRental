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
    private ArrayList<Booking> bookings;
    private VehicleTypePanel vehicleTypeInstance; //Used to get the addType-panel from the createPanel of the VehicleTypePanel class - to avoid some code duplication
    private GraphicAlternate graph;

    public VehiclePanel() {
        vehicleList = CarRental.getInstance().requestVehicles();
        vehicleTypes = CarRental.getInstance().requestVehicleTypes();
        bookings = CarRental.getInstance().requestBookings();
        vehicleTypeInstance = new VehicleTypePanel();
        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), new CreatePanel(), new ViewVehiclePanel(), new AddTypePanel(), new ListPanel());
        this.setPreferredSize(new Dimension(800, 600));

        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        showMainScreenPanel();
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
        graph.setBookings(array);
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
        DefaultComboBoxModel vehicleTypeComboModel;
        private JComboBox vehicleTypeCombo;
        private JTextField descriptionField, licensePlateField, vinField, drivenField;
        private JTextArea additionalArea;
        private JScrollPane centerScrollPane;
        private JButton createButton, cancelButton;
        private final int defaultJTextFieldColumns = 20, strutDistance = 0;
        private String[] vehicleTypeArray;

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
            for (VehicleType vehicleType : vehicleTypes){
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
                                Vehicle newVehicle = new Vehicle(vehicleList.size() + 1, vehicleTypes.get(vehicleTypeCombo.getSelectedIndex()).getID(),
                                        descriptionField.getText().trim(), licensePlateField.getText().trim(),
                                        vinField.getText().trim(), Integer.parseInt(drivenField.getText().trim()), additionalArea.getText().trim());
                                CarRental.getInstance().saveVehicle(newVehicle);
                                CarRental.getInstance().appendLog("Vehicle \"" + descriptionField.getText() + "\" added to the database");
                                vehicleList.add(newVehicle);
                            }
                        }
                    });
            buttonPanel.add(createButton);
        }

        public void update() {
            //Check for an added type for the JComboBox
            vehicleTypeComboModel.removeAllElements();
            for (VehicleType vehicleType : vehicleTypes){
                vehicleTypeComboModel.addElement(vehicleType.getName());
            }
            //Sets all text fields blank
                            vehicleTypeCombo.setSelectedIndex(0);
                            descriptionField.setText(null);
                            licensePlateField.setText(null);
                            vinField.setText(null);
                            drivenField.setText(null);
                            additionalArea.setText(null);
        }
    }

    public class ViewVehiclePanel extends JPanel {

        public ViewVehiclePanel() {
            JPanel centerPanel, reservationPanel, buttonPanel, vehicleTypePanel, descriptionPanel, licensePlatePanel, vinPanel, drivenPanel, additionalPanel;
            JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel, additionalLabel;
            JTextField vehicleTypeField, descriptionField, licensePlateField, vinField, drivenField;
            JTextArea additionalArea;
            JButton backButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;
            //some temporary strings for testing the GUI
            String vehicleTypeString, descriptionString, licensePlateString, vinString, drivenString, additionalString;


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
            vehicleTypeString = "Station Car";
            vehicleTypeField = new JTextField(vehicleTypeString, defaultJTextFieldColumns);
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
            descriptionString = "Citröen C5";
            descriptionField = new JTextField(descriptionString, defaultJTextFieldColumns);
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
            licensePlateString = "ZYX 547262";
            licensePlateField = new JTextField(licensePlateString, defaultJTextFieldColumns);
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
            vinString = "4241424421";
            vinField = new JTextField(vinString, defaultJTextFieldColumns);
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
            drivenString = "3000 miles";
            drivenField = new JTextField(drivenString, defaultJTextFieldColumns);
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
            additionalString = "This is like the best car ever. \n Not joking. \n SO AWESOME!!!";
            additionalArea = new JTextArea(additionalString, 3, 30);
            additionalArea.setEditable(false);
            additionalArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            additionalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            additionalPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            additionalPanel.add(additionalLabel);
            additionalPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            additionalPanel.add(additionalArea);
            centerPanel.add(additionalPanel);

            //ReservationPanel
            reservationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Reservations"));
            centerPanel.add(reservationPanel);
//        //ReservationTablePanel
//        reservationTablePanel = new JPanel();
//        reservationTablePanel.setBackground(Color.GREEN);
//        reservationTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));


            //Testing Table setup
            Object[] columnNames = {"Customer", "Phone number", "From", "To"};
            ArrayList<ArrayList<String>> reservationData = new ArrayList<>();
            ArrayList<String> rowData;
            //getting the data in the arrayList - this might be unnecessary in final implementation depending on how this class receives the simple type objects.
            Customer testCustomer = new Customer(130, 75834920, "Jens Jensen", "Johnsgade 30 \n 2630 \n Taastrup", "poul@gmail.com");
            Reservation testReservation = new Reservation(100, 3, Timestamp.valueOf("1991-12-24 13:37:00"), Timestamp.valueOf("1991-12-31 13:37:00"), 2);
            for (int i = 0; i < 10; i++) {
                rowData = new ArrayList<>();
                rowData.add(testCustomer.getName());
                rowData.add("" + testCustomer.getTelephone());
                rowData.add("" + testReservation.getTStart());
                rowData.add("" + testReservation.getTEnd());
                reservationData.add(rowData);
                assert reservationData.get(i).size() == columnNames.length;
            }

            //Converting to Object[][] for the JTable
            Object[][] tableData = new Object[reservationData.size()][columnNames.length];
            for (int i = 0; i < reservationData.size(); i++) { //  'i' represents a row
                for (int j = 0; j < columnNames.length; j++) { //'j' represents a certain cell on row 'i'
                    tableData[i][j] = reservationData.get(i).get(j); //out of bounds cannot happen because of the conditions in the for loops.
                }
            }
            //Creating the table
            JTable reservationTable = new JTable(tableData, columnNames);
            //adding it to it's own scrollpane
            JScrollPane scrollPane = new JScrollPane(reservationTable);
            //Setting the default size for the scrollpane
            reservationTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
            reservationPanel.add(scrollPane);

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
                    //TODO NICLASONLY Set text() for all fields aka blank
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(backButton);

        }
        
//        public void update() {
//            //Check for an added type for the JComboBox
//            vehicleTypeComboModel.removeAllElements();
//            for (VehicleType vehicleType : vehicleTypes){
//                vehicleTypeComboModel.addElement(vehicleType.getName());
//            }
//            //Sets all text fields blank
//                            vehicleTypeCombo.setSelectedIndex(0);
//                            descriptionField.setText(null);
//                            licensePlateField.setText(null);
//                            vinField.setText(null);
//                            drivenField.setText(null);
//                            additionalArea.setText(null);
//        }
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

        public ListPanel() {

            JPanel centerPanel, vehicleListPanel, filterPanel, topFilterPanel, middleFilterPanel, bottomFilterPanel, buttonPanel;
            JScrollPane scrollPane;
            final JTable vehicleTable;
            DefaultTableModel vehicleTableModel;
            JLabel vehicleTypeLabel, descriptionLabel, licensePlateLabel, vinLabel, drivenLabel; // make "additional" search filter too?
            final JComboBox vehicleTypeCombo;
            final JTextField descriptionField, licensePlateField, vinField, drivenField;
            JButton filterButton, cancelButton, viewButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;
            final String[] vehicleTypeArray;


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
                vehicleTableModel.addRow(new Object[]{vehicleTypes.get(vehicle.getVehicleType()-1).getName(),
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
            //Putting the description of vehicle types in an array to use in the JComboBox constructor
            vehicleTypeArray = new String[vehicleTypes.size()];

            for (int i = 0; i < vehicleTypes.size(); i++) {
                vehicleTypeArray[i] = vehicleTypes.get(i).getName();
            }
            vehicleTypeCombo = new JComboBox(vehicleTypeArray);

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
                    //Sets all text fields blank
                    vehicleTypeCombo.setSelectedIndex(0);
                    descriptionField.setText(null);
                    licensePlateField.setText(null);
                    vinField.setText(null);
                    drivenField.setText(null);
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
                    CarRental.getInstance().appendLog("Showing \"" + vehicleToView.getDescription() + "\" now.");
                    //showViewEntityPanel();
                }
            });
            buttonPanel.add(viewButton);
        }
//        public void update() {
//            //Check for an added type for the JComboBox
//            vehicleTypeComboModel.removeAllElements();
//            for (VehicleType vehicleType : vehicleTypes){
//                vehicleTypeComboModel.addElement(vehicleType.getName());
//            }
//            //Sets all text fields blank
//                            vehicleTypeCombo.setSelectedIndex(0);
//                            descriptionField.setText(null);
//                            licensePlateField.setText(null);
//                            vinField.setText(null);
//                            drivenField.setText(null);
//                            additionalArea.setText(null);
//        }
    }
}
