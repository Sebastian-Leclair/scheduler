package controller;

import DAO.AppointmentsQuery;
import DAO.ContactsQuery;
import DAO.CountriesQuery;
import DAO.CustomersQuery;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import helper.LoginInterface;
import helper.ReportsInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Country;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

/** This class acts as a controller for the Reports view.*/
public class Reports implements Initializable {
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
    private TableColumn<Appointment, Integer> appointmentIdCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<Contact> contactCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableView<Appointment> contactTableView;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private Label countLbl;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> countryCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<Country> countryCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableView<Customer> countryTableView;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol1;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> customerNameCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> divisionCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<String> monthCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> titleCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> typeCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<String> typeCombo;

    /**
     * An event to display the Main view.
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
     * An event to display the count of appointments that have a certain selected month and type.
     * The justification for the lambda expression is that it helps maintain the readability of the code while modifying the label to display the number of appointments that have a selected month and type.
     * @throws SQLException
     * @param event
     */
    @FXML
    void onActionMonthCount(ActionEvent event) throws SQLException {
        // Lambda Expression
        ReportsInterface message = (total) -> "Count: " + total;
        countLbl.setText(message.count(AppointmentsQuery.count(monthCombo.getValue(), typeCombo.getValue())));
    }

    /**
     * An event to populate the contact table view.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionPopulateContactTableView(ActionEvent event) throws SQLException {
        contactTableView.setItems(AppointmentsQuery.getContactAppointments(contactCombo.getValue().getContactId()));

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    /**
     * An event to populate the country table view.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionPopulateCountryTableView(ActionEvent event) throws SQLException {
        countryTableView.setItems(CustomersQuery.getCountryCustomers(countryCombo.getValue().getCountryName()));

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
    }

    /**
     * An event to display the count of appointments with a selected type and month. There is a lambda expression that modifies a label to display the number of appointments that have a selected month and type, and it is useful for maintaining readability of the code.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionTypeCount(ActionEvent event) throws SQLException {
        // Lambda Expression
        ReportsInterface message = (total) -> "Count: " + total;
        countLbl.setText(message.count(AppointmentsQuery.count(monthCombo.getValue(), typeCombo.getValue())));
    }

    /**
     * The code that runs when the view is initially displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> types = FXCollections.observableArrayList();

        types.add("Planning Session");
        types.add("De-Briefing");

        typeCombo.setItems(types);
        typeCombo.setValue("Planning Session");

        ObservableList<String> months = FXCollections.observableArrayList();

        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        monthCombo.setItems(months);
        monthCombo.setValue("January");

        try {
            countryCombo.setItems(CountriesQuery.getAllCountries());
            contactCombo.setItems(ContactsQuery.getAllContacts());

            countLbl.setText("Count: " + AppointmentsQuery.count(monthCombo.getValue(), typeCombo.getValue()));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
