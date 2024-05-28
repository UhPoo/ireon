package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostDetailResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import com.uhpoo.ireon.domain.lost.repository.LostQueryRepository;
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

    private final LostQueryRepository lostQueryRepository;

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

    /**
     * 스크랩 실종동물 목록 조회
     *
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 현재 로그인 중인 회원이 스크랩한 실종동물 목록
     */
    public PageResponse<List<LostResponse>> getLostScraps(String nickname) {
        return null;
    }
}
