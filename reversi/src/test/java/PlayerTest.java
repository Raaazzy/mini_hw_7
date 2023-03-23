import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("test on checking player's points")
    void testGetPointsHuman() {
        Player player = new Human("A");
        var expected = 0;
        var result = player.getPoints();
        assertEquals(expected, result, "player has 0 points");
    }

    @Test
    @DisplayName("test on checking player's points")
    void testGetPointsComputer() {
        Player player = new Computer();
        var expected = 0;
        var result = player.getPoints();
        assertEquals(expected, result, "player has 0 points");
    }

    @Test
    @DisplayName("test on checking player's color")
    void testGetColorHuman() {
        Player player = new Human("A");
        var expected = "green";
        var result = player.getColor();
        assertEquals(expected, result, "player has green color");
    }

    @Test
    @DisplayName("test on checking player's color")
    void testGetColorComputer() {
        Player player = new Computer();
        var expected = "red";
        var result = player.getColor();
        assertEquals(expected, result, "player has red color");
    }

    @Test
    @DisplayName("test on checking player's new color")
    void testChangeColorHuman() {
        Player player = new Human("A");
        var expected = "red";
        player.changeColor("red");
        var result = player.getColor();
        assertEquals(expected, result, "player has red color");
    }

    @Test
    @DisplayName("test on checking player's new color")
    void testChangeColorComputer() {
        Player player = new Computer();
        var expected = "green";
        player.changeColor("green");
        var result = player.getColor();
        assertEquals(expected, result, "player has green color");
    }

    @Test
    void testGetNameHuman() {
        Player player = new Human("A");
        var expected = "A";
        var result = player.getName();
        assertEquals(expected, result, "player's name - 'A'");
    }

    @Test
    void testGetNameComputer() {
        Player player = new Computer();
        var expected = "Computer";
        var result = player.getName();
        assertEquals(expected, result, "player's name - 'Computer'");
    }
}