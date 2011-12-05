/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.sql.Timestamp;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niclas
 */
public class MaintenanceTest {
    
    public MaintenanceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of isBooked method, of class Maintenance.
     */
    @Test
    public void testIsBooked() {
        System.out.println("isBooked");
        Timestamp timestamp = null;
        Maintenance instance = null;
        boolean expResult = false;
        boolean result = instance.isBooked(timestamp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMaintenance method, of class Maintenance.
     */
    @Test
    public void testIsMaintenance() {
        System.out.println("isMaintenance");
        Maintenance instance = null;
        boolean expResult = false;
        boolean result = instance.isMaintenance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateObject method, of class Maintenance.
     */
    @Test
    public void testUpdateObject() {
        System.out.println("updateObject");
        int id = 0;
        int vehicle_id = 0;
        Timestamp date_start = null;
        Timestamp date_end = null;
        int type_id = 0;
        Maintenance instance = null;
        instance.updateObject(id, vehicle_id, date_start, date_end, type_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTEnd method, of class Maintenance.
     */
    @Test
    public void testGetTEnd() {
        System.out.println("getTEnd");
        Maintenance instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getTEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTStart method, of class Maintenance.
     */
    @Test
    public void testGetTStart() {
        System.out.println("getTStart");
        Maintenance instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getTStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Maintenance.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Maintenance instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVehicleID method, of class Maintenance.
     */
    @Test
    public void testGetVehicleID() {
        System.out.println("getVehicleID");
        Maintenance instance = null;
        int expResult = 0;
        int result = instance.getVehicleID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypeID method, of class Maintenance.
     */
    @Test
    public void testGetTypeID() {
        System.out.println("getTypeID");
        Maintenance instance = null;
        int expResult = 0;
        int result = instance.getTypeID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
