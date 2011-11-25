/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

/**
 * Vehicle holds information on each vehicle
 * @author CNN
 * @version 25 Nov. 2011
 */
public class Vehicle //implements Upgradeable
{
    private final int ID;
    private int vehicleType;
    private String description;
    private String licensplate;
    private int odo;
    private String additional;
    
    /**
     * Constructor for Vehicle
     * @param ID
     * @param vehicleType
     * @param description
     * @param licensplate
     * @param odo
     * @param additional 
     */
    public Vehicle(int ID, int vehicleType, String description, String licensplate, int odo, String additional){
        this.ID = ID;
        this.vehicleType = vehicleType;
        this.description = description;
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
    public void updateObject(int vehicleType, String description, String licensplate, int odo, String additional){
        this.vehicleType = vehicleType;
        this.description = description;
        this.licensplate = licensplate;
        this.odo = odo;
        this.additional = additional;
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
