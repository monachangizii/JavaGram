/**
 * Sample Skeleton for 'follower-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Model.Personal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.PersonalRepository;

public class FollowerController {
    PersonalRepository personalRepository = new PersonalRepository();
    Personal personal = HelloApplication.getPersonal();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_profile"
    private Button btn_profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="cFollowers"
    private TableColumn<Personal, String> cFollowers; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_followers"
    private TableView<Personal> tbl_followers; // Value injected by FXMLLoader

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("follower-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'follower-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'follower-view.fxml'.";
        assert cFollowers != null : "fx:id=\"cFollowers\" was not injected: check your FXML file 'follower-view.fxml'.";
        assert tbl_followers != null : "fx:id=\"tbl_followers\" was not injected: check your FXML file 'follower-view.fxml'.";

        List<Personal> followers = personalRepository.getFollowers(personal);
        PropertyValueFactory<Personal, String> usernameF = new PropertyValueFactory<>("p_username");
        cFollowers.setCellValueFactory(usernameF);
        cFollowers.setText("Followers : "+ followers.size());
        tbl_followers.setItems(FXCollections.observableList(followers));
    }

}
