/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CNN                                Include test for if the start date is after the end date?
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
        //also testing updating of this, to ensure it's not just 1 always. The other getters are tested this way through testUpdateObject()
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
}
