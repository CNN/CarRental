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
 * This is the main panel regarding vehicles.
 * It contains JPanels for every relevant screen, when dealing with customers.
 * @author CNN
 * @version 2011-12-07
 */
public class CustomerPanel extends SuperPanel {

    private Customer customerToView; //specific customer, used to view details
    private ArrayList<Customer> customers; //array of costumers
    private final CreatePanel createPanel = new CreatePanel();
    private final ViewEntityPanel viewEntityPanel = new ViewEntityPanel();
    private final ListPanel listPanel = new ListPanel();

    public CustomerPanel() {
        this.customers = CarRental.getInstance().requestCustomers();

        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(null, createPanel, viewEntityPanel, null, null, listPanel);
        this.setPreferredSize(new Dimension(800, 600));

        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    /*
     * Sets the given customer as the customer to be shown.
     * @param Customer customer
     */
    public void setCustomerToView(Customer customer) {
        customerToView = customer;
    }

    /**
     * Sets the customer array
     * @param customers 
     */
    public void setCustomerList(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Updates entire panel
     */
    public void updateCustomerPanel() {
        customers = CarRental.getInstance().requestCustomers();

        createPanel.update();
        viewEntityPanel.update();
        listPanel.update();
    }

    /**
     * Inner class. For creating customers.
     */
    public class CreatePanel extends SubPanel {

        private JTextField customerCityTextField, customerZipcodeTextField, customerStreetTextField, customerIDTextField, customerNameTextField, customerPhoneTextField, customerEMailTextField;

        public CreatePanel() {
            //Fields
            JPanel centerPanel, idPanel, namePanel, phonePanel, streetPanel, zipPanel, cityPanel, eMailPanel, buttonPanel;
            JLabel customerCityLabel, customerIDLabel, customerZipcodeLabel, customerNameLabel, customerPhoneLabel, customerStreetLabel, customerEMailLabel;
            JButton createButton, cancelButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            //createPanel
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a customer"));

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
            customerIDTextField = new JTextField(" Automatically generated", defaultJTextFieldColumns);
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
            namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            namePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            namePanel.add(customerNameLabel);
            namePanel.add(Box.createRigidArea(new Dimension(87 + strutDistance, 0)));
            namePanel.add(customerNameTextField);
            centerPanel.add(namePanel);

            //Phone
            customerPhoneLabel = new JLabel("Phone number");
            customerPhoneTextField = new JTextField(defaultJTextFieldColumns);
            phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            phonePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            phonePanel.add(customerPhoneLabel);
            phonePanel.add(Box.createRigidArea(new Dimension(37 + strutDistance, 0)));
            phonePanel.add(customerPhoneTextField);
            centerPanel.add(phonePanel);

            //Adress
//            customerAdressLabel = new JLabel("Adress");
//            customerAdressTextField = new JTextField(defaultJTextFieldColumns);
//            adressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            
//            adressPanel.add(Box.createRigidArea(new Dimension(5, 0)));
//            adressPanel.add(customerAdressLabel);
//            adressPanel.add(Box.createRigidArea(new Dimension(79 + strutDistance, 0)));
//            adressPanel.add(customerAdressTextField);
//            centerPanel.add(adressPanel);

            //Split Adress
            streetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            zipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            customerStreetLabel = new JLabel("Street and number");
            customerStreetTextField = new JTextField(defaultJTextFieldColumns);
            customerZipcodeLabel = new JLabel("Zip/postal code");
            customerZipcodeTextField = new JTextField(defaultJTextFieldColumns);
            customerCityLabel = new JLabel("City/Town");
            customerCityTextField = new JTextField(defaultJTextFieldColumns);

            streetPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            streetPanel.add(customerStreetLabel);
            streetPanel.add(Box.createRigidArea(new Dimension(16 + strutDistance, 0)));
            streetPanel.add(customerStreetTextField);
            zipPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            zipPanel.add(customerZipcodeLabel);
            zipPanel.add(Box.createRigidArea(new Dimension(36 + strutDistance, 0)));
            zipPanel.add(customerZipcodeTextField);
            cityPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            cityPanel.add(customerCityLabel);
            cityPanel.add(Box.createRigidArea(new Dimension(68 + strutDistance, 0)));
            cityPanel.add(customerCityTextField);
            centerPanel.add(streetPanel);
            centerPanel.add(zipPanel);
            centerPanel.add(cityPanel);


            //EMail
            customerEMailLabel = new JLabel("E-Mail");
            customerEMailTextField = new JTextField(defaultJTextFieldColumns);
            eMailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            eMailPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            eMailPanel.add(customerEMailLabel);
            eMailPanel.add(Box.createRigidArea(new Dimension(85 + strutDistance, 0)));
            eMailPanel.add(customerEMailTextField);
            centerPanel.add(eMailPanel);

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
                    update();
                    showListPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //create-button
            createButton = new JButton("Create");
            createButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!customerPhoneTextField.getText().trim().isEmpty()
                            && !customerNameTextField.getText().trim().isEmpty()
                            && !customerStreetTextField.getText().trim().isEmpty()
                            && !customerZipcodeTextField.getText().trim().isEmpty()
                            && !customerCityTextField.getText().trim().isEmpty()
                            && !customerEMailTextField.getText().trim().isEmpty()) {
                        String adress = customerStreetTextField.getText().trim() + "\n" + customerZipcodeTextField.getText().trim() + "\n" + customerCityTextField.getText().trim();
                        try {
                            customerIDTextField.setText(" " + CarRental.getInstance().requestNewCustomerId());
                            CarRental.getInstance().saveCustomer(new Customer(
                                    CarRental.getInstance().requestNewCustomerId(),
                                    customerPhoneTextField.getText(),
                                    customerNameTextField.getText(),
                                    adress,
                                    customerEMailTextField.getText()));
                            customers = CarRental.getInstance().requestCustomers();
                            update();
                        } catch (NumberFormatException ex) {
                            CarRental.getInstance().appendLog("Phone number must be numbers only");
                        }
                    } else { //A TextFild is empty
                        CarRental.getInstance().appendLog("A text field is empty");
                    }
                    update();
                }
            });
            buttonPanel.add(createButton);
        }

        /**
         * Resets text fields.
         */
        public void update() {
            customerIDTextField.setText(" Automatically generated");
            customerPhoneTextField.setText("");
            customerNameTextField.setText("");
            customerCityTextField.setText("");
            customerZipcodeTextField.setText("");
            customerStreetTextField.setText("");
            customerEMailTextField.setText("");
        }
    }

    /**
     * Inner class. Shows a single customer in detail.
     */
    public class ViewEntityPanel extends SubPanel {

        private String customerID, customerName, customerPhone, customerAdress, customerEMail;
        private JTextField customerZipcodeTextField, customerStreetTextField, customerCityTextField, customerIDTextField, customerNameTextField, customerPhoneTextField, customerAdressTextField, customerEMailTextField;

        public ViewEntityPanel() {
            //Fields
            JPanel cityPanel, streetPanel, zipPanel, centerPanel, idPanel, namePanel, phonePanel, adressPanel, eMailPanel, buttonPanel;
            JLabel customerZipcodeLabel, customerStreetLabel, customerCityLabel, customerIDLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerEMailLabel;
            JButton cancelButton, deleteButton, editButton;
            final int defaultJTextFieldColumns = 20, strutDistance = 0;

            update();

            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "View customers"));

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

//            //Adress old look
//            customerAdressLabel = new JLabel("Adress");
//            customerAdressTextField = new JTextField(defaultJTextFieldColumns);
//            customerAdressTextField.setBackground(Color.WHITE);
//            adressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            
//            adressPanel.add(Box.createRigidArea(new Dimension(5, 0)));
//            adressPanel.add(customerAdressLabel);
//            adressPanel.add(Box.createRigidArea(new Dimension(80 + strutDistance, 0)));
//            adressPanel.add(customerAdressTextField);
//            centerPanel.add(adressPanel);

            //Split Adress
            streetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            zipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            customerStreetLabel = new JLabel("Street and number");
            customerStreetTextField = new JTextField(defaultJTextFieldColumns);
            customerZipcodeLabel = new JLabel("Zip/postal code");
            customerZipcodeTextField = new JTextField(defaultJTextFieldColumns);
            customerCityLabel = new JLabel("City/Town");
            customerCityTextField = new JTextField(defaultJTextFieldColumns);

            streetPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            streetPanel.add(customerStreetLabel);
            streetPanel.add(Box.createRigidArea(new Dimension(16 + strutDistance, 0)));
            streetPanel.add(customerStreetTextField);
            zipPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            zipPanel.add(customerZipcodeLabel);
            zipPanel.add(Box.createRigidArea(new Dimension(36 + strutDistance, 0)));
            zipPanel.add(customerZipcodeTextField);
            cityPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            cityPanel.add(customerCityLabel);
            cityPanel.add(Box.createRigidArea(new Dimension(68 + strutDistance, 0)));
            cityPanel.add(customerCityTextField);
            centerPanel.add(streetPanel);
            centerPanel.add(zipPanel);
            centerPanel.add(cityPanel);

            //EMail
            customerEMailLabel = new JLabel("E-Mail");
            customerEMailTextField = new JTextField(defaultJTextFieldColumns);
            customerEMailTextField.setBackground(Color.WHITE);
            eMailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            eMailPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            eMailPanel.add(customerEMailLabel);
            eMailPanel.add(Box.createRigidArea(new Dimension(88 + strutDistance, 0)));
            eMailPanel.add(customerEMailTextField);
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
                    if (delete(customerToView)) {
                        CarRental.getInstance().appendLog("Succesfully deleted customer " + id);
                        update();
                    } else {
                        CarRental.getInstance().appendLog("Failed to delete customer " + id);
                    }
                }
            });
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //editButton
            editButton = new JButton("Save changes");
            editButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!customerPhoneTextField.getText().trim().isEmpty()
                            && !customerNameTextField.getText().trim().isEmpty()
                            && !customerStreetTextField.getText().trim().isEmpty()
                            && !customerZipcodeTextField.getText().trim().isEmpty()
                            && !customerCityTextField.getText().trim().isEmpty()
                            && !customerEMailTextField.getText().trim().isEmpty()) {
                        String adress = customerStreetTextField.getText() + "\n" + customerZipcodeTextField.getText() + "\n" + customerCityTextField.getText();
                        try {
                            CarRental.getInstance().saveCustomer(new Customer(
                                    customerToView.getID(),
                                    customerPhoneTextField.getText().trim(),
                                    customerNameTextField.getText().trim(),
                                    adress,
                                    customerEMailTextField.getText().trim()));
                            customers = CarRental.getInstance().requestCustomers();
                            CarRental.getInstance().appendLog("Customer " + customerIDTextField.getText() + " edited");
                            customerToView = CarRental.getInstance().requestCustomer(customerToView.getID());
                            update();
                            listPanel.update();
                            showViewEntityPanel();
                        } catch (NumberFormatException ex) {
                            CarRental.getInstance().appendLog("Phone number must be numbers only");
                        }
                    } else { //A TextFild is empty
                        CarRental.getInstance().appendLog("A text field is empty");
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
                    update();
                    showListPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        }

        /**
         * Deletes the given customer. 
         * @param customer
         * @return success
         */
        private boolean delete(Customer customer) {
            boolean success;
            ArrayList<Reservation> bookings = CarRental.getInstance().requestReservationsByCustomer(customer.getID());
            if (bookings == null || bookings.isEmpty()) {
                CarRental.getInstance().deleteCustomer(customer.getID());
                success = true;
            } else {
                success = false;
            }
            return success;
        }

        /**
         * Sets the customer fields based on the given customer.
         * @param customer 
         */
        public void setCustomerTextFields(Customer customer) {
            customerID = "" + customer.getID();
            if (customer.getName() != null) {
                customerName = customer.getName();
            } else {
                customerName = "Unknown customer";
            }
            customerPhone = "" + customer.getTelephone();
            if (customer.getAdress() != null) {
                customerAdress = customer.getAdress();
            } else {
                customerAdress = "Unknown adress";
            }
            if (customer.getEMail() != null) {
                customerEMail = customer.getEMail();
            } else {
                customerEMail = "Unknown E-Mail";
            }
        }

        /**
         * Sets customer text fields based on 'customerToView'
         */
        public void update() {
            if (customerToView != null) {
                if (customerToView.getAdress() != null && !customerToView.getAdress().trim().isEmpty()) {
                    String[] split = customerToView.getAdress().split("\n");
                    if (split.length > 0 && !split[0].isEmpty()) {
                        customerStreetTextField.setText(split[0]);
                    }
                    if (split.length > 1 && !split[1].isEmpty()) {
                        customerZipcodeTextField.setText(split[1]);
                    }
                    if (split.length > 2 && !split[2].isEmpty()) {
                        customerCityTextField.setText(split[2]);
                    }

                }

                if (customerToView.getID() != 0) {
                    customerIDTextField.setText("" + customerToView.getID());
                }
                if (customerToView.getName() != null) {
                    customerNameTextField.setText(customerToView.getName());
                }
                if (customerToView.getTelephone() != null) {
                    customerPhoneTextField.setText(customerToView.getTelephone());
                }
                if (customerToView.getEMail() != null) {
                    customerEMailTextField.setText(customerToView.getEMail());
                }
            }
        }
    }

    public void makeAddTypePanel() { //not used
    }

    /**
     * Shows a list of customers.
     */
    public class ListPanel extends SubPanel {

        private JTextField filterAdressTextField, filterPhoneTextField, filterNameTextField, filterIDTextField;
        private JTable customerTable;
        private DefaultTableModel customerTableModel;

        public ListPanel() {
            //Fields
            JPanel centerPanel, customerListPanel, filterPanel, topFilterPanel, bottomFilterPanel, buttonPanel;
            JScrollPane scrollPane;
            JButton viewButton;
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

            //View-button
            viewButton = new JButton("View selected");
            viewButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (customerTable.getSelectedRow() >= 0) {
                        customerToView = customers.get(customerTable.getSelectedRow());
                        viewEntityPanel.update();
                        showViewEntityPanel();
                        CarRental.getInstance().appendLog("Showing customer \"" + customerToView.getName() + " " + customerToView.getID() + "\" now.");
                    }
                }
            });
            buttonPanel.add(viewButton);
        }

        /**
         * Reloads the list of customer.
         */
        public void setCustomerTable() {
            customers = CarRental.getInstance().requestCustomers(); //update customers array
            customerTableModel.setRowCount(0);

            for (Customer customer : customers) { //update table
                String[] split = customer.getAdress().split("\n"); //for nicer look
                String displayed = split[0];
                for (int i = 1; i < split.length; i++) {
                    displayed = displayed + ", " + split[i];
                }

                customerTableModel.addRow(new Object[]{customer.getID(), //ID
                            customer.getTelephone(), //Phone
                            customer.getName(), //Name
                            displayed, //Adress
                            customer.getEMail() //E-Mail
                        });
            }
        }

        /**
         * Reloads the list of customers and resets filter text fields.
         */
        public void update() {
            setFilterTextFields();
            setCustomerTable();
        }

        /**
         * Resets filter text fields
         */
        public void setFilterTextFields() {
            filterAdressTextField.setText("");
            filterPhoneTextField.setText("");
            filterNameTextField.setText("");
            filterIDTextField.setText("");
        }

        /**
         * Reloads list of customers. Shows only entries matching parameters.
         */
        public void filter() {
            //Delete exisiting rows
            customerTableModel.setRowCount(0);
            //Add the rows that match the filter
            for (Customer customer : customers) {
                //parameters
                if ((filterIDTextField.getText().trim().isEmpty() || //Filter ID is empty OR
                        Integer.toString(customer.getID()).trim().toLowerCase(Locale.ENGLISH).contains(filterIDTextField.getText().toLowerCase(Locale.ENGLISH))) && //Customer matches criteria
                        (filterNameTextField.getText().trim().isEmpty() || //Filter name is empty OR
                        customer.getName().trim().toLowerCase(Locale.ENGLISH).contains(filterNameTextField.getText().trim().toLowerCase(Locale.ENGLISH))) && //Customer matches criteria
                        (filterPhoneTextField.getText().trim().isEmpty() || //Filter Phone is empty OR
                        customer.getTelephone().trim().toLowerCase(Locale.ENGLISH).contains(filterPhoneTextField.getText().trim().toLowerCase(Locale.ENGLISH))) &&//Customer matches criteria
                        (filterAdressTextField.getText().trim().isEmpty() || //Adress field is empty OR
                        customer.getAdress().trim().toLowerCase(Locale.ENGLISH).contains(filterAdressTextField.getText().trim().toLowerCase(Locale.ENGLISH)))) //Customer matches criteria
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
