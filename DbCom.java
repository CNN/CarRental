/**
 * @(#)DbCom.java
 *              <---- needs project name
 * @author SGN  <---- needs study group name
 * @author CNN  <---- Claus Niels Niclas
 * @version 1.00 2011/11/18
 */


public class DbCom {

    public DbCom() {
    	//initialise connection
    	//run
    	//close connection
    }

    /**
     * @param Start date and end date
     * @return reservations within the given dates
     */
     public somehow getReservations(Date start, Date end){ //Would you prefer one get-method with table as param?

     }

    /**
     * Adds entry in table
     * @param String , String
     * @return unique carID or 0 if not succeeded
     */
    public int addCar(String tableName, fieldNames){ //methods needs try catch
    	int pk = createPK(Car); //remember to check !0
    	//more code

    	INSERT INTO Car VALUES(PK, ) //more values

    	return pk; //or zero
    }

    /**
     * Adds reservation to reservation table
     * @param stuff
     * @return succeeded
     */
    public boolean addReservation(stuff){
    	int pk = createPK(Reservation); //remember to check !0
		INSERT INTO Reservation VALUES(PK, )
    }

    /**
     * Edits reservation
     * @param reservation name or id?, new fields
     * @return succeeded
     */
     public boolean editReservation(stuff){

     }

     /**
      * Deletes reservation
      * @param reservation to delete
      * @return succeeded
      */
      public boolean deleteReservation(stuff){

      }

     /**
      * Creates primary key for next entry in a given table
      * @param table to check
      * @return int PK
      */
      private int createPK(table){
      	int pk = 0;
      	//code

      	return pk;
      }
}