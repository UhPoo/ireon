package com.uhpoo.ireon.domain.abandon;

import com.uhpoo.ireon.domain.TimeBaseEntity;
import com.uhpoo.ireon.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private AnimalType animalType;

    @Column(nullable = false, length = 50)
    private String animalDetail;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDateTime foundTime;
    @Column(nullable = false)
    private String foundLoc;
    @Column(nullable = false)
    private String currentLoc;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Abandon(String title, String content, AnimalType animalType, String animalDetail, Gender gender,
                    LocalDateTime foundTime, String foundLoc, String currentLoc, String phoneNumber,
                    Boolean active, Member member) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.gender = gender;
        this.foundTime = foundTime;
        this.foundLoc = foundLoc;
        this.currentLoc = currentLoc;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.member = member;
    }
}
