package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is to access the countries table in the connected database.*/
public class CountriesQuery {

    /**
     * A method to get the countries with the selected country name.
     * @param countryName
     * @return
     */
    public static Country getCustomerCountry(String countryName) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Country country = new Country(rs.getInt("Country_ID"), countryName);
        return country;
    }

    /**
     * A method to get all the countries.
     * @return
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        String sql = "SELECT * FROM countries;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        while(rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country country = new Country(countryId, countryName);
            allCountries.add(country);
        }
        return allCountries;
    }
}
