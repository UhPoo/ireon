package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonDto;
import com.uhpoo.ireon.domain.abandon.repository.AbandonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 유기동물 게시글 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AbandonService {

    private final AbandonRepository abandonRepository;

    /**
     * 유기동물 게시글 등록
     *
     * @param dto      유기동물 게시글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @param file     등록할 첨부파일
     * @return 등록된 PK 값
     */
    public Long createAbandon(CreateAbandonDto dto, String nickname, MultipartFile file) {
        return null;
    }

    public Long editAbandon(EditAbandonDto dto, String nickname, MultipartFile file) {
        return null;
    }
}
