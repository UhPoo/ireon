package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.global.file.UploadFile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 첨부파일 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbandonAttachment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abandon_attachment_id")
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "abandon_id")
    private Abandon abandon;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    private AbandonAttachment(UploadFile uploadFile, Abandon abandon) {
        this.uploadFile = uploadFile;
        this.abandon = abandon;
        this.active = true;
    }
}
