package org.company.models;

import static org.company.constants.PlayerConstant.DEFAULT_START_POSITION;

public class Player {

    private final String id;
    private final String gameId;
    private final String name;
    private int position;

    public Player(String id, String gameId, String name) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = DEFAULT_START_POSITION;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
