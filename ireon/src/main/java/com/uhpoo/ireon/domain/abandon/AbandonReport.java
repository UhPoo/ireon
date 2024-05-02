package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 신고 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbandonReport extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abandon_report_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "abandon_id")
    private Abandon abandon;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public AbandonReport(String content, Abandon abandon, Member member) {
        this.content = content;
        this.abandon = abandon;
        this.member = member;
    }
}
