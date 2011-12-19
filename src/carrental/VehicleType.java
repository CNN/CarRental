package carrental;

/**
 * VehicleType holds information about a vehicle type
 * @author CNN
 * @version 2011-11-30
 */
public class VehicleType {

    private final int ID;
    private String name;
    private String description;
    private int pricePerDay;

    /**
     * This constructor is used to initialize the fields needed for an 
     * instance of VehicleType
     * @param ID The ID number of this VehicleTypeType
     * @param name The name of this VehicleType
     * @param description The description of this VehicleType
     * @param pricePerDay The approximate price per day of this VehicleType
     */
    public VehicleType(int ID, String name, String description, 
            int pricePerDay) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    /**
     * This method updates this VehicleType object
     * @param name The updated name of this VehicleType
     * @param description The updated description of this VehicleType
     * @param pricePerDay The updated approximate price per day of this 
     * VehicleType
     */
    public void updateObject(String name, String description, int pricePerDay) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    /**
     * This method returns the ID number of this VehicleType
     * @return The ID number of this VehicleType
     */
    public int getID() {
        return ID;
    }

    /**
     * This method returns the name of this VehicleType
     * @return The name of this VehicleType
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the description of this VehicleType
     * @return The description of this VehicleType
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the approximate price per day of this VehicleType
     * @return The approximate price per day of this VehicleType
     */
    public int getPricePerDay() {
        return pricePerDay;
    }
}
