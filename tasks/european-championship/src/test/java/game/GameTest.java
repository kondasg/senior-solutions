package game;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final Object[][] games = {
            {new Game("Hungary", "France", 1, 1), "Draw"},
            {new Game("Hungary", "France", 2, 1), "Hungary"},
            {new Game("Hungary", "France", 1, 2), "France"}
    };

    @RepeatedTest(value = 3, name = "Get winner {currentRepetition}/{totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repetitionInfo) {
        Game game = (Game) games[repetitionInfo.getCurrentRepetition() - 1][0];
        assertEquals(games[repetitionInfo.getCurrentRepetition() - 1][1], game.winner());
    }

}