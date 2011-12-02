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
     * Get the first vehicle matching the name provided
     * @param name
     * @return Vehicle
     */
    public Vehicle getVehicle(String name) {
        ArrayList<String> v = database.getFirstMatch("SELECT * FROM vehicle WHERE name = '"+name+"'");
        return new Vehicle(Integer.parseInt(v.get(0)),Integer.parseInt(v.get(1)),v.get(2),v.get(3),Integer.parseInt(v.get(4)),v.get(5));
    }
    
    /**
     * Get an array of all vehicles in the database
     * @return Array of vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        ArrayList<ArrayList<String>> vs = database.getMatches("SELECT * FROM vehicle ORDER BY type DESC");
        ArrayList<Vehicle> results = new ArrayList<>();
        for(ArrayList<String> v : vs) {
            results.add(new Vehicle(Integer.parseInt(v.get(0)),Integer.parseInt(v.get(1)),v.get(2),v.get(3),Integer.parseInt(v.get(4)),v.get(5)));
        }
        return results;
    }
}
