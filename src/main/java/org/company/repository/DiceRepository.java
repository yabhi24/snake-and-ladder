package org.company.repository;

import org.company.models.Dice;
import org.company.models.DiceManager;

import java.util.*;

import static org.company.utils.IdUtil.generateUniqueId;

public class DiceRepository implements DiceManager {

    private final TreeMap<String, List<Dice>> dices = new TreeMap<>();

    @Override
    public Dice create(String gameId, int numberOfFaces) {
        String diceId = generateUniqueId();
        Dice dice = new Dice(diceId, gameId, numberOfFaces);
        dices.computeIfAbsent(diceId, k -> new ArrayList<>()).add(dice);
        return dice;
    }

    @Override
    public List<Dice> get(String gameId) {
        return dices.get(gameId);
    }

    @Override
    public void delete(String gameId, String id) {
        //
    }

    @Override
    public int roll(String gameId, String id) {

        return 0;
    }
}
