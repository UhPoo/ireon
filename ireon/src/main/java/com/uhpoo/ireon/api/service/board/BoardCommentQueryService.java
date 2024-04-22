package com.uhpoo.ireon.api.service.board;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.response.BoardCommentResponse;
import com.uhpoo.ireon.domain.board.repository.BoardCommentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 자유게시판 댓글 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCommentQueryService {

    private final BoardCommentQueryRepository boardCommentQueryRepository;

    /**
     * 자유게시판 댓글 목록 조회
     *
     * @param boardId            조회할 댓글의 게시글 PK
     * @param lastBoardCommentId 마지막으로 조회한 댓글 PK
     * @return 댓글 목록
     */
    public PageResponse<List<BoardCommentResponse>> getComments(Long boardId, Long lastBoardCommentId) {
        return null;
    }
}
