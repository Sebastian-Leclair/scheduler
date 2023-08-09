package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

/** This class is to access the appointments table in the connected database.*/
public class AppointmentsQuery {

    /**
     * A method to count the number of appointments that have the selected month and type.
     * @param month
     * @param type
     * @return
     */
    public static int count(String month, String type) throws SQLException {
        String sql = "SELECT COUNT(Appointment_ID) FROM appointments WHERE MONTHNAME(Start) = ? AND Type = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, month);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("COUNT(Appointment_ID)");
    }

    /**
     * A method to insert a new record into the appointments table of the connected database.
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
     */
    public static void insert(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, Timestamp.valueOf(start));
        ps.setTimestamp(7, Timestamp.valueOf(end));
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.executeUpdate();
    }

    /**
     * A method to delete a record from the appointments table of the connected database.
     * @param appointmentId
     */
    public static void delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.executeUpdate();
    }

    public static void deleteCustomerAppointments(int customerId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }

    /**
     * A method to update a record from the appointments table of the connected database.
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
     */
    public static void update(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        ps.executeUpdate();
    }

    /**
     * A method to generate unique appointment ids for new appointments.
     * @return
     */
    public static int generateAppointmentId() throws SQLException {
        String sql = "SELECT Appointment_ID FROM appointments ORDER BY Appointment_ID DESC LIMIT 0,1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Appointment_ID") + 1;
    }

    /**
     * A method to determine if the selected customer has any appointments.
     * @param customerId
     * @return
     */
    public static boolean isCustomerAppointmentsEmpty(int customerId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A method to determine if there are any upcoming appointments within 15 minutes.
     * @return
     */
    public static String upcomingAppointment() throws SQLException {

        String sql = "SELECT * FROM appointments WHERE YEAR(appointments.Start) = ? AND TIME(appointments.Start) BETWEEN ? AND ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(LocalDateTime.now().getYear()));
        ps.setString(2, String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalTime()));
        ps.setString(3, String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalTime().plusMinutes(15)));
        ResultSet rs = ps.executeQuery();
        // rs.next();

        if (rs.next()) {
            return ("Appointment ID: " + rs.getInt("Appointment_ID") + "\nDate / Time: " + (rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()));
        }
        else {
            return "";
        }
    }

    /**
     * A method to get all the appointments for the selected contact.
     * @param contactId
     * @return
     */
    public static ObservableList<Appointment> getContactAppointments(int contactId) throws SQLException {

        String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            String contactName = rs.getString("Contact_Name");
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            contactAppointments.add(appointment);
        }
        return contactAppointments;
    }

    /**
     * A method to get all the appointments for the current week.
     * @return
     */
    public static ObservableList<Appointment> getCurrentWeekAppointments() throws SQLException {
        String sql = "SELECT appointments.Appointment_ID, appointments.title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE DAY(appointments.Start) BETWEEN ? AND ? AND YEAR(appointments.Start) = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, LocalDateTime.now().with(DayOfWeek.MONDAY).getDayOfMonth());
        ps.setInt(2, LocalDateTime.now().with(DayOfWeek.FRIDAY).getDayOfMonth());
        ps.setInt(3, LocalDateTime.now().getYear());
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> currentWeekAppointments = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            currentWeekAppointments.add(appointment);
        }
        return currentWeekAppointments;
    }

    /**
     * A method to get all the appointments for the current month.
     * @return
     */
    public static ObservableList<Appointment> getCurrentMonthAppointments() throws SQLException {
        String sql = "SELECT appointments.Appointment_ID, appointments.title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(appointments.Start) = ? AND YEAR(appointments.Start) = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, LocalDateTime.now().getMonthValue());
        ps.setInt(2, LocalDateTime.now().getYear());
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> currentMonthAppointments = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            currentMonthAppointments.add(appointment);
        }
        return currentMonthAppointments;
    }

    /**
     * A method to get all the appointments for the selected customer.
     * @param customerId
     * @return
     */
    public static ObservableList<Appointment> getCustomerIdAppointments(int customerId) throws SQLException {

        String sql = "SELECT appointments.Appointment_ID, appointments.title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Customer_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            allAppointments.add(appointment);
        }
        return allAppointments;
    }

    /**
     * A method to get all the appointments.
     * @return
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {

        String sql = "SELECT appointments.Appointment_ID, appointments.title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            allAppointments.add(appointment);
        }
        return allAppointments;
    }
}
