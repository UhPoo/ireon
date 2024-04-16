package com.uhpoo.ireon.api.service.lost.dto;

import com.uhpoo.ireon.domain.lost.repository.LostCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 실종동물 댓글 서비스
 *
 * @author yekk1
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LostCommentService {

    private final LostCommentRepository lostCommentRepository;

    /**
     * 실종동물 댓글 등록
     * @param dto       실종동물 댓글 정보
     * @param nickname  현재 로그인 중인 회원 닉네임
     * @return 등록 된 PK 값
     */
    public Long createLostComment(CreateLostCommentDto dto, String nickname)
    {
        return null;
    }

    /**
     * 실종동물 댓글 수정
     * @param dto       실종동물 댓글 정보
     * @param nickname  현재 로그인 중인 회원 닉네임
     * @return 수정 된 PK 값
     */
    public Long editLostComment(EditLostCommentDto dto, String nickname) {
        return null;
    }
}
