package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.domain.abandon.repository.AbandonReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유기동물 신고 CUD 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AbandonReportService {

    private final AbandonReportRepository abandonReportRepository;

    /**
     * 유기동물 게시글 신고 등록
     *
     * @param abandonId 유기동물 게시글 PK
     * @param nickname  현재 로그인 중인 회원의 닉네임
     * @return 등록된 신고 PK
     */
    public Long createAbandonReport(Long abandonId, String nickname) {
        return null;
    }
}
