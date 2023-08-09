package controller;

import DAO.AppointmentsQuery;
import DAO.CustomersQuery;
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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class acts as a controller for the Main view.*/
public class Main implements Initializable {
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
    private TableView<Appointment> appointmentTableView;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> contactCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Appointment, String> locationCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableView<Customer> customerTableView;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> addressCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> countryCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, Integer> customerIdCol1;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> customerNameCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> divisionCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> phoneNumberCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TableColumn<Customer, String> postalCodeCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private Label customMessageLbl;

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
    private TableColumn<Appointment, Integer> userIdCol;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private RadioButton allRBtn;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private RadioButton currentMonthRBtn;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private RadioButton currentWeekRBtn;

    /**
     * An event to view all appointments with a radio button.
     * @param event
     * @throws SQLException
     */
    @FXML
    void allAppointmentsSelected(ActionEvent event) throws SQLException {
        appointmentTableView.setItems(AppointmentsQuery.getAllAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * An event to view all appointments during the current month with a radio button.
     * @param event
     * @throws SQLException
     */
    @FXML
    void currentMonthSelected(ActionEvent event) throws SQLException {
        appointmentTableView.setItems(AppointmentsQuery.getCurrentMonthAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * An event to view all appointments during the current week with a radio button.
     * @param event
     * @throws SQLException
     */
    @FXML
    void currentWeekSelected(ActionEvent event) throws SQLException {
        appointmentTableView.setItems(AppointmentsQuery.getCurrentWeekAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * An event to delete the selected appointment.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm that you want to delete the selected appointment.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            customMessageLbl.setText("Appointment ID: " + appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentId() + ", " + appointmentTableView.getSelectionModel().getSelectedItem().getType() + " has been deleted from appointments.");
            AppointmentsQuery.delete(appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentId());
            appointmentTableView.setItems(AppointmentsQuery.getAllAppointments());
        }
    }

    /**
     * An event to delete the selected customer.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm that you want to delete the selected customer.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            customMessageLbl.setText(customerTableView.getSelectionModel().getSelectedItem().getCustomerName() + " has been deleted from customers.");
            AppointmentsQuery.deleteCustomerAppointments(customerTableView.getSelectionModel().getSelectedItem().getCustomerId());
            CustomersQuery.delete(customerTableView.getSelectionModel().getSelectedItem().getCustomerId());
            customerTableView.setItems(CustomersQuery.getAllCustomers());
            appointmentTableView.setItems(AppointmentsQuery.getAllAppointments());
        }
    }

    /**
     * An event to display the AddCustomer view.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionDisplayAddCustomerMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * An event to display the UpdateCustomer view.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionDisplayUpdateCustomerMenu(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/UpdateCustomer.fxml"));
        loader.load();

        UpdateCustomer updateCustomer = loader.getController();
        updateCustomer.sendCustomer(customerTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * An event to display the AddAppointment view.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionDisplayAddAppointmentMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * An event to display the UpdateAppointment view.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionDisplayUpdateAppointmentMenu(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
        loader.load();

        UpdateAppointment updateAppointment = loader.getController();
        updateAppointment.sendAppointment(appointmentTableView.getSelectionModel().getSelectedItem());

        AppointmentsQuery.delete(appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentId());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * An event to display the Reports view.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * The code that runs when the view is initially displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allRBtn.setSelected(true);

        try {
            customerTableView.setItems(CustomersQuery.getAllCustomers());

            customerIdCol1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));

            appointmentTableView.setItems(AppointmentsQuery.getAllAppointments());

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
