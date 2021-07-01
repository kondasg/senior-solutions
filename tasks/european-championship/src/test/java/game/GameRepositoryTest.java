package game;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    GameRepository gameRepository = new GameRepository();

    @Test
    void addGame() {
        gameRepository.addGame(new Game("a", "b", 0, 0));
        gameRepository.addGame(new Game("a", "b", 0, 0));
        assertEquals(2, gameRepository.getGames().size());
    }

    @Test
    void readGamesFromCsv() {
        gameRepository.readGamesFromCsv(Path.of("src/main/resources/results.csv"));
        assertEquals(15, gameRepository.getGames().size());
    }
}