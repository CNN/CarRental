package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font.*;

/**
 * GUI for CarRental project
 * @author CNN
 * @version 1. Dec. 2011
 */
public class View {
    private JFrame frame;
    private final String title = "CarRental DeLuxe";
    private JPanel main;
    
    public View(CarRental controller) {
        controller.appendLog("Creating View...");
        
        frame = new JFrame(title);
        
        main = new MainPanel();
        frame.add(main);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        controller.appendLog("View created.");
    }
    
    class MainPanel extends JPanel {
        private JPanel west = new JPanel(),
                west_inner = new JPanel(),
                center_super = new JPanel(),
                center_north = new JPanel(),
                center = new JPanel(),
                northReservation = new JPanel(),
                northCustomer = new JPanel(),
                northVehicle = new JPanel(),
                northMaintenance = new JPanel(),
                vehiclePanel = new VehiclePanel(),
                maintenancePanel = new JPanel(),
                reservationPanel = new JPanel(),
                customerPanel = new JPanel();
        //TODO: not yet added: reservationpanel, maintenancepanel, customerpanel
        
        public MainPanel() {
            this.setLayout(new BorderLayout());
            
            //build west
            west.setLayout(new FlowLayout());
            west.setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.LIGHT_GRAY));
            west.add(west_inner);
            west_inner.setLayout(new GridLayout(0,1));
            
            JButton reservationButton = new JButton("Reservation");
            reservationButton.setFont(new Font("Arial",Font.BOLD,16));
            reservationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectReservation();
                }
            });
            
            JButton customerButton = new JButton("Customer");
            customerButton.setFont(new Font("Arial",Font.BOLD,16));
            customerButton.addActionListener(new ActionListener() {
                @Override    
                public void actionPerformed(ActionEvent e) {
                    selectCustomer();
                }
            });
            
            JButton vehicleButton = new JButton("Vehicle");
            vehicleButton.setFont(new Font("Arial",Font.BOLD,16));
            vehicleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectVehicle();
                }
            });
            
            JButton maintenanceButton = new JButton("Maintenance");
            maintenanceButton.setFont(new Font("Arial",Font.BOLD,16));
            maintenanceButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    selectMaintenance();
                }
            });
            
            west_inner.add(reservationButton);
            west_inner.add(customerButton);
            west_inner.add(vehicleButton);
            west_inner.add(maintenanceButton);
            
            //build northReservation
            JButton reservationCreate = new JButton("Create");
            JButton reservationList = new JButton("List");
            northReservation.setLayout(new FlowLayout(FlowLayout.LEFT));
            northReservation.add(reservationCreate);
            northReservation.add(reservationList);
            
            //build northCustomer
            JButton customerCreate = new JButton("Create");
            JButton customerList = new JButton("List");
            northCustomer.setLayout(new FlowLayout(FlowLayout.LEFT));
            northCustomer.add(customerCreate);
            northCustomer.add(customerList);
            
            //build northVehicle
            JButton vehicleCreate = new JButton("Create");
            JButton vehicleTypeCreate = new JButton("Create Vehicle Type");
            JButton vehicleList = new JButton("List");
            JButton vehicleOverview = new JButton("Overview");
            northVehicle.setLayout(new FlowLayout(FlowLayout.LEFT));
            northVehicle.add(vehicleCreate);
            northVehicle.add(vehicleTypeCreate);
            northVehicle.add(vehicleList);
            northVehicle.add(vehicleOverview);
            
            //build northMaintenance
            JButton maintenanceCreate = new JButton("Create");
            JButton maintenanceTypeCreate = new JButton("Create Maintenance Type");
            JButton maintenanceList = new JButton("List");
            northMaintenance.setLayout(new FlowLayout(FlowLayout.LEFT));
            northMaintenance.add(maintenanceCreate);
            northMaintenance.add(maintenanceTypeCreate);
            northMaintenance.add(maintenanceList);
                    
            //build center
            center.setLayout(new FlowLayout());
            center.setPreferredSize(new Dimension(800,600));
            center.add(vehiclePanel);
            
            center_north.setLayout(new FlowLayout(FlowLayout.LEFT));
            center_north.add(northVehicle);
            center_north.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.LIGHT_GRAY));
            center_super.setLayout(new BorderLayout());
            center_super.add(center, BorderLayout.CENTER);
            center_super.add(center_north, BorderLayout.NORTH);
            this.add(west, BorderLayout.WEST);
            this.add(center_super, BorderLayout.CENTER);
        }
        
        public void selectVehicle() {
            center.removeAll();
            center_north.removeAll();
            frame.pack();
            center.add(vehiclePanel);
            center_north.add(northVehicle);
            frame.pack();
        }
        
        public void selectMaintenance() {
            center.removeAll();
            center_north.removeAll();
            frame.pack();
            center.add(maintenancePanel);
            center_north.add(northMaintenance);
            frame.pack();
        }
        
        public void selectReservation() {
            center.removeAll();
            center_north.removeAll();
            frame.pack();
            center.add(reservationPanel);
            center_north.add(northReservation);
            frame.pack();
        }
        
        public void selectCustomer() {
            center.removeAll();
            center_north.removeAll();
            frame.pack();
            center.add(customerPanel);
            center_north.add(northCustomer);
            frame.pack();
        }
    }
}