package carrental;

/**
 * Interface for types of booking
 * Known classes to implement: Reservation, Maintenance
 * @author CNN
 * @version 30. Nov. 2011
 */
public interface Booking {
    //id, vid, start, end
    public int getID();
    public int getVehicleID();
    public long getTStart();
    public long getTEnd();
}
