package org.company.models;

public class Dice {

    private final String id;
    private final String gameId;
    private final int numberOfFaces;

    public Dice(String id, String gameId, int numberOfFaces) {
        this.id = id;
        this.gameId = gameId;
        this.numberOfFaces = numberOfFaces;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public int getNumberOfFaces() {
        return numberOfFaces;
    }
}
