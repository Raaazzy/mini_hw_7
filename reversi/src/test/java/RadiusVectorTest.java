import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RadiusVectorTest {

    @Test
    @DisplayName("test on checking X coordinate")
    void getX() {
        var vec = new RadiusVector(1, 2);
        var expected = 1;
        var result = vec.getX();
        assertEquals(expected, result, "X coordinate = 1");
    }

    @Test
    @DisplayName("test on checking Y coordinate")
    void getY() {
        var vec = new RadiusVector(1, 2);
        var expected = 2;
        var result = vec.getY();
        assertEquals(expected, result, "Y coordinate = 1");
    }
}