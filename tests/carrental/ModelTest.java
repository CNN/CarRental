package carrental;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

/**
 * This is a test of the functionality in the model.
 * @author CNN
 * @version 2011-12-17
 */
public class ModelTest {
    private Model model;
    
    public ModelTest() {
        model = new Model();
    }
    
    /**
     * Tests if the model can succesfully save, get and delete objects from the
     * database.
     */
    @Test
    public void testSaveGetDeleteVehicle() {
        Vehicle vTest = new Vehicle(714,1,"test car","xq12891","1091LKi1USL119s",35,"Flat tire front left");
        model.saveVehicle(vTest);
        Vehicle vModel = model.getVehicle(vTest.getID());
        
        assertEquals(vTest.getID(), vModel.getID());
        assertEquals(vTest.getVehicleType(), vModel.getVehicleType());
        assertEquals(vTest.getDescription(), vModel.getDescription());
        assertEquals(vTest.getLicensePlate(), vModel.getLicensePlate());
        assertEquals(vTest.getVin(), vModel.getVin());
        assertEquals(vTest.getOdo(), vModel.getOdo());
        assertEquals(vTest.getAdditional(), vModel.getAdditional());
        
        model.deleteVehicle(vTest.getID());
        assertEquals(model.getVehicle(vTest.getID()),null);
    }
    
    /**
     * Test whether the model updates an entry on save instead of inserting as new,
     * if it already exists in the database.
     */
    @Test
    public void testUpdateOnExistsVehicle() {
        Vehicle vTest = new Vehicle(714,1,"test car","xq12891","1091LKi1USL119s",35,"Flat tire and broken windshield");
        
        model.saveVehicle(vTest);
        vTest.updateObject(2, vTest.getDescription(), vTest.getLicensePlate(), vTest.getVin(), vTest.getOdo() + 5, "Fixed!");
        model.saveVehicle(vTest);
        
        Vehicle vModel = model.getVehicle(vTest.getID());
        
        assertEquals(vTest.getID(), vModel.getID());
        assertEquals(vTest.getVehicleType(), vModel.getVehicleType());
        assertEquals(vTest.getDescription(), vModel.getDescription());
        assertEquals(vTest.getLicensePlate(), vModel.getLicensePlate());
        assertEquals(vTest.getVin(), vModel.getVin());
        assertEquals(vTest.getOdo(), vModel.getOdo());
        assertEquals(vTest.getAdditional(), vModel.getAdditional());
    }
    
    /**
     * Tests whether the save get and delete methods for maintenances work
     * properly
     */
    @Test
    public void testSaveGetDeleteMaintenance() {
        long now_time = Calendar.getInstance().getTimeInMillis();
        Maintenance mTest = new Maintenance(222, 1,
                new Timestamp(now_time - (now_time % 86400)),
                new Timestamp(now_time - (now_time % 86400) + 86400),1);
        model.saveMaintenance(mTest);
        Maintenance mModel = model.getMaintenance(mTest.getID());
        
        //TODO: Notice the insecurity that arises here, as timestamps in database
        // are saved without millis.
        assertEquals(mTest.getID(),mModel.getID());
        assertEquals(mTest.getVehicleID(),mModel.getVehicleID());
        assertEquals(mTest.getTypeID(),mModel.getTypeID());
        //assertEquals(mTest.getTEnd(),mModel.getTEnd());
        //assertEquals(mTest.getTStart(),mModel.getTStart());
        
        model.deleteMaintenance(mTest.getID());
        assertEquals(model.getMaintenance(mTest.getID()),null);
    }
    
    @Test
    public void testUpdateOnExistsMaintenance() {
        long now_time = Calendar.getInstance().getTimeInMillis();
        Maintenance mTest = new Maintenance(222, 1,
                new Timestamp(now_time - (now_time % 86400)),
                new Timestamp(now_time - (now_time % 86400) + 86400),1);
        model.saveMaintenance(mTest);
        mTest.updateObject(1, new Timestamp(now_time),
                new Timestamp(now_time - (now_time % 86400) + 86400), 1);
        model.saveMaintenance(mTest);
        Maintenance mModel = model.getMaintenance(mTest.getID());
        
        assertEquals(mTest.getID(),mModel.getID());
        assertEquals(mTest.getVehicleID(),mModel.getVehicleID());
        assertEquals(mTest.getTypeID(),mModel.getTypeID());
        //assertEquals(mTest.getTEnd(),mModel.getTEnd());
        //assertEquals(mTest.getTStart(),mModel.getTStart());
    }
    
    /**
     * Tests that the save, get and delete functionality for the customers works
     * properly in the model.
     */
    @Test
    public void testSaveGetDeleteCustomer() {
        Customer cTest = new Customer(117,"12121212","John Stewart", "45th Street 20111\n2191911 Washington","johnstwart@comedycentral.com");
        model.saveCustomer(cTest);
        Customer cModel = model.getCustomer(cTest.getID());
        
        assertEquals(cTest.getID(),cModel.getID());
        assertEquals(cTest.getTelephone(),cModel.getTelephone());
        assertEquals(cTest.getName(),cModel.getName());
        assertEquals(cTest.getAdress(),cModel.getAdress());
        assertEquals(cTest.getEMail(),cModel.getEMail());
    }
    
    /**
     * Test that customer updates when saved if an entry with the same id already exists.
     */
    @Test
    public void testUpdateOnExistsCustomer() {
        Customer cTest = new Customer(117,"12121212","John Stewart", "45th Street 20111\n2191911 Washington","johnstwart@comedycentral.com");
        model.saveCustomer(cTest);
        cTest.updateObject("12122111", "John Stewart", "2 Londong St.\n1212 HelloKitty", "jst@cc.com");
        model.saveCustomer(cTest);
        
        Customer cModel = model.getCustomer(cTest.getID());
        
        assertEquals(cTest.getID(),cModel.getID());
        assertEquals(cTest.getTelephone(),cModel.getTelephone());
        assertEquals(cTest.getName(),cModel.getName());
        assertEquals(cTest.getAdress(),cModel.getAdress());
        assertEquals(cTest.getEMail(),cModel.getEMail());
    }
    
    //TODO: Repeat above 2 tests for all other simple classes there are.
    //Missing: VehicleType, MaintenanceType, Reservation
    
    /**
     * Tests whether the bookings gotten that are supposed to belong to a vehicle
     * actually belong to this vehicle
     */
    @Test
    public void testGetBookingsByVehicle() {
        ArrayList<Vehicle> vs = model.getVehicles();
        for(Vehicle v : vs) {
            ArrayList<Booking> bs = model.getBookingsByVehicleId(v.getID());
            for(Booking b : bs) {
                assertEquals(b.getVehicleID(), v.getID());
            }
        }
    }
}
