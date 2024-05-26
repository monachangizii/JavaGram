/**
 * Sample Skeleton for 'signup-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repository.PersonalRepository;

public class SignupController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_signup"
    private Button btn_signup; // Value injected by FXMLLoader

    @FXML // fx:id="txt_country"
    private TextField txt_country; // Value injected by FXMLLoader

    @FXML // fx:id="txt_email"
    private TextField txt_email; // Value injected by FXMLLoader

    @FXML // fx:id="txt_lastName"
    private TextField txt_lastName; // Value injected by FXMLLoader

    @FXML // fx:id="txt_name"
    private TextField txt_name; // Value injected by FXMLLoader

    @FXML // fx:id="txt_password"
    private TextField txt_password; // Value injected by FXMLLoader

    @FXML // fx:id="txt_userName"
    private TextField txt_userName; // Value injected by FXMLLoader

    @FXML
    void onActionsignup(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login failed");
        alert.setHeaderText(null);
        if(
                txt_name.getText().trim().isEmpty() ||
                txt_lastName.getText().trim().isEmpty() ||
                txt_userName.getText().trim().isEmpty() ||
                txt_password.getText().trim().isEmpty() ||
                txt_email.getText().trim().isEmpty() ||
                txt_country.getText().trim().isEmpty()
        )
        {
            alert.setContentText("Please fill the necessary field !");
            alert.showAndWait();
        }else
        {
            PersonalRepository personalRepository = new PersonalRepository();
            boolean result = personalRepository.signUp(
                    txt_name.getText().trim(),
                    txt_lastName.getText().trim(),
                    txt_userName.getText().trim(),
                    txt_password.getText().trim(),
                    txt_email.getText().trim(),
                    txt_country.getText().trim()
            );
            if(result){
                alert.setContentText("Welcome to JavaGram !");
                alert.showAndWait();
            }else
            {
                alert.setContentText("process failed:( !");
                alert.showAndWait();
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn_signup != null : "fx:id=\"btn_signup\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_country != null : "fx:id=\"txt_country\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_lastName != null : "fx:id=\"txt_lastName\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'signup-view.fxml'.";
        assert txt_userName != null : "fx:id=\"txt_userName\" was not injected: check your FXML file 'signup-view.fxml'.";

    }

}
