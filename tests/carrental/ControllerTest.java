package carrental;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests whether the controller works.
 * @author CNN
 * @version 2011-12-17
 */
public class ControllerTest {

    private CarRental controller;

    public ControllerTest() {
        controller = CarRental.getInstance();
    }

    /**
     * Tests the functionality of appendLog and getLog.
     */
    @Test
    public void testAppendLog() {
        String testStr = "AppendLog Test: test string";
        controller.appendLog(testStr);
        String[] splitLog = controller.getLog().split("\n");
        assertEquals(splitLog[splitLog.length - 1].trim(), testStr);
    }

    /**
     * Tests whether the requested object exists, and is infact a reservation
     */
    @Test
    public void testRequestReservation() {
        boolean success = false;
        if (controller.requestReservation(controller.requestNewReservationId()
                - 1) != null
                && controller.requestReservation(controller.
                requestNewReservationId() - 1) instanceof Reservation) {
            success = true;
        }
        assertEquals(success, true);
    }

    /**
     * Tests whether the requested object exists, and is infact a customer
     */
    @Test
    public void testRequestCustomer() {
        boolean success = false;
        if (controller.requestCustomer(controller.requestNewCustomerId() - 1)
                != null
                && controller.requestCustomer(controller.requestNewCustomerId()
                - 1) instanceof Customer) {
            success = true;
        }
        assertEquals(success, true);
    }

    /**
     * Tests whether the requested object exists, and is infact a vehicle
     */
    @Test
    public void testRequestVehicle() {
        boolean success = false;
        if (controller.requestVehicle(controller.requestNewVehicleId() - 1) 
                != null
                && controller.requestVehicle(controller.requestNewVehicleId()
                - 1) instanceof Vehicle) {
            success = true;
        }
        assertEquals(success, true);
    }

    /**
     * Tests whether the requested object exists, and is infact a vehicle type
     */
    @Test
    public void testRequestVehicleType() {
        boolean success = false;
        if (controller.requestVehicleType(controller.requestNewVehicleTypeId()
                - 1) != null
                && controller.requestVehicleType(controller.
                requestNewVehicleTypeId() - 1) instanceof VehicleType) {
            success = true;
        }
        assertEquals(success, true);
    }

    /**
     * Tests whether the requested object exists, and is infact a maintenance
     */
    @Test
    public void testRequestMaintenance() {
        boolean success = false;
        if (controller.requestMaintenance(controller.requestNewMaintenanceId()
                - 1) != null
                && controller.requestMaintenance(controller.
                requestNewMaintenanceId() - 1) instanceof Maintenance
                || controller.requestNewMaintenanceId() == 1 ) {
            success = true;
        }
        assertEquals(success, true);
    }

    /**
     * Tests whether the requested object exists, and is infact a 
     * maintenance type
     */
    @Test
    public void testRequestMaintenanceType() {
        boolean success = false;
        if (controller.requestMaintenanceType(controller.
                requestNewMaintenanceTypeId() - 1) != null
                && controller.requestMaintenanceType(controller.
                requestNewMaintenanceTypeId() - 1) instanceof MaintenanceType){
            success = true;
        }
        assertEquals(success, true);
    }
}
