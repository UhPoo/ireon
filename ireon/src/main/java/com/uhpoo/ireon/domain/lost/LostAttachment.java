package com.uhpoo.ireon.domain.lost;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.global.file.UploadFile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 첨부파일 엔티티
 *
 * @author 최예지
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostAttachment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_attchment_id")
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lost_id")
    private Lost lost;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    public LostAttachment(UploadFile uploadFile, Lost lost, Boolean active) {
        this.uploadFile = uploadFile;
        this.lost = lost;
        this.active = active;
    }
}
