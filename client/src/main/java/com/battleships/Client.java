package com.battleships;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    private WindowScalling windowScalling;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        windowScalling = new WindowScalling();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start_window/start_window.fxml"));
        Parent root = loader.load();
        primaryStage.titleProperty().bind(Translator.createStringBinding("battleships_title"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        windowScalling.enableFor(scene, root);
    }
}