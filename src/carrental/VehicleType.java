/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

/**
 * VehicleType holds information on each vehicle type
 * @author CNN
 * @version 30 Nov. 2011
 */
public class VehicleType{
    private final int ID;
    private String name;
    private String description;
    private int pricePerDay;
    
    /**
     * Constructor for VehicleType
     * @param ID
     * @param name
     * @param description
     * @param pricePerDay 
     */
    public VehicleType(int ID, String name, String description, int pricePerDay){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }
    
    /**
     * Update object information
     * @param name
     * @param description
     * @param pricePerDay 
     */
    public void updateObject(String name, String description, int pricePerDay){
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }
    
    /**
     * 
     * @return ID
     */
    public int getID(){
        return ID;
    }
    /**
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return description
     */
    public String getDescription(){
        return description;
    }
    /**
     * 
     * @return pricePerDay
     */
    public int getPricePerDay(){
        return pricePerDay;
    }
}
