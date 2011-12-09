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

//TODO Print errors to View

/**
 * This is the main panel for reservations
 * It contains JPanels for every relevant screen, when dealing with reservations.
 * @author CNN
 * @version 2011-12-09
 */
public class ReservationPanel extends SuperPanel {
    
    private Reservation reservationToView; //specific customer, used to view details
    private ArrayList<Reservation> reservations; //array of costumers
    private final CreatePanel createPanel = new CreatePanel();
    private final ViewEntityPanel viewEntityPanel = new ViewEntityPanel();
    private final ListPanel listPanel = new ListPanel();
    private final AddTypePanel addTypePanel = new AddTypePanel();
    
    public ReservationPanel() {
        this.reservations = CarRental.getInstance().requestReservations();
        if (reservations.get(0) != null) {
            reservationToView = reservations.get(0);
        } else {
            reservationToView = CarRental.getInstance().requestReservation();
        }

        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), createPanel, viewEntityPanel, addTypePanel, listPanel);
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
    
    public void setReservationToView(Reservation reservation) {
        reservationToView = reservation;
    }
    
    public void setReservationList(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * Updates entire panel
     */
    public void updateReservationPanel() {
        reservations = CarRental.getInstance().requestReservations();
        if (reservations.get(0) != null) {
            reservationToView = reservations.get(0);
        } else {
            reservationToView = CarRental.getInstance().requestReservation();
        }
        
        createPanel.updateCreatePanel();
        viewEntityPanel.updateViewEntityPanel();
        listPanel.updateListPanel();
        addTypePanel.updateAddTypePanel();
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
        
        public CreatePanel() {
            //Fields
            JPanel vehiclePanel, endDatePanel, startDatePanel, reservationIDPanel, customerPanel, centerPanel, buttonPanel;
            JLabel vehicleIDLabel, dateFormatLabel, reservationIDLabel, customerIDLabel, startDateLabel, endDateLabel;
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
                    if (customerIDTextField.getText().trim().length() > 0){ //TODO Check dates
                        if(maintenanceCheckBox.isEnabled()) //TODO Change to so this works. Optionaly move down
                        try{
                        reservationIDTextField.setText(" " + reservations.size() + 1);
                        CarRental.getInstance().saveReservation(new Reservation(
                                reservations.size() + 1,
                                Integer.parseInt(vehicleIDTextField.getText()),
                                startDateTextField.getText(), //TODO Requires Timestamp !
                                Integer.parseInt(endDateTextField.getText()),
                                Integer.parseInt(customerIDTextField.getText()),
                                false));
                        reservations = CarRental.getInstance().requestReservations();
                        updateCreatePanel();
                        }catch (NumberFormatException ex){
                            //errorLabel.setText("All fields must contain numbers only");
                        }
                    } else { //A TextFild is empty
                        //errorLabel.setText("A field is empty");
                    }
                    updateCreatePanel();
                }
            });
            buttonPanel.add(createButton);
        }
        
        public void updateCreatePanel() {
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
        
        public ViewEntityPanel() {
            //Fields
            JPanel centerPanel, idPanel, namePanel, phonePanel, adressPanel, eMailPanel, buttonPanel;
            JLabel customerIDLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerEMailLabel;
            JButton cancelButton, deleteButton, editButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;
            
            setCustomerTextFields(customerToView);
            
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "View vehicles"));

            //Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
            add(centerPanel, BorderLayout.CENTER);

            //Colors
            setBackground(new Color(216, 216, 208));
            centerPanel.setBackground(new Color(239, 240, 236));

            //ID
            customerIDLabel = new JLabel("Customer ID");
            customerIDTextField = new JTextField(defaultJTextFieldColumns);
            customerIDTextField.setEditable(false);
            customerIDTextField.setBackground(Color.WHITE);
            idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            idPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            idPanel.add(customerIDLabel);
            idPanel.add(Box.createRigidArea(new Dimension(50 + strutDistance, 0)));
            idPanel.add(customerIDTextField);
            centerPanel.add(idPanel);

            //Name
            customerNameLabel = new JLabel("Name");
            customerNameTextField = new JTextField(defaultJTextFieldColumns);
            customerNameTextField.setBackground(Color.WHITE);
            namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            namePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            namePanel.add(customerNameLabel);
            namePanel.add(Box.createRigidArea(new Dimension(87 + strutDistance, 0)));
            namePanel.add(customerNameTextField);
            centerPanel.add(namePanel);

            //Phone
            customerPhoneLabel = new JLabel("Phone number");
            customerPhoneTextField = new JTextField(defaultJTextFieldColumns);
            customerPhoneTextField.setBackground(Color.WHITE);
            phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            phonePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            phonePanel.add(customerPhoneLabel);
            phonePanel.add(Box.createRigidArea(new Dimension(38 + strutDistance, 0)));
            phonePanel.add(customerPhoneTextField);
            centerPanel.add(phonePanel);

            //TODO Split adress
            //Adress
            customerAdressLabel = new JLabel("Adress");
            customerAdressTextField = new JTextField(defaultJTextFieldColumns);
            customerAdressTextField.setBackground(Color.WHITE);
            adressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            adressPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            adressPanel.add(customerAdressLabel);
            adressPanel.add(Box.createRigidArea(new Dimension(80 + strutDistance, 0)));
            adressPanel.add(customerAdressTextField);
            centerPanel.add(adressPanel);

            //EMail
            customerEMailLabel = new JLabel("EMail");
            customerEMailTextField = new JTextField(defaultJTextFieldColumns);
            customerEMailTextField.setEditable(false);
            customerEMailTextField.setBackground(Color.WHITE);
            eMailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            eMailPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            eMailPanel.add(customerAdressLabel);
            eMailPanel.add(Box.createRigidArea(new Dimension(80 + strutDistance, 0)));
            eMailPanel.add(customerAdressTextField);
            centerPanel.add(eMailPanel);

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
                    String id = Integer.toString(customerToView.getID());
                    if(delete(customerToView)){
                        CarRental.getInstance().appendLog("Succesfully deleted customer " + id);
                        System.out.println("Succesfully deleted customer " + id);
                        updateViewEntityPanel();
                    }else{
                        CarRental.getInstance().appendLog("Failed to delete customer " + id);
                        System.out.println("Failed to delete customer " + id + "\nCustomer might have a future reservation");
                    }
                }
            });
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            
            //editButton
            editButton = new JButton("Save edits");
            editButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (customerPhoneTextField.getText().trim().length() > 0
                            && customerNameTextField.getText().trim().length() > 0
                            && customerAdressTextField.getText().trim().length() > 0
                            && customerEMailTextField.getText().trim().length() > 0) {
                        try{
                        CarRental.getInstance().saveCustomer(new Customer(
                                Integer.parseInt(customerIDTextField.getText()),
                                Integer.parseInt(customerPhoneTextField.getText()),
                                customerNameTextField.getText(),
                                customerAdressTextField.getText(),
                                customerEMailTextField.getText()));
                        customers = CarRental.getInstance().requestCustomers();
                        CarRental.getInstance().appendLog("Customer " + customerIDTextField.getText() + " edited");
                        System.out.println("Customer " + customerIDTextField.getText() + " edited");
                        updateViewEntityPanel();
                        showViewEntityPanel();
                        }catch (NumberFormatException ex){
                            System.out.println("Phone number must be numbers only");
                        }
                    } else { //A TextFild is empty
                        System.out.println("A text field is empty");
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
        
        private void delete(Reservation reservation){
            CarRental.getInstance().deleteReservation(reservation.getID());
        } //TODO Request method from Niels
        
        public void setReservationTextFields(Reservation reservation) {
            if (reservation == null) {
                reservation = CarRental.getInstance().requestReservation();
            }
            reservationID = "" + reservation.getID();
            vehicleID = "" + reservation.getID();
            customerID = "" + reservation.getCustomerID();
            startDate = reservation.getTStart().toString();
            endDate = reservation.getTEnd().toString();
            maintenanceCheckBox.setEnabled(reservation.isMaintenance()); //TODO Don't think this works...
        }
        
        public void updateViewEntityPanel() {
            setReservationTextFields(reservationToView);
            customerIDTextField.setText(" " + customerID);
            reservationIDTextField.setText(" " + reservationID);
            vehicleIDTextField.setText(" " + vehicleID);
            startDateTextField.setText(" " + startDate);
            endDateTextField.setText(" " + endDate);
        }
    }
    
    public class AddTypePanel extends JPanel {
        public AddTypePanel(){
            
        }
        
        public void updateAddTypePanel(){
            
        }
    }
    
    public class ListPanel extends JPanel {
        
        JTextField filterAdressTextField, filterPhoneTextField, filterNameTextField, filterIDTextField;
        JTable customerTable;
        DefaultTableModel customerTableModel;
        
        public ListPanel() {
            //Fields
            JPanel centerPanel, customerListPanel, filterPanel, topFilterPanel, bottomFilterPanel, buttonPanel;
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
            customerListPanel = new JPanel();

            //Colors
            setBackground(new Color(216, 216, 208));

            //Creating the table model
            customerTableModel = new DefaultTableModel(new Object[]{"ID", "Phone", "Name", "Adress", "E-Mail"}, 0);
            setCustomerTable();

            //Creating the table
            customerTable = new JTable(customerTableModel);
            //adding it to it's own scrollpane
            scrollPane = new JScrollPane(customerTable);
            //Setting the default size for the scrollpane
            customerTable.setPreferredScrollableViewportSize(new Dimension(680, 200));
            customerListPanel.add(scrollPane);
            centerPanel.add(customerListPanel);

            //FilterPanel
            JLabel filterAdressLabel, filterPhoneLabel, filterNameLabel, filterIDLabel;
            
            filterPanel = new JPanel();
            filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
            filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Filters"));
            centerPanel.add(filterPanel);

            //top row of filters
            topFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(topFilterPanel);

            //ID
            filterIDLabel = new JLabel("ID");
            filterIDTextField = new JTextField(defaultJTextFieldColumns);
            
            topFilterPanel.add(Box.createRigidArea(new Dimension(0, 0)));
            topFilterPanel.add(filterIDLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(68 + strutDistance, 0)));
            topFilterPanel.add(filterIDTextField);

            //Name
            filterNameLabel = new JLabel("Name");
            filterNameTextField = new JTextField(defaultJTextFieldColumns);
            
            topFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            topFilterPanel.add(filterNameLabel);
            topFilterPanel.add(Box.createRigidArea(new Dimension(12 + strutDistance, 0)));
            topFilterPanel.add(filterNameTextField);

            //Bottom Filter panel
            bottomFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filterPanel.add(bottomFilterPanel);

            //Phone
            filterPhoneLabel = new JLabel("Phone number");
            filterPhoneTextField = new JTextField(defaultJTextFieldColumns);
            
            bottomFilterPanel.add(filterPhoneLabel);
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
            bottomFilterPanel.add(filterPhoneTextField);

            //Adress
            filterAdressLabel = new JLabel("Adress");
            filterAdressTextField = new JTextField(defaultJTextFieldColumns);
            
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            bottomFilterPanel.add(filterAdressLabel);
            bottomFilterPanel.add(Box.createRigidArea(new Dimension(5 + strutDistance, 0)));
            bottomFilterPanel.add(filterAdressTextField);

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
                    if (customerTable.getSelectedRow() >= 0) {
                        customerToView = customers.get(customerTable.getSelectedRow());
                        showViewEntityPanel();
                        CarRental.getInstance().appendLog("Showing customer \"" + customerToView.getName() + " " + customerToView.getID() + "\" now.");
                    }
                }
            });
            buttonPanel.add(viewButton);
        }
        
        public void setCustomerTable() {
            customers = CarRental.getInstance().requestCustomers(); //update customers array
            if (customers.get(0) != null) {
                customerToView = customers.get(0);
            } else {
                customerToView = CarRental.getInstance().requestCustomer();
            }
            
            for (Customer customer : customers) { //update table
                customerTableModel.addRow(new Object[]{customer.getID(), //ID
                            customer.getTelephone(), //Phone
                            customer.getName(), //Name
                            customer.getAdress(), //Adress
                            customer.getEMail() //E-Mail
                        });
            }
        }
        
        public void updateListPanel() {
            setFilterTextFields();
            setCustomerTable();
        }
        
        public void setFilterTextFields() {
            filterAdressTextField.setText("");
            filterPhoneTextField.setText("");
            filterNameTextField.setText("");
            filterIDTextField.setText("");
        }
        
        public void filter(){
            //Delete exisiting rows
            customerTableModel.setRowCount(0);
            //Add the rows that match the filter
            for(Customer customer : customers){
                //parameters
                if(filterIDTextField.getText().trim().isEmpty() || //Filter ID is empty OR
                   Integer.toString(customer.getID()).trim().toLowerCase(Locale.ENGLISH).contains(filterIDTextField.getText().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                   filterNameTextField.getText().trim().isEmpty() || //Filter name is empty OR
                   customer.getName().trim().toLowerCase(Locale.ENGLISH).contains(filterNameTextField.getText().trim().toLowerCase(Locale.ENGLISH)) && //Customer matches criteria
                   filterPhoneTextField.getText().trim().isEmpty() || //Filter Phone is empty OR
                   Integer.toString(customer.getTelephone()).trim().toLowerCase(Locale.ENGLISH).contains(filterPhoneTextField.getText().trim().toLowerCase(Locale.ENGLISH)) &&//Customer matches criteria
                   filterAdressTextField.getText().trim().isEmpty() || //Adress field is empty OR
                   customer.getAdress().trim().toLowerCase(Locale.ENGLISH).contains(filterAdressTextField.getText().trim().toLowerCase(Locale.ENGLISH))) //Customer matches criteria
                {
                    customerTableModel.addRow(new Object[]{customer.getID(), //ID
                            customer.getTelephone(), //Phone
                            customer.getName(), //Name
                            customer.getAdress(), //Adress
                            customer.getEMail() //E-Mail
                            });
                }
            }
        }
    }
}
