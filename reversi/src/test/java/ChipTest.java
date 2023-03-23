import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChipTest {

    @Test
    @DisplayName("test on checking transparent color of chip")
    void testIsTransparent() {
        Chip chip = new Chip("transparent", 1, 1);
        var expected = true;
        var result = chip.isTransparent();
        assertEquals(expected, result, "this chip is transparent");
    }

    @Test
    @DisplayName("test on checking yellow color of chip")
    void testIsYellow() {
        Chip chip = new Chip("yellow", 1, 1);
        var expected = true;
        var result = chip.isYellow();
        assertEquals(expected, result, "this chip is yellow");
    }

    @Test
    @DisplayName("test on checking red color of chip")
    void itTestIsRed() {
        Chip chip = new Chip("red", 1, 1);
        var expected = true;
        var result = chip.isRed();
        assertEquals(expected, result, "this chip is red");
    }

    @Test
    @DisplayName("test on checking green color of chip")
    void testIsGreen() {
        Chip chip = new Chip("green", 1, 1);
        var expected = true;
        var result = chip.isGreen();
        assertEquals(expected, result, "this chip is green");
    }
}