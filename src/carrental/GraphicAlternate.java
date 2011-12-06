package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat; //To be used

/**
 * @(#)GraphicAlternate.java
 *
 *
 * @author
 * @version 1.00 2011/12/4
 */

public class GraphicAlternate extends JComponent {

    private int width, height, collumnWidth, rowHeight, numberOfCollumns, numberOfRows, pointerX, pointerY, textSpace, textHeight;
    private ArrayList<Booking> bookings;
    private ArrayList<Timestamp> timestamps;
    private ArrayList<Date> dates;
    public GraphicAlternate() {
        timestamps = new ArrayList<>();
        bookings = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < 10; i++) {
            timestamps.add(new Timestamp(calendar.getTimeInMillis() + (i * 3600)));
        }
        
        updateDatesArray();

        addMouseListener(new MouseAdapter() { //TODO This does not work:
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

        repaint();
    }
    
    public void setBookings(ArrayList<Booking> b) {
        bookings = b;
        repaint();
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
        frame.setPreferredSize(new Dimension(800, 600));
        frame.add(new GraphicAlternate());
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
     * Method is ran when mouse is clicked on component
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
        if(bookings.isEmpty()) numberOfRows = 0;
        else numberOfRows = bookings.size();
    }

    private void setCollumnWidth() {
        collumnWidth = width / numberOfCollumns;
    }

    private void setRowHeight() {
        if(numberOfRows > 0) rowHeight = height / numberOfRows;
        else rowHeight = height;
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