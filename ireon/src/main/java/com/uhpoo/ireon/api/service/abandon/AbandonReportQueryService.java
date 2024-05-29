package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportResponse;
import com.uhpoo.ireon.domain.abandon.repository.AbandonReportQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 유기동물 게시글 신고 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbandonReportQueryService {

    private final AbandonReportQueryRepository abandonReportQueryRepository;

    /**
     * 유기동물 게시글 신고 목록 조회
     *
     * @return 유기동물 게시글 신고 목록
     */
    public PageResponse<List<AbandonReportResponse>> getAbandonReports() {
        return null;
    }

    /**
     * 유기동물 게시글 신고 상세 조회
     *
     * @param abandonReportId 유기동물 게시글 신고 PK
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 유기동물 게시글 신고 상세 정보
     */
    public AbandonReportDetailResponse getAbandonReport(Long abandonReportId, String nickname) {
        return null;
    }
}
