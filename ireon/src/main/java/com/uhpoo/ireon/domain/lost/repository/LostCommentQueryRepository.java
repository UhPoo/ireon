package com.uhpoo.ireon.domain.lost.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 실종동물 댓글 조회 레포지토리
 *
 * @author yekk1
 */
@Slf4j
@Repository
@RequiredArgsConstructor()
public class LostCommentQueryRepository {
    private final JPAQueryFactory queryFactory;
}
