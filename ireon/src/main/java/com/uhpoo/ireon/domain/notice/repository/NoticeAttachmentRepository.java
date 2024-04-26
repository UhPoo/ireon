package com.uhpoo.ireon.domain.notice.repository;

import com.uhpoo.ireon.domain.notice.NoticeAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 공지사항 첨부파일 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface NoticeAttachmentRepository extends JpaRepository<NoticeAttachment, Long> {
}
