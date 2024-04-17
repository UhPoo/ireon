package com.uhpoo.ireon.domain.board.repository;

import com.uhpoo.ireon.domain.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 자유게시판 댓글 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
