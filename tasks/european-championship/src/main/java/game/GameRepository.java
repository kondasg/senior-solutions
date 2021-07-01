package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void readGamesFromCsv(Path file) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            readLines(reader);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file", ioe);
        }
    }

    private void readLines(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splittedLine = line.split(";");
            games.add(new Game(
                    splittedLine[0],
                    splittedLine[1],
                    Integer.parseInt(splittedLine[2]),
                    Integer.parseInt(splittedLine[3])
            ));
        }
    }

}
