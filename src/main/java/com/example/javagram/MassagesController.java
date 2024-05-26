/**
 * Sample Skeleton for 'Massages-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import Model.Massage;
import Model.Personal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.MassageRepository;

public class MassagesController {
    Personal personal = HelloApplication.getPersonal();
    Massage massage = null;
    MassageRepository massageRepository = new MassageRepository();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_profile"
    private Button btn_profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="cDate"
    private TableColumn<Massage, Timestamp> cDate; // Value injected by FXMLLoader

    @FXML // fx:id="cFrom"
    private TableColumn<Massage, String> cFrom; // Value injected by FXMLLoader

    @FXML // fx:id="cMSG"
    private TableColumn<Massage, String> cMSG; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_massages"
    private TableView<Massage> tbl_massages; // Value injected by FXMLLoader

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("massages-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'Massages-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'Massages-view.fxml'.";
        assert cDate != null : "fx:id=\"cDate\" was not injected: check your FXML file 'Massages-view.fxml'.";
        assert cFrom != null : "fx:id=\"cFrom\" was not injected: check your FXML file 'Massages-view.fxml'.";
        assert cMSG != null : "fx:id=\"cMSG\" was not injected: check your FXML file 'Massages-view.fxml'.";
        assert tbl_massages != null : "fx:id=\"tbl_massages\" was not injected: check your FXML file 'Massages-view.fxml'.";

        List<Massage> massages = massageRepository.getMassages(personal);

        PropertyValueFactory<Massage, String> userF = new PropertyValueFactory<>("user1");
        PropertyValueFactory<Massage, String> msgF = new PropertyValueFactory<>("msg");
        PropertyValueFactory<Massage, Timestamp> dateF = new PropertyValueFactory<>("date");

        cFrom.setCellValueFactory(userF);
        cMSG.setCellValueFactory(msgF);
        cDate.setCellValueFactory(dateF);

        tbl_massages.setItems(FXCollections.observableList(massages));

        tbl_massages.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                massage = tbl_massages.getSelectionModel().getSelectedItem();
                HelloApplication.setUser(new Personal(massage.getUser1()));
                HelloApplication.navigateToUserPage("sendMassage-view.fxml");
            }
        });

    }

}
