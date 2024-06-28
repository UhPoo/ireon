package com.uhpoo.ireon.domain.abandon.repository.command;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.domain.abandon.Abandon;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.common.Address;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 유기동물 CRUD 레포지토리 테스트
 *
 * @author 최영환
 */
@Slf4j
class AbandonRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AbandonRepository abandonRepository;

    @DisplayName("유기동물 게시글 등록")
    @Test
    void save() {
        // given
        Member member = createMember();
        AnimalInfo animalInfo = createAnimalInfo();
        Address address = createAddress();

        Abandon abandon = createAbandon(animalInfo, address, member);

        // when
        Abandon savedAbandon = abandonRepository.save(abandon);

        // then
        assertThat(abandon).isSameAs(savedAbandon);
        assertThat(abandon.getTitle()).isEqualTo(savedAbandon.getTitle());
        assertThat(savedAbandon.getId()).isNotNull();
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

    private Address createAddress() {
        return Address.builder()
                .zipcode("11111")
                .road("서울시 송파구 토성로")
                .jibun("서울시 송파구 풍납동")
                .detail("비밀")
                .build();
    }

    private Abandon createAbandon(AnimalInfo animalInfo, Address address, Member member) {
        return Abandon.builder()
                .title("제목")
                .content("글내용")
                .animalInfo(animalInfo)
                .vaccinationStatus(VaccinationStatus.SECOND)
                .deSexing(DeSexing.UNDONE)
                .abandonStatus(AbandonStatus.SEARCHING)
                .address(address)
                .phoneNumber("010-1234-5678")
                .member(member)
                .build();
    }
}