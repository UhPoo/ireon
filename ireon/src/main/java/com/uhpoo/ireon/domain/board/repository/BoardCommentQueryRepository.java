package com.uhpoo.ireon.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 자유게시판 댓글 조회 레포지토리
 *
 * @author 최영환
 */
@Repository
@RequiredArgsConstructor
public class BoardCommentQueryRepository {

    private final JPAQueryFactory queryFactory;
}
