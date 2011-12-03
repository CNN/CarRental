package carrental;

import java.sql.Timestamp;

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
    public Timestamp getTStart();
    public Timestamp getTEnd();
    public boolean isMaintenance();
}
