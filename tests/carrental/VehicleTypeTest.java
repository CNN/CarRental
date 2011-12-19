package carrental;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for VehicleType class
 * @author CNN
 * @version 2011-12-08
 */
public class VehicleTypeTest {

    private VehicleType vehicleType;

    public VehicleTypeTest() {
        vehicleType = new VehicleType(1, "Stationcar", "This is a description", 300);
    }

    /**
     * Test of getID method, of class VehicleType.
     */
    @Test
    public void testGetID() {
        assertEquals(1, vehicleType.getID());
        //also testing updating of this, to ensure it's not just 1 always.
        vehicleType = new VehicleType(4, "Stationcar", "This is a description", 300);
        assertEquals(4, vehicleType.getID());
    }

    /**
     * Test of getName method, of class VehicleType.
     */
    @Test
    public void testGetName() {
        assertEquals("Stationcar", vehicleType.getName());
    }

    /**
     * Test of getDescription method, of class VehicleType.
     */
    @Test
    public void testGetDescription() {
        assertEquals("This is a description", vehicleType.getDescription());
    }

    /**
     * Test of getPricePerDay method, of class VehicleType.
     */
    @Test
    public void testGetPricePerDay() {
        assertEquals(300, vehicleType.getPricePerDay());
    }

    /**
     * Test of updateObject method, of class VehicleType.
     */
    @Test
    public void testUpdateObject() {
        vehicleType.updateObject("Truck", "This is a NEW description", 600);
        //test that the vehicle type's fields equals the new values.
        assertEquals("Truck", vehicleType.getName());
        assertEquals("This is a NEW description", vehicleType.getDescription());
        assertEquals(600, vehicleType.getPricePerDay());
    }
}
