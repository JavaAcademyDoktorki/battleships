package com.battleships.gamewindow.services;

import com.battleships.models.Events;
import com.battleships.models.board.Boards;
import com.battleships.models.board.CoordState;
import com.battleships.models.board.Coordinate;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


public class BoardServiceTest {

    Map<Coordinate, CoordState> board;
    private BoardService boardService;
    private JFXPanel fxPanel; // don't delete it!

    @BeforeClass
    public void beforeTests() {
        board = mockMap();
        boardService = new BoardService(10, 10, board);
        fxPanel = new JFXPanel();
    }

    @Test
    public void button_has_brown_style_if_there_is_a_mast() {
        //given
        Button button = new Button();
        Coordinate mastCoord = new Coordinate(10, 10);
        when(board.get(mastCoord)).thenReturn(CoordState.SHIP);
        //when
        boardService.colourButton(button, mastCoord);
        //then
        assertEquals(button.getStyle(), "-fx-background-color: #15b007\n");
    }

    @Test
    public void button_has_blue_style_if_no_mast_is_there() {
        //given
        Button button = new Button();
        Coordinate shipCoordinate = new Coordinate(10, 10);
        Coordinate waterCoordinate = new Coordinate(9, 10);
        when(board.get(shipCoordinate)).thenReturn(CoordState.SHIP);
        when(board.get(waterCoordinate)).thenReturn(CoordState.WATER);
        //when
        boardService.colourButton(button, waterCoordinate);
        //then
        assertEquals(button.getStyle(), "-fx-background-color: #0a73fe");
    }

    @Test
    public void creating_buttons_in_boards_creates_proper_amount_of_buttons() {
        //given
        GridPane myBoard = new GridPane();
        GridPane opponentsBoard = new GridPane();
        Boards boards = new Boards(myBoard, opponentsBoard);
        Events events = new Events(event -> System.out.println(("My board event")),
                event -> System.out.println("Opponnents board event"));
        //when
        boardService.createButtonsInBothBoards(boards, events);
        //then
        ObservableList<Node> myBoardChildren = myBoard.getChildren();
        ObservableList<Node> opponnentsChildren = opponentsBoard.getChildren();

        assertEquals(myBoardChildren.size(), 100);
        assertEquals(opponnentsChildren.size(), 100);
    }


    @SuppressWarnings("unchecked")
    private <K, V> Map<K, V> mockMap() {
        return mock(Map.class);
    }

}