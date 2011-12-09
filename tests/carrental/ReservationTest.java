/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Reservation class
 * @author CNN
 * @version 2011-12-08
 */
public class ReservationTest {
  private Reservation reservation;
    public ReservationTest() {
      reservation = new Reservation(1, 2, new Timestamp(22), new Timestamp(23), 3);
    }

    /**
     * Test of getID method, of class Reservation.
     */
    @Test
    public void testGetID() {
        assertEquals(1, reservation.getID());
        //also testing updating of this, to ensure it's not just 1 always. The other getters are tested this way through testUpdateObject()
        reservation = new Reservation(4, 2, new Timestamp(22), new Timestamp(23), 3);
        assertEquals(4, reservation.getID());
    }
    
    /**
     * Test of getVehicleID method, of class Reservation.
     */
    @Test
    public void testGetVehicleID() {
        assertEquals(2, reservation.getVehicleID());
    }

    /**
     * Test of getTStart method, of class Reservation.
     */
    @Test
    public void testGetTStart() {
        assertEquals(22, reservation.getTStart().getTime());
    }

    /**
     * Test of getTEnd method, of class Reservation.
     */
    @Test
    public void testGetTEnd() {
        assertEquals(23, reservation.getTEnd().getTime());
    }

    /**
     * Test of getCustomerID method, of class Reservation.
     */
    @Test
    public void testGetCustomerID() {
        assertEquals(3, reservation.getCustomerID());
    }
    
    /**
     * Test of isBooked method
     */
    @Test
    public void testIsBooked(){
        assertEquals(true, reservation.isBooked(new Timestamp(23)));
    }

    /**
     * Test of updateObject method, of class Reservation.
     */
    @Test
    public void testUpdateObject() {
        reservation.updateObject(3, new Timestamp(32), new Timestamp(33), 4);
        //test that the reservation's fields equals the new values.
        assertEquals(3, reservation.getVehicleID());
        assertEquals(32, reservation.getTStart().getTime());
        assertEquals(33, reservation.getTEnd().getTime());
        assertEquals(4, reservation.getCustomerID());
    }
}
