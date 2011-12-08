package carrental;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * CarRental acts as the main controller of the CarRental project. It creates
 * instances of the view-controller and model-controller and communicates
 * between these.
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
        view = new View();
    }
    
    /**
     * Get an instance of the CarRental controller. Useful for using the
     * controller's appendLog-feature.
     * @return instance of controller
     */
    public static CarRental getInstance() {
        //should never happen that there is no instance, but just in case
        if(instance == null) return new CarRental();
        return instance;
    }
    
    /**
     * Request list of vehicles from the model
     * @return list of vehicles
     */
    public ArrayList<Vehicle> requestVehicles() {
        return model.getVehicles();
    }
    
    /**
     * Request empty vehicle from the model
     * @return empty vehicle
     */
    public Vehicle requestVehicle() {
        return model.getVehicle();
    }
    
    /**
     * Request vehicle matching id from model
     * @param id id of vehicle
     * @return vehicle
     */
    public Vehicle requestVehicle(int id) {
        return model.getVehicle(id);
    }
    
    /**
     * Request list of vehicle types from model
     * @return list of vehicle types
     */
    public ArrayList<VehicleType> requestVehicleTypes() {
        return model.getVehicleTypes();
    }
    
    /**
     * Request an empty vehicle type object
     * @return empty vehicle type
     */
    public VehicleType requestVehicleType() {
        return model.getVehicleType();
    }
    
    /**
     * Request a vehicle type matching the provided id
     * @param id id of vehicle type
     * @return vehicle type
     */
    public VehicleType requestVehicleType(int id) {
        return model.getVehicleType(id);
    }
    
    /**
     * Request a vehicle type matching the provided name.
     * @param name name of vehicle type
     * @return vehicle type
     */
    public VehicleType requestVehicleType(String name) {
        return model.getVehicleType(name);
    }
    
    /**
     * Request a list of maintenances from model
     * @return list of maintenances
     */
    public ArrayList<Maintenance> requestMaintenances() {
        return model.getMaintenances();
    }
    
    /**
     * Request a single, empty maintenance from model
     * @return maintenance
     */
    public Maintenance requestMaintenance() {
        return model.getMaintenance();
    }
    
    /**
     * Request a maintenance from the model, matching the id
     * @param id id of maintenance
     * @return maintenance
     */
    public Maintenance requestMaintenance(int id) {
        return model.getMaintenance(id);
    }
    
    /**
     * Request a list of maintenance types from the model
     * @return list of maintenance types
     */
    public ArrayList<MaintenanceType> requestMaintenanceTypes() {
        return model.getMaintenanceTypes();
    }
    
    /**
     * Request a single, empty maintenance type from the model
     * @return maintenance type
     */
    public MaintenanceType requestMaintenanceType() {
        return model.getMaintenanceType();
    }
    
    /**
     * Request a maintenance type matching the provided id
     * @param id id of maintenance type
     * @return maintenance type
     */
    public MaintenanceType requestMaintenanceType(int id) {
        return model.getMaintenanceType(id);
    }
    
    /**
     * Request a list of customers from the model
     * @return list of customers
     */
    public ArrayList<Customer> requestCustomers() {
        return model.getCustomers();
    }
    
    /**
     * Request a single, empty customer from the model
     * @return customer
     */
    public Customer requestCustomer() {
        return model.getCustomer();
    }
    
    /**
     * Request a customer matching the id provided
     * @param id id of customer
     * @return customer
     */
    public Customer requestCustomer(int id) {
        return model.getCustomer(id);
    }
    
    /**
     * Request the first customer matching the phone number provided
     * @param phone Phone number of customer
     * @return customer
     */
    public Customer requestCustomerByPhone(int phone) {
        return model.getCustomerByPhone(phone);
    }
    
    /**
     * Request a list of reservations from the model
     * @return list of reservations
     */
    public ArrayList<Reservation> requestReservations() {
        return model.getReservations();
    }
    
    /**
     * Request an empty reservation from the model
     * @return reservation
     */
    public Reservation requestReservation() {
        return model.getReservation();
    }
    
    /**
     * Request the reservation matching the provided id from the model
     * @param id id of reservation
     * @return reservation
     */
    public Reservation requestReservation(int id) {
        return model.getReservation(id);
    }
    
    /**
     * Request a list of bookings from the model. Bookings are either
     * reservations or maintenances.
     * @return list of bookings
     */
    public ArrayList<Booking> requestBookings() {
        return model.getBookings();
    }
    
    /**
     * Request a list of bookings from the model related to the vehicle with the
     * specified id. Bookings are either reservations or maintenances.
     * @return list of bookings
     */
    public ArrayList<Booking> requestBookingsByVehicle(int v_id) {
        return model.getBookingsByVehicleId(v_id);
    }
    
    /**
     * Save a vehicle to the model
     * @param v Vehicle
     */
    public void saveVehicle(Vehicle v) {
        model.saveVehicle(v);
    }
    
    /**
     * Save a vehicletype to the model
     * @param vt VehicleType
     */
    public void saveVehicleType(VehicleType vt) {
        model.saveVehicleType(vt);
    }
    
    /**
     * Save a maintenance to the model
     * @param m Maintenance
     */
    public void saveMaintenance(Maintenance m) {
        model.saveMaintenance(m);
    }
    
    /**
     * Save a maintenance type to the model
     * @param mt maintenance type
     */
    public void saveMaintenanceType(MaintenanceType mt) {
        model.saveMaintenanceType(mt);
    }
    
    /**
     * Save a customer to the model
     * @param c customer
     */
    public void saveCustomer(Customer c) {
        model.saveCustomer(c);
    }
    
    /**
     * Save a reservation to the model
     * @param r reservation
     */
    public void saveReservation(Reservation r) {
        model.saveReservation(r);
    }
    
    /**
     * Append a string as a new line to the log. If something goes wrong or
     * is of interest as to determine when something goes wrong, it should be
     * added to the log.
     * @param string New line to add to the log.
     */
    public void appendLog(String string) {
        if(CarRental.DEBUG) System.out.println(string);
        log = log+"\n"+string;
    }
    
    /**
     * Append a string as a new line to the log. If something goes wrong or
     * is of interest as to determine when something goes wrong, it should be
     * added to the log.
     * Additionally takes an exception e, that will be printed before the string
     * and hence there is no reason to specify exception type in the string.
     * @param string New line to add to the log.
     * @param e Exception of add as a prefix to the string.
     */
    public void appendLog(String string, Exception e) {
        if(CarRental.DEBUG) System.out.println(e+": "+string);
        log = log+"\n"+string+": "+e;
    }
    
    /**
     * Prints out the entire log to the console.
     */
    private void printLog() {
        System.out.println(log);
    }
     
    public static void main(String[] args) {
        CarRental controller = new CarRental();
    }
    
}
