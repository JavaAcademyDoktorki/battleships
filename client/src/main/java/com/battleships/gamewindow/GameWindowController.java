package com.battleships.gamewindow;

import com.battleships.LogMessages;
import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.shot.Shot;
import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.HitMastField;
import com.battleships.gamewindow.services.BoardService;
import com.battleships.gamewindow.board.BoardGridPanes;
import com.battleships.shot.Coordinate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameWindowController {
    @FXML
    private GridPane playerGridPaneBoard;
    @FXML
    private GridPane opponentGridPaneBoard;
    @FXML
    private Label readyLabel;
    @FXML
    private Label yourBoardLabel;
    @FXML
    private Label opponentBoardLabel;
    @FXML
    private Button randomShipPlacementButton;
    @FXML
    private Button readyToPlayButton;
    @FXML
    private Label turnLabel;

    private BoardService boardService;
    private final static Logger logger = LogManager.getLogger(Connection.class);

    public void initialize() {
        initListeners();
        bindGUIComponents();
        initBoardService(this::shot);
    }

    private void initListeners() {
        initListenerForTextInfoAboutGameReadiness();
        initListenerForTextInfoAboutPlayerTurn();
    }

    private void initListenerForTextInfoAboutPlayerTurn() {
        turnLabel.textProperty().bind(Translator.createStringBinding("not_your_turn"));
        Connection.INSTANCE.playerActiveProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                turnLabel.textProperty().bind(Translator.createStringBinding("your_turn"));
            } else {
                turnLabel.textProperty().bind(Translator.createStringBinding("not_your_turn"));
            }
        });
        turnLabel.setVisible(false);
    }

    private void initListenerForTextInfoAboutGameReadiness() {
        readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
        Connection.INSTANCE.playerReadyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                readyLabel.textProperty().bind(Translator.createStringBinding("ready_to_play_label"));
            } else {
                readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
            }
        });
    }

    private void bindGUIComponents() {
        yourBoardLabel.textProperty().bind(Translator.createStringBinding("your_board"));
        opponentBoardLabel.textProperty().bind(Translator.createStringBinding("opponent_board"));

        randomShipPlacementButton.textProperty().bind(Translator.createStringBinding("random_ship_placement"));
        readyToPlayButton.textProperty().bind(Translator.createStringBinding("ready_to_play"));
        readyToPlayButton.disableProperty().bind(Connection.INSTANCE.playerActiveProperty().not());
    }

    private void initBoardService(EventHandler<ActionEvent> shotEvent) {
        BoardSize boardSize = new BoardSize(10, 10);
        boardService = new BoardService(boardSize);

        BoardGridPanes boardGridPanes = new BoardGridPanes(playerGridPaneBoard, opponentGridPaneBoard);
        boardService.initBoards(boardGridPanes, shotEvent);
    }

    private void shot(ActionEvent event) {
        BoardField clickedButton = (BoardField) event.getSource();
        Coordinate[] coordinates = new Coordinate[]{clickedButton.getCoordinate()};
        clickedButton.refreshColor(); // TODO 24/07/18 damian -  is it neeed

        Shot shot = new Shot(coordinates);
        sendShootMessageToServer(shot);
        makePlayerInactiveAndUnreadyAfterShoot();

        // TODO 24/07/18 damian - AFTER SERVER RESPONS... DO SOMETHING - test method
        boardService.onShootOpponentMessageRecieve(coordinates[0], new HitMastField(coordinates[0]));

        logAllShootCoordinates(shot);
    }

    private void logAllShootCoordinates(Shot shot) {
        for (Coordinate coordinate:shot.getCoordinates()) {
            logger.info(String.format(LogMessages.FIRED_SHOT_ON, coordinate.getRow(), coordinate.getColumn()));
        }
    }

    private void sendShootMessageToServer(Shot shot) {
        Connection.INSTANCE.sendToServer(new Message(CommandType.SHOT, shot));
    }

    private void makePlayerInactiveAndUnreadyAfterShoot() {
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(false));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(false));
    }

    public void placeShipsRandomly(Event event) {
        boardService.createNewRandomConfig(playerGridPaneBoard);
        logger.info(LogMessages.SHIP_PLACED);
    }

    public void confirmReady(ActionEvent event) {
        boolean boardSetupValid = validateBoard();
        if (boardSetupValid && Connection.INSTANCE.getPlayerActive()) {
            readyToPlayButton.setVisible(false);
            randomShipPlacementButton.setVisible(false);

            Connection.INSTANCE.sendToServer(new Message(CommandType.SETUP_COMPLETED, ""));

            Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(false));
            turnLabel.setVisible(true);
        }
    }

    private boolean validateBoard() {
        System.out.println("zwalidowane"); // TODO krzychu
        return true;
    }
}
