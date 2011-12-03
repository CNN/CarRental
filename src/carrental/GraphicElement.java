/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Graphic element for GUI
 * @author CNN
 * @version 2. Dec. 2011
 */
public class GraphicElement extends JComponent{
    private Canvas canvas;
    private int height, width, pointerX, pointerY, pHeight, pWidth, textSpace, numberOfDays;
    private ArrayList<Booking> bookings;
    private Timestamp tStart, tEnd;
    
    //need to convert timestamp to date
    
    public GraphicElement(ArrayList<Booking> bookings, Timestamp tStart, Timestamp tEnd){
        //initialise
        this.bookings = bookings;
        this.tStart = tStart;
        this.tEnd = tEnd;
        
        canvas = new Canvas();
        textSpace = 30;
        width = 800;
        height = 600;
        
        //do stuff
        
        
        //finalise
        canvas.repaint();
        canvas.setVisible(true);
    }
    
    /**
     * Sets paint width based on number of days booked, and width of canvas minus space for vehicle description
     * @param booking 
     */
    private void setPWidth(Booking booking){
        long difb = (booking.getTEnd().getTime() - booking.getTStart().getTime());
        int daysb = (int) TimeUnit.DAYS.convert(difb, TimeUnit.NANOSECONDS); //number of days booked
        long dif = (tEnd.getTime() - tStart.getTime());
        numberOfDays = (int) TimeUnit.DAYS.convert(dif, TimeUnit.NANOSECONDS); //number of days total
        double doublewidth = (daysb/numberOfDays) * (width-textSpace);
        pWidth = (int) doublewidth;
        System.out.println("" + pWidth);
    }
    
    private void movePointer(Booking booking){
        if(bookings.get(0).equals(booking)){
            pointerY = 0;
        }else{
            pointerY += pHeight;
        }
        pointerX = textSpace + 2;
    }
    
    private void setPHeight(){
        pHeight = height/bookings.size();
    }
    
    //for testing
    public static void main(String[] args){
        //new GraphicElement();
    }
    
    private Date toDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }
    
    public void paint(Graphics g){
        setPHeight();
        for(Booking booking : bookings){
            //evt. tjek om den faktisk er booket inden for den viste tidsperiode
            //tjek om der er flere bookings af det samme vehicle
            movePointer(booking);
            setPWidth(booking);
            //String textToPrint = model.getVehicleDescription(booking.getVehicleID()); //evt. gem vehicleDescription i en booking... det er nok i virkeligheden bedre
            String textToPrint = "Vehicle description"; //to be deleted
            g.setColor(Color.black);
            g.drawString(textToPrint, 0, pointerY);
            if(booking.isMaintenance()){
                g.setColor(Color.yellow);
            }else{
                g.setColor(Color.blue);
            }
            // ! move pointerX to correct date
            g.fillRect(pointerX, pointerY, pWidth, pHeight);
        }
        //paint x-koordinate description
    }
}
