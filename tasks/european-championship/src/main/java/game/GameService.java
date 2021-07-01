package game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameService {

    private GameRepository gameRepository;
    private List<Game> games;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        games = gameRepository.getGames();
    }

    public Game biggestGoalDifference() {
        int maxGoal = 0;
        Game maxGame = games.get(0);
        for (Game game : games) {
            int diff = Math.abs(game.getFirstCountryScore() - game.getSecondCountryScore());
            if (maxGoal < diff) {
                maxGoal = diff;
                maxGame = game;
            }
        }
        return maxGame;
    }

    public Game biggestGoalDifferenceWithStream() {
        return games.stream()
                .max(Comparator.comparing(game -> Math.abs(game.getFirstCountryScore() - game.getSecondCountryScore())))
                .get();
    }

    public int numberOfGoalsByCountries(String country) {
        int goals = 0;
        for (Game game : games) {
            if (game.getFirstCountry().equals(country)) {
                goals += game.getFirstCountryScore();
            }
            if (game.getSecondCountry().equals(country)) {
                goals += game.getSecondCountryScore();
            }
        }
        return goals;
    }

    public String countryWithMostGoals() {
        Map<String, Integer> result = new HashMap<>();
        for (Game game : games) {
            if (!result.containsKey(game.getFirstCountry())) {
                result.put(game.getFirstCountry(), numberOfGoalsByCountries(game.getFirstCountry()));
            }
        }
        return getCountry(result);
    }

    private String getCountry(Map<String, Integer> result) {
        int max = 0;
        String country = "";
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                country = entry.getKey();
            }
        }
        return country;
    }
}
