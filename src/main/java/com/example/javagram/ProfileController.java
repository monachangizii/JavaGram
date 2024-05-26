/**
 * Sample Skeleton for 'profile-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Model.Personal;
import Model.Post;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.w3c.dom.events.MouseEvent;
import repository.PostRepository;

public class ProfileController {

    Personal personal = HelloApplication.getPersonal();
    PostRepository postRepository = new PostRepository();
    Post post = null;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_direct"
    private Button btn_direct; // Value injected by FXMLLoader

    @FXML // fx:id="btn_followers"
    private Button btn_followers; // Value injected by FXMLLoader

    @FXML // fx:id="btn_following"
    private Button btn_following; // Value injected by FXMLLoader

    @FXML // fx:id="btn_posts"
    private Button btn_posts; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="btn_search"
    private Button btn_search; // Value injected by FXMLLoader

    @FXML // fx:id="btn_user"
    private Button btn_user; // Value injected by FXMLLoader


    @FXML // fx:id="cTime"
    private TableColumn<Post, Timestamp> cTime; // Value injected by FXMLLoader

    @FXML // fx:id="cUser"
    private TableColumn<Post, String> cUser; // Value injected by FXMLLoader


    @FXML // fx:id="cText"
    private TableColumn<Post, String> cText; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_user"
    private TableView<Post> tbl_user; // Value injected by FXMLLoader

    @FXML
    private Label time;

    @FXML
    void onActionFollowing(ActionEvent event) {
        HelloApplication.navigateToUserPage("following-view.fxml");
    }

    @FXML
    void onActionFollower(ActionEvent event) {
        HelloApplication.navigateToUserPage("follower-view.fxml");
    }
    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionPost(ActionEvent event) {
        HelloApplication.navigateToUserPage("post-view.fxml");
    }

    @FXML
    void onActionSearch(ActionEvent event) {
        HelloApplication.navigateToUserPage("search-view.fxml");
    }
    private void updateTime(Label label) {
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        label.setText(formattedTime);
    }

    @FXML
    void onActionMassage(ActionEvent event) {
        HelloApplication.navigateToUserPage("massages-view.fxml");
    }
    void showtime(){
        updateTime(time); // Update label with current time

        // Create a Timeline to update the time every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateTime(time))
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play();
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_direct != null : "fx:id=\"btn_direct\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_followers != null : "fx:id=\"btn_followers\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_following != null : "fx:id=\"btn_following\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_posts != null : "fx:id=\"btn_posts\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_search != null : "fx:id=\"btn_search\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert btn_user != null : "fx:id=\"btn_user\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert cTime != null : "fx:id=\"cTime\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert cUser != null : "fx:id=\"cUser\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert cText != null : "fx:id=\"ctext\" was not injected: check your FXML file 'profile-view.fxml'.";
        assert tbl_user != null : "fx:id=\"tbl_user\" was not injected: check your FXML file 'profile-view.fxml'.";

        showtime();
        btn_user.setText(personal.getP_username());

        List<Post> posts = postRepository.getPosts();

        PropertyValueFactory<Post, String> usernameF = new PropertyValueFactory<>("name");
        PropertyValueFactory<Post, java.sql.Timestamp> timeF = new PropertyValueFactory<>("time");
        PropertyValueFactory<Post, String> textF = new PropertyValueFactory<>("text");

        cUser.setCellValueFactory(usernameF);
        cTime.setCellValueFactory(timeF);
        cText.setCellValueFactory(textF);

        tbl_user.setItems(FXCollections.observableList(posts));
        tbl_user.setVisible(true);

        tbl_user.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                Post selectedPost = tbl_user.getSelectionModel().getSelectedItem();
                if (selectedPost!=null){
                    post = new Post(selectedPost.getName(), selectedPost.getText(),
                            selectedPost.getPicture(), selectedPost.getTime());
                    HelloApplication.setPost(post);
                    HelloApplication.navigateToUserPage("userpost-view.fxml");
                }
            }
        });
    }

}
