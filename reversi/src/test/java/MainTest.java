import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.jar.Manifest;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("print message")
    void finishGameSession() {
        Table table = new Table();
        Main.finishGameSession();
        var expected = false;
        var result = table.isFieldFull();
        assertEquals(expected, result, "message of finish of the game");
    }

    @Test
    @DisplayName("print message")
    void hardComputerMode() {
        Table table = new Table();
        Main.hardComputerMode();
        var expected = false;
        var result = table.isFieldFull();
        assertEquals(expected, result, "message of start of the game");
    }
}