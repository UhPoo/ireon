package com.uhpoo.ireon.api.service.board;

import com.uhpoo.ireon.api.service.board.dto.CreateBoardCommentDto;
import com.uhpoo.ireon.api.service.board.dto.EditBoardCommentDto;
import com.uhpoo.ireon.domain.board.repository.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 자유게시판 댓글 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    /**
     * 자유게시판 댓글 등록
     *
     * @param dto      댓글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 등록된 댓글 PK
     */
    public Long createBoardComment(CreateBoardCommentDto dto, String nickname) {
        return null;
    }

    /**
     * 자유게시판 댓글 수정
     *
     * @param dto      수정할 댓글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 수정된 댓글 PK
     */
    public Long editComment(EditBoardCommentDto dto, String nickname) {
        return null;
    }

    /**
     * 자유게시판 댓글 삭제
     *
     * @param boardCommentId 삭제할 댓글 PK
     * @param nickname       현재 로그인 중인 회원 닉네임
     * @return 삭제된 댓글 PK
     */
    public Long deleteBoardComment(Long boardCommentId, String nickname) {
        return null;
    }
}
