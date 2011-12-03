/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;
import java.sql.*;
import java.util.ArrayList;

/**
 * Communicator object for controller and database
 * @author CNN
 * @version 2011-12-02
 */
public class DbCom {
    private Connection conn;
    private static Statement stm;
    
    public DbCom(){
    	openConnection();
    }
    
    /**
     * Get the first element matching the query string as an array of strings.
     * The array can easily be inserted into an object of the fitting type.
     * @param query string to match
     * @return entry matching string
     */
    public ArrayList<String> getFirstMatch(String query) {
        ArrayList<String> result = new ArrayList<>();
        try {
            if(newStatement().execute(query)) {
                int columns = stm.getResultSet().getMetaData().getColumnCount();
                stm.getResultSet().next();
                for(int i = 0; i < columns; i++) {
                    result.add(stm.getResultSet().getString(i + 1));
                }
            }
            else CarRental.getInstance().appendLog("Could not get matches, query execution failed.");
        }
        catch (SQLException e) {
            CarRental.getInstance().appendLog("Error while getting matches from database.",e);
        }
        return result;
    }
    
    /**
     * Get an array of all the matches of the query string as string arrays.
     * Each arraylist of strings represents and entry in the table.
     * @param query string to match
     * @return entries matching string
     */
    public ArrayList<ArrayList<String>> getMatches(String query) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        try {
            if(newStatement().execute(query)) {
               ResultSet rs = stm.getResultSet();
               int columns = rs.getMetaData().getColumnCount();
               
               while(rs.next()) {
                   ArrayList<String> record = new ArrayList<>();
                   
                   for(int i = 0; i < columns; i++) {
                       record.add(rs.getString(i + 1));
                   }
                   results.add(record);
               }
               return results;
            }
            else CarRental.getInstance().appendLog("Could not get matches, query execution failed.");
        }
        catch (SQLException e) {
            CarRental.getInstance().appendLog("Error while getting matches from database.",e);
        }
        return null;
    }
    
    /**
     * Save the object to the database. If the object exists (id found) the
     * entry is updated, if not it is inserted.
     * @param table the table to save to
     * @param columns the collumns to save, ordered as the collumns in the table
     */
    public void saveArray(String table, ArrayList<String> columns) {
        //null
    }
    
    /**
     * Creates a new statement, leaving the previous behind.
     * @return 
     */
    private Statement newStatement() {
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            CarRental.getInstance().appendLog("Failed to create a new statement.",e);
        }
        return stm;
    }
    
    /*public ArrayList<Vehicle> getInformation(){
        ArrayList<Vehicle> info = new ArrayList<Vehicle>();
        String query = "SELECT id, type, description, licensplate, odo, additional FROM Vehicle";
        try {
            boolean ok = newStatement().execute(query);
            if (ok) {
                ResultSet res = stm.getResultSet();
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
    }*/
    
/*    private void doStuff2(){
        ArrayList<Vehicle> test = getInformation();
        for(Vehicle ve : test){
            System.out.println("" + ve.getDescription());
        }
    }*/
    
/*    private void doStuff(){
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
    }*/
    
    /**
     * Connect to the database.
     */
    private void openConnection() {
        try {
            // Fra mysql-connector-java-5.1.5-bin.jar, lagt i /program files/java/jdk1.7.0_01/jre/lib/ext/
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/carrental", "root", "N1ller1993");
            CarRental.getInstance().appendLog("Connected to database.");
        }
        catch (SQLException e) {
            CarRental.getInstance().appendLog("Could not open database connection.",e);
        }
        catch (ClassNotFoundException e) {
            CarRental.getInstance().appendLog("Cannot find database",e);
        }
    }
    
    /**
     * Close the connection to the database
     */
    public void closeConnection(){
        try {
            if(conn != null) conn.close();   
        }
        catch (SQLException e){ 
             CarRental.getInstance().appendLog("Kan ikke lukke databaseforbindelsen",e);
        }
    }
}