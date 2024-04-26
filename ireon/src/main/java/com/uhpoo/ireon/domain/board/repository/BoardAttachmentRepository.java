package com.uhpoo.ireon.domain.board.repository;

import com.uhpoo.ireon.domain.board.BoardAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 자유게시판 게시글 첨부파일 CUD 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface BoardAttachmentRepository extends JpaRepository<BoardAttachment, Long> {
}
