package com.uhpoo.ireon.domain.lost.repository;

import com.uhpoo.ireon.domain.lost.LostScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 실종동물 스크랩 레포지토리
 *
 * @author yekki
 */
@Repository
public interface LostScrapRepository  extends JpaRepository<LostScrap, Long> {
}
