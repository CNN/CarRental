package carrental;

/**
 * Customer holds information about a customer.
 * @author CNN
 * @version 2011-12-04
 */
public class Customer {

    private final int ID;
    private String telephone;
    private String name;
    private String adress;
    private String eMail;

    /**
     * This constructor is used to initialize the fields needed for an instance 
     * of Customer
     * @param ID The ID number of this Customer.
     * @param telephone The telephone number of this Customer.
     * @param name The name of this Customer.
     * @param adress The adress of this Customer, in the format "[Road] 
     * [Number]\n[Zip code] [Town]".
     * @param eMail The e-mail adress of this Customer.
     */
    public Customer(int ID, String telephone, String name, String adress, 
            String eMail) {
        this.ID = ID;
        this.telephone = telephone;
        this.name = name;
        this.adress = adress;
        this.eMail = eMail;
    }

    /**
     * This method updates this Customer object
     * @param telephone The updated telephone number of this Customer.
     * @param name The updated name of this Customer.
     * @param adress The updated adress of this Customer, in the format 
     * "[Road] [Number]\n[Zip code] [Town]".
     * @param eMail The updated e-mail adress of this Customer.
     */
    public void updateObject(String telephone, String name, String adress, 
            String eMail) {
        this.telephone = telephone;
        this.name = name;
        this.adress = adress;
        this.eMail = eMail;
    }

    /**
     * This method returns the ID number of this Customer.
     * @return The ID number of this Customer.
     */
    public int getID() {
        return ID;
    }

    /**
     * This method returns the telephone number of this Customer.
     * @return The telephone number of this Customer.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method returns the name of this Customer.
     * @return The name of this Customer.
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the adress of this Customer.
     * @return The adress of this Customer.
     */
    public String getAdress() {
        return adress;
    }
    
    /**
     * This method returns the postal code of this Customer. The postal code 
     * is found from the provided adress.
     * @return The postal code of this Customer.
     */
    public int getPostalCode() {
        String[] address = adress.split("\n");
        if (address.length > 1) {
            return Integer.parseInt(address[1]);
        } else {
            return 0;
        }
    }

    /**
     * This method returns the e-mail adress of this Customer.
     * @return The e-mail adress of this Customer.
     */
    public String getEMail() {
        return eMail;
    }
}
