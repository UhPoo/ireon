package com.uhpoo.ireon.api.service.abandon.query;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.domain.abandon.dto.SearchCondition;
import com.uhpoo.ireon.domain.abandon.repository.query.AbandonQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.uhpoo.ireon.global.constants.SizeConstants.PAGE_SIZE;

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
    public PageResponse<List<AbandonResponse>> getAbandons(SearchCondition condition, Long lastAbandonId, String nickname) {
        log.debug("condition={}", condition);
        log.debug("lastAbandonId={}", lastAbandonId);
        log.debug("nickname={}", nickname);

        List<AbandonResponse> responses = abandonQueryRepository.getAbandonsByCondition(condition, lastAbandonId, nickname);
        log.debug("responses={}", responses);

        boolean hasNext = checkHasNext(responses);

        return PageResponse.of(hasNext, responses);
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

    /**
     * 다음 페이지 여부를 반환하는 메소드
     * @param list 조회 결과 리스트
     * @return true: 리스트의 길이가 PAGE_SIZE 보다 큰 경우 / false: 리스트의 길이가 PAGE_SIZE 보다 작거나 같은 경우
     */
    private boolean checkHasNext(List<AbandonResponse> list) {
        if (list.size() > PAGE_SIZE) {
            list.remove(PAGE_SIZE);
            return true;
        }
        return false;
    }
}
