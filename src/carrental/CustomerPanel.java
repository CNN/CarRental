package carrental;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Calendar;
import javax.swing.border.TitledBorder;

/**
 * This is the main panel regarding vehicles.
 * It contains JPanels for every relevant screen, when dealing with customers.
 * @author CNN
 * @version 2011-12-07
 */
public class CustomerPanel extends SuperPanel {

    private JPanel emptyPanel = null;
    private Customer customerToView; //specific customer, used to view details
    private ArrayList<Customer> customers; //array of costumers

    public CustomerPanel() {
        this.customers = CarRental.getInstance().requestCustomers();
        if (customers.get(0) != null) {
            customerToView = customers.get(0);
        } else {
            customerToView = CarRental.getInstance().requestCustomer();
        }

        //Sets the different subpanels. Also adds them to this object with JPanel.add().
        AssignAndAddSubPanels(new MainScreenPanel(), new CreatePanel(), new ViewEntityPanel(), emptyPanel, new ListPanel());
        this.setPreferredSize(new Dimension(800, 600));

        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //For testing: Choose your panel
        //showCreatePanel();
        //showMainScreenPanel();
        showViewEntityPanel();
        //showListPanel();
    }

    //Temporary Main
    //TODO: Remove this:
    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerPanel");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(new CustomerPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public void setCustomerToView(Customer customer) {
        customerToView = customer;
    }

    public void setCustomerList(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Updates entire panel
     */
    public void updateCustomerPanel() {
        customers = CarRental.getInstance().requestCustomers();
        if (customers.get(0) != null) {
            customerToView = customers.get(0);
        } else {
            customerToView = CarRental.getInstance().requestCustomer();
        }

//        CreatePanel.updateCreatePanel();
//        ViewEntityPanel.updateViewEntityPanel();
//        ListPanel.updateListPanel();
    }

    public class MainScreenPanel extends JPanel {

        public MainScreenPanel() {
            //Fields
            TitledBorder titleBorder;

            setLayout(new BorderLayout());
            titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Customers");
            setBorder(titleBorder);
            add(new ViewEntityPanel());
            add(new ListPanel());
        }
    }

    public class CreatePanel extends JPanel {

        JTextField customerIDTextField, customerNameTextField, customerPhoneTextField, customerAdressTextField, customerEMailTextField;

        public CreatePanel() {
            //Fields
            JPanel centerPanel, idPanel, namePanel, phonePanel, adressPanel, eMailPanel, buttonPanel;
            JLabel customerIDLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerEMailLabel;
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
            customerIDTextField = new JTextField(" Automaticly generated", defaultJTextFieldColumns);
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

            //TODO Split adress
            //Adress
            customerAdressLabel = new JLabel("Adress");
            customerAdressTextField = new JTextField(defaultJTextFieldColumns);
            adressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            adressPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            adressPanel.add(customerAdressLabel);
            adressPanel.add(Box.createRigidArea(new Dimension(79 + strutDistance, 0)));
            adressPanel.add(customerAdressTextField);
            centerPanel.add(adressPanel);

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
                    customerIDTextField.setText(" " + customers.size() + 1);
                    CarRental.getInstance().saveCustomer(new Customer(
                            customers.size() + 1,
                            Integer.parseInt(customerPhoneTextField.getText()),
                            customerNameTextField.getText(),
                            customerAdressTextField.getText(),
                            customerEMailTextField.getText()));
                    customers = CarRental.getInstance().requestCustomers();
                    updateCreatePanel();
                }
            });
            buttonPanel.add(createButton);
        }

        public void updateCreatePanel() {
            customerIDTextField.setText(" Automaticly generated");
            customerPhoneTextField.setText("");
            customerNameTextField.setText("");
            customerAdressTextField.setText("");
            customerEMailTextField.setText("");
        }
    }

    public class ViewEntityPanel extends JPanel {

        String customerID, customerName, customerPhone, customerAdress, customerEMail;
        JTextField customerIDTextField, customerNameTextField, customerPhoneTextField, customerAdressTextField, customerEMailTextField;

        public ViewEntityPanel() {
            //Fields
            JPanel centerPanel, idPanel, namePanel, phonePanel, adressPanel, eMailPanel, buttonPanel;
            JLabel customerIDLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerEMailLabel;
            JButton cancelButton;
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
            customerNameTextField.setEditable(false);
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
            customerPhoneTextField.setEditable(false);
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
            customerAdressTextField.setEditable(false);
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

            //TODO insert list

            //ButtonPanel
            buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
            buttonPanel.add(Box.createHorizontalGlue());

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

        public void setCustomerTextFields(Customer customer) {
            customerID = "" + customer.getID();
            customerName = customer.getName();
            customerPhone = "" + customer.getTelephone();
            customerAdress = customer.getAdress();
            customerEMail = customer.getEMail();
        }

        public void updateViewEntityPanel() {
            setCustomerTextFields(customerToView);
            customerIDTextField.setText(" " + customerID);
            customerNameTextField.setText(" " + customerName);
            customerPhoneTextField.setText(" " + customerPhone);
            customerAdressTextField.setText(" " + customerAdress);
            customerEMailTextField.setText(" " + customerEMail);
        }
    }

    public void makeAddTypePanel() { //not used
    }

    public class ListPanel extends JPanel {
        JTextField filterAdressTextField, filterPhoneTextField, filterNameTextField, filterIDTextField;

        public ListPanel() {
            //Fields
            JPanel centerPanel, customerListPanel, filterPanel, topFilterPanel, bottomFilterPanel, buttonPanel;
            JScrollPane scrollPane;
            JTable customerTable;
            JButton cancelButton, filterButton;
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

            //Testing Table setup
            Object[] columnNames = {"ID", "Name", "Phone", "Adress"};
            ArrayList<ArrayList<String>> customerData = new ArrayList<>();
            ArrayList<String> rowData;
            //TODO Add adress needs splitting
            //getting the data in the arrayList - this might be unnecessary in final implementation depending on how this class receives the simple type objects.
            Customer testCustomer = new Customer(100, 123456789, "Hans Hansen", "Jylland", "email");
            for (int i = 0; i < 30; i++) {
                rowData = new ArrayList<>();
                rowData.add("" + testCustomer.getID());
                rowData.add("" + testCustomer.getTelephone());
                rowData.add(testCustomer.getName());
                rowData.add(testCustomer.getAdress());
                //rowData.add(testCustomer.getEMail()); //makes assert error on build
                customerData.add(rowData);
                assert customerData.get(i).size() == columnNames.length;
            }

            //Converting to Object[][] for the JTable
            Object[][] tableData = new Object[customerData.size()][columnNames.length];
            for (int i = 0; i < customerData.size(); i++) { //  'i' represents a row
                for (int j = 0; j < columnNames.length; j++) { //'j' represents a certain cell on row 'i'
                    tableData[i][j] = customerData.get(i).get(j); //out of bounds cannot happen because of the conditions in the for loops.
                }
            }

            //Creating the table
            customerTable = new JTable(tableData, columnNames);
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

            //cancel-Button
            cancelButton = new JButton("Back");
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO NICLASONLY Set text() for all fields aka blank
                    showMainScreenPanel();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            //filter-button
            filterButton = new JButton("Filter");
            filterButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO NICLASONLY make the database update here
                }
            });
            buttonPanel.add(filterButton);
        }
        
        public void updateListPanel(){
            setFilterTextFields();
            //TODO Somehow update list
        }
        
        public void setFilterTextFields(){
            filterAdressTextField.setText("");
            filterPhoneTextField.setText("");
            filterNameTextField.setText("");
            filterIDTextField.setText("");
        }
    }
}