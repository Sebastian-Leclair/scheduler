package controller;

import DAO.CountriesQuery;
import DAO.CustomersQuery;
import DAO.FirstLevelDivisionsQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class acts as a controller for the UpdateCustomer view.*/
public class UpdateCustomer implements Initializable {
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
    private TextField addressTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<Country> countryCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField customerIdTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField nameTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField phoneNumberTxt;

    /**
     * An element that is part of the GUI.
     */
    @FXML
    private TextField postalCodeTxt;

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
     * An event to update a customer in the database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException {

        if (nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || postalCodeTxt.getText().isEmpty() || phoneNumberTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please make sure that none of the text fields are left blank.");
            alert.showAndWait();
        }
        else {
            CustomersQuery.update(Integer.parseInt(customerIdTxt.getText()), nameTxt.getText(), addressTxt.getText(), postalCodeTxt.getText(), phoneNumberTxt.getText(), divisionCombo.getSelectionModel().getSelectedItem().getDivisionId());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * An event to display the division combo box options based on the country selection.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onClickedDisplayDivisionCombo(MouseEvent event) throws SQLException {
        if(countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 1){
            divisionCombo.setItems(FirstLevelDivisionsQuery.getUnitedStatesFirstLevelDivisions());
        }
        else if(countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 2) {
            divisionCombo.setItems(FirstLevelDivisionsQuery.getUnitedKingdomFirstLevelDivisions());
        }
        else if(countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 3) {
            divisionCombo.setItems(FirstLevelDivisionsQuery.getCanadaFirstLevelDivisions());
        }
    }

    /**
     * A method to send the customer information to the Main view.
     * @param customer
     * @throws SQLException
     */
    public void sendCustomer(Customer customer) throws SQLException {
        customerIdTxt.setText(String.valueOf(customer.getCustomerId()));
        nameTxt.setText(customer.getCustomerName());
        addressTxt.setText(customer.getAddress());
        postalCodeTxt.setText(customer.getPostalCode());
        phoneNumberTxt.setText(customer.getPhoneNumber());
        countryCombo.setValue(CountriesQuery.getCustomerCountry(customer.getCountryName()));
        divisionCombo.setValue(FirstLevelDivisionsQuery.getCustomerDivision(customer.getDivisionName()));
    }

    /**
     * The code that runs when the view is initially displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countryCombo.setItems(CountriesQuery.getAllCountries());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
