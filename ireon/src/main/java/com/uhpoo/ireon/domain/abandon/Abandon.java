package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 엔티티
 *
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Abandon extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abandon_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Embedded
    private AnimalInfo animalInfo;

    @Column(nullable = false, length = 20)
    private VaccinationStatus vaccinationStatus;

    @Column(nullable = false)
    private Boolean neutralized;

    @Column(nullable = false, length = 20)
    private AbandonStatus abandonStatus;

    @Embedded
    private Address address;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Abandon(String title, String content, AnimalInfo animalInfo, VaccinationStatus vaccinationStatus,
                   Boolean neutralized, AbandonStatus abandonStatus, Address address, String phoneNumber,
                    Boolean active, Member member) {
        this.title = title;
        this.content = content;
        this.animalInfo = animalInfo;
        this.vaccinationStatus = vaccinationStatus;
        this.neutralized = neutralized;
        this.abandonStatus = abandonStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.member = member;
    }
}
