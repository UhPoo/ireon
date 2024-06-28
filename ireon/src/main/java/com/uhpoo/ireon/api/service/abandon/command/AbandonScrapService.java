package com.uhpoo.ireon.api.service.abandon.command;

import com.uhpoo.ireon.api.service.abandon.dto.AbandonScrapDto;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AbandonScrapService {

    private final AbandonScrapRepository abandonScrapRepository;

    /**
     * 유기동물 스크랩 등록 / 취소
     * 이미 스크랩한 경우 스크랩을 취소하고 스크랩하지 않았으면 스크랩을 새롭게 등록
     *
     * @param dto      유기동물 게시글 PK
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 등록 / 취소된 스크랩 PK
     */
    public Long scrap(AbandonScrapDto dto, String nickname) {
        return null;
    }
}
