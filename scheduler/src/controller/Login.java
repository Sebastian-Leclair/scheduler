package controller;

import DAO.AppointmentsQuery;
import DAO.UsersQuery;
import com.mysql.cj.xdevapi.Warning;
import com.sun.javafx.runtime.VersionInfo;
import helper.LoginInterface;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static java.time.LocalDateTime.now;

/** This class acts as a controller for the Login view.*/
public class Login implements Initializable {
    /**
     * The stage of the GUI.
     */
    Stage stage;

    /**
     * The scene of the GUI.
     */
    Parent scene;

    /**
     * An element that is part of the GUI.
     */
    public Label zoneIdLbl;

    /**
     * An element that is part of the GUI.
     */
    public Label userNameLbl;

    /**
     * An element that is part of the GUI.
     */
    public Label passwordLbl;

    /**
     * An element that is part of the GUI.
     */
    public Label locationLbl;

    /**
     * An element that is part of the GUI.
     */
    public TextField userNameTxt;

    /**
     * An element that is part of the GUI.
     */
    public TextField passwordTxt;

    /**
     * An element that is part of the GUI.
     */
    public Button loginBtn;

    /**
     * An error alert variable.
     */
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * A warning alert variable.
     */
    private Alert warning = new Alert(Alert.AlertType.WARNING);

    /**
     * A variable for the user id.
     */
    public int userId;

    /**
     * An event that checks whether a login is successful or not.
     * The justification for the lambda expression is that it will help maintain readability of the code while creating a successful login message to track user activity.
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onActionLogin(ActionEvent actionEvent) throws SQLException, IOException {

        if(UsersQuery.verify(userNameTxt.getText(), passwordTxt.getText())){
            String filename = "login_activity.txt", item;
            FileWriter file = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(file);

            // Lambda Expression
            LoginInterface message = (user, now, zoneId) -> "User " + user + " successfully logged in at " + now + " " + zoneId;

            outputFile.println(message.successfulLogin(userNameTxt.getText(), LocalDateTime.now(), LocalDateTime.now().atZone(ZoneId.systemDefault())));
            outputFile.close();

            if (AppointmentsQuery.upcomingAppointment() != "") {
                warning.setTitle("Upcoming Appointment");
                warning.setContentText("You have an appointment scheduled within 15 minutes. \n" + AppointmentsQuery.upcomingAppointment());
                warning.showAndWait();
            }
            else {
                warning.setTitle("No Upcoming Appointment");
                warning.setContentText("You do not have an appointment scheduled within 15 minutes.");
                warning.showAndWait();
            }

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            String filename = "login_activity.txt", item;
            FileWriter file = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(file);
            outputFile.println("User " + userNameTxt.getText() + " failed to log in at " + now() + " " + now().atZone(ZoneId.systemDefault()));
            outputFile.close();

            if(alert.getContentText() == "") {
                alert.setTitle("Error Dialog");
                alert.setContentText("The username and/or password you have entered is incorrect.");
            }
            alert.showAndWait();
        }
    }

    /**
     * The code that runs when the view is initially displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ResourceBundle rb = ResourceBundle.getBundle("helper/Nat", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("fr")) {
                userNameLbl.setText(rb.getString("Username") + ":");
                passwordLbl.setText(rb.getString("Password") + ":");
                locationLbl.setText(rb.getString("Location") + ":");
                loginBtn.setText(rb.getString("Login"));

                alert.setTitle(rb.getString("Error") + " " + rb.getString("Dialog"));
                alert.setContentText
                        (
                        rb.getString("The") + " " +
                        rb.getString("username") + " " +
                        rb.getString("and/or") + " " +
                        rb.getString("password") + " " +
                        rb.getString("you") + " " +
                        rb.getString("have") + " " +
                        rb.getString("entered") + " " +
                        rb.getString("is") + " " +
                        rb.getString("incorrect") + "."
                        );

            }
        }
        catch(MissingResourceException e){
            System.out.println("MissingResourceException");
        }

        ZoneId zone = ZoneId.systemDefault();
        zoneIdLbl.setText(String.valueOf(zone));
    }
}
