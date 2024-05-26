/**
 * Sample Skeleton for 'newpost-view.fxml' Controller Class
 */

package com.example.javagram;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import repository.PostRepository;

public class NewPostController {

    Image image = null;
    Blob pic = null;
    Path path = null;
    String text = null;
    String username = HelloApplication.getPersonal().getP_username();
    Post post = null;
    PostRepository postRepository = new PostRepository();



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_chooseimg"
    private Button btn_chooseimg; // Value injected by FXMLLoader

    @FXML // fx:id="btn_share"
    private Button btn_share; // Value injected by FXMLLoader

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_refresh;

    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader

    @FXML // fx:id="txt_post"
    private TextArea txt_post; // Value injected by FXMLLoader

    @FXML
    void onActionChooseimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set title for the file chooser dialog
        fileChooser.setTitle("Open File");

        // Set initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show open dialog
        File selectedFile = fileChooser.showOpenDialog( new Stage() );

        if (selectedFile != null) {
            image = new Image(selectedFile.toURI().toString());
            img.setImage(image);

            path = selectedFile.toPath();

            // Optionally, set size constraints for the ImageView
            img.setFitWidth(210);
            img.setFitHeight(200);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login failed");
            alert.setHeaderText(null);
            alert.setContentText("No choose !");
        }

    }

    @FXML
    void onActionShare(ActionEvent event) throws SQLException, ClassNotFoundException, FileNotFoundException {
//        pic = fileInputStream;
        text = txt_post.getText();
        post = new Post(username,text, path);
        postRepository.insertPost(post);
    }

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("newpost-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn_chooseimg != null : "fx:id=\"btn_chooseimg\" was not injected: check your FXML file 'newpost-view.fxml'.";
        assert btn_share != null : "fx:id=\"btn_share\" was not injected: check your FXML file 'newpost-view.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'newpost-view.fxml'.";
        assert txt_post != null : "fx:id=\"txt_post\" was not injected: check your FXML file 'newpost-view.fxml'.";

    }

}
