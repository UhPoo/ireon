package com.uhpoo.ireon.api.service.notice;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.notice.response.NoticeDetailResponse;
import com.uhpoo.ireon.api.controller.notice.response.NoticeResponse;
import com.uhpoo.ireon.domain.notice.repository.NoticeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 공지사항 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    /**
     * 공지사항 목록 조회
     *
     * @param lastNoticeId 마지막으로 조회된 공지사항 PK
     * @return 공지사항 목록
     */
    public PageResponse<List<NoticeResponse>> getNotices(Long lastNoticeId) {
        return null;
    }

    /**
     * 공지사항 상세 조회
     *
     * @param noticeId 조회할 공지사항 PK
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 공지사항 상세 정보
     */
    public NoticeDetailResponse getNotice(Long noticeId, String nickname) {
        return null;
    }
}
