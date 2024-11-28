package org.company.models;

public class Snake implements BoardEntity {

    private final String id;
    private final String boardId;
    private final int start;
    private final int end;

    public Snake(String id, String boardId, int start, int end) {
        this.id = id;
        this.boardId = boardId;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getBoardId() {
        return boardId;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }
}
