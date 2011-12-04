/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

/**
 * Vehicle holds information on each vehicle
 * @author CNN
 * @version 30 Nov. 2011
 */
public class Vehicle{
    private final int ID;
    private int vehicleType, odo, vin;
    private String description, licensplate, additional;

    /**
     * Constructor for Vehicle
     * @param ID
     * @param vehicleType
     * @param description
     * @param licensplate
     * @param odo
     * @param additional 
     */
    public Vehicle(int ID, int vehicleType, String description, String licensplate, int vin, int odo, String additional){
        this.ID = ID;
        this.vehicleType = vehicleType;
        this.description = description;
        this.vin = vin;
        this.licensplate = licensplate;
        this.odo = odo;
        this.additional = additional;
    }
    
    /**
     * Update object information
     * @param vehicleType
     * @param description
     * @param licensplate
     * @param odo
     * @param additional 
     */
    public void updateObject(int vehicleType, String description, String licensplate, int vin, int odo, String additional){
        this.vehicleType = vehicleType;
        this.description = description;
        this.licensplate = licensplate;
        this.vin = vin;
        this.odo = odo;
        this.additional = additional;
    }
    
    /**
     * @return Vehicle VIN number
     */
    public int getVIN(){
        return vin;
    }
    
    /**
     * 
     * @return Vehicle ID
     */
    public int getID(){
        return ID;
    }
    /**
     * 
     * @return vehicle type
     */
    public int getVehicleType(){
        return vehicleType;
    }
    /**
     * 
     * @return vehicle description
     */
    public String getDescription(){
        return description;
    }
    /**
     * 
     * @return licensplate
     */
    public String getLicensplate(){
        return licensplate;
    }
    /**
     * 
     * @return odo
     */
    public int getOdo(){
        return odo;
    }
    /**
     * 
     * @return additional information string
     */
    public String getAdditional(){
        return additional;
    }
}
