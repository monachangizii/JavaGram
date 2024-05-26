/**
 * Sample Skeleton for 'search-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Model.Personal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.PersonalRepository;

public class SearchController {
    PersonalRepository personalRepository = new PersonalRepository();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_profile"
    private Button btn_profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="btn_search"
    private Button btn_search; // Value injected by FXMLLoader

    @FXML // fx:id="cProfiles"
    private TableColumn<String, String> cProfiles; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_profile"
    private TableView<String> tbl_profile; // Value injected by FXMLLoader

    @FXML // fx:id="txt_username"
    private TextField txt_username; // Value injected by FXMLLoader

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefres(ActionEvent event) {
        HelloApplication.navigateToUserPage("search-view.fxml");
    }

    @FXML
    void onActionSearch(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<String> usernames = FXCollections.observableArrayList();
        tbl_profile.setItems(usernames);

        List<String> personals = personalRepository.search(txt_username.getText());
        cProfiles.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tbl_profile.setItems(FXCollections.observableList(personals));

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'search-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'search-view.fxml'.";
        assert btn_search != null : "fx:id=\"btn_search\" was not injected: check your FXML file 'search-view.fxml'.";
        assert cProfiles != null : "fx:id=\"cProfiles\" was not injected: check your FXML file 'search-view.fxml'.";
        assert tbl_profile != null : "fx:id=\"tbl_profile\" was not injected: check your FXML file 'search-view.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'search-view.fxml'.";

        tbl_profile.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                String selectedPersonal = tbl_profile.getSelectionModel().getSelectedItem();
                if(selectedPersonal!=null){
                    Personal personal = new Personal(selectedPersonal);
                    HelloApplication.setUser(personal);
                    HelloApplication.navigateToUserPage("user-view.fxml");
                }
            }
        });
    }

}
