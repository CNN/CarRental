/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.util.ArrayList;

/**
 * Interface for types of Updateable
 * Known classes to implement: Vehicle, VehicleType, Customer, Reservation, Maintenance
 * @author CNN
 * @version 30. Nov. 2011
 */
public interface Updateable {
    public void updateObject(ArrayList<String> update);
    public int getID();
}
