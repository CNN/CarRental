/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

/**
 * Graphic element for GUI
 * @author CNN
 * @version 2. Dec. 2011
 */
public class GraphicElement extends JComponent{
    private Canvas canvas;
    private JPanel panel;
    private int height, width, pointerX, pointerY, pHeight, pWidth, textSpace;
    private ArrayList<Booking> bookings;
    private Timestamp tStart, tEnd;
    private String textToPrint;
    
    public JPanel GraphicElement(ArrayList<Booking> bookings, Timestamp tStart, Timestamp tEnd){
        //initialise
        this.bookings = bookings;
        this.tStart = tStart;
        this.tEnd = tEnd;
        
        panel = new JPanel();
        canvas = new Canvas();
        textSpace = 30;
        width = 800;
        height = 600;
        
        //do stuff
        setPHeight();
        for(Booking booking : bookings){
            //evt. tjek om den er booket inden for den givne tidsperiode
            movePointer(booking);
            setPWidth(booking);
            printVehicleName(booking);
            if(booking.isMaintenance()){
                paint(Graphics g, new Color.yellow, pointerX, pointerY, pWidth, pHeight);
            }else{
                paint(Graphics g, new Color.blue, pointerX, pointerY, pWidth, pHeight);
            }
        }
        
        //return 
        panel.add(canvas);
        canvas.setVisible(true);
        panel.setVisible(true);
        return panel;
    }
    
    private void printVehicleName(Booking booking){
        textToPrint = model.getVehicleDescription(booking.getVehicleID());
        paintText(Graphics g, Color.black, text, 0, pointerY);
    }
    
    /**
     * Sets paint width based on number of days booked, and width of canvas minus space for vehicle description
     * @param booking 
     */
    private void setPWidth(Booking booking){
        long difb = (booking.getTEnd().getTime() - booking.getTStart().getTime());
        int daysb = (int) TimeUnit.DAYS.convert(difb, TimeUnit.NANOSECONDS); //number of days booked
        long dif = (tEnd.getTime() - tStart.getTime());
        int days = (int) TimeUnit.DAYS.convert(dif, TimeUnit.NANOSECONDS); //number of days total
        double doublewidth = (daysb/days) * (width-textSpace);
        pWidth = (int) doublewidth;
        System.out.println("" + pWidth);
    }
    
    private void movePointer(Booking booking){
        if(bookings.get(0).equals(booking)){
            pointerY = 0;
        }else{
            pointerY = pHeight;
        }
        pointerX += textSpace + 2;
    }
    
    private void setPHeight(){
        pHeight = height/bookings.size();
    }
    
    //for testing
    public static void main(String[] args){
        new GraphicElement();
    }
    
    private void paintVehicleDescription(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString("hej", 0, pointerY);
    }
    
    public void paintText(Graphics g, Color color, String text, int x, int y){
        g.setColor(color);
        g.drawString(text, y, y);
    }
    
    public void paint(Graphics g, Color color, int x, int y, int width, int height){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
