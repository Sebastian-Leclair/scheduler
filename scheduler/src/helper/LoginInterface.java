package helper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/** This class creates a LoginInterface interface for a lambda expression.*/
public interface LoginInterface {

    /**
     * An abstract method that runs whenever there is a successful login.
     * @param user
     * @param now
     * @param zoneId
     */
    String successfulLogin(String user, LocalDateTime now, ZonedDateTime zoneId);
}
