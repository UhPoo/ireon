package com.uhpoo.ireon.domain.abandon.repository.command;

import com.uhpoo.ireon.domain.abandon.AbandonComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 유기동물 게시글 댓글 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface AbandonCommentRepository extends JpaRepository<AbandonComment, Long> {
}
