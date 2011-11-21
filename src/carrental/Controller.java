package carrental;

import java.awt.event.*;

/**
 *
 * @author hypesystem
 */
public interface Controller {
    public void mouseEvent(MouseEvent e);
    public void actionEvent(ActionEvent e);
    public void appendLog(String string);
}
