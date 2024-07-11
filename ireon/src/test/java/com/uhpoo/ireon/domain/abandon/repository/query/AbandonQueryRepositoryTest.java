package com.uhpoo.ireon.domain.abandon.repository.query;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.domain.abandon.*;
import com.uhpoo.ireon.domain.abandon.dto.AbandonDetailDto;
import com.uhpoo.ireon.domain.abandon.dto.SearchCondition;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonAttachmentRepository;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonRepository;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonScrapRepository;
import com.uhpoo.ireon.domain.common.Address;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import com.uhpoo.ireon.global.file.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 유기동물 게시글 쿼리 레포지토리 테스트
 *
 * @author 최영환
 */
@Slf4j
class AbandonQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private AbandonQueryRepository abandonQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AbandonRepository abandonRepository;

    @Autowired
    private AbandonScrapRepository abandonScrapRepository;

    @Autowired
    private AbandonAttachmentRepository abandonAttachmentRepository;

    @DisplayName("검색 조건 없이 유기동물 게시글 목록 조회")
    @Test
    void getAbandonsWithoutCondition() {
        // given
        Member author1 = createMember("author1@gmail.com", "작성자1");
        Member author2 = createMember("author2@gmail.com", "작성자2");
        Member member = createMember("member@gmail.com", "일반회원1");

        Address address1 = createAddress("11111", "서울시 송파구 토성로", "서울시 송파구 풍납동", "비밀1");
        Address address2 = createAddress("22222", "서울시 송파구 송파로", "서울시 송파구 송파동", "비밀2");

        AnimalInfo info1 = createAnimalInfo(AnimalType.DOG, "말티즈", Gender.FEMALE, 2);
        AnimalInfo info2 = createAnimalInfo(AnimalType.CAT, "코리안 숏헤어", Gender.MALE, 3);
        AnimalInfo info3 = createAnimalInfo(AnimalType.DOG, "믹스", Gender.FEMALE, 1);

        Abandon abandon1 = createAbandon(info1, address1, author1, "말티즈 입양처 구해요",
                "글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용",
                VaccinationStatus.FIRST, DeSexing.UNDONE, AbandonStatus.SEARCHING, "010-1234-5678");

        Abandon abandon2 = createAbandon(info2, address2, author2, "임보 중인 삼색 고양이에요",
                "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이" +
                        "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이",
                VaccinationStatus.ZERO, DeSexing.DONE, AbandonStatus.PROTECTING, "010-5678-5678");

        Abandon abandon3 = createAbandon(info3, address1, author1, "믹스견 입양처 구해요",
                "믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양",
                VaccinationStatus.ZERO, DeSexing.UNDONE, AbandonStatus.PROTECTING, "010-1234-5678");

        abandonRepository.saveAll(List.of(abandon1, abandon2, abandon3));

        createAttachment(abandon3);

        SearchCondition condition = SearchCondition.of(null);

        // when
        List<AbandonResponse> responses =
                abandonQueryRepository.getAbandonsByCondition(condition, 0L, member.getNickname());
        log.debug("responses={}", responses);

        // then
        assertThat(responses).isNotEmpty();
        assertThat(responses).hasSize(3);
    }

    @DisplayName("제목과 내용에 키워드가 포함되는 유기동물 게시글 목록 조회")
    @Test
    void getAbandonsWithCondition() {
        // given
        Member author1 = createMember("author1@gmail.com", "작성자1");
        Member author2 = createMember("author2@gmail.com", "작성자2");
        Member member = createMember("member@gmail.com", "일반회원1");

        Address address1 = createAddress("11111", "서울시 송파구 토성로", "서울시 송파구 풍납동", "비밀1");
        Address address2 = createAddress("22222", "서울시 송파구 송파로", "서울시 송파구 송파동", "비밀2");

        AnimalInfo info1 = createAnimalInfo(AnimalType.DOG, "말티즈", Gender.FEMALE, 2);
        AnimalInfo info2 = createAnimalInfo(AnimalType.CAT, "코리안 숏헤어", Gender.MALE, 3);
        AnimalInfo info3 = createAnimalInfo(AnimalType.DOG, "믹스", Gender.FEMALE, 1);

        Abandon abandon1 = createAbandon(info1, address1, author1, "말티즈 입양처 구해요",
                "글내용글내용글내용강아지글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용",
                VaccinationStatus.FIRST, DeSexing.UNDONE, AbandonStatus.SEARCHING, "010-1234-5678");

        Abandon abandon2 = createAbandon(info2, address2, author2, "임보 중인 삼색 고양이에요",
                "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이" +
                        "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이",
                VaccinationStatus.ZERO, DeSexing.DONE, AbandonStatus.PROTECTING, "010-5678-5678");

        Abandon abandon3 = createAbandon(info3, address1, author1, "믹스 강아지 입양처 구해요",
                "믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양",
                VaccinationStatus.ZERO, DeSexing.UNDONE, AbandonStatus.PROTECTING, "010-1234-5678");

        abandonRepository.saveAll(List.of(abandon1, abandon2, abandon3));

        createAttachment(abandon3);

        SearchCondition condition = SearchCondition.of("강아지");

        // when
        List<AbandonResponse> responses =
                abandonQueryRepository.getAbandonsByCondition(condition, 0L, member.getNickname());
        log.info("responses={}", responses);

        // then
        assertThat(responses).isNotEmpty();
        assertThat(responses).hasSize(2);
    }

    @DisplayName("작성된 글이 없으면 빈 리스트를 반환한다.")
    @Test
    void getEmptyAbandons() {
        // given
        Member member = createMember("member@gmail.com", "일반회원1");

        SearchCondition condition = SearchCondition.of(null);

        // when
        List<AbandonResponse> responses =
                abandonQueryRepository.getAbandonsByCondition(condition, 0L, member.getNickname());
        log.info("responses={}", responses);

        // then
        assertThat(responses).isEmpty();

    }

    @DisplayName("PK 와 로그인 중인 회원 닉네임으로 유기동물 게시글을 조회할 수 있다.")
    @Test
    void getAbandonByIdAndNickname() {
        // given
        Member author1 = createMember("author1@gmail.com", "작성자1");
        Member author2 = createMember("author2@gmail.com", "작성자2");
        Member member = createMember("member@gmail.com", "일반회원1");

        Address address1 = createAddress("11111", "서울시 송파구 토성로", "서울시 송파구 풍납동", "비밀1");
        Address address2 = createAddress("22222", "서울시 송파구 송파로", "서울시 송파구 송파동", "비밀2");

        AnimalInfo info1 = createAnimalInfo(AnimalType.DOG, "말티즈", Gender.FEMALE, 2);
        AnimalInfo info2 = createAnimalInfo(AnimalType.CAT, "코리안 숏헤어", Gender.MALE, 3);
        AnimalInfo info3 = createAnimalInfo(AnimalType.DOG, "믹스", Gender.FEMALE, 1);

        Abandon abandon1 = createAbandon(info1, address1, author1, "말티즈 입양처 구해요",
                "글내용글내용글내용강아지글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용",
                VaccinationStatus.FIRST, DeSexing.UNDONE, AbandonStatus.SEARCHING, "010-1234-5678");

        Abandon abandon2 = createAbandon(info2, address2, author2, "임보 중인 삼색 고양이에요",
                "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이" +
                        "삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이삼색이",
                VaccinationStatus.ZERO, DeSexing.DONE, AbandonStatus.PROTECTING, "010-5678-5678");

        Abandon abandon3 = createAbandon(info3, address1, author1, "믹스 강아지 입양처 구해요",
                "믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양믹스견입양",
                VaccinationStatus.ZERO, DeSexing.UNDONE, AbandonStatus.PROTECTING, "010-1234-5678");

        abandonRepository.saveAll(List.of(abandon1, abandon2, abandon3));

        createAttachment(abandon3);

        createAbandonScrap(member, abandon1);

        // when
        AbandonDetailDto dto = abandonQueryRepository.getAbandonByIdAndNickname(abandon1.getId(), member.getNickname());
        log.info("dto={}", dto);

        // then
        assertThat(dto).isNotNull();
    }

    private Member createMember(String email, String nickname) {
        Member author = Member.builder()
                .email(email)
                .nickname(nickname)
                .encryptedPwd("test1234")
                .build();
        return memberRepository.save(author);
    }

    private AnimalInfo createAnimalInfo(AnimalType animalType, String detail, Gender gender, int age) {
        return AnimalInfo.builder()
                .animalType(animalType)
                .animalDetail(detail)
                .animalGender(gender)
                .age(age)
                .build();
    }

    private Address createAddress(String zipcode, String road, String jibun, String detail) {
        return Address.builder()
                .zipcode(zipcode)
                .road(road)
                .jibun(jibun)
                .detail(detail)
                .build();
    }

    private Abandon createAbandon(AnimalInfo info, Address address, Member member, String title, String content, VaccinationStatus vaccinationStatus, DeSexing deSexing, AbandonStatus abandonStatus, String phoneNumber) {
        return Abandon.builder()
                .title(title)
                .content(content)
                .animalInfo(info)
                .vaccinationStatus(vaccinationStatus)
                .deSexing(deSexing)
                .abandonStatus(abandonStatus)
                .address(address)
                .phoneNumber(phoneNumber)
                .member(member)
                .build();
    }

    private void createAttachment(Abandon abandon) {
        AbandonAttachment attachment = AbandonAttachment.builder()
                .abandon(abandon)
                .uploadFile(UploadFile.builder().uploadFileName("믹스견").storeFileName("IMG_URL").build())
                .build();

        abandonAttachmentRepository.save(attachment);
    }

    private void createAbandonScrap(Member member, Abandon abandon) {
        AbandonScrap scrap = AbandonScrap.builder()
                .abandon(abandon)
                .member(member)
                .build();

        abandonScrapRepository.save(scrap);
    }
}