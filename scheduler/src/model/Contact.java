package model;

/** This class creates a Contact object.*/
public class Contact {

    /**
     * A variable for the contact id of the Contact object.
     */
    private int contactId;

    /**
     * A variable for the contact name of the Contact object.
     */
    private String contactName;

    /**
     * A variable for the email of the Contact object.
     */
    private String email;

    /**
     * A constructor to make a Contact object.
     * @param contactId
     * @param contactName
     * @param email
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * A getter for the contact id variable.
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * A setter for the contact id variable.
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * A getter for the contact name variable.
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * A setter for the contact name variable.
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * A getter for the email variable.
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * A setter for the email variable.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * An overloaded method to display contact name variable instead of Contact object when cast to a String.
     * @return
     */
    @Override
    public String toString() {
        return contactName;
    }
}
