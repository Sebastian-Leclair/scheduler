package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates a Customer object.*/
public class Customer {
    /**
     * A variable for the customer id of the Customer object.
     */
    private int customerId;

    /**
     * A variable for the customer name of the Customer object.
     */
    private String customerName;

    /**
     * A variable for the address of the Customer object.
     */
    private String address;

    /**
     * A variable for the postal code of the Customer object.
     */
    private String postalCode;

    /**
     * A variable for the phone number of the Customer object.
     */
    private String phoneNumber;

    /**
     * A variable for the division id of the Customer object.
     */
    private int divisionId;

    /**
     * A variable for the division name of the Customer object.
     */
    private String divisionName;

    /**
     * A variable for the country name of the Customer object.
     */
    private String countryName;

    /**
     * A constructor to make a Customer object.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param divisionId
     * @param divisionName
     * @param countryName
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryName = countryName;
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
     * A getter for the customer name variable.
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * A setter for the customer name variable.
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * A getter for the address variable.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * A setter for the address variable.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * A getter for the postal code variable.
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * A setter for the postal code variable.
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * A getter for the phone number variable.
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * A setter for the phone number variable.
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * A getter for the division id variable.
     * @return
     */
    public int getDivisionId() { return divisionId; }

    /**
     * A setter for the division id variable.
     * @param divisionId
     */
    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }

    /**
     * A getter for the division name variable.
     * @return
     */
    public String getDivisionName() { return divisionName; }

    /**
     * A setter for the division name variable.
     * @param divisionName
     */
    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    /**
     * A getter for the country name variable.
     * @return
     */
    public String getCountryName() { return countryName; }

    /**
     * A setter for the country name variable.
     * @param countryName
     */
    public void setCountryName(String countryName) { this.countryName = countryName; }

    /**
     * An overloaded method to display customer id variable instead of Customer object when cast to a String.
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(customerId);
    }
}
