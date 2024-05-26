/**
 * Sample Skeleton for 'following-view.fxml' Controller Class
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

public class FollowingController {
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

    @FXML // fx:id="cFollowing"
    private TableColumn<Personal, String> cFollowing; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_following"
    private TableView<Personal> tbl_following; // Value injected by FXMLLoader

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("following-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'following-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'following-view.fxml'.";
        assert cFollowing != null : "fx:id=\"cFollowing\" was not injected: check your FXML file 'following-view.fxml'.";
        assert tbl_following != null : "fx:id=\"tbl_following\" was not injected: check your FXML file 'following-view.fxml'.";

        List<Personal> followings = personalRepository.getFollowings(personal);
        PropertyValueFactory<Personal, String> usernameF = new PropertyValueFactory<>("p_username");
        cFollowing.setCellValueFactory(usernameF);
        cFollowing.setText("Followings : "+ followings.size());
        tbl_following.setItems(FXCollections.observableList(followings));

        tbl_following.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                Personal selectedPersonal = tbl_following.getSelectionModel().getSelectedItem();
                if(selectedPersonal!=null){
                    HelloApplication.setUser(selectedPersonal);
                    HelloApplication.navigateToUserPage("user-view.fxml");
                }
            }
        });
    }

}
