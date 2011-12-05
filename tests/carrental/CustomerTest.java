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
public class CustomerTest {
    
    public CustomerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of updateObject method, of class Customer.
     */
    @Test
    public void testUpdateObject() {
        System.out.println("updateObject");
        int telephone = 0;
        String name = "";
        String adress = "";
        String eMail = "";
        Customer instance = null;
        instance.updateObject(telephone, name, adress, eMail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Customer.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Customer instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelephone method, of class Customer.
     */
    @Test
    public void testGetTelephone() {
        System.out.println("getTelephone");
        Customer instance = null;
        int expResult = 0;
        int result = instance.getTelephone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Customer instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdress method, of class Customer.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");
        Customer instance = null;
        String expResult = "";
        String result = instance.getAdress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostalCode method, of class Customer.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        Customer instance = null;
        int expResult = 0;
        int result = instance.getPostalCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEMail method, of class Customer.
     */
    @Test
    public void testGetEMail() {
        System.out.println("getEMail");
        Customer instance = null;
        String expResult = "";
        String result = instance.getEMail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
