package carrental;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Vehicle class
 * @author CNN
 * @version 2011-12-08
 */
public class VehicleTest {

    private Vehicle vehicle;  //Should these be static? maybe it doesn't matter??

    public VehicleTest() {
        vehicle = new Vehicle(1, 2, "This is a description", "This is the license plate", "YV1740", 20000, "Additional comment");
    }

    /**
     * Test of getID method, of class Vehicle.
     */
    @Test
    public void testGetID() {
        assertEquals(1, vehicle.getID());
        //also testing updating of this, to ensure it's not just 1 always. The other getters are tested this way through testUpdateObject()
        vehicle = new Vehicle(4, 2, "This is a description", "This is the license plate", "YV1740", 20000, "Additional comment");
        assertEquals(4, vehicle.getID());
    }

    /**
     * Test of getVehicleType method, of class Vehicle.
     */
    @Test
    public void testGetVehicleType() {
        assertEquals(2, vehicle.getVehicleType());
    }

    /**
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        assertEquals("This is a description", vehicle.getDescription());
    }

    /**
     * Test of getLicensplate method, of class Vehicle.
     */
    @Test
    public void testGetLicensplate() {
        assertEquals("This is the license plate", vehicle.getLicensePlate());
    }

    /**
     * Test of getVIN method, of class Vehicle.
     */
    @Test
    public void testGetVIN() {
        assertEquals("YV1740", vehicle.getVin());
    }
    
    /**
     * Test of getOdo method, of class Vehicle.
     */
    @Test
    public void testGetOdo() {
        assertEquals(20000, vehicle.getOdo());
    }
    
    /**
     * Test of getAdditional method, of class Vehicle.
     */
    @Test
    public void testGetAdditional() {
        assertEquals("Additional comment", vehicle.getAdditional());
    }

    /**
     * Test of updateObject method, of class Vehicle.
     */
    @Test
    public void testUpdateObject() {
        vehicle.updateObject(3, "This is a NEW description", "This is the NEW license plate", "XH8263", 10000, "NEW Additional comment");
        //test that the vehicle's fields equals the new values.
        assertEquals(3, vehicle.getVehicleType());
        assertEquals("This is a NEW description", vehicle.getDescription());
        assertEquals("This is the NEW license plate", vehicle.getLicensePlate());
        assertEquals("XH8263", vehicle.getVin());
        assertEquals(10000, vehicle.getOdo());
        assertEquals("NEW Additional comment", vehicle.getAdditional());
    }
}
