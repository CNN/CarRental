package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GUI for CarRental project
 * @author CNN
 * @version 1. Dec. 2011
 */
public class View {
    private JFrame frame;
    private final String title = "CarRental DeLuxe";
    private Font headline;
    private JPanel contentPane, 
            overview_panel, 
            vehicle_panel, 
            vehicle_type_panel,
            customer_panel,
            reservation_panel, 
            maintenance_panel,
            maintenance_type_panel;
    
    //contentPane
    private JButton homeBtn, reservationBtn, vehicleBtn, customerBtn;
    private JPanel cpTop, cpBottom, cpLeft;
    private JLabel cpStatusLabel;
    private JList menuList;
    
    // customer_panel
    private JPanel customer_panel_panel;
    private JLabel customerHeadlineLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerSearchLabel;
    private JTextField customerNameTextField, customerPhoneTextField, customerAdressTextField;
    private JList customerResultList;
    private JButton customerLoadBtn, customerSaveBtn;
    
    //overview_panel
    private JLabel opCenterLabel;
    
    public View(CarRental controller) {
        controller.appendLog("Creting View...");
        controller.appendLog("View created.");
        System.out.println(controller);
        
        buildGUI();
    }
    
    //Action performed when Save Customer button is pressed
    private void saveCustomerBtn(){
        
    }
    
    //Action performed when Soad Costumer button is pressed
    private void loadCustomerBtn(){
        
    }
    
    //Action performed when Home button is pressed
    private void homeBtn(){
        allInvisible();
        overview_panel.setVisible(true);
        frame.pack();
    }
    //Action performed when Reservation button is pressed
    private void reservationBtn(){
        allInvisible();
        reservation_panel.setVisible(true);
        frame.pack();
    }
    
    //Action performed when Vehicle button is pressed
    private void vehicleBtn(){
        allInvisible();
        vehicle_panel.setVisible(true);
        frame.pack();
    }
    
    //Action performed when Customer button is pressed
    private void customerBtn(){
        allInvisible();
        customer_panel.setVisible(true);
        frame.pack();
    }
    
    private void allInvisible(){
        overview_panel.setVisible(false);
        customer_panel.setVisible(false);
//        vehicle_panel.setVisible(false);
//        vehicle_type_panel.setVisible(false);
//        reservation_panel.setVisible(false);
//        maintenance_panel.setVisible(false);
//        maintenance_type_panel.setVisible(false);
    }
    
    //creates user interface
    private void buildGUI(){
        frame = new JFrame(title);
        
        //fonts
        headline = new Font("Arial", Font.PLAIN, 24);
        
        //contentPane
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        //customer panel
        customer_panel = new JPanel();
        customer_panel.setLayout(new BorderLayout());
        customer_panel_panel = new JPanel();
        customer_panel_panel.setLayout(new GridLayout(0, 2));
        
        customerHeadlineLabel = new JLabel("Create/Edit Customer");
        customerHeadlineLabel.setFont(headline);
        
        //TODO create proper String[] resultList
        String[] resultList = new String[] {    "Search results"    };
        customerResultList = new JList(resultList);
        customerSearchLabel = new JLabel("0 Results");
        
        customerLoadBtn = new JButton("Load Customer");
        customerSaveBtn = new JButton("Save Customer");
        
        customerLoadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadCustomerBtn();
            }
        });
        customerSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCustomerBtn();
            }
        });
        
        customerNameLabel = new JLabel("Name");
        customerPhoneLabel = new JLabel("Phone");
        customerAdressLabel = new JLabel("Adress");
        
        customerNameTextField = new JTextField();
        customerPhoneTextField = new JTextField();
        customerAdressTextField = new JTextField();
        
        customer_panel_panel.add(customerNameLabel);
        customer_panel_panel.add(customerNameTextField);
        customer_panel_panel.add(customerPhoneLabel);
        customer_panel_panel.add(customerPhoneTextField);
        customer_panel_panel.add(customerAdressLabel);
        customer_panel_panel.add(customerAdressTextField);
        
        customer_panel.add(customerHeadlineLabel, BorderLayout.NORTH);
        customer_panel.add(customer_panel_panel, BorderLayout.CENTER);
        customer_panel.add(customerLoadBtn, BorderLayout.SOUTH);
        customer_panel.add(customerSaveBtn, BorderLayout.SOUTH);
        customer_panel.add(customerResultList, BorderLayout.EAST);
        customer_panel.add(customerSearchLabel, BorderLayout.EAST);
        
        //contentPane left panel
        cpLeft = new JPanel();
        cpLeft.setLayout(new FlowLayout());
        
        //TODO create proper String[] menuItems
        String[] menuItems = new String[]{  "Menu", "This is some sort of a menu"   };
        menuList = new JList(menuItems);
        
        cpLeft.add(menuList);
        
        //contentPane top panel
        cpTop = new JPanel();
        cpTop.setLayout(new FlowLayout());
        
        homeBtn = new JButton("Home");
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Icons/Home1.PNG")));
        reservationBtn = new JButton("Reservations");
        reservationBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Icons/Reservation1.PNG")));
        vehicleBtn = new JButton("Vehicles");
        vehicleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Icons/Car1.PNG")));
        customerBtn = new JButton("Customers");
        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Icons/Person1.PNG")));
        
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homeBtn();
            }
        });
        reservationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservationBtn();
            }
        });
        vehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleBtn();
            }
        });
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerBtn();
            }
        });
        
        cpTop.add(homeBtn);
        cpTop.add(reservationBtn);
        cpTop.add(vehicleBtn);
        cpTop.add(customerBtn);
        
        //contentPane bottom panel
        cpBottom = new JPanel();
        cpBottom.setLayout(new FlowLayout());
        
        cpStatusLabel = new JLabel("Status: Ok");
        
        cpBottom.add(cpStatusLabel);
        
        //overview_panel
        overview_panel = new JPanel();
        opCenterLabel = new JLabel();
        
        opCenterLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Capture.PNG")));
        
        overview_panel.add(opCenterLabel);
        
        //final setups
        contentPane.add(customer_panel, BorderLayout.CENTER);
//        customer_panel.setVisible(false);
//        contentPane.add(overview_panel, BorderLayout.CENTER);
        contentPane.add(cpTop, BorderLayout.NORTH);
        contentPane.add(cpBottom, BorderLayout.SOUTH);
        contentPane.add(cpLeft, BorderLayout.WEST);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
