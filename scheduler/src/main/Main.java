package main;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This is the Main class that is runs first when the program is launched. */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        // Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();
    }

}