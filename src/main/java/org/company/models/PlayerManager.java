package org.company.models;

import java.util.List;
import java.util.Optional;

public interface PlayerManager {

    Player create(String gameId, String name);

    Optional<Player> get(String gameId, String id);

    List<Player> getAllPlayers(String gameId);

    void delete(String gameId, String id);

    void move(String gameId, String id, int position);
}
