package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is to access the contacts table in the connected database.*/
public class ContactsQuery {

    /**
     * A method to get all the contacts with the selected contact name.
     * @param contactName
     * @return
     */
    public static Contact getAppointmentContact(String contactName) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Contact contact = new Contact(rs.getInt("Contact_ID"), contactName, rs.getString("Email"));
        return contact;
    }

    /**
     * A method to get all the contacts.
     * @return
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {

        String sql = "SELECT * FROM contacts;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact contact = new Contact(contactId, contactName, email);
            allContacts.add(contact);
        }
        return allContacts;
    }
}
