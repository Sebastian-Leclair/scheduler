package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is to access the customers table in the connected database.*/
public class CustomersQuery {

    /**
     * A method to insert a new record into the customers table.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param divisionId
     */
    public static void insert(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setString(2, customerName);
        ps.setString(3, address);
        ps.setString(4, postalCode);
        ps.setString(5, phoneNumber);
        ps.setInt(6, divisionId);
        ps.executeUpdate();
    }

    /**
     * A method to delete a record from the customers table.
     * @param customerId
     */
    public static void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }

    /**
     * A method to update a record in the customers table.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param divisionId
     */
    public static void update(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        ps.executeUpdate();
    }

    /**
     * A method to get the customer record with the selected customer id.
     * @param customerId
     * @return
     */
    public static Customer getAppointmentCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID WHERE Customer_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Customer customer = new Customer(customerId, rs.getString("Customer_Name"), rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"), rs.getInt("Division_ID"), rs.getString("Division"), rs.getString("Country"));
        return customer;
    }

    /**
     * A method to generate a unique customer id for a new customer record.
     * @return
     */
    public static int generateCustomerId() throws SQLException {
        String sql = "SELECT Customer_ID FROM customers ORDER BY Customer_ID DESC LIMIT 0,1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Customer_ID") + 1;
    }

    /**
     * A method to get all the customers with the selected country name.
     * @param countryName
     * @return
     */
    public static ObservableList<Customer> getCountryCustomers(String countryName) throws SQLException {

        String sql = "SELECT * FROM customers INNER JOIN  first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID WHERE countries.Country = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customer> countryCustomers = FXCollections.observableArrayList();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            Customer customer = new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionId, divisionName, countryName);
            countryCustomers.add(customer);
        }
        return countryCustomers;
    }

    /**
     * A method to get all the customers.
     * @return
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {

        String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, countries.Country FROM customers INNER JOIN  first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            String countryName = rs.getString("Country");
            Customer customer = new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionId, divisionName, countryName);
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
