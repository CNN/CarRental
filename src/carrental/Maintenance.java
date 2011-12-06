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
    private boolean isMaintenance;
    
    public Maintenance(int id, int vehicle_id, Timestamp date_start, Timestamp date_end, int type_id) {
        if(date_end != null && date_start != null && date_end.after(date_start)) {
            this.date_start = date_start;
            this.date_end = date_end;
        }
        else {
            CarRental.getInstance().appendLog("Dates supplied for Maintenance #"+id+" in constuctor not valid. End date must be AFTER start date.");
            date_start = new Timestamp(0);
            date_end = new Timestamp(0);
        }
        this.type_id = type_id;
        this.id = id;
        this.vehicle_id = vehicle_id;
        isMaintenance = true;
    }
    
    public boolean isBooked(Timestamp timestamp){
        return (timestamp.after(date_start) && timestamp.before(date_end));
    }
    
    public boolean isMaintenance(){
        return isMaintenance;
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
    
    public int getTypeID() {
        return type_id;
    }
}
