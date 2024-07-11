package com.uhpoo.ireon.domain.abandon.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.uhpoo.ireon.domain.abandon.QAbandon.abandon;
import static com.uhpoo.ireon.domain.abandon.QAbandonAttachment.abandonAttachment;

/**
 * 유기동물 게시글 첨부파일 조회 레포지토리
 *
 * @author 최영환
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AbandonAttachmentQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 유기동물 게시글 PK로 유기동물 게시글 첨부파일 저장 경로 목록 조회
     *
     * @param abandonId 유기동물 게시글 PK
     * @return 해당 유기동물 게시글의 유기동물 게시글 첨부파일 저장 경로 목록
     */
    public List<String> getAttachmentsByAbandonId(Long abandonId) {
        List<Long> ids = queryFactory
                .select(abandonAttachment.id)
                .from(abandonAttachment)
                .join(abandonAttachment.abandon, abandon)
                .where(
                        abandonAttachment.abandon.id.eq(abandonId)
                )
                .fetch();

        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(abandonAttachment.uploadFile.storeFileName)
                .from(abandonAttachment)
                .where(
                        abandonAttachment.id.in(ids)
                )
                .fetch();
    }

}
