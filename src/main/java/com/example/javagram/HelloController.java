/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

package com.example.javagram;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class HelloController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bar"
    private Label bar; // Value injected by FXMLLoader

    @FXML // fx:id="prog"
    private ProgressBar prog; // Value injected by FXMLLoader

    @FXML // fx:id="welcomeText"
    private Label welcomeText; // Value injected by FXMLLoader

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        // Create a background task to simulate progress
        Task<Void> task = new Task<Void>() {
            private volatile boolean running = true;
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(50);
                    double progress = (i + 1) / 100.0; // Calculate progress as a fraction
                    // Update UI components on the JavaFX Application Thread
                    int finalI = i;
                    Platform.runLater(() -> {
                        prog.setProgress(progress);
                        bar.setText(Integer.toString(finalI) + "%");
                    });
                    if(progress>=1){
                        running = false;
                    }
                }

                return null;
            }


        };
//
        if (!task.isRunning()){
            HelloApplication.navigateToUserPage("login-view.fxml");
        }

    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bar != null : "fx:id=\"bar\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert prog != null : "fx:id=\"prog\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert welcomeText != null : "fx:id=\"welcomeText\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
