package com.battleships;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getResource("start_window/start_window.fxml").toString());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start_window/start_window.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("battleships");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
