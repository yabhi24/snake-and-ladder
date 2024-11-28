package org.company.models;

import java.util.Optional;

public interface BoardEntityManager<T extends BoardEntity> {

    T create(String boardId, int start, int end);

    Optional<T> getByBoardIdAndStart(String boardId, int start);

    void delete(String boardId, int start);
}
