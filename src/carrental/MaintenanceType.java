package carrental;

/**
 * MaintenanceType holds information about each maintenance type
 * @author CNN
 * @version 2011-12-04
 */
public class MaintenanceType {

    private final int ID;
    private String name;
    private boolean is_service;

    /**
     * This constructor is used to initialize the fields needed for an instance of MaintenanceType
     * @param ID  The ID number of the this MaintenanceType
     * @param name The name of this MaintenanceType
     * @param is_service A boolean for whether or not this is a service maintenance. Use 'true' if it is
     */
    public MaintenanceType(int ID, String name, boolean is_service) {
        this.ID = ID;
        this.name = name;
        this.is_service = is_service;
    }

    /**
     * This method updates the MaintenanceType with the supplied parameters
     * @param name The name of the updated MaintenanceType
     * @param is_service A boolean for whether or not the updated object is a service maintenance. Use 'true' if it is
     */
    public void updateObject(String name, boolean is_service) {
        this.name = name;
        this.is_service = is_service;
    }

    /**
     * This method states if this MaintenanceType is a service maintenance. Returns'true' if it is
     * @return A boolean for wheter or not this MaintenanceType is a service maintenance. Returns'true' if it is
     */
    public boolean getIs_service() {
        return is_service;
    }

    /**
     * This method returns the name of this MaintenanceType
     * @return The name of this MaintenanceType
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method returns the ID number of this MaintenanceType
     * @return The ID number of this MaintenanceType
     */
    public int getID() {
        return ID;
    }
}
