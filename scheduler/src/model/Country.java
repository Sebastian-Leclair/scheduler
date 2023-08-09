package model;

/** This class creates a Country object.*/
public class Country {

    /**
     * A variable for the country id of the Country object.
     */
    private int countryId;

    /**
     * A variable for the country name of the Country object.
     */
    private String countryName;

    /**
     * A constructor to make a Country object.
     * @param countryId
     * @param countryName
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * A getter for the country id variable.
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * A setter for the country id variable.
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * A getter for the country name variable.
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * A setter for the country name variable.
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * An overloaded method to display country name variable instead of Country object when cast to a String.
     * @return
     */
    @Override
    public String toString() {
        return countryName;
    }
}
