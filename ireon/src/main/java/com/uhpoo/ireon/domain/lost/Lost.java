package com.uhpoo.ireon.domain.lost;

import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 엔티티
 *
 * @author CYJ
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lost extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Embedded
    private AnimalInfo animalInfo;

    @Column(nullable = false)
    private DeSexing deSexing;

    @Column(nullable = false, length = 20)
    private LostStatus lostStatus;

    @Embedded
    private LostAddress lostAddress;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Lost(String title, String content, AnimalInfo animalInfo, DeSexing deSexing,
                 LostStatus lostStatus, LostAddress lostAddress, String phoneNumber, Member member) {
        this.title = title;
        this.content = content;
        this.animalInfo = animalInfo;
        this.deSexing = deSexing;
        this.lostStatus = lostStatus;
        this.lostAddress = lostAddress;
        this.phoneNumber = phoneNumber;
        this.active = true;
        this.member = member;
    }
}
