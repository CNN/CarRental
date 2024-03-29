package carrental;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Customer class
 * @author CNN
 * @version 2011-12-17
 */
public class CustomerTest {

    private Customer customer;

    public CustomerTest() {
        customer = new Customer(1, "24736456", "Poul Poulsen", "Enghavevej "
                + "13\n7420\nViborg", "p.poulsen@gmail.com");
    }

    /**
     * Test of getID method, of class Customer.
     */
    @Test
    public void testGetID() {
        assertEquals(1, customer.getID());
        //also testing updating of this, to ensure it's not just 1 always.
        customer = new Customer(4, "24736456", "Poul Poulsen", "Enghavevej "
                + "13\n7420\nViborg", "p.poulsen@gmail.com");
        assertEquals(4, customer.getID());
    }

    /**
     * Test of getTelephone method, of class Customer.
     */
    @Test
    public void testGetTelephone() {
        assertEquals("24736456", customer.getTelephone());
    }

    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        assertEquals("Poul Poulsen", customer.getName());
    }

    /**
     * Test of getAdress method, of class Customer.
     */
    @Test
    public void testGetAdress() {
        assertEquals("Enghavevej 13\n7420\nViborg", customer.getAdress());
    }
    
    /**
     * Test of getPostalCode method, of class Customer.
     */
    @Test
    public void testGetPostalCode() {
        assertEquals(7420, customer.getPostalCode());
    }

    /**
     * Test of getEMail method, of class Customer.
     */
    @Test
    public void testGetEMail() {
        assertEquals("p.poulsen@gmail.com", customer.getEMail());
    }
    
    /**
     * Test of updateObject method, of class Customer.
     */
    @Test
    public void testUpdateObject() {
        customer.updateObject("24736400", "Poul Erik Poulsen", "Enghavevej "
                + "15\n7500\nViborg", "p.e.poulsen@gmail.com");
        //test that the customer's fields equals the new values.
        assertEquals("24736400", customer.getTelephone());
        assertEquals("Poul Erik Poulsen", customer.getName());
        assertEquals("Enghavevej 15\n7500\nViborg", customer.getAdress());
        assertEquals(7500, customer.getPostalCode());
        assertEquals("p.e.poulsen@gmail.com", customer.getEMail());
    }
}
