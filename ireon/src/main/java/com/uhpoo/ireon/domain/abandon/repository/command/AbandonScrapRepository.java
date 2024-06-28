package com.uhpoo.ireon.domain.abandon.repository.command;

import com.uhpoo.ireon.domain.abandon.AbandonScrap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유기동물 스크랩 CUD 레포지토리
 *
 * @author 최영환
 */
public interface AbandonScrapRepository extends JpaRepository<AbandonScrap, Long> {
}
