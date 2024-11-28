package org.company.models;

import java.util.Optional;

public interface GameManager {

    Game create(String name);

    void delete(String id);

    Optional<Game> get(String id);
}
