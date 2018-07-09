package com.battleships;

import com.battleships.start_window.window_scaling.ScreenSize;
import com.battleships.windowScalling.WindowScalling;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

/**
 * Responsible for running client application
 */

public class Client extends Application {
    private static final Logger logger = LogManager.getLogger(Client.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts client application
     *
     * @param primaryStage - the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        enableAppTitleTranslation(primaryStage);
        runApp(primaryStage);
    }

    private void enableAppTitleTranslation(Stage primaryStage) {
        primaryStage.titleProperty().bind(Translator.createStringBinding("battleships_title"));
    }

    private void runApp(Stage primaryStage) {
        Optional<Parent> rootFxmlOptional = tryToGetRootFxmlOptional();
        if (rootFxmlOptional.isPresent()) {
            setScalingScene(primaryStage, rootFxmlOptional.get());
            addQuitWithEscapeKeyHandling(primaryStage);
            primaryStage.show();
            logger.info(LogMessages.MAIN_FXML_VIEW_LOADED_APP_STARTED);
        } else {
            logger.error(LogMessages.NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW);
        }
    }

    private void addQuitWithEscapeKeyHandling(Stage primaryStage) {
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                logger.info(LogMessages.QUIT_WITH_ESCAPE);
                Platform.exit();
            }
        });
    }

    private Optional<Parent> tryToGetRootFxmlOptional() {
        String mainFxmlPath = "start_window/start_window.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(mainFxmlPath));
        return tryToLoadRoot(mainFxmlPath, loader);
    }

    private Optional<Parent> tryToLoadRoot(String mainFxmlPath, FXMLLoader loader) {
        Optional<Parent> root = Optional.empty();
        try {
            root = Optional.of(loader.load());
        } catch (IOException e) {
            logger.error(String.format(LogMessages.NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW_FROM_PATH, mainFxmlPath), e.getMessage());
        }
        return root;
    }

    private void setScalingScene(Stage primaryStage, Parent root) {
        ScreenSize screenSize = new ScreenSize(Screen.getPrimary());
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        enableScaling(root, scene);
    }

    private void enableScaling(Parent root, Scene scene) {
        WindowScalling windowScalling = new WindowScalling();
        windowScalling.enableFor(scene, root);
    }
}