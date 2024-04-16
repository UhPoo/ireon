package com.uhpoo.ireon.domain.lost.repository;

import com.uhpoo.ireon.domain.lost.Lost;
import com.uhpoo.ireon.domain.lost.LostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 실종동물 댓글 CUD 레포지토리
 *
 * @author yekk1
 */
@Repository
public interface LostCommentRepository extends JpaRepository<LostComment, Long> {
}
