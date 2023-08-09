package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class creates an Appointment object.*/
public class Appointment {
    /**
     * A variable for the appointment id of the Appointment object.
     */
    private int appointmentId;

    /**
     * A variable for the title of the Appointment object.
     */
    private String title;

    /**
     * A variable for the description of the Appointment object.
     */
    private String description;

    /**
     * A variable for the location of the Appointment object.
     */
    private String location;

    /**
     * A variable for the type of the Appointment object.
     */
    private String type;

    /**
     * A variable for the start date and time of the Appointment object.
     */
    private LocalDateTime start;

    /**
     * A variable for the end date and time of the Appointment object.
     */
    private LocalDateTime end;

    /**
     * A variable for the customer id of the Appointment object.
     */
    private int customerId;

    /**
     * A variable for the user id of the Appointment object.
     */
    private int userId;

    /**
     * A variable for the contact id of the Appointment object.
     */
    private int contactId;

    /**
     * A variable for the contact name of the Appointment object.
     */
    private String contactName;

    /**
     * A constructor to make an Appointment object.
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     * @param contactName
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * A getter for the appointment id variable.
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * A setter for the appointment id variable.
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * A getter for the title variable.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * A setter for the title variable.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A getter for the description variable.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * A setter for the description variable.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A getter for the location variable.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * A setter for the location variable.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * A getter for the type variable.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * A setter for the type variable.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A getter for the start date and time variable.
     * @return
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * A setter for the start date and time variable.
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * A getter for the end date and time variable.
     * @return
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * A setter for the end date and time variable.
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * A getter for the customer id variable.
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * A setter for the customer id variable.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * A getter for the user id variable.
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * A setter for the user id variable.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
}
