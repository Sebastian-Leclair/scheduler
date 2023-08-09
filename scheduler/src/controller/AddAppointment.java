package controller;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

/** This class acts as a controller for the AddAppointment view.*/
public class AddAppointment implements Initializable {
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
    @FXML
    private ComboBox<Contact> contactCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<Customer> customerIdCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField descriptionTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private DatePicker endDatePicker;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<String> endTimeCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField locationTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private DatePicker startDatePicker;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<String> startTimeCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField titleTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<String> typeCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<User> userIdCombo;

    /**
     * A method that checks if the inputted appointment overlaps with an existing appointment.
     * @throws SQLException
     * @return
     */
    private boolean isCustomerAppointmentOverlap() throws SQLException {
        for (Appointment appointment : AppointmentsQuery.getCustomerIdAppointments(customerIdCombo.getValue().getCustomerId())) {
            if ((appointment.getStart().getYear() == startDatePicker.getValue().getYear() && appointment.getStart().getMonth() == startDatePicker.getValue().getMonth() && appointment.getStart().getDayOfMonth() == startDatePicker.getValue().getDayOfMonth() && appointment.getStart().toLocalTime().isBefore(LocalTime.parse(endTimeCombo.getValue())) && appointment.getEnd().toLocalTime().isAfter(LocalTime.parse(startTimeCombo.getValue()))) || appointment.getEnd().toLocalTime() == LocalTime.parse(endTimeCombo.getValue()) || appointment.getStart().toLocalTime() == LocalTime.parse(startTimeCombo.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * An event to add a new appointment to the appointments table of the database.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException, SQLException {

        LocalDateTime start = LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeCombo.getValue())).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime end = LocalDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeCombo.getValue())).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        LocalDateTime startDisplay = LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeCombo.getValue())).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
        LocalDateTime endDisplay = LocalDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeCombo.getValue())).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();

        String startDayOfWeek = String.valueOf(start.toLocalDate().getDayOfWeek());

        if (startDisplay.atZone(ZoneId.of("America/New_York")).toLocalTime().isBefore(LocalTime.parse("08:00")) || endDisplay.atZone(ZoneId.of("America/New_York")).toLocalTime().isBefore(LocalTime.parse("08:00")) || startDisplay.atZone(ZoneId.of("America/New_York")).toLocalTime().isAfter(LocalTime.parse("22:00")) || endDisplay.atZone(ZoneId.of("America/New_York")).toLocalTime().isAfter(LocalTime.parse("22:00")) || start.toLocalTime() == end.toLocalTime() || startDayOfWeek == "SUNDAY" || startDayOfWeek == "SATURDAY") {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please make sure your selected times are within business hours, 8:00 AM - 10:00 PM EST, and that your selected date is not a weekend.");
            alert.showAndWait();
        }
        else if (isCustomerAppointmentOverlap()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("There is already an existing appointment during that time for that customer.");
            alert.showAndWait();
        }
        else {
            AppointmentsQuery.insert(AppointmentsQuery.generateAppointmentId(), titleTxt.getText(), descriptionTxt.getText(), locationTxt.getText(), typeCombo.getValue(), start, end, customerIdCombo.getValue().getCustomerId(), userIdCombo.getValue().getUserId(), contactCombo.getValue().getContactId());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * An event to display the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * An event that sets the end date to the start date when the start date is selected.
     * @param event
     */
    @FXML
    void onActionSetEndDatePickerValue(ActionEvent event) {
        endDatePicker.setValue(startDatePicker.getValue());
    }

    /**
     * The code that runs when the view is initially displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            customerIdCombo.setItems(CustomersQuery.getAllCustomers());
            userIdCombo.setItems(UsersQuery.getAllUsers());
            contactCombo.setItems(ContactsQuery.getAllContacts());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> types = FXCollections.observableArrayList();

        types.add("Planning Session");
        types.add("De-Briefing");

        typeCombo.setItems(types);

        ObservableList<String> appointmentTimes = FXCollections.observableArrayList();

        LocalTime appointmentTime = LocalTime.MIN.plusHours(0);

        while (!appointmentTimes.contains("23:45")) {
            appointmentTimes.add(String.valueOf(appointmentTime));
            appointmentTime = appointmentTime.plusMinutes(15);
        }

        startTimeCombo.setItems(appointmentTimes);
        endTimeCombo.setItems(appointmentTimes);
    }
}
