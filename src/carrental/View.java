package carrental;

/**
 *
 * @author hypesystem
 */
public class View {
    
    public View(Controller controller) {
        controller.appendLog("Creting View...");
        controller.appendLog("View created.");
        System.out.println(controller);
    }
    
}
