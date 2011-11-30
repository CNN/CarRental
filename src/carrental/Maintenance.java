package carrental;

import java.sql.Timestamp;

/**
 *
 * @author CNN
 */
public class Maintenance implements Booking {
    private int id;
    private int vehicle_id;
    private Timestamp date_start;
    private Timestamp date_end;
    private int type_id;
    
    public Maintenance(int id, int vehicle_id, Timestamp date_start, Timestamp date_end, int type_id) {
        if(date_end.after(date_start)) {
            this.date_start = date_start;
            this.date_end = date_end;
            this.type_id = type_id;
            this.id = id;
            this.vehicle_id = vehicle_id;
        }
        else CarRental.getInstance().appendLog("Dates supplied for Maintenance #"+id+" in constuctor not valid. End date must be AFTER start date.");
    }
    
    public void updateObject(int id, int vehicle_id, Timestamp date_start, Timestamp date_end, int type_id) {
        if(date_end.after(date_start)) {
            this.date_start = date_start;
            this.date_end = date_end;
            this.type_id = type_id;
            this.vehicle_id = vehicle_id;
        }
        else CarRental.getInstance().appendLog("Dates supplied for Maintenance #"+id+" in updateObject not valid. End date must be AFTER start date.");
    }
    
    public Timestamp getTEnd() {
        return date_end;
    }
    
    public Timestamp getTStart() {
        return date_start;
    }
    
    public int getID() {
        return id;
    }
    
    public int getVehicleID() {
        return vehicle_id;
    }
    
}
