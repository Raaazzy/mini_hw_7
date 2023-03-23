import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    @DisplayName("test on checking full of table")
    void isFieldFull() {
        Table table = new Table();
        var expected = false;
        var result = table.isFieldFull();
        assertEquals(expected, result, "64 chips on table");
    }

    @Test
    @DisplayName("test on checking is possible to move")
    void isPossibleToMove() {
        Table table = new Table();
        var expected = false;
        var result = table.isPossibleToMove(1, 1, -1, -1, "red");
        assertEquals(expected, result, "this move is out of the table");
    }

    @Test
    @DisplayName("test on checking a chip is prompt")
    void isPrompt() {
        Table table = new Table();
        var expected = false;
        var result = table.isPrompt(new Coordinates(1, 1));
        assertEquals(expected, result, "the chip isn't prompt");
    }

    @Test
    @DisplayName("test on checking on copy field")
    void copyField() {
        Table table = new Table();
        var expected = 1;
        table.copyField();
        var result = table.moveHistory.size();
        assertEquals(expected, result, "field must to copy current version of the field");
    }
}