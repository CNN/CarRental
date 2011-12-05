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
        view = new View(this);
    }
    
    public static CarRental getInstance() {
        //should never happen that there is no instance, but just in case
        if(instance == null) return new CarRental();
        return instance;
    }
    
    public void requestVehicles() {
        view.setCurrentVehicles(model.getVehicles());
    }
    public void requestVehicle() {
        view.setCurrentVehicle(model.getVehicle(0));
    }
    public void requestVehicle(int id) {
        view.setCurrentVehicle(model.getVehicle(id));
    }
    
    public void requestVehicleTypes() {
        view.setCurrentVehicleTypes(model.getVehicleTypes());
    }
    public void requestVehicleType() {
        view.setCurrentVehicleType(model.getVehicleType(0));
    }
    public void requestVehicleType(int id) {
        view.setCurrentVehicleType(model.getVehicleType(id));
    }
    public void requestVehicleType(String name) {
        view.setCurrentVehicleType(model.getVehicleType(name));
    }
    
    public void requestMaintenances() {
        view.setCurrentMaintenances(model.getMaintenances());
    }
    public void requestMaintenance() {
        view.setCurrentMaintenance(model.getMaintenance(0));
    }
    public void requestMaintenance(int id) {
        view.setCurrentMaintenance(model.getMaintenance(id));
    }
    
    public void requestMaintenanceTypes() {
        view.setCurrentMaintenanceTypes(model.getMaintenanceTypes());
    }
    public void requestMaintenanceType() {
        view.setCurrentMaintenanceType(model.getMaintenanceType(0));
    }
    public void requestMaintenanceType(int id) {
        view.setCurrentMaintenanceType(model.getMaintenanceType(id));
    }
    
    public void requestCustomers() {
        view.setCurrentCustomers(model.getCustomers());
    }
    public void requestCustomer() {
        view.setCurrentCustomer(model.getCustomer(0));
    }
    public void requestCustomer(int id) {
        view.setCurrentCustomer(model.getCustomer(id));
    }
    public void requestCustomerByPhone(int phone) {
        view.setCurrentCustomer(model.getCustomerByPhone(phone));
    }
    
    public void requestReservations() {
        view.setCurrentReservations(model.getReservations());
    }
    public void requestReservation() {
        view.setCurrentReservation(model.getReservation(0));
    }
    public void requestReservation(int id) {
        view.setCurrentReservation(model.getReservation(id));
    }
//    public void requestBookings() {
//        view.setCurrentBookings(model.getBookings());
//    }
    
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
