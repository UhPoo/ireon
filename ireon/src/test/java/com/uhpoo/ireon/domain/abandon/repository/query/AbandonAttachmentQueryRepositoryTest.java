package com.uhpoo.ireon.domain.abandon.repository.query;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.domain.abandon.Abandon;
import com.uhpoo.ireon.domain.abandon.AbandonAttachment;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonAttachmentRepository;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonRepository;
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
class AbandonAttachmentQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private AbandonAttachmentQueryRepository abandonAttachmentQueryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AbandonRepository abandonRepository;

    @Autowired
    private AbandonAttachmentRepository abandonAttachmentRepository;

    @DisplayName("")
    @Test
    void getAttachmentsByAbandonId() {
        // given
        Member author1 = createMember("author1@gmail.com", "작성자1");

        Address address1 = createAddress("11111", "서울시 송파구 토성로", "서울시 송파구 풍납동", "비밀1");

        AnimalInfo info1 = createAnimalInfo(AnimalType.DOG, "말티즈", Gender.FEMALE, 2);

        Abandon abandon = createAbandon(info1, address1, author1, "말티즈 입양처 구해요",
                "글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용글내용",
                VaccinationStatus.FIRST, DeSexing.UNDONE, AbandonStatus.SEARCHING, "010-1234-5678");

        abandonRepository.save(abandon);

        createAttachment(abandon, "믹스견", "IMG_URL1");
        createAttachment(abandon, "말티즈", "IMG_URL2");
        createAttachment(abandon, "고양이", "IMG_URL3");

        // when
        List<String> attachments = abandonAttachmentQueryRepository.getAttachmentsByAbandonId(abandon.getId());
        attachments.forEach(s -> log.info("storeFileName: {}", s));

        // then
        assertThat(attachments).isNotEmpty();
        assertThat(attachments).hasSize(3);
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

    private void createAttachment(Abandon abandon, String uploadFileName, String storeFileName) {
        AbandonAttachment attachment = AbandonAttachment.builder()
                .abandon(abandon)
                .uploadFile(UploadFile.builder().uploadFileName(uploadFileName).storeFileName(storeFileName).build())
                .build();

        abandonAttachmentRepository.save(attachment);
    }
}