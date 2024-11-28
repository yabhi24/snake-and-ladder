package org.company.repository;

import org.company.models.Game;
import org.company.models.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.company.utils.IdUtil.generateUniqueId;

public class GameRepository implements GameManager {

    private final List<Game> games = new ArrayList<>();

    @Override
    public Game create(String name) {
        String gameId = generateUniqueId();
        Game game = new Game(gameId, name);
        games.add(game);
        return game;
    }

    @Override
    public void delete(String id) {
        games.removeIf(game -> id.equals(game.getId()));
    }

    @Override
    public Optional<Game> get(String id) {
        return games.stream().filter(game -> id.equals(game.getId())).findFirst();
    }
}
