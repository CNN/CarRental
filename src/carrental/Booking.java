package carrental;

import java.sql.Timestamp;

/**
 * This is an interface for types of a Booking
 * @author CNN
 * @version 2011-11-30
 */
public interface Booking {

    /**
     * This method returns the ID number of this Booking
     * @return The ID number of this Booking
     */
    public int getID();

    /**
     * This method returns the Vehicle ID number of this Booking
     * @return The Vehicle ID number of this Booking
     */
    public int getVehicleID();

    /**
     * This method returns the starting time of this Booking as a 
     * Timestamp object
     * @return The starting time of this Booking as a Timestamp object
     */
    public Timestamp getTStart();

    /**
     * This method returns the ending time of this Booking as a 
     * Timestamp object
     * @return The ending time of this Booking as a Timestamp object
     */
    public Timestamp getTEnd();

    /**
     * This method should state if this Booking is a regarded as a 
     * maintenance in the system
     * @return Returns 'true' if this Booking is a maintenance. 
     * Returns 'false' if not
     */
    public boolean isMaintenance();
}
