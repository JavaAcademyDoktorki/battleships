import com.battleships.Coordinate;
import com.battleships.gamewindow.board.Ship;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipTest {
    private JFXPanel jfxPanel = new JFXPanel();
    private Ship ship;

    @BeforeMethod
    public void setUp(){
        ship = new Ship();
        ship.addMast(new BoardField(Coordinate.fromIntCoords(1, 1), FieldState.MAST));
        ship.addMast(new BoardField(Coordinate.fromIntCoords(1, 2), FieldState.MAST));
        ship.addMast(new BoardField(Coordinate.fromIntCoords(1, 3), FieldState.MAST));
    }

    @Test
    public void isMastShot_whenHitEmptyField_shouldReturnFalse() {
        Coordinate itIsNotAFuckingMast = Coordinate.fromIntCoords(3, 4);
        assertFalse(ship.isMastHit(itIsNotAFuckingMast));
    }

    @Test
    public void isMastShot_whenHitMast_shouldReturnTrue() {
        Coordinate itIsNotAFuckingMast = Coordinate.fromIntCoords(1, 1);
        assertTrue(ship.isMastHit(itIsNotAFuckingMast));
    }
}
