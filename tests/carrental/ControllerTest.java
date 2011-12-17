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
        assertEquals(splitLog[splitLog.length - 1].trim(),testStr);
    }
}
