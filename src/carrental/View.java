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
            JButton customerButton = new JButton("Customer");
            JButton vehicleButton = new JButton("Vehicle");
            JButton maintenanceButton = new JButton("Maintenance");
            west_inner.add(reservationButton);
            west_inner.add(customerButton);
            west_inner.add(vehicleButton);
            west_inner.add(maintenanceButton);
            
            //build northReservation
            
            //build northCustomer
            
            //build northVehicle
            
            //build northMaintenance
            JButton maintenanceCreate = new JButton("Create");
            JButton maintenanceTypeCreate = new JButton("Create Maintenance Type");
            JButton maintenanceList = new JButton("List");
            northMaintenance.setLayout(new FlowLayout());
            northMaintenance.add(maintenanceCreate);
            northMaintenance.add(maintenanceTypeCreate);
            northMaintenance.add(maintenanceList);
                    
            //build center
            center.setLayout(new FlowLayout());
            center.add(vehiclePanel);
            
            this.add(west, BorderLayout.WEST);
            this.add(center, BorderLayout.CENTER);
            this.add(northVehicle, BorderLayout.NORTH);
        }
    }
}