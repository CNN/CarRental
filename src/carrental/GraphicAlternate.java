package carrental;

import java.awt.*;
import java.awt.event.*;
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
public class GraphicAlternate extends JComponent {

    private int width, height, collumnWidth, rowHeight, numberOfCollumns, numberOfRows, pointerX, pointerY, textSpace, textHeight;
    private Canvas canvas;
    private ArrayList<Booking> bookings;
    private ArrayList<Timestamp> timestamps;
    private ArrayList<Date> dates;

    public GraphicAlternate(ArrayList<Booking> bookings, ArrayList<Timestamp> timestamps) {
        canvas = new Canvas();
        this.bookings = bookings;
        this.timestamps = timestamps;
        updateDatesArray();

        canvas.addMouseListener(new MouseAdapter() { //TODO This does not work:
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mouseClicked(x, y);
            }
        });

        width = 800;
        height = 600;
        pointerX = 0;
        pointerY = 0;
        textSpace = 50;

        setNumberOfCollumns();
        setNumberOfRows();
        setCollumnWidth();
        setRowHeight();

        canvas.setPreferredSize(new Dimension(width, height));
        canvas.repaint();
    }

    public static void main(String[] args) {
        ArrayList bs = new ArrayList();
        for (int x = 0; x < 20; x++) {
            bs.add(new Reservation(x, x * 1000 + 1, new Timestamp(x * 2 + 1), new Timestamp(x * 5 - 10), x + 2));
        }

        ArrayList ts = new ArrayList();
        for (int x = 0; x < 12; x++) {
            ts.add(new Timestamp(x * 10));
        }

        JFrame frame = new JFrame();
        frame.add(new GraphicAlternate(bs, ts));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    //Type converter
    private Date toDate(Timestamp timestamp) { //I know it isn't used yet
        long milliseconds = timestamp.getTime();
        return new Date(milliseconds);
    }

    /**
     * Method is ran when mouse is clicked on canvas
     * @param x 
     * @param y 
     */
    private void mouseClicked(int x, int y) {
        System.out.println("You clicked" + x + " " + y);
    }

    private void updateDatesArray() {
        dates = null;
        dates = new ArrayList();
        for (Timestamp timestamp : timestamps) {
            dates.add(toDate(timestamp));
        }
    }

    private void setNumberOfCollumns() {
        numberOfCollumns = timestamps.size();
    }

    private void setNumberOfRows() {
        numberOfRows = bookings.size();
    }

    private void setCollumnWidth() {
        collumnWidth = width / numberOfCollumns;
    }

    private void setRowHeight() {
        rowHeight = height / numberOfRows;
    }

    private void movePointerY() {
        if (pointerY >= (height - textHeight)) {
            pointerY = 0;
        } else {
            pointerY += rowHeight;
        }
    }

    private void movePointerX() {
        if (pointerX >= (width - collumnWidth)) {
            pointerX = textSpace;
        } else {
            pointerX += collumnWidth;
        }
    }

    private void setTextSpace() {
        //for(Booking booking : bookings){
        //if(textSpace < "".length()) 
        textSpace = "Volvo 740 ".length(); //TODO fix
        //}
    }

    private void setTextHeight() {
        textHeight = 10; //TODO Fix this too
    }

    public void paint(Graphics g) {
        int run = 0; //for testing

        //print y-axis text
        for (int y = 0; y < numberOfRows; y++) {
            g.setColor(Color.black);
            g.drawString("Vehicle ", 0, pointerY);
            movePointerY();
        }

        setTextSpace();
        setTextHeight();
        pointerX = textSpace;
        int textpointer = height - textHeight;

        //print x-axis text
        for (int x = 0; x < numberOfCollumns; x++) {
            g.setColor(Color.black);
            g.drawString(dates.get(x).toString(), pointerX, textpointer);
            movePointerX();
            if (textpointer == height - textHeight) { //TODO Fix so this doesn't expand height
                textpointer -= textHeight;
            } else {
                textpointer += textHeight;
            }
        }

        //print reservation blocks
        for (int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < numberOfCollumns; x++) {
                if (bookings.get(y).isBooked(timestamps.get(x))) {
                    if (bookings.get(y).isMaintenance()) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.blue);
                    }
                    g.fillRect(pointerX, pointerY, collumnWidth, rowHeight);
                    movePointerX();
                    movePointerY();
                }
                run++; //for testing
            }
            run++; //for testing
        }
        pointerX = 0;
        pointerY = 0;
        System.out.println("" + run); //for testing
    }
}