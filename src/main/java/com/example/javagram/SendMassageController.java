/**
 * Sample Skeleton for 'sendMassage-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Personal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import repository.MassageRepository;

public class SendMassageController {
    MassageRepository massageRepository = new MassageRepository();
    Personal user = HelloApplication.getUser();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_profile"
    private Button btn_profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="btn_send"
    private Button btn_send; // Value injected by FXMLLoader

    @FXML // fx:id="txtBox"
    private TextArea txtBox; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Text username; // Value injected by FXMLLoader

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("sendMassage-view.fxml");
    }

    @FXML
    void onActionSend(ActionEvent event) throws SQLException, ClassNotFoundException {
        massageRepository.sendMassage(user, txtBox.getText());
        HelloApplication.navigateToUserPage("sendMassage-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'sendMassage-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'sendMassage-view.fxml'.";
        assert btn_send != null : "fx:id=\"btn_send\" was not injected: check your FXML file 'sendMassage-view.fxml'.";
        assert txtBox != null : "fx:id=\"txtBox\" was not injected: check your FXML file 'sendMassage-view.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'sendMassage-view.fxml'.";

        username.setText("Send Massage to : "+user.getP_username());
    }

}
