package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is to access the first_level_divisions table in the connected database. */
public class FirstLevelDivisionsQuery {

    /**
     * A method to get the United States first level division record.
     * @return
     */
    public static ObservableList<FirstLevelDivision> getUnitedStatesFirstLevelDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_Id = rs.getInt("Country_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionId, divisionName, country_Id);
            allFirstLevelDivisions.add(firstLevelDivision);
        }
        return allFirstLevelDivisions;
    }

    /**
     * A method to get the United Kingdom first level division record.
     * @return
     */
    public static ObservableList<FirstLevelDivision> getUnitedKingdomFirstLevelDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_Id = rs.getInt("Country_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionId, divisionName, country_Id);
            allFirstLevelDivisions.add(firstLevelDivision);
        }
        return allFirstLevelDivisions;
    }

    /**
     * A method to get the Canada first level division record.
     * @return
     */
    public static ObservableList<FirstLevelDivision> getCanadaFirstLevelDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_Id = rs.getInt("Country_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionId, divisionName, country_Id);
            allFirstLevelDivisions.add(firstLevelDivision);
        }
        return allFirstLevelDivisions;
    }

    /**
     * A method to get the first_level_division record with the selected division name.
     * @param divisionName
     * @return
     */
    public static FirstLevelDivision getCustomerDivision(String divisionName) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divisionName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        FirstLevelDivision division = new FirstLevelDivision(rs.getInt("Division_ID"), divisionName, rs.getInt("Country_ID"));
        return division;
    }

    /**
     * A method to get all the first level divisions.
     * @return
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_Id = rs.getInt("Country_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionId, divisionName, country_Id);
            allFirstLevelDivisions.add(firstLevelDivision);
        }
        return allFirstLevelDivisions;
    }
}
