package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

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
            headlinePanel, 
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
    
    //headlinePanel
    private JLabel headlineLabel;
    
    // customer_panel
    private JPanel customer_panel_left, customer_panel_center, customer_panel_right;
    private JLabel customerIDLabel, customerNameLabel, customerPhoneLabel, customerAdressLabel, customerSearchLabel;
    private JTextField customerIDTextField, customerNameTextField, customerPhoneTextField, customerAdressTextField;
    private JList customerResultList;
    private JButton customerLoadBtn, customerSaveBtn;
    
    //overview_panel
    private JLabel opCenterLabel;
    
    public View(CarRental controller) {
        controller.appendLog("Creating View...");
        controller.appendLog("View created.");
        System.out.println(controller);
        
        buildGUI();
    }
    
    //quit
    private void quit(){
        //TODO quit model and controller
        System.exit(0);
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
        setHeadline("Create/Edit Reservation");
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
        setHeadline("Create/Edit Customer");
        headlinePanel.setVisible(true);
        customer_panel.setVisible(true);
        frame.pack();
    }
    
    private void allInvisible(){
        overview_panel.setVisible(false);
        headlinePanel.setVisible(false);
        customer_panel.setVisible(false);
//        vehicle_panel.setVisible(false);
//        vehicle_type_panel.setVisible(false);
//        reservation_panel.setVisible(false);
//        maintenance_panel.setVisible(false);
//        maintenance_type_panel.setVisible(false);
    }
    
    private void setHeadline(String headline){
        headlineLabel.setText(headline);
    }
    
    //creates user interface
    private void buildGUI(){
        frame = new JFrame(title);
        makeMenuBar(frame);
        
        //fonts
        headline = new Font("Arial", Font.PLAIN, 24);
        
        //contentPane
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        //headlinePanel
        headlinePanel = new JPanel();
        headlinePanel.setLayout(new BorderLayout());
        headlinePanel.setBorder(new EtchedBorder());
        //TODO Find proper color
        headlinePanel.setBackground(Color.cyan);
        
        headlineLabel = new JLabel("Headline");
        headlineLabel.setFont(headline);
        headlineLabel.setHorizontalAlignment(JLabel.CENTER);
        
        headlinePanel.add(headlineLabel, BorderLayout.NORTH);
        
        //reservation panel
        
        
        //customer panel
        customer_panel = new JPanel();
        customer_panel.setLayout(new GridLayout(0, 3));
        customer_panel.setBorder(new EtchedBorder());
        customer_panel_left = new JPanel();
        customer_panel_left.setLayout(new GridLayout(0, 1));
        customer_panel_center = new JPanel();
        customer_panel_center.setLayout(new GridLayout(0, 1));
        customer_panel_right = new JPanel();
        customer_panel_right.setLayout(new GridLayout(0, 1));
        
        //TODO create proper String[] resultList
        String[] resultList = new String[] {    "Search results"    };
        customerResultList = new JList(resultList);
        
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
        
        customerIDLabel = new JLabel("Customer ID: ");
        customerIDLabel.setHorizontalAlignment(JLabel.RIGHT);
        customerNameLabel = new JLabel("Name");
        customerNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        customerPhoneLabel = new JLabel("Phone");
        customerPhoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        customerAdressLabel = new JLabel("Adress");
        customerAdressLabel.setHorizontalAlignment(JLabel.RIGHT);
        customerSearchLabel = new JLabel("0 Results");
        customerSearchLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        customerIDTextField = new JTextField("0000");
        customerIDTextField.setEditable(false);
        customerIDTextField.setHorizontalAlignment(JTextField.RIGHT);
        customerNameTextField = new JTextField();
        customerPhoneTextField = new JTextField();
        customerAdressTextField = new JTextField();
        
        customer_panel_left.add(customerIDLabel);
        customer_panel_left.add(customerNameLabel);
        customer_panel_left.add(customerPhoneLabel);
        customer_panel_left.add(customerAdressLabel);
        customer_panel_left.add(customerLoadBtn);
        
        customer_panel_center.add(customerIDTextField);
        customer_panel_center.add(customerNameTextField);
        customer_panel_center.add(customerPhoneTextField);
        customer_panel_center.add(customerAdressTextField);
        customer_panel_center.add(customerSaveBtn);
        
        customer_panel_right.add(customerResultList);
        customer_panel_right.add(customerSearchLabel);
        
        customer_panel.add(customer_panel_left);
        customer_panel.add(customer_panel_center);
        customer_panel.add(customer_panel_right);
        
        //contentPane left panel
        cpLeft = new JPanel();
        cpLeft.setLayout(new FlowLayout());
        cpLeft.setBorder(new EtchedBorder());
        
        //TODO create proper String[] menuItems
        String[] menuItems = new String[]{  "Menu", "This is some sort of a menu"   };
        menuList = new JList(menuItems);
        
        cpLeft.add(menuList);
        
        //contentPane top panel
        cpTop = new JPanel();
        cpTop.setLayout(new FlowLayout());
        cpTop.setBorder(new EtchedBorder());
        
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
        overview_panel.setBorder(new EtchedBorder());
        opCenterLabel = new JLabel();
        
        opCenterLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carrental/Capture.PNG")));
        
        overview_panel.add(opCenterLabel);
        
        //final setups
        headlinePanel.add(customer_panel, BorderLayout.CENTER);
        contentPane.add(headlinePanel, BorderLayout.CENTER);
        headlinePanel.setVisible(true);
        contentPane.add(headlinePanel, BorderLayout.CENTER);
        contentPane.add(cpTop, BorderLayout.NORTH);
        contentPane.add(cpBottom, BorderLayout.SOUTH);
        contentPane.add(cpLeft, BorderLayout.WEST);
        
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    private void makeMenuBar(JFrame frame){
        JMenuBar menubar = new JMenuBar();
        JMenu menu, newMenu;
        JMenuItem item;
        
        frame.setJMenuBar(menubar);
        
        //File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        newMenu = new JMenu("New");
        menu.add(newMenu);
        
        item = new JMenuItem("Reservation");
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {    reservationBtn();   }
            }
        );
        newMenu.add(item);
        
        item = new JMenuItem("Customer");
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {    customerBtn();   }
            }
        );
        newMenu.add(item);
        
        item = new JMenuItem("Vehicle");
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {    vehicleBtn();   }
            }
        );
        newMenu.add(item);
        
        menu.addSeparator();
        
        item = new JMenuItem("Quit");
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {    quit();   }
            }
        );
        menu.add(item);
        
        //Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About");
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {       }
            }
        );
        menu.add(item);
    }
}
