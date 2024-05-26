package com.example.javagram;

import Model.Personal;
import Model.Post;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;
    public static Personal personal;
    public static Personal user;

    public static Personal getUser() {
        return user;
    }

    public static void setUser(Personal user) {
        HelloApplication.user = user;
    }

    public static Post post;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        HelloApplication.primaryStage = primaryStage;
    }

    public static Post getPost() {
        return post;
    }

    public static void setPost(Post post) {
        HelloApplication.post = post;
    }

    public static Personal getPersonal() {
        return personal;
    }

    public static void setPersonal(Personal personal) {
        HelloApplication.personal = personal;
    }

    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 430, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void navigateToUserPage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(path));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloApplication.launch(args);
    }
}