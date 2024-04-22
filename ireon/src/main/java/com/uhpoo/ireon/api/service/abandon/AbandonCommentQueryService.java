package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonCommentResponse;
import com.uhpoo.ireon.domain.abandon.repository.AbandonCommentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbandonCommentQueryService {

    private final AbandonCommentQueryRepository commentQueryRepository;


    /**
     * 유기동물 게시글 댓글 목록 조회
     *
     * @param abandonId     조회하려는 게시글 PK
     * @param lastCommentId 마지막으로 조회된 댓글 PK
     * @return 유기돌물 게시글 댓글 목록
     */
    public PageResponse<List<AbandonCommentResponse>> getComment(Long abandonId, Long lastCommentId) {
        return null;
    }
}
