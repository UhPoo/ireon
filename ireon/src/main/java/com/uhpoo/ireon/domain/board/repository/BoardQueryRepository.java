package com.uhpoo.ireon.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 자유게시판 쿼리 레포지토리
 *
 * @author 최영환
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;
}
