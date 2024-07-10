package com.uhpoo.ireon.domain.abandon.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.domain.abandon.dto.AbandonDetailDto;
import com.uhpoo.ireon.domain.abandon.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.uhpoo.ireon.domain.abandon.QAbandon.abandon;
import static com.uhpoo.ireon.domain.abandon.QAbandonAttachment.abandonAttachment;
import static com.uhpoo.ireon.domain.abandon.QAbandonScrap.abandonScrap;
import static com.uhpoo.ireon.domain.member.QMember.member;
import static com.uhpoo.ireon.global.constants.SizeConstants.PAGE_SIZE;
import static org.springframework.util.StringUtils.hasText;

/**
 * 유기동물 게시글 쿼리 레포지토리
 *
 * @author 최영환
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AbandonQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 검색 조건으로 유기동물 게시글 목록 조회 쿼리
     *
     * @param condition     검색 조건
     * @param lastAbandonId 마지막으로 조회된 유기동물 PK
     * @param nickname      현재 로그인 중인 회원 닉네임
     * @return 검색 조건에 해당하는 유기동물 게시글 목록
     */
    public List<AbandonResponse> getAbandonsByCondition(SearchCondition condition, Long lastAbandonId, String nickname) {

        List<Long> ids = queryFactory
                .select(abandon.id)
                .from(abandon)
                .join(abandon.member, member)
                .leftJoin(abandonAttachment).on(abandonAttachment.abandon.eq(abandon), abandonAttachment.active)
                .where(
                        containsKeyword(condition.getKeyword()),
                        greaterThanLastAbandonId(lastAbandonId),
                        abandon.active
                )
                .limit(PAGE_SIZE + 1)
                .fetch();

        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(AbandonResponse.class,
                        abandon.id,
                        abandon.title,
                        abandon.member.nickname,
                        abandon.animalInfo.animalType,
                        abandon.abandonStatus,
                        abandon.address.road,
                        abandon.address.jibun,
                        abandon.address.detail,
                        abandon.phoneNumber,
                        isClipped(nickname),
                        abandonAttachment.uploadFile.storeFileName,
                        abandon.createdDate
                ))
                .from(abandon)
                .join(abandon.member, member)
                .leftJoin(abandonAttachment).on(abandonAttachment.abandon.eq(abandon), abandonAttachment.active)
                .where(
                        abandon.id.in(ids)
                )
                .orderBy(abandon.createdDate.desc())
                .fetch();
    }

    /**
     * PK 와 닉네임으로 유기동물 게시글 상세 조회 쿼리
     *
     * @param abandonId 조회하려는 유기동물 게시글 PK
     * @param nickname  현재 로그인 중인 회원 닉네임
     * @return 조건에 해당하는 유기동물 게시글 정보
     */
    public AbandonDetailDto getAbandonByIdAndNickname(Long abandonId, String nickname) {
        return queryFactory.select(Projections.constructor(AbandonDetailDto.class,
                        abandon.id,
                        abandon.title,
                        abandon.content,
                        abandon.member.nickname,
                        abandon.animalInfo.animalType,
                        abandon.animalInfo.animalDetail,
                        abandon.animalInfo.animalGender,
                        abandon.animalInfo.age,
                        abandon.vaccinationStatus,
                        abandon.deSexing,
                        abandon.abandonStatus,
                        abandon.address.zipcode,
                        abandon.address.road,
                        abandon.address.jibun,
                        abandon.address.detail,
                        abandon.phoneNumber,
                        isClipped(nickname),
                        abandon.createdDate
                ))
                .from(abandon)
                .join(abandon.member, member)
                .leftJoin(abandonAttachment).on(abandonAttachment.abandon.eq(abandon), abandonAttachment.active)
                .where(
                        isAbandonId(abandonId)
                )
                .fetchOne();
    }

    private BooleanExpression containsKeyword(String keyword) {
        return hasText(keyword) ? abandon.title.contains(keyword).or(abandon.content.contains(keyword)) : null;
    }

    private BooleanExpression greaterThanLastAbandonId(Long lastAbandonId) {
        return lastAbandonId == null ? null : abandon.id.gt(lastAbandonId);
    }

    /**
     * 스크랩 여부 확인 메소드
     *
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 현재 로그인 중인 회원의 해당 게시글 스크랩 여부
     */
    private JPQLQuery<Boolean> isClipped(String nickname) {
        return select(abandonScrap.count().goe(1L))
                .from(abandonScrap)
                .where(
                        abandonScrap.abandon.id.eq(abandon.id),
                        abandonScrap.member.nickname.eq(nickname),
                        abandonScrap.active,
                        member.active
                );
    }

    private BooleanExpression isAbandonId(Long abandonId) {
        return abandonId == null ? null : abandon.id.eq(abandonId);
    }
}
