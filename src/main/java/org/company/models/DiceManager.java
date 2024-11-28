package org.company.models;

import java.util.List;

public interface DiceManager {

    Dice create(String gameId, int numberOfFaces);

    List<Dice> get(String gameId);

    void delete(String gameId, String id);

    int roll(String gameId, String id);
}
