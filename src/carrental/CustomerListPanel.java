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
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
public class CustomerListPanel extends JPanel {

    private ArrayList<Customer> customers = CarRental.getInstance().requestCustomers(); //update customers array
    private Customer customerToView;
    private JTextField filterAdressTextField, filterPhoneTextField, filterNameTextField, filterIDTextField;
    private JTable customerTable;
    private DefaultTableModel customerTableModel;
    private JButton cancelButton, viewButton;
    
    public CustomerListPanel() {
        if (customers.get(0) != null) {
            customerToView = customers.get(0);
        } else {
            customerToView = CarRental.getInstance().requestCustomer();
        }

        //Fields
        JPanel centerPanel, customerListPanel, filterPanel, topFilterPanel, bottomFilterPanel, buttonPanel;
        JScrollPane scrollPane;
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
        //centerPanel.add(filterPanel);

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
        cancelButton.setVisible(false);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        //View-button
        viewButton = new JButton("View selected");
        viewButton.setVisible(false);
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
    
    public void filter() {
        //Delete exisiting rows
        customerTableModel.setRowCount(0);
        //Add the rows that match the filter
        for (Customer customer : customers) {
            //parameters
            if (filterIDTextField.getText().trim().isEmpty() || //Filter ID is empty OR
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
        customerTable.setPreferredScrollableViewportSize(new Dimension(width, height));
    }
}