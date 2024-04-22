package com.uhpoo.ireon.api.service.notice;

import com.uhpoo.ireon.api.service.notice.dto.CreateNoticeDto;
import com.uhpoo.ireon.api.service.notice.dto.EditNoticeDto;
import com.uhpoo.ireon.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 공지사항 CUD 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 등록
     *
     * @param dto      등록할 공지사항 정보
     * @param nickname 현재 로그인 중인 회원의 닉네임
     * @return 등록된 공지사항 PK
     */
    public Long createNotice(CreateNoticeDto dto, String nickname) {
        return null;
    }

    /**
     * 공지사항 수정
     *
     * @param dto      수정할 공지사항 정보
     * @param nickname 현재 로그인 중인 회원의 닉네임
     * @return 수정된 공지사항 PK
     */
    public Long editNotice(EditNoticeDto dto, String nickname) {
        return null;
    }

    /**
     * 공지사항 삭제
     *
     * @param noticeId 삭제할 공지사항 PK
     * @param nickname 현재 로그인 중인 회원의 닉네임
     * @return 삭제된 공지사항 PK
     */
    public Long deleteNotice(Long noticeId, String nickname) {
        return null;
    }
}