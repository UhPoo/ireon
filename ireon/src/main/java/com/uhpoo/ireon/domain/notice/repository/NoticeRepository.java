package com.uhpoo.ireon.domain.notice.repository;

import com.uhpoo.ireon.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 공지사항 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
