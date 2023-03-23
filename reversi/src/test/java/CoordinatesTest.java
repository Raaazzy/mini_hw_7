import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    @DisplayName("test on checking X coordinate")
    void getX() {
        var coord = new Coordinates(1, 2);
        var expected = 1;
        var result = coord.getX();
        assertEquals(expected, result, "X coordinate = 1");
    }

    @Test
    @DisplayName("test on checking Y coordinate")
    void getY() {
        var coord = new Coordinates(1, 2);
        var expected = 2;
        var result = coord.getY();
        assertEquals(expected, result, "Y coordinate = 2");
    }

    @Test
    @DisplayName("test on checking equals of 2 coordinates")
    void testEquals() {
        var first = new Coordinates(1, 2);
        var second = new Coordinates(1, 2);
        var expected = true;
        var result = first.equals(second);
        assertEquals(expected, result, "(1;2) == (1;2)");
    }
}