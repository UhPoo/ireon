package com.uhpoo.ireon.domain.notice;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.global.file.UploadFile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공지사항 첨부파일 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeAttachment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_attachment_id")
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Notice notice;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    public NoticeAttachment(UploadFile uploadFile, Notice notice, Boolean active) {
        this.uploadFile = uploadFile;
        this.notice = notice;
        this.active = active;
    }
}
