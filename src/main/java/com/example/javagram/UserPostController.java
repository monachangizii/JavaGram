/**
 * Sample Skeleton for 'userpost-view.fxml' Controller Class
 */

package com.example.javagram;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Model.Comment;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repository.CommentRepository;
import repository.LikeRepository;
import repository.PostRepository;

public class UserPostController {
    Post post = HelloApplication.getPost();
    Image image =null;
    PostRepository postRepository = new PostRepository();
    CommentRepository commentRepository =new CommentRepository();

    LikeRepository likeRepository = new LikeRepository();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_like"
    private Button btn_like; // Value injected by FXMLLoader

    @FXML // fx:id="btn_send"
    private Button btn_send; // Value injected by FXMLLoader

    @FXML // fx:id="cComment"
    private TableColumn<Comment, String> cComment; // Value injected by FXMLLoader

    @FXML // fx:id="cSender"
    private TableColumn<Comment, String> cSender; // Value injected by FXMLLoader

    @FXML // fx:id="commentCounter"
    private Label commentCounter; // Value injected by FXMLLoader

    @FXML // fx:id="likeCounter"
    private Label likeCounter; // Value injected by FXMLLoader

    @FXML // fx:id="shareTime"
    private Label shareTime; // Value injected by FXMLLoader

    @FXML // fx:id="tbl_comment"
    private TableView<Comment> tbl_comment; // Value injected by FXMLLoader

    @FXML // fx:id="textBox"
    private TextArea commentBox; // Value injected by FXMLLoader

    @FXML
    private ImageView post_img;
    @FXML // fx:id="post_txt"
    private Label post_txt; // Value injected by FXMLLoader
    @FXML
    void onActionRefresh(ActionEvent event) {
        HelloApplication.navigateToUserPage("userpost-view.fxml");
    }
    @FXML
    void onActionProfile(ActionEvent event) {
        HelloApplication.navigateToUserPage("profile-view.fxml");
    }
    @FXML
    void onActionLike(ActionEvent event) throws SQLException, ClassNotFoundException {
        likeRepository.setLike(post);
        HelloApplication.navigateToUserPage("userpost-view.fxml");

    }

    @FXML
    void onActionSend(ActionEvent event) throws SQLException, ClassNotFoundException {
        commentRepository.setComment(post, commentBox.getText());
        HelloApplication.navigateToUserPage("userpost-view.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert btn_like != null : "fx:id=\"btn_like\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert btn_send != null : "fx:id=\"btn_send\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert cComment != null : "fx:id=\"cComment\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert cSender != null : "fx:id=\"cSender\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert commentCounter != null : "fx:id=\"commentCounter\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert likeCounter != null : "fx:id=\"likeCounter\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert shareTime != null : "fx:id=\"shareTime\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert tbl_comment != null : "fx:id=\"tbl_comment\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert commentBox != null : "fx:id=\"textBox\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert post_img != null : "fx:id=\"post_img\" was not injected: check your FXML file 'userpost-view.fxml'.";
        assert post_txt != null : "fx:id=\"post_txt\" was not injected: check your FXML file 'userpost-view.fxml'.";

        post_txt.setText(post.getText());

        likeCounter.setText(String.valueOf(postRepository.getLike(post)));

        commentCounter.setText(String.valueOf(postRepository.getComment(post)));

        shareTime.setText(post.getTime().toString());

        InputStream img = post.getPicture().getBinaryStream();
        image = new Image(img);
        post_img.setImage(image);

        List<Comment> comments = commentRepository.getComments(post);

        PropertyValueFactory<Comment, String> senderF = new PropertyValueFactory<>("username2");
        PropertyValueFactory<Comment, String> commentF = new PropertyValueFactory<>("text");

        cSender.setCellValueFactory(senderF);
        cComment.setCellValueFactory(commentF);

        tbl_comment.setItems(FXCollections.observableList(comments));
        tbl_comment.setVisible(true);

    }

}
