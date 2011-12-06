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
 * GraphicAlternate displays an overview of vehicles and their respective
 * reservations and maintenances.
 * @author CNN
 * @version 2011-12-06
 */
public class GraphicAlternate extends JComponent {
    private int width = 800, height = 600,
            collumnWidth, rowHeight = 20,
            numberOfCollumns, numberOfRows,
            pointerX, pointerY,
            textSpace = 50, textHeight = 15;
    private ArrayList<Booking> bookings;
    private ArrayList<Timestamp> timestamps;
    private ArrayList<String> dateString;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public GraphicAlternate() {
        timestamps = new ArrayList<>();
        bookings = new ArrayList<>();

        //generate timestamps that are at a certain point EACH day.
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < 10; i++) {
            timestamps.add(new Timestamp(calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 86400000) + (i * 86400000)));
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

        repaint();
    }
    
    public void setBookings(ArrayList<Booking> b) {
        bookings = b;
        repaint();
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
        dateString = new ArrayList<>();
        for (Timestamp timestamp : timestamps) {
            dateString.add(dateFormat.format(timestamp));
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

    public void paint(Graphics g) {
        int run = 0; //for testing

        //print y-axis text
        for (int y = 0; y < numberOfRows; y++) {
            g.setColor(Color.black);
            g.drawString("Vehicle ", 0, pointerY);
            movePointerY();
        }
        
        pointerX = textSpace;
        int textpointer = height - textHeight;

        //print x-axis text
        for (int x = 0; x < numberOfCollumns; x++) {
            g.setColor(Color.black);
            g.drawString(dateString.get(x).toString(), pointerX, textpointer);
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