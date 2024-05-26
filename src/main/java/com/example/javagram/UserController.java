/**
 * Sample Skeleton for 'user-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import Model.Personal;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.PersonalRepository;
import repository.PostRepository;

public class UserController {
    Personal user = HelloApplication.getUser();
    Personal personal = HelloApplication.getPersonal();
    PostRepository postRepository = new PostRepository();
    PersonalRepository personalRepository = new PersonalRepository();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_Follow"
    private Button btn_Follow; // Value injected by FXMLLoader

    @FXML // fx:id="btn_Profile"
    private Button btn_Profile; // Value injected by FXMLLoader

    @FXML // fx:id="btn_UnFollow"
    private Button btn_UnFollow; // Value injected by FXMLLoader

    @FXML // fx:id="btn_massage"
    private Button btn_massage; // Value injected by FXMLLoader

    @FXML // fx:id="btn_refresh"
    private Button btn_refresh; // Value injected by FXMLLoader

    @FXML // fx:id="cDate"
    private TableColumn<Post, Timestamp> cDate; // Value injected by FXMLLoader

    @FXML // fx:id="cPost"
    private TableColumn<Post, String> cPost; // Value injected by FXMLLoader

    @FXML // fx:id="followerCounter"
    private Label followerCounter; // Value injected by FXMLLoader

    @FXML // fx:id="followingCounter"
    private Label followingCounter; // Value injected by FXMLLoader

    @FXML // fx:id="postCounter"
    private Label postCounter; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_posts"
    private TableView<Post> tbl_posts; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private Label userName; // Value injected by FXMLLoader

    @FXML
    void onActionFollow(ActionEvent event) throws SQLException, ClassNotFoundException {
        personalRepository.follow(user);
        HelloApplication.navigateToUserPage("user-view.fxml");
    }

    @FXML
    void onActionMassage(ActionEvent event) {
        HelloApplication.navigateToUserPage("sendMassage-view.fxml");
    }

    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("user-view.fxml");
    }

    @FXML
    void onActionUnFollow(ActionEvent event) throws SQLException, ClassNotFoundException {
        personalRepository.unfollow(user);
        HelloApplication.navigateToUserPage("user-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_Follow != null : "fx:id=\"btn_Follow\" was not injected: check your FXML file 'user-view.fxml'.";
        assert btn_Profile != null : "fx:id=\"btn_Profile\" was not injected: check your FXML file 'user-view.fxml'.";
        assert btn_UnFollow != null : "fx:id=\"btn_UnFollow\" was not injected: check your FXML file 'user-view.fxml'.";
        assert btn_massage != null : "fx:id=\"btn_massage\" was not injected: check your FXML file 'user-view.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'user-view.fxml'.";
        assert cDate != null : "fx:id=\"cDate\" was not injected: check your FXML file 'user-view.fxml'.";
        assert cPost != null : "fx:id=\"cPost\" was not injected: check your FXML file 'user-view.fxml'.";
        assert followerCounter != null : "fx:id=\"followerCounter\" was not injected: check your FXML file 'user-view.fxml'.";
        assert followingCounter != null : "fx:id=\"followingCounter\" was not injected: check your FXML file 'user-view.fxml'.";
        assert postCounter != null : "fx:id=\"postCounter\" was not injected: check your FXML file 'user-view.fxml'.";
        assert tbl_posts != null : "fx:id=\"tbl_posts\" was not injected: check your FXML file 'user-view.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'user-view.fxml'.";

        List<Post> posts = postRepository.getPost(user);

        PropertyValueFactory<Post, String> posttext = new PropertyValueFactory<>("text");
        PropertyValueFactory<Post, Timestamp> postdate = new PropertyValueFactory<>("time");

        cPost.setCellValueFactory(posttext);
        cDate.setCellValueFactory(postdate);

        tbl_posts.setItems(FXCollections.observableList(posts));

        postCounter.setText(String.valueOf(posts.size()));
        userName.setText(user.getP_username());
        followerCounter.setText(String.valueOf(personalRepository.getFollowers(user).size()));
        followingCounter.setText(String.valueOf(personalRepository.getFollowings(user).size()));

        tbl_posts.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                Post selectedPost = tbl_posts.getSelectionModel().getSelectedItem();
                if (selectedPost!=null){
                    Post post = new Post(selectedPost.getName(), selectedPost.getText(),
                            selectedPost.getPicture(), selectedPost.getTime());
                    HelloApplication.setPost(post);
                    HelloApplication.navigateToUserPage("userpost-view.fxml");
                }
            }
        });
    }

}
