package com.example.javafxlogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for the login screen
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafxlogin/sample.fxml"));

        // Set up the scene and stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // This will launch the JavaFX application
    }
}
