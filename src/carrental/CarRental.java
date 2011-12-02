package carrental;

import java.awt.event.*;

/**
 *
 */
public final class CarRental {
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
    }
    
    public static CarRental getInstance() {
        //should never happen that there is no instance, but just in case
        if(instance == null) return new CarRental();
        return instance;
    }
    
    public void mouseEvent(MouseEvent e) {
        //do mouse event
        System.out.println("MOUSEEVENT!");
    }
    
    public void actionEvent(ActionEvent e) {
        //do action event
        System.out.println("ACTIONEVENT!");
    }
    
    public void appendLog(String string) {
        if(CarRental.DEBUG) System.out.println(string);
        log = log+"\n"+string;
    }
    public void appendLog(String string, Exception e) {
        if(CarRental.DEBUG) System.out.println(e+": "+string);
        log = log+"\n"+string+": "+e;
    }
    
    private void printLog() {
        System.out.println(log);
    }
    
    
    public static void main(String[] args) {
        //CarRental controller = new CarRental();
        new DbCom();
    }
    
}
