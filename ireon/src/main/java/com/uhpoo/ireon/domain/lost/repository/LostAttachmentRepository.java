package com.uhpoo.ireon.domain.lost.repository;

import com.uhpoo.ireon.domain.lost.LostAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 실종동물 게시글 첨부파일 CUD 레포지토리
 *
 * @author CYJ
 */
@Repository
public interface LostAttachmentRepository extends JpaRepository<LostAttachment, Long> {

}
