/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.sql.Timestamp;

/**
 * Reservation holds holds information on a reservation
 * @author CNN
 * @version 30 Nov. 2011
 */
public class Reservation implements Booking {
    private final int ID;
    private int vehicleID;
    private Timestamp tStart;
    private Timestamp tEnd;
    private int customerID;
    private boolean isMaintenance;
    
    /**
     * Constructor for reservation class
     * @param ID
     * @param vehicleID
     * @param tStart
     * @param tEnd
     * @param customerID 
     */
    public Reservation(int ID, int vehicleID, Timestamp tStart, Timestamp tEnd, int customerID){
        this.ID = ID;
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
        isMaintenance = false;
    }
    
    //TODO Delete this:
    /**
     * Constructor for reservation class. This is a Maintenance test
     * @param ID
     * @param vehicleID
     * @param tStart
     * @param tEnd
     */
    public Reservation(int ID, int vehicleID, Timestamp tStart, Timestamp tEnd){
        this.ID = ID;
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = 0000;
        isMaintenance = true;
    }
    
    /**
     * Update reservation object
     * @param vehicleID
     * @param tStart
     * @param tEnd
     * @param customerID 
     */
    public void updateObject(int vehicleID, Timestamp tStart, Timestamp tEnd, int customerID){
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
    }
    
    public boolean isBooked(Timestamp timestamp){
        return (timestamp.after(tStart) && timestamp.before(tEnd));
    }
    
    public boolean isMaintenance(){
        return isMaintenance;
    }
    /**
     * 
     * @return Reservation ID
     */
    public int getID(){
        return ID;
    }
    /**
     * 
     * @return Vehicle ID
     */
    public int getVehicleID(){
        return vehicleID;
    }
    /**
     * 
     * @return Time start
     */
    public Timestamp getTStart(){
        return tStart;
    }
    /**
     * 
     * @return Time end
     */
    public Timestamp getTEnd(){
        return tEnd;
    }
    /**
     * 
     * @return Customer ID
     */
    public int getCustomerID(){
        return customerID;
    }
}
