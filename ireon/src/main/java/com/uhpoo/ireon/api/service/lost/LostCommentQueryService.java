package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostCommentResponse;
import com.uhpoo.ireon.domain.lost.repository.LostCommentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 실종동물 게시글 댓글 조회 서비스
 *
 * @author CYJ
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LostCommentQueryService {

    private final LostCommentQueryRepository lostCommentQueryRepository;

    /**
     * 실종동물 댓글 목록 조회
     * @param lostId 실종동물 게시글 PK
     * @param lastCommentId 마지막으로 조회된 댓글 PK
     * @return PK에 해당하는 실종동물 댓글 목록
     */
    public PageResponse<List<LostCommentResponse>> getLostComment(Long lostId, Long lastCommentId) {
        return null;
    }
}
