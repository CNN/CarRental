package carrental;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Vehicle holds information about a vehicle
 * @author CNN
 * @version 2011-11-30
 */
public class Vehicle {

    private final int ID;
    private int vehicleType, odo;
    private String description, licensePlate, additional, vin;

    /**
     * This constructor is used to initialize the fields needed for an instance of Vehicle
     * @param ID The ID number of this Vehicle
     * @param vehicleType The vehicle type of this Vehicle
     * @param description The description of this Vehicle
     * @param licensePlate The license plate of this Vehicle
     * @param vin The VIN number of this Vehicle
     * @param odo The ODO number of this Vehicle
     * @param additional The additional comment of this Vehicle
     */
    public Vehicle(int ID, int vehicleType, String description, String licensePlate, String vin, int odo, String additional) {
        this.ID = ID;
        this.vehicleType = vehicleType;
        this.description = description;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.odo = odo;
        this.additional = additional;
    }

    /**
     * This method updates this Vehicle object
     * @param vehicleType The updated vehicle type of this Vehicle
     * @param description The updated description of this Vehicle
     * @param licensePlate The updated license plate of this Vehicle
     * @param vin The updated VIN number of this Vehicle
     * @param odo The updated ODO number of this Vehicle
     * @param additional The updated additional comment of this Vehicle
     */
    public void updateObject(int vehicleType, String description, String licensePlate, String vin, int odo, String additional) {
        this.vehicleType = vehicleType;
        this.description = description;
        this.licensePlate = licensePlate;
        this.vin = vin;
        this.odo = odo;
        this.additional = additional;
    }

    /**
     * This method returns the ID number of this Vehicle
     * @return The ID number of this Vehicle
     */
    public int getID() {
        return ID;
    }

    /**
     * This method returns the vehicle type of this Vehicle
     * @return The vehicle type of this Vehicle
     */
    public int getVehicleType() {
        return vehicleType;
    }

    /**
     * This method returns the description of this Vehicle
     * @return The description of this Vehicle
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the license plate of this Vehicle
     * @return The license plate of this Vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * This method returns The VIN number of this Vehicle
     * @return The VIN number of this Vehicle
     */
    public String getVin() {
        return vin;
    }

    /**
     * This method returns the ODO number of this Vehicle
     * @return The ODO number of this Vehicle
     */
    public int getOdo() {
        return odo;
    }

    /**
     * This method returns the additional comment of this Vehicle
     * @return The additional comment of this Vehicle
     */
    public String getAdditional() {
        return additional;
    }
    
    public boolean isAvailible(Timestamp start, Timestamp end) {
        ArrayList<Booking> bs = CarRental.getInstance().requestBookingsByVehicle(ID);
        for(Booking b : bs) {
            if(!(start.before(b.getTStart()) && end.before(b.getTStart())) &&
                    !(start.after(b.getTEnd()) && end.after(b.getTEnd()))) 
                return false;
        }
        return true;
    }
}
