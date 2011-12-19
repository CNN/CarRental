package carrental;

import java.sql.Timestamp;

/**
 * Reservation holds holds information about a reservation
 * @author CNN
 * @version 2011-11-30
 */
public class Reservation implements Booking {

    private final int ID;
    private int vehicleID;
    private Timestamp tStart;
    private Timestamp tEnd;
    private int customerID;
    private boolean isMaintenance;

    /**
     * This constructor is used to initialize the fields needed for an 
     * instance of Reservation
     * @param ID The ID number of this Reservation
     * @param vehicleID The vehicle ID number of this Reservation
     * @param tStart The start time of this Reservation
     * @param tEnd The end time of this Reservation
     * @param customerID The customer ID number of this Reservation
     */
    public Reservation(int ID, int vehicleID, Timestamp tStart, Timestamp 
            tEnd, int customerID) {
        this.ID = ID;
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
        isMaintenance = false;
    }

    /**
     * This method updates the Reservation with the supplied parameters
     * @param vehicleID The updated vehicle ID number of this Reservation
     * @param tStart The updated start time of this Reservation
     * @param tEnd The updated end time of this Reservation
     * @param customerID The updated customer ID number of this Reservation
     */
    public void updateObject(int vehicleID, Timestamp tStart, Timestamp tEnd, 
            int customerID) {
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
    }
    
    public boolean isMaintenance() {
        return isMaintenance;
    }

    public int getID() {
        return ID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public Timestamp getTStart() {
        return tStart;
    }

    public Timestamp getTEnd() {
        return tEnd;
    }

    /**
     * This method returns the customer ID number of this Maintenance
     * @return The customer ID number of this Maintenance
     */
    public int getCustomerID() {
        return customerID;
    }
}
