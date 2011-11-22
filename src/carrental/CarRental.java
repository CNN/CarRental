package carrental;

import java.awt.event.*;

/**
 *
 * @author hypesystem
 */
public final class CarRental implements Controller {
    public static final boolean DEBUG = true;
    private static CarRental instance;
    private Model model;
    private View view;
    private String log;
    
    private CarRental() {
        //next line is ugly
        if(instance == null) instance = this;
        appendLog("Initializing...");
        model = new Model();
        view = new View(this);
        printLog();
    }
    
    public static CarRental getInstance() {
        //should never happen that there is no instance, but just in case
        if(instance == null) new CarRental();
        return instance;
    }
    
    @Override
    public void mouseEvent(MouseEvent e) {
        //do mouse event
        System.out.println("MOUSEEVENT!");
    }
    
    @Override
    public void actionEvent(ActionEvent e) {
        //do action event
        System.out.println("ACTIONEVENT!");
    }
    
    @Override
    public void appendLog(String string) {
        if(CarRental.DEBUG) System.out.println(string);
        log = log+"\n"+string;
    }
    
    private void printLog() {
        System.out.println(log);
    }
    
    
    public static void main(String[] args) {
        CarRental controller = new CarRental();
    }
    
}
