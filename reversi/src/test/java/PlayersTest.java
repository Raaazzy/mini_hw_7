import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    @DisplayName("test on correct return of players index")
    void getFirstPlayerIndex() {
        Player player = new Human("A");
        Players players = new Players(player);
        var expected = -1;
        var result = players.getFirstPlayerIndex();
        assertEquals(expected, result, "index = -1");
    }

    @Test
    @DisplayName("test on correct return of players index")
    void getSecondPlayerIndex() {
        Player player = new Human("A");
        Players players = new Players(player);
        var expected = -1;
        var result = players.getFirstPlayerIndex();
        assertEquals(expected, result, "index = -1");
    }

    @Test
    @DisplayName("test on correct return of players count")
    void getCount() {
        Player player = new Human("A");
        Players players = new Players(player);
        var expected = 1;
        var result = players.getCount();
        assertEquals(expected, result, "count of players = 1");
    }
}