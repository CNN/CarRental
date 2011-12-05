package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font.*;
import java.util.ArrayList;

/**
 * GUI for CarRental project
 * @author CNN
 * @version 1. Dec. 2011
 */
public class View {
    private static View view;
    private CarRental controller;
    private JFrame frame;
    private final String title = "CarRental DeLuxe";
    private JPanel main;
    private ArrayList<Vehicle> current_vehicles;
    private Vehicle current_vehicle;
    private ArrayList<VehicleType> current_vehicle_types;
    private VehicleType current_vehicle_type;
    private ArrayList<Maintenance> current_maintenances;
    private Maintenance current_maintenance;
    private ArrayList<MaintenanceType> current_maintenance_types;
    private MaintenanceType current_maintenance_type;
    private ArrayList<Customer> current_customers;
    private Customer current_customer;
    private ArrayList<Reservation> current_reservations;
    private Reservation current_reservation;
    
    private View(CarRental c) {
        controller = c;
        controller.appendLog("Creating View...");
        
        frame = new JFrame(title);
        
        controller.requestVehicles();
        assert current_vehicles != null: "View->Controller Request Vehicles Failed";
        controller.requestVehicle();
        assert current_vehicle != null: "View->Controller Request Vehicle Failed";
        controller.requestVehicleTypes();
        assert current_vehicle_types != null: "View->Controller Request Vehicle Types Failed";
        controller.requestVehicleType();
        assert current_vehicle_type != null: "View->Controller Request Vehicle Type Failed";
        controller.requestMaintenances();
        assert current_maintenances != null: "View->Controller Request Maintenances Failed";
        controller.requestMaintenance();
        assert current_maintenance != null: "View->Controller Request Maintenance Failed";
        controller.requestMaintenanceTypes();
        assert current_maintenance_types != null: "View->Controller Request Maintenance Types Failed";
        controller.requestMaintenanceType();
        assert current_maintenance_type != null: "View->Controller Request Maintenance Type Failed";
        controller.requestCustomers();
        assert current_customers != null: "View->Controller Request Customers Failed";
        controller.requestCustomer();
        assert current_customer != null: "View->Controller Request Customer Failed";
        controller.requestReservations();
        assert current_reservations != null: "View->Controller Request Reservations Failed";
        controller.requestReservation();
        assert current_reservation != null: "View->Controller Request Reservation Failed";
        //controller.requestBookings();
        //assert current_bookings != null: "View->Controller Request Bookings Failed";
        //TODO: Make bookings requestable
        
        main = new MainPanel();
        frame.add(main);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        controller.appendLog("View created.");
    }
    
    public static View getInstance(CarRental controller) {
        if(view == null) view = new View(controller);
        return view;
    }
    
    public static View getInstance() {
        return view;
    }
    
    public void setCurrentVehicles(ArrayList<Vehicle> vs) {
        current_vehicles = vs;
    }
    public void setCurrentVehicle(Vehicle v) {
        current_vehicle = v;
    }
    public void setCurrentVehicleTypes(ArrayList<VehicleType> vts) {
        current_vehicle_types = vts;
    }
    public void setCurrentVehicleType(VehicleType vt) {
        current_vehicle_type = vt;
    }
    public void setCurrentMaintenances(ArrayList<Maintenance> ms) {
        current_maintenances = ms;
    }
    public void setCurrentMaintenance(Maintenance m) {
        current_maintenance = m;
    }
    public void setCurrentMaintenanceTypes(ArrayList<MaintenanceType> mts) {
        current_maintenance_types = mts;
    }
    public void setCurrentMaintenanceType(MaintenanceType mt) {
        current_maintenance_type = mt;
    }
    public void setCurrentCustomers(ArrayList<Customer> cs) {
        current_customers = cs;
    }
    public void setCurrentCustomer(Customer c) {
        current_customer = c;
    }
    public void setCurrentReservations(ArrayList<Reservation> rs) {
        current_reservations = rs;
    }
    public void setCurrentReservation(Reservation r) {
        current_reservation = r;
    }
    
    public void vehiclePanelVehicle(int id) {
        controller.requestVehicle(id);
        main.updateVehiclePanel();
    }
    public void vehiclePanelVehicleType(int id) {
        controller.requestVehicleType(id);
        main.updateVehiclePanel();
    }
    public void saveVehicle(Vehicle v) {
        
    }
    public void saveVehicleType(VehicleType vt) {
        
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
                maintenancePanel = new JPanel(),
                reservationPanel = new JPanel(),
                customerPanel = new JPanel();
        private VehiclePanel vehiclePanel = new VehiclePanel();
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
            vehicleCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vehiclePanel.showCreatePanel();
                }
            });
            
            JButton vehicleTypeCreate = new JButton("Create Vehicle Type");
            vehicleTypeCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vehiclePanel.showAddTypePanel();
                }
            });
            
            JButton vehicleList = new JButton("List");
            vehicleList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vehiclePanel.showListPanel();
                }
            });
            JButton vehicleOverview = new JButton("Overview");
            vehicleOverview.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vehiclePanel.showMainScreenPanel();
                }
            });
            
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
        
        public void updateVehiclePanel() {
            vehiclePanel.setVehicleList(current_vehicles);
            vehiclePanel.setVehicleTypes(current_vehicle_types);
            vehiclePanel.setVehicleToView(current_vehicle);
            vehiclePanel.setVehicleTypeToView(current_vehicle_type);
        }
    }
}