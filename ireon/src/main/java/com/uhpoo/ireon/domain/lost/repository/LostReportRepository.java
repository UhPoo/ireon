package com.uhpoo.ireon.domain.lost.repository;

import com.uhpoo.ireon.domain.lost.LostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 실종동물 게시글 신고 CUD 레포지토리
 *
 * @author 최예지
 */
@Repository
public interface LostReportRepository extends JpaRepository<LostReport, Long> {
}
