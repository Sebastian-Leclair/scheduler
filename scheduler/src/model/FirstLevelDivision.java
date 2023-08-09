package model;

/** This class creates a First Level Division object.*/
public class FirstLevelDivision {

    /**
     * A variable for the division id of the FirstLevelDivision object.
     */
    private int divisionId;

    /**
     * A variable for the division name of the FirstLevelDivision object.
     */
    private String divisionName;

    /**
     * A variable for the country id of the FirstLevelDivision object.
     */
    private int countryId;

    /**
     * A constructor to make a FirstLevelDivision object.
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public FirstLevelDivision(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * A getter for the division id variable.
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * A setter for the division id variable.
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * A getter for the division name variable.
     * @return
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * A setter for the division name variable.
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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
     * An overloaded method to display division name variable instead of FirstLevelDivision object when cast to a String.
     * @return
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
