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

/** This class acts as a controller for the AddCustomer view.*/
public class AddCustomer implements Initializable {
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
     * An event to add a new customer to the customers table of the database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws SQLException, IOException {
        if (nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || postalCodeTxt.getText().isEmpty() || phoneNumberTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please make sure that none of the text fields are left blank.");
            alert.showAndWait();
        }
        else {
            CustomersQuery.insert(CustomersQuery.generateCustomerId(), nameTxt.getText(), addressTxt.getText(), postalCodeTxt.getText(), phoneNumberTxt.getText(), divisionCombo.getSelectionModel().getSelectedItem().getDivisionId());

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
