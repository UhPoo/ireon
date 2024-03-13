package com.uhpoo.ireon.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uhpoo.ireon.domain.common.Address;
import com.uhpoo.ireon.domain.common.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false, unique = true,length = 30)
    private String nickname;

    @Column(nullable = false)
    @JsonIgnore
    private String encryptedPwd;

    @Column(length = 20)
    private String name;

    @Embedded
    private Address address;

    @Column(length = 13)
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    private UserRole userRole;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    private Member(String email, String nickname, String encryptedPwd) {
        this.email = email;
        this.nickname = nickname;
        this.encryptedPwd = encryptedPwd;
        this.userRole = UserRole.USRE;
        this.active = true;
    }
}
