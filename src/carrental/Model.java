package carrental;

import java.util.ArrayList;

/**
 * The model keeps track of the logic of CarRental
 * @author CNN
 * @version 2011-12-02
 */
public class Model {
    private DbCom database;
    
    public Model() {
        CarRental.getInstance().appendLog("Creating model...");
        database = new DbCom();
    }
    
    //VEHICLE
    
    /**
     * Get a vehicle matching an id
     * @param id
     * @return Vehicle
     */
    public Vehicle getVehicle(int id) {
        ArrayList<String> v = database.getFirstMatch("SELECT * FROM vehicle WHERE id = '"+id+"'");
        return new Vehicle(Integer.parseInt(v.get(0)),Integer.parseInt(v.get(1)),v.get(2),v.get(3),Integer.parseInt(v.get(4)),v.get(5));
    }
    
    /**
     * Get an array of all vehicles in the database
     * @return Array of vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        ArrayList<ArrayList<String>> vs = database.getMatches("SELECT * FROM vehicle ORDER BY type,id DESC");
        ArrayList<Vehicle> results = new ArrayList<>();
        for(ArrayList<String> v : vs) {
            results.add(new Vehicle(Integer.parseInt(v.get(0)),Integer.parseInt(v.get(1)),v.get(2),v.get(3),Integer.parseInt(v.get(4)),v.get(5)));
        }
        return results;
    }
    
    /**
     * Save a vehicle to the database
     * @param v the vehicle to save
     */
    public void saveVehicle(Vehicle v) {
        ArrayList<String> save_data = new ArrayList<>();
        save_data.add(Integer.toString(v.getID()));
        save_data.add(Integer.toString(v.getVehicleType()));
        save_data.add(v.getDescription());
        save_data.add(v.getLicensplate());
        save_data.add(Integer.toString(v.getOdo()));
        save_data.add(v.getAdditional());
        database.saveArray("vehicle", save_data);
    }
    
    //VEHICLE TYPE
    
    /**
     * Get a vehicle type by it's id
     * @param id
     * @return VehicleType
     */
    public VehicleType getVehicleType(int id) {
        ArrayList<String> vt = database.getFirstMatch("SELECT * FROM vehicletype WHERE id = '"+id+"'");
        return new VehicleType(Integer.parseInt(vt.get(0)),vt.get(1),vt.get(2),Integer.parseInt(vt.get(3)));
    }
    
    /**
     * Get a vehicle type by it's name
     * @param id
     * @return VehicleType
     */
    public VehicleType getVehicleType(String name) {
        ArrayList<String> vt = database.getFirstMatch("SELECT * FROM vehicletype WHERE name = '"+name+"'");
        return new VehicleType(Integer.parseInt(vt.get(0)),vt.get(1),vt.get(2),Integer.parseInt(vt.get(3)));
    }
    
    /**
     * Get an array of all vehicle types in the database
     * @return Array of vehicletypes
     */
    public ArrayList<VehicleType> getVehicleTypes() {
        ArrayList<ArrayList<String>> vts = database.getMatches("SELECT * FROM vehicletype ORDER BY id DESC");
        ArrayList<VehicleType> results = new ArrayList<>();
        for(ArrayList<String> vt : vts) {
            results.add(new VehicleType(Integer.parseInt(vt.get(0)),vt.get(1),vt.get(2),Integer.parseInt(vt.get(3))));
        }
        return results;
    }
    
    /**
     * Save a vehicletype to the database
     * @param vt the vehicletype to save
     */
    public void saveVehicleType(VehicleType vt) {
        ArrayList<String> save_data = new ArrayList<>();
        save_data.add(Integer.toString(vt.getID()));
        save_data.add(vt.getName());
        save_data.add(vt.getDescription());
        save_data.add(Integer.toString(vt.getPricePerDay()));
        database.saveArray("vehicle", save_data);
    }
}