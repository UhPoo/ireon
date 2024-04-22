package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostDetailResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 실종동물 게시글 조회 서비스
 *
 * @author yekk1
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LostQueryService {

    private final LostQueryService lostQueryService;

    /**
     * 실종동물 목록 조회
     *
     * @return 검색 조건에 해당하는 실종동물 목록
     */
    public PageResponse<List<LostResponse>> getLosts() {
        return null;
    }

    /**
     * 실종동물 게시글 상세 조회
     *
     * @param lostId 실종동물 게시글 PK
     * @return PK에 해당하는 실종동물 게시글 정보
     */
    public LostDetailResponse getLost(Long lostId) {
        return null;
    }
}
