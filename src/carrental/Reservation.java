/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.util.ArrayList;

/**
 * Reservation holds holds information on a reservation
 * @author CNN
 * @version 25 Nov. 2011
 */
public class Reservation implements Booking, Updateable {
    private final int ID;
    private int vehicleID;
    private long tStart;
    private long tEnd;
    private int customerID;
    
    /**
     * Constructor for reservation class
     * @param ID
     * @param vehicleID
     * @param tStart
     * @param tEnd
     * @param customerID 
     */
    public Reservation(int ID, int vehicleID, long tStart, long tEnd, int customerID){
        this.ID = ID;
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
    }
    
    /**
     * Update reservation object
     * @param vehicleID
     * @param tStart
     * @param tEnd
     * @param customerID 
     */
    public void updateObject(ArrayList<String> update){
        //TOOD Fix this:
        this.vehicleID = vehicleID;
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.customerID = customerID;
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
    public long getTStart(){
        return tStart;
    }
    /**
     * 
     * @return Time end
     */
    public long getTEnd(){
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
