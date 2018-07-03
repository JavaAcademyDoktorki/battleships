package com.battleships;

import com.battleships.windowScalling.WindowScalling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class Client extends Application {
    private WindowScalling windowScalling;
    private static final Logger logger = LogManager.getLogger(Client.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        enableAppTitleTranslation(primaryStage);
        setSceneRunAppIfViewLoaded(primaryStage);
    }

    private void enableAppTitleTranslation(Stage primaryStage) {
        primaryStage.titleProperty().bind(Translator.createStringBinding("battleships_title"));
    }

    private void setSceneRunAppIfViewLoaded(Stage primaryStage) {
        Optional<Parent> rootFxml = tryToGetRootFxml();
        if (rootFxml.isPresent()) {
            setScalingScene(primaryStage, rootFxml.get());
            primaryStage.show();
        }
        else{
            logger.error(LogMessages.NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW);
        }
    }

    private Optional<Parent> tryToGetRootFxml() {
        String mainFxmlPath = "start_window/start_window.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(mainFxmlPath));
        return tryToLoadRoot(mainFxmlPath, loader);
    }

    private Optional<Parent> tryToLoadRoot(String mainFxmlPath, FXMLLoader loader) {
        Optional <Parent> root = Optional.empty();
        try {
            root = Optional.of(loader.load());
        } catch (IOException e) {
            logger.error(String.format(LogMessages.NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW_FROM_PATH, mainFxmlPath), e.getMessage());
        }
        finally {
            return root;
        }
    }

    private void setScalingScene(Stage primaryStage, Parent root) {
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        enableScaling(root, scene);
    }

    private void enableScaling(Parent root, Scene scene) {
        windowScalling = new WindowScalling();
        windowScalling.enableFor(scene, root);
    }
}