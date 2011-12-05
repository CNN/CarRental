package carrental;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat; //To be used

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

        canvas.setPreferredSize(new Dimension(width, height));
        canvas.repaint();
    }

    public static void main(String[] args) {
        ArrayList bs = new ArrayList();
        for (int x = 0; x < 10; x++) {
            bs.add(new Reservation(x, x + 1, new Timestamp(x * 5), new Timestamp(x * 10), x + 2));
        }

        ArrayList ts = new ArrayList();
        for (int x = 0; x < 12; x++) {
            ts.add(new Timestamp(x * 10));
        }

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.add(new GraphicAlternate(bs, ts));
        frame.pack();
        frame.setVisible(true);
    }

    private Date toDate(Timestamp timestamp) { //I know it isn't used yet
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
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

    public void paint(Graphics g) {
        int x = 0;
        while (x < numberOfCollumns) {
            int y = 0;
            while (y < numberOfRows) {
                if (bookings.get(y).isBooked(timestamps.get(x))) {
                    if (bookings.get(y).isMaintenance()) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.blue);
                    }
                    g.fillRect(x, y, collumnWidth, rowHeight);
                }
                y++;
            }
            x++;
        }
    }
}