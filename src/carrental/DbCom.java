/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Communicator object for controller and database
 * @author CNN
 * @version 29 Nov. 2011
 */
public class DbCom {
    private Connection conn;
    private Statement dbStatement;
    
    public DbCom(){
    	openDb(); //create connection
    	//doStuff2(); //run
    	//closeDb();
    }
    
    public static void main(String[] args){
        DbCom com = new DbCom();
    }
    
    public ArrayList<Vehicle> getInformation(){
        try {
            dbStatement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DbCom.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Vehicle> info = new ArrayList<Vehicle>();
        String query = "SELECT id, type, description, licensplate, odo, additional FROM Vehicle";
        try {
            boolean ok = dbStatement.execute(query);
            if (ok) {
                ResultSet res = dbStatement.getResultSet();
                while (res.next()) { 
                    Vehicle v = new Vehicle(
                            res.getInt("id"), 
                            res.getInt("type"), 
                            res.getString("description"), 
                            res.getString("licensplate"), 
                            res.getInt("odo"), 
                            res.getString("additional")
                            );
                    //param: int ID, int vehicleType, String description, String licensplate, int odo, String additional
                    info.add(v);
                }
            }
        } catch (SQLException exn) {
            System.out.println("Can not read from database: " + exn);   
        }
        return info; //ArrayList with information
    }
    
    private void doStuff2(){
        ArrayList<Vehicle> test = getInformation();
        for(Vehicle ve : test){
            System.out.println("" + ve.getDescription());
        }
    }
    
    private void doStuff(){
        try {
            dbStatement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DbCom.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "SELECT name FROM  vehicletype;";
        try {
            boolean ok = dbStatement.execute(query);
            if (ok) {
                ResultSet res = dbStatement.getResultSet();
                while (res.next()) { 
                    System.out.println(""+ res.getString("name"));
                }
            }
        } catch (SQLException exn) {
            System.out.println("Kan ikke lÊse fra database: " + exn);   
        }
    }
    
    private void openDb() {
        try {
            // Fra mysql-connector-java-5.1.5-bin.jar, lagt i /program files/java/jdk1.7.0_01/jre/lib/ext/
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/carrental", "root", "1991");
        } catch (SQLException exn) {
            System.out.println("Kan ikke Âbne databaseforbindelsen: " + exn);
        } catch (ClassNotFoundException exn) {
            System.out.println("Kan ikke finde databasedriver: " + exn);
        }
    }
    
    private void closeDb(){
        try{
            if(conn != null)
                conn.close();   
             }catch (SQLException exn){ 
                 System.out.println("Kan ikke lukke databaseforbindelsen: " + exn);
             }
    }
}