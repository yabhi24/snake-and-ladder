package org.company.repository;

import org.company.models.Board;
import org.company.models.BoardManager;

import java.util.*;

import static org.company.utils.IdUtil.generateUniqueId;

public class BoardRepository implements BoardManager {

    private final Map<String, Board> boards = new HashMap<>();

    @Override
    public Board create(String gameId, int size) {
        String boardId = generateUniqueId();
        Board board = new Board(boardId, gameId, size);
        return boards.put(boardId, board);
    }

    @Override
    public Optional<Board> getByGameId(String gameId) {
        return Optional.ofNullable(boards.get(gameId));
    }

    @Override
    public void delete(String gameId) {
        boards.remove(gameId);
    }
}
