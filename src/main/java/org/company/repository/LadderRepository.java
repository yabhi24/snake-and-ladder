package org.company.repository;

import org.company.models.BoardEntityManager;
import org.company.models.Ladder;

import java.util.*;

import static org.company.utils.IdUtil.generateUniqueId;

public class LadderRepository implements BoardEntityManager<Ladder> {

    TreeMap<String, List<Ladder>> ladders = new TreeMap<>();

    @Override
    public Ladder create(String boardId, int start, int end) {
        String ladderId = generateUniqueId();
        Ladder ladder = new Ladder(ladderId, boardId, start, end);
        ladders.computeIfAbsent(boardId, k -> new ArrayList<>()).add(ladder);
        return ladder;
    }

    @Override
    public Optional<Ladder> getByBoardIdAndStart(String boardId, int start) {
        List<Ladder> existingLadders = ladders.getOrDefault(boardId, new ArrayList<>());
        return existingLadders.stream().filter(ladder -> ladder.getStart() == start).findFirst();
    }

    @Override
    public void delete(String boardId, int start) {
        List<Ladder> existingLadders = ladders.getOrDefault(boardId, new ArrayList<>());
        existingLadders.removeIf(ladder -> ladder.getStart() == start);
    }
}
