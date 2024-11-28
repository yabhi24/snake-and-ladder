package org.company.repository;

import org.company.models.Player;
import org.company.models.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import static org.company.utils.IdUtil.generateUniqueId;

public class PlayerRepository implements PlayerManager {

    private final TreeMap<String, List<Player>> players = new TreeMap<>();

    @Override
    public Player create(String gameId, String name) {
        String playerId = generateUniqueId();
        Player player = new Player(playerId, gameId, name);
        players.computeIfAbsent(gameId, k -> new ArrayList<>()).add(player);
        return player;
    }

    @Override
    public Optional<Player> get(String gameId, String id) {
        List<Player> existingPlayers = players.getOrDefault(gameId, new ArrayList<>());
        return existingPlayers.stream().filter(player -> id.equals(player.getId())).findFirst();
    }

    @Override
    public List<Player> getAllPlayers(String gameId) {
        return players.getOrDefault(gameId, new ArrayList<>());
    }

    @Override
    public void delete(String gameId, String id) {
        List<Player> existingPlayers = players.getOrDefault(gameId, new ArrayList<>());
        existingPlayers.removeIf(player -> id.equals(player.getId()));
    }

    @Override
    public void move(String gameId, String id, int position) {
        Optional<Player> existingPlayer = get(gameId, id);
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            players.get(gameId).remove(player);
            player.setPosition(position);
            players.get(gameId).add(player);
        }
    }
}
