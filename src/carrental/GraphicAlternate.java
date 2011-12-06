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
            pointerX = 0, pointerY = 0,
            textSpace = 100, textHeight = 15;
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Timestamp> timestamps = new ArrayList<>();
    private ArrayList<String> dateString;
    private Calendar calendar;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public GraphicAlternate() {
        calendar = Calendar.getInstance();
        
        addMouseListener(new MouseAdapter() { //TODO This does not work:
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mouseClicked(x, y);
            }
        });
        setTimestamps(timestamps);
    }
    
    public final void setTimestamps(ArrayList<Timestamp> t) {
        //generate timestamps that are at a certain point EACH day.
        if(t.isEmpty()) {
            for(int i = 0; i < 7; i++) {
                t.add(new Timestamp(calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 86400000) + (i * 86400000)));
            }
            timestamps = t;
        }
        else {
            timestamps = t;
        }
        //convert timestamps to specific date format
        dateString = new ArrayList<>();
        for (Timestamp timestamp : timestamps) {
            dateString.add(dateFormat.format(timestamp));
        }
        //calculate amount of collumsn from the number of timestamps.
        if(timestamps.isEmpty()) numberOfCollumns = 0;
        else numberOfCollumns = timestamps.size();
        if(numberOfCollumns > 0) collumnWidth = (width - textSpace) / numberOfCollumns;
        else collumnWidth = (width - textSpace);
        repaint();
    }
    
    public final void setBookings(ArrayList<Booking> bs) {
        bookings = bs;
        calendar = Calendar.getInstance();
        if(bookings.isEmpty()) numberOfRows = 0;
        else numberOfRows = bookings.size();
        repaint();
        
        //generate timestamps
        if(!bookings.isEmpty()) {
            Timestamp first_date = bookings.get(0).getTStart();
            for(Booking b : bs) {
                if(b.getTStart().before(first_date)) {
                    first_date = b.getTStart();
                }
            }
            if(first_date.before(new Timestamp(calendar.getTimeInMillis()))) {
                first_date = new Timestamp(calendar.getTimeInMillis());
            }
            ArrayList<Timestamp> t = new ArrayList<>();
            for(int i = 0; i < 7; i++) {
                t.add(new Timestamp(first_date.getTime() - (first_date.getTime() % 86400000) + (i * 86400000)));
            }
            setTimestamps(t);
        }
    }

    /**
     * Method is ran when mouse is clicked on component
     * @param x 
     * @param y 
     */
    private void mouseClicked(int x, int y) {
        System.out.println("You clicked" + x + " " + y);
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

        pointerX = textSpace;
        pointerY = 0;
        
        //print reservation blocks
        for(int y = 0; y < numberOfRows; y++) {
            for(int x = 0; x < numberOfCollumns; x++) {
                if(bookings.size() > x && bookings.get(y).isBooked(timestamps.get(x))) {
                    if(bookings.get(y).isMaintenance()) {
                        g.setColor(Color.YELLOW);
                    }
                    else {
                        g.setColor(Color.BLUE);
                    }
                    g.fillRect(x * collumnWidth + textSpace, y * rowHeight + 5, collumnWidth, rowHeight);
                }
                g.setColor(Color.BLACK);
                boolean bz = false;
                if(bookings.size() > x) bz = bookings.get(y).isBooked(timestamps.get(x));
                g.drawString(x+"::"+bz, x * collumnWidth + textSpace + 5, y * rowHeight + rowHeight);
                movePointerX();
            }
            movePointerY();
            pointerX = 0;
        }
        pointerY = 0;
        
        //print y-axis text
        for (int y = 0; y < numberOfRows; y++) {
            g.setColor(Color.black);
            String str = "";
            Vehicle v = CarRental.getInstance().requestVehicle(bookings.get(y).getID());
            if(v.getID() > -1 && !v.getDescription().isEmpty()) {
                str = v.getID()+": "+v.getDescription().substring(0,15);
            }
            else str = "Unknown Vehicle";
            g.drawString(str, 0, pointerY);
            movePointerY();
        }
        
        pointerX = textSpace;
        int textpointer = height - textHeight;

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height - 3*textHeight, width, height - 3*textHeight);
        //print x-axis text
        for (int x = 0; x < numberOfCollumns; x++) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(pointerX, 0, pointerX, height - 3*textHeight);
            g.setColor(Color.black);
            g.drawString(dateString.get(x), pointerX + 20, textpointer);
            movePointerX();
            if (textpointer == height - textHeight) { //TODO Fix so this doesn't expand height
                textpointer -= textHeight;
            } else {
                textpointer += textHeight;
            }
        }
    }
}