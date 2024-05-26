/**
 * Sample Skeleton for 'login-view.fxml' Controller Class
 */

package com.example.javagram;

import Model.Personal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repository.PersonalRepository;

import java.sql.SQLException;

public class LoginController {

    @FXML // fx:id="btn_login"
    private Button btn_login; // Value injected by FXMLLoader

    @FXML // fx:id="btn_signup"
    private Button btn_signup; // Value injected by FXMLLoader

    @FXML // fx:id="txt_password"
    private PasswordField txt_password; // Value injected by FXMLLoader

    @FXML // fx:id="txt_username"
    private TextField txt_username; // Value injected by FXMLLoader

    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login failed");
        alert.setHeaderText(null);
        if (txt_password.getText().trim().isEmpty() || txt_username.getText().trim().isEmpty()){
            alert.setContentText("Pleas fill necessary field !");
            alert.showAndWait();
        }else{
            PersonalRepository personalRepository = new PersonalRepository();
            Personal login = personalRepository.getPass(txt_username.getText(), txt_password.getText());
            if (login!=null){
                alert.setContentText("Welcome to JavaGram !");
                alert.showAndWait();
                HelloApplication.setPersonal(login);
                HelloApplication.navigateToUserPage("profile-view.fxml");
            }else {
                alert.setContentText("Wrong user/pass !");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void onActionSignup(ActionEvent event) {
        HelloApplication.navigateToUserPage("signup-view.fxml");
    }

}
