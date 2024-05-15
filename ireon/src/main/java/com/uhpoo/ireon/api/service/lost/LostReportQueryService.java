package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostReportResponse;
import com.uhpoo.ireon.domain.lost.repository.LostReportQueryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 실종동물 게시글 신고 목록 조회 서비스
 *
 * @author 최예지
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LostReportQueryService {
    private final LostReportQueryRepository lostReportQueryRepository;

    /**
     * 실종동물 게시글 신고 목록 조회
     *
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 현재 로그인 중인 회원이 신고한 실종동물 게시글 목록
     */
    public PageResponse<List<LostReportResponse>> getLostReports(String nickname) {
        return null;
    }

}
