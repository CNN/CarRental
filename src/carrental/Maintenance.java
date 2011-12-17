package carrental;

import java.sql.Timestamp;

/**
 * Maintenance holds information about a maintenance
 * @author CNN
 * @version 2011-11-30
 */
public class Maintenance implements Booking {

    private int id;
    private int vehicle_id;
    private Timestamp date_start;
    private Timestamp date_end;
    private int type_id;
    private boolean isMaintenance;

    /**
     * This constructor is used to initialize the fields needed for an instance of Maintenance
     * @param id The ID number of this Maintenance
     * @param vehicle_id The vehicle ID number of this Maintenance
     * @param date_start The start time of this Maintenance
     * @param date_end id The end time of this Maintenance
     * @param type_id id The type ID number of this Maintenance
     */
    public Maintenance(int id, int vehicle_id, Timestamp date_start, Timestamp date_end, int type_id) {
        if (date_end != null && date_start != null && date_end.after(date_start)) {
            this.date_start = date_start;
            this.date_end = date_end;
        } else {
            CarRental.getInstance().appendLog("Dates supplied for Maintenance #" + id + " in constuctor not valid. End date must be AFTER start date.");
            this.date_start = new Timestamp(0);
            this.date_end = new Timestamp(0);
        }
        this.type_id = type_id;
        this.id = id;
        this.vehicle_id = vehicle_id;
        isMaintenance = true;
    }

    /**
     * This method updates this Maintenance with the supplied parameters
     * @param vehicle_id The updated vehicle ID number of this Maintenance
     * @param date_start The updated start time of this Maintenance
     * @param date_end id The updated end time of this Maintenance
     * @param type_id id The updated type ID number of this Maintenance
     */
    public void updateObject(int vehicle_id, Timestamp date_start, Timestamp date_end, int type_id) {
        if (date_end.after(date_start)) {
            this.date_start = date_start;
            this.date_end = date_end;
            this.type_id = type_id;
            this.vehicle_id = vehicle_id;
        } else {
            CarRental.getInstance().appendLog("Dates supplied for Maintenance #" + id + " in updateObject not valid. End date must be AFTER start date.");
        }

    }

    /**
     * This method returns the type ID number of this Maintenance
     * @return The type ID number of this Maintenance
     */
    public int getTypeID() {
        return type_id;
    }

    public boolean isMaintenance() {
        return isMaintenance;
    }

    public int getID() {
        return id;
    }

    public int getVehicleID() {
        return vehicle_id;
    }

    public Timestamp getTStart() {
        return date_start;
    }

    public Timestamp getTEnd() {
        return date_end;
    }
}
