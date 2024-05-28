package com.uhpoo.ireon.domain.board;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.global.file.UploadFile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 자유게시판 게시글 첨부파일 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardAttachment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_attachment_id")
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    private BoardAttachment(UploadFile uploadFile, Board board, Boolean active) {
        this.uploadFile = uploadFile;
        this.board = board;
        this.active = active;
    }
}
