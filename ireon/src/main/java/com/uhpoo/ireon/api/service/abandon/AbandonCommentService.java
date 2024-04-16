package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonCommentDto;
import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonCommentDto;
import com.uhpoo.ireon.domain.abandon.repository.AbandonCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유기동물 게시글 댓글 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AbandonCommentService {

    private final AbandonCommentRepository commentRepository;

    /**
     * 유기동물 댓글 등록
     *
     * @param dto      댓글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 등록된 PK 값
     */
    public Long createComment(CreateAbandonCommentDto dto, String nickname) {
        return null;
    }

    /**
     * 유기동물 댓글 수정
     *
     * @param dto      수정할 댓글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 수정된 PK 값
     */
    public Long editComment(EditAbandonCommentDto dto, String nickname) {
        return null;
    }

    /**
     * 유기동물 댓글 삭제
     *
     * @param commentId 삭제할 댓글 PK
     * @param nickname  현재 로그인 중인 회원 닉네임
     * @return 삭제된 PK 값
     */
    public Long deleteComment(Long commentId, String nickname) {
        return null;
    }
}
