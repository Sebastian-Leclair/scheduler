package model;

/** This class creates a User object.*/
public class User {

    /**
     * A variable for the user id of the User object.
     */
    private int userId;

    /**
     * A variable for the username of the User object.
     */
    private String userName;

    /**
     * A variable for the password of the User object.
     */
    private String password;

    /**
     * A constructor to make a Customer object.
     * @param userId
     * @param userName
     * @param password
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
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
     * A getter for the username variable.
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A setter for the username variable.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * A getter for the password variable.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * A setter for the password variable.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * An overloaded method to display user id variable instead of User object when cast to a String.
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(userId);
    }
}
