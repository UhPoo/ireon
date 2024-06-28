package com.uhpoo.ireon.domain.abandon.repository.command;

import com.uhpoo.ireon.domain.abandon.AbandonReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유기동물 게시글 신고 CUD 레포지토리
 *
 * @author 최영환
 */
public interface AbandonReportRepository extends JpaRepository<AbandonReport, Long> {
}
