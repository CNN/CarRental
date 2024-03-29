package carrental;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for MaintenanceType class
 * @author CNN
 * @verstion 2011-12-17
 */
public class MaintenanceTypeTest {

    private MaintenanceType maintenanceType;

    public MaintenanceTypeTest() {
        maintenanceType = new MaintenanceType(1, "Oil check", true);
    }

    /**
     * Test of getID method, of class MaintenanceType.
     */
    @Test
    public void testGetID() {
        assertEquals(1, maintenanceType.getID());
        //also testing updating of this, to ensure it's not just 1 always.
        maintenanceType = new MaintenanceType(4, "Oil check", true);
        assertEquals(4, maintenanceType.getID());
    }

    /**
     * Test of getName method, of class MaintenanceType.
     */
    @Test
    public void testGetName() {
        assertEquals("Oil check", maintenanceType.getName());
    }

    /**
     * Test of getIs_service method, of class MaintenanceType.
     */
    @Test
    public void testGetIs_service() {
        assertEquals(true, maintenanceType.getIs_service());
    }

    /**
     * Test of updateObject method, of class MaintenanceType.
     */
    @Test
    public void testUpdateObject() {
        maintenanceType.updateObject("Motor check", false);
        //test that the vehicle type's fields equals the new values.
        assertEquals("Motor check", maintenanceType.getName());
        assertEquals(false, maintenanceType.getIs_service());
    }
}
