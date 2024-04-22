package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 댓글 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbandonComment extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abandon_comment_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "abandon_id")
    private Abandon abandon;

    @Builder
    private AbandonComment(String content, Boolean active, Member member, Abandon abandon) {
        this.content = content;
        this.active = active;
        this.member = member;
        this.abandon = abandon;
    }
}
