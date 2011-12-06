package carrental;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author CNN
 * @version 2011-12-02
 */
public final class CarRental {
    public static final boolean DEBUG = true;
    private static CarRental instance;
    private Model model;
    private View view;
    private String log;
    
    private CarRental() {
        //next line is ugly
        if(instance == null) instance = this;
        appendLog("Initializing...");
        model = new Model();
        model.getBookings();
        view = View.getInstance(this);
    }
    
    public static CarRental getInstance() {
        //should never happen that there is no instance, but just in case
        if(instance == null) return new CarRental();
        return instance;
    }
    
    public ArrayList<Vehicle> requestVehicles() {
        return model.getVehicles();
    }
    public Vehicle requestVehicle() {
        return model.getVehicle();
    }
    public Vehicle requestVehicle(int id) {
        return model.getVehicle(id);
    }
    
    public ArrayList<VehicleType> requestVehicleTypes() {
        return model.getVehicleTypes();
    }
    public VehicleType requestVehicleType() {
        return model.getVehicleType();
    }
    public VehicleType requestVehicleType(int id) {
        return model.getVehicleType(id);
    }
    public VehicleType requestVehicleType(String name) {
        return model.getVehicleType(name);
    }
    
    public ArrayList<Maintenance> requestMaintenances() {
        return model.getMaintenances();
    }
    public Maintenance requestMaintenance() {
        return model.getMaintenance();
    }
    public Maintenance requestMaintenance(int id) {
        return model.getMaintenance(id);
    }
    
    public ArrayList<MaintenanceType> requestMaintenanceTypes() {
        return model.getMaintenanceTypes();
    }
    public MaintenanceType requestMaintenanceType() {
        return model.getMaintenanceType();
    }
    public MaintenanceType requestMaintenanceType(int id) {
        return model.getMaintenanceType(id);
    }
    
    public ArrayList<Customer> requestCustomers() {
        return model.getCustomers();
    }
    public Customer requestCustomer() {
        return model.getCustomer();
    }
    public Customer requestCustomer(int id) {
        return model.getCustomer(id);
    }
    public Customer requestCustomerByPhone(int phone) {
        return model.getCustomerByPhone(phone);
    }
    
    public ArrayList<Reservation> requestReservations() {
        return model.getReservations();
    }
    public Reservation requestReservation() {
        return model.getReservation();
    }
    public Reservation requestReservation(int id) {
        return model.getReservation(id);
    }
    public void requestBookings() {
        view.setCurrentBookings(model.getBookings());
    }
    
    public void saveVehicle(Vehicle v) {
        model.saveVehicle(v);
    }
    public void saveVehicleType(VehicleType vt) {
        model.saveVehicleType(vt);
    }
    public void saveMaintenance(Maintenance m) {
        model.saveMaintenance(m);
    }
    public void saveMaintenanceType(MaintenanceType mt) {
        model.saveMaintenanceType(mt);
    }
    public void saveCustomer(Customer c) {
        model.saveCustomer(c);
    }
    public void saveReservation(Reservation r) {
        model.saveReservation(r);
    }
    
    public void appendLog(String string) {
        if(CarRental.DEBUG) System.out.println(string);
        log = log+"\n"+string;
    }
    public void appendLog(String string, Exception e) {
        if(CarRental.DEBUG) System.out.println(e+": "+string);
        log = log+"\n"+string+": "+e;
    }
    
    private void printLog() {
        System.out.println(log);
    }
     
    public static void main(String[] args) {
        CarRental controller = new CarRental();
    }
    
}
