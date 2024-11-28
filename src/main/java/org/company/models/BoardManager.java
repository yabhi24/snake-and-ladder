package org.company.models;

import java.util.Optional;

public interface BoardManager {

    Board create(String gameId, int size);

    Optional<Board> getByGameId(String gameId);

    void delete(String gameId);
}
