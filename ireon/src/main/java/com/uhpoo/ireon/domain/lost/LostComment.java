package com.uhpoo.ireon.domain.lost;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 덧글 엔티티
 *
 * @author yekki
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostComment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_comment_id")
    private Long id;


    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String comment;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lost_id")
    private Lost lost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public LostComment(Long id, String comment, Boolean active, Lost lost, Member member) {
        this.id = id;
        this.comment = comment;
        this.active = active;
        this.lost = lost;
        this.member = member;
    }
}
