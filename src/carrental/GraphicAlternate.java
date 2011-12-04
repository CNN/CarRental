package carrental;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.sql.Timestamp;

/**
 * @(#)GraphicAlternate.java
 *
 *
 * @author
 * @version 1.00 2011/12/4
 */


public class GraphicAlternate extends JComponent{
	private int width, height, collumnWidth, rowHeight, numberOfCollumns, numberOfRows;
    private Canvas canvas;
    private ArrayList<Booking> bookings;
    private ArrayList<Timestamp> timestamps;

    public GraphicAlternate(ArrayList<Booking> bookings, ArrayList<Timestamp> timestamps) {
    	canvas = new Canvas();
    	this.bookings = bookings;
    	this.timestamps = timestamps;

    	width = 800;
    	height = 600;

    	setNumberOfCollumns();
    	setNumberOfRows();
    	setCollumnWidth();
    	setRowHeight();

    	canvas.repaint();
    }

    public static void main(String[] args){
    	Booking b1 = new Reservation(1, 2, new Timestamp(20000000), new Timestamp(200000000), 123);
        ArrayList bs = new ArrayList();
        bs.add(b1);

        Timestamp t1 = new Timestamp(20000000);
        ArrayList ts = new ArrayList();
        ts.add(t1);

        JFrame frame = new JFrame();
        frame.add(new GraphicAlternate(bs, ts));
        frame.pack();
        frame.setVisible(true);
    }

    private Date toDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }

    private void setNumberOfCollumns(){
    	numberOfCollumns = timestamps.size();
    }

	private void setNumberOfRows(){
		numberOfRows = bookings.size();
	}

	private void setCollumnWidth(){
		collumnWidth = width/numberOfCollumns;
	}

	private void setRowHeight(){
		rowHeight = height/numberOfRows;
	}

    public void paint(Graphics g){
    	int x = 0;
    	while(x < width){
    		int y = 0;
    		while(y < height){
    			if(bookings.get(y).isBooked(timestamps.get(x))){
    				if(bookings.get(y).isMaintenance()){
    					g.setColor(Color.yellow);
    				} else{
    					g.setColor(Color.blue);
    				}
    				g.fillRect(x, y, collumnWidth, rowHeight);
    			}
    			y += height/numberOfRows;
    		}
    		x += width/numberOfCollumns;
    	}
    }
}