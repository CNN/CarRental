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
    private JLabel customerNameLabel, customerPhoneLabel, customerAdressLabel, customerSearchLabel;
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
    
    //Action performed when Home button is pressed
    private void homeBtn(){
        allInvisible();
        overview_panel.setVisible(true);
    }
    //Action performed when Reservation button is pressed
    private void reservationBtn(){
        allInvisible();
        reservation_panel.setVisible(true);
    }
    
    //Action performed when Vehicle button is pressed
    private void vehicleBtn(){
        allInvisible();
        vehicle_panel.setVisible(true);
    }
    
    //Action performed when Customer button is pressed
    private void customerBtn(){
        allInvisible();
        customer_panel.setVisible(true);
    }
    
    private void allInvisible(){
        overview_panel.setVisible(false);
        vehicle_panel.setVisible(false);
        vehicle_type_panel.setVisible(false);
        customer_panel.setVisible(false);
        reservation_panel.setVisible(false);
        maintenance_panel.setVisible(false);
        maintenance_type_panel.setVisible(false);
    }
    
    //creates user interface
    private void buildGUI(){
        frame = new JFrame(title);
        
        //contentPane
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        //customer panel
        customer_panel = new JPanel();
        customer_panel_panel = new JPanel();
        
        customer_panel.add(customer_panel_panel);
        
        //contentPane left panel
        cpLeft = new JPanel();
        cpLeft.setLayout(new FlowLayout());
        
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
        customer_panel.setVisible(false);
        contentPane.add(overview_panel, BorderLayout.CENTER);
        contentPane.add(cpTop, BorderLayout.NORTH);
        contentPane.add(cpBottom, BorderLayout.SOUTH);
        contentPane.add(cpLeft, BorderLayout.WEST);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
