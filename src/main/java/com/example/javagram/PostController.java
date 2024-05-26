/**
 * Sample Skeleton for 'post-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import Model.Personal;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.PostRepository;

public class PostController {
    PostRepository postRepository = new PostRepository();
    Personal personal = HelloApplication.getPersonal();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_delete"
    private Button btn_delete; // Value injected by FXMLLoader

    @FXML // fx:id="btn_new"
    private Button btn_new; // Value injected by FXMLLoader

    @FXML // fx:id="btn_profile"
    private Button btn_profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="cText"
    private TableColumn<Post, String> cTextt; // Value injected by FXMLLoader

    @FXML // fx:id="cTime"
    private TableColumn<Post, Timestamp> cTimee; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_post"
    private TableView<Post> tbl_post; // Value injected by FXMLLoader

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        int select = tbl_post.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(select!=-1){
            boolean b = postRepository.deletPost(tbl_post.getSelectionModel().getSelectedItem());
            if (b){
                HelloApplication.navigateToUserPage("post-view.fxml");
            }else
            {
                alert.setTitle("Process failed");
                alert.setHeaderText(null);
                alert.setContentText("delete failed :(");
                alert.showAndWait();
            }
        }else {
            alert.setContentText("Pleas choose on of post !");
        }
    }

    @FXML
    void onActionNew(ActionEvent event) {
        HelloApplication.navigateToUserPage("newpost-view.fxml");
    }

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        HelloApplication.navigateToUserPage("post-view.fxml");
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_delete != null : "fx:id=\"btn_delete\" was not injected: check your FXML file 'post-view.fxml'.";
        assert btn_new != null : "fx:id=\"btn_new\" was not injected: check your FXML file 'post-view.fxml'.";
        assert btn_profile != null : "fx:id=\"btn_profile\" was not injected: check your FXML file 'post-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'post-view.fxml'.";
        assert cTextt != null : "fx:id=\"cText\" was not injected: check your FXML file 'post-view.fxml'.";
        assert cTimee != null : "fx:id=\"cTime\" was not injected: check your FXML file 'post-view.fxml'.";
        assert tbl_post != null : "fx:id=\"tbl_post\" was not injected: check your FXML file 'post-view.fxml'.";
//        List<Post> posts = postRepository.getText(personal);
//
//        tbl_post.setItems(FXCollections.observableList(posts));
//
//        cText.setCellValueFactory(new PropertyValueFactory<>("text"));
//        cTime.setCellValueFactory(new PropertyValueFactory<>("time"));
//
//        tbl_post.getColumns().add(cText);
//        tbl_post.getColumns().add(cTime);
        List<Post> posts = postRepository.getPost(personal);


        PropertyValueFactory<Post, String> textFactory = new PropertyValueFactory<>("text");
        PropertyValueFactory<Post, Timestamp> timeFactory = new PropertyValueFactory<>("time");

        cTextt.setCellValueFactory(textFactory);
        cTimee.setCellValueFactory(timeFactory);


        tbl_post.setItems(FXCollections.observableList(posts));
        tbl_post.setVisible(true);


    }

}
