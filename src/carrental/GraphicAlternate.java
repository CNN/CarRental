package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * GraphicAlternate displays an overview of vehicles and their respective
 * reservations and maintenances.
 * @author CNN
 * @version 2011-12-06
 */
public class GraphicAlternate extends JComponent {
    public static final int
            VIEW_DAYS = 0,
            VIEW_WEEKS = 1,
            VIEW_MONTHS = 2,
            VIEW_MONTH = 3;
    public static final int
            S_IN_DAY = 86400,
            S_IN_WEEK = 604800,
            S_IN_MONTH = 2592000;
    private int unit;
    private int width = 800, height = 600,
            collumnWidth, rowHeight = 20,
            numberOfCollumns, numberOfRows,
            pointerX = 0, pointerY = 0,
            textSpace = 100, textHeight = 15,
            display = VIEW_DAYS;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<ArrayList<Booking>> vehicle_bookings = new ArrayList<>();
    private ArrayList<Timestamp> timestamps = new ArrayList<>();
    private ArrayList<String> dateString;
    private Calendar calendar;
    private Timestamp first_date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"),
            month = new SimpleDateFormat("MM");
    
    public GraphicAlternate() {
        calendar = Calendar.getInstance();
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Clickety "+x+","+y);
            }
        });
        refreshDataAndPaint();
    }
    
    public final void refreshDataAndPaint() {
        //get vehicles, set row amounts
        vehicles = CarRental.getInstance().requestVehicles();
        if(!vehicles.isEmpty()) numberOfRows = vehicles.size();
        else numberOfRows = 0;
        
        //find all bookings and first date
        first_date = new Timestamp(calendar.getTimeInMillis() * 1000);
        vehicle_bookings = new ArrayList<>();
        for(Vehicle v : vehicles) {
            ArrayList<Booking> bs = CarRental.getInstance().requestBookingsByVehicle(v.getID());
            if(!bs.isEmpty()) {
                for(Booking b : bs) {
                    if(b.getTStart().before(first_date) && b.getTEnd().after(new Timestamp(calendar.getTimeInMillis())))
                        first_date = b.getTStart();
                }
                if(first_date.before(new Timestamp(calendar.getTimeInMillis())))
                    first_date = new Timestamp(calendar.getTimeInMillis());
            }
            vehicle_bookings.add(bs);
        }
        
        //find millis pr. unit and number of collumns
        switch(display) {
            case(VIEW_DAYS):
                unit = S_IN_DAY;
                numberOfCollumns = 7;
                break;
            case(VIEW_WEEKS):
                unit = S_IN_WEEK;
                numberOfCollumns = 4;
                break;
            case(VIEW_MONTHS):
                unit = S_IN_MONTH;
                numberOfCollumns = 12;
                break;
            case(VIEW_MONTH):
                unit = S_IN_DAY;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(first_date.getTime());
                if(month.format(new Date(first_date.getTime())).equals("02") &&
                        cal.isLenient()) numberOfCollumns = 29;
                else if(month.format(new Date(first_date.getTime())).equals("02") &&
                        !cal.isLenient()) numberOfCollumns = 28;
                else if(Integer.parseInt(month.format(new Date(first_date.getTime()))) % 2 == 0 &&
                        Integer.parseInt(month.format(new Date(first_date.getTime()))) > 7 ||
                        Integer.parseInt(month.format(new Date(first_date.getTime()))) % 2 == 1 &&
                        Integer.parseInt(month.format(new Date(first_date.getTime()))) < 8) numberOfCollumns = 31;
                else numberOfCollumns = 30;
                numberOfCollumns = 10;
                break;
            default:
                unit = 0;
                numberOfCollumns = 0;
                break;
        }
        
        if(numberOfCollumns != 0) collumnWidth = width / numberOfCollumns;
        else collumnWidth = width;
                
        //generate all timestamps
        timestamps = new ArrayList<>();
        for(int i = 0; i < numberOfCollumns; i++) {
            timestamps.add(new Timestamp(first_date.getTime() - (first_date.getTime() % (unit * 1000)) + (i * unit * 1000)));
        }
        
        //calculate amount of collumns from the number of timestamps.
        if(timestamps.isEmpty()) numberOfCollumns = 0;
        else numberOfCollumns = timestamps.size();
        if(numberOfCollumns > 0) collumnWidth = (width - textSpace) / numberOfCollumns;
        else collumnWidth = (width - textSpace);
        
        //convert timestamps to specific date format
        dateString = new ArrayList<>();
        for (Timestamp timestamp : timestamps) {
            dateString.add(dateFormat.format(timestamp));
        }
        
        repaint();
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
        pointerX = textSpace;
        pointerY = 0;
        
        g.setColor(Color.GREEN);
        g.fillRect(textSpace, 5, collumnWidth, rowHeight);
        
        //print reservation blocks
        for(int y = 0; y < numberOfRows; y++) {
            for(int x = 0; x < numberOfCollumns; x++) {
                boolean booked = false;
                for(Booking b : vehicle_bookings.get(y)) {
                    if(b.getTStart().before(timestamps.get(x))) {
                        booked = true;
                        if(b.isMaintenance()) g.setColor(Color.RED);
                        else g.setColor(Color.BLUE);
                    }
                }
                if(booked) g.fillRect(x * collumnWidth + textSpace, y * rowHeight + 5, collumnWidth, rowHeight);

                g.setColor(Color.BLACK);
                movePointerX();
            }
            movePointerY();
            pointerX = 0;
        }
        pointerY = 0;
        
        //print y-axis text
        for(Vehicle v : vehicles) {
            g.setColor(Color.black);
            String str = "";
            if(v.getID() > -1 && !v.getDescription().isEmpty()) {
                String desc = v.getDescription();
                if(desc.length() > 16) desc = desc.substring(0,16);
                str = v.getID()+": "+desc;
                if(v.getID() < 10) str = "0"+str;
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