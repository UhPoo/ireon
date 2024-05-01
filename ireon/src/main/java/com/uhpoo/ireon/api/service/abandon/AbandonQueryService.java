package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.domain.abandon.repository.AbandonQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 유기동물 게시글 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbandonQueryService {

    private final AbandonQueryRepository abandonQueryRepository;

    /**
     * 유기동물 목록 조회
     *
     * @return 검색 조건에 해당하는 유기동물 목록
     */
    public PageResponse<List<AbandonResponse>> getAbandons() {
        return null;
    }

    /**
     * 유기동물 상세 조회
     *
     * @param abandonId 유기동물 게시글 PK
     * @return PK에 해당하는 유기동물 게시글 정보
     */
    public AbandonDetailResponse getAbandon(Long abandonId) {
        return null;
    }

    /**
     * 스크랩한 유기동물 목록 조회
     * 
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 현재 로그인 중인 회원이 스크랩한 유기동물 목록
     */
    public PageResponse<List<AbandonResponse>> getScrappedAbandons(String nickname) {
        return null;
    }
}
