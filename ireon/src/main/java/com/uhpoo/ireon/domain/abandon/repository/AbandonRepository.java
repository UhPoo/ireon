package com.uhpoo.ireon.domain.abandon.repository;

import com.uhpoo.ireon.domain.abandon.Abandon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 유기동물 게시글 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface AbandonRepository extends JpaRepository<Abandon, Long> {
}
