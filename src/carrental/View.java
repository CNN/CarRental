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
                center = new JPanel(),
                northReservation = new JPanel(),
                northCustomer = new JPanel(),
                northVehicle = new JPanel(),
                northMaintenance = new JPanel(),
                vehiclePanel = new VehiclePanel();
        
        public MainPanel() {
            this.setLayout(new BorderLayout());
            
            //build west
            west.setLayout(new FlowLayout());
            west.add(west_inner);
            west_inner.setLayout(new GridLayout(0,1));
            JButton reservationButton = new JButton("Reservation");
            reservationButton.setFont(new Font("Arial",Font.BOLD,16));
            JButton customerButton = new JButton("Customer");
            customerButton.setFont(new Font("Arial",Font.BOLD,16));
            JButton vehicleButton = new JButton("Vehicle");
            vehicleButton.setFont(new Font("Arial",Font.BOLD,16));
            JButton maintenanceButton = new JButton("Maintenance");
            maintenanceButton.setFont(new Font("Arial",Font.BOLD,16));
            west_inner.add(reservationButton);
            west_inner.add(customerButton);
            west_inner.add(vehicleButton);
            west_inner.add(maintenanceButton);
            
            //build northReservation
            
            //build northCustomer
            
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
            center.add(vehiclePanel);
            
            center_super.setLayout(new BorderLayout());
            center_super.add(center, BorderLayout.CENTER);
            center_super.add(northVehicle, BorderLayout.NORTH);
            this.add(west, BorderLayout.WEST);
            this.add(center_super, BorderLayout.CENTER);
        }
    }
}