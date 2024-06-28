package com.uhpoo.ireon.domain.abandon.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 유기동물 게시글 신고 쿼리 레포지토리
 *
 * @author 최영환
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AbandonReportQueryRepository {

    private final JPAQueryFactory queryFactory;
}
