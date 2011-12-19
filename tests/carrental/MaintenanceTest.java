package carrental;

import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Maintenance class
 * @author CNN
 * @version 2011-12-17
 */
public class MaintenanceTest {
    private Maintenance maintenance;
    public MaintenanceTest() {
      maintenance = new Maintenance(1, 2, new Timestamp(22), new Timestamp(23), 3);
    }

    /**
     * Test of getID method, of class Maintenance.
     */
    @Test
    public void testGetID() {
        assertEquals(1, maintenance.getID());
        //also testing updating of this, to ensure it's not just 1 always.
        maintenance = new Maintenance(4, 2, new Timestamp(22), new Timestamp(23), 3);
        assertEquals(4, maintenance.getID());
    }
    
    /**
     * Test of getVehicleID method, of class Maintenance.
     */
    @Test
    public void testGetVehicleID() {
        assertEquals(2, maintenance.getVehicleID());
    }
    
    /**
     * Test of getTStart method, of class Maintenance.
     */
    @Test
    public void testGetTStart() {
        assertEquals(22, maintenance.getTStart().getTime());
    }

    /**
     * Test of getTEnd method, of class Maintenance.
     */
    @Test
    public void testGetTEnd() {
        assertEquals(23, maintenance.getTEnd().getTime());
    }

    /**
     * Test of getTypeID method, of class Maintenance.
     */
    @Test
    public void testGetTypeID() {
        assertEquals(3, maintenance.getTypeID());
    }

    /**
     * Test of updateObject method, of class Maintenance.
     */
    @Test
    public void testUpdateObject() {
        maintenance.updateObject(3, new Timestamp(32), new Timestamp(33), 4);
        //test that the maintenance's fields equals the new values.
        assertEquals(3, maintenance.getVehicleID());
        assertEquals(32, maintenance.getTStart().getTime());
        assertEquals(33, maintenance.getTEnd().getTime());
        assertEquals(4, maintenance.getTypeID());
    }
    
    /**
     * Test of end before start check
     */
    @Test
    public void testIncorrectTimestamps(){
        maintenance = new Maintenance(4, 2, new Timestamp(23), new Timestamp(22), 3);
        assertEquals(0, maintenance.getTStart().getTime());
        assertEquals(0, maintenance.getTEnd().getTime());
    }
}
