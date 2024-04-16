package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.service.lost.dto.CreateLostDto;
import com.uhpoo.ireon.api.service.lost.dto.EditLostDto;
import com.uhpoo.ireon.domain.lost.repository.LostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 실종동물 게시글 서비스
 *
 * @author yekk1
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LostService {

    private final LostRepository lostRepository;

    /**
     * 실종동물 게시글 등록
     *
     *  @param dto 실종동물 게시글 정보
     *  @param nickname 현재 로그인 중인 회원 닉네임
     *  @param file 첨부파일
     *  @return 등록된 PK 값
     */
    public Long createLost(CreateLostDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     * 실종동물 게시글 수정
     *
     *  @param dto 실종동물 게시글 수정 정보
     *  @param nickname 현재 로그인 중인 회원 닉네임
     *  @param file 첨부파일
     *  @return 수정 된 PK 값
     */
    public Long editLost(EditLostDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     *  실종동물 게시물 삭제
     *
     * @param LostId 삭제할 실종동물 게시글 PK 값
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 삭제 된 PK 값
     */
    public Long deleteLost(Long LostId, String nickname) { return null; }
}
