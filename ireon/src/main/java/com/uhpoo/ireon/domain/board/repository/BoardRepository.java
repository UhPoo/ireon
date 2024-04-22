package com.uhpoo.ireon.domain.board.repository;

import com.uhpoo.ireon.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 자유 게시판 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
