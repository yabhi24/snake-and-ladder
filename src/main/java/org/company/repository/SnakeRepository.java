package org.company.repository;

import org.company.models.BoardEntityManager;
import org.company.models.Snake;

import java.util.*;

import static org.company.utils.IdUtil.generateUniqueId;

public class SnakeRepository implements BoardEntityManager<Snake> {

    TreeMap<String, List<Snake>> snakes = new TreeMap<>();

    @Override
    public Snake create(String boardId, int start, int end) {
        String snakeId = generateUniqueId();
        Snake snake = new Snake(snakeId, boardId, start, end);
        snakes.computeIfAbsent(boardId, k -> new ArrayList<>()).add(snake);
        return snake;
    }

    @Override
    public Optional<Snake> getByBoardIdAndStart(String boardId, int start) {
        List<Snake> existingSnakes = snakes.getOrDefault(boardId, new ArrayList<>());
        return existingSnakes.stream().filter(snake -> snake.getStart() == start).findFirst();
    }

    @Override
    public void delete(String boardId, int start) {
        List<Snake> existingSnakes = snakes.getOrDefault(boardId, new ArrayList<>());
        existingSnakes.removeIf(snake -> snake.getStart() == start);
    }
}
