package org.zerock.board.repository.search;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.entity.Board;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
        implements SearchBoardRepository{

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1.....");
        return null;
    }
}
