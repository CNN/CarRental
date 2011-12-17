package carrental;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

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
    
    //TODO: Repeat above 2 tests for all other simple classes there are.
        
    @Test
    public void testGetBookingsByVehicle() {
        ArrayList<Booking> bs = model.getBookingsByVehicleId(model.getHighestVehicleId());
        for(Booking b : bs) {
            assertEquals(b.getVehicleID(), model.getHighestVehicleId());
        }
    }
}
