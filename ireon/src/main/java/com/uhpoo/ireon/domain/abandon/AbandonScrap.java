package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 스크랩 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbandonScrap extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abandon_scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "abandon_id")
    private Abandon abandon;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    private AbandonScrap(Member member, Abandon abandon, Boolean active) {
        this.member = member;
        this.abandon = abandon;
        this.active = active;
    }

}
