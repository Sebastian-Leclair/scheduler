package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is to access the users table in the connected database.*/
public abstract class UsersQuery {

    /**
     * A method to get the user record with the selected user id.
     * @param userId
     * @return
     */
    public static User getAppointmentUser(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(userId, rs.getString("User_Name"), rs.getString("Password"));
        return user;
    }

    /**
     * A method to get all the users.
     * @return
     */
    public static ObservableList<User> getAllUsers() throws SQLException {

        String sql = "SELECT * FROM users;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<User> allUsers = FXCollections.observableArrayList();

        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            User user = new User(userId, userName, password);
            allUsers.add(user);
        }
        return allUsers;
    }

    /**
     * A method to verify that the inputted username and password exist within the users table.
     * @param userName
     * @param password
     * @return
     */
    public static boolean verify(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}
