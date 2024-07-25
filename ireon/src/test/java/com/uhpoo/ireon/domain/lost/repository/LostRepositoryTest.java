package com.uhpoo.ireon.domain.lost.repository;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.lost.Lost;
import com.uhpoo.ireon.domain.lost.LostAddress;
import com.uhpoo.ireon.domain.lost.LostStatus;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 실종동물 CRUD 레포지토리 테스트
 *
 * @author CYJ
 */

@Slf4j
public class LostRepositoryTest  extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LostRepository lostRepository;

    @DisplayName("실종동물 게시글 등록")
    @Test
    void save() {
        // given
        Member member = createMember();
        AnimalInfo animalInfo = createAnimalInfo();
        LostAddress lostAdress = createLostAddress();

        Lost lost = Lost.builder()
                .title("제목")
                .content("글내용")
                .animalInfo(animalInfo)
                .deSexing(DeSexing.UNDONE)
                .lostStatus(LostStatus.LOST)
                .lostAddress(lostAdress)
                .phoneNumber("010-1234-5678")
                .member(member)
                .member(member)
                .build();

        // when
        Lost savedLost = lostRepository.save(lost);

        // then
        assertThat(lost).isSameAs(savedLost);
        assertThat(lost.getTitle()).isEqualTo(savedLost.getTitle());
        assertThat(savedLost.getId()).isNotNull();
    }

    private Member createMember() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .nickname("nickname")
                .encryptedPwd("test1234")
                .build();
        return memberRepository.save(member);
    }

    private AnimalInfo createAnimalInfo() {
        return AnimalInfo.builder()
                .animalType(AnimalType.DOG)
                .animalDetail("말티즈")
                .animalGender(Gender.FEMALE)
                .age(2)
                .build();
    }

    private LostAddress createLostAddress() {
        return LostAddress.LostAddressBuilder()
                .zipcode("11111")
                .road("서울시 송파구 토성로")
                .jibun("서울시 송파구 풍납동")
                .detail("비밀")
                .build();
    }
}
