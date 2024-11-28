package org.company.models;

public class Board {

    private final String id;
    private final String gameId;
    private final int size;

    public Board(String id, String gameId, int size) {
        this.id = id;
        this.gameId = gameId;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public int getSize() {
        return size;
    }
}
