/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niclas
 */
public class VehicleTest {

    private static Vehicle vehicle;

    public VehicleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        vehicle = new Vehicle(1, 2, "This is a description", "This is the licenseplate", 20000, "Additional comment");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of updateObject method, of class Vehicle.
     */
    @Test
    public void testUpdateObject() {
        assertEquals(2, vehicle.getVehicleType());
        vehicle.updateObject(3, "This is a NEW description", "This is the NEW licenseplate", 10000, "NEW Additional comment");
        //test that the vehicle's fields equals the new values.
        assertEquals(3, vehicle.getVehicleType());
        assertEquals("This is a NEW description", vehicle.getDescription());
        assertEquals("This is the NEW licenseplate", vehicle.getLicensplate());
        assertEquals(10000, vehicle.getOdo());
        assertEquals("NEW Additional comment", vehicle.getAdditional());
    }

    /**
     * Test of getID method, of class Vehicle.
     */
    @Test
    public void testGetID() {
        assertEquals(1, vehicle.getID());
        vehicle = new Vehicle(4, 2, "This is a description", "This is the licenseplate", 42, "Additional comment");
        assertEquals(4, vehicle.getID());
    }
    //TODO Do you agree that the below is pretty unnecessary, due to the testUpdateObject() method above? :)
//
//    /**
//     * Test of getVehicleType method, of class Vehicle.
//     */
//    @Test
//    public void testGetVehicleType() {
//        System.out.println("getVehicleType");
//        Vehicle instance = null;
//        int expResult = 0;
//        int result = instance.getVehicleType();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDescription method, of class Vehicle.
//     */
//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        Vehicle instance = null;
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLicensplate method, of class Vehicle.
//     */
//    @Test
//    public void testGetLicensplate() {
//        System.out.println("getLicensplate");
//        Vehicle instance = null;
//        String expResult = "";
//        String result = instance.getLicensplate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOdo method, of class Vehicle.
//     */
//    @Test
//    public void testGetOdo() {
//        System.out.println("getOdo");
//        Vehicle instance = null;
//        int expResult = 0;
//        int result = instance.getOdo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAdditional method, of class Vehicle.
//     */
//    @Test
//    public void testGetAdditional() {
//        System.out.println("getAdditional");
//        Vehicle instance = null;
//        String expResult = "";
//        String result = instance.getAdditional();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
