package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    GameService gameService;

    @BeforeEach
    void init() {
        GameRepository gameRepository = new GameRepository();
        gameRepository.readGamesFromCsv(Path.of("src/main/resources/results.csv"));
        gameService = new GameService(gameRepository);
    }

    @Test
    void biggestGoalDifference() {
        assertEquals("Turkey", gameService.biggestGoalDifference().getFirstCountry());
        assertEquals(3, gameService.biggestGoalDifference().getSecondCountryScore());

        assertEquals("Turkey", gameService.biggestGoalDifferenceWithStream().getFirstCountry());
        assertEquals(3, gameService.biggestGoalDifferenceWithStream().getSecondCountryScore());
    }

    @Test
    void numberOfGoalsByCountries() {
        assertEquals(5, gameService.numberOfGoalsByCountries("Italy"));
        assertEquals(2, gameService.numberOfGoalsByCountries("Wales"));
    }

    @Test
    void countryWithMostGoals() {
        assertEquals("Italy", gameService.countryWithMostGoals());
    }
}