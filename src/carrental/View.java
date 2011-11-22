package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author hypesystem
 */
public class View {
    private JPanel overview_panel, vehicle_panel, vehicle_type_panel,
            customer_panel, reservation_panel;
    
    public View(Controller controller) {
        controller.appendLog("Creting View...");
        controller.appendLog("View created.");
        System.out.println(controller);
    }
    
}
