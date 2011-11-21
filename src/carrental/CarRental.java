package carrental;

import java.awt.event.*;

/**
 *
 * @author hypesystem
 */
public class CarRental implements Controller {
    private Model model;
    private View view;
    private String log;
    
    public CarRental() {
        model = new Model();
        view = new View(this);
    }
    
    @Override
    public void mouseEvent(MouseEvent e) {
        
    }
    
    @Override
    public void actionEvent(ActionEvent e) {
        System.out.println("ACTIONEVENT!");
    }
    
    @Override
    public void appendLog(String string) {
        log = log+"\n"+string;
    }
    
    
    public static void main(String[] args) {
        CarRental controller = new CarRental();
    }
    
}
