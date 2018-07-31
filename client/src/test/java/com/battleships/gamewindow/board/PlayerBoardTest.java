package com.battleships.gamewindow.board;

import com.battleships.gamewindow.services.BoardService;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class PlayerBoardTest {

    private JFXPanel jfxPanel;
    private PlayerBoard playerBoard;
    private BoardService boardService;

    @BeforeMethod
    public void init() {
        jfxPanel = new JFXPanel();
        playerBoard = new PlayerBoard();
        playerBoard.changeAllFieldsToSea();
        boardService = new BoardService(new BoardSize(10, 10));

    }

    public void isBoardInited_ifBoardNotInitedWithMasts_returnsFalse() {
        assertFalse(playerBoard.isBoardInited());
    }

    public void isBoardInited_ifBoardInitedWithMasts_returnsTrue() {
        //given
        BoardGridPanes boardGridPanesMock = mock(BoardGridPanes.class);
        when(boardGridPanesMock.playerGridPane()).thenReturn(mock(GridPane.class));
        when(boardGridPanesMock.opponentGridPane()).thenReturn(mock(GridPane.class));
        boardService.initBoards(boardGridPanesMock, null);
        boardService.createNewRandomConfig();

        //when
        boolean playersBoardInited = boardService.isPlayersBoardInited();

        //then
        assertTrue(playersBoardInited);
    }
}