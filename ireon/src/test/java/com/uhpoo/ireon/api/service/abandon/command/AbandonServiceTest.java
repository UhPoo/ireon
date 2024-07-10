package com.uhpoo.ireon.api.service.abandon.command;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonAttachmentRepository;
import com.uhpoo.ireon.domain.abandon.repository.command.AbandonRepository;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 유기동물 게시글 CUD 서비스 테스트
 *
 * @author 최영환
 */
@Slf4j
class AbandonServiceTest extends IntegrationTestSupport {

    @Autowired
    private AbandonService abandonService;
    @Autowired
    private AbandonRepository abandonRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AbandonAttachmentRepository attachmentRepository;

    @DisplayName("사용자는 유기동물 게시판에 첨부파일 없이 글을 등록할 수 있다.")
    @Test
    void createAbandonWithoutAttachment() throws IOException {
        // given
        createMember();

        CreateAbandonDto dto = getCreateAbandonDto();

        // when
        Long saveId = abandonService.createAbandon(dto, "nickname", null);

        // then
        assertThat(saveId).isNotNull();
    }

    @DisplayName("사용자는 유기동물 게시판에 첨부파일과 함께 글을 등록할 수 있다.")
    @Test
    void createAbandonWithAttachment() throws IOException {
        // given
        createMember();

        CreateAbandonDto dto = getCreateAbandonDto();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        // when
        Long saveId = abandonService.createAbandon(dto, "nickname", file);

        // then
        assertThat(saveId).isNotNull();
    }

    private void createMember() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .nickname("nickname")
                .encryptedPwd("test1234")
                .build();
        memberRepository.save(member);
    }

    private CreateAbandonDto getCreateAbandonDto() {

        return CreateAbandonDto.builder()
                .title("제목")
                .content("글내용")
                .animalType(AnimalType.DOG.toString())
                .animalDetail("말티즈")
                .animalGender(Gender.FEMALE.toString())
                .animalAge(2)
                .vaccinationStatus(VaccinationStatus.SECOND.toString())
                .deSexing(DeSexing.UNDONE.toString())
                .abandonStatus(AbandonStatus.SEARCHING.toString())
                .zipcode("11111")
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .build();
    }
}